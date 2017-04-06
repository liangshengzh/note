package cn.devlab.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2016/12/26.
 */
public class ProducerConsumer {



    class Store{

        public static final int CAPACITY = 10;
        private  List<Product> products = new ArrayList<>();

        public void addProduct(Product product){
            synchronized (products){
                while (products.size() >= CAPACITY){
                    try {
                        products.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                products.add(product);
                products.notifyAll();
            }
        }

        public Product takeProduct(){
            Product product = null;
            synchronized (products){
                while (products.size() == 0){
                    try {
                        products.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }



               product = products.remove(products.size() -1);
                products.notifyAll();
            }
            return product;
        }


    }

    class Product{

        private String name;

        public Product(String name) {
            this.name = name;
        }
    }


}
