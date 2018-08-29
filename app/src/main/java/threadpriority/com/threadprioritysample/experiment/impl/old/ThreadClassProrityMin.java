package threadpriority.com.threadprioritysample.experiment.impl.old;

import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class ThreadClassProrityMin implements IExperiment{

    private static final String TAG = "ThreadPriorityMin";

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void runExperiment() {

        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), getThreadFactory());


        //Log.d(TAG, "main thread priority : " + Process.getThreadPriority(Process.myTid()));

        /*Debug.startMethodTracing(TAG);

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
        executor.execute(getRunnable());*/

        //Log.d(TAG, "main thread priority : " + Process.getThreadPriority(Process.myTid()));


        for(int i = 0 ; i < 100; i ++) {
            executor.execute(getRunnable());
        }

        for(int i = 0 ; i < 100; i ++) {
            getRunnable().run();
        }

    }

    private static ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);

            int mThreadPriority = Thread.MAX_PRIORITY;

            @Override
            public Thread newThread(@NonNull Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName( "sample-" + mCount.incrementAndGet());
                thread.setPriority(mThreadPriority);

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

                /*try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                int newCount = count.incrementAndGet();
                Log.d(TAG, "name : " + Thread.currentThread().getName() + " count : " + newCount);

                if(newCount == 10) {
                    Debug.stopMethodTracing();
                }
            }
        };
    }
}
