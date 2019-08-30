package cn.zdk.thread;

/**
 * @author ZhangDongkun
 * @date：2019/8/30 10:27
 * @description： jdk 8  :: 测试方法同步器 synchronized
 */
public class ThreadTest02 {
    private int count = 0;


    /**
     * 方法同步器 作用在当前对象 相当于 synchronized(this){
     * <p>
     * }
     */
    public synchronized void print2() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {

        }
        System.err.println(Thread.currentThread().getId() + "---- count:-->" + count);
        count++;
    }

    public static void main(String[] args) {
        ThreadTest02 t = new ThreadTest02();
        // 类名::方法名，相当于对这个方法闭包的引用，类似js中的一个function
        //Runnable r = () -> t.print1();
        //Runnable r = t::print1
        for (int i = 0; i < 50; i++) {
            new Thread(t::print2, "t2").start();
        }

    }
}
