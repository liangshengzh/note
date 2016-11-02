package cn.devlab.iiv;

import com.rabbitmq.client.*;

import java.util.UUID;

/**
 * Created by zhong on 2016/10/27.
 */
public class RPCClient {

    private static final String RPC_QUEUE_NAME = "rpc_queue";

    public static void main(String[] args)  throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);
        String callbackQueueName = channel.queueDeclare().getQueue();
        String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties  props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(callbackQueueName).build();
        System.out.println(" [x] Requesting fib(30)");
        String message = "30";
        channel.basicPublish("",RPC_QUEUE_NAME, props,  message.getBytes());

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(callbackQueueName, consumer);
        String response = null;
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if(delivery.getProperties().getCorrelationId().equals(corrId)){
                response = new String(delivery.getBody());
                System.out.println(" [.] Got '" + response + "'");
                break;
            }

        }


        channel.close();
        connection.close();
    }
}
