/**
 * Created by zhong on 2016/10/18.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;


}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass ");
    }
}


class NotInitialization{
    public static void main(String[] args) {
        //只触发父类的初始化，子类没有初始化
        //System.out.println(SubClass.value);

        //SuperClass没有被触发初始化，只是触发了[L....SuperClass类的初始化阶段。
        //SuperClass[] sca = new SuperClass[10];
    }
}




