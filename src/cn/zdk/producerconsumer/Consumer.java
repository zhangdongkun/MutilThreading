package cn.zdk.producerconsumer;

public class Consumer implements Runnable {
    Storage storage;

    public Consumer(Storage storage) {
        this.storage = storage;

    }

    @Override
    public void run() {
        while (true) {
            storage.consumer();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
