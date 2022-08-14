package cn.zdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyTest {
    public static void main(String[] args) {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if(method.getName().equals("sayHello")) {
                  return  "hi," + args[0];
                }
                return null;
            }
        };
        Hello hello = (Hello)Proxy.newProxyInstance(ProxyTest.class.getClassLoader(),new Class[]{Hello.class},invocationHandler);

        String ming_tian = hello.sayHello("ming tian");
        System.out.println(ming_tian);


    }
}
