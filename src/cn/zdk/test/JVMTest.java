package cn.zdk.test;

import java.util.ArrayList;
import java.util.List;

public class JVMTest {
    public static void main(String[] args) {
        List ss = new ArrayList<>();
        while (true) {
            List<String> s0 = new ArrayList<>(20);

            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s0.add("test");
            ss.add(s0);

        }
    }
}
