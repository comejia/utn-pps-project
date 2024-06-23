package flow.core.cucumber;

import flow.core.influx.InfluxMetrics;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestStepFinished;


public class SendMetricsPlugin implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {

        var influxMetrics = new InfluxMetrics();

        eventPublisher.registerHandlerFor(TestStepFinished.class, step -> {
            if (!step.getTestStep().getCodeLocation().contains("Hooks")) {
                influxMetrics.send(step);
            }
        });

        eventPublisher.registerHandlerFor(TestCaseFinished.class, influxMetrics::send);
    }
}