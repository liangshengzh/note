package cn.devlab.generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhong on 2016/12/21.
 */
public class ListGenericFoo{

    static class Food{}
    static class Fruit extends Food{}
    static class Apple extends Fruit{}
    static class RedApple extends Apple{}



    public static void main(String[] args) {
        List<String> testList = new ArrayList<String>();
    }
}
