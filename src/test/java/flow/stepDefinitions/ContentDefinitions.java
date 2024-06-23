package flow.stepDefinitions;

import flow.pageActions.*;
import flow.pageObjects.*;
import flow.webdriverUtils.ExtendedWebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

public class ContentDefinitions {
    protected ExtendedWebDriver webDriver;
    protected CommonPageAction commonPageAction;
    protected ContentPageAction contentPageAction;
    protected StripePageAction stripePageAction;
    protected MusicPageAction musicPageAction;
    protected PlayerPageAction playerPageAction;
    protected StripePageFlow stripePageFlow;
    protected ContentPageFlow contentPageFlow;
    protected HomePageAction homePageAction;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected SearchPageAction searchPageAction;
    protected SportsPageAction sportsPageAction;
    protected SportsPageFlow sportsPageFlow;
    protected GridOfPostersActions gridOfPostersActions;
    protected VodDetailInfoPageFlow vodDetailInfoPageFlow;
    protected CommonPageElementsFlow commonPageElementsFlow;

    public ContentDefinitions() {
        this.musicPageAction = new MusicPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.contentPageAction = new ContentPageAction(Hooks.getDriver());
        this.stripePageAction = new StripePageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.stripePageFlow = new StripePageFlow(Hooks.getDriver());
        this.contentPageFlow = new ContentPageFlow(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.searchPageAction = new SearchPageAction(Hooks.getDriver());
        this.sportsPageAction = new SportsPageAction(Hooks.getDriver());
        this.sportsPageFlow = new SportsPageFlow(Hooks.getDriver());
        this.webDriver = new ExtendedWebDriver(Hooks.getDriver());
        this.gridOfPostersActions = new GridOfPostersActions(Hooks.getDriver());
        this.vodDetailInfoPageFlow = new VodDetailInfoPageFlow(Hooks.getDriver());
        this.commonPageElementsFlow = new CommonPageElementsFlow(Hooks.getDriver());
    }

    @Entonces("La sección de series se muestra correctamente")
    public void laSeccionDeSeriesSeMuestraCorrectamente() {
        this.contentPageAction.pageIsDisplayed("Series");
    }

    @Entonces("Se muestra nuevamente la sección Series")
    public void seMuestraNuevamenteLaSeccionSeries() {
        this.contentPageAction.pageIsDisplayed("Series");
    }
    @Entonces("Se muestra nuevamente la sección Kids")
    public void seMuestraNuevamenteLaSeccionKids() {
        this.contentPageAction.pageIsDisplayed("Kids");
    }

    @Entonces("La sección de películas se muestra correctamente")
    public void laSeccionDePeliculasSeMuestraCorrectamente() {
        this.contentPageAction.pageIsDisplayed("Películas");
    }

    @Entonces("Se muestra nuevamente la sección Películas")
    public void seMuestraNuevamenteLaSeccionPeliculas() {
        this.contentPageAction.pageIsDisplayed("Películas");
    }

    @Y("El usuario accede a los filtros de contenidos")
    public void elUsuarioAccedeALosFiltrosDeContenidos() {
        this.contentPageAction.clickFiltersButton();
    }

    @Entonces("Se muestran los filtros de contenidos de la sección 'Películas'")
    public void seMuestranLosFiltrosDeContenidosDeLaSeccionPeliculas() {
        this.contentPageAction.filtersIsDisplayed(Hooks.props.contentFilterTvOrMovie_Movie());
    }

    @Cuando("El usuario accede a 'Todos los títulos' de la sección 'Películas'")
    public void elUsuarioAccedeATodosLosTitulosDeLaSeccionPeliculas() {
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllMovies());
    }

