/**
 * Created by zhong on 2016/10/18.
 */
public class ConsClass {

    static {
        System.out.println("ConsClass init");
    }
    public static final String HELLOWORLD = "hello world";
}

class Notinitialization{
    public static void main(String[] args) {
        System.out.println(ConsClass.HELLOWORLD);
    }
}
