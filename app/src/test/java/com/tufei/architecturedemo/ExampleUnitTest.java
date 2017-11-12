package com.tufei.architecturedemo;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit Rtest, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String KEY_IO_PRIORITY = "rx2.io-priority";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        AtomicLong atomicLong = new AtomicLong();
        long l = atomicLong.incrementAndGet();
        System.out.println(l);
        long l2 = atomicLong.incrementAndGet();
        System.out.println(l2);
        //atomicLong当前值等于预期值，也就是这里的2时，才会更新值
        boolean b = atomicLong.compareAndSet(2, 5);
        System.out.println(""+b+atomicLong.get());
    }
}