import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 16/10/12.
 *
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 */
public class MonitoringTest1 {

    static class  OOMObject{
        public  byte[] placehoder = new byte[64*1024];
    }


    public static void fillHeap(int num) throws InterruptedException{
        List<OOMObject> list = new ArrayList<>();
        for(int i =0 ; i< num;i++){
            Thread.sleep(100);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception{
        fillHeap(1000);
    }
}
