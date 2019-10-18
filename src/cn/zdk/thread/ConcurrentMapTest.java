package cn.zdk.thread;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

/**
 * 位什么ConcurrentSkipListMap 无法读取数据
 * 为社么 m1 为全局才有作用
 *
 * t2 在循环里 sheep 一段时间虽然 t1 更新完了 map ，但此时 t2 循环的变量size 还是等于 1 ，至于为什么  ConcurrentHashMap
 * 可以打印出来   skiplist 打印不出应该时 skiplist 消耗时间比较大 t1还没开始放 就被t2 锁住了，应该把sleep 放到for 外部
 * ConcurrentHashMap所使用的锁分段技术，首先将数据分成一段一段的存储，然后给每一段数据配一把锁，
 * 当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
 */
public class ConcurrentMapTest {
    //   static Map<String,String>  m1 = new TreeMap<String,String>((a,b)-> Integer.valueOf(a).compareTo( Integer.valueOf(b)));
      // volatile static Map<Integer,Integer>  m1 = Collections.synchronizedMap(new HashMap());

//   static   Map<Integer,Integer>  m1  = new ConcurrentSkipListMap<>((a,b)-> Integer.valueOf(a).compareTo( Integer.valueOf(b)));


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
       Map<Integer, Integer>  m1 = new ConcurrentHashMap<>(100);
       //   Map<Integer,Integer>  m1  = new ConcurrentSkipListMap<>((a,b)-> a.compareTo( b));

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 100; i++) {
                System.out.println("size1：" + m1.size());

                m1.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException a) {
                a.printStackTrace();
            }
            for (Map.Entry e : m1.entrySet()) {
                System.out.println("size2：" + m1.size());


                System.err.println(e.getValue());
            }

        });

        t1.start();
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start));
        System.out.println("size：" + m1.size());

    }
}
