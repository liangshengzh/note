package cn.devlab.cp;

import cn.devlab.ConnectionDriver;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by zhong on 2016/12/10.
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if(initialSize > 0){
            for(int i = 0; i < initialSize; i++){
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }



    public void releaseConnection(Connection connection){

        synchronized (pool){
            pool.addLast(connection);
            pool.notifyAll();
        }

    }
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if(mills < 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remaing = mills;
                while (pool.isEmpty() && remaing >0){
                    pool.wait(remaing);
                    remaing = future - System.currentTimeMillis();
                }
                if(!pool.isEmpty()){
                    return pool.removeFirst();
                }
                return null;
            }
        }
    }
}
