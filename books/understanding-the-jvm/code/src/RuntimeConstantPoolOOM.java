import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zhong on 16/9/27.
 * -XX:PermSize=10M -XXMaxPermSize=10M
 *
 *只在1.8之前的JDK生效,这两个参数在1.8中被移除.
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;

        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
