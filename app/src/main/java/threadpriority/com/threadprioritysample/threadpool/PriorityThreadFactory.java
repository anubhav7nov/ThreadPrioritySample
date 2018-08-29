/*
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package threadpriority.com.threadprioritysample.threadpool;

import android.os.Process;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadFactory that applies a priority to the threads it creates.
 */
public class PriorityThreadFactory implements ThreadFactory {

  /* package */ final int mThreadPriority;
  private final String mPrefix;
  private final boolean mAddThreadNumber;

  private final boolean daemon;

  private final AtomicInteger mThreadNumber = new AtomicInteger(1);

  public PriorityThreadFactory(int threadPriority, boolean daemon, String prefix, boolean addThreadNumber) {
    mThreadPriority = threadPriority;
    this.daemon = daemon;
    this.mPrefix = prefix;
    this.mAddThreadNumber = addThreadNumber;
  }

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
    final String name;
    if (mAddThreadNumber) {
      name = mPrefix + "-" + mThreadNumber.getAndIncrement();
    } else {
      name = mPrefix;
    }
    Thread thread = new Thread(wrapperRunnable, name);
    thread.setDaemon(daemon);
    return thread;
  }

}
