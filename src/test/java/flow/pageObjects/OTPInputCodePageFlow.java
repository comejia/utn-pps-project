package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OTPInputCodePageFlow extends Page {

    public OTPInputCodePageFlow(ExtendedWebDriver webDriver) { super(webDriver); }

    @FindBy(xpath = "//div[@class='container__otp--logo']")
    protected WebElement imgLogo;

    @FindBy(xpath = "//p[@class='text text--empty-state']")
    protected WebElement checkMenssageSendCode;

    @FindBy(xpath = "//p[contains(@class,'text') and text()='Ingresá el código que te enviamos por mail a flowfactoryautomation@gmail.com']")
    protected WebElement titleQuestion;

    @FindBy(xpath = "//button[normalize-space()='Ingresar']")
    protected WebElement buttonEnter;

    @FindBy(xpath = "//button[normalize-space()='Reenviar código']")
    protected WebElement buttonResendCode;

    @FindBy(xpath = "//input[@aria-label='Please enter verification code. Digit 1']")
    protected WebElement containerOtpCode;

    public WebElement getButtonResendCode() { return buttonResendCode; }

    public WebElement getButtonEnter() { return buttonEnter; }

    public WebElement getTitleQuestion() { return titleQuestion; }

    public WebElement getCheckMenssageSendCode() { return checkMenssageSendCode; }

    public WebElement getImgLogo() { return imgLogo; }

    public WebElement getContainerOtpCode() { return containerOtpCode; }
}
