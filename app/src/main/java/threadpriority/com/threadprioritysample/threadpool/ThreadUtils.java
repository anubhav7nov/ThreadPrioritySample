package threadpriority.com.threadprioritysample.threadpool;

import android.os.Process;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadUtils {

    /**
     *
     * @param name prefix name of threads created via this factory
     *
     * <p>By default all threads created have Process.THREAD_PRIORITY_BACKGROUND</p>
     * @return
     */
    public static ThreadFactory threadFactory(final String name) {
        return threadFactory(name, Process.THREAD_PRIORITY_BACKGROUND);
    }

    /**
     *
     * @param name prefix name of threads created via this factory
     * @param priority A Linux priority level, from -20 for highest scheduling
     * priority to 19 for lowest scheduling priority.
     * @return
     */
    public static ThreadFactory threadFactory(final String name, int priority) {
        return threadFactory(name, priority, false);
    }

    /**
     *
     * @param name prefix name of threads created via this factory
     *
     * @param daemon Marks the threads generated as either a daemon thread
     * or a user thread. The Java Virtual Machine exits when the only
     * threads running are all daemon threads.
     *
     * <p>By default all threads created have Process.THREAD_PRIORITY_BACKGROUND</p>
     * @return
     */
    public static ThreadFactory threadFactory(final String name, boolean daemon) {
        return threadFactory(name, Process.THREAD_PRIORITY_BACKGROUND, daemon);
    }

    /**
     *
     * @param name prefix name of threads created via this factory
     * @param priority A Linux priority level, from -20 for highest scheduling
     * priority to 19 for lowest scheduling priority.
     * @return
     */
    public static ThreadFactory threadFactory(final String name, int priority, boolean daemon) {
        return new PriorityThreadFactory(priority, daemon, name, true);
    }

    public static RejectedExecutionHandler rejectedExecutionHandler() {
        return new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        };
    }
}