    @Cuando("Cuando El usuario accede a un stripe de la sección 'Series'")
    public void elUsuarioAccedeATodosLosTitulosDeLaSeccionSeries() {
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.seriesPageAllTitles());
    }

    @Entonces("Se muestran los filtros de contenidos de la sección 'Series'")
    public void seMuestranLosFiltrosDeContenidosDeLaSeccionSeries() {
        this.contentPageAction.filtersIsDisplayed("Series");
    }

    @Entonces("La sección de alquileres se muestra correctamente")
    public void laSeccionDeAlquileresSeMuestraCorrectamente() {
        this.contentPageAction.pageIsDisplayed("Alquileres");
        this.commonPageAction.goToStripe(Hooks.props.homePageRents());
    }

    @Entonces("La sección kids se muestra correctamente")
    public void laSeccionKidsSeMuestraCorrectamente() {
        this.contentPageAction.pageIsDisplayed("Kids");
    }

    @Cuando("El usuario accede a 'Todos los títulos' de la sección 'Kids'")
    public void elUsuarioAccedeATodosLosTitulosDeLaSeccionKids() {
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllTitles());
    }

    @Entonces("Se muestran los filtros de contenidos de la sección 'Kids'")
    public void seMuestranLosFiltrosDeContenidosDeLaSeccionKids() {
        this.contentPageAction.filtersIsDisplayed("Kids");
    }

    @Entonces("Se muestran los stripes de la sección 'Películas'")
    public void seMuestranLosStripesDeLaSeccionPeliculas() {
        this.contentPageAction.pageIsDisplayed("Películas");
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllMovies());
    }

    @Entonces("Se muestran los stripes de la sección 'Series'")
    public void seMuestranLosStripesDeLaSeccionSeries() {
        this.contentPageAction.pageIsDisplayed("Series");
        this.commonPageAction.goToStripe(Hooks.props.seriesPageAllTitles());
    }

    @Entonces("Se visualizan elementos restringidos en la sección")
    public void seVisualizanElementosRestringidos() {
        this.commonPageAction.getContentRestrictedRandom();
    }

    @Cuando("El usuario accede a 'Todos los alquileres' de la sección 'Alquileres'")
    public void elUsuarioAccedeATodosLosTitulosDeLaSeccionAlquileres() {
        this.commonPageAction.goToStripe(Hooks.props.homePageRents());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.homePageRents());
    }

    @Entonces("Se muestran los filtros de contenidos de la sección 'Alquileres'")
    public void seMuestranLosFiltrosDeContenidosDeLaSeccionAlquileres() {
        this.contentPageAction.filtersIsDisplayed(Hooks.props.contentFilterPrice_Rentals());
    }

    @Y("El usuario aplica filtros de contenidos")
    public void elUsuarioAplicaFiltrosDeContenidos() {
        this.contentPageAction.openFilterButton();
        this.contentPageAction.selectFilterElement("Audiencia");
        this.contentPageAction.selectFilterElementItem("General");
        this.contentPageAction.selectFilterElement("Género");
        this.contentPageAction.selectFilterElementItem("Comedia");
    }

    @Entonces("Se muestran los contenidos de 'Alquileres' que coinciden con los filtros seleccionados")
    public void seMuestranLosContenidosDeAlquileresQueCoincidenConLosFiltrosSeleccionados() {
        this.contentPageAction.checkClearFilterButtonIsDisplayed();
        this.contentPageAction.clickClearFilterButton();
    }

    @Entonces("No se visualizan elementos restringidos en la pantalla de inicio")
    public void noSeVisualizanElementosRestringidos() {
        this.commonPageAction.checkContentRestrictedNotExist();
    }

    @Entonces("Se muestran todos los contenidos del stripe")
    public void seMuestranTodosLosContenidosDelStripe() {
        // TODO move stripePageElements and methods, to contentPage Class
        this.stripePageAction.pageIsDisplayed();
    }

    @Entonces("La sección 'Radios y Música' se muestra correctamente")
    public void laSeccionRadiosYMusicaSeMuestraCorrectamente() {
        this.musicPageAction.waitTitleMusicDisplayed();
        this.musicPageAction.waitTitleRadiosDisplayed();
        this.musicPageAction.checkContentRadios();
        this.musicPageAction.checkContentMusic();
    }

    @Cuando("El usuario ingresa a la sección 'Radios y Música'")
    public void elUsuarioIngresaALaSeccionRadiosYMusica() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.musicPageAction.waitNavbarContentMusic();
        this.musicPageAction.clickNavbarContentMusic();
    }

    @Cuando("El usuario reproduce un contenido desde el SuperHero")
    public void elUsuarioReproduceUnContenidoDesdeElSuperHero() {
        this.commonPageAction.clickActiveSuperHeroButtonPlay();
    }

    @Entonces("La radio seleccionada se reproduce correctamente")
    public void laRadioSeleccionadaSeReproduceCorrectamente() {
        this.playerPageAction.isPlayingRadio();
    }

    @Cuando("El usuario selecciona una Radio disponible")
    public void elUsuarioSeleccionaUnaRadioDisponible() {
        this.contentPageAction.clickRandomRadio();
    }

    @Cuando("El usuario selecciona una lista de música disponible")
    public void elUsuarioSeleccionaUnaListaDeMusicaDisponible() {
        this.contentPageAction.clickRandomMusic();
    }

    @Entonces("La lista de música seleccionada se reproduce correctamente")
    public void laListaDeMusicaSeleccionadaSeReproduceCorrectamente() {
        this.playerPageAction.isPlayingMusic();
    }

    @Entonces("Los elementos del reproductor se muestran correctamente")
    public void losElementosDelReproductorSeMuestranCorrectamente() {
        this.playerPageAction.checkElementsMusicPlayer();
    }

    @Cuando("El usuario reproduce una Radio disponible")
    public void elUsuarioReproduceUnaRadioDisponible() {
        this.contentPageAction.clickRandomRadio();
    }

    @Cuando("El usuario reproduce una lista de música disponible")
    public void elUsuarioReproduceUnaListaDeMusicaDisponible() {
        this.contentPageAction.clickRandomMusic();
    }

    @Y("El usuario pausa la reproducción de la Radio")
    public void elUsuarioPausaLaReproduccionDeLaRadio() {
        this.playerPageAction.isPlayingRadio();
        this.playerPageAction.clickPauseMusicPlayer();
    }

    @Entonces("La reproducción se detiene correctamente")
    public void laReproduccionSeDetieneCorrectamente() {
        this.playerPageAction.waitPlayMusicPlayerIsDisplayed();
        this.playerPageAction.playerMusicIsPaused();
    }

    @Y("El usuario pausa la reproducción de la lista de música")
    public void elUsuarioPausaLaReproduccionDeLaListaDeMusica() {
        this.playerPageAction.isPlayingMusic();
        this.playerPageAction.clickPauseMusicPlayer();
    }

    @Cuando("El usuario accede a un contenido con costo adicional directamente desde el SuperHero")
    public void elUsuarioAccedeAUnContenidoConCostoAdicionalDirectamenteDesdeElSuperHero() {
        this.contentPageAction.accessToContentSuperHeroRents(Hooks.props.pinParental());
    }

    @Y("se muestra DIP con la ficha tecnica")
    public void seMuestraDIPConLaFichaTecnica() {
        if (this.contentPageAction.statusResult == true) {
            this.contentPageAction.checkDipContectRents();
        }
    }

    @Cuando("se hace presiona el boton 'Alquilar'")
    public void seHacePresionaElBotonAlquilar() {
        if (this.contentPageAction.statusResult == true) {
            this.contentPageAction.pressToRent();
        }
    }

    @Y("se ingresa el codigo")
    public void seIngresaElCodigo() {
        if (this.contentPageAction.statusResult == true) {
            this.contentPageAction.enterPinRent();
        }
    }

    @Entonces("Se muestra la navegacion automatica del SuperHero")
    public void seMuestraLaNavegacionAutomaticaDelSuperHero() {
        this.contentPageAction.checkAutomaticMovementSuperHero();
    }

    @Y("Se dirige al Stripe Star+")
    public void seDirigeAlStripeStar() {
        this.commonPageAction.goToStripe(Hooks.props.stripeStar());
    }

    @Entonces("Se debe posicionar sobre los poster visibles de peliculas Star+ y no se debe mostrar el boton de play en el centro")
    public void seDebePosicionarSobreLosPosterVisiblesDePeliculasStarYNoSeDebeMostrarElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeStarMovies, stripePageFlow.getSlickActive);
    }

    @Y("Se dirige al Stripe Disney+")
    public void seDirigeAlStripeDisney() {
        this.commonPageAction.goToStripe(Hooks.props.stripeDisney());
    }

    @Entonces("Se debe posicionar sobre los poster visibles y no se debe mostrar el boton de play en el centro")
    public void seDebePosicionarSobreLosPosterVisiblesYNoSeDebeMostrarElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeDisneyMovies, stripePageFlow.getSlickActive);
    }

    @Y("Se dirige al Stripe Star+ en la seccion series")
    public void seDirigeAlStripeStarEnLaSeccionSeries() {
        this.commonPageAction.goToStripe(Hooks.props.stripeStar());
    }

    @Entonces("Se debe posicionar sobre los poster visibles de series Star+ y no se debe mostrar el boton de play en el centro")
    public void seDebePosicionarSobreLosPosterVisiblesDeSeriesStarYNoSeDebeMostrarElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeStarSeries, stripePageFlow.getSlickActive);
    }

    @Y("Se dirige al Stripe Disney+ en la seccion series")
    public void seDirigeAlStripeDisneyEnLaSeccionSeries() {
        this.commonPageAction.goToStripe(Hooks.props.stripeDisney());
    }

    @Entonces("Se debe posicionar sobre los poster visibles de series Disney+ y no se debe mostrar el boton de play en el centro")
    public void seDebePosicionarSobreLosPosterVisiblesDeSeriesDisneyYNoSeDebeMostrarElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeDisneySeries, stripePageFlow.getSlickActive);
    }

    @Cuando("El usuario busca un contenido grabado")
    public void elUsuarioBuscaUnContenidoGrabado() {
        this.contentPageAction.searchRecordContent();
    }

    @Y("El usuario reproduce el contenido grabado")
    public void elUsuarioReproduceElContenidoGrabado() {
        this.contentPageAction.checkRecording();
        this.contentPageAction.playRecordedContent();
    }

    @Y("El usuario reproduce el contenido grabado desde DIP")
    @Cuando("El usuario vuelve a reproducir el contenido")
    public void elUsuarioReproduceElContenidoGrabadoDesdeDIP() {
        this.vodDetailInfoPageAction.clickPlayButton();
    }

    @Y("El usuario se dirige al Stripe Star+ y selecciona 'ver mas'")
    public void elUsuarioSeDirigeAlStripeStarYSeleccionaVerMas() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeStar());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeSeries());
    }

    @Entonces("Se muestran los poster visibles sin el boton de play en el centro.")
    public void seMuestranLosPosterVisiblesSinElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeStarSeries,
                this.stripePageFlow.getItemWrapperBy);
    }

    @Y("Se dirige al Stripe Disney+ y selecciona 'Ver Mas'")
    public void seDirigeAlStripeDisneyYSeleccionaVerMas() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeDisney());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeMovies());
    }

    @Entonces("Se muestran los poster de Disney+ sin el boton de play en el centro.")
    public void seMuestranLosPosterDeDisneySinElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeOnSeriesAndMovies,
                this.stripePageFlow.getItemWrapperBy);
    }

    @Y("se dirige al Stripe Star+ y seleccionar 'ver mas'")
    public void seDirigeAlStripeStarYSeleccionarVerMas() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeStar());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeMovies());
    }

    @Y("Se dirige al Stripe Disney+ y selecciona 'Ver Mas'.")
    public void seDirigeAlStripeDisneyYSeleccionaVerMasEnSeries() {
        this.homePageAction.waitForStripes();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeDisney());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeSeries());
    }


    @Entonces("Se muestran los poster de Star+ sin el boton de play en el centro")
    public void seMuestranLosPosterDeStarSinElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeOnSeriesAndMovies,
                this.stripePageFlow.getItemWrapperBy);
    }

    @Entonces("Se muestran los poster de Disney+ en Series sin el boton de play en el centro")
    public void seMuestranLosPosterDeDisneyEnSeriesSinElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeOnSeriesAndMovies,
                this.stripePageFlow.getItemWrapperBy);
    }

    @Entonces("Se muestran los poster de Star+ en Series sin el boton de play en el centro")
    public void seMuestranLosPosterDeStarEnSeriesSinElBotonDePlayEnElCentro() {
        this.stripePageAction.checkPostersWithoutPlayButton(this.stripePageFlow.stripeOnSeriesAndMovies,
                this.stripePageFlow.getItemWrapperBy);
    }

    @Cuando("El usuario realiza una busqueda de un contenido grabado")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoGrabado() {
        this.contentPageAction.searchRecordContent();
    }

    @Entonces("No se muestra el boton play dentro del poster")
    public void noSeMuestraElBotonPlayDentroDelPoster() {
        this.contentPageAction.checkRecording();
    }

    @Cuando("El usuario accede a un stripe de la sección 'Series'")
    public void elUsuarioAccedeAUnStripeDeLaSeccionSeries() {
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.seriesPageAllTitles());
    }

    @Y("El usuario reproduce un contenido aleatorio")
    public void elUsuarioReproduceUnContenidoAleatorio() {
        this.contentPageAction.clickRandomContentItem();
    }

    @Y("El usuario accede al contenido grabado")
    public void elUsuarioAccedeAlContenidoGrabado() {
        this.contentPageAction.checkRecording();
        this.contentPageAction.accessRecordContent();
    }

    @Cuando("El usuario selecciona un contenido aleatorio del stripe 'Listas de música'")
    public void elUsuarioSeleccionaUnContenidoAleatorioDelStripeListasDeMúsica() {
        this.contentPageAction.clickRandomListMusic();
    }

    @Entonces("La lista de música seleccionada continua reproduciendo correctamente")
    public void laListaDeMúsicaSeleccionadaContinuaReproduciendoCorrectamente() {
        this.playerPageAction.isPlayingMusic();
    }

    @Cuando("El usuario reproduce una lista de música")
    public void elUsuarioReproduceUnaListaDeMúsica() {
        this.contentPageAction.clickRandomListMusic();
        this.playerPageAction.isPlayingMusic();
    }

    @Y("El usuario quita los filtros de contenidos")
    public void elUsuarioQuitaLosFiltrosDeContenidos() {
        this.contentPageAction.closedFilterButton();
        this.contentPageAction.openFilterButton();
        this.contentPageAction.selectFilterItem("General");
        this.contentPageAction.selectFilterItem("Comedia");
        this.contentPageAction.closedFilterButton();
    }

    @Entonces("Se muestran todos los contenidos de 'Alquileres'")
    public void seMuestranTodosLosContenidosDeAlquileres() {
        this.homePageAction.postersAreShownCorrectly(this.contentPageFlow.gridItemsWrapper, this.stripePageFlow.getItemWrapperBy);
    }

    @Cuando("El usuario accede al DIP de un contenido")
    public void elUsuarioAccedeAlDIPDeUnContenido() {
        this.contentPageAction.accessContent();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película infantil gratuita '")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaInfantilGratuita() {
        var content = Hooks.dataProvider.getMovieFreeChildren(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
    }

    @Y("El usuario accede a un contenido del resultado de la búsqueda 'TV en vivo'")
    public void elUsuarioAccedeAUnContenidoDelResultadoDeLaBusquedaTVEnVivo() {
        this.searchPageAction.accessSearchLiveTVFirstResult();
        this.playerPageAction.waitPlayerLoadAndPlayback();
    }

    @Y("Accede al stripe 'Todos los Alquileres'")
    public void accedeAlStripeTodosLosAlquileres() {
        this.commonPageAction.goToStripe(Hooks.props.homePageRents());
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.homePageRents());
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Deportes'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoDeportes() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.sportsPageAction.accessToSportsSection();
        this.sportsPageAction.accessToStripe(sportsPageFlow.stripeBiographiesAndDocumentaries);
        this.homePageAction.accessContentStripe(sportsPageFlow.stripeBiographiesAndDocumentaries);
    }

    @Entonces("El contenido se muestra correctamente para continuar la reproducción")
    public void elContenidoSeMuestraCorrectamenteParaContinuarLaReproduccion() {
        this.contentPageAction.checkContentContinueViewing();
    }

    @Y("Accede al stripe 'Todas las series'")
    public void accedeAlStripeTodasLasSeries() {
        this.commonPageAction.goToStripe(Hooks.props.seriesPageAllTitles());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.seriesPageAllTitles());
    }

    @Entonces("El 'GOP' del stripe seleccionado se visualiza correctamente")
    public void elGOPDelStripeSeleccionadoSeVisualizaCorrectamente() {
        this.contentPageAction.gopAllTitlesIsDisplayed();
    }

    @Y("Accede al stripe 'Todas las películas'")
    public void accedeAlStripeTodasLasPeliculas() {
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllMovies());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllMovies());
    }

    @Y("El usuario accede al 'GOP' del stripe 'Todas las películas'")
    public void elUsuarioAccedeAlGOPDelStripeTodasLasPeliculas() {
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllMovies());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllMovies());
        this.contentPageAction.gopAllTitlesIsDisplayed();
    }

    @Cuando("El usuario vuelve atrás desde el 'GOP'")
    public void elUsuarioVuelveAtrasDesdeElGOP() {
        this.webDriver.navigateBack();
    }

    @Y("El usuario accede al 'GOP' del stripe 'Todas las series'")
    public void elUsuarioAccedeAlGOPDelStripeTodasLasSeries() {
        this.commonPageAction.goToStripe(Hooks.props.seriesPageAllTitles());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.seriesPageAllTitles());
        this.contentPageAction.gopAllTitlesIsDisplayed();
    }

    @Y("El usuario accede al 'GOP' del stripe 'Todos los títulos'")
    public void elUsuarioAccedeAlGOPDelStripeTodasLasTitulos() {
        this.commonPageAction.goToStripe(Hooks.props.moviesPageAllTitles());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.moviesPageAllTitles());
        this.contentPageAction.gopAllTitlesIsDisplayed();
    }

    @Y("El usuario accede al 'GOP' del stripe 'Todas los alquileres'")
    public void elUsuarioAccedeAlGOPDelStripeTodasLosAlquileres() {
        this.commonPageAction.goToStripe(Hooks.props.rentPageAllTitles());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.rentPageAllTitles());
        this.gridOfPostersActions.gopIsDisplayed();
    }

    @Entonces("Se muestra nuevamente la seccion alquileres")
    public void seMuestraNuevamenteLaSeccionAlquileres() {
        this.contentPageAction.pageIsDisplayed("Alquileres");
    }

    @Cuando("El usuario accede al 'GOP' del stripe 'Adultos' dentro de la seccion Películas")
    public void elUsuarioAccedeAlGOPDelStripeAdultosDentroDeLaSeccionPeliculas() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.accessNavbarElementMovies();
        this.commonPageAction.goToStripe(Hooks.props.stripeAdult());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeAdult());
    }

    @Y("El usuario abre el menu de filtros")
    public void elUsuarioAbreElMenuDeFiltros() {
        this.contentPageAction.openFilterButton();
    }

    @Cuando("El usuario cierra el menu de filtros")
    public void elUsuarioCierraElMenuDeFiltros() {
        this.contentPageAction.closedFilterButton();
    }

    public static String titleContentAddedMyList;
    @Y("El usuario se dirige al stripe 'Mis Peliculas y Series' en la sección 'Mi lista'")
    public void elUsuarioSeDirigeAlStripeMisPeliculasYSeriesEnLaSeccionMiLista() {
        titleContentAddedMyList = this.vodDetailInfoPageFlow.title.getText();
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
    }

    @Entonces("Se visualiza el contenido agregado en la primera posición del stripe")
    public void seVisualizaElContenidoAgregadoEnLaPrimerPosicionDelStripe() {
        this.contentPageAction.checkContentAddedMyList(titleContentAddedMyList);
    }

    @Entonces("Los posters de la sección 'Películas' se visualizan correctamente")
    public void losPostersDeLaSeccionPeliculasSeVisualizanCorrectamente() {
        this.commonPageAction.checkPostersIsDisplayed(Hooks.props.moviesPageAllMovies(), commonPageElementsFlow.stripes);
    }

    @Entonces("Los posters de la sección 'Series' se visualizan correctamente")
    public void losPostersDeLaSeccionSeriesSeVisualizanCorrectamente() {
        this.commonPageAction.checkPostersIsDisplayed(Hooks.props.seriesPageAllTitles(), commonPageElementsFlow.stripes);
    }

    @Entonces("Los posters de la sección 'Kids' se visualizan correctamente")
    public void losPostersDeLaSeccionKidsSeVisualizanCorrectamente() {
        this.commonPageAction.checkPostersIsDisplayed(Hooks.props.moviesPageAllTitles(), commonPageElementsFlow.stripes);
    }

    @Entonces("Los posters de la sección 'Deportes' se visualizan correctamente")
    public void losPostersDeLaSeccionDeportesSeVisualizanCorrectamente() {
        this.commonPageAction.checkPostersIsDisplayed("Biografías, documentales y especiales deportivos", commonPageElementsFlow.stripes);
    }

    @Entonces("Los posters de la sección 'Alquileres' se visualizan correctamente")
    public void losPostersDeLaSeccionAlquileresSeVisualizanCorrectamente() {
        this.commonPageAction.checkPostersIsDisplayed(Hooks.props.homePageRents(), commonPageElementsFlow.stripesRent);
    }
}