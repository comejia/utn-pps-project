package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.*;
import flow.pageObjects.HomePageFlow;
import flow.pageObjects.OnboardingPageFlow;
import flow.pageObjects.VodDetailInfoPageFlow;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.time.Duration;

public class CommonDefinitions {

    protected ExtendedWebDriver webDriver;
    protected CommonPageAction commonPageAction;
    protected LoginPageAction loginPageAction;
    protected SearchPageAction searchPageAction;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected VodDetailInfoPageFlow vodDetailInfoPageFlow;
    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected OnboardingPageFlow onboardingPageFlow;
    protected TvGuidePageAction tvGuidePageAction;
    protected PlayerPageAction playerPageAction;
    protected PreLoginPageAction preLoginPageAction;
    protected HomePageAction homePageAction;
    protected HomePageFlow homePageFlow;
    protected OTPSendCodeAction otpSendCodeAction;

    public CommonDefinitions() {
        this.otpSendCodeAction = new OTPSendCodeAction(Hooks.getDriver());
        this.webDriver = new ExtendedWebDriver(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.searchPageAction = new SearchPageAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.onboardingPageFlow = new OnboardingPageFlow(Hooks.getDriver());
        this.tvGuidePageAction = new TvGuidePageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.preLoginPageAction = new PreLoginPageAction(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
        this.homePageFlow = new HomePageFlow(Hooks.getDriver());
        this.vodDetailInfoPageFlow = new VodDetailInfoPageFlow(Hooks.getDriver());
    }

    @Dado("Que el usuario accede a la pantalla de login")
    public void queElUsuarioAccedeALaPantallaDeLogin() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
    }

        @Entonces("El usuario es redireccionado a la pantalla de registrar usuario")
    public void elUsuarioEsRedireccionadoALaPantallaDeRegistrarUsuario() {
        webDriver.checkUrlRedirection(Hooks.props.pageTitleRegister(),
                Hooks.props.pageUrlRegister());
    }

    @Entonces("El usuario es redireccionado a la pantalla de ingresar codigo")
    public void elUsuarioEsRedireccionadoALaPantallaDeIngresarCodigo() {
        webDriver.checkUrlRedirection(Hooks.props.pageTitleIhaveCode(),
                Hooks.props.pageUrlIhaveCode());
    }

    @Entonces("El usuario es redireccionado a la pantalla de recuperación de usuario")
    public void elUsuarioEsRedireccionadoALaPantallaDeRecuperacionDeUsuario() {
        webDriver.checkUrlRedirection(Hooks.props.pageTitleRecoveryUsername(),
                Hooks.props.pageUrlRecoveryUsername());
    }

    @Entonces("El usuario es redireccionado a la pantalla de recuperación de contraseña")
    public void elUsuarioEsRedireccionadoALaPantallaDeRecuperacionDeContrasena() {
        webDriver.checkUrlRedirection(Hooks.props.pageTitleRecoveryPassword(),
                Hooks.props.pageUrlRecoveryPassword());
    }

    @Entonces("El usuario es redireccionado a la pantalla de ayuda")
    public void elUsuarioEsRedireccionadoALaPantallaDeAyuda() {
        this.commonPageAction.checkTitle();
    }

    @Cuando("El usuario realiza logout de la aplicación")
    public void elUsuarioRealizaLogoutDeLaAplicacion() {
        this.commonPageAction.logoutUser();
    }

    @Cuando("El usuario accede a la sección 'Series'")
    public void elUsuarioAccedeALaSeccionSeries() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementSeries();
    }

