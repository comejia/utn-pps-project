package flow.pageActions;

import flow.pageObjects.StripePageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StripePageAction extends StripePageFlow {



    public StripePageAction(ExtendedWebDriver webDriver) {

        super(webDriver);
    }

    public void pageIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        WebElement firstItem = getGridItemWrapper(1);
        wait.until(ExpectedConditions.visibilityOf(firstItem));
        Assert.assertTrue("First item not displayed", firstItem.isDisplayed());
        logger.info("First item title: " + itemImgList.get(1).getAttribute("alt"));
    }

    public WebElement getGridItemWrapper(int itemNumber) {
        webDriver.waitUntil(10, wd -> itemWrapper.size() > 0);
        logger.info("Content amount in stripe: " + itemWrapper.size());
        return itemWrapperList.get(itemNumber);
    }

    public void checkPostersWithoutPlayButton(WebElement elementStripe, By element) {
        List<WebElement> content = elementStripe.findElements(element);
        content.stream()
                .forEach(webElement -> {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
                    this.webDriver.mouseMoveElement(webElement);
                    this.webDriver.waitForElementNotVisible(buttonPlay, 1);
                });
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void accessStripeSeriesSection() {
        this.webDriver.elementWaitDisplayed(stripes.get(0));
        this.webDriver.scrollToELementPageDownAwaitility(seeMore);
        logger.info("Stripe: "+ selectedStripe.getText());
        WebElement seeMore = selectedStripe.findElement(seeMoreBy);
        this.webDriver.elementWaitDisplayed(seeMore);
        this.webDriver.elementClick(seeMore);
    }
}
