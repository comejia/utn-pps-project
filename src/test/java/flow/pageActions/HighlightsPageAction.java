package flow.pageActions;

import flow.pageObjects.HighlightsFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;

import java.time.Duration;

public class HighlightsPageAction extends HighlightsFlow {

    public HighlightsPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void selectRevivedTheBestItemStripeActive(String stripeName) {
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                revivedTheBest.isDisplayed();
                this.webDriver.scrollPageToElement(revivedTheBest.findElement(this.getContentStripe(stripeName)));
                this.webDriver.clickOn(this.webDriver.findElement(this.getPlayIconActiveSlick(stripeName)));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

    public void selectLootAtInItemStripeActive() {
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                lookAtIt.isDisplayed();
                this.webDriver.scrollPageToElement(lookAtIt);
                this.webDriver.mouseOver(lookAtItPlayIcon);
                this.webDriver.clickOn(lookAtItPlayIcon.findElement(this.getPlayIcon()));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

    public void selectItemStripeActive(String itemStripeName) {
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                logger.info("stripe name: " + itemStripeName);
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                this.webDriver.findElement(this.getStripe(itemStripeName)).isDisplayed();
                this.webDriver.scrollPageToElement(this.webDriver.findElement(this.getStripe(itemStripeName)));
                this.webDriver.mouseOver(this.webDriver.findElement(this.getItemStripeActive(itemStripeName)));
                this.webDriver.clickOn(this.webDriver.findElement(this.getItemStripeActive(itemStripeName)).findElement(this.getPlayIcon()));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }
}
