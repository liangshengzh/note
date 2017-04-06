package cn.devlab;

/**
 * Created by zhong on 2016/11/28.
 */
public class Interrupted {
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){}
        }
    }


    public static void main(String[] args) {
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);


        Thread threadBusy = new Thread(new BusyRunner(), "BusyThread");
        threadBusy.setDaemon(true);

        sleepThread.start();
        threadBusy.start();


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        sleepThread.interrupt();
        threadBusy.interrupt();

        System.out.println("SleepThread interrupted is " +  sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + threadBusy.isInterrupted());



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
