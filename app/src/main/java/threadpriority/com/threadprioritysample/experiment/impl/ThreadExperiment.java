package threadpriority.com.threadprioritysample.experiment.impl;

import android.os.Process;
import android.util.Log;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class ThreadExperiment implements IExperiment {

    private static final String TAG = "ThreadExperiment";

    @Override
    public void runExperiment() {
        Thread thread = new Thread(getRunnable(), "ThreadExperiment");
        thread.start();
    }

    private Runnable getRunnable() {

        return new Runnable() {
            @Override
            public void run() {

                Thread currentThread = Thread.currentThread();

                Log.d(TAG, "before set priority name  : main priority : " + Process.getThreadPriority(1));
                Log.d(TAG, "before set priority  name : " +  currentThread.getName() + " priority : " + Process.getThreadPriority(Process.myTid()));

                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

                Log.d(TAG, "after set priority name  : main priority : " + Process.getThreadPriority(1));
                Log.d(TAG, "after set priority  name : " +  currentThread.getName() + " priority : " + Process.getThreadPriority(Process.myTid()));

            }
        };
    }
}
