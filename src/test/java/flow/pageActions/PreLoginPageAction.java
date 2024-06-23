package flow.pageActions;

import flow.pageObjects.PreLoginPageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;

import static org.junit.Assert.assertTrue;

public class PreLoginPageAction extends PreLoginPageFlow {

    public PreLoginPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void accessLoginPage() {
        if (Hooks.props.country() != null && Hooks.props.country().equals("ar")) {
            webDriver.clickOn(buttonGetIn);
        }
    }

    public void pageIsDisplay() {
        assertTrue("Enter button not displayed", this.getButtonGetIn().isEnabled());
        assertTrue("Register button not displayed", this.getButtonRegister().isEnabled());
    }

    public void activatedAccount(){
        webDriver.clickOn(buttonRegister);
        webDriver.clickOn(buttonActivateFlow);
    }
}
