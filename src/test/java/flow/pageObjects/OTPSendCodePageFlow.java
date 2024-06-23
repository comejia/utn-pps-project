package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OTPSendCodePageFlow extends Page {


    public OTPSendCodePageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }


    @FindBy(xpath = "//img[@alt='personal flow']")
    protected WebElement imgLogo;

    @FindBy(xpath = "//input[contains(@class,'otp--input')]")
    protected List<WebElement> codeList;

    @FindBy(xpath = "//div[@class='info']")
    protected WebElement description;

    @FindBy(xpath = "//input[@id='username']")
    protected WebElement username;

    @FindBy(xpath = "//button[@id='enterButton']")
    protected WebElement buttonSendCode;

    @FindBy(xpath = "//button[@class='button button--sign-in']")
    protected WebElement buttonOtpEnter;

    @FindBy(xpath = "//button[normalize-space()='Recuperar usuario']")
    protected WebElement buttonRecoveryUser;

    @FindBy(xpath = "//label[@id='usernameError']")
    protected WebElement checkErrorInvalid;

    @FindBy(xpath = "//label[@id='usernameError']")
    protected WebElement userNameError;

    @FindBy(xpath = "//img[@alt='personal flow']")
    protected WebElement imgLogoTranstion;

    @FindBy(xpath = "//p[@class='transition__page__otp__description--text']")
    protected WebElement descriptionOtpTranstion;

    @FindBy(xpath = "//p[@class='container__otp__message--error']")
    protected WebElement messageErrorCode;

    public List<WebElement> getCodeList() { return codeList; }

    public WebElement getImgLogo() {
        return imgLogo;
    }

    public WebElement getdescription() {
        return description;
    }

    public WebElement getMailOrNombreInput() {
        return username;
    }

    public WebElement getButtonSendCode() {
        return buttonSendCode;
    }

    public WebElement getButtonRecoveryUser() {
        return buttonRecoveryUser;
    }

    public WebElement getCheckErrorInvalid() {
        return checkErrorInvalid;
    }

    public WebElement getUserNameError() {
        return userNameError;
    }

    public WebElement getImgLogoTranstion() {
        return imgLogoTranstion;
    }

    public WebElement getDescriptionOtpTranstion() {
        return descriptionOtpTranstion;
    }

    public WebElement getMessageErrorCode() {
        return messageErrorCode;
    }

}
