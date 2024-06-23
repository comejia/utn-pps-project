package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreLoginPageFlow extends Page {

    @FindBy(xpath = "//h1[@class='title']")
    protected WebElement logoFlow;
    @FindBy(xpath = ".//button[contains(@class,'button button--sign-in')]")
    protected WebElement buttonGetIn;
    @FindBy(xpath = "//button[normalize-space()='Registrarme']")
    protected WebElement buttonRegister;
    @FindBy(xpath = "//button[normalize-space()='ACTIVAR FLOW']")
    protected WebElement buttonActivateFlow;
    public PreLoginPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLogoFlow() {
        return logoFlow;
    }

    public WebElement getButtonGetIn() {
        return buttonGetIn;
    }

    public WebElement getButtonRegister() {
        return buttonRegister;
    }
}


