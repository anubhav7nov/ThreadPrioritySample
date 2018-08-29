package threadpriority.com.threadprioritysample.experiment.impl;

import android.os.Process;
import android.util.Log;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class RxPriorityExperiment implements IExperiment {

    private static final String TAG = "RxPriorityExperiment";

    @Override
    public void runExperiment() {
        System.setProperty("rx2.computation-priority", "" + (Thread.NORM_PRIORITY - 1));
        System.setProperty("rx2.io-priority", "" + (Thread.MIN_PRIORITY));
        System.setProperty("rx2.single-priority", "" + (Thread.NORM_PRIORITY - 1));

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                Thread currentThread = Thread.currentThread();
                Log.d(TAG, "thread name : main priority : " + Process.getThreadPriority(1));

                Log.d(TAG, "thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));
            }
        }).subscribeOn(Schedulers.computation()).subscribe();

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                Thread currentThread = Thread.currentThread();
                Log.d(TAG, "thread name : main priority : " + Process.getThreadPriority(1));

                Log.d(TAG, "thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));
            }
        }).subscribeOn(Schedulers.io()).subscribe();

        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter emitter) {
                Thread currentThread = Thread.currentThread();
                Log.d(TAG, "thread name : main priority : " + Process.getThreadPriority(1));

                Log.d(TAG, "thread name : " + currentThread.getName() + " thread priority : " + Process.getThreadPriority(Process.myTid()));
            }
        }).subscribeOn(Schedulers.single()).subscribe();
    }
}
