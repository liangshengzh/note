package cn.devlab.generic;

/**
 * Created by zhong on 2016/12/20.
 */
public class Pair<K, V> {

    private K key;
    private V value;


    public K getLeft(){
        return key;
    }


    private V getRight(){
        return value;
    }
}
