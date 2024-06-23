package flow.stepDefinitions;

import flow.pageActions.*;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

public class MyContentDefinitions {

    protected MyContentFavoritesPageAction myContentFavoritesPageAction;
    protected MyContentRecordingsPageActions myContentRecordingsPageActions;
    protected MyContentContinueWatchingPageAction myContentContinueWatchingPageAction;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected ContentPageAction contentPageAction;
    protected CommonPageAction commonPageAction;

    public MyContentDefinitions() {
        this.myContentFavoritesPageAction = new MyContentFavoritesPageAction(Hooks.getDriver());
        this.myContentRecordingsPageActions = new MyContentRecordingsPageActions(Hooks.getDriver());
        this.myContentContinueWatchingPageAction = new MyContentContinueWatchingPageAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.contentPageAction = new ContentPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
    }

    @Entonces("Se muestra el contenido seleccionado en la sección 'Favoritos'")
    public void seMuestraElContenidoSeleccionadoEnLaSeccionFavoritos() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
        this.commonPageAction.checkContentExists(Hooks.props.titleContentIsNotFavorite());
    }

    @Entonces("No se muestra el contenido seleccionado en la sección 'Favoritos'")
    public void noSeMuestraElContenidoSeleccionadoEnLaSeccionFavoritos() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
        this.commonPageAction.checkContentNotExists(Hooks.props.titleContentInFavorite());
    }

    @Entonces("La sección 'Mi lista' se muestra correctamente")
    public void laSeccionFavoritosSeMuestraCorrectamente() {
        this.myContentFavoritesPageAction.pageIsDisplayed(false);
    }

    @Entonces("Se muestra el canal seleccionado en la sección 'Favoritos'")
    public void seMuestraElCanalSeleccionadoEnLaSeccionFavoritos() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
        this.commonPageAction.checkChannelExists(Hooks.contentData.get("channelNumber"));
    }

    @Entonces("No se muestra el canal seleccionado en la sección 'Favoritos'")
    public void noSeMuestraElCanalSeleccionadoEnLaSeccionFavoritos() {
        this.commonPageAction.accessNavbarContentMyContent();
        this.commonPageAction.accessNavbarMyContentFavorites();
        this.commonPageAction.checkContentNotExists(Hooks.contentData.get("channelNumber"));
    }

    @Entonces("La sección grabaciones se muestra correctamente")
    public void laSeccionGrabacionesSeMuestraCorrectamente() {
        this.myContentRecordingsPageActions.pageIsDisplayed(false);
    }

    @Entonces("La sección continuar viendo se muestra correctamente")
    public void laSeccionContinuarViendoSeMuestraCorrectamente() {
        this.myContentContinueWatchingPageAction.pageIsDisplayed(Hooks.props.homePageContinueWatching(), true);
    }

    @Entonces("Se realiza la compra del contenido")
    public void seRealizaLaCompraDelContenido() {
        //TODO
    }
    @Cuando("El usuario reanuda la reproducción de un contenido sin finalizar")
    public void elUsuarioReanudaLaReproduccionDeUnContenidoSinFinalizar() {
        this.contentPageAction.clickRandomContentItem();
        this.vodDetailInfoPageAction.clickOnStartPlayer();
    }

    @Entonces("Los contenidos se visualizan correctamente")
    public void losContenidosSeVisualizanCorrectamente() {
        this.myContentContinueWatchingPageAction.pageIsDisplayed(Hooks.props.homePageContinueWatching(), true);
        this.myContentContinueWatchingPageAction.contentsAreDisplayed();
    }


    @Cuando("El usuario selecciona 'Explorar contenidos'")
    public void elUsuarioSeleccionaExplorarContenidos() {
        this.myContentContinueWatchingPageAction.selectExploreContents();
    }

    @Entonces("La sección 'Mis Canales' se muestra correctamente")
    public void laSecciónMisCanalesSeMuestraCorrectamente() {
        this.myContentFavoritesPageAction.pageIsDisplayed(false);
    }

}
