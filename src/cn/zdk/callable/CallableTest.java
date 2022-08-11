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

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();



        Future<Integer> future =  executor.submit(task);
        System.out.println("子线程结束:"+future.get(1000,TimeUnit.MILLISECONDS).toString());
        System.out.println("主线程结束");

    }
}


class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子在进行计算");
        Thread.sleep(30000);
        return 1;
      }

}

