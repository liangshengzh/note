//package cn.devlab;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * Created by zhong on 2016/11/29.
// */
//public class BoundQueue {
//
//    private Object[] items;
//
//    private Lock lock = new ReentrantLock();
//
//    private Condition notFullConditaion = lock.newCondition();
//    private Condition notEmptyCondition = lock.newCondition();
//
//    private int addIndex, removeIndex, count;
//
//    public BoundQueue(int size) {
//        items = new Object[size];
//    }
//
//
//    public void Add(Object item) throws InterruptedException{
//        lock.lock();
//        try {
//            while (count == items.length){
//                notFullConditaion.await();
//            }
//
//            items[addIndex] = item;
//            addIndex++;
//            if(addIndex == items.length){
//                addIndex = 0；
//            }
//            count++;
//            notEmptyCondition.signal();
//        } finally {
//            lock.unlock();
//        }
//    }
//
//
//    public Object remove() throws InterruptedException{
//        lock.lock();
//        try {
//            while (count == 0){
//                notEmptyCondition.await();
//            }
//
//            Object x = items[removeIndex];
//            removeIndex++;
//            if (removeIndex == items.length){
//                removeIndex = 0;
//            }
//            count--;
//            notFullConditaion.signal();
//
//        } finally {
//            lock.unlock();
//        }
//    }
//}
