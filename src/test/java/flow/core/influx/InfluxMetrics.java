package flow.core.influx;

import ch.qos.logback.classic.Logger;
import flow.configuration.IProperties;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsCucumber;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestStepFinished;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.InfluxDBIOException;
import org.influxdb.dto.Point;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;


public class InfluxMetrics {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(InfluxMetrics.class));

    private static Boolean isOkBd = true;

    private static IProperties props;

    public static void setProps(IProperties props) {
        InfluxMetrics.props = props;
    }

    public void send(TestStepFinished test) {

        if (props.influxSendMetrics()) {
            var tags = test.getTestCase().getTags();
            var tagTuf = UtilsCucumber.getTuf(tags);
            var tagStream = UtilsCucumber.getTag("@stream", tags);
            var testName = test.getTestCase().getName();
            var stepName = Hooks.stepName.get();
            var result = test.getResult().getStatus().name();
            var feature = UtilsCucumber.getFeature(test.getTestCase().getUri().toString());
            var duration = test.getResult().getDuration().toMillis();
            try {
                var point = Point.measurement(props.influxMeasurement())
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("type", "step")
                        .tag("stream", tagStream)
                        .tag("feature", feature)
                        .tag("testName", testName)
                        .tag("country", props.country())
                        .tag("status", result)
                        .tag("environment", props.environment())
                        .tag("host", props.baseUrl())
                        .tag("browser", props.browser())
                        .tag("suiteName", props.suite())
                        .tag("step", stepName)
                        .tag("tuf", tagTuf)
                        .addField("duration", duration)
                        .build();
                sendData(point);
            } catch (Exception e) {
                logger.info("Error " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void send(TestCaseFinished test) {

        if (props.influxSendMetrics()) {
            var tags = test.getTestCase().getTags();
            var tagTuf = UtilsCucumber.getTuf(tags);
            var tagStream = UtilsCucumber.getTag("@stream", tags);
            var testName = test.getTestCase().getName();
            var result = test.getResult().getStatus().name();
            var feature = UtilsCucumber.getFeature(test.getTestCase().getUri().toString());
            var duration = test.getResult().getDuration().toMillis();
            try {
                var point = Point.measurement(props.influxMeasurement())
                        .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("type", "test")
                        .tag("stream", tagStream)
                        .tag("feature", feature)
                        .tag("testName", testName)
                        .tag("country", props.country())
                        .tag("status", result)
                        .tag("environment", props.environment())
                        .tag("host", props.baseUrl())
                        .tag("browser", props.browser())
                        .tag("suiteName", props.suite())
                        .tag("step", "N/A")
                        .tag("tuf", tagTuf)
                        .addField("duration", duration)
                        .build();
                sendData(point);
            } catch (Exception e) {
                logger.info("Error " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void sendData(Point point) {
        try {
            if (isOkBd) {
                var db = create();
                db.write(point);
                db.close();
                logger.info("Successfully send metrics --> " + point.toString());
            }
        } catch (InfluxDBIOException e) {
            isOkBd = false;
            logger.error("Error connect to influx - Message: " + e.getMessage());
        }
    }

    private InfluxDB create() {
        final InfluxDB influxDB = InfluxDBFactory.connect(props.influxHostname(), props.influxUsername(),
                props.influxPassword());
        influxDB.setDatabase(props.influxDatabase());
        influxDB.setRetentionPolicy("1year");
        return influxDB;
    }
}