package cn.devlab.cp;

import cn.devlab.ConnectionDriver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by zhong on 2016/12/10.
 */
public class ConnectioDriver {

    static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.equals("commit")){
                Thread.sleep(200);
            }
            return null;
        }
    }

    public static Connection createConnection(){
        return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class[]{Connection.class},new ConnectionHandler());
    }
}
