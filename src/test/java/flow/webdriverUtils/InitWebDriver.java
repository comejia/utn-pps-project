package flow.webdriverUtils;

import ch.qos.logback.classic.Logger;
import flow.configuration.IProperties;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsProxy;
import io.cucumber.java.Scenario;
import net.lightbody.bmp.BrowserMobProxy;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class InitWebDriver {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(InitWebDriver.class));
    protected ExtendedWebDriver webDriver;
    protected IProperties props;
    protected BrowserMobProxy browserMobProxy;
    String REMOTE_URL;

    public InitWebDriver(BrowserMobProxy browserMobProxy, IProperties properties) {
        this.browserMobProxy = browserMobProxy;
        this.props = properties;
        REMOTE_URL = "http://127.0.0.1:4444";
    }

    public ExtendedWebDriver initDriverWebDesktop(Scenario scenario) throws MalformedURLException {
        // Set WebDriver driver var
        WebDriver driver;

        switch (props.browser()) {
            case "firefox":
                driver = getDriverFirefox();
                break;
            case "edge":
                driver = getDriverEdge();
                break;
            case "safari":
                driver = getDriverSafari();
                break;
            case "chrome":
            default:
                driver = getDriverChrome();
                break;
        }

        this.webDriver = new ExtendedWebDriver(driver);

        // Display configuration values
        logger.info("Browser name: " + this.webDriver.getBrowserName());
        logger.info("Browser version: " + this.webDriver.getBrowserVersion());
        logger.info("Browser User-Agent: " + this.webDriver.getBrowserUserAgent());
        logger.info("Browser settings fullscreen: " + props.browserFullScreen());
        logger.info("Browser settings size X: " + props.browserSizeX());
        logger.info("Browser settings size Y: " + props.browserSizeY());

        // Set driver timeouts
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        this.webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(240));

        return this.webDriver;
    }

    private WebDriver getDriverFirefox() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        // Set Firefox Profile
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
        firefoxProfile.setPreference("javascript.enabled", true);
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting", false);
        firefoxProfile.setPreference("browser.download.panel.shown", false);
        firefoxProfile.setPreference("browser.download.manager.alertOnEXEOpen", false);
        firefoxProfile.setPreference("browser.download.manager.focusWhenStarting", false);
        firefoxProfile.setPreference("browser.download.manager.useWindow", true);
        firefoxProfile.setPreference("browser.download.manager.closeWhenDone", true);
        firefoxProfile.setPreference("media.eme.enabled", true);
        firefoxProfile.setPreference("media.drm.enabled", true);
        firefoxProfile.setPreference("media.gmp-manager.cert.requireBuiltIn", true);
        firefoxProfile.setPreference("media.gmp-manager.cert.checkAttributes", false);
        firefoxProfile.setPreference("media.gmp-manager.updateEnabled", true);
        firefoxProfile.setPreference("media.gmp-provider.enabled", true);
        firefoxProfile.setPreference("media.gmp-widevinecdm.enabled", true);
        firefoxProfile.setPreference("media.gmp-widevinecdm.visible", true);
        firefoxProfile.setPreference("devtools.console.stdout.content", true);
        firefoxOptions.setProfile(firefoxProfile);
        // Dont wait for pages to load
        //firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        // Check if headless mode
        if (props.browserHeadless()) {
            firefoxOptions.addArguments("--headless");
            firefoxOptions.addArguments(String.format("--window-size=%sx%s", Hooks.props.browserSizeX(), Hooks.props.browserSizeY()));
            logger.info("Browser running in headless mode");
        } else {
            firefoxProfile.setPreference("dom.webnotifications.enabled", false);
        }

        // Check if proxy mode
        if (props.browserProxy() ||
                props.country() != null && !props.country().equals("ar")) {
            // Creating Selenium proxy
            Proxy seleniumProxy = UtilsProxy.getSeleniumProxy(this.browserMobProxy);
            firefoxOptions.setCapability("proxy", seleniumProxy);
            // Create new HAR
            this.browserMobProxy.newHar();
            logger.info("Browser running with proxy enabled");
        }

        // Disable browser console log
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_PROFILE, "/dev/null");

        // Init driver
        WebDriver driver = new FirefoxDriver(firefoxOptions);

        // Set window size
        setBrowserFullScreen(driver);

        // Set window size if not fullscreen
        setBrowserResize(driver);

        return driver;
    }

    private WebDriver getDriverSafari() {
        WebDriver driver;
        driver = new SafariDriver();
        return driver;
    }

    private WebDriver getDriverEdge() {
        WebDriver driver;
        driver = new EdgeDriver();
        return driver;
    }

    private WebDriver getDriverChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // Enable Widevine component in MacOs
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("excludeSwitches", "disable-component-update");

        // Enable network and page logging
        prefs.put("traceCategories", "browser,devtools.timeline,devtools");
        prefs.put("enableNetwork", true);
        prefs.put("enablePage", true);
        chromeOptions.setExperimentalOption("prefs", prefs);

        // Don't wait for pages to load
        //chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        // Check if headless mode
        if (Hooks.props.browserHeadless()) {
            chromeOptions.addArguments("--headless");
            System.setProperty("java.awt.headless", "true");
            logger.info("Browser running in headless mode");
        }

        // Set Browser options
        chromeOptions.addArguments(String.format("--window-size=%sx%s", Hooks.props.browserSizeX(), Hooks.props.browserSizeY()));
        chromeOptions.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
        chromeOptions.addArguments("--no-sandbox"); // https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-dev-shm-usage"); // https://stackoverflow.com/a/50725918/1689770
        chromeOptions.addArguments("--disable-browser-side-navigation"); // https://stackoverflow.com/a/49123152/1689770
        chromeOptions.addArguments("--disable-gpu"); // https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
        //chromeOptions.addArguments("--remote-debugging-port=9222");

        // Set browser fullscreen
        if (props.browserFullScreen() && !props.browserHeadless()) {
            chromeOptions.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
            logger.info("Browser window resized to fullscreen");
        }

        // Enable Widevine
        chromeOptions.addArguments("--enable-widevine");

        // Ignore certificate errors
        chromeOptions.addArguments("--ignore-certificate-errors");

        // Chrome 111 bug
        chromeOptions.addArguments("--remote-allow-origins=*");

        // Check if proxy mode
        this.setProxyMode(chromeOptions);

        // Enable browser console log
        chromeOptions.addArguments("--log-level=1");
        //chromeOptions.addArguments("--enable-logging --v=1");

        // Handling SSL certs in Chrome
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromeOptions.setAcceptInsecureCerts(true);

        //chromeOptions.addArguments("--log-net-log");
        // DesiredCapabilities are deprecated
        //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        //capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        // Set window size
        if (props.browserFullScreen()) {
            chromeOptions.addArguments("--start-fullscreen");
            logger.info("Browser window resized to fullscreen");
        }

        // Enable performance logging
        if (props.browserNetworkLogs()) {
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        }

        // Init driver
        WebDriver driver = new ChromeDriver(chromeOptions);

        // Set window size if not fullscreen
        setBrowserResize(driver);

        return driver;
    }

    public void setBrowserFullScreen(WebDriver driver) {
        if (props.browserFullScreen() && !props.browserHeadless()) {
            driver.manage().window().fullscreen();
            logger.info("Browser window resized to fullscreen");
        }
    }

    public void setBrowserResize(WebDriver driver) {
        // Set window size if not fullscreen
        if (!props.browserHeadless() && !props.browserFullScreen()
                && props.browserSizeX() != null && props.browserSizeY() != null) {
            driver.manage().window().setSize(
                    new Dimension(Integer.parseInt(props.browserSizeX()), Integer.parseInt(props.browserSizeY())));
            logger.info("Browser window resized to " + props.browserSizeX() + "x" + props.browserSizeY());
        } else {
            logger.info("Browser window not resized");
        }
    }

    private void setProxyMode(ChromeOptions chromeOptions) {
        if (props.browserProxy()) {
            // Creating Selenium proxy
            Proxy seleniumProxy = UtilsProxy.getSeleniumProxy(this.browserMobProxy);
            chromeOptions.setCapability("proxy", seleniumProxy);
            // Create new HAR
            this.browserMobProxy.newHar();
            logger.info("Browser running with proxy enabled");
        }
    }
}
