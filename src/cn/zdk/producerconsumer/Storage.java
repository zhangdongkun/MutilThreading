package cn.zdk.producerconsumer;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 生产者消费者模型
 */
public class Storage {
    private final int MAX_SIZE = 100;
    private LinkedList<Long> list = new LinkedList<>();

    //当生产者向缓冲区放入一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态；
    public void producer() {
        synchronized (list) {
            while (list.size() == MAX_SIZE) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(Thread.currentThread().getId());
            System.out.println("生产者：" + Thread.currentThread().getId() + ",生产了一个数据，现有库存：" + list.size());

            list.notifyAll();


        }
    }

    /***
     * 消费
     */
    public void consumer() {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            list.remove();
            System.err.println("消费者：" + Thread.currentThread().getId() + ",消费了一个数据，现有库存：" + list.size());

            list.notifyAll();


        }


    }


    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread t1 = new Thread(new Producer(storage));
        Thread t2 = new Thread(new Producer(storage));
        Thread t3 = new Thread(new Producer(storage));

        Thread t4 = new Thread(new Consumer(storage));
        Thread t5 = new Thread(new Consumer(storage));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
