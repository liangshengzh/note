package cn.devlab;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhong on 2016/11/29.
 */
public class TwinsLock implements Lock {
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    static class Sync extends AbstractQueuedSynchronizer{
        public Sync(int count) {
            if(count <= 0){
                throw new IllegalArgumentException("count must be larger than zero");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reducedCount) {
            for(;;){
                int current = getState();
                int newCount = current - reducedCount;
                if(newCount <0 || compareAndSetState(current, newCount)){
                    return newCount;
                }
            }

        }

        @Override
        protected boolean tryReleaseShared(int returnedCount) {
           for(;;){
               int current = getState();
               int newCount = current + returnedCount;
               if(compareAndSetState(current, newCount)){
                   return true;
               }
           }
        }
    }
}
