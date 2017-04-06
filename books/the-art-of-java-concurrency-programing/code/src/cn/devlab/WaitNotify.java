package cn.devlab;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhong on 2016/11/29.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock  = new Object();

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + "flag is true. Wait @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + "flag is false. Running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            }
        }
    }


    static class Notify implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println( Thread.currentThread()  + "Hold lock notify @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock){
                System.out.println( Thread.currentThread()  + "Hold lock again. Sleep @" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread  notifyThread = new Thread(new Notify(), "Notify Thread");
        notifyThread.start();
    }
}
