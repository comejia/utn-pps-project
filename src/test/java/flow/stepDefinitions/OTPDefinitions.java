package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.LoginPageAction;
import flow.pageActions.OTPInpuntCodeAction;
import flow.pageActions.OTPSendCodeAction;
import flow.pageActions.ProfileSelectionPageAction;
import flow.pageObjects.LoginPageFlow;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;


public class OTPDefinitions {

    protected OTPSendCodeAction otpSendCodeAction;
    protected OTPInpuntCodeAction otpInpuntCodeAction;
    protected LoginPageFlow loginPageFlow;
    protected LoginPageAction loginPageAction;

    protected ProfileSelectionPageAction profileSelectionPageAction;

    public OTPDefinitions() {
        this.otpSendCodeAction = new OTPSendCodeAction(Hooks.getDriver());
        this.otpInpuntCodeAction = new OTPInpuntCodeAction(Hooks.getDriver());
        this.loginPageFlow = new LoginPageFlow(Hooks.getDriver());
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());    }

    @Entonces("La pantalla de 'Envio de código' por mail se muestra correctamente")
    public void laPantallaDeEnvioDeCodigoPorMailSeMuestraCorrectamente() {
        Hooks.getDriver().checkUrlRedirection(Hooks.props.pageTitleOpteCodeValidation(),
                Hooks.props.pageUrlOtpCodeValidation());
    }

    @Entonces("La pantalla 'Ingresar sin contraseña' se muestra correctamente")
    public void laPantallaIngresarSinContrasenaSeMuestraCorrectamente() {
        this.otpSendCodeAction.pageIsDisplay();
    }

    @Cuando("El usuario ingresa 'Mail o numero' inválido")
    public void elUsuarioIngresaMailONumeroInvalido() {
        this.otpSendCodeAction.pageIsDisplay();
        this.otpSendCodeAction.enterUsername(Hooks.props.Username());
        this.otpSendCodeAction.sendCode();
    }

    @Entonces("Se muestra mensaje de error indicando que los datos son incorrectos")
    public void seMuestraMensajeDeErrorIndicandoQueLosDatosSonIncorrectos() {
        this.otpSendCodeAction.checkErrorInvalid();
    }

    @Cuando("El usuario ingresa campo 'Mail o numero' con formato incorrecto")
    public void elUsuarioIngresaCampoMailONumeroConFormatoIncorrecto() {
        this.otpSendCodeAction.pageIsDisplay();
        this.otpSendCodeAction.enterUsername(Hooks.props.UserIncorrectFormat());
    }

    @Entonces("Se muestra mensaje de error indicando formato incorrecto de mail o numero")
    public void seMuestraMensajeDeErrorIndicandoFormatoIncorrectoDeMailONumero() {
        this.otpSendCodeAction.checkErrorFormat();
    }

    @Cuando("El usuario no ingresa campo 'Mail o numero'")
    public void elUsuarioNoIngresaCampoMailONumero() {
        this.otpSendCodeAction.enterUsernameEmpty();
    }

    @Entonces("No es posible enviar codigo")
    public void noEsPosibleEnviarCodigo() {
        this.otpSendCodeAction.buttonSendCodeDisabled();
    }

    @Cuando("El usuario accede a 'Recuperar usuario' de la sección")
    public void elUsuarioAccedeARecuperarUsuarioDeLaSeccion() {
        this.otpSendCodeAction.buttonRecoveryUser();
    }

    @Entonces("El usuario es redireccionado a la pantalla de recuperar usuario")
    public void elUsuarioEsRedireccionadoALaPantallaDeRecuperarUsuario() {
        Hooks.getDriver().checkUrlRedirection(Hooks.props.pageTitleOtpTitleRecoveryUser(),
                Hooks.props.pageUrlOtpRecoveryUser());
    }

    @Y("El usuario ingresa 'Mail o numero' valido")
    public void elUsuarioIngresaMailONumeroValido() {
        this.otpSendCodeAction.enterUsername(Hooks.props.loginUsername());
        this.otpSendCodeAction.sendCode();
    }

    @Cuando("El usuario ingresa campo 'código' no valido")
    public void elUsuarioIngresCampoCodigoNoValido() {
        this.otpInpuntCodeAction.enterInputCode();
        this.otpInpuntCodeAction.enterButton();
    }

    @Entonces("El ingreso del código se encuentra habilitado")
    public void elIngresoDelCodigoSeEncuentraHabilitado() {
        this.otpInpuntCodeAction.checkEnterButtonDisplayed();
    }

    @Entonces("La pantalla de 'Ingresar código' se muestra correctamente")
    public void laPantallaDeIngresarCodigoSeMuestraCorrectamente() {
        this.otpInpuntCodeAction.pageIsDisplay();
    }

    @Cuando("El usuario no ingresa campo 'código'")
    public void elUsuarioNoIngresaCampoCodigo() {
        this.otpInpuntCodeAction.enterUsernameEmpty();
    }

    @Entonces("El ingreso del código se encuentra deshabilitado")
    public void elIngresoDelCodigoSeEncuentraDeshabilitado() {
        this.otpInpuntCodeAction.enterButtonDisabled();
    }

    @Cuando("El usuario ingresa 'Mail' valido")
    public void elUsuarioIngresaMailValido() {
        this.otpSendCodeAction.enterUsername(Hooks.props.loginUsername());
        this.otpSendCodeAction.sendCode();
    }

    @Entonces("La pantalla de transicion de 'Envio de código' por mail se muestra correctamente")
    public void laPantallaDeTransicionDeEnvioDeCodigoPorMailSeMuestraCorrectamente() {
        this.otpSendCodeAction.pageScreenOtpTransition();
    }

    @Y("El usuario ingresa numero de telefono en el login")
    public void elUsuarioIngresaNumeroDeTelefonoEnElLogin() {
        this.otpSendCodeAction.enterUsername(Hooks.props.loginUsernamePhoneNumber());
    }

    @Entonces("La pantalla de 'Envio de código' por SMS se muestra correctamente")
    public void laPantallaDeEnvioDeCodigoPorSMSSeMuestraCorrectamente() {
        this.otpInpuntCodeAction.pageIsDisplay();
    }

    @Cuando("El usuario ingresa campo 'código' no inválido")
    public void elUsuarioIngresaCampoCodigoNoInvalido() {
        this.otpInpuntCodeAction.enterInputCode();
        this.otpInpuntCodeAction.enterButton();
    }

    @Entonces("Se muestra mensaje de error indicando que el código es incorrecto")
    public void seMuestraMensajeDeErrorIndicandoQueElCodigoEsIncorrecto() {
        this.otpSendCodeAction.messageInvalidCode();
    }

    @Cuando("El usuario ingresa 'numero' valido")
    public void elUsuarioIngresaNumeroValido() {
        this.otpSendCodeAction.enterUsername(Hooks.props.loginUsernamePhoneNumber());
        this.otpSendCodeAction.sendCode();
    }

    @Entonces("Se habilita el reenvio de codigo despues de un tiempo")
    public void seHabilitaElReenvioDeCodigoDespuesDeUnTiempo() {
        this.otpInpuntCodeAction.checkEnterButtonResendCode();
    }

    @Cuando("El usuario ingresa campo 'código'")
    public void elUsuarioIngresaCampoCodigo() {
        this.otpInpuntCodeAction.enterInputCode();
    }

    @Entonces("La pantalla de transicion de 'Envio de código' por SMS se muestra correctamente")
    public void laPantallaDeTransicionDeEnvioDeCodigoPorSMSSeMuestraCorrectamente() {
        this.otpSendCodeAction.pageScreenOtpTransitionSms();
    }

    @Cuando("El usuario ingresa campo 'Mail o numero'")
    public void elUsuarioIngresaCampoMailONumero() {
        this.otpSendCodeAction.enterUsername(Hooks.props.loginUsername());
        this.otpSendCodeAction.sendCode();
    }

    @Cuando("El usuario ingresa código otp valido")
    public void elUsuarioIngresaCodigoOtpValido() {
        this.otpSendCodeAction.otpLogin();
        this.profileSelectionPageAction.selectUserProfile(0);
    }
}
