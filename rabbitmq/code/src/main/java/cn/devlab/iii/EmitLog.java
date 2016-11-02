package cn.devlab.iii;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by zhong on 2016/10/26.
 */
public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = getMessage(args);
        channel.basicPublish(EXCHANGE_NAME, "", null
                , message.getBytes());

        System.out.println("[x] Send message " + message);

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