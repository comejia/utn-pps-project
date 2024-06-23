package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.PlayerPageAction;
import flow.pageActions.SearchPageAction;
import flow.pageActions.VodDetailInfoPageAction;
import flow.pageObjects.SearchPageFlow;
import flow.pageObjects.VodDetailInfoPageFlow;
import flow.utils.UtilsString;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.awaitility.Awaitility;

import java.awt.*;
import java.time.Duration;

public class SearchDefinitions {

    protected static String searchTitle;
    protected CommonPageAction commonPageAction;
    protected SearchPageAction searchPageAction;
    protected PlayerPageAction playerPageAction;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected VodDetailInfoPageFlow vodDetailInfoPageFlow;
    protected SearchPageFlow searchPageFlow;
    protected
    String titleMovieFreeAllAgesStarPlus;
    String titleContentDisneyPlus;
    String searchContent;

    public SearchDefinitions() {
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.searchPageAction = new SearchPageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.searchPageFlow = new SearchPageFlow(Hooks.getDriver());
        this.vodDetailInfoPageFlow = new VodDetailInfoPageFlow(Hooks.getDriver());
    }

    @Entonces("Se muestran los elementos de la búsqueda")
    public void seMuestranLosElementosDeLaBusqueda() {
        this.commonPageAction.checkSearchButtonText();
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Película gratuita'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoPeliculaGratuita() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle(), 5);
        this.commonPageAction.searchContent(searchTitle);
    }

    @Entonces("Se muestran los resultados de la búsqueda realizada")
    public void seMuestranLosResultadosDeLaBusquedaRealizada() {
        this.searchPageAction.pageIsDisplayed("search");
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Series'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoSeries() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getSeriesFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle(), 5);
        this.commonPageAction.searchContent(searchTitle);
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Canal vivo'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoCanalVivo() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        searchTitle = "telefe";
        this.commonPageAction.searchContent(searchTitle);
    }

    @Y("El usuario reproduce el contenido del resultado de la búsqueda")
    public void elUsuarioReproduceElContenidoDelResultadoDeLaBusqueda() {
        this.searchPageAction.clickOnFirstElementOfContentSearched();
    }

    @Cuando("El usuario realiza la búsqueda de un contenido contenido 'vivo'")
    public void elUsuarioRealizalaBusquedaDeUnContenidoVivo() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        searchTitle = "tn hd";
        this.commonPageAction.searchContent(searchTitle);
    }

    @Cuando("El usuario realiza una búsqueda de un contenido")
    public void elUsuarioRealizaUnaBusquedaDeUnContenido() {
        this.commonPageAction.clickOnInputSearch();
        this.commonPageAction.sendCharacter("M");
        this.commonPageAction.sendCharacter("a");
        this.commonPageAction.sendCharacter("t");
        this.commonPageAction.sendCharacter("r");
        this.commonPageAction.sendCharacter("i");
        this.commonPageAction.sendCharacter("x");
    }

    @Entonces("Se muestran los resultados de la búsqueda de 'Canal vivo' realizada")
    public void seMuestranLosResultadosDeLaBusquedaDeCanalVivoRealizada() {
        this.searchPageAction.checkSearchChannelResult();
    }

    @Y("El usuario accede al dip de un contenido del resultado de la búsqueda")
    public void elUsuarioAccedeALDipDeUnContenidoDelResultadoDeLaBusqueda() {
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas");
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Película gratuita restringida'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoPeliculaGratuitaRestringida() {
        var content = Hooks.dataProvider.getMovieFreeRestrictedPg13(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle(), 5);
        this.commonPageAction.searchContent(content.getTitle());
    }

    @Cuando("El usuario realiza una búsqueda de un contenido inexistente con caracteres especiales")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoInexistenteConCaracteresEspeciales() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = " -&$#/[]+´'";
        this.commonPageAction.searchContent(searchTitle);
    }

    @Entonces("Se muestra mensaje indicando que no se han encontrado resultados de búsqueda")
    public void seMuestraMensajeIndicandoQueNoSeHanEncontradoResultadosDeBusqueda() {
        this.searchPageAction.checkSearchResult(searchTitle);
        this.searchPageAction.checkNoResultsMessage();
    }

    @Y("Se muestran contenidos recomendados")
    public void seMuestranContenidosRecomendados() {
        this.searchPageAction.checkRecommendedContents();
    }

    @Cuando("El usuario realiza una búsqueda de un contenido inexistente")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoInexistente() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        searchTitle = "    ";
        this.commonPageAction.searchContent(searchTitle);
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Tv en vivo'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoTvEnVivo() {
        var content = Hooks.dataProvider.getLiveChannel(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchContent = content.getTitle();
        searchPageAction.searchContent(searchContent);
        searchTitle = UtilsString.getStringFirstWords(searchContent, 3);
        this.commonPageAction.searchContent(searchTitle);
    }

    @Entonces("Se muestran los resultados de la búsqueda realizada para 'TV en vivo'")
    public void seMuestranLosResultadosDeLaBusquedaRealizadaParaTVEnVivo() {
        this.searchPageAction.checkSearchChannelsAmount();
        this.searchPageAction.checkSearchChannelResult(searchTitle);
    }

    @Entonces("Se realiza la búsqueda de contenidos del Director seleccionado")
    public void seRealizaLaBusquedaDeContenidosDelDirectorSeleccionado() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("directorName"));
    }

    @Entonces("Se realiza la búsqueda de contenidos del Elenco seleccionado")
    public void seRealizaLaBusquedaDeContenidosDelElencoSeleccionado() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("castName"));
    }

    @Cuando("El usuario ingresa 3 caracteres en el campo de búsqueda")
    public void elUsuarioIngresaCaracteresEnElCampoDeBusqueda() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle(), 2);
        this.commonPageAction.searchContent(searchTitle);
    }

    @Entonces("Se muestra la búsqueda predictiva en base a los caracteres ingresados")
    public void seMuestraLaBusquedaPredictivaEnBaseALosCaracteresIngresados() {
        this.searchPageAction.checkSearchResult(searchTitle);
    }

    @Cuando("El usuario realiza la misma búsqueda nuevamente")
    public void elUsuarioRealizaLaMismaBusquedaNuevamente() {
        this.searchPageAction.refreshUrl();
        searchTitle = Hooks.contentData.get("title");
        this.commonPageAction.searchContent(searchTitle);
    }

    @Entonces("Se muestra el historial de búsqueda realizado anteriormente")
    public void seMuestraElHistorialDeBusquedaRealizadoAnteriormente() {
        //TODO History
    }

    @Y("El usuario selecciona el contenido desde el historial de búsqueda")
    public void elUsuarioSeleccionaElContenidoDesdeElHistorialDeBusqueda() {
        searchTitle = Hooks.contentData.get("title");
        this.searchPageAction.accessFirstItemFromVodSearchResults(searchTitle);
        this.vodDetailInfoPageAction.clickBackButton();
    }

    @Y("El usuario elimina el historial de búsqueda")
    public void elUsuarioEliminaElHistorialDeBusqueda() {
        //TODO History
    }

    @Entonces("Se elimina el historial de búsqueda")
    public void seEliminaElHistorialDeBusqueda() {
        //TODO History
    }

    @Y("El usuario cancela la eliminación del historial de búsqueda")
    public void elUsuarioCancelaLaEliminacionDelHistorialDeBusqueda() {
        //TODO History
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Película gratuita' escribiendo el título en mayúsculas")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoPeliculaGratuitaEscribiendoElTituloEnMayusculas() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAges(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle().toUpperCase());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle().toUpperCase(), 5);
        this.commonPageAction.searchContent(content.getTitle().toUpperCase());
    }

    @Cuando("El usuario realiza una búsqueda 'Promo channel'")
    public void elUsuarioRealizaUnaBusquedaPromoChannel() {
        String titlePromoChannel = Hooks.props.promoChannel();
        this.commonPageAction.searchContent(titlePromoChannel);
    }

    @Entonces("El contenido se visualiza correctamente dentro del stripe 'Canales en Vivo'")
    public void elContenidoSeVisualizaCorrectamenteDentroDelStripeCanalesEnVivo() {
        this.searchPageAction.searchContentPromoChannelInStripeLive();
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Paramount Plus'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoParamountPlus() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var contentParamountPlus = Hooks.dataProvider.getMovieFreeAllAgesParamountPlus(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(contentParamountPlus.getTitle());
        Hooks.props.setProperty("titleContentParamout", contentParamountPlus.getTitle());
    }

    @Entonces("Se muestran los resultados en el stripe de 'Películas, series y programas emitidos'")
    public void seMuestranLosResultadosEnElStripeDePeliculasSeriesYProgramasEmitidos() {
        this.searchPageAction.checkResultsContentParamountInStripe();
    }

    @Cuando("El usuario realiza una busqueda de contenido de Star+")
    public void elUsuarioRealizaUnaBusquedaDeContenidoDeStar() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesStarPlus(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
        titleMovieFreeAllAgesStarPlus = content.getTitle();
    }

    @Entonces("Se muestran los poster de Star+ sin el boton de play en el centro.")
    public void seMuestranLosPosterDeStarSinElBotonDePlayEnElCentro() {
        this.searchPageAction.checkPostersWithoutPlayButton(this.searchPageFlow.stripePeliculasSeriesYProgramas,
                titleMovieFreeAllAgesStarPlus,
                this.searchPageFlow.itemWrapper,
                this.searchPageFlow.contentTitle);
    }

    @Cuando("El usuario realiza una busqueda de contenido de Disney+")
    public void elUsuarioRealizaUnaBusquedaDeContenidoDeDisney() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getMovieFreeAllAgesDisneyPlus(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
        titleContentDisneyPlus = content.getTitle();
    }

    @Entonces("Se muestra los poster de Disney+ sin el boton de play en el centro.")
    public void seMuestraLosPosterDeDisneySinElBotonDePlayEnElCentro() {
        this.searchPageAction.checkPostersWithoutPlayButton(this.searchPageFlow.stripePeliculasSeriesYProgramas,
                titleContentDisneyPlus,
                this.searchPageFlow.itemWrapper,
                this.searchPageFlow.contentTitle);
    }


    @Cuando("El usuario realiza una búsqueda de un contenido 'Prime video'")
    public void elUsuarioRealizaUnaBusquedaDeContenidoPrimeVideo() {
        var content = Hooks.dataProvider.getContentPrime(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
    }


    @Entonces("Se muestran los poster de Prime video sin el boton de play en el centro")
    public void seMuestranLosPosterDePrimeVideoSinElBotonDePlayEnElCentro() {
        this.searchPageAction.viewPostersStripePrimeVideo();
    }


    @Entonces("El stripe 'Prime video' se visualiza en la cuarta posición")
    public void elStripePrimeVideoSeVisualizaEnLaCuartaPosicion() {
        this.searchPageAction.stripePrimeVideoIsDisplayed();
    }

    @Y("El usuario accede al dip de un contenido 'Prime video' del resultado de la búsqueda")
    public void accedeAUnContenidoDelResultadoDeLaBusqueda() {
        this.searchPageAction.accessSearchContentPrimeVideo("Prime video");
    }

    @Y("Accede a un contenido del resultado de la búsqueda")
    public void accedeAUnContenidoDelResultadoDeLaBúsqueda() {
        this.searchPageAction.accessSearchContentPrimeVideo("Prime video");
    }

    @Entonces("El resultado se visualiza en el orden Canales en vivo, Películas, Series Y Programas emitidos, Guía de TV")
    public void elResultadoSeVisualizaEnElOrdenCanalesEnVivoPeliculasSeriesYProgramasEmitidosGuiaDeTV() {
        this.searchPageAction.checkOrderSearchResults();
    }

    @Entonces("Visualiza los resultados en el stripe de 'Películas, series y programas emitidos'")
    public void visualizaLosResultadosEnElStripeDePeliculasSeriesYProgramasEmitidos() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("castName"));
        this.searchPageAction.displayCastResults(Hooks.props.titleContentIsNotFavorite());
    }
    @Y("El usuario realiza una busqueda aplicando un filtro por género")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroPorGenero() {
        this.searchPageAction.searchContentWithFilter("Género");
    }

    @Entonces("Se muestran los resultados de la busqueda por filtro realizada")
    public void seMuestranLosResultadosDeLaBusquedaPorFiltroRealizada() {
        this.searchPageAction.checkResultsSearchFilters("1");
    }

    @Y("El usuario realiza una busqueda aplicando un filtro por Canales")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroPorCanales() {
        this.searchPageAction.searchContentWithFilter("Canales");
    }

    @Y("El usuario realiza una busqueda aplicando un filtro por Audiencia")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroPorAudiencia() {
        this.searchPageAction.searchContentWithFilter("Audiencia");
    }

    @Y("El usuario realiza una busqueda aplicando un filtro por Año de lanzamiento")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroPorAnoDeLanzamiento() {
        this.searchPageAction.searchContentWithFilter("Año de lanzamiento");
    }

    @Y("El usuario realiza una busqueda aplicando un filtro por Precio")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroPorPrecio() {
        this.searchPageAction.searchContentWithFilter("Precio");
    }

    @Y("El usuario realiza una busqueda aplicando un filtro de cada tipo")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltroDeCadaTipo() {
        this.searchPageAction.applyAllFilters();
    }

    @Entonces("Se muestran los resultados de la busqueda con todos los filtros")
    public void seMuestranLosResultadosDeLaBusquedaConTodosLosFiltros() {
        this.searchPageAction.checkResultsSearchFilters("6");
    }

    @Entonces("Se Visualizan los filtros aplicados")
    public void seVisualizanLosFiltrosAplicados() {
        this.searchPageAction.viewFilters();
    }

    @Y("El usuario realiza una busqueda aplicando un filtro")
    public void elUsuarioRealizaUnaBusquedaAplicandoUnFiltro() {
        this.searchPageAction.accessFilters();
        this.searchPageAction.applyFilter("Género", "Comedia");
    }

    @Y("Accede a un DIP del resultado de la busqueda")
    public void accedeAUnDIPDelResultadoDeLaBusqueda() {
        this.searchPageAction.accessDipResult();
    }

    @Entonces("El contenido coicide con el filtro aplicado")
    public void elContenidoCoicideConElFiltroAplicado() {
        this.searchPageAction.checkFilterDip();
    }

    @Y("Elimina el filtro aplicado")
    public void eliminaElFiltroAplicado() {
        this.searchPageAction.removeFilter();
    }

    @Entonces("El filtro se elimina correctamente")
    public void elFiltroSeEliminaCorrectamente() {
        this.searchPageAction.checkRemoveFilter();
    }

    @Y("Accede a un DIP del resultado de la búsqueda 'TV en vivo'")
    public void accedeAUnDIPDelResultadoDeLaBusquedaTVEnVivo() {
        this.searchPageAction.accessLiveContentSearch();
    }

    @Entonces("Visualiza los resultados en el stripe de 'Guía de TV'")
    public void visualizaLosResultadosEnElStripeDeGuiaDeTV() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("castName"));
        this.searchPageAction.displayCastResults(this.searchPageFlow.searchStripeGuiaDeTv, this.searchPageFlow.contentSearchTitle);
    }

    @Y("Selecciona un integrante del elenco dentro del DIP del resultado de la búsqueda")
    public void seleccionaUnIntegranteDelElencoDentroDelDIPDelResultadoDeLaBusqueda() throws AWTException {
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                this.searchPageAction.accessLiveContentSearch();

                Hooks.contentData.put("castName", this.vodDetailInfoPageAction.getCastName());
                this.searchPageFlow.contentSearchTitle = this.vodDetailInfoPageFlow.vodTitle.getText();
                this.vodDetailInfoPageAction.clickActorButton(0);
                return true;
            } catch (Throwable throwable) {
                this.elUsuarioRealizaUnaBusquedaDeUnContenidoTvEnVivo();
                return false;
            }
        });
    }

    @Y("El usuario accede al DIP del contenido 'TV en vivo' anteriormente buscado")
    public void elUsuarioAccedeAlDIPDelContenidoTVEnVivoAnteriormenteBuscado() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("castName"));
        this.searchPageAction.accessToCastResults(this.searchPageFlow.searchStripeGuiaDeTv, this.searchPageFlow.contentSearchTitle);
    }

    @Y("Accede a la ficha técnica del contenido anterior")
    public void accedeALaFichaTecnicaDelContenidoAnterior() {
        this.searchPageAction.checkSearchResult(Hooks.contentData.get("castName"));
        this.searchPageAction.accessToCastResults(this.searchPageFlow.stripePeliculasSeriesYProgramas, Hooks.props.titleContentIsNotFavorite());
    }

    @Cuando("El usuario selecciona un integrante del elenco")
    public void elUsuarioSeleccionaUnIntegranteDelElenco() throws AWTException{
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                Hooks.contentData.put("castName", this.vodDetailInfoPageAction.getCastName());
                this.vodDetailInfoPageAction.clickActorButton(0);
                return true;
            } catch (Throwable throwable) {
                this.elUsuarioRealizaUnaBusquedaDeUnContenidoTvEnVivo();
                this.searchPageAction.accessLiveContentSearch();
                return false;
            }
        });
    }

    @Y("El usuario aplica un filtro de busqueda en el GOP 'Adultos'")
    public void elUsuarioAplicaUnFiltroDeBusquedaEnElGOPAdultos() {
        this.searchPageAction.accessFilters();
        this.searchPageAction.applyFilter("Género", "XXX Rapiditos");
    }

    @Entonces("El DIP coicide con el filtro de contenido 'Adulto' aplicado")
    public void elDipCoicideConElFiltroDeContenidoAdultoAplicado() {
        this.searchPageAction.checkFilterDipAdult();
    }

    @Y("El usuario aplica un filtro de cada tipo en el GOP 'Adultos'")
    public void elUsuarioAplicaUnFiltroDeCadaTipoEnElGOPAdultos() {
        this.searchPageAction.applyAllFiltersAdult();
    }

    @Entonces("Se muestran los filtros aplicados")
    public void seMuestranLosFiltrosAplicados() {
        this.searchPageAction.viewFiltersAdult();
    }

    @Entonces("Se visualizan los resultados de la busqueda con todos los filtros")
    public void seVisualizanLosResultadosDeLaBusquedaConTodosLosFiltros() {
        this.searchPageAction.checkResultsSearchFilters("2");
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido tipo 'Película gratuita' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoTipoPeliculaGratuitaATravesDeUnaBusqueda() {
        this.elUsuarioRealizaUnaBusquedaDeUnContenidoPeliculaGratuita();
        this.searchPageAction.accessContentSearchResult("Películas, series y programas");
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido tipo 'Series' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoTipoSeriesATravesDeUnaBusqueda() {
        this.elUsuarioRealizaUnaBusquedaDeUnContenidoSeries();
        this.searchPageAction.accessContentSearchResult("Películas, series y programas");
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido tipo 'Alquiler' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoTipoAlquilerATravesDeUnaBusqueda() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesToRental(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.commonPageAction.searchContent(content.getTitle());
        this.searchPageAction.accessFirstItemFromVodSearchResults("Películas, series y programas emitidos");
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Película infantil gratuita' a traves de una búsqueda")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoPeliculaInfantilGratuitaATravesDeUnaBusqueda() {
        var content = Hooks.dataProvider.getMovieFreeChildren(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        searchPageAction.searchContent(content.getTitle());
        searchTitle = UtilsString.getStringFirstWords(content.getTitle(), 5);
        this.commonPageAction.searchContent(searchTitle);
        this.searchPageAction.accessContentSearchResult("Películas, series y programas");
    }
}
