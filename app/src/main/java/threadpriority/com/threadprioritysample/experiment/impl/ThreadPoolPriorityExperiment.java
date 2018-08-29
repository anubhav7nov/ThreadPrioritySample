package threadpriority.com.threadprioritysample.experiment.impl;

import android.os.Process;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import threadpriority.com.threadprioritysample.experiment.IExperiment;
import threadpriority.com.threadprioritysample.threadpool.ThreadUtils;

public class ThreadPoolPriorityExperiment implements IExperiment {

    private static final String TAG = "ThreadPoolPriorityExpt";

    @Override
    public void runExperiment() {
        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1),
                ThreadUtils.threadFactory("ThreadPoolPriorityExperiment", Process.THREAD_PRIORITY_BACKGROUND));

        for(int i = 0 ; i < 10; i ++) {
            executor.execute(getRunnable());
        }
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Thread currentThread = Thread.currentThread();
                Log.d(TAG, "thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));
            }
        };
    }
}
