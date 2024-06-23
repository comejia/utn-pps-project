package flow.stepDefinitions;

import flow.core.PageFactory;
import flow.core.countries.PageTypes;
import flow.pageActions.*;
import flow.pageObjects.HomePageFlow;
import flow.pageObjects.PlayerFlow;
import flow.pageObjects.VodDetailInfoPageFlow;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.awaitility.Awaitility;

import java.awt.*;
import java.time.Duration;
import java.util.logging.Logger;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class VodDetailInfoPageDefinitions {

    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected CommonPageAction commonPageAction;
    protected SearchPageAction searchPageAction;
    protected PlayerPageAction playerPageAction;
    protected RentalPageAction rentPageAction;
    protected LoginPageAction loginPageAction;
    protected ProfileSelectionPageAction profileSelectionPageAction;
    protected VodDetailInfoPageFlow vodDetailInfoPageFlow;
    protected HomePageAction homePageAction;
    protected HomePageFlow homePageFlow;
    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    protected PlayerFlow playerFlow;
    protected TvGuidePageAction tvGuidePageAction;
    protected ContentPageAction contentPageAction;

    public VodDetailInfoPageDefinitions() {
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.searchPageAction = new SearchPageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.rentPageAction = new RentalPageAction(Hooks.getDriver());
        this.profileSelectionPageAction = new ProfileSelectionPageAction(Hooks.getDriver());
        this.loginPageAction = new LoginPageAction(PageFactory.build(PageTypes.LoginPage));
        this.vodDetailInfoPageFlow = new VodDetailInfoPageFlow(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
        this.homePageFlow = new HomePageFlow(Hooks.getDriver());
        this.playerFlow = new PlayerFlow(Hooks.getDriver());
        this.tvGuidePageAction = new TvGuidePageAction(Hooks.getDriver());
        this.contentPageAction = new ContentPageAction(Hooks.getDriver());
    }

    @Entonces("Se muestra la ficha técnica del contenido 'VOD' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoVODSeleccionado() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Alquiler' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAlquilerATravesDeUnaBusqueda() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas emitidos");
    }

    @Cuando("El usuario accede a un contenido del tipo 'Alquiler'")
    public void elUsuarioAccedeAUnContenidoDelTipeAlquiler() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Cuando("El usuario con pin de compra accede a la ficha técnica de un contenido del tipo 'Alquiler'")
    public void elUsuarioPinAccedeALaFichaTecnicaDeUnContenidoDelTipoAlquiler() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Cuando("El Usuario accede a un contenido catchup de la 'Guía de programacion'")
    public void elUsuarioAccedeAUnContenidoCatchupDeLaGuiaDeProgramacion() {
        var content = Hooks.dataProvider.getCatchupTv(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openCatchUpUrl(content.getTitle(), content.getId());
        this.playerPageAction.clickOnPlayButton();
    }

    @Cuando("El usuario hace la búsqueda de un contenido catchup")
    public void elUsuarioHaceLaBusquedaDeUnContenidoCatchup() {
        var content = Hooks.dataProvider.getCatchupTv(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openCatchUpUrl(content.getTitle(), content.getId());
        this.playerPageAction.clickOnPlayButton();
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Alquiler' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoAlquilerSeMuestraCorrectamente() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.pageIsDisplayedContentMovies(String.valueOf(content.getPrice()), false);
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película gratuita'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaGratuita() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesIsNotFavorite(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        Hooks.props.setProperty("titleContentIsNotFavorite", content.getTitle());
        Hooks.props.setProperty("typeContentMovieFreeAllAge", content.getType());
        Hooks.props.setProperty("idContentMovieFreeAllAge", content.getId());
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película gratuita' sin reproducir")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaGratuitaSinReproducir() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesWithoutBookmark(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        Hooks.props.setProperty("titleContentIsNotFavorite", content.getTitle());
        Hooks.props.setProperty("typeContentMovieFreeAllAge", content.getType());
        Hooks.props.setProperty("idContentMovieFreeAllAge", content.getId());
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película gratuita' casi finalizado")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaGratuitaCasiFinalizado() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesBingeWatching(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Series' casi finalizado")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoSeriesGratuitaCasiFinalizado() {
        var content = Hooks.dataProvider.getSeriesFreeAllAgesBingeWatching(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Película gratuita' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoPeliculaGratuitaSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Película gratuita' con pin se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoPeliculaGratuitaConPinSeMuestraCorrectamente() {
        this.commonPageAction.enterParentalControlPinAndConfirm(Hooks.props.pinParental());
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Película gratuita' sin pin se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoSinPinPeliculaGratuitaSeMuestraCorrectamente() {
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.pageIsDisplayedContentMovies(String.valueOf(content.getPrice()), false);
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Series'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoSeries() {
        var content = Hooks.dataProvider.getSeriesFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Y("El usuario accede a un contenido del tipo 'Series' con dos temporadas")
    public void elUsuarioAccedeAUnContenidoDelTipoSeriesConDosTemporadas() {
        this.vodDetailInfoPageAction.openVodUrlWithOneMoreSeasons();
    }

    @Y("El usuario accede a un contenido del tipo 'Series'")
    public void elUsuarioAccedeAUnContenidoDelTipoSeries() {
        this.vodDetailInfoPageAction.openVodUrlWithOneMoreSeasons();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Ya alquilado'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoYaAlquilado() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesPurchased(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Series' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoSeriesSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentSeries();
    }

    @Y("El usuario accede al nombre del Director del contenido")
    public void elUsuarioAccedeAlNombreDelDirectorDelContenido() throws AWTException {
        Hooks.contentData.put("directorName", this.vodDetailInfoPageAction.getDirectorName());
        this.vodDetailInfoPageAction.clickDirectorButton();
    }

    @Y("El usuario accede al nombre del Elenco del contenido")
    public void elUsuarioAccedeAlNombreDelElencoDelContenido() throws AWTException {
        Hooks.contentData.put("castName", this.vodDetailInfoPageAction.getCastName());
        this.vodDetailInfoPageAction.clickActorButton(0);
    }

    @Entonces("Se muestra mensaje de error indicando que el contenido es inexistente")
    public void seMuestraMensajeDeErrorIndicandoQueElContenidoEsInexistente() {
        this.vodDetailInfoPageAction.checkNoContentMessage();
    }

    @Y("El usuario cancela el alquiler del contenido")
    public void elUsuarioCancelaElAlquilerDelContenido() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.clickRentButton();
        this.vodDetailInfoPageAction.modalIsDisplayed(String.valueOf(content.getPrice()));
        this.vodDetailInfoPageAction.clickModalCancelButton();
    }

    @Entonces("No se realiza la compra ni reproducción del contenido")
    public void noSeRealizaLaCompraNiReproduccionDelContenido() throws InterruptedException {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.pageIsDisplayedContentMovies(String.valueOf(content.getPrice()), false);
        //TODO Check that the content was not buyed
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'TV'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoTV() {
        var content = Hooks.dataProvider.getTvFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'TV' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoTVSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.channelDipIsDisplayed();
    }

    @Cuando("El usuario agrega el contenido a Favoritos")
    public void elUsuarioAgregaElContenidoAFavoritos() {
        this.vodDetailInfoPageAction.addFavoriteContent();
        this.vodDetailInfoPageAction.checkContentIsFavorite();
    }

    @Y("El usuario accede a la ficha técnica de un contenido agregado a favoritos")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoAgregadoAFavoritos() {
        this.vodDetailInfoPageAction.openVodUrlFavoriteContent();
    }

    @Cuando("El usuario quita el contenido de Favoritos")
    public void elUsuarioQuitaElContenidoDeFavoritos() {
        this.vodDetailInfoPageAction.removeFavoriteContent();
        this.vodDetailInfoPageAction.checkContentIsNotFavorite();
    }

    @Y("El usuario confirma el alquiler del contenido")
    public void elUsuarioConfirmaElAlquilerDelContenido() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        Awaitility.await().atMost(Duration.ofSeconds(60)).until(() -> {
            try {
                this.vodDetailInfoPageAction.clickRentButton();
                this.vodDetailInfoPageAction.modalIsDisplayed(String.valueOf(content.getPrice()));
                this.vodDetailInfoPageAction.clickModalConfirmButton();
                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                this.commonPageAction.accessNavbarElementRentedItems();
                this.rentPageAction.accessPopularFlowStripe();
                this.rentPageAction.clickItemStripePopularFlowMovie();
                return false;
            }
        });
        this.playerPageAction.playerIsDisplayed();
    }

    @Y("El usuario comienza la reproducción del contenido")
    public void elUsuarioComienzaLaReproduccionDelContenido() {
        this.vodDetailInfoPageAction.clickPlayButton();
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario comienza la reproducción del contenido alquilado")
    public void elUsuarioComienzaLaReproduccionDelContenidoAlquilado() {
        this.vodDetailInfoPageAction.clickOnPlayBtn();
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.basicCheckPlayerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario finaliza la reproduccion del contenido")
    public void elUsuarioFinalizaLaReproduccionDelContenido() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
        this.vodDetailInfoPageAction.clickOnStartPlayer();
    }

    @Y("El usuario finaliza la reproduccion")
    public void elUsuarioFinalizaLaReproduccion() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
        //this.vodDetailInfoPageAction.clickOnStartPlayer();
    }

    @Y("El usuario comienza la reproducción del contenido de series")
    public void elUsuarioComienzaLaReproduccionDelContenidoDeSeries() {
        this.vodDetailInfoPageAction.clickEpisodesButton();
        this.vodDetailInfoPageAction.selectRandomEpisode();
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película gratuita restringida'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaGratuitaRestringida() {
        var content = Hooks.dataProvider.getMovieFreeRestricted(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Película gratuita restringida' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoPeliculaGratuitaRestringidaSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("Se muestra la ficha técnica del contenido")
    public void seMuestraLaFichaTecnicaDelContenido() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("Se muestra la ficha técnica del contenido seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoSeleccionado() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("El contenido se muestra como alquilado y disponible para reproducir")
    public void elContenidoSeMuestraComoAlquiladoYDisponibleParaReproducir() {
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.clickBackButton();
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
        this.vodDetailInfoPageAction.rentedElementsAreDisplayed();
    }

    @Y("El usuario reproduce un contenido 'Película gratuita'")
    public void elUsuarioReproduceUnContenidoPeliculaGratuita() {
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.vodDetailInfoPageAction.clickPlayButtonVodDetailInfo();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario reproduce un contenido 'Series gratuita'")
    public void elUsuarioReproduceUnContenidoSeriesGratuita() {
        var content = Hooks.dataProvider.getSeriesFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.vodDetailInfoPageAction.clickPlayButtonVodDetailInfo();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario reproduce un contenido con 'Skip Intro'")
    public void elUsuarioReproduceUnContenidoConSkipIntro() {
        this.vodDetailInfoPageAction.openVodUrlSkipIntro();
        this.vodDetailInfoPageAction.selectRandomEpisode();
        this.playerPageAction.waitPlayerNotLoading();
        this.playerPageAction.playerSetTimeStart();
    }

    @Y("El usuario reproduce un contenido con 'Skip Resume'")
    public void elUsuarioReproduceUnContenidoConSkipResume() {
        this.vodDetailInfoPageAction.openVodUrlSkipResume();
        this.playerPageAction.clickOnPlayButton();
        this.playerPageAction.playerSetTimeStart();
    }

    @Y("El usuario accede a un contenido del tipo 'Película gratuita' con subtitulos")
    @Y("El usuario comienza la reproducción de un contenido con subtitulos")
    public void elUsuarioAccedeAUnContenidoDelTipoPeliculaGratuitaConSubtitulos() {
        this.vodDetailInfoPageAction.openMovieFreeUrlWithSubtitles();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnPlayButton();
        this.playerPageAction.waitPlayerNotLoading();
        this.playerPageAction.playerSetTimeStart();
    }

    @Y("El usuario accede a un contenido del tipo 'Película gratuita' sin subtitulos")
    @Y("El usuario comienza la reproducción de un contenido sin subtitulos")
    public void elUsuarioAccedeAUnContenidoDelTipoPeliculaGratuitaSinSubtitulos() {
        this.vodDetailInfoPageAction.openMovieFreeUrlWithoutSubtitles();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnPlayButton();
        this.playerPageAction.waitPlayerNotLoading();
        this.playerPageAction.playerSetTimeStart();
    }

    @Y("El usuario selecciona la opcion de 'Skip Intro' sin activarla")
    public void elUsuarioSeleccionaLaOpcionDeSkipIntroSinActivarla() {
        this.playerPageAction.mouseOverSkipIntro();
    }

    @Y("El usuario selecciona la opcion de 'Skip Resume' sin activarla")
    public void elUsuarioSeleccionaLaOpcionDeSkipResumeSinActivarla() {
        this.playerPageAction.mouseOverSkipResume();
    }

    @Entonces("Se visualiza la opcion de skip intro seleccionada")
    public void seVisualizaLaOpcionDeSkipIntroSeleccionada() {
        this.playerPageAction.skipIntroButtonIsDisplayed();
    }

    @Entonces("Se visualiza la opcion de skip Resume seleccionada")
    public void seVisualizaLaOpcionDeSkipResumeSeleccionada() {
        this.playerPageAction.skipResumeButtonIsDisplayed();
    }

    @Entonces("Se visualiza la opcion de skip Resume correctamente")
    public void seVisualizaLaOpcionDeSkipResumeCorrectamente() {
        this.playerPageAction.skipResumeButtonIsDisplayed();
    }

    @Y("El usuario confirma el alquiler del contenido con pin")
    public void elUsuarioConfirmaElAlquilerDelContenidoConPin() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.clickRentButton();
        this.vodDetailInfoPageAction.modalIsDisplayed(String.valueOf(content.getPrice()));
        this.vodDetailInfoPageAction.setPinAndConfirmPurhasesBlocked();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Alquileres'")
    public void elUsuarioAccedeAUnContenidoDeTipoAlquileres() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Cuando("El usuario reproduce el 'Trailer'")
    public void elUsuarioReproduceElTrailer() {
        this.vodDetailInfoPageAction.clickOnTrailerButton();
        this.playerPageAction.clickOnPlayButton();
    }

    @Y("El usuario reproduce el contenido desde el reproductor")
    public void elUsuarioReproduceElContenidoDesdeElReproductor() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnPlayButton();
    }

    @Y("El usuario reproduce el contenido 'Guia de Tv' desde el reproductor")
    public void elUsuarioReproduceElContenidoGuiaDeTvDesdeElReproductor() {
        this.playerPageAction.mouseOverPlayer();
        this.tvGuidePageAction.clickOnPlayButton();
    }
    @Entonces("Se muestra el mensaje de error indicando que el contenido es inexistente")
    public void seMuestraElMensajeDeErrorIndicandoQueElContenidoEsInexistente() {
        vodDetailInfoPageAction.checkNoContentMessageContentNoExist(Hooks.props.loginUsername_ProfileName1());
    }

    @Cuando("El usuario reproduce el último capitulo de la última temporada del contenido")
    public void elUsuarioReproduceElUltimoCapituloDeLaUltimaTemporadaDelContenido() {
        vodDetailInfoPageAction.clickEpisodesButton();
        vodDetailInfoPageAction.selectLastSeason();
        vodDetailInfoPageAction.selectLastEpisode();
        playerPageAction.waitPlayerLoadAndPlayback();
    }

    @Cuando("El usuario reproduce un capitulo de la serie")
    public void elUsuarioReproduceUnCapituloDeLaSerie() {
        vodDetailInfoPageAction.selectFirstSeason();
        vodDetailInfoPageAction.selectFirstEpisode();
        playerPageAction.mouseOverPlayer();
        playerPageAction.clickOnPlayButton();
        playerPageAction.waitPlayerLoadAndPlayback();
    }

    @Cuando("El usuario reproduce el último capitulo de la primer temporada del contenido")
    public void elUsuarioReproduceElUltimoCapituloDeLaPrimerTemporadaDelContenido() {
        vodDetailInfoPageAction.selectFirstSeason();
        vodDetailInfoPageAction.selectLastEpisode();
        playerPageAction.mouseOverPlayer();
        playerPageAction.clickOnPausedButton();
        playerPageAction.waitPlayerLoadAndPlayback();
    }

    @Entonces("Se muestra la ficha tecnica del contenido recomendado")
    public void seMuestraLaFichaTecnicaDelContenidoRecomendado() {
        vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Y("El usuario accede a la sección 'Episodios'")
    public void elUsuarioAccedeALaSecciónEpisodios() {
        vodDetailInfoPageAction.clickEpisodesButton();
    }

    @Entonces("La sección 'Episodios' se muestra correctamente")
    public void laSecciónEpisodiosSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.checkPageElementsEpisodes();
    }

    @Cuando("El usuario reproduce un contenido desde la lista de episodios")
    public void elUsuarioReproduceUnContenidoDesdeLaListaDeEpisodios() {
        vodDetailInfoPageAction.clickEpisodesButton();
        vodDetailInfoPageAction.selectRandomEpisode();
    }

    @Cuando("El usuario accede a un DIP VIVO")
    public void elUsuarioAccedeAUnDIPVIVO() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.homePageAction.waitForStripes();
        this.commonPageAction.goToStripe(Hooks.props.homePageMostViewedTV());
        this.homePageAction.accessContentStripe(this.vodDetailInfoPageFlow.stripeLoMasVistoEnTV);
        this.vodDetailInfoPageAction.channelDipIsDisplayed();
    }

    @Y("Copia la URL del DIP VIVO y lo comparte")
    public void copiaLaURLDelDIPVIVOYLoComparte() {
        vodDetailInfoPageAction.getUrlLiveDip();
    }

    @Entonces("Se visualiza el DIP VIVO desde otra cuenta")
    public void seVisualizaElDIPVIVODesdeOtraCuenta() {
        this.vodDetailInfoPageAction.goToUrl();
        this.loginPageAction.loginUser(Hooks.props.loginUsernameStarLinked(),
                Hooks.props.loginPasswordStarLinked(),
                false);
        this.profileSelectionPageAction.selectUserProfile(Hooks.props.loginUsernameStarLinked_ProfileName());
        this.commonPageAction.userIsLogged();
        vodDetailInfoPageAction.dipLiveAnotherAccountIsDisplayed();
    }

    @Entonces("Se muestra la ficha técnica del contenido grabado")
    public void seMuestraLaFichaTécnicaDelContenidoGrabado() {
        this.vodDetailInfoPageAction.channelDipIsDisplayed();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Recomendados' seleccionado")
    public void seMuestraLaFichaTécnicaDelContenidoRecomendadosSeleccionado() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido para alquilar")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoParaAlquilar() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Prime video' seleccionado")
    public void seMuestraLaFichaTécnicaDelContenidoPrimeVideoSeleccionado() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentPrimeVideo();
    }

    @Cuando("El usuario accede a un contenido con subtitulos")
    public void elUsuarioAccedeAUnContenidoConSubtitulos() {
        this.vodDetailInfoPageAction.openVodUrlSubtitles();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnPlayButton();
        this.playerPageAction.waitPlayerNotLoading();
        this.playerPageAction.playerSetTimeStart();
    }

    @Cuando("El usuario accede a un contenido sin subtitulos")
    public void elUsuarioAccedeAUnContenidoSinSubtitulos(){
        this.vodDetailInfoPageAction.openVodUrlWithoutSubtitles();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnPlayButton();
        this.playerPageAction.waitPlayerNotLoading();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido 'Prime video'")
    public void elUsuarioAccedeALaFichaTécnicaDeUnContenidoPrimeVideo() {
        var content = Hooks.dataProvider.getContentPrime(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La ficha técnica del contenido 'Prime video' sin 'Mi Lista' se muestra correctamente")
    public void laFichaTécnicaDelContenidoPrimeVideoSinMiListaSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentPrimeVideo();
    }

    @Y("Selecciona la opcion 'Compartir'")
    public void seleccionaLaOpcionCompartir() {
        this.vodDetailInfoPageAction.selectShare();
    }

    @Entonces("Las opciones para compartir se muestran correctamente")
    public void lasOpcionesParaCompartirSeMuestranCorrectamente() {
        this.vodDetailInfoPageAction.sharingOptionsIsDisplayed();
    }

    @Cuando("Accede a un contenido del stripe 'Top 10 Películas'")
    public void accedeAUnContenidoDelStripeTopPelículas() {
        this.homePageAction.accessContentStripe(this.homePageFlow.stripeTopMovies);
    }

    @Entonces("La ficha técnica del contenido del stripe 'Top 10 Películas' se muestra correctamente")
    public void elContenidoSeleccionadoEnTopPelículasSeMuestraCorrectamente() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Entonces("La ficha técnica del contenido del stripe 'Top 10 Series' se muestra correctamente")
    public void elContenidoSeleccionadoEnTopSeriesSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("^El usuario selecciona la pelicula (.*) - (.*)")
    public void elUsuarioSeleccionaLaPelicula(String title, String id) {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.vodDetailInfoPageAction.openVodMovieUrl(id);
        if (this.vodDetailInfoPageAction.isRentConfirmButtonPresent()) {
            this.vodDetailInfoPageAction.clickModalConfirmButton();
        } else {
            this.playerPageAction.clickOnPlayButton();
        }
    }

    @Cuando("^El usuario selecciona la serie (.*) - (.*) - (.*) - (.*)")
    public void elUsuarioSeleccionaLaSerie(String season, String id, String vod, String title) {
        this.vodDetailInfoPageAction.openVodSeriesUrl(id, vod, title);
        this.playerPageAction.clickOnPlayButton();
    }

    @Entonces("La ficha técnica del contenido del stripe 'Todo HBO' se muestra correctamente")
    public void laFichaTecnicaDelContenidoDelStripeTodoHBOSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Alquiler'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAlquiler() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.commonPageAction.searchContent(content.getTitle());
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas emitidos");
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película gratuita restringida' ingresando el PIN correcto")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaGratuitaRestringidaIngresandoUnPinCorrecto() {
        var content = Hooks.dataProvider.getMovieFreeRestricted(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.commonPageAction.enterParentalControlPinAndConfirm(Hooks.props.pinParental());
    }

    @Entonces("La ficha técnica del contenido del stripe 'Todo Paramount+' se muestra correctamente")
    public void laFichaTecnicaDelContenidoDelStripeTodoParamountSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario con compras bloqueadas accede a la ficha técnica de un contenido del tipo 'Alquiler'")
    public void elUsuarioConComprasBloqueadasAccedeALaFichaTecnicaDeUnContenidoDelTipoAlquiler() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Cuando("El usuario accede a la sección 'Episodios' de un contenido tipo 'Serie'")
    public void elUsuarioAccedeALaSeccionEpisodiosDeUnContenidoTipoSerie() {
        var content = Hooks.dataProvider.getSeriesFreeAllAgesBingeWatching(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        vodDetailInfoPageAction.clickEpisodesButton();
    }

    @Entonces("La barra de progreso se muestra correctamente")
    public void laBarraDeProgresoSeMuestraCorrectamente() {
        this.commonPageAction.checkEpisodieProgressBar();
    }

    @Entonces("El DIP del contenido seleccionado se visualiza correctamente")
    public void elDIPDelContenidoSeleccionadoSeVisualizaCorrectamente() {
        this.vodDetailInfoPageAction.channelDipIsDisplayed();
    }

    @Entonces("El stripe de Recomendaciones se visualiza correctamente")
    public void elStripeDeRecomendacionesSeVisualizaCorrectamente() {
        this.vodDetailInfoPageAction.recommendationsStripeIsDisplayed();
    }

    @Entonces("La pantalla de ficha técnica del contenido seleccionado se muestra correctamente")
    public void laPantallaDeFichaTecnicaDelContenidoSeleccionadoSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Y("Accede a un contenido del stripe de Recomendaciones")
    public void accedeAUnContenidoDelStripeDeRecomendaciones() {
        String titleDip = this.vodDetailInfoPageFlow.title.getText();
        this.homePageAction.accessContentStripe(this.vodDetailInfoPageFlow.dipStripeRecommendations);
        String titleDipRecommendations = this.vodDetailInfoPageFlow.title.getText();
        assertNotEquals("Does not access content recommendations",titleDip,titleDipRecommendations);
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Kids'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoKids() {
        var content = Hooks.dataProvider.getMovieFreeChildren(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Kids' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoKidsSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Alquileres'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAlquileres() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Entonces("La pantalla de ficha técnica de contenido 'Alquileres' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoAlquileresSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido 'Alquilado' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoAlquiladoATravesDeUnaBusqueda() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAgesPurchased(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas emitidos");
    }

    @Entonces("El contenido se muestra 'Alquilado' y disponible para reproducir")
    public void elContenidoSeMuestraDisponibleParaSerReproducido() {
        this.vodDetailInfoPageAction.contentRentedIsEnabled();
    }

    @Y("El usuario reproduce contenido 'Vivo' desde la ficha tecnica")
    public void elUsuarioReproduceContenidoVivo(){
        this.tvGuidePageAction.selectLiveChannel();
        this.vodDetailInfoPageAction.clickOnStartPlayer();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario reinicia contenido 'Vivo' desde la ficha tecnica")
    public void elUsuarioReiniciaContenidoVivo(){
        this.vodDetailInfoPageAction.clickOnRestart();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario inicia la reproducción del contenido durante menos de 60 segundos")
    public void elUsuarioIniciaLaReproduccionDelContenidoDuranteMenosDeSegundos(){
        this.playerPageAction.playLessThan60seconds();
    }

    @Y("El usuario vuelve a la pantalla de la ficha tecnica")
    public void elUsuarioVuelveALaPantallaDeLaFichaTecnica() {
        this.playerPageAction.clickBackButton();
        this.contentPageAction.getContentTitle();
    }

    @Entonces("El contenido se muestra disponible para reproducir desde el inicio")
    public void elContenidoSeMuestraDisponibleParaReproducirDesdeElInicio() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
        assertTrue(this.playerFlow.buttonPlay.isDisplayed());
    }

    @Cuando("El usuario inicia la reproducción del contenido durante más de 60 segundos")
    public void elUsuarioIniciaLaReproduccionDelContenidoDuranteMasDeSesentaSegundos() {
        this.playerPageAction.playbackMoreThan60Seconds();
    }

    @Entonces("El contenido se muestra disponible para continuar la reproducción")
    public void elContenidoSeMuestraDisponibleParaContinuarLaReproduccion() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAll();
        assertTrue(this.vodDetailInfoPageFlow.vodButtonContinuar.isDisplayed());
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Cine' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoCineSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelLive();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Aire' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoAireSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelLive();
    }

    @Y("El usuario agrega y quita el contenido a 'Mi lista'")
    public void elUsuarioAgregaYQuitaElContenidoAMiLista() {
        this.vodDetailInfoPageAction.addFavoriteContent();
        this.commonPageAction.checkNotificationAlert("Contenido agregado a Mi Lista");
        this.vodDetailInfoPageAction.checkContentIsFavorite();
        this.vodDetailInfoPageAction.removeFavoriteContent();
        this.commonPageAction.checkNotificationAlert("Contenido eliminado de Mi Lista");
        this.vodDetailInfoPageAction.checkContentIsNotFavorite();
    }

    @Y("Realiza una busqueda por elenco")
    public void realizaUnaBusquedaPorElenco() throws AWTException {
        Hooks.contentData.put("castName", this.vodDetailInfoPageAction.getCastName());
        this.vodDetailInfoPageAction.clickActorButton(0);
        this.searchPageAction.accessContentSearchResult("Películas, series y programas");
    }

    @Y("Realiza una busqueda por Director")
    public void realizaUnaBusquedaPorDirector() throws AWTException {
        Hooks.contentData.put("directorName", this.vodDetailInfoPageAction.getDirectorName());
        this.vodDetailInfoPageAction.clickDirectorButton();
        this.searchPageAction.accessContentSearchResult("Películas, series y programas");
    }

    @Cuando("El usuario agrega un contenido del tipo 'Película' a 'Mi Lista'")
    public void elUsuarioAgregaUnContenidoDelTipoPeliculaAMiLista() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesIsNotFavorite(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.vodDetailInfoPageAction.addFavoriteContent();
        this.commonPageAction.checkNotificationAlert("Contenido agregado a Mi Lista");
    }

    @Cuando("El usuario agrega un contenido del tipo 'Series' a 'Mi Lista'")
    public void elUsuarioAgregaUnContenidoDelTipoSeriesAMiLista() {
        this.elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoSeries();
        this.vodDetailInfoPageAction.addFavoriteContent();
        this.commonPageAction.checkNotificationAlert("Contenido agregado a Mi Lista");
    }
}
