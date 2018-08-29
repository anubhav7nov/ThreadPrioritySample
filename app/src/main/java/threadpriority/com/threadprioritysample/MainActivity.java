package threadpriority.com.threadprioritysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import threadpriority.com.threadprioritysample.experiment.IExperiment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IExperiment experiment = new ExperimentFactory().getExperiment(ExperimentFactory.ExperimentType.THREAD_EXPERIMENT);
        experiment.runExperiment();
    }

}
