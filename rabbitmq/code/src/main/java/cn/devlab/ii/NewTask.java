package cn.devlab.ii;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * Created by zhong on 2016/10/25.
 */
public class NewTask {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        String message = getMessage(args);
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("[X] Sent '" + message + "'");

        channel.close();
        connection.close();
    }

    private static String getMessage(String[] args) {
        if(args.length < 1){
            return "hello world";
        }
        return joinString(args, " ");
    }

    private static String joinString(String[] args, String delimiter) {

        int length = args.length;

        if(length == 0){
            return "";
        }

        StringBuilder  words = new StringBuilder(args[0]);
        for(String word : args){
            words.append(delimiter).append(word);
        }

        return words.toString();
    }
}
