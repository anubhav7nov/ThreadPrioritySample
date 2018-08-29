package threadpriority.com.threadprioritysample.experiment.impl;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class HandlerThreadPriorityExperiment implements IExperiment {

    private static final String TAG = "HandlerThreadPriorExpt";

    @Override
    public void runExperiment() {

        HandlerThread handlerThreadDefaultPriority = new HandlerThread("handlerThreadDefaultPriority");
        handlerThreadDefaultPriority.start();
        Handler defaultPriorityHandler = new Handler(handlerThreadDefaultPriority.getLooper());
        defaultPriorityHandler.post(getRunnable());

        HandlerThread handlerThreadDefaultCustomPriority = new HandlerThread("handlerThreadDefaultCustomPriority", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThreadDefaultCustomPriority.start();
        Handler customPriorityHandler = new Handler(handlerThreadDefaultCustomPriority.getLooper());
        customPriorityHandler.post(getRunnable());


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
