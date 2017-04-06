package cn.devlab;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhong on 2016/11/29.
 */
public class DefaultThreadPool<T extends Runnable> implements ThreadPoll<T> {

    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKER_NUMBER = 1;

    private final LinkedList<T> jobs = new LinkedList<>();

    private final List<Worker> workers = new ArrayList<>();

    private int workNumber = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNumber = new AtomicLong();


    public DefaultThreadPool() {
        initializedWorker(DEFAULT_WORKER_NUMBERS);
    }

    @Override
    public void execute(T job) {

        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }

    }


    private void initializedWorker(int num){
        for(int i = 0; i < num; i++){
            Worker worker = new Worker();
            workers.add(worker);

            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNumber.incrementAndGet());
            thread.start();
        }
    }

    public DefaultThreadPool(int num) {
        workNumber = num >MAX_WORKER_NUMBERS?MAX_WORKER_NUMBERS:num<MIN_WORKER_NUMBER?MIN_WORKER_NUMBER:num;
        initializedWorker(workNumber);
    }

    @Override
    public void shutdown() {
        for(Worker worker : workers){
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (workers){
            if(num + this.workNumber >MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workNumber;
            }
            initializedWorker(num);
            this.workNumber += num;
        }

    }

    @Override
    public void removeWorkers(int num) {
        synchronized (workers){
            if(num >= this.workNumber){
                throw  new IllegalArgumentException("beyond workNumber");
            }

            int count = 0;

            while (count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workNumber -= num;
        }

    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }



    class Worker implements Runnable{

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                T job = null;

                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    job = jobs.removeFirst();

                    if(job != null){
                        try{
                            job.run();
                        }catch (Exception e){}
                    }
                }
            }
        }


        public void shutdown(){
            running = false;
        }
    }
}
