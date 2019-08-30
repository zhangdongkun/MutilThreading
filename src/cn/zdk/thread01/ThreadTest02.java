package cn.zdk.thread01;

/**
 * jdk 8 dd 的写法
 */
public class ThreadTest02 {
    private  int count =0;
    public synchronized void print1(){
        System.err.println( Thread.currentThread().getName() +"---- count:-->" +count );
    }

    public synchronized  void print2(){
     count ++;
       // System.out.println( Thread.currentThread().getName() +"---- count:-->" +count );
    }

    public static void main(String[] args) {
        ThreadTest02 t = new  ThreadTest02();
       // 类名::方法名，相当于对这个方法闭包的引用，类似js中的一个function
        //Runnable r = () -> t.print1();
        //Runnable r = t::print1
        for (int i = 0; i < 50;i++) {
            new Thread(t::print1,"t1").start();
            new Thread(t::print2,"t2").start();
        }

    }
}
