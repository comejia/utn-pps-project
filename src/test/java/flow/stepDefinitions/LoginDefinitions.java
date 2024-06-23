package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.CommonPageAction;
import flow.pageActions.LoginPageAction;
import flow.pageActions.PreLoginPageAction;
import flow.pageActions.ProfileSelectionPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class LoginDefinitions {

    protected LoginPageAction loginPageAction;
    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected CommonPageAction commonPageAction;
    protected PreLoginPageAction preLoginPageAction;

    public LoginDefinitions() {
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.preLoginPageAction = new PreLoginPageAction(Hooks.getDriver());
    }

    @Entonces("La pantalla de login se muestra correctamente")
    public void laPantallaDeLoginSeMuestraCorrectamente() {
        this.loginPageAction.pageIsDisplayed();
    }

    @Cuando("El usuario ingresa credenciales válidas")
    public void elUsuarioIngresaCredencialesValidas() {
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.props.loginPassword(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
    }

    @Cuando("El usuario con mas de un perfil ingresa credenciales válidas")
    public void elUsuarioConMasDeUnPerfilIngresaCredencialesValidas() {
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(),
                Hooks.props.loginPasswordProfile(),
                true);
    }

    @Cuando("El usuario ingresa campo contraseña")
    public void elUsuarioIngresaCampoContrasena() {
        this.loginPageAction.enterPassword(Hooks.props.loginPassword());
    }

    @Y("El usuario activa 'Mostrar contraseña'")
    public void elUsuarioActivaMostrarContrasena() {
        this.loginPageAction.showPassword();
    }

    @Entonces("La contraseña es visible")
    public void laContrasenaEsVisible() {
        this.loginPageAction.checkShowPassword();
    }

    @Cuando("El usuario no ingresa campo usuario")
    public void elUsuarioNoIngresaCampoUsuario() {
        this.loginPageAction.enterPassword(Hooks.props.loginPassword());
    }

    @Entonces("No es posible realizar el login con campo username vacío")
    public void noEsPosibleRealizarElLoginConCampoUsernameVacio() {
        this.loginPageAction.checkSubmitButtonIsDisabled();
    }

    @Cuando("El usuario accede a 'Recuperar contraseña'")
    public void elUsuarioAccedeARecuperarContrasena() {
        this.loginPageAction.redirectUrlPasswordRecovery();
    }

    @Cuando("El usuario accede a 'Recuperar usuario'")
    public void elUsuarioAccedeARecuperarUsuario() {
        this.loginPageAction.redirectUserRecovery();
    }

    @Cuando("El usuario accede a 'Ayuda'")
    public void elUsuarioAccedeAAyuda() {
        this.loginPageAction.redirectLinkHelpCenter();
    }

    @Cuando("El usuario ingresa credenciales inválidas")
    public void elUsuarioIngresaCredencialesInvalidas() {
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                "12345678",
                false);
    }

    @Entonces("Se muestra mensaje de error indicando credenciales inválidas")
    public void seMuestraMensajeDeErrorIndicandoCredencialesInvalidas() {
        this.loginPageAction.checkErrorMessage("Los datos ingresados son incorrectos.");
    }

    @Cuando("El usuario ingresa campo email con formato incorrecto")
    public void elUsuarioIngresaCampoEmailConFormatoIncorrecto() {
        this.loginPageAction.loginUser(Hooks.props.loginUsername().concat(".ar"),
                Hooks.props.loginPassword(),
                false);
    }

    @Entonces("Se muestra mensaje de error indicando formato incorrecto de email")
    public void seMuestraMensajeDeErrorIndicandoFormatoIncorrectoDeEmail() {
        this.loginPageAction.checkErrorMessage("Los datos ingresados son incorrectos.");
    }

    @Cuando("El usuario ingresa credenciales inválidas con caracteres especiales")
    public void elUsuarioIngresaCredencialesInvalidasConCaracteresEspeciales() {
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.stringSpecialChars,
                false);
    }

    @Entonces("Se muestra mensaje de error indicando los datos ingresados son incorrectos")
    public void seMuestraMensajeDeErrorIndicandoVerificarLosDatosIngresadosSonIncorrectos() {
        this.loginPageAction.checkErrorMessage("Los datos ingresados son incorrectos.");
    }

    @Cuando("El usuario ingresa credenciales válidas de otro producto")
    public void elUsuarioIngresaCredencialesValidasDeOtroProducto() {
        this.loginPageAction.loginUser(Hooks.props.loginUsernameOtherSite(),
                Hooks.props.loginPasswordOtherSite(),
                false);
    }

    @Cuando("El usuario no ingresa campo contraseña")
    public void elUsuarioNoIngresaCampoContrasena() {
        this.loginPageAction.loginUserMissingFields(Hooks.props.loginUsername(), "");
    }

    @Entonces("No es posible realizar el login con campo contraseña vacío")
    public void noEsPosibleRealizarElLoginConCampoContrasenaVacio() {
        this.loginPageAction.checkSubmitButtonIsDisable();
    }

    @Cuando("El usuario ingresa credenciales inválidas con menos de 4 caracteres")
    public void elUsuarioIngresaCredencialesInvalidasConMenosDeCaracteres() {
        this.loginPageAction.loginUserMissingFields(Hooks.props.loginUsername(), "123");
    }

    @Entonces("Se muestra mensaje de error indicando que la contraseña debe tener un mínimo de 4 caracteres")
    public void seMuestraMensajeDeErrorIndicandoQueLaContrasenaDebeTenerUnMinimoDe4Caracteres() {
        this.loginPageAction.errorMessageisDisplayed("Debe tener entre 4 y 10 numeros");
    }

    @Entonces("Se muestra el DeviceID en la pantalla de login")
    public void seMuestraElDeviceIDEnLaPantallaDeLogin() {
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.checkDeviceIdIsDisplayed();
    }

    @Cuando("El usuario accede a 'Registrarme'")
    public void elUsuarioAccedeARegistrarme() {
        this.loginPageAction.redirectRegister();
    }

    @Cuando("El usuario accede a 'Tengo un codigo'")
    public void elUsuarioAccedeATengoUnCodigo() {
        this.loginPageAction.iHaveACodeRedirect();
    }

    @Cuando("El usuario accede a 'Activar Flow'")
    public void elUsuarioAccedeAActivarFlow() {
        this.loginPageAction.buttonActiveFlow();
    }

    @Y("El usuario ingresa mail en el login")
    public void elUsuarioIngresaMailEnElLogin() {
        this.loginPageAction.enterUsername(Hooks.props.loginUsername());
    }

    @Cuando("El usuario accede a 'Ingresar sin contraseña'")
    public void elUsuarioAccedeAIngresarSinContraseña() {
        this.loginPageAction.buttonLoginWithoutPassword();
    }

    @Dado("Que el usuario accede a la pantalla 'Ingresar sin contraseña'")
    public void queElUsuarioAccedeALaPantallaIngresarSinContrasena() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.buttonLoginWithoutPassword();
    }

    @Dado("Que el usuario accede desde un pais no disponible en Flow")
    public void queElUsuarioAccedeDesdeUnPaisNoDisponibleEnFlow() {
        this.commonPageAction.mockResponse("/geo/v1/country", "US");
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
    }

    @Entonces("La pantalla de 'error de pais' se muestra correctamente")
    public void laPantallaDeErrorDePaisSeMuestraCorrectamente() {
        loginPageAction.countryErrorPageIsDisplayed();
    }

    @Entonces("El usuario es redireccionado a la pantalla de activar cuenta")
    public void elUsuarioEsRedireccionadoALaPantallaDeActivarCuenta() {
        this.loginPageAction.pageIsDisplayed();
    }

    @Y("El usuario ingresa contraseña con menos de 4 caracteres")
    public void elUsuarioIngresaContrasenaConMenosDeCaracteres() {
        this.loginPageAction.enterPassword(Hooks.props.loginPasswordIncorrect());
    }

    @Entonces("Se muestra mensaje de error indicando verificar los datos ingresados")
    public void seMuestraMensajeDeErrorIndicandoVerificarLosDatosIngresados() {
        this.loginPageAction.checkErrorMessage("Los datos ingresados son incorrectos.");
    }

    @Entonces("Se muestra mensaje de error indicando que los datos ingresados son incorrectos.")
    public void seMuestraMensajeDeErrorIndicandoQueLosDatosIngresadosSonIncorrectos() {
        this.loginPageAction.errorMessagePassword();
    }

    @Y("El usuario ingresa contraseña con menos de 6 caracteres")
    public void elUsuarioIngresaContraseñaConMenosDeCaracteres() {
        this.loginPageAction.enterPassword(Hooks.props.loginPasswordIncorrect());
    }

    @Cuando("El usuario ingresa credenciales con mora")
    public void elUsuarioIngresaCredencialesConMora() {
        this.loginPageAction.loginUser(Hooks.props.loginUserNameMora(),
                Hooks.props.loginPasswordMora(),
                false);
    }

    @Entonces("Se muestra el mensaje indicando que el cliente esta en mora")
    public void seMuestraElMensajeIndicandoQueElClienteEstaEnMora() {
        this.loginPageAction.pageIsDisplayedMora();
    }
}


