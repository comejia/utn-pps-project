package flow.pageActions;

import flow.pageObjects.AdultPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AdultPageAction extends AdultPageFlow {

    public AdultPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void enterDipAdult() {
        this.webDriver.elementWaitDisplayed(modalBody);
        this.webDriver.elementWaitDisplayed(enterButton);
        this.webDriver.elementClick(enterButton);
    }

    public void selectRent() {
        this.webDriver.elementWaitDisplayed(rentButton);
        this.webDriver.elementClick(rentButton);
    }

    public void adultRentalOptionsDisplayed() {
        this.webDriver.elementWaitDisplayed(modalBody);
        assertTrue(modalBody.getText().contains("Confirmar alquiler"));
        assertTrue("Button cancel is not displayed", cancelButton.isDisplayed());
        assertTrue("Button confirm is not displayed", confirmButton.isDisplayed());
    }

    public void censoredPostersDisplayed() {
        this.webDriver.scrollToElement(stripeAdult);
        assertTrue("Stripe 'Adult' is not displayed", stripeAdult.isDisplayed());
        List<WebElement> contents = stripeAdult.findElements(getSlickActive);
        for (int cont = 0; cont < contents.size(); ) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.elementMoveTo(contents.get(cont));
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            cont++;
        }
    }

    public void noSearchResultsDisplayedAdult() {
        this.webDriver.elementWaitDisplayed(stripePeliculasSeriesYProgramas);
        String search = searchWrapper.getAttribute("value");
        List<WebElement> content = webDriver.findElements(itemWrapper);
        for (int cont = 0; cont < content.size(); ) {
            this.webDriver.mouseMoveElement(content.get(cont));
            String titleContent = content.get(cont).findElement(contentTitle).getText();
            if (!titleContent.contains(search)) {
                cont++;
            } else {
                titleContent.contains(search);
                Assert.fail("Se encontró una busqueda");
            }
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void contentDisplayedAvailableToRent() {
        this.webDriver.elementWaitDisplayed(rentPrice);
        assertTrue("Button rent is not displayed", rentButton.isDisplayed());
    }

    public void playContentRented() {
        this.webDriver.elementWaitDisplayed(flowPrimaryButton);
        this.webDriver.elementClick(flowPrimaryButton);
    }

    public void adultContentNotDisplayedInContinueWatchingSection() {
        this.enterDipAdult();
        this.webDriver.elementWaitDisplayed(titleContentRented);
        String nameContent = titleContentRented.getText();
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentContinueWatching();

        List<WebElement> content = webDriver.findElements(itemWrapper);
        for (int cont = 0; cont < content.size(); ) {
            this.webDriver.mouseMoveElement(content.get(cont));
            String titleContent = content.get(cont).findElement(contentTitle).getText();
            if (!titleContent.contains(nameContent)) {
                cont++;
            } else {
                titleContent.contains(nameContent);
                Assert.fail("Se encontró un contenido en la sección");
            }
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void playButtonIsDisplayed() {
        assertTrue("Button play is not displayed", flowPrimaryButton.isDisplayed());
    }

}