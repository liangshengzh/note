package cn.devlab.generic;

import java.util.List;

/**
 * Created by zhong on 2016/12/20.
 */
public class Test{


   public void act(List<? extends Animal> list){
      for(Animal animal:list){

      }
   }
}


class Animal{

}

class Cat extends Animal{

}
