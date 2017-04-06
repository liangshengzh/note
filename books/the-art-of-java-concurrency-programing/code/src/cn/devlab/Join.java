package cn.devlab;

/**
 * Created by zhong on 2016/11/29.
 */
public class Join {


    public static void main(String[] args) throws Exception{
        Thread previous = Thread.currentThread();

        for(int i = 0; i < 10; i++){
            Thread t = new Thread(new Domino(previous), String.valueOf(i));
            previous = t;
            t.start();
        }
        Thread.sleep(50000);
        System.out.println(Thread.currentThread().getName() + " terminated");
    }
    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminated");
        }
    }
}
