/**
 * Created by zhong on 2016/10/20.
 */
public class HotSwapClassLoader extends  ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadByte(byte[] classByte){
        return defineClass(null, classByte, 0, classByte.length);
    }
}
