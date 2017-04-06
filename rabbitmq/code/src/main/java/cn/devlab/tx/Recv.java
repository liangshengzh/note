package cn.devlab.tx;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by zhong on 2016/10/25.
 */
public class Recv {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true   , false, false, null);
        System.out.println("[*] Waiting for message. To exit, press CTRL+C");


        QueueingConsumer qc = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, qc);
            for (int i = 0; i < 1000; ++i) {
                qc.nextDelivery();
                System.out.printf("Consumed %d\n", i);
            }
    }
}
