package flow.stepDefinitions;

import ch.qos.logback.classic.Logger;
import flow.configuration.IProperties;
import flow.core.PageFactory;
import flow.core.influx.InfluxMetrics;
import flow.core.jira.Jira;
import flow.utils.*;
import flow.utils.dataProvider.DataProvider;
import flow.webdriverUtils.ExtendedWebDriver;
import flow.webdriverUtils.InitWebDriver;
import io.cucumber.java.*;
import net.lightbody.bmp.BrowserMobProxy;
import org.junit.Rule;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static java.lang.annotation.ElementType.METHOD;

public class Hooks {

    // Set logger
    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(Hooks.class));

    public static IProperties props;

    public static BrowserMobProxy proxyServer;

    // Set webdriver
    protected static ThreadLocal<ExtendedWebDriver> threadLocalDriver = new ThreadLocal<>();

    public static ExtendedWebDriver getDriver() {
        return threadLocalDriver.get();
    }

    public static ThreadLocal<String> getFailedStep() {
        return failedStep;
    }

    // Set configuration variables
    protected static ThreadLocal<String> failedStep = new ThreadLocal<>();
    public static ThreadLocal<String> stepName = new ThreadLocal<>();
    public static boolean alreadyFailed = false;

    // Set API data variables
    public static Map<String, String> contentData = new HashMap<>();
    public static List<String> listNumberChannelsPlayBackFail;
    public static String stringSpecialChars = "™†‡◊‰←↑→↓♠♣♥♦";
    public static Map<String, String> localStorage = new HashMap<>();

    public static DataProvider dataProvider;

    // Set global tests timeout
    @Rule
    public Timeout globalTimeout = Timeout.seconds(1200); // max time per test
    // Apply different timeouts for tests methods
    @Rule
    public TestRule watcherTimeout = new TestWatcher() {
        public FailOnTimeout apply(Statement base, Description description) {
            if (description.getAnnotation(LongTest.class) != null) {
                return new FailOnTimeout(base, 14400000);
            } else {
                return new FailOnTimeout(base, 300000);
            }
        }
    };

    public static BrowserMobProxy getProxyServer() {
        return proxyServer;
    }

    @BeforeAll
    public static void setupClass() {
        props = UtilsProperties.getRunProperties(IProperties.class);
        dataProvider = new DataProvider(props.dataProviderPort());

        if (props.cloudBuildName() != null) {
            props.setProperty("buildName", props.cloudBuildName());
        } else {
            try {
                props.setProperty("buildName", props.suite() + "_" + props.product() + "_"
                        + InetAddress.getLocalHost().getHostName() + "_" + UtilsRandom.getCurrentDateAndTime());
            } catch (UnknownHostException e) {
                logger.warn("Error settings buildName property");
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setUp(Scenario scenario) throws Exception {

        Hooks.props.setProperty("testName", scenario.getName());

        // Creating BrowserMobProxy proxy server
        if (props.browserProxy()) {
            proxyServer = UtilsProxy.getProxyServer();
        } else {
            proxyServer = null;
        }

        // Mocking "/geo/v1/country" request response for all countries but AR
        UtilsProxy.setProxyCountry();

        //SoftAssertions softly = new SoftAssertions();

        // Set WebDriver
        InitWebDriver initWebDriver = new InitWebDriver(proxyServer, props);
        threadLocalDriver.set(initWebDriver.initDriverWebDesktop(scenario));

        // Instance classes
        InfluxMetrics.setProps(props);
        PageFactory.setWebDriver(getDriver());
        PageFactory.setCountry(props.country().toUpperCase());
        PageFactory.setProps(props);

        // Start video recording
        //UtilsMedia.startScreenRecording();

        // Reset alreadyFailed value
        Hooks.alreadyFailed = false;
    }

    @After
    public void tearDown(Scenario scenario) throws Exception {

        //FAILED
        var jira = new Jira(scenario, props);
        if (jira.canSendTask()) {
            jira.createTask();
        }

        // Close browser after each test
        getDriver().quit();
        threadLocalDriver.remove();
        logger.info("Stopped WebDriver driver");

        // Close proxy server
        UtilsProxy.stopProxyServer(proxyServer);

        // Remove temporary files
        UtilsFile.removeFile("target/propertiesPath.txt");
    }

    @AfterAll
    public static void teardownClass() throws Exception {
        // Set environment in Allure report
        addEnvironmentToAllureReport();
    }

    private static void addEnvironmentToAllureReport() {
        Properties properties = new Properties();

        properties.setProperty("environment", props.environment());
        properties.setProperty("suite", props.suite());
        properties.setProperty("deviceType", props.deviceType());
        properties.setProperty("product", props.product());
        properties.setProperty("country", props.country());
        properties.setProperty("url", props.baseUrl());
        properties.setProperty("browser", props.browser());
        properties.setProperty("browserSizeX", props.browserSizeX());
        properties.setProperty("browserSizeY", props.browserSizeY());

        try {
            String allurePath = System.getProperty("user.dir") + "/target/allure-results";
            File allureDir = new File(allurePath);
            if (!allureDir.isDirectory()) {
                allureDir.mkdirs();
            }
            File environmentProperties = new File(allurePath, "environment.properties");
            properties.store(new FileWriter(environmentProperties), null);
        } catch (IOException e) {
            logger.error("Could not store environment properties");
            e.printStackTrace();
        }
    }

    @Target(METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface LongTest {
        // Just for tagging a method and adapt the timeout
    }
}