    @Cuando("El usuario accede a la sección 'Películas'")
    public void elUsuarioAccedeALaSeccionPeliculas() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementMovies();
    }

    @Cuando("El usuario accede a realizar una búsqueda")
    public void elUsuarioAccedeARealizarUnaBusqueda() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.searchPageAction.clickButtonSearchWrapper();
    }

    @Y("El usuario accede a un contenido restringido del resultado de la búsqueda")
    public void elUsuarioAccedeAUnContenidoRestringidoDelResultadoDeLaBusqueda() {
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas emitidos");
        this.commonPageAction.enterParentalControlPinAndConfirm(Hooks.props.pinParental());
//        this.commonPageAction.playContentFromDIP();
//        this.playerPageAction.waitPlayerLoadAndPlayback();
//        this.webDriver.navigateBack();
    }

    @Cuando("El usuario accede al menú")
    public void elUsuarioAccedeAlMenu() {
        this.commonPageAction.clickDropdownUser();
    }

    @Entonces("Se muestran los elementos del menú")
    public void seMuestranLosElementosDelMenu() {
        this.commonPageAction.checkMenuItems();
        this.commonPageAction.navbarContentsIsDisplayed(
                true,
                true,
                true,
                true,
                true,
                true,
                true);
    }

    @Entonces("Es posible navegar horizontalmente el SuperHero")
    public void esPosibleNavegarHorizontalmenteElSuperHero() {
        this.commonPageAction.checkSuperheroNavigation();
    }

    @Cuando("El usuario accede a la pantalla de selección de perfiles")
    public void elUsuarioAccedeALaPantallaDeSeleccionDePerfiles() {
        this.commonPageAction.accessChangeProfilePage();
    }

    @Dado("Que el usuario accede a un link de un contenido inexistente")
    public void queElUsuarioAccedeAUnLinkDeUnContenidoInexistente() {
        // Open Url
        webDriver.openVodUrl(Hooks.props.baseUrl(), "vod", "11111111");
        // Set Browser local Storage vCasId parameter
        webDriver.setBrowserLocalStorageAll(Hooks.props.authClientCasId());
        webDriver.openVodUrl(Hooks.props.baseUrl(), "vod", "11111111");
    }

    @Dado("Que el usuario accede a un link de un contenido existente")
    public void queElUsuarioAccedeAUnLinkDeUnContenidoExistente() {
        //Set Content
        var content = Hooks.dataProvider.getSeriesFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        Hooks.contentData.put("type", content.getType());
        Hooks.contentData.put("id", content.getId());
        // Open Url
        webDriver.openVodUrl(Hooks.props.baseUrl(), Hooks.contentData.get("type"), Hooks.contentData.get("id"));
        // Set Browser local Storage vCasId parameter
        webDriver.setBrowserLocalStorageAll(Hooks.props.authClientCasId());
    }

    @Y("El usuario vuelve a la ficha tecnica")
    public void elUsuarioVuelveALaFichaTecnica() {
        Awaitility.await().atMost(Duration.ofSeconds(20)).until(() -> {
            try {
                this.vodDetailInfoPageAction.clickBackButton();
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }

        });
    }

    @Y("El usuario vuelve atras a la ficha tecnica")
    public void elUsuarioVuelveAtrasALaFichaTecnica(){
        this.tvGuidePageAction.clickOnWatchTvGuide();
    }

    @Cuando("El usuario accede a la sección 'Mi Lista'")
    public void elUsuarioAccedeALaSeccionFavoritos() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
    }

    @Cuando("Que el usuario accede a la sección 'Guía de Tv'")
    public void queElUsuarioAccedeALaSeccionGuiaDeTv() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.props.loginPassword(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
        this.commonPageAction.userIsLogged();
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.closeEpgWithBackButton();
    }

    @Cuando("El usuario accede a la sección 'Alquileres'")
    public void elUsuarioAccedeALaSeccionAlquileres() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementRentedItems();
    }

    @Y("El usuario accede a la sección 'Kids'")
    public void elUsuarioAccedeALaSeccionKids() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementKids();
    }

    @Cuando("El usuario accede a la sección 'Grabaciones'")
    public void elUsuarioAccedeALaSeccionGrabaciones() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentRecordings();
    }

    @Cuando("El usuario accede a la sección 'Continuar viendo'")
    public void elUsuarioAccedeALaSeccionContinuarViendo() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentContinueWatching();
    }

    @Cuando("El usuario reproduce un contenido directamente desde el SuperHero")
    public void elUsuarioReproduceUnContenidoDesdeElSuperHero() {
        this.commonPageAction.clickActiveSuperHeroButtonPlay();
    }

    @Dado("Que el usuario abre la aplicación")
    public void queElUsuarioAbreLaAplicacion() {
        //TODO
    }

    @Dado("Que el usuario reproduce un programa en 'Vivo' de la 'Guía de programación'")
    public void queElUsuarioReproduceUnProgramaEnVivoDeLaGuiaDeProgramacion() throws AWTException {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.props.loginPassword(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
        this.commonPageAction.userIsLogged();
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
        this.playerPageAction.actionProgramRestart();
        this.playerPageAction.playerIsDisplayed();
    }

    @Dado("Que el usuario accede un programa con estadisticas deportivas")
    public void queElUsuarioAccedeUnProgramaConEstadisticasDeportivas() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
        this.preLoginPageAction.accessLoginPage();
        this.loginPageAction.loginUser(Hooks.props.loginUsername(),
                Hooks.props.loginPassword(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsername_ProfileName1());
        this.commonPageAction.userIsLogged();
        String searchTitle = "fox sports";

        this.commonPageAction.searchContent(searchTitle);
        this.searchPageAction.selectChannelElement(1);
        this.playerPageAction.waitPlayerLoad();
    }

    @Cuando("El usuario navega horizontalmente un stripe de izquierda a derecha")
    public void elUsuarioNavegaHorizontalmenteUnStripeDeIzquierdaADerecha() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.stripeRecommended());
    }

    @Entonces("Se muestran los demás contenidos del stripe")
    public void seMuestranLosDemasContenidosDelStripe() {
        this.homePageAction.navegationForStripe(homePageFlow.stripeRecomendadosParaVos);
    }

    @Cuando("El usuario abre y cierra una nueva pestaña del navegador")
    public void elUsuarioAbreYCierraUnaNuevaPestanaDelNavegador() {
        this.webDriver.createNewTab();
        this.playerPageAction.isPlaying();
        this.webDriver.switchToNewTab(1);
        this.webDriver.closeCurrentTab();
        this.webDriver.switchToNewTab(0);
    }

    @Cuando("El usuario accede a un contenido aleatorio de un stripe")
    public void elUsuarioAccedeAUnContenidoAleatorioDeUnStripe() {
        //TODO Make it better
        this.commonPageAction.scrollToContentStripe(Hooks.props.moviesPageAllTitles());
        WebElement stripe = this.commonPageAction.getContentStripe(Hooks.props.moviesPageAllTitles());
        //this.commonPageAction.accessDipForRandomItemFromContentStripe(stripe);
        this.commonPageAction.accessContentStripe(Hooks.props.moviesPageAllTitles());
    }

    @Cuando("El usuario accede a la funcion 'Ver todo' de un 'stripe'")
    public void elUsuarioAccedeALaFuncionVerTodoDeUnStripe() {
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllTitles());
    }

    @Y("El usuario ingresa el PIN de desbloqueo de control parental correctamente")
    public void elUsuarioIngresaElPINDeDesbloqueoDeControlParentalCorrectamente() {
        this.commonPageAction.enterParentalControlPinAndConfirm(Hooks.props.pinParental());
    }

    @Y("El usuario accede a la sección 'Guía de Tv'")
    public void elUsuarioAccedeALaSeccionGuiaDeTv() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementTvGuide();
    }

    @Dado("Que el usuario accede a la pantalla de 'Pre Login'")
    public void queElUsuarioAccedeALaPantallaDePreLogin() {
        this.commonPageAction.loadPage(Hooks.props.baseUrl(), Hooks.props.authClientCasId());
    }
    @Entonces("El usuario es redireccionado a la pantalla de login")
    public void elUsuarioEsRedireccionadoALaPantallaDeLogin() {
        this.loginPageAction.pageIsDisplayed();
    }

    @Cuando("El usuario accede a 'Continuar viendo' en 'Mis Contenidos'")
    public void elUsuarioAccedeAContinuarViendoEnMisContenidos() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentContinueWatching();
    }

    @Dado("Que el usuario restringido accede a un link de un contenido existente")
    public void queElUsuarioRestringidoAccedeAUnLinkDeUnContenidoExistente() {
        //Set Content
        var content = Hooks.dataProvider.getSeriesFreeRestricted(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        Hooks.contentData.put("type", content.getType());
        Hooks.contentData.put("id", content.getId());
        // Open Url
        webDriver.openVodUrl(Hooks.props.baseUrl(), Hooks.contentData.get("type"), Hooks.contentData.get("id"));
        // Set Browser local Storage vCasId parameter
        webDriver.setBrowserLocalStorageAll(Hooks.props.authClientCasId());

    }

    @Y("Se dirige a otra seccion")
    public void seDirigeAOtraSeccion() {
        this.commonPageAction.accessNavbarElementMovies();
    }


    @Y("El usuario vuelve a la pantalla anterior a la ficha técnica")
    public void elUsuarioVuelveALaPantallaAnteriorALaFichaTecnica() {
        this.vodDetailInfoPageAction.clickBackButton();
    }

    @Y("Se dirige a sección 'Mi Lista'")
    public void seDirigeASecciónMiLista() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
    }

    @Y("El usuario vuelve a la pantalla anterior")
    public void elUsuarioVuelveAtras() {
        this.homePageAction.waitForStripes();
        this.webDriver.navigateBack();
    }

    @Cuando("El usuario accede a la sección 'Peliculas'")
    public void usuarioAccedeALaSeccionPeliculas() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementMovies();
    }

    @Entonces("No se muestra el contenido seleccionado en la sección 'Mi lista'")
    public void noSeMuestraElContenidoSeleccionadoEnLaSeccionMiLista() {
        String titleContent = this.vodDetailInfoPageFlow.title.getText();
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
        this.commonPageAction.checkContentNotExists(titleContent);
    }
}
