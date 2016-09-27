/**
 * Created by zhong on 16/9/27.
 *
 * -Xss160k
 */
public class JVMStackSOF {


    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JVMStackSOF sof = new JVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + sof.stackLength);
            throw e;
        }
    }
}
