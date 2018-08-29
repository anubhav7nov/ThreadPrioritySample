package threadpriority.com.threadprioritysample.experiment.impl.old;

import android.os.Debug;
import android.os.Process;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class ThreadFactoryRunnablePriorityBackground implements IExperiment{

    private static final String TAG = "ThreadFacPriorBack";

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void runExperiment() {

        ExecutorService executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1), getThreadFactory());


        //Log.d(TAG, "main thread priority : " + Process.getThreadPriority(Process.myTid()));

        Debug.startMethodTracing(TAG);

        /*Handler handler = new Handler();

        getRunnable().run();
        executor.execute(getRunnable());
        getRunnable().run();
        executor.execute(getRunnable());
        getRunnable().run();
        executor.execute(getRunnable());
        getRunnable().run();
        executor.execute(getRunnable());
        getRunnable().run();
        executor.execute(getRunnable());*/


        /*executor.execute(getRunnable());
        executor.execute(getRunnable());
        executor.execute(getRunnable());
        executor.execute(getRunnable());
        handler.post(getRunnable());*/

        Log.d(TAG, "cores : " + Runtime.getRuntime().availableProcessors());

        TestThreadOne testThreadOne = new TestThreadOne("one");
        TestThreadTwo testThreadTwo = new TestThreadTwo("two");
        testThreadOne.setPriority(Thread.MAX_PRIORITY);
        testThreadTwo.setPriority(Thread.MIN_PRIORITY);
        testThreadTwo.start();
        testThreadOne.start();
        Log.d(TAG, "main");
    }

    private class TestThreadOne extends Thread {

        public TestThreadOne(String name) {
            super(name);
        }

        @Override
        public void run() {

            //int mThreadPriority = Process.THREAD_PRIORITY_URGENT_AUDIO;
            //Process.setThreadPriority(mThreadPriority);
            Log.d("anubhav", "thread name : " + Thread.currentThread().getName());
            Log.d("anubhav", "thread priority : " + Process.getThreadPriority(Process.myTid()));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "one thread");

        }
    }

    private class TestThreadTwo extends Thread {

        public TestThreadTwo(String name) {
            super(name);
        }

        @Override
        public void run() {

            //int mThreadPriority = Process.THREAD_PRIORITY_DEFAULT;
            //Process.setThreadPriority(mThreadPriority);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "two thread");

        }
    }

    private static ThreadFactory getThreadFactory() {
        return new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(0);

            int mThreadPriority = Process.THREAD_PRIORITY_URGENT_AUDIO;

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
                Log.d(TAG, "name : " + Thread.currentThread().getName() + " count : " + newCount);

                if(newCount == 10) {
                    Debug.stopMethodTracing();
                }
            }
        };
    }
}
