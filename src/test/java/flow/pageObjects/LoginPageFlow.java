package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageFlow extends Page {

    @FindBy(xpath = "//img[@src=\"/img/convergent-login/personal-flow-logo.svg\"]//..")
    private WebElement imgLogoFlow;
    @FindBy(id = "username")
    private WebElement inputUsername;
    @FindBy(id = "password")
    private WebElement inputPassword;
    @FindBy(xpath = ".//input[@id='password' and @type='password']")
    private WebElement inputPasswordHidden;
    @FindBy(xpath = ".//input[@id='password' and @type='text']")
    private WebElement inputPasswordToggled;
    @FindBy(id = "enterButton")
    private WebElement buttonSubmit;
    @FindBy(xpath = ".//button[@id='enterButton' and @disabled]")
    private WebElement buttonSubmitDisabled;
    @FindBy(xpath = "//button[normalize-space()='Registrarme']")
    private WebElement buttonRegister;
    @FindBy(xpath = "//button[normalize-space()='REGISTRARME']")
    private WebElement buttonConfirmationOfRegister;
    @FindBy(xpath = "//p[text()='Tengo un código para Flow']")
    private WebElement buttonIhaveCode;
    @FindBy(xpath = "//button[normalize-space()='ACTIVAR FLOW']")
    private WebElement buttonActiveFlow;
    @FindBy(id = "passwordToggle")
    private WebElement buttonPasswordToggle;
    @FindBy(id = "rememberMe")
    private WebElement checkboxRememberMe;
    @FindBy(xpath = ".//a[@class='forgot-link' and text()='RECUPERAR USUARIO']")
    private WebElement buttonUserRecovery;
    @FindBy(xpath = ".//a[@class='forgot-link' and text()='RECUPERAR CONTRASEÑA']")
    private WebElement buttonPasswordRecovery;
    @FindBy(xpath = "//button[normalize-space()='Ingresar sin contraseña']")
    private WebElement buttonLoginWithoutPassword;
    @FindBy(id = "help")
    private WebElement help;
    @FindBy(xpath = ".//a[text()='CENTRO DE AYUDA']")
    private WebElement linkHelpCenter;
    @FindBy(id = "deviceId")
    private WebElement deviceId;
    @FindBy(className = "errorBox")
    private WebElement errorBox;
    @FindBy(xpath = ".//div[contains(@class,'row') and contains(@class,'form-group') and contains(@class,'info')]/p")
    private WebElement forgotPasswordDescription;
    @FindBy(xpath = ".//form[contains(@name,'RecuperarUsuario')]//div[contains(@class,'row') and contains(@class,'form-group') and contains(@class,'info')]/p")
    private WebElement forgotUsernameDescription;
    @FindBy(xpath = ".//button[@type='button']")
    private WebElement buttonSendForgotPassword;
    @FindBy(xpath = ".//*[contains(text(),'Debe tener entre 4 y 10 números')]")
    private WebElement messageErrorText;
    @FindBy(xpath = "//label[@id='passwordError']")
    private WebElement errorPassword;
    @FindBy(xpath = "//p[normalize-space()='Flow no está disponible en tu ubicación']")
    private WebElement countryErrorMessage;
    @FindBy(xpath = "//div[@class='navbar-header' and @id='header-xs']")
    private WebElement logoMora;

    public WebElement getMessageErrorMora() {
        return messageErrorMora;
    }

    @FindBy(xpath = "//p[@class='h5 text ng-binding' and text()='Este producto no está disponible por el momento.']")
    private WebElement messageErrorMora;

    public LoginPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLogoMora() { return logoMora; }
    public WebElement getImgLogoFlow() {
        return imgLogoFlow;
    }

    public WebElement getErrorPassword() { return errorPassword; }

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getInputPasswordHidden() {
        return inputPasswordHidden;
    }

    public WebElement getInputPasswordToggled() {
        return inputPasswordToggled;
    }

    public WebElement getButtonSubmit() {
        return buttonSubmit;
    }

    public WebElement getButtonSubmitDisabled() {
        return buttonSubmitDisabled;
    }

    public WebElement getButtonRegister() {
        return buttonRegister;
    }

    public WebElement getButtonActiveFlow() {
        return buttonActiveFlow;
    }

    public WebElement getButtonConfirmationOfRegister() {
        return buttonConfirmationOfRegister;
    }

    public WebElement getButtonIhaveCode() {
        return buttonIhaveCode;
    }

    public WebElement getButtonPasswordToggle() {
        return buttonPasswordToggle;
    }

    public WebElement getCheckboxRememberMe() {
        return checkboxRememberMe;
    }

    public WebElement getButtonUserRecovery() {
        return buttonUserRecovery;
    }

    public WebElement getButtonPasswordRecovery() {
        return buttonPasswordRecovery;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getLinkHelpCenter() {
        return linkHelpCenter;
    }

    public WebElement getDeviceId() {
        return deviceId;
    }

    public WebElement getErrorBox() {
        return errorBox;
    }

    public WebElement getButtonLoginWithoutPassword() {
        return buttonLoginWithoutPassword;
    }

    public WebElement getForgotPasswordDescription() {
        return forgotPasswordDescription;
    }

    public WebElement getForgotUsernameDescription() {
        return forgotUsernameDescription;
    }

    public WebElement getButtonSendForgotPassword() {
        return buttonSendForgotPassword;
    }

    public WebElement getMessageErrorText() {
        return messageErrorText;
    }

    public WebElement getCountryErrorMessage() {
        return countryErrorMessage;
    }
}
