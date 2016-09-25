import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 16/9/22.
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(new Object());
        }
    }
}
