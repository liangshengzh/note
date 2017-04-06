package cn.devlab.collection;

import java.util.*;

/**
 * Created by zhong on 2016/12/20.
 */
public class Main {

    public static void main(String[] args) {

     SortedMap<String, Integer> map = new TreeMap<>();
        map.put("z",1);
        map.put("l",2);
        map.put("s",3);


        for(Iterator it = map.keySet().iterator();it.hasNext();){
            System.out.println(it.next());
        }
    }
}
