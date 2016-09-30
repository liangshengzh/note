/**
 * Created by zhong on 16/9/30.
 *-verbose:gc -Xms20M -Xmx20m -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * -XX:PretenureSizeThreshold=3145788
 *
 * -XX:MaxTenuringThreshold=1
 * -XX:+PrintTenuringDistribution
 */
public class GC {
    private static final int _1MB = 1024*1024;

    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[5 * _1MB];


    }

    public static void testPretenureSizeThreshold(){

        byte[] allocation = new byte[4*_1MB];
    }

    public static void testTenuringThreshold(){
        byte[] allocation1, allocation2, allocation3;

        allocation1 = new byte[_1MB/10];
        allocation2 = new byte[4*_1MB];
        allocation3 = new byte[4*_1MB];
        allocation3 = null;
        allocation3 = new byte[4*_1MB];

    }
    public static void testTenuringThreshold2(){
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[_1MB/4];
        allocation2 = new byte[_1MB/4];
        allocation3 = new byte[4*_1MB];
        allocation4 = new byte[4*_1MB];
        allocation4 = null;
        allocation4 = new byte[4*_1MB];

    }

    public static void main(String[] args) {
        //testAllocation();

        //testPretenureSizeThreshold();

        testTenuringThreshold();
    }




}
