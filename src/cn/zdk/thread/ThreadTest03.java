package cn.zdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * 测试读写一致
 * @author ZhangDongkun
 * @date：2019/8/30 10:37
 */
public class ThreadTest03  {
    private int count = 0;


    public synchronized void write() {
        count++;
        try {

        } catch (Exception e) {

        }
       // System.err.println(Thread.currentThread().getId() + "---- count:-->" + count);

    }



    public synchronized void read() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println(Thread.currentThread().getId() + "---- count:-->" + count);

    }

    public static void main(String[] args) {
        ThreadTest03 t = new ThreadTest03 ();
        // 类名::方法名，相当于对这个方法闭包的引用，类似js中的一个function
        //Runnable r = () -> t.print1();
        //Runnable r = t::print1
        for (int i = 0; i < 50; i++) {
            new Thread(t::write, "write").start();
            new Thread(t::read, "read").start();
        }

    }
}
