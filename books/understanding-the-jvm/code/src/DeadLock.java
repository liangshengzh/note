/**
 * Created by zhong on 16/10/12.
 */
public class DeadLock {

    static class SyncAddRunable implements Runnable{
        int a, b;

        public SyncAddRunable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++){
            new Thread(new SyncAddRunable(1,3)).start();
            new Thread(new SyncAddRunable(3,1)).start();
        }
    }
}
