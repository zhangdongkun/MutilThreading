package cn.zdk.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    @Test
    public void test() {
        AtomicInteger atomicInteger = new AtomicInteger(8);
        Assert.assertEquals(9, atomicInteger.addAndGet(1));

        Assert.assertEquals(true, atomicInteger.compareAndSet(9, 10));
        Assert.assertEquals(true, atomicInteger.compareAndSet(10, 11));


    }

}
