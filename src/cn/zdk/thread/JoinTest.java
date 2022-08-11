package cn.zdk.thread;

public class JoinTest {
    /**
     * 一个线程还可以等待另一个线程直到其运行结束。例如，main线程在启动t线程后，可以通过t.join()等待t线程结束后再继续运行：
     * Java线程对象Thread的状态包括：New、Runnable、Blocked、Waiting、Timed Waiting和Terminated；
     * @param args
     * @throws InterruptedException
     */
        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello1");
            });
            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hello2");
            });
            System.out.println("start");
            t.start();
            t2.start();
            t.join();
            t2.join();
            System.out.println("end");

        }
    }

