package cn.devlab.mt;

import java.util.concurrent.*;

/**
 * Created by zhong on 2016/12/25.
 */
public class Main {

    public static void main(String[] args) {


        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        }, 10, 5, TimeUnit.SECONDS);

        ExecutorService es = Executors.newFixedThreadPool(3);

        es.submit(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){
                    i++;
                    if(i > 200){
                        break;
                    }
                    System.out.println("world" + i);
                }

            }
        });
    }

}
