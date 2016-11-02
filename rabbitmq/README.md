


exchange
channel
queue

###### work queue



在默认情况下，RabbitMQ按顺序发送消息到下一个Consumer。平均下来每个consumer获得相同数量到消息。这种分发消息到方式被称为round-robin.


###### message acknowledgment
RabbitMQ为了保证消息不丢失，支持消息回执(message acknowledgment). 消息回执是由consumer发送给RabbitMQ，通知某个特定的消息已经收到，并且处理完毕，RabbitMQ可以删除消息。

如果一个consumer死掉(比如连接关闭)，并且没有发送回执，那么RabbitMQ就认为这个消息没有完成处理，会把消息重新放到queue。如果这时候有其他的消费者，消息会被分发到其他的消费者。依此来保证消息的不丢失。

没有消息超时，所以即使一个消息处理很长时间也没有问题。

消息回执是默认打开的。



###### 消息持久化

我们知道即使是消费者死亡，消息也不会丢失。但是如果RabbitMQ一旦退出或者崩溃，消息就会丢失。除非我们把消息队列和消息持久化。
我们要声明队列为可持久化的

```java
boolean durable = true;
channel.queueDeclare("hello", durable, false, false, null);
```
然后要标记消息为持久化的
```java
channel.basicPublish("", "task_queue",
            MessageProperties.PERSISTENT_TEXT_PLAIN,
            message.getBytes());
```

消息持久化并不能百分之百的保证消息不会丢失。即使我们设置消息为持久化，仍然有一个短暂的时间窗口在RabbitMQ接收到消息，但是还没有保存。RabbitMQ也不会对于每条消息都触发fsync(同步内存中所有已修改的文件数据到储存设备), 可能只是把消息写入到缓存。 这并不是消息持久化到强保证，如果需要更强到消息持久化保证，可以使用发布确认。


###### 公平分发

有时间消息的分发并不是如我们想象的那样。比如我们现在有两个消费者，当我们奇数的消息处理非常耗时，而偶数的消息处理的很快，这样就会造成一个消费者非常的繁忙，另外一个消费者非常的轻松，并没有充分的利用资源。但是RabbitMQ并不了解这些信息，他依然均等的分发消息。

这是因为RabbitMQ只是分发消息而不关心每个消费者有多少未发送回执的消息。只是盲目的均等分发。为了避免这种情况，我们可以使用basicQos方法来设置prefetchCount为1. 它告诉RabbitMQ每次只发送一条消息给消费者。直到消费者发送前一个消息的回执，再发送新的消息给该消费者。RabbitMQ将会把消息发送给下一个可用的消费者。

如果所有的消息都是繁忙的，那么你的队列可能会被填满，这时候需要增加消费者或者使用其他策略。


###### 发布/订阅
Work Queue是一个消息只会发送给一个Consumer，而订阅/发布模式会把消息发送给多个Consumer
Producer并不是直接发送消息到Queue，而是发送到echange，然后echange把消息发送到Queue. exchange是把消息发送到一个queue还是多个queue还是直接丢弃消息都是由exchange到类型来决定到。

exchange到类型有direct, topic, headers, fanout.

###### 临时队列

在Java Client中， RabbitMQ提供了没有参数的queueDeclare()方法来创建一个临时的queue。系统给他生成来一个随机的名字 比如amq.gen-JzTY20BRgKO-HjmUJj0wLg。在连接断开后自动删除。

###### 绑定
在创建了exchange和queue之后，需要告诉exchange发送消息到哪些queue。这种exchange和queue之间的关系我们就叫做binding.
```java
chanel.queueBind(queueName, "logs", "");
```


###### 路由
在绑定时，我们可以使用另外一个参数routingKey. 为了避免和发布消息的rountingKey搞混，我们可以叫做 binding key。
```java
channel.queueBind(queueName, EXCHANGE_NAME, "black");
```

binding key根据exchnage类型的不同也会有所不同。

- direct exchange
  消息会被发送到binding key和消息的routing key相等的queue。
- topic exchange
  发送到topic exchange的message的routing key需要是以点好分割的字符串。比如stock.usd.nyse，最长为255个字节。binding key也一样。binding key有两个通配符

  1. * 匹配任意一个单词
  2. # 匹配0到多个单词
