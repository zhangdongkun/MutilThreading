package cn.zdk.thread;

/**
 * @description 手动创建一个线程练练手
 * @author: zhangDongkun
 * @date: 2019-08-29 22:53
 **/

public class ThreadTest1 {


    public static void main(String[] args) {

        Thread t1 = new Thread(new Thread01());
        Thread t2 = new Thread(new Thread02());
        t1.start();
        t2.start();

    }
}

class Thread01 implements Runnable {

    @Override
    public void run() {
        System.err.println("当前线程：｛｝" + Thread.currentThread().getId());
    }
}

class Thread02 implements Runnable {
    @Override
    public void run() {
        System.err.println("当前线程：｛｝" + Thread.currentThread().getId());

    }
}