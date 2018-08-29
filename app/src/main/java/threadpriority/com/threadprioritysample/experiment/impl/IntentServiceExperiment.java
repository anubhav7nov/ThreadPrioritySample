package threadpriority.com.threadprioritysample.experiment.impl;

import android.app.IntentService;
import android.content.Intent;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import threadpriority.com.threadprioritysample.ThreadPrioritySampleApplication;
import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class IntentServiceExperiment implements IExperiment {

    private static final String TAG = "IntentServiceExpt";

    @Override
    public void runExperiment() {
        Intent intent = new Intent(ThreadPrioritySampleApplication.getInstance(), TestIntentService.class);
        ThreadPrioritySampleApplication.getInstance().startService(intent);
    }


    public static class TestIntentService extends IntentService {

        /**
         * Creates an IntentService.  Invoked by your subclass's constructor.
         *
         * @param name Used to name the worker thread, important only for debugging.
         */
        public TestIntentService() {
            super("TestIntentService");
        }

        @Override
        protected void onHandleIntent(@Nullable Intent intent) {
            Thread currentThread = Thread.currentThread();
            Log.d(TAG, "before set priority thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));

            Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);

            Log.d(TAG, "after set priority thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));
        }
    }
}
