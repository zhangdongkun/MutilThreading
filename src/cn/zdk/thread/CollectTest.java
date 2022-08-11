package cn.zdk.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectTest {
    /**
     * 】泛型通配符<? extends T>来接收返回的数据，此写法的泛型集合不能使用 add方 法，而<? super T>不能使用 get方法，作为接口调用赋值时易出错。 说明：扩展说一下 PECS(Producer Extends Consumer Super)原则：第一、频繁往外读取内容的，适合 用<? extends T>。第二、经常往里插入的，适合用<? super T>
     * 10
     * @param args
     */
    public static void main(String[] args) {
        List<? extends A> collection = new ArrayList();

        B b = new B();
        A a = new A();
        T t = new T();
       // collection.add(b);
    }
}
