/**
 * Created by zhong on 16/9/22.
 */
public class StackOverflowErrorTest {

    public static void main(String[] args) {
        int i = getInt(1);
    }

    public static int getInt(int i){
        return getInt(i++);
    }
}
