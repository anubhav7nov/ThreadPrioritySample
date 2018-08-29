package threadpriority.com.threadprioritysample;

import threadpriority.com.threadprioritysample.experiment.IExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.old.CallingThreadPriority;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityBackground;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityBackgroundMoreFavorable;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityMax;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityMin;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadFactoryRunnablePriorityBackground;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadFactoryRunnablePriorityBackgroundMoreFavourable;

public class ExperimentFactoryOld {

    enum ExperimentType {
        CALLING_THREAD_PRIORITY,
        THREAD_CLASS_PRIORITY_BACKGROUND,
        THREAD_CLASS_PRIORITY_BACKGROUND_MORE_FAVOURABLE,
        THREAD_FACTORY_RUNNABLE_PRIORITY_BACKGROUND,
        THREAD_FACTORY_RUNNABLE_PRIORITY_BACKGROUND_MORE_FAVOURABLE,
        THREAD_CLASS_PRIORITY_MAX,
        THREAD_CLASS_PRIORITY_MIN
    }

    public IExperiment getExperiment(ExperimentType experimentType) {
        switch (experimentType) {
            case CALLING_THREAD_PRIORITY:
                return new CallingThreadPriority();
            case THREAD_CLASS_PRIORITY_BACKGROUND:
                return new ThreadClassProrityBackground();
            case THREAD_CLASS_PRIORITY_BACKGROUND_MORE_FAVOURABLE:
                return new ThreadClassProrityBackgroundMoreFavorable();
            case THREAD_FACTORY_RUNNABLE_PRIORITY_BACKGROUND:
                return new ThreadFactoryRunnablePriorityBackground();
            case THREAD_FACTORY_RUNNABLE_PRIORITY_BACKGROUND_MORE_FAVOURABLE:
                return new ThreadFactoryRunnablePriorityBackgroundMoreFavourable();
            case THREAD_CLASS_PRIORITY_MAX:
                return new ThreadClassProrityMax();
            case THREAD_CLASS_PRIORITY_MIN:
                return new ThreadClassProrityMin();
            default:
                throw new IllegalStateException("Bad Type");

        }
    }
}
