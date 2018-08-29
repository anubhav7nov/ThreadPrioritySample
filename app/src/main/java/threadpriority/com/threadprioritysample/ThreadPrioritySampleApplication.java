package threadpriority.com.threadprioritysample;

import android.app.Application;

public class ThreadPrioritySampleApplication extends Application {

    private static ThreadPrioritySampleApplication _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public static ThreadPrioritySampleApplication getInstance() {
        return _instance;
    }
}
