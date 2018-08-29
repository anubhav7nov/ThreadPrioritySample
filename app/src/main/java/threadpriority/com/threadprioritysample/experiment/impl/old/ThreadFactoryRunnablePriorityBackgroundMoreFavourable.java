package threadpriority.com.threadprioritysample.experiment.impl.old;

import android.os.Debug;
import android.os.Handler;
import android.os.Process;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class ThreadFactoryRunnablePriorityBackgroundMoreFavourable implements IExperiment{

    private static final String TAG = "TdFacPriorBackMrFv";

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void runExperiment() {

        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), getThreadFactory());


        //Log.d(TAG, "main thread priority : " + Process.getThreadPriority(Process.myTid()));

        Debug.startMethodTracing(TAG);

        Handler handler = new Handler();

        handler.post(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());
        executor.execute(getRunnable());


        /*executor.execute(getRunnable());
        executor.execute(getRunnable());
        executor.execute(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());*/

        //Log.d(TAG, "main thread priority : " + Process.getThreadPriority(Process.myTid()));

    }

    private static ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            int mThreadPriority = Process.THREAD_PRIORITY_BACKGROUND + Process.THREAD_PRIORITY_MORE_FAVORABLE;

            @Override
            public Thread newThread(final Runnable runnable) {
                Runnable wrapperRunnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Process.setThreadPriority(mThreadPriority);
                        } catch (Throwable t) {
                            // just to be safe
                        }
                        runnable.run();
                    }
                };

                Thread thread = new Thread(wrapperRunnable);
                thread.setName( "sample-" + mCount.incrementAndGet());

                //Log.d(TAG, "thread name in factory : " + thread.getName());
                //Log.d(TAG, "thread id in factory : " + thread.getId());

                return thread;
            }
        };
    }

    private Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                //Log.d(TAG, "thread name in runnable : " + Thread.currentThread().getName());
                //Log.d(TAG, "thread id in runnable : " + Process.myTid());
                //Log.d(TAG, "thread priority in runnable "  + Process.getThreadPriority(Process.myTid()));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int newCount = count.incrementAndGet();
                if(newCount == 10) {
                    Debug.stopMethodTracing();
                }
            }
        };
    }
}
