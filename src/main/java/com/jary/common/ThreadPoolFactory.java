package com.jary.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by GF on 2017/6/18.
 * 线程池单例工厂
 */
public class ThreadPoolFactory {

    private static ThreadPoolExecutor threadPoolExecutor = null;
    private static int queueDeep = 10;

    // 获取线程池
    public static ThreadPoolExecutor getExecutor() {
        if (threadPoolExecutor == null) {
            threadPoolExecutor = new ThreadPoolExecutor(2, 10, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueDeep),
                    new ThreadPoolExecutor.DiscardOldestPolicy());
        }
        return threadPoolExecutor;
    }

    public static int getQueueDeep() {
        return queueDeep;
    }
}
