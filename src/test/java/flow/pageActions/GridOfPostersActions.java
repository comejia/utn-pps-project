package flow.pageActions;

import flow.pageObjects.GridOfPostersPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class GridOfPostersActions extends GridOfPostersPageFlow {

    public GridOfPostersActions(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void gopIsDisplayed() {
        try {
            this.webDriver.elementWaitClickable(getStripe,5);
        } catch (Throwable throwable) {
            this.webDriver.elementWaitClickable(rowAssets,5);
        }
        List<WebElement> contents = this.webDriver.findElements(itemWrapper);
        for (int cont = 0;cont<contents.size();){
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.elementMoveTo(contents.get(cont));
            assertTrue(contents.get(cont).findElement(img).isDisplayed());
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            cont++;
        }
    }

    public void gopAllTitlesIsDisplayed() {
        this.webDriver.elementWaitDisplayed(titleSection);
        assertTrue("Element Title not displayed", titleStripeActive.isDisplayed());
        List<WebElement>contents = this.webDriver.findElements(itemWrapper);
        for (int cont = 0;cont<contents.size();){
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.elementMoveTo(contents.get(cont));
            assertTrue(contents.get(cont).findElement(img).isDisplayed());
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            cont++;
        }
    }

}
