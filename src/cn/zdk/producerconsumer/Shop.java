package cn.zdk.producerconsumer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shop {
    private List<Long> list = new LinkedList<>();
    private static final int MAX_SIZE = 100;

    public void producter() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                //todo 思考 这里用while 和 if 有不一样吗?
                //因为外层有while 测试结构是一样的
                while (list.size() == MAX_SIZE) {
                    try {
                        System.out.println("库存满了------停止生产");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                list.add(Thread.currentThread().getId());
                System.out.println("生成者:" + Thread.currentThread().getId() + "生产了一个商品,商品库存:" + list.size());


                list.notifyAll();
            }
        }

    }

    public void consumer() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (list) {
                while (list.isEmpty()) {
                    try {
                        System.out.println("库存已满停止消费");
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                list.remove(0);
                System.err.println("消费者" + Thread.currentThread().getId() + "消费一个商品:,商品库存:" + list.size());
                list.notifyAll();
            }


        }


    }


    public static void main(String[] args) {
        Shop shop = new Shop();
        Thread thread1 = new Thread(() -> shop.producter());
        Thread thread2 = new Thread(() -> shop.producter());
        Thread thread3 = new Thread(() -> shop.producter());


        thread1.start();
        thread2.start();
        thread3.start();

          new Thread(() -> shop.consumer()).start();

       //  new Thread(() -> shop.consumer()).start();
         // new Thread(() -> shop.consumer()).start();


    }

}
