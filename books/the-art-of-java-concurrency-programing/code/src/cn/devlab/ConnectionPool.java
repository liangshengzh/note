package cn.devlab;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by zhong on 2016/11/29.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize){
        if(initialSize > 0 ){
            for(int i = 0 ; i < initialSize; i++){
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }


    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.add(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills)throws InterruptedException{
        synchronized (pool){
            if(mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remaning = mills;

                while (pool.isEmpty() && remaning > 0){
                    pool.wait(remaning);
                    remaning = future - System.currentTimeMillis();

                }
                Connection connection = null;
                if(!pool.isEmpty()){
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
