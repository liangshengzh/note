package cn.devlab.collection;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * Created by zhong on 2016/12/20.
 */
public class SerialMain implements Serializable{
    public static void main(String[] args)throws Exception {
        ByteOutputStream bos = new ByteOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        SerialMain main = new SerialMain();
        oos.writeObject(main);
    }


    private void writeObject(ObjectOutputStream oos) throws IOException{
        System.out.println("seraial");
    }
}
