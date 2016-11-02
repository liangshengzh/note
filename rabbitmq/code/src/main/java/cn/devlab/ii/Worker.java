package cn.devlab.ii;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by zhong on 2016/10/25.
 */
public class Worker {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("[*] Waiting for message. To exit, press CTRL+C");


        Consumer consumer =  new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[x] Received '" + message + "'");

                try{
                    doWork(message);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    System.out.println("[x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }


        };

        channel.basicConsume(QUEUE_NAME, false,  consumer);
    }

    private static void doWork(String message) throws InterruptedException {
        for(char ch: message.toCharArray()){
            if(ch == '.'){
                Thread.sleep(5000);
            }
        }
    }
}
