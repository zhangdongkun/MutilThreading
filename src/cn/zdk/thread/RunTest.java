package cn.zdk.thread;

public class RunTest {
    public static void main(String[] args) {
        System.out.println(""+Thread.currentThread().getId());
        Runnable b= ()->System.out.println("hi ,i am," + Thread.currentThread().getId());
        b.run();
        Thread thread = new Thread(b);
        thread.start();
    }
}
