package cn.devlab.tx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by zhong on 2016/10/25.
 */
public class Send {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.txSelect();
        for(int i = 0; i < 1000; i++) {
            String message = "Hello World";
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, message.getBytes());
            System.out.println("[X] Sent '" + message + "'");
            channel.txCommit();
        }
        channel.close();
        connection.close();
    }
}
