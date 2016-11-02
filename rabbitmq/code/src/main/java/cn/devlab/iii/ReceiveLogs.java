package cn.devlab.iii;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by zhong on 2016/10/27.
 */
public class ReceiveLogs {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection  connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "info");

        System.out.println("Waiting for message. To exit, press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println("[x] Received message " + envelope.getRoutingKey()  + " " + message);
            }
        };

        channel.basicConsume(queueName, true, consumer);
    }
}
