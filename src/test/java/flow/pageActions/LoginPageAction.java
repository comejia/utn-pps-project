package flow.pageActions;

import flow.core.PageFactory;
import flow.pageObjects.LoginPageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageAction extends ActionPageBase {

    private final LoginPageFlow loginPageFlow;
    private final ExtendedWebDriver webDriver;

    public LoginPageAction(LoginPageFlow loginPageFlow) {
        this.webDriver = PageFactory.getWebDriver();
        this.loginPageFlow = loginPageFlow;
    }

    public void pageIsDisplayed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        assertTrue("Element image logo Flow not displayed", this.loginPageFlow.getImgLogoFlow().isEnabled());
        assertTrue("Element input username not displayed", this.loginPageFlow.getInputUsername().isDisplayed());
        assertTrue("Element input password not displayed", this.loginPageFlow.getInputPassword().isDisplayed());
        assertTrue("Element button submite disabled not displayed", this.loginPageFlow.getButtonSubmitDisabled().isDisplayed());
        assertTrue("Element input password hidden is not displayed", this.loginPageFlow.getInputPasswordHidden().isDisplayed());
        assertTrue("Element link recovery user is not displayed", this.loginPageFlow.getButtonUserRecovery().isDisplayed());
        assertTrue("Element link forgot password is not displayed", this.loginPageFlow.getButtonPasswordRecovery().isDisplayed());
        if (Hooks.props.country() != null && !Hooks.props.country().equals("py")) {
            assertTrue("Element link help center is not displayed", this.loginPageFlow.getHelp().isDisplayed());
        }
        logger.info("Elements are displayed");
    }

    public void pageIsDisplayedMora(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        //webDriver.waitUntil(10, wd -> loginPageFlow.getLogoMora().isDisplayed());
        assertTrue("Element Logo Flow not displayed", this.loginPageFlow.getLogoMora().isEnabled());
        assertTrue("Element message not displayed",this.loginPageFlow.getMessageErrorMora().isEnabled());
    }

    public void loginUser(String username, String password, boolean passwordToggleVisible) {
        // Fill login data
        fillUsernameAndPassword(username, password);
        // Check passwordToggle visible
        if (passwordToggleVisible) {
            checkPasswordToggleVisible(password);
        }
        // Submit data
        submitLogin();
    }

    public void loginUserMissingFields(String username, String password) {
        // Fill login data
        fillUsernameAndPassword(username, password);
    }

    public void checkSubmitButtonIsDisable() {
        // Check buttonSubmit button is disabled
        checkSubmitButtonIsDisabled();
    }

    public void fillUsernameAndPassword(String username, String password) {
        logger.info("Login data: " +
                "\nUser: " + username +
                "\nPassword: " + password);
        this.loginPageFlow.getInputUsername().clear();
        this.loginPageFlow.getInputUsername().sendKeys(username);
        this.loginPageFlow.getInputPassword().clear();
        this.loginPageFlow.getInputPassword().sendKeys(password);
    }

    public void checkPasswordToggleVisible(String password) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(this.loginPageFlow.getInputPasswordHidden()));
        //assertEquals(password, this.getInputPassword().getText());
        this.webDriver.elementClick(this.loginPageFlow.getButtonPasswordToggle());
        wait.until(ExpectedConditions.elementToBeClickable(this.loginPageFlow.getInputPasswordToggled()));
        //assertNotEquals(password, this.getInputPassword().getText());
    }

    public void enterUsername(String username) {
        this.loginPageFlow.getInputUsername().sendKeys(username);
    }

    public void enterPassword(String password) {
        this.loginPageFlow.getInputPassword().sendKeys(password);
    }

    public void showPassword() {
        this.webDriver.elementClick(this.loginPageFlow.getButtonPasswordToggle());
    }

    public void checkShowPassword() {
        assertTrue(this.loginPageFlow.getInputPasswordToggled().isDisplayed());
    }

    public void submitLogin() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(this.loginPageFlow.getButtonSubmit()));
        this.webDriver.elementClick(this.loginPageFlow.getButtonSubmit());
    }

    public void checkSubmitButtonIsDisabled() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        assertTrue(this.loginPageFlow.getButtonSubmitDisabled().isDisplayed());
        logger.info("Submit button is disabled");
    }

    public void checkErrorMessage(String errorMessage) {
        webDriver.waitUntil(10, wd -> this.loginPageFlow.getErrorBox().isDisplayed());
        assertEquals(errorMessage, this.loginPageFlow.getErrorBox().getText());
        logger.info("Error message is correct: " +
                this.loginPageFlow.getErrorBox().getText());
    }

    public void checkDeviceIdIsDisplayed() {
        assertTrue("Element deviceId is not displayed", this.loginPageFlow.getDeviceId().isDisplayed());
        logger.info("Element deviceId is displayed");
    }

    public void checkMessageByElement(By element, String message) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        assertTrue("Message incorrect", this.webDriver.findElement(element).getText().contains(message));
        logger.info("Message is correct: " + this.webDriver.findElement(element).getText());
    }

    public void errorMessageisDisplayed(String String ) {
        webDriver.waitUntil(10, wd -> this.loginPageFlow.getMessageErrorText().isDisplayed());
        assertTrue("Message error is not displayed", this.loginPageFlow.getMessageErrorText().isDisplayed());
        logger.info("Message error is displayed");
    }

    public void errorMessageIsDisplayed(String message) {
        webDriver.waitUntil(10, wd -> this.loginPageFlow.getMessageErrorText().isDisplayed());
        assertTrue("Message error is not displayed", this.loginPageFlow.getMessageErrorText().getText().contains(message));
        logger.info("Message error is displayed: " + this.loginPageFlow.getMessageErrorText().getText());
    }

    public void errorMessagePassword(){
        webDriver.waitUntil(10, wd -> this.loginPageFlow.getErrorPassword().isDisplayed());
        assertTrue("Message error is not displayed",this.loginPageFlow.getErrorPassword().isDisplayed());
        logger.info("Message error is displayed");
    }

    public void redirectUrlPasswordRecovery() {
        webDriver.elementClick(this.loginPageFlow.getButtonPasswordRecovery());
        webDriver.switchToNewTab(1);
    }

    public void redirectUserRecovery() {
        webDriver.elementClick(this.loginPageFlow.getButtonUserRecovery());
        webDriver.switchToNewTab(1);
    }

    public void redirectLinkHelpCenter() {
        webDriver.elementClick(this.loginPageFlow.getLinkHelpCenter());
        this.webDriver.switchToNewTab(1);
    }

    public void redirectRegister() {
        webDriver.elementClick(this.loginPageFlow.getButtonRegister());
        webDriver.elementClick(this.loginPageFlow.getButtonConfirmationOfRegister());
    }

    public void iHaveACodeRedirect() {
        webDriver.elementClick(this.loginPageFlow.getButtonIhaveCode());
    }

    public void buttonActiveFlow() {
        webDriver.elementClick(this.loginPageFlow.getButtonRegister());
        webDriver.elementClick(this.loginPageFlow.getButtonActiveFlow());
    }

    public void buttonLoginWithoutPassword() {
        webDriver.elementClick(this.loginPageFlow.getButtonLoginWithoutPassword());
    }

    public void countryErrorPageIsDisplayed() {
        assertTrue(loginPageFlow.getCountryErrorMessage().isDisplayed());
    }
}
