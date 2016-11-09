package cn.devlab;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.validation.ObjectError;

public class App {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new CachingConnectionFactory();
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        Queue queue = new Queue("myQueue");
        admin.declareQueue(queue);
        TopicExchange exchange = new TopicExchange("myExchange");
        admin.declareExchange(exchange);
        admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("*.red"));


        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        Object listener = new Object() {
            public void handleMessage(String foo){
                System.out.println(foo);
            }
        };


        MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
        container.setMessageListener(adapter);
        container.setQueueNames("myQueue");
        container.start();


        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.convertAndSend("myExchange", "abc.red", "Hello Red");
        Thread.sleep(100);
        container.stop();
    }
}
