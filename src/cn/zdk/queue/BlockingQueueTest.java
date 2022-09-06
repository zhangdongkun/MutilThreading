package cn.zdk.queue;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/***
 *ReentrantLock内部通过AbstractQueuedSynchronizer（AQS）来管理获取锁的线程，而AQS的状态state定义为：private volatile int state。volatile保证了AQS的可见性，也是保证ReentrantLock可见性的关键。而volatile保证可见性的原理为：volatile修饰的变量，每次修改值后，必须立刻同步回主内存；每次使用前，必须先从主内存刷新最新的值。
 *
 */
public class BlockingQueueTest {

    public static void main(String[] args) {


        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(1);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    Random random = new Random(100);
                    int i = random.nextInt();
                    blockingQueue.put(i);
                    System.err.println("正在装载-----" + i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    Object take = blockingQueue.take();
                    System.out.println("正在移除第:----" + take);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }


        }).start();


    }
}
