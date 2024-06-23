package flow.pageObjects;

import ch.qos.logback.classic.Logger;
import flow.pageActions.CommonPageAction;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

public class Page {

    private static final int TIMEOUT_FOR_DOCUMENT_READY = 40;

    protected Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(this.getClass()));
    protected ExtendedWebDriver webDriver;
    protected CommonPageAction commonPageAction;

    public Page(ExtendedWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        this.commonPageAction = new CommonPageAction(webDriver);
        waitUntilPageIsLoaded();
    }

    private void waitUntilPageIsLoaded() {
        webDriver.waitUntil(TIMEOUT_FOR_DOCUMENT_READY, wd -> webDriver.executeScript("return document.readyState").equals("complete"));
    }
}
