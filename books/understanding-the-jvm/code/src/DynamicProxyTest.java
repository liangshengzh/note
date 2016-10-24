import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zhong on 2016/10/20.
 */
public class DynamicProxyTest {

    interface  IHello{
        void sayHello();
    }


    static class Helllo implements IHello{
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }


    static class DynamicProxy implements InvocationHandler{
        Object target;

        Object bind (Object target){
            this.target = target;

            return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcom");
            return method.invoke(target, args);
        }
    }

    public static void main(String[] args) {
        IHello hello =(IHello) new DynamicProxy().bind(new Helllo());
        hello.sayHello();
    }
}
