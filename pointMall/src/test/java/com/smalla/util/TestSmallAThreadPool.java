package com.smalla.util;


import com.smalla.util.threadPool.SmallAThreadPool;

public class TestSmallAThreadPool {

    public static void main(String[] args) {
        for (int i = 0;i < 1000; i++) {
            SmallAThreadPool.submit(new TestMyRunnable());
        }
    }
}

class TestMyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "---" + i);
        }
    }
}
