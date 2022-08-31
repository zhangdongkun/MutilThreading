package cn.zdk.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定
 * 和lock.hasQueueThread()不同的地方是：lock.hasQueueThread(Thread thread)的作用是判断当前线程是否处于等待lock的状态
 */
public class ServiceIsHeldByCurrentThread {
    private ReentrantLock lock;

    public ServiceIsHeldByCurrentThread(Boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod(){
        try {
            System.out.println("lock.isHeldByCurrentThread() "+lock.isHeldByCurrentThread());

            lock.lock();

            System.out.println("lock.isHeldByCurrentThread() "+lock.isHeldByCurrentThread());
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
       // ServiceIsHeldByCurrentThread serviceIsHeldByCurrentThread = new ServiceIsHeldByCurrentThread();
    }

}
