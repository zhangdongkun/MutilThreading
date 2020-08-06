package cn.zdk.producerconsumer;

public  class Producer implements Runnable {
    Storage storage;

    public Producer(Storage storage) {
        this.storage = storage;

    }


    @Override
    public void run() {
        while (true) {
            storage.producer();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}