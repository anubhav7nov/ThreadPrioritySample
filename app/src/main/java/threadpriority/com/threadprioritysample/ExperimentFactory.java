package threadpriority.com.threadprioritysample;

import threadpriority.com.threadprioritysample.experiment.IExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.HandlerThreadPriorityExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.IntentServiceExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.ThreadExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.ThreadPoolPriorityExperiment;
import threadpriority.com.threadprioritysample.experiment.impl.old.CallingThreadPriority;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityBackground;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityBackgroundMoreFavorable;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityMax;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadClassProrityMin;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadFactoryRunnablePriorityBackground;
import threadpriority.com.threadprioritysample.experiment.impl.old.ThreadFactoryRunnablePriorityBackgroundMoreFavourable;

public class ExperimentFactory {

    enum ExperimentType {
        THREAD_POOL_PRIORITY,
        HANDLER_THREAD_PRIORITY,
        INTENT_SERVICE_EXPERIMENT,
        THREAD_EXPERIMENT
    }

    public IExperiment getExperiment(ExperimentType experimentType) {
        switch (experimentType) {
            case THREAD_POOL_PRIORITY:
                return new ThreadPoolPriorityExperiment();
            case HANDLER_THREAD_PRIORITY:
                return new HandlerThreadPriorityExperiment();
            case INTENT_SERVICE_EXPERIMENT:
                return new IntentServiceExperiment();
            case THREAD_EXPERIMENT:
                return new ThreadExperiment();
            default:
                throw new IllegalStateException("Bad Type");

        }
    }
}
