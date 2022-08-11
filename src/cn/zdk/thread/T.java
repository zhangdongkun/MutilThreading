package cn.zdk.thread;

public class T {
  private Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.waitTest();
    }


     void   waitTest() throws InterruptedException {
         synchronized(object) {
             object.wait(5000);
             System.out.println(10000);
         }
  }

}
