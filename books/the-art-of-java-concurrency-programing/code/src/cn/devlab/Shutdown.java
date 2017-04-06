package cn.devlab;

/**
 * Created by zhong on 2016/11/28.
 */
public class Shutdown {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runner());
        Runner runner = new Runner();
        Thread thread2 = new Thread(runner);

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread1.interrupt();

        runner.cancel();

    }
    static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i ++;
            }
            System.out.println(i);
        }

        public void cancel(){
            on = false;
        }
    }
}
