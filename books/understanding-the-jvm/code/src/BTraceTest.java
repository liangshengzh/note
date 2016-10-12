import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zhong on 16/10/12.
 */
public class BTraceTest {

    public int add(int a, int b){
        return a + b;
    }

    public static void main(String[] args) throws  Exception{
        BTraceTest test = new BTraceTest();
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
       for(int i = 0; i < 10; i++){
           br.readLine();
           int a = (int)Math.round(Math.random()*1000);
           int b = (int)Math.round(Math.random()*1000);
           System.out.println(test.add(a, b));
       }
    }
}
