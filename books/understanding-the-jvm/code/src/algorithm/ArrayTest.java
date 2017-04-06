package algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhong on 2016/12/9.
 */
public class ArrayTest {

    public static void main(String[] args) {

        int[] a = {1, 3, 4, 5, 6};
        int[] b = {1,3};

//        String[] a = {"1","2","3"};
//        String[] b = {"1","3"};

        List listA = Arrays.asList(a);
        List listB = Arrays.asList(b);

        System.out.println(listA.containsAll(listB));
    }
}
