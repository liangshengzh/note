/**
 * Created by zhong on 16/9/27.
 */
public class ReferenceCountingGC {

    public Object instance;
    private static final int _1MB = 1024*1024;

    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
