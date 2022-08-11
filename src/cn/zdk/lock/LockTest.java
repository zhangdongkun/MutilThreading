package cn.zdk.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest  {
   static ReentrantLock lock  = new ReentrantLock();

    public static  void test() {

        try {
            Random random = new Random(500);//指定种子数字

            Thread.sleep(random.nextLong()%3);
            lock.lock();
            System.out.println("线程： " +Thread.currentThread().getId() +"，获得了锁");
        }catch (Exception e) {
            e.printStackTrace();
        } finally {


           if(lock.isHeldByCurrentThread()) {
               System.out.println("线程： " +Thread.currentThread().getId() +"，释放锁");


             //  lock.unlock();
           }
        }



    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for(int i = 0;i<100;i++) {
            executor.execute(()->{
                test();
            });
        }


    }



}
