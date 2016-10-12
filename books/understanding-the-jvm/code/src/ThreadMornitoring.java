import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhong on 16/10/12.
 */
public class ThreadMornitoring {

    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
              while (true){}
            }
        }, "testBusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try{
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        },"testLOckThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();

        Object obj = new Object();
        createLockThread(obj);
    }
}
