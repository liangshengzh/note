package cn.devlab;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhong on 2016/11/14.
 */
public class Application {
    public static void main(String[] args) throws Exception{
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");

        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        template.convertAndSend("Hello Rabbit");

        Thread.sleep(1000);
        ctx.destroy();
    }
}
