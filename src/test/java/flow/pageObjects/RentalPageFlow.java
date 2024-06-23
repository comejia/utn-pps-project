package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RentalPageFlow extends Page {

    @FindBy(xpath = ".//a[@class='stripeTitle']//h3[text()= 'Populares en Flow']")
    protected WebElement flowPopularStripe;
    @FindBy(css = "[data-testid=contentStripeItem] div div[id=playIcon] div")
    protected List<WebElement> stripePopularFlowItems;
    public RentalPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
