package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by zhong on 2016/12/2.
 */
public interface MyRemote extends Remote {
    String sayHello() throws RemoteException;
}
