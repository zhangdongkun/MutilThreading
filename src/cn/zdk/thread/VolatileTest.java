package cn.zdk.thread;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

  /* volatile*/ boolean running = true; //对比一下有无volatile的情况下，整个程序运行结果的区别
    void m() {
        System.out.println("m start");
        while(running) {

            System.out.println("running::" +running);
         /*
         try {
            TimeUnit.MILLISECONDS.sleep(10);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }*/
            // 若上面几行代码取消注释，有可能在cpu空闲时去读取堆内存中的running变量的值从而结束while代码块
        }
        System.out.println("running::" +running);
        System.out.println("m end!");
    }



    public static void main(String[] args) {
        VolatileTest t = new VolatileTest();

        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running = false;
    }
}
