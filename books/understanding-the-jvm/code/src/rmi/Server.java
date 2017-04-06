package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by zhong on 2016/12/2.
 */
public class Server {

    public static void main(String[] args) {


        try {
            MyRemote remote = new MyRemoteImpl();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/abc", remote);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
