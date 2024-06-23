package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.LoginPageAction;
import flow.pageActions.MyAccountPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.util.logging.Logger;


public class MyAccountDefinitions {

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    protected CommonPageAction commonPageAction;
    protected MyAccountPageAction myAccountPageAction;
    protected LoginPageAction loginPageAction;

    public MyAccountDefinitions() {
        this.myAccountPageAction = new MyAccountPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        //this.loginPageAction = new LoginPageAction(Hooks.getDriver());
    }

    @Y("El usuario accede a la sección 'Mi cuenta'")
    public void elUsuarioAccedeALaSeccionMiCuenta() {
        this.commonPageAction.clickDropdownUser();
        this.myAccountPageAction.clickDropdownEditProfile();
        logger.info("User enters 'Mi cuenta' section");
    }

    @Cuando("El usuario accede a 'Configuración de Flow'")
    public void elUsuarioAccedeAConfiguracionDeFlow() {
        this.myAccountPageAction.clickConfigurationButton();
        this.myAccountPageAction.clickConfigurationTextRedirect();
        this.myAccountPageAction.clickConfigurationButtonRedirect();
        logger.info("User enters 'Configuración' and selects button 'Ir a la Configuración de Flow'");
    }

    @Y("El usuario selecciona 'Control Parental'")
    public void elUsuarioSeleccionaControlParental() {
        this.commonPageAction.clickDropdownUser();
        this.myAccountPageAction.clickDropdownParentalControl();
        this.myAccountPageAction.clickPopUpConfigureButton();
        logger.info("User enable 'Control Parental'");
    }

    @Entonces("Se redirige a 'Configuración de Flow' desde 'Mi cuenta' correctamente")
    public void seRedirigeAConfiguracionDeFlowDesdeMiCuentaCorrectamente() {
        this.commonPageAction.switchToNewTab(1);
        this.myAccountPageAction.clickConfigurationRedirectHomePage();
        logger.info("User is redirected to Flow configuration page");
    }

    @Entonces("Se redirige a 'Configuración de Flow' desde 'Control parental' correctamente")
    public void seRedirigeAConfiguracionDeFlowDesdeControlParentalCorrectamente() {
        this.myAccountPageAction.configurationRedirectHomePageIsDisplayed();
        logger.info("User is redirected to Flow configuration page");
    }

    @Entonces("La sección configuración se muestra correctamente")
    public void laSeccionConfiguracionSeMuestraCorrectamente() {
        this.commonPageAction.switchToNewTab(1);
        this.myAccountPageAction.configurationElementsAreDisplayed();
        logger.info("'Configuración' items displayed");
    }

    @Cuando("El usuario accede a 'Configuración de Flow' desde 'Mi cuenta'")
    public void elUsuarioAccedeAConfiguracionDeFlowDesdeMiCuenta() {
        this.commonPageAction.clickDropdownUser();
        this.myAccountPageAction.clickDropdownEditProfile();
        logger.info("User enters 'Configuración' and selects button 'Ir a la Configuración de Flow'");
    }

    @Cuando("El usuario accede a la configuracion de usuario")
    public void elUsuarioAccedeALaConfiguracionDeUsuario() {
        this.myAccountPageAction.clickDropdownManage();
        this.myAccountPageAction.clickButtonDeviceManage();
        logger.info("User enters 'Configuration' and selects button 'Device Manager of Flow'");
    }

    @Entonces("El usuario es redireccionado a la pantalla de 'Configuración de Flow' correctamente")
    public void elUsuarioEsRedireccionadoALaPantallaDeConfiguraciónDeFlowCorrectamente() {
        this.commonPageAction.switchToNewTab(1);
        Hooks.getDriver().checkUrlRedirection(Hooks.props.pageTitleFlowConfig(),
                Hooks.props.pageUrlFlowConfig());
    }

    @Cuando("El usuario accede a la sección 'Mi Cuenta'")
    public void elUsuarioAccedeALaSecciónMiCuenta() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.myAccountPageAction.clickDropdownManage();
        this.commonPageAction.clickDropdownMyAccount();
    }

    @Entonces("La pantalla de 'Mi Cuenta' se muestra correctamente")
    public void laPantallaDeMiCuentaSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayedMyAccount();
    }
}
