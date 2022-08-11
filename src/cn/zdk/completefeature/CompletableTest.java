package cn.zdk.completefeature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<CompletableFuture> completableFutureList = new ArrayList<>();
        //completableFuture.get(); 在训练里面把并行变串行了, i 是顺序舒服
        for (int i = 0; i < 10; i++) {
            int j = i;
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> sheep(j));
            completableFutureList.add(completableFuture);
           // completableFuture.get();
        }

        //测试表没这两个结果是一样的,可以看大坪这里的join 是回调而不是阻塞
        completableFutureList.stream().forEach(a -> {
             System.out.println("--------------------------"+Thread.currentThread().getId());
             a.join();
        });
        //主线程不能离开关闭否则子线程也会立刻关闭
        Thread.sleep(6000L);
        //  CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();
    }
    public static int sheep(int i) {
        try {
            if (i % 2 == 0) {
                Thread.sleep(3000);
            } else if (i % 3 == 0) {
                Thread.sleep(4000);
            } else {
                Thread.sleep(5000);

            }

            System.out.println("hello this is (threadid:"+Thread.currentThread().getId() +")-------------"+ i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;

    }


}
