package cn.devlab;

/**
 * Created by zhong on 2016/11/29.
 */
public class Main {

    public static void main(String[] args) {
        String s1 = "Programing";
        String s2 = new String("Programing");
        String s3 = "Program" + "ing";

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());

        for(;;){}
    }
}
