package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.*;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class OnboardingDefinitions {

    protected CommonPageAction commonPageAction;
    protected LoginPageAction loginPageAction;
    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected OnboardingPageAction onboardingPageAction;
    protected PreLoginPageAction preLoginPageAction;

    public OnboardingDefinitions() {
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.onboardingPageAction = new OnboardingPageAction(Hooks.getDriver());
        this.preLoginPageAction = new PreLoginPageAction(Hooks.getDriver());
    }

    @Dado("Que el usuario accede a la aplicación por primera vez")
    public void queElUsuarioAccedeALaAplicacionPorPrimeraVez() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(), Hooks.props.loginPasswordProfile(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameProfile_ProfileName1());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
        this.onboardingPageAction.setIntoBrowserSessionStorage(true, Hooks.props.loginUsernameProfile_ProfileId());
    }

    @Entonces("El onboarding se muestra correctamente")
    public void elOnboardingSeMuestraCorrectamente() {
        this.onboardingPageAction.checkOnboardingIsDisplayed();
    }

    @Y("El usuario cierra el onboarding")
    public void elUsuarioCierraElOnboarding() {
        this.onboardingPageAction.closeOnboardingInOnboarding();
    }

    @Entonces("El onboarding no es visualizado")
    public void elOnboardingNoEsVisualizado() {
        this.onboardingPageAction.checkOnboardingIsNotDisplayed();
    }

    @Y("El usuario cierra el onboarding sin usar el botón 'cerrar'")
    public void elUsuarioCierraElOnboardingSinUsarElBotonCerrar() {
        this.onboardingPageAction.closeOnboardingNoCloseButton();
    }

    @Entonces("El onboarding deja de mostrarse")
    public void elOnboardingDejaDeMostrarse() {
        this.onboardingPageAction.checkOnboardingIsNotDisplayed();
    }

    @Entonces("La pantalla de 'Onboarding' se muestra correctamente")
    public void laPantallaDeOnboardingSeMuestraCorrectamente() {
        this.onboardingPageAction.checkOnboardingIsDisplayed();
    }
}
