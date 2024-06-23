package flow.pageActions;

import flow.pageObjects.MyContentFavoritesPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;

import static org.junit.Assert.assertTrue;

public class MyContentFavoritesPageAction extends MyContentFavoritesPageFlow {

    public MyContentFavoritesPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed(boolean contents) {
        // Check page locator
        assertTrue("Required Page not displayed",
                pageLocatorTitle.isDisplayed());

        // Check page title
        String title = "Mi Lista";
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
}
