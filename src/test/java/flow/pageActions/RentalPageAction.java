package flow.pageActions;

import flow.pageObjects.RentalPageFlow;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;

public class RentalPageAction extends RentalPageFlow {

    public RentalPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void accessPopularFlowStripe() {
        this.webDriver.searchPageDownForElement(flowPopularStripe);
        this.webDriver.elementClick(flowPopularStripe);
    }

    public void clickItemStripePopularFlowMovie() {
        var random = UtilsRandom.getRandom(1, stripePopularFlowItems.size());
        stripePopularFlowItems.get(random).click();
    }
}
