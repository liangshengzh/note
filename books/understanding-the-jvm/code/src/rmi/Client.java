package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by zhong on 2016/12/2.
 */
public class Client {
    public static void main(String[] args) {
        try {
            MyRemote rhello =(MyRemote) Naming.lookup("rmi://localhost:8888/abc");
            System.out.println(rhello.sayHello());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
