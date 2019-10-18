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
 * λʲôConcurrentSkipListMap �޷���ȡ����
 * Ϊ��ô m1 Ϊȫ�ֲ�������
 *
 * t2 ��ѭ���� sheep һ��ʱ����Ȼ t1 �������� map ������ʱ t2 ѭ���ı���size ���ǵ��� 1 ������Ϊʲô  ConcurrentHashMap
 * ���Դ�ӡ����   skiplist ��ӡ����Ӧ��ʱ skiplist ����ʱ��Ƚϴ� t1��û��ʼ�� �ͱ�t2 ��ס�ˣ�Ӧ�ð�sleep �ŵ�for �ⲿ
 * ConcurrentHashMap��ʹ�õ����ֶμ��������Ƚ����ݷֳ�һ��һ�εĴ洢��Ȼ���ÿһ��������һ������
 * ��һ���߳�ռ������������һ�������ݵ�ʱ�������ε�����Ҳ�ܱ������̷߳��ʡ�
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
                System.out.println("size1��" + m1.size());

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
                System.out.println("size2��" + m1.size());


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
        System.out.println("��ʱ��" + (end - start));
        System.out.println("size��" + m1.size());

    }
}
