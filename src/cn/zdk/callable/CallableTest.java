package cn.zdk.callable;

import java.util.concurrent.*;

/***
 * @FunctionalInterface
 * public interface Callable<V> {
 *     /**
 *      * Computes a result, or throws an exception if unable to do so.
 *      *
 *      * @return computed result
 *      * @throws Exception if unable to compute a result
 *
 *
 *
 *
 *      callable 可以返回结果 可以抛出异常
 *
 *      */




public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        System.out.println("主线程结束");


        Future<Integer> future =  executor.submit(()->{ Thread.sleep(3000); return 2;});
        Future future2 =  executor.submit(()->System.out.println("111111111111111111"));
        System.out.println("子线程结束:"+future2.get().toString());

    }
}


class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++) {
            sum += i;
        }
        return sum;
    }
}

