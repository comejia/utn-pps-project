package flow.pageActions;

import flow.pageObjects.MyContentContinueWatchingPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MyContentContinueWatchingPageAction extends MyContentContinueWatchingPageFlow {

    public MyContentContinueWatchingPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed(String title, boolean contents) {
        // Check page locator
        assertTrue("Required Page not displayed",
                pageLocator.isDisplayed());

        // Check page title
        assertTrue("Incorrect Page Title: " + title,
                pageLocatorTitle.getText().equalsIgnoreCase(title));
        logger.info("Navigating: " + title);

        // Check search button
        assertTrue("Search button not displayed",
                this.commonPageAction.buttonSearchWrapperIsDisplayed());

        // Check at least 1 content item exists
        if (contents) {
            int itemsAmount = this.webDriver.findElements(this.commonPageAction.getBySlickItem()).size();
            assertTrue("There are not displayed content items",
                    itemsAmount > 0);
            logger.info("Content items displayed: " + itemsAmount);
        }

        logger.info("Elements are displayed");
    }

    public void contentsAreDisplayed() {
        List<WebElement> myContetContinueWatching = grid.findElements(getItemWrapperBy);
        for (WebElement content : myContetContinueWatching){
            this.webDriver.elementWaitDisplayed(content);
            this.webDriver.elementMoveToWithWait(content,2);
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void selectExploreContents() {
        this.webDriver.elementWaitDisplayed(exploreContent);
        this.webDriver.elementClick(exploreContent);
    }

}
