package flow.pageActions;

import flow.pageObjects.SportsPageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SportsPageAction extends SportsPageFlow {

    public SportsPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void accessToSportsSection() {
        this.webDriver.elementWaitDisplayed(getDeportesMenu);
        this.webDriver.elementClick(getDeportesMenu);
    }

    public void checkSportsSection() {
        this.webDriver.elementWaitDisplayed(getStripeBoxDeportes);

        int qStripes = stripesBox.size();
        logger.info("Stripes: " + qStripes);
        for (WebElement stripeBox : stripesBox) {
            this.webDriver.scrollPageToElement(stripeBox);
            String stripeTitle = stripeBox.findElement(getStripeTitle)
                    .getText().toUpperCase();
            System.out.println("Stripe " + stripeTitle);
            List<WebElement> slicksActive = stripeBox.findElements(getSlickActive);
            for (WebElement slickActive : slicksActive) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
                this.webDriver.elementMoveToWithWait(slickActive, 1);
                String slickTitle = slickActive.findElement(getSlickText)
                        .getAttribute("innerHTML");
                System.out.println(slickTitle);
            }
        }
    }

    public void navStripeSportsEvents() {
        this.webDriver.elementWaitDisplayed(stripeSportsEvents);
        try {
            int contents = stripeSportsEvents.findElements(itemWrapper).size();
            assertTrue(contents >= 4);
            this.commonPageAction.navToStripe(Hooks.props.sportsEvents());
        } catch (Throwable throwable) {
            this.commonPageAction.navPosterStripe(stripeSportsEvents, itemWrapper);
        }
    }

    public void accessToStripe(WebElement stripe) {
        Awaitility.await().atMost(Duration.ofSeconds(80)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2));
                stripe.isDisplayed();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                this.webDriver.scrollPageToElement(stripe);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

}
