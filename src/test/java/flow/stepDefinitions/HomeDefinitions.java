package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.*;
import flow.pageObjects.HomePageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class HomeDefinitions {

    private final PreLoginPageAction preLoginPageAction;
    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    protected HomePageAction homePageAction;
    protected CommonPageAction commonPageAction;
    protected LoginPageAction loginPageAction;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected TvGuidePageAction tvGuidePageAction;
    protected HomePageFlow homePageFlow;
    protected HighlightsPageAction highlightsPageAction;
    protected MyContentFavoritesPageAction myContentFavoritesPageAction;
    protected OTPSendCodeAction otpSendCodeAction;
    protected GridOfPostersActions gridOfPostersActions;
    protected ExtendedWebDriver webDriver;

    public HomeDefinitions() {
        this.otpSendCodeAction = new OTPSendCodeAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.preLoginPageAction = new PreLoginPageAction(Hooks.getDriver());
        this.tvGuidePageAction = new TvGuidePageAction(Hooks.getDriver());
        this.highlightsPageAction = new HighlightsPageAction(Hooks.getDriver());
        this.homePageFlow = new HomePageFlow(Hooks.getDriver());
        this.myContentFavoritesPageAction = new MyContentFavoritesPageAction(Hooks.getDriver());
        this.gridOfPostersActions = new GridOfPostersActions(Hooks.getDriver());
        this.webDriver = new ExtendedWebDriver(Hooks.getDriver());
    }

    @Entonces("El usuario se encuentra en la página de inicio")
    public void elUsuarioSeEncuentraEnLaPaginaDeInicio() {
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Entonces("El usuario se encuentra en la página de inicio con el perfil seleccionado")
    public void elUsuarioSeEncuentraEnLaPaginaDeInicioConElPerfilSeleccionado() {
        this.commonPageAction.userIsLogged();
        this.commonPageAction.enableParentalControlIfDisabled();
        //TODO Check that the user profile is the one selected on previous step
    }

    @Dado("Que el usuario accede a la pantalla de inicio")
    public void queElUsuarioAccedeALaPantallaDeInicio() {
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                if (Hooks.localStorage.get("id_token") == null) {
                    this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
                    this.preLoginPageAction.accessLoginPage();
                    this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                            Hooks.props.loginPassword(),
                            false);
                    this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
                    this.commonPageAction.userIsLogged();
                    this.commonPageAction.setLocalStorageValues();
                } else {
                    this.commonPageAction.loadPageAuthenticated(Hooks.props.baseUrl());
                    this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
                    this.commonPageAction.userIsLogged();
                }
                return true;
            } catch (Throwable throwable) {
                this.commonPageAction.clearLocalStorageValues();
                return false;
            }
        });
        Hooks.props.setProperty("providerUsername", Hooks.props.loginUsername());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginUsername());
    }

    @Entonces("La pantalla de inicio se muestra correctamente")
    public void laPantallaDeInicioSeMuestraCorrectamente() {
        this.homePageAction.pageIsDisplayed();
    }

    @Cuando("El usuario visualiza el stripe 'Todos los títulos'")
    public void elUsuarioVisualizaElStripeTodosLosTitulos() {
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllTitles());
    }

    @Y("El usuario accede a un contenido aleatorio del stripe 'Todos los títulos'")
    public void elUsuarioAccedeAUnContenidoAleatorioDelStripeTodosLosTitulos() {
        this.commonPageAction.scrollDownVertical();
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeAllTitles);
    }

    @Cuando("El usuario accede a un contenido aleatorio del stripe 'Destacados Flow'")
    public void elUsuarioAccedeAUnContenidoAleatorioDelStripeDestacadoFlow() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.goToStripe(Hooks.props.destacadosFlow());
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeDestacadosFlow);
    }

    @Cuando("El usuario accede al stripe 'Lo mas visto en TV'")
    public void elUsuarioAccedeAlStripeLoMasVistoEnTv(){
        this.homePageAction.selectTheMostWatchingLiveTv();
    }

    @Cuando("El usuario accede a un stripe")
    public void elUsuarioAccedeAUnStripe() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessSeeMoreToStripe(this.commonPageAction.getTitleStripe());
    }

    @Cuando("El usuario accede a un stripe de la sección 'Películas'")
    public void elUsuarioAccedeAUnStripeDeLaSeccionPeliculas(){
        this.commonPageAction.accessNavbarElementMovies();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllMovies());
    }

    @Dado("Que el usuario con control parental activado accede a la pantalla de inicio")
    public void queElUsuarioConControlParentalActivadoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(),
                Hooks.props.loginPasswordProfile(),
                true);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
        this.commonPageAction.enableParentalControlIfDisabled();
        Hooks.props.setProperty("providerUsername", Hooks.props.loginUsernameProfile());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginPasswordProfile());
    }

    @Dado("Que el usuario con control parental activado accede a la sección 'Guía de Tv'")
    public void queElUsuarioConControlParentalActivadoAccedeALaSeccionGuiaDeTv() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(),
                Hooks.props.loginPasswordProfile(),
                true);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
        this.commonPageAction.enableParentalControlIfDisabled();
        this.commonPageAction.accessNavbarElementTvGuide();
        Hooks.props.setProperty("providerUsername", Hooks.props.loginUsernameProfile());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginPasswordProfile());
    }

    @Cuando("El usuario accede a un contenido del SuperHero")
    public void elUsuarioAccedeAUnContenidoDelSuperHero() {
        this.commonPageAction.clickActiveSuperHeroInfo();
    }

    @Dado("Que el usuario con mas de un perfil accede a la pantalla de inicio")
    public void queElUsuarioConMasDeUnPerfilAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(),
                Hooks.props.loginPasswordProfile(),
                true);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
        this.commonPageAction.userIsLogged();
        this.commonPageAction.enableParentalControlIfDisabled();
    }

    @Entonces("Se muestra la pantalla anterior")
    public void seMuestraLaPantallaDeInicio() {
        this.homePageAction.pageIsDisplayed();
    }

    @Cuando("El usuario visualiza el stripe 'Tv en vivo'")
    public void elUsuarioVisualizaElStripeTvEnVivo() {
        this.commonPageAction.checkStripeIsDisplayed("Tv en vivo");
    }

    @Entonces("Se muestra la barra de progreso de reproducción para los contenidos del stripe 'Tv en vivo'")
    public void seMuestraLaBarraDeProgresoDeReproduccionParaLosContenidosDelStripeTvEnVivo() {
        this.commonPageAction.scrollToContentStripe(Hooks.props.homePageLiveTv());
        this.commonPageAction.checkProgressBarStripe();
    }

    @Cuando("el usuario visualiza el stripe 'Continuar viendo'")
    public void elUsuarioVisualizaElStripeContinuarViendo() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.homePageContinueWatching());
    }

    @Entonces("Se muestra la barra de progreso de reproducción para los contenidos del stripe 'Continuar viendo'")
    public void seMuestraLaBarraDeProgresoDeReproduccionParaLosContenidosDelStripeContinuarViendo() {
        this.commonPageAction.checkProgressBarStripeContinueWatching();
    }

    @Y("El usuario accede a un contenido aleatorio del stripe 'Tv en vivo'")
    public void elUsuarioAccedeAUnContenidoAleatorioDelStripeTvEnVivo() {
        this.commonPageAction.scrollToContentStripe(Hooks.props.homePageLiveTv());
        this.commonPageAction.accesRandomStripeContent(Hooks.props.homePageLiveTv());
    }

    @Cuando("El usuario reproduce un contenido desde el stripe 'TV en vivo'")
    public void elUsuarioReproduceUnContenidoDesdeElStripeTVEnVivo() {
        this.commonPageAction.scrollToContentStripe(Hooks.props.homePageLiveTv());
        WebElement stripe = this.commonPageAction.getContentStripe(Hooks.props.homePageLiveTv());
        this.commonPageAction.stripeRandomItemPlay(stripe);
    }

    @Dado("Que el usuario sin packs accede a la pantalla de inicio")
    public void queElUsuarioSinPacksAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyDeactivatedOffer(),
                Hooks.props.loginPasswordDisneyDeactivatedOffer(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDeactivated_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario sin packs accede a la pantalla de inicio con onboarding Star+")
    public void queElUsuarioSinPacksAccedeALaPantallaDeInicioConOnboardingStar() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDeactivated_temp(),
                Hooks.props.loginPasswordDeactivated_temp(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDeactivated_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario sin pack reincidente Disney accede a la pantalla de inicio")
    public void queElUsuarioSinPackReincidenteDisneyAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyDeactivatedRecidivist(),
                Hooks.props.loginPasswordDisneyDeactivatedRecidivist(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDisneyDeactivated_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario sin pack con oferta Disney accede a la pantalla de inicio")
    public void queElUsuarioSinPackConOfertaDisneyAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyDeactivatedOffer(),
                Hooks.props.loginPasswordDisneyDeactivatedOffer(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDisneyDeactivated_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario con pack Disney vinculado accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyVinculadoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyLinked(),
                Hooks.props.loginPasswordDisneyLinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDisneyLinked_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario con pack Disney no vinculado accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyNoVinculadoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyUnlinked(),
                Hooks.props.loginPasswordDisneyUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginPasswordDisneyUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario con pin y compras bloqueadas accede a la pantalla de inicio")
    public void queElUsuarioConPinYComprasBloqueadasAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUserNamePurchasesBlocked(),
                Hooks.props.loginPasswordPurchasesBlocked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUserNamePurchasesBlocked_ProfileName());
        this.commonPageAction.userIsLogged();
        Hooks.props.setProperty("providerUsername", Hooks.props.loginUserNamePurchasesBlocked());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginPasswordPurchasesBlocked());
    }

    @Y("El usuario accede a la sección 'Inicio'")
    public void elUsuarioAccedeALaSeccionInicio() {
        this.homePageAction.clickNavbarHome();
    }

    @Dado("Que el usuario con pack Combo+ linked accede a la pantalla de inicio")
    public void queElUsuarioConPackComboLinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameComboLinked(),
                Hooks.props.loginPasswordComboLinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameComboLinked_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario con pack Combo+ unlinked accede a la pantalla de inicio")
    public void queElUsuarioConPackComboUnlinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameComboUnlinked(),
                Hooks.props.loginPasswordComboUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameComboUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Dado("Que el usuario con pack Disney+ unlinked accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyUnlinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyUnlinked(),
                Hooks.props.loginPasswordDisneyUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDisneyUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack Star+ linked accede a la pantalla de inicio")
    public void queElUsuarioConPackStarLinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameStarLinked(),
                Hooks.props.loginPasswordStarLinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack Star+ unlinked accede a la pantalla de inicio")
    public void queElUsuarioConPackStarUnlinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameStarUnlinked(),
                Hooks.props.loginPasswordStarUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameStarUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();
        //this.commonPageAction.waitPageNotLoading();
    }

    @Cuando("Se dirige al stripe 'Destacados Flow'")
    public void seDirigeAlStripeDestacadosFlow() {
        this.commonPageAction.goToStripe(Hooks.props.destacadosFlow());
    }

    @Entonces("Los posters de colecciones de 'Destacados Flow' se muestran correctamente")
    public void losPostersDeColeccionesDeDestacadosFlowSeMuestranCorrectamente() {
        this.homePageAction.checkStripeDestacadosFlow();
    }

    @Cuando("El usuario accede a un contenido del stripe 'Destacados Flow'")
    public void elUsuarioAccedeAUnContenidoDelStripeDestacadosFlow() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.destacadosFlow());
        this.homePageAction.accessContentStripe(homePageFlow.stripeDestacadosFlow);
    }

    @Cuando("El usuario accede a un contenido del stripe 'Lo más visto en TV'")
    public void elUsuarioAccedeAUnContenidoDelStripeLoMasVisteEnTv() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.homePageMostViewedTV());
        this.homePageAction.accessContentStripe(homePageFlow.homePageMostViewedTV);
    }

    @Entonces("La ficha técnica del contenido del stripe 'Destacados Flow' se muestran correctamente")
    public void laFichaTecnicaDelContenidoDelStripeDestacadosFlowSeMuestranCorrectamente() {
        this.homePageAction.checkDipContent();
    }

    @Entonces("Las categorias del stripe 'Categorias' se muestran correctamente")
    public void lasCategoriasDelStripeMiraEnFlowSeMuestranCorrectamente() {
        this.homePageAction.checkStripeCategorias();
    }

    @Cuando("Se dirige al stripe 'Mirá en Flow' y accede a una categoría")
    public void seDirigeAlStripeMiraEnFlowYAccedeAUnaCategoria() {
        this.commonPageAction.goToStripe(Hooks.props.stripeCategory());
        this.homePageAction.accessToStripeMiraEnFlow();
    }

    @Entonces("Los stripes y el contenido de la categoría seleccionada se muestran correcamente")
    public void losStripesYElContenidoDeLaCategoriaSeleccionadaSeMuestranCorrecamente() {
        this.homePageAction.checkContentStripeMiraEnFlow();
    }

    @Dado("Que el usuario con disponibilidad de grabaciones accede a la pantalla de inicio")
    public void queElUsuarioConDisponibilidadDeGrabacionesAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUserNameAvailableRecord(),
                Hooks.props.loginPasswordAvailableRecord(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUserNameAvailableRecord_ProfileName());
        this.commonPageAction.userIsLogged();
        this.commonPageAction.setLocalStorageValues();
    }

    @Dado("Que el usuario con grabaciones disponibles accede a la pantalla de inicio")
    public void queElUsuarioConGrabacionesDisponiblesAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyLinked(),
                Hooks.props.loginPasswordDisneyLinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameDisneyUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack 'paramount plus' accede a la pantalla de inicio")
    public void queElUsuarioConPackParamountPlusAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(),
                Hooks.props.loginPasswordProfile(),
                true);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
        this.commonPageAction.enableParentalControlIfDisabled();
    }

    @Cuando("El usuario accede a un contenido aleatorio del stripe 'Recomendados'")
    public void elUsuarioAccedeAUnContenidoAleatorioDelStripeRecomendados() {
        this.homePageAction.accessRandomStripeContent();
    }

    @Dado("Que el usuario con pack Disney+ linked accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyLinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyLinked(), Hooks.props.loginPasswordDisneyLinked(),false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack Disney+ y Star+ linked accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyYStarLinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameComboLinked(), Hooks.props.loginPasswordComboLinked(),false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Y("accede a un contenido aleatorio del stripe 'Continuar viendo'")
    public void accedeAUnContenidoAleatorioDelStripeContinuarViendo() {
        this.commonPageAction.goToStripe(Hooks.props.homePageContinueWatching());
        this.homePageAction.accessRandomContentStripeBingeWatching(this.homePageFlow.continueWatching);
    }

    @Dado("Que el usuario con pack Paramount+ activo accede a la pantalla de inicio")
    public void queElUsuarioConPackParamountActivoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameActiveParamount(), Hooks.props.loginPasswordActiveParamount(),false);
        this.profileSelectionPageAction.selectUserProfile(2);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack HBO activo accede a la pantalla de inicio")
    public void queElUsuarioConPackHBOActivoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginPacksHboAndFutbol(), Hooks.props.loginPasswordPacksHboAndFutbol(),false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario sin espacio de grabación accede a la pantalla de inicio")
    public void queElUsuarioSinEspacioDeGrabaciónAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyDeactivatedOffer(),
                Hooks.props.loginPasswordDisneyUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameStarLinked_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Cuando("El usuario visualiza el stripe 'Categorías'")
    public void elUsuarioVisualizaElStripeCategorías() {
        this.commonPageAction.goToStripe(Hooks.props.stripeCategory());
        this.homePageAction.viewToStripeCategorias();
    }

    @Entonces("Las categorias del stripe 'Categorías' se muestran correctamente")
    public void lasCategoriasDelStripeCategoríasSeMuestranCorrectamente() {
        this.homePageAction.checkStripeCategorias();
    }

    @Dado("Que el usuario con mas de un perfil accede a la pantalla de inicio con el nuevo perfil")
    public void queElUsuarioConMasDeUnPerfilAccedeALaPantallaDeInicioConElNuevoPerfil() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameProfile(), Hooks.props.loginPasswordProfile(),false);
        this.profileSelectionPageAction.selectUserProfile(2);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack Fútbol activo accede a la pantalla de inicio")
    public void queElUsuarioConPackFutbolActivoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginPacksHboAndFutbol(), Hooks.props.loginPasswordPacksHboAndFutbol(),false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con pack Disney+ y Star+ unlinked accede a la pantalla de inicio")
    public void queElUsuarioConPackDisneyYStarUnlinkedAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameComboUnlinked(), Hooks.props.loginPasswordComboUnlinked(),false);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Cuando("Se dirige al stripe 'Explorá categorías y géneros'")
    public void seDirigeAlStripeExploraCategoriasYGeneros() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeHomeCategoriasYGeneros());
    }
    @Entonces("Los posters de colecciones de 'Explorá categorías y géneros' se muestran correctamente")
    public void losPostersDeColeccionesDeExploraCategoriasYGenerosSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeHomeCategoriasYGeneros,this.homePageFlow.getSlickActive);
    }

    @Cuando("Accede a un género del stripe 'Genero'")
    public void accedeAUnGeneroDelStripeGenero() {
        this.commonPageAction.goToStripe("Géneros");
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeGeneros);
    }

    @Entonces("Los contenidos del stripe 'Explorá categorías y géneros' se muestran correctamente")
    public void losContenidosDelStripeGeneroSeMuestranCorrectamente() {
        this.homePageAction.checkContentStripe();
    }

    @Cuando("Se dirige al stripe 'Todo HBO'")
    public void seDirigeAlStripeTodoHBO() {
        this.commonPageAction.goToStripe(Hooks.props.stripeHBO());
    }

    @Entonces("Los posters de colecciones de 'Todo HBO' se muestran correctamente")
    public void losPostersDeColeccionesDeTodoHBOSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeTodoHBO,this.homePageFlow.getSlickActive);
    }

    @Cuando("El usuario accede a un contenido del stripe 'Todo HBO'")
    public void elUsuarioAccedeAUnContenidoDelStripeTodoHBO() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeHBO());
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeTodoHBO);
    }

    @Entonces("La ficha técnica del contenido del stripe 'Todo HBO' se muestran correctamente")
    public void laFichaTécnicaDelContenidoDelStripeTodoHBOSeMuestranCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Dado("Que el usuario sin contenido previamente reproducido accede a la pantalla de inicio")
    public void queElUsuarioSinContenidoDisponibleAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginNoChannelsFavorites(),
                Hooks.props.loginPasswordNoChannelsFavorites(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginNoChannelsFavorites_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Entonces("Se visualizan elementos restringidos en la pantalla de inicio")
    public void seVisualizanElementosRestringidosEnLaPantallaDeInicio() {
        this.commonPageAction.getContentRestrictedRandom();
        this.commonPageAction.goToStripe(Hooks.props.homePageContinueWatching());
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.continueWatching,this.homePageFlow.getSlickActive);
    }

    @Y("Se dirige al stripe 'Top 10 Películas'")
    public void seDirigeAlStripeTopPeliculas() {
        this.commonPageAction.goToStripe(Hooks.props.stripeHomeTop10Movies());
    }

    @Entonces("Los posters de 'Top 10 Películas' se muestran correctamente")
    public void losPostersDeTopPelículasSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeTopMovies,this.homePageFlow.getSlickActive);
    }

    @Y("Se dirige al stripe 'Top 10 Series'")
    public void seDirigeAlStripeTopSeries() {
        this.homePageAction.waitForStripes();
        this.homePageAction.goToStripeTopSeries();
    }

    @Entonces("Los posters de 'Top 10 Series' se muestran correctamente")
    public void losPostersDeTopSeriesSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeTopSeries,this.homePageFlow.getSlickActive);
    }

    @Dado("Que el usuario sin pack 'TV en vivo' accede a la pantalla de inicio")
    public void queElUsuarioSinPackTVEnVivoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUserFlex(),
                Hooks.props.loginPasswordFlex(),
                false);
        this.profileSelectionPageAction.selectUserProfile(2);
        this.commonPageAction.userIsLogged();
    }

    @Cuando("Accede a un contenido del stripe 'Top 10 Series'")
    public void accedeAUnContenidoDelStripeTopSeries() {
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeTopSeries);
    }

    @Dado("Que el usuario con stripe de contenidos accede a la pantalla de inicio")
    public void queElUsuarioConStripeDeContenidosAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameStarUnlinked(),
                Hooks.props.loginPasswordStarUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameStarUnlinked_ProfileName());
        this.commonPageAction.userIsLogged();

        Hooks.props.setProperty("providerUsername", Hooks.props.loginUsernameStarUnlinked());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginPasswordStarUnlinked());
    }

    @Y("Se dirige al stripe 'Mis Canales'")
    public void seDirigeAlStripeMisCanales()  {
        this.homePageAction.viewToStripe(this.homePageFlow.stripeMisCanales);
    }

    @Entonces("Los posters de 'Mis Canales' se muestran correctamente")
    public void losPostersDeMisCanalesSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeMisCanales,this.homePageFlow.getSlickActive);
    }

    @Cuando("Accede al stripe desde 'Ver Mas'")
    public void accedeAlStripeDesdeVerMas() {
        this.commonPageAction.clickStripeContentSeeMore(this.homePageFlow.stripeMisCanales);
    }

    @Y("Se dirige al stripe 'Mis Películas y Series'")
    public void seDirigeAlStripeMisPeliculasYSeries() {
        this.homePageAction.viewToStripe(this.homePageFlow.stripeMisCanales);
    }

    @Entonces("Los posters de 'Mis Películas y Series' se muestran correctamente")
    public void losPostersDeMisPeliculasYSeriesSeMuestranCorrectamente()  {
        this.homePageAction.checkPosterStripeMisPeliculasYSeries();
    }
    @Cuando("Accede al stripe desde 'Mis Películas y Series'")
    public void accedeAlStripeDesdeMisPeliculasYSeries() {
        this.homePageAction.accessStripeMisPeliculasYSeries();
    }

    @Entonces("La sección 'Mis Películas y Series' se muestra correctamente")
    public void laSeccionMisPeliculasYSeriesSeMuestraCorrectamente() {
        this.myContentFavoritesPageAction.pageIsDisplayed(false);
    }

    @Cuando("Se dirige al stripe 'Series Originales Flow'")
    public void seDirigeAlStripeSeriesOriginalesFlow() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeSeriesOriginalsDeFlow());
    }

    @Entonces("Los posters de colecciones de 'Series Originales Flow' se muestran correctamente")
    public void losPostersDeColeccionesDeSeriesOriginalesFlowSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeSerieOriginalsFlow,
                this.homePageFlow.getSlickActive);
    }

    @Cuando("El usuario accede a un contenido del stripe 'Series Originales Flow'")
    public void elUsuarioAccedeAUnContenidoDelStripeSeriesOriginalesFlow() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeSeriesOriginalsDeFlow());
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeSerieOriginalsFlow);
    }

    @Entonces("La ficha técnica del contenido del stripe 'Series Originales Flow' se muestran correctamente")
    public void laFichaTecnicaDelContenidoDelStripeSeriesOriginalesFlowSeMuestranCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("Es posible navegar horizontalmente el stripe 'Top 10 Series'")
    public void esPosibleNavegarHorizontalmenteElStripeTopSeries() {
        this.commonPageAction.navToStripe(Hooks.props.stripeHomeTop10Serie());
    }

    @Cuando("Se dirige al stripe 'Todo Paramount+'")
    public void seDirigeAlStripeTodoParamount() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeHomeTodoParamount());
    }

    @Entonces("Los posters de colecciones de 'Todo Paramount+' se muestran correctamente")
    public void losPostersDeColeccionesDeTodoParamountSeMuestranCorrectamente() {
        this.homePageAction.postersAreShownCorrectly(this.homePageFlow.stripeHomeTodoParamount,this.homePageFlow.getSlickActive);
    }

    @Cuando("El usuario accede a un contenido del stripe 'Todo Paramount+'")
    public void elUsuarioAccedeAUnContenidoDelStripeTodoParamount() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeHomeTodoParamount());
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeHomeTodoParamount);
    }

    @Entonces("Es posible navegar horizontalmente el stripe 'Top 10 Películas'")
    public void esPosibleNavegarHorizontalmenteElStripeTopPeliculas() {
        this.commonPageAction.navToStripe(Hooks.props.stripeHomeTop10Movies());
    }

    @Dado("Que el usuario con límite de grabación alcanzado accede a la pantalla de inicio")
    public void queElUsuarioConLimiteDeGrabacionAlcanzadoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyDeactivatedOffer(),  //usuario 1 // limite de grabaciones
                Hooks.props.loginPasswordDisneyUnlinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameStarLinked_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario flex sin packs accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPacksAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(), Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
        this.commonPageAction.userIsLogged();
    }

    @Cuando("Accede a un género del stripe 'Explorá categorías y géneros'")
    public void accedeAUnGéneroDelStripeExploráCategoríasYGéneros() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeHomeCategoriasYGeneros());
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeHomeCategoriasYGeneros);
    }

    @Dado("Que el usuario flex sin pack 'Fútbol' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackFútbolAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(), Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario flex sin pack 'TV en vivo' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackTVEnVivoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(), Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario flex sin pack 'Paramount+' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackParamountAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(), Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario flex sin pack 'HBO' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackHBOAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(), Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
        this.commonPageAction.userIsLogged();
    }

    @Cuando("El usuario accede a un contenido aleatorio del stripe 'Colecciones'")
    public void elUsuarioAccedeAUnContenidoAleatorioDelStripeColeccionesImperdibles() {
        this.homePageAction.waitForStripes();
        this.webDriver.scrollToELementPageDownAwaitility(this.homePageFlow.stripeColeccionesImperdibles);
        this.homePageAction.accessContentStripeCollections(this.homePageFlow.stripeColeccionesImperdibles);
    }

    @Entonces("El 'GOP' del tipo de contenido seleccionado se visualiza correctamente")
    public void elGOPDelTipoDeContenidoSeleccionadoSeVisualizaCorrectamente() {
        this.gridOfPostersActions.gopIsDisplayed();
    }

    @Dado("Que el usuario con canales favoritos accede a la pantalla de inicio")
    public void queElUsuarioConContenidosAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUserWithContents(),
                Hooks.props.loginPasswordWithContents(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameWithContents_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario sin canales favoritos accede a la pantalla de inicio")
    public void queElUsuarioSinContenidosAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginNoChannelsFavorites(),
                Hooks.props.loginPasswordNoChannelsFavorites(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginNoChannelsFavorites_ProfileName());
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario con control parental sin configurar accede a la pantalla de inicio")
    public void queElUsuarioConControlParentalSinConfigurarAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.props.loginPassword(),
                true);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Dado("Que el usuario sin control parental configurado accede a la pantalla de inicio")
    public void queElUsuarioSinControlParentalConfiguradoAccedeALaPantallaDeInicio() {
        this.queElUsuarioAccedeALaPantallaDeInicio();
    }

    @Cuando("El usuario accede al 'GOP' del stripe 'Todos los titulos'")
    public void elUsuarioAccedeAlGOPDelStripeTodosLosTitulos() {
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllTitles());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllTitles());
    }

    @Dado("Que el usuario con packs accede a la pantalla de inicio")
    public void queElUsuarioConPacksAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameDisneyLinked(),
                Hooks.props.loginPasswordDisneyLinked(),
                true);
        this.profileSelectionPageAction.selectUserProfile(0);
        this.commonPageAction.userIsLogged();
    }

    @Entonces("Se muestra nuevamente la pantalla de inicio")
    public void seMuestraNuevamenteLaPantallaDeInicio() {
        this.homePageAction.pageIsDisplayed();
    }

    @Dado("Que el usuario con contenido Adulto accede a la pantalla de inicio")
    public void queElUsuarioConContenidoAdultoAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameAdult(),
                Hooks.props.loginPasswordAdult(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameAdultProfileName());
        this.commonPageAction.userIsLogged();
        Hooks.props.setProperty("providerUsername", Hooks.props.loginUsernameAdult());
        Hooks.props.setProperty("providerPassword", Hooks.props.loginPasswordAdult());
    }

    @Dado("Que el usuario flex sin pack 'Star+' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackStarAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(),
                Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
    }

    @Dado("Que el usuario flex sin pack 'Disney+ y Star+' accede a la pantalla de inicio")
    public void queElUsuarioFlexSinPackDisneyYStarAccedeALaPantallaDeInicio() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameFlex(),
                Hooks.props.loginPasswordFlexI(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsarNameFlexProfile());
    }

    public static String titleContentAddedMyList;

    @Y("El usuario se dirige al stripe 'Mis Peliculas y Series' de la Home")
    public void elUsuarioSeDirigeAlStripeMisPeliculasYSeriesDeLaHome() {
        titleContentAddedMyList = this.homePageFlow.titleContent.getText();
        this.commonPageAction.accessNavbarHome();
        this.homePageAction.waitForStripes();
        this.homePageAction.viewToStripe(this.homePageFlow.stripeMyMoviesAndSeries);
    }

    @Entonces("Se visualiza el contenido agregado en la primer posición del stripe")
    public void seVisualizaElContenidoAgregadoEnLaPrimerPosicionDelStripe() {
        this.homePageAction.checkContentAddedMyList(titleContentAddedMyList);
    }
}
