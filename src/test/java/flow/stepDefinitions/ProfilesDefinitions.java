package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.ProfileSelectionPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.awt.*;

import static org.junit.Assert.assertTrue;

public class ProfilesDefinitions {

    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected CommonPageAction commonPageAction;

    public ProfilesDefinitions() {
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
    }

    @Y("El usuario selecciona un perfil")
    public void elUsuarioSeleccionaUnPerfil() {
        this.profileSelectionPageAction.selectUserProfile(0);
    }

    @Entonces("La pantalla de selección de perfiles se muestra correctamente")
    public void laPantallaDeSeleccionDePerfilesSeMuestraCorrectamente() {
        this.profileSelectionPageAction.pageIsDisplayed();
    }

    @Y("El usuario selecciona un perfil diferente al actual")
    public void elUsuarioSeleccionaUnPerfilDiferenteAlActual() {
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameProfile_ProfileName2());
    }

    @Cuando("El usuario modifica el nombre del perfil a 'CAMBIADO'")
    public void elUsuarioModificaElNombreDelPerfilACAMBIADO() throws InterruptedException, AWTException {
        this.commonPageAction.accessChangeProfilePage();
        this.profileSelectionPageAction.accessManageProfiles();
        this.profileSelectionPageAction.changeUserProfileName(2, "CAMBIADO");
    }

    @Y("El usuario accede a la pantalla de administración de perfiles")
    public void elUsuarioAccedeALaPantallaDeAdministracionDePerfiles() {
        this.commonPageAction.accessChangeProfilePage();
        this.profileSelectionPageAction.accessManageProfiles();
    }

    @Entonces("Se muestra el nombre 'CAMBIADO' del perfil seleccionado")
    public void seMuestraElNombreCAMBIADODelPerfilSeleccionado() {
        //TODO check if the user profile got the new name correctly
    }

    @Cuando("El usuario modifica el nombre del perfil al original")
    public void elUsuarioModificaElNombreDelPerfilAlOriginal() throws InterruptedException, AWTException {
        this.profileSelectionPageAction.accessManageProfiles();
        this.profileSelectionPageAction.changeUserProfileName(2, "PRUEBA3");
    }

    @Entonces("Se muestra el nombre original del perfil")
    public void seMuestraElNombreOriginalDelPerfil() {
        //TODO check if the user profile got the new name correctly
    }

    @Cuando("El usuario modifica la imagen del perfil")
    public void elUsuarioModificaLaImagenDelPerfil() {
        this.commonPageAction.accessChangeProfilePage();
        this.profileSelectionPageAction.accessManageProfiles();
        this.profileSelectionPageAction.changeUserProfilePicture(1, "left");
    }

    @Cuando("El usuario modifica la imagen del perfil a la original")
    public void elUsuarioModificaLaImagenDelPerfilALaOriginal() {
        this.profileSelectionPageAction.accessManageProfiles();
        this.profileSelectionPageAction.changeUserProfilePicture(1, "right");
    }

    @Entonces("Se muestra la nueva imagen del perfil")
    public void seMuestraLaNuevaImagenDelPerfil() {
        //TODO add check that the image has changed
    }

    @Entonces("Se muestra la imagen original del perfil")
    public void seMuestraLaImagenOriginalDelPerfil() {
        //TODO add check that the image has changed to the original one
    }

    @Cuando("El usuario crea un nuevo perfil")
    public void elUsuarioCreaUnNuevoPerfil() throws Throwable {
        this.commonPageAction.accessChangeProfilePage();
        this.profileSelectionPageAction.createProfile(Hooks.props.loginUsernameProfile_ProfileName4());
    }

    @Entonces("El nuevo perfil es creado correctamente")
    public void elNuevoPerfilEsCreadoCorrectamente() {
        //TODO check that the profile is created correctly
    }

    @Cuando("El usuario elimina el nuevo perfil")
    public void elUsuarioEliminaElNuevoPerfil() {
        this.profileSelectionPageAction.removeProfile();
    }

    @Entonces("El nuevo perfil es eliminado correctamente")
    public void elNuevoPerfilEsEliminadoCorrectamente() {
        this.profileSelectionPageAction.pageIsDisplayed();
    }

    @Cuando("El usuario desactiva el control parental")
    public void elUsuarioDesactivaElControlParental() {
        this.commonPageAction.disableParentalControl(Hooks.props.pinParental());
        this.commonPageAction.checkParentalControlIsDisable();
    }

    @Entonces("Se muestra mensaje de error indicando que el PIN es incorrecto")
    public void seMuestraMensajeDeErrorIndicandoQueElPinEsIncorrecto() {
        assertTrue(this.commonPageAction.checkMessageErrorIncorrectPinParentalControl());
    }

    @Y("El usuario se encuentra en la pantalla de selección de perfiles")
    public void elUsuarioSeEncuentraEnLaPantallaDeSeleccionDePerfiles() {
        this.profileSelectionPageAction.pageIsDisplayed();
    }

    @Y("El usuario vuelve a activar el control parental")
    public void elUsuarioVuelveAActivarElControlParental() {
        this.commonPageAction.activateParentalControl();
    }

    @Cuando("El usuario modifica el nombre del perfil")
    public void elUsuarioModificaElNombreDelPerfil() throws InterruptedException, AWTException {
        this.profileSelectionPageAction.accessManageProfiles();
        this.profileSelectionPageAction.changeUserProfileName(2,"HOLA");
    }

    @Entonces("Se muestra el nombre del perfil modificado")
    public void seMuestraElNombreDelPerfilModificado() {
        this.profileSelectionPageAction.checkNameProfile();
    }
    
    @Cuando("El usuario selecciona el nuevo perfil creado")
    public void elUsuarioSeleccionaElNuevoPerfilCreado() throws AWTException {
        this.profileSelectionPageAction.selectUserProfile(3);
    }

    @Cuando("El usuario intenta crear un perfil con nombre vacio")
    public void elUsuarioIntentaCrearUnPerfilConNombreVacio() throws AWTException {
        this.commonPageAction.accessChangeProfilePage();
        this.profileSelectionPageAction.accessManageProfiles();
    }

    @Entonces("No es posible crear el perfil con nombre vacio")
    public void noEsPosibleCrearElPerfilConNombreVacio() {
        this.profileSelectionPageAction.pageDisplayedNewProfile();
    }

    @Cuando("El usuario intenta desactivar el control parental utilizando un PIN erróneo")
    public void elUsuarioIntentaDesactivarElControlParentalUtilizandoUnPinErroneo() {
        this.commonPageAction.accessParentalControl();
        this.commonPageAction.enterParentalControlPinAndConfirm("4321");
    }

    @Cuando("El usuario intenta activar el control parental")
    public void elUsuarioIntentaActivarElControlParental() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.settingsControlParental();
    }

    @Entonces("Se muestra un mensaje indicando cómo configurar el PIN")
    public void seMuestraUnMensajeIndicandoComoConfigurarElPIN() {
        this.commonPageAction.pageIsDisplayedSettingsControlParental();
    }

    @Entonces("El mensaje para configurar el control parental se muestra correctamente")
    @Entonces("Se solicita al usuario desactivar el control parental")
    public void elMensajeDeConfiguracionDelControlParentalSeMuestraCorrectamente() {
        this.commonPageAction.checkMessageConfigurationParentalControl();
    }

    @Y("Cancela la activación del control parental")
    public void cancelaLaActivacionDelControlParental() {
        this.commonPageAction.cancelParentalControlActivation();
    }

    @Entonces("La pantalla de 'Mi Perfil' se muestra correctamente")
    public void laPantallaDeMiPerfilSeMuestraCorrectamente() {
        this.profileSelectionPageAction.pageIsDisplayed();
    }

    @Cuando("El usuario accede a la sección 'Mi Perfil'")
    public void elUsuarioAccedeALaSeccionMiPerfil() {
        this.commonPageAction.accessChangeProfilePage();
    }
}
