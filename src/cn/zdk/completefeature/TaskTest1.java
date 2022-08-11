package cn.zdk.completefeature;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TaskTest1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //todo 为什么runAsync 一直返回为null
        CompletableFuture cf = CompletableFuture.supplyAsync(TaskTest1::getPrice);
        System.out.println("1当前线程：" + Thread.currentThread().isDaemon());
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            System.out.println("失败：" + e);
            return null;
        });

        // Thread.sleep(2000);
        CompletableFuture.runAsync(()->      {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("xxx");


        });

    }



    static Double getPrice() {
        double num = Math.random();
        System.out.println("2当前线程："+Thread.currentThread().getName()+Thread.currentThread().isDaemon());

        try {
            Thread.sleep(1000);
            if(num < 0.3) {
                throw  new RuntimeException("数值太小");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("num:"+num);
        return num;
    }
}
