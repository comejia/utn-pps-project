package flow.pageActions;

import flow.pageObjects.OTPInputCodePageFlow;
import flow.webdriverUtils.ExtendedWebDriver;

import static org.junit.Assert.assertTrue;

public class OTPInpuntCodeAction extends OTPInputCodePageFlow {
    public OTPInpuntCodeAction(ExtendedWebDriver webDriver) { super(webDriver);  }

    public void pageIsDisplay(){
        assertTrue("Element image logo Flow is not displayed", this.getImgLogo().isEnabled());
        assertTrue("Element check  menssage send code not displayed", this.getCheckMenssageSendCode().isEnabled());
        assertTrue("Element button enter not displayed",this.getButtonEnter().isDisplayed());
    }

    public void enterInputCode(){
        this.containerOtpCode.sendKeys("123456");
    }

    public void enterUsernameEmpty() {
        this.containerOtpCode.sendKeys("");
    }

    public void enterInputCodeIncomplete(){
        this.containerOtpCode.sendKeys("12345");
    }

    public void checkEnterButtonDisplayed(){
        this.getButtonEnter().isDisplayed();
    }

    public void checkEnterButtonResendCode(){
        webDriver.waitUntil(126, wd -> getButtonResendCode().isEnabled());
        assertTrue("Element Button resend code not displayed", buttonResendCode.isEnabled());
        logger.info("Element are displayed");
    }

    public void enterButtonDisabled(){
        assertTrue("Element button enter not displayed",this.getButtonEnter().isDisplayed());
    }

    public void enterButton(){
        this.getButtonEnter().click();
    }
}
