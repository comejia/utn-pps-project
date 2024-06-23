package flow.pageActions;

import flow.pageObjects.OTPSendCodePageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class OTPSendCodeAction extends OTPSendCodePageFlow {
    public OTPSendCodeAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplay() {
        assertTrue("Element image logo Flow is not displayed", this.getImgLogo().isEnabled());
        assertTrue("Element description menssage not displayed", this.getdescription().isEnabled());
        assertTrue("Element button send code not displayed", this.getButtonSendCode().isDisplayed());
        assertTrue("Element recovery user not displayed", this.getButtonRecoveryUser().isEnabled());
        assertTrue("Element mail or phone number input not displayed", this.getMailOrNombreInput().isEnabled());
    }

    public void pageScreenOtpTransition() {
        assertTrue("Element image page OTP Transition is not displayed", this.getImgLogoTranstion().isEnabled());
        assertTrue("Element description menssage OTP Transition not displayed", this.getDescriptionOtpTranstion().isEnabled());
    }

    public void pageScreenOtpTransitionSms() {
        assertTrue("Element image page OTP Transition is not displayed", this.getImgLogoTranstion().isEnabled());
        assertTrue("Element description menssage OTP Transition not displayed", this.getDescriptionOtpTranstion().isEnabled());
    }

    public void messageInvalidCode() {
        this.getMessageErrorCode().isDisplayed();
    }

    public void enterUsername(String username) {
        webDriver.waitUntil(10, wd -> getMailOrNombreInput().isDisplayed());
        this.getMailOrNombreInput().sendKeys(username);
        webDriver.sleep(70);
    }

    public void sendCode() {
        this.getButtonSendCode().click();
    }

    public void otpLogin() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsername()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsername()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginDisneyDesactivedOffer() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsernameDisneyDeactivatedOffer()).getMessage(); //usuario 1 // limite de grabaciones
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsernameDisneyDeactivatedOffer()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginDisneyLinkend() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsernameDisneyLinked()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsernameDisneyLinked()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginStarLinkend() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsernameStarLinked()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsernameStarLinked()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginStarUnlinkend() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsernameStarUnlinked()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsernameStarUnlinked()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginPurchasesBlocked() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUserNamePurchasesBlocked()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUserNamePurchasesBlocked()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginNoContentAvailable() {
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginNoChannelsFavorites()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginNoChannelsFavorites()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void otpLoginWithProfiles(){
        webDriver.waitUntil(10, wd -> codeList.size() == 6
                && codeList.stream().allMatch(WebElement::isDisplayed)
        );
        Hooks.dataProvider.otpPurge(Hooks.props.loginUsernameProfile()).getMessage();
        String codigo = Hooks.dataProvider.otpCode(Hooks.props.loginUsernameProfile()).getCode();
        System.out.println("CODIGO"+codigo);
        for (int i= 0; i<6; i++){
            codeList.get(i).sendKeys(String.valueOf(codigo.charAt(i)));
        }
        webDriver.elementClick(buttonOtpEnter);
    }

    public void checkErrorInvalid() {
        this.getCheckErrorInvalid().isDisplayed();
    }

    public void checkErrorFormat() {
        this.getUserNameError().isDisplayed();
    }

    public void enterUsernameEmpty() {
        this.getMailOrNombreInput().sendKeys("");
    }

    public void buttonSendCodeDisabled() {
        assertTrue("Element button send code not displayed", this.getButtonSendCode().isDisplayed());
    }

    public void buttonRecoveryUser() {
        this.getButtonRecoveryUser().click();
    }
}

