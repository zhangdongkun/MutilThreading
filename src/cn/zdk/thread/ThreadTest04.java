package cn.zdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * join 等待当前线程运行完
 */
public class ThreadTest04 {
    public static void main(String[] args) throws InterruptedException {
   Thread t1 =      new Thread(() -> {
            try {

                TimeUnit.SECONDS.sleep(3);
                System.out.println("1111");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        );
   t1.start();
   t1.join();


        System.out.println("2222");
    }
    }


