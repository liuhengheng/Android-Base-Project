package com.bgp.cnpc.fourreport.common.util;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liuheng
 * @date 2017/9/25
 * 类：线程池管理
 */

public class ThreadPoolProxy {
    ThreadPoolExecutor mThreadPoolExecutor;
    private static ThreadPoolProxy threadPoolProxy;

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;

    public static ThreadPoolProxy Instance() {
        if (threadPoolProxy == null) {
            synchronized (ThreadPoolProxy.class) {
                threadPoolProxy = new ThreadPoolProxy(5, 10, 1);
            }
        }
        return threadPoolProxy;
    }

    private ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }

    private ThreadPoolExecutor initExecutor() {
        if (mThreadPoolExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mThreadPoolExecutor == null) {

                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();

                    mThreadPoolExecutor = new ThreadPoolExecutor(
                            //核心线程数
                            corePoolSize,
                            //最大线程数
                            maximumPoolSize,
                            //保持时间
                            keepAliveTime,
                            //保持时间对应的单位
                            unit,
                            workQueue,
                            //线程工厂
                            threadFactory,
                            //异常捕获器
                            handler);
                }
            }
        }
        return mThreadPoolExecutor;
    }


    /**
     * 执行任务
     */
    public void executeTask(Runnable r) {
        initExecutor();
        mThreadPoolExecutor.execute(r);
    }


    /**
     * 提交任务
     */
    public Future<?> commitTask(Runnable r) {
        initExecutor();
        return mThreadPoolExecutor.submit(r);
    }

    /**
     * 删除任务
     */
    public void removeTask(Runnable r) {
        initExecutor();
        mThreadPoolExecutor.remove(r);
    }
}
