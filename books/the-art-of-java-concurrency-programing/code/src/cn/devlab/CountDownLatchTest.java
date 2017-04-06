package cn.devlab;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhong on 2016/11/29.
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws Exception{
        CountDownLatch c = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
            }
        });
        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
                c.countDown();
            }
        });

        t2.start();

        c.await();

        System.out.println("3");
    }


}
