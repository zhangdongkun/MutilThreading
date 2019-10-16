package cn.zdk.thread;

/***
 * synchronized 是加载对象上的 生成者和消费者要同时加锁才能保持数据一直
 */
public class ThreadTest05 {
    private String  id = "111";
    public static void main(String[] args) {
        ThreadTest05 threadTest05 = new ThreadTest05();
        Thread t1 = new Thread(()->{
            threadTest05.setId("2222");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            threadTest05.getId();
        });
        t2.start();

       System.out.println(threadTest05.getId());
    }

    public synchronized String getId() {
        return id;
    }

    public  synchronized void setId(String id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.id = id;
    }
}
