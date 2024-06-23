package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.PlayerPageAction;
import flow.pageActions.TvGuidePageAction;
import flow.pageActions.VodDetailInfoPageAction;
import flow.pageObjects.TvGuidePageFlow;
import flow.utils.UtilsRandom;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.awt.*;

public class TvGuideDefinitions {

    protected PlayerPageAction playerPageAction;
    protected TvGuidePageAction tvGuidePageAction;
    protected CommonPageAction commonPageAction;
    protected String channelNumber;
    protected int randomEpgChannel;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected TvGuidePageFlow tvGuidePageFlow;

    public TvGuideDefinitions() {
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.tvGuidePageAction = new TvGuidePageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.tvGuidePageFlow = new TvGuidePageFlow(Hooks.getDriver());
    }

    @Entonces("El contenido de TV se reproduce correctamente")
    public void elContenidoDeTVSeReproduceCorrectamente() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.isPlaying();
    }

    @Entonces("El contenido de TV vivo se reproduce correctamente")
    public void elContenidoDeTVVicoSeReproduceCorrectamente() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.isPlaying();
    }

    @Entonces("El canal se reproduce correctamente")
    public void elCanalSeReproduceCorrectamente() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Otro contenido de TV se reproduce correctamente")
    public void otroContenidoDeTVSeReproduceCorrectamente() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.isPlaying();
    }

    @Entonces("La pantalla de 'Guía de Tv' se muestra correctamente")
    public void laPantallaDeGuiaDeTvSeMuestraCorrectamente() {
        this.tvGuidePageAction.pageIsDisplayed();
    }

    @Cuando("El usuario agrega un canal a Favoritos")
    public void elUsuarioAgregaUnCanalAFavoritos() {
        Hooks.contentData.put("channelNumber", this.tvGuidePageAction.getRandomChannelNumberFromGrid());
        this.tvGuidePageAction.scrollToEpgGridRow(this.tvGuidePageAction.getRandomChannelNumberFromGrid());
        this.tvGuidePageAction.setFavoriteActiveChannel();
    }

    @Cuando("El usuario quita un canal de Favoritos")
    public void elUsuarioQuitaUnCanalDeFavoritos() {
        Hooks.contentData.put("channelNumber", this.tvGuidePageAction.getRandomChannelNumberFromGrid());
        this.tvGuidePageAction.scrollToEpgGridRow(this.tvGuidePageAction.getRandomChannelNumberFromGrid());

        this.tvGuidePageAction.unsetFavoriteActiveChannel();
    }

    @Cuando("El usuario selecciona un contenido de la 'Guía de programación'")
    public void elUsuarioSeleccionaUnContenidoDeLaGuiaDeProgramacion() {
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario selecciona un contenido con subtitulos de la 'Guía de programación'")
    public void elUsuarioSeleccionaUnContenidoConSubtitulosDeLaGuiaDeProgramacion() {
        this.tvGuidePageAction.scrollToChannel();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se muestran los elementos del reproductor del contenido 'Guía de Tv' seleccionado")
    public void seMuestranLosElementosDelReproductorDelContenidoGuiaDeTvSeleccionado() {
        this.playerPageAction.tvLivePlayerIsDisplayed();
    }

    @Entonces("Se muestran los elementos del reproductor del contenido filtrado por fecha pasada")
    public void seMuestranLosElementsDelReproductorDelContenidoFiltradoPorFechaPasada() {
        this.playerPageAction.tvLivePlayerIsDisplayed();
    }

    @Cuando("El usuario aplica filtros 'Por fecha pasada'")
    public void elUsuarioAplicaFiltrosPorFechaPasada() {
        this.tvGuidePageAction.selectYesterdayDayEpgProgram();
    }

    @Entonces("Se muestran los elementos que coinciden con los filtros 'por fecha pasada' seleccionados")
    public void seMuestranLosElementosQueCoincidenConLosFiltrosPorFechaPasadaSeleccionados() {
        this.tvGuidePageAction.checkEpgElements();
    }

    @Cuando("El usuario aplica el filtro para fecha pasada")
    public void elUsuarioAplicaElFiltroParaFechaPasada() {
        this.tvGuidePageAction.selectYesterdayDayEpgProgram();
        this.tvGuidePageAction.checkEpgElements();
    }

    @Cuando("El usuario aplica filtros 'Por fecha futura'")
    public void elUsuarioAplicaFiltrosPorFechaFutura() {
        this.tvGuidePageAction.selectTomorrowDayEpgProgram();
    }

    @Entonces("Se muestran los elementos que coinciden con los filtros 'por fecha futura' seleccionados")
    public void seMuestranLosElementosQueCoincidenConLosFiltrosPorFechaFuturaSeleccionados() {
        this.tvGuidePageAction.checkEpgElements();
    }

    @Cuando("El usuario accede a los filtros 'Por fecha'")
    public void elUsuarioAccedeALosFiltrosPorFecha() {
        this.tvGuidePageAction.checkEpgGridDaySelectorDropdown();
    }

    @Entonces("Se muestran los filtros de programación 'por fecha'")
    public void seMuestranLosFiltrosDeProgramacionPorFecha() {
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario selecciona un canal de la 'Guía de programación'")
    public void elUsuarioSeleccionaUnCanalDeLaGuiaDeProgramacion() {
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se muestran elementos restringidos en la programación")
    public void seMuestranElementosRestringidosEnLaProgramacion() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.checkEpgRestrictedElements();
    }

    @Cuando("El usuario accede a los filtros 'Por categoría'")
    public void elUsuarioAccedeALosFiltrosPorCategoria() {
        this.tvGuidePageAction.checkEpgFiltersAmount();
    }

    @Entonces("Se muestran los filtros de programación 'por categoría'")
    public void seMuestranLosFiltrosDeProgramacionPorCategoria() {
        this.tvGuidePageAction.filterChannelsEpgDisplayed();
    }

    @Entonces("La 'guía de programación' se muestra correctamente")
    public void laGuiaDeProgramacionSeMuestraCorrectamente() {
        this.tvGuidePageAction.checkEpgFiltersAmount();
        this.tvGuidePageAction.checkEpgElements();
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
    }

    @Entonces("La ficha técnica del contenido ya emitido se muestra correctamente")
    public void laFichaTecnicaDelContenidoYaEmitidoSeMuestraCorrectamente() {
        this.tvGuidePageAction.checkEpgFiltersAmount();
        this.tvGuidePageAction.checkEpgElements();
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario aplica filtros 'Por categoría'")
    public void elUsuarioAplicaFiltrosPorCategoria() {
        this.tvGuidePageAction.checkEpgFiltersAmount();
        this.tvGuidePageAction.filterChannelsEpg("Deportes", "");
        this.tvGuidePageAction.filterChannelsEpg("Otros", "Noticias");
    }

    @Entonces("Se muestran los elementos que coinciden con los filtros 'por categoría' seleccionados")
    public void seMuestranLosElementosQueCoincidenConLosFiltrosPorCategoriaSeleccionados() {
        //TODO add check
    }

    @Cuando("El usuario selecciona el canal 11 de la 'Guía de programación'")
    public void elUsuarioSeleccionaElCanalADeLaGuiaDeProgramacion() {
        //TODO Doesn't make sense, please fix
        this.tvGuidePageAction.closeEpgWithBackButton();
    }

    @Cuando("El usuario accede a la 'Guía de Tv' desde el reproductor")
    public void elUsuarioAccedeALaGuiaDeTvDesdeElReproductor() {
        this.tvGuidePageAction.openEpg();
    }

    @Y("El usuario selecciona otro canal de la 'Guía de programación'")
    public void elUsuarioSeleccionaOtroCanalDeLaGuiaDeProgramacion() throws AWTException {
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Próximo a emitirse'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoProximoAEmitirse() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Próximo a emitirse' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoProximoAEmitirseSeleccionado() {
        //TODO next to be broadcast
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Ya emitido'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoYaEmitido() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomProgramAlreadyIssued();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'ya emitido' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoYaEmitidoSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelPast();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'En emisión'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoEnEmision() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'en emisión' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoEnEmisionSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelLive();
    }

    @Cuando("El usuario accede a contenido ya emitido en la 'Guía de Tv'")
    public void elUsuarioAccedeAContenidoYaEmitidoEnLaGuiaDeTv() {
        this.tvGuidePageAction.clickOnArrowLeftButton();
        this.tvGuidePageAction.clickOnArrowLeftButton();
        this.tvGuidePageAction.clickOnArrowLeftButton();
        this.tvGuidePageAction.clickOnArrowLeftButton();
        this.tvGuidePageAction.clickOnArrowLeftButton();
        this.tvGuidePageAction.clickOnFirstProgramPastActive();
        this.tvGuidePageAction.getProgram();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario accede a contenido aletorio ya emitido en la 'Guía de Tv'")
    public void elUsuarioAccedeAContenidoAletorioYaEmitidoEnLaGuiaDeTv() {
        this.tvGuidePageAction.selectYesterdayDayEpgProgram();
        this.tvGuidePageAction.navigateEpgGridScheduleNext();
        this.tvGuidePageAction.getRandomEpgProgram();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario accede a contenido ya emitido desde la 'Guía de Tv'")
    public void elUsuarioAccedeAContenidoYaEmitidoEnLaGuíaDeTv() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomProgramAlreadyIssued();
        this.tvGuidePageAction.getBtnDipEpgChannelPlay();
    }

    @Entonces("Se muestran los elementos del reproductor del contenido 'Catchup' seleccionado")
    public void seMuestranLosElementosDelReproductorDelContenidoCatchupSeleccionado() {
        this.playerPageAction.tvCatchupPlayerIsDisplayed();
    }

    @Cuando("El usuario se desplaza horizontalmente en la 'Guía de programación'")
    public void elUsuarioSeDesplazaHorizontalmenteEnLaGuiaDeProgramacion() {
        this.tvGuidePageAction.navigateEpgGridScheduleNext();
        this.tvGuidePageAction.navigateEpgGridSchedulePrev();
        this.tvGuidePageAction.navigateEpgGridScheduleNext();
        this.tvGuidePageAction.navigateEpgGridScheduleBack();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Tv en vivo' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoTvEnVivoSeleccionado() {
        this.tvGuidePageAction.tvDipPageIsDisplayed();
    }

    @Cuando("El usuario selecciona un canal 'Vivo' de la 'Guía de programación'")
    public void elUsuarioSeleccionaUnCanalVivoDeLaGuiaDeProgramacion() throws AWTException {
        randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
        this.tvGuidePageAction.waitSportStatisticsIsHidden();
    }

    @Cuando("El usuario accede a la mini EPG")
    public void elUsuarioAccedeALaMiniEPG() {
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
        this.tvGuidePageAction.openMiniEpg();
    }

    @Entonces("Entonces Se muestran los elementos de la mini EPG")
    public void entoncesSeMuestranLosElementosDeLaMiniEPG() {
        this.tvGuidePageAction.checkMiniEpgElements();
        this.tvGuidePageAction.closeMiniEpg();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario se desplaza verticalmente en la 'Guía de programación'")
    public void elUsuarioSeDesplazaVerticalmenteEnLaGuiaDeProgramacion() {
        this.tvGuidePageAction.scrollVerticalDown("20");
    }

    @Cuando("El usuario cambia de canal desde el reproductor")
    public void elUsuarioCambiaDeCanalDesdeElReproductor() {
        //TODO Add checks that the channel is changed, like TUF-17134
        this.tvGuidePageAction.scrollChannelsDownInPlayer(3);
        this.tvGuidePageAction.scrollChannelsUpInPlayer(3);
    }

    @Entonces("Se verifica el funcionamiento de las 'Estadisticas deportivas'")
    public void seVerificaElFuncionamientoDeLasEstadisticasDeportivas() {
        this.tvGuidePageAction.openSportStatistics();
        this.tvGuidePageAction.sportStatisticsIsDisplayed();
    }

    @Cuando("El usuario realiza zapping de todos los canales desde la 'mini EPG'")
    public void elUsuarioRealizaZappingDeTodosLosCanalesDesdeLaMiniEPG() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.playerPageAction.isPlaying();
        this.tvGuidePageAction.openMiniEpg();
        this.tvGuidePageAction.zappingAllChannelsMiniEpg("8");
    }

    @Entonces("Se reproducen correctamente todos los canales")
    public void seReproducenCorrectamenteTodosLosCanales() {
        this.tvGuidePageAction.checkAllChannelsZappingResult();
    }

    @Cuando("El usuario accede a un contenido restringido de la 'Guía de programación'")
    @Cuando("El usuario cambia de canal a otro canal restringido")
    public void elUsuarioAccedeAUnContenidoRestringidoDeLaGuiaDeProgramacion() {
        this.tvGuidePageAction.accessRandomRestrictedProgramFromEpg();
    }

    @Entonces("Se accede a la reproducción del contenido restringido seleccionado")
    public void seAccedeALaReproduccionDelContenidoRestringidoSeleccionado() {
        this.tvGuidePageAction.getBtnDipEpgChannelPlay();
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario cambia de canal desde la mini EPG")
    public void elUsuarioCambiaDeCanalDesdeLaMiniEPG() {
        int selectedChannel = this.tvGuidePageAction.getRandomChannelFromMiniEpg();
        this.tvGuidePageAction.selectChannelFromMiniEpg(selectedChannel);
    }

    @Entonces("Se reproduce correctamente el canal seleccionado")
    public void seReproduceCorrectamenteElCanalSeleccionado() {
        this.playerPageAction.isPlaying();
        //TODO Add check that the channel is the same the user selected previously
    }

    @Cuando("El usuario navega verticalmente la 'Guía de programación'")
    public void elUsuarioNavegaVerticalmenteLaGuiaDeProgramacion() {
        channelNumber = this.tvGuidePageAction.getRandomChannelNumberFromGrid();
        this.tvGuidePageAction.scrollToEpgGridRow(channelNumber);
        this.playerPageAction.isPlaying();
        this.tvGuidePageAction.goPageDownEpg(3);
        this.tvGuidePageAction.closeEpgWithBackButton();
        this.tvGuidePageAction.openEpg();
    }

    @Entonces("Se continúa reproduciendo el canal seleccionado previo a realizar la navegación")
    public void seContinuaReproduciendoElCanalSeleccionadoPrevioARealizarLaNavegacion() {
        this.tvGuidePageAction.checkEpgActiveChannelNumber(channelNumber, this.tvGuidePageAction.getActiveChannelFromGrid());
    }

    @Y("El usuario accede a contenido en vivo desde la 'Guía de Tv'")
    public void elUsuarioAccedeAContenidoEnVivoDesdeLaGuiaDeTv() throws AWTException {
        randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
    }

    @Cuando("El usuario cambia de canal desde la 'Guía de programación'")
    public void elUsuarioCambiaDeCanalDesdeLaGuiaDeProgramacion() {
        // Scroll down and check channel number and title are not equals
        String currentChannelEpgNumber = this.tvGuidePageAction.getActiveChannelNumberFromGrid();
        this.tvGuidePageAction.scrollChannelsDownInEpg(5);
        String newChannelEpgNumber = this.tvGuidePageAction.getActiveChannelNumberFromGrid();
        this.tvGuidePageAction.checkActiveChannelNumbersNotEquals(currentChannelEpgNumber, newChannelEpgNumber);

        // Scroll up and check channel number and title are equals
        this.tvGuidePageAction.scrollChannelsUpInEpg(5);
        newChannelEpgNumber = this.tvGuidePageAction.getActiveChannelNumberFromGrid();
        this.tvGuidePageAction.checkActiveChannelNumbersEquals(currentChannelEpgNumber, newChannelEpgNumber);
    }

    @Cuando("Cuando El usuario cambia de canal desde la 'mini EPG'")
    public void cuandoElUsuarioCambiaDeCanalDesdeLaMiniEPG() throws InterruptedException, AWTException {
        randomEpgChannel = UtilsRandom.getRandom(10, 20);
        this.tvGuidePageAction.scrollChannelsDownInMiniEpg(randomEpgChannel);
        var currentChannelMiniEpgNumber = this.tvGuidePageAction.getActiveChannelFromMiniEpg();
        var currentChannelPlayerNumber = this.playerPageAction.getPlayerChannelNumber();
        this.tvGuidePageAction.getChannelInMiniEPG(randomEpgChannel, currentChannelMiniEpgNumber);
        this.tvGuidePageAction.checkChannelsInMiniEPG(randomEpgChannel, currentChannelMiniEpgNumber, currentChannelPlayerNumber);
    }

    @Cuando("El usuario abre 'Guía de TV' en una nueva pestaña y reproduce")
    public void elUsuarioAbreGuiaDeTVEnUnaNuevaPestanaYReproduce() {
        this.commonPageAction.createNewTab();
        this.playerPageAction.isPlaying();
        this.commonPageAction.switchToNewTab(1);
        this.commonPageAction.closeCurrentTab();
        this.commonPageAction.switchToNewTab(0);
    }

    @Y("El usuario reinicia la reproducción del contenido 'Vivo' desde DIP")
    public void elUsuarioReiniciaLaReproduccionDelContenidoVivoDesdeDIP() {
        this.tvGuidePageAction.openEpg();
        this.tvGuidePageAction.selectLiveChannel();
        this.tvGuidePageAction.restartEpgChannel();
    }

    @Y("El usuario vuelve atras")
    public void elUsuarioVuelveAtras() {
        this.playerPageAction.clickBackButton();
    }

    @Y("El usuario vuelve a la 'Guía de TV'")
    public void elUsuarioVuelveALaGuiaDeTv() {
        this.playerPageAction.clickBackButton();
        this.commonPageAction.accessNavbarElementTvGuide();
    }

    @Y("El usuario accede a la ficha técnica del contenido alquilado")
    public void elUsuarioAccedeALaFichaTecnicaDelContenidoAlquilado() {
        this.playerPageAction.clickBackButton();
    }

    @Entonces("Se muestra el programa seleccionado en la sección 'Grabaciones'")
    public void seMuestraElProgramaSeleccionadoEnLaSeccionGrabaciones() {
        this.commonPageAction.clickThisEmission();
        this.commonPageAction.clickNavbarContentMyContent();
        this.commonPageAction.clickNavbarRecordings();
    }

    @Y("El usuario accede a un contenido del stripe 'Tv en vivo'")
    public void elUsuarioAccedeAUnContenidoDelStripeTvEnVivo() throws AWTException {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
    }

    @Cuando("El usuario reinicia la reproducción del contenido desde el DIP")
    public void elUsuarioReiniciaLaReproduccionDelContenidoDesdeElDIP() {
        this.tvGuidePageAction.restartChannelLiveDIP();
    }

    @Cuando("El usuario visualiza contenido ya emitido en la 'Guía de Tv'")
    public void elUsuarioVisualizaContenidoYaEmitidoEnLaGuiaDeTv() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomProgramAlreadyIssued();
    }

    @Entonces("Se muestran contenidos ya emitidos en la programación")
    public void seMuestranContenidosYaEmitidosEnLaProgramacion() {
        this.tvGuidePageAction.checkDipElementsChannelPast();
    }

    @Y("El usuario accede a la función 'Ver todo' que se encuentra al final del stripe 'Tv en vivo'")
    public void elUsuarioAccedeALaFuncionVerTodoQueSeEncuentraAlFinalDelStripeTvEnVivo() {
        this.tvGuidePageAction.clickViewGuide();
    }

    @Entonces("Se visualizan los elementos de la EPG")
    public void seVisualizanLosElementosDeLaEPG() {
        this.tvGuidePageAction.checkElementsEPG();
    }

    @Cuando("El usuario accede al stripe 'Tv en vivo'")
    public void elUsuarioAccedeAlStripeTvEnVivo() {
        this.tvGuidePageAction.accessToStripeTVLive();
    }

    @Y("El usuario intenta desactivar el control parental con un PIN incorrecto")
    public void elUsuarioIntentaDesactivarElControlParentalConUnPinIncorrecto() {
//        this.commonPageAction.accessParentalControl();
        this.commonPageAction.enterParentalControlPinAndConfirm(Hooks.props.incorrectPinParental());
    }

    @Cuando("El usuario graba un contenido proximo a emitirse")
    public void elUsuarioGrabaUnContenidoProximoAEmitirse() {
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS HD");
        this.tvGuidePageAction.recordProgram();
    }

    @Entonces("La grabación ya no se encuentra disponible")
    public void laGrabacionYaNoSeEncuentraDisponible() {
        this.tvGuidePageAction.checkRecordingIsNotAvailable();
    }

    @Y("El usuario cancela la grabación desde grabar 'Esta emision'")
    public void cancelaLaGrabacionDesdeGrabarEstaEmision() throws InterruptedException {
        this.tvGuidePageAction.selectThisEmissionButton();
        this.tvGuidePageAction.pressCancelButton();
    }

    @Y("El usuario elimina la grabación")
    public void eliminaLaGrabacion() {
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS HD");
        this.tvGuidePageAction.pressCancelButton();
        this.tvGuidePageAction.pressDeleteButtonRecord();
    }

    @Y("El usuario accede a la sección 'Emisiones'")
    public void elUsuarioAccedeALaSecciónEmisiones() {
        this.tvGuidePageAction.accessEmissionsSection();
    }

    @Entonces("La sección 'Emisiones' se muestra correctamente")
    public void laSecciónEmisionesSeMuestraCorrectamente() {
        this.tvGuidePageAction.emissionsSectionIsDisplayed();
    }

    @Cuando("El usuario quita un programa actual de Grabaciones agregado previamente")
    public void elUsuarioQuitaUnProgramaActualDeGrabacionesAgregadoPreviamente() throws AWTException {
        randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.playerPageAction.clickEpgProgramLive();
        this.playerPageAction.clickEpgRecordProgram();
        this.commonPageAction.clickThisEmission();
        this.playerPageAction.removeRecording();
    }

    @Entonces("No se muestra el programa seleccionado en la sección 'Grabaciones'")
    public void noSeMuestraElProgramaSeleccionadoEnLaSecciónGrabaciones() {
        this.playerPageAction.contentNotDisplayedInRecordingsSection();
    }

    @Y("El usuario selecciona 'Grabar'")
    public void elUsuarioSeleccionaGrabar() {
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectButtonRecord();
    }

    @Entonces("Se visualizan las opciones de grabación correctamente")
    public void seVisualizanLasOpcionesDeGrabacionCorrectamente() {
        this.tvGuidePageAction.recordingOptionsIsDisplayed();
    }

    @Y("Selecciona que se desea grabar 'Todas las emisiones'")
    public void seleccionaQueSeDeseaGrabarTodasLasEmisiones() {
        this.tvGuidePageAction.recordAllEmissions();
    }

    @Entonces("La grabación se encuentra disponible")
    public void laGrabacionSeEncuentraDisponible() {
        this.tvGuidePageAction.recordingIsDisplayed();
    }

    @Cuando("El usuario borra un programa actual en grabación")
    public void elUsuarioBorraUnProgramaActualEnGrabación() {
        this.tvGuidePageAction.pressCurrentProgram("TV PUBLICA HD");
        this.tvGuidePageAction.pressCancelButton();
        this.tvGuidePageAction.pressDeleteButtonRecord();
    }

    @Entonces("La grabación se detiene correctamente")
    public void laGrabacionSeDetieneCorrectamente() {
        this.tvGuidePageAction.checkRecordingIsNotAvailable();
    }

    @Cuando("El usuario graba 'Todas las emisiones' de un programa proximo a emitirse desde la EPG")
    public void elUsuarioGrabaTodasLasEmisionesDeUnProgramaProximoAEmitirseDesdeLaEPG() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS LA PLATA HD");
        this.tvGuidePageAction.recordProgram();
        this.tvGuidePageAction.recordAllBroadcasts();
    }

    @Y("El usuario borra la grabación de 'Todas las emisiones' del programa proximo a emitirse")
    public void elUsuarioQuitaLaGrabaciónTodasLasEmisionesDelProgramaProximoAEmitirse() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS LA PLATA HD");
        this.tvGuidePageAction.removeRecordingNextProgramToEmit();
    }

    @Cuando("El usuario graba 'Esta emision' de un programa proximo a emitirse desde la EPG")
    public void elUsuarioGrabaEstaEmisionDeUnProgramaProximoAEmitirseDesdeLaEPG() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS HD");
        this.tvGuidePageAction.recordProgram();
        this.tvGuidePageAction.recordThisEmissions();
    }

    @Y("El programa proximo a emitirse se muestra correctamente en 'Grabaciones'")
    public void elProgramaProximoAEmitirseSeMuestraCorrectamenteEnGrabaciones() {
        this.tvGuidePageAction.recordedProgramIsDisplayedInRecordings();
    }

    @Y("El usuario borra la grabación de 'Esta emision' del programa proximo a emitirse")
    public void elUsuarioBorraLaGrabacionDelProgramaProximoAEmitirse() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressContentNextEmissions("SOMOS HD");
        this.tvGuidePageAction.removeRecordingNextProgramToEmit();
    }

    @Entonces("No se muestra el programa proximo a emitirse seleccionado en la sección 'Grabaciones'")
    public void noSeMuestraElProgramaProximoAEmitirseSeleccionadoEnLaSecciónGrabaciones() {
        this.tvGuidePageAction.theRecordingIsNotAvailable();
    }

    @Cuando("El usuario graba 'Todas las emisiones' de un programa actual desde la EPG")
    public void elUsuarioGrabaTodasLasEmisionesDeUnProgramaActualDesdeLaEPG() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressCurrentProgram("AMERICA 2 HD");
        this.tvGuidePageAction.recordProgram();
        this.tvGuidePageAction.recordAllBroadcasts();
    }

    @Y("El programa actual grabado se muestra correctamente en 'Grabaciones'")
    public void elProgramaActualGrabadoSeMuestraCorrectamenteEnGrabaciones() {
        this.tvGuidePageAction.recordedProgramIsDisplayedInRecordings();
    }

    @Y("El usuario borra la grabación de 'Todas las emisiones' de un programa actual")
    public void elUsuarioBorraLaGrabaciónTodasLasEmisionesDeUnProgramaActual() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressCurrentProgram("AMERICA 2 HD");
        this.tvGuidePageAction.removeRecordingNextProgramToEmit();
    }

    @Entonces("No se muestra el programa actual seleccionado en la sección 'Grabaciones'")
    public void noSeMuestraElProgramaActualSeleccionadoEnLaSecciónGrabaciones() {
        this.tvGuidePageAction.theRecordingIsNotAvailable();
    }

    @Cuando("El usuario graba 'Esta emision' de un programa actual desde la EPG")
    public void elUsuarioGrabaUnProgramaActualDesdeLaEPG() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.pressCurrentProgram("METRO");
        this.tvGuidePageAction.recordProgram();
        this.commonPageAction.clickThisEmission();
    }

    @Y("El usuario borra la grabación de 'Esta emision' de un programa actual")
    public void elUsuarioQuitaLaGrabaciónDeUnProgramaActual() {
        this.tvGuidePageAction.removeRecordingFromCurrentProgram();
    }

    @Cuando("Graba 'Todas las emisiones' de un contenido desde la Guía de TV")
    public void grabaTodasLasEmisionesDeUnContenidoDesdeLaGuiaDeTV() throws AWTException {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.clickAllBroadcasts();
    }

    @Cuando("Graba 'Esta emision' de un contenido desde la Guía de TV")
    public void grabaEstaEmisionUnContenidoDesdeLaGuiaDeTV() throws AWTException {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.recordThisBroadcast();

    }

    @Entonces("Se muestra mensaje indicando que no posee espacio disponible para grabaciones")
    public void seMuestraMensajeIndicandoQueNoPoseeEspacioDisponibleParaGrabaciones() {
        this.tvGuidePageAction.recordingLimitIsDisplayed();
    }

    @Entonces("^El usuario selecciona el canal (.*) - (.*)")
    public void elusuarioSeleccionaElCanal(String number, String title) {
        this.vodDetailInfoPageAction.openTvGuideChannel(number);
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Cuando("El usuario reproduce un contenido 'Vivo' no restringido")
    public void elUsuarioReproduceUnContenidoVivoNoRestringido(){
        this.vodDetailInfoPageAction.openTvGuideChannel("11");
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Cuando("El usuario selecciona un contenido de la 'Guía de programación' con subtitulos")
    public void elUsuarioSeleccionaUnContenidoDeLaGuiaDeProgracionConSubtitulos(){
        this.vodDetailInfoPageAction.openTvGuideChannel("11");
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Cuando("El usuario selecciona un contenido premium de la 'Guía de programación' con subtitulos")
    public void elUsuarioSeleccionaUnContenidoPremiunDeLaGuiaDeProgracionConSubtitulos(){
        this.vodDetailInfoPageAction.openTvGuideChannel("252");
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Película gratuita' con audio chino activado")
    public void elUsuarioAccedeAUnContenidoDeTipoPeliculaGratuitaConAudioChinoActivado() {
        this.vodDetailInfoPageAction.openVodUrlWithChineseAudio();
        this.playerPageAction.activateAudioChinese();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Serie' con audio aleman activado")
    public void elUsuarioAccedeAUnContenidoDeTipoSerieConAudioAlemanActivado() {
        this.vodDetailInfoPageAction.openVodUrlWithAlemanAudio();
        this.playerPageAction.activateAudioGerman();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Película gratuita' con audio frances activado")
    public void elUsuarioAccedeAUnContenidoDeTipoPeliculaGratuitaConAudioFrancesActivado() {
        this.vodDetailInfoPageAction.openVodUrlWithFrenchAudio();
        this.playerPageAction.activateAudioFrench();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Película gratuita' con audio coreano activado")
    public void elUsuarioAccedeAUnContenidoDeTipoPeliculaGratuitaConAudioCoreanoActivado() {
        this.vodDetailInfoPageAction.openVodUrlWithKoreanAudio();
        this.playerPageAction.activateAudioKorean();
    }

    @Cuando("El usuario accede a un contenido de tipo 'Serie' con audio italiano activado")
    public void elUsuarioAccedeAUnContenidoDeTipoSerieConAudioItalianoActivado() {
        this.vodDetailInfoPageAction.openVodUrlWithItalianAudio();
        this.playerPageAction.activateAudioItalian();
    }

    @Cuando("^El usuario vuelve a reproducir el mismo canal (.*) - (.*)")
    public void elUsuarioVuelveAReproducirElMismoCanal(String number, String title) {
        this.vodDetailInfoPageAction.openTvGuideChannel(number);
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Y("El usuario selecciona un canal con contenido vivo")
    public void elUsuarioSeleccionUnCanalConContenidoVivo() {
        this.vodDetailInfoPageAction.openTvGuideChannel("10");
        this.tvGuidePageAction.clickOnPlayButton();
    }

    @Y("El usuario vuelve a la 'Guía de programación'")
    public void elUsuarioVuelveALaGuiaDeProgramacion() {
        this.playerPageAction.clickBackButton();
    }

    @Cuando("El usuario accede al mini DIP de un contenido del tipo 'En emisión'")
    public void elUsuarioAccedeAlMiniDIPDeUnContenidoDelTipoEnEmision() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
    }

    @Cuando("El usuario graba 'Esta emision' de un programa actual")
    public void elUsuarioGrabaEstaEmisionDeUnProgramaActual() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.recordThisBroadcast();
    }

    @Y("El usuario selecciona 'Cancelar' luego de visualizar el mensaje de limite alcanzado")
    public void elUsuarioSeleccionaCancelar() {
        this.tvGuidePageAction.recordingLimitDisplayingCorrectly();
        this.tvGuidePageAction.pressCancelButton();
    }

    @Entonces("No se realiza la grabacion del contenido")
    public void noSeRealizaLaGrabacionDelContenido() {
        this.tvGuidePageAction.recordingCancelled();
    }

    @Cuando("El usuario graba 'Todas las emisiones' de un programa actual")
    public void elUsuarioGrabaTodasLasEmisionesDeUnProgramaActual() throws AWTException {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.clickAllBroadcasts();
    }

    @Cuando("El usuario realiza una grabación de 'Todas las emisiones' de un programa actual visualizando el mensaje de limite")
    public void elUsuarioRealizaUnaGrabacionDeTodasLasEmisionesDeUnProgramaActualVisualizandoMensaje() throws AWTException {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.clickAllBroadcasts();
        this.tvGuidePageAction.recordingLimitDisplayingCorrectly();
        this.tvGuidePageAction.confirmRecording();
    }

    @Cuando("El usuario cancela la grabación programada")
    public void elUsuarioCancelaLaGrabacionTodasLasEmisionesDelProgramaActualSeleccionado() {
        this.tvGuidePageAction.checkRecordingAllBroadcasts();
        this.tvGuidePageAction.cancelRecording();
    }

    @Entonces("La grabación se cancela correctamente")
    public void laGrabacionSeCancelaCorrectamente() {
        this.tvGuidePageAction.checkRecordingCancelled();
    }

    @Cuando("El usuario realiza una grabación de 'Esta emisión' de un programa actual visualizando el mensaje de limite")
    public void elUsuarioRealizaUnaGrabacionDeEstaEmisionDeUnProgramaActualVisualizandoElMensajelDeLimite() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.recordThisBroadcast();
        this.tvGuidePageAction.recordingLimitDisplayingCorrectly();
        this.tvGuidePageAction.confirmRecording();
    }

    @Cuando("El usuario graba 'Todas las emisiones' de un programa proximo a emitirse")
    public void elUsuarioGrabaTodasLasEmisionesDeUnProgramaProximoAEmitirse() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.clickAllBroadcasts();
    }

    @Cuando("El usuario graba 'Esta emisión' de un programa proximo a emitirse")
    public void elUsuarioGrabaEstaEmisionDeUnProgramaProximoAEmitirse() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.recordThisBroadcast();
    }

    @Cuando("El usuario realiza una grabación de 'Todas las emisiones' de un programa proximo a emitirse visualizando el mensaje de limite")
    public void elUsuarioRealizaUnaGrabacionDeTodasLasEmisionesDeUnProgramaProximoAEmitirseVisualizandoElMensajeDeLimite() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.clickAllBroadcasts();
        this.tvGuidePageAction.recordingLimitDisplayingCorrectly();
        this.tvGuidePageAction.confirmRecording();
    }

    @Cuando("El usuario realiza una grabación de 'Esta emision' de un programa proximo a emitirse visualizando el mensaje de limite")
    public void elUsuarioRealizaUnaGrabacionDeEstaEmisionDeUnProgramaProximoAEmitirseVisualizandoElMensjaeDeLimite() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
        this.tvGuidePageAction.recordIsDisplayed();
        this.tvGuidePageAction.selectRecord();
        this.tvGuidePageAction.recordThisBroadcast();
        this.tvGuidePageAction.recordingLimitDisplayingCorrectly();
        this.tvGuidePageAction.confirmRecording();
    }

    @Cuando("El usuario accede a la sección 'Emisiones' de un contenido del tipo 'En emisión'")
    public void elUsuarioAccedeALaSeccionEmisionesDeUnContenidoDelTipoEnEmisión() throws AWTException {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.tvGuidePageAction.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.tvGuidePageAction.selectRandomChannelLive();
        this.tvGuidePageAction.accessEmissionsSection();
    }

    @Entonces("Se muestran el boton 'Grabar' dentro de las emisiones")
    public void seMuestranElBotonGrabarDentroDeLasEmisiones() {
        this.tvGuidePageAction.displayRecordButtonInBroadcasts();
    }

    @Cuando("El usuario graba una emisión desde la sección 'Emisiones'")
    public void elUsuarioGrabaUnaEmisionDesdeLaSeccionEmisiones() throws AWTException {
        this.tvGuidePageAction.recordABroadcastFromBroadcasts();
    }

    @Entonces("Las emisiones se visualizan correctamente")
    public void lasEmisionesSeVisualizanCorrectamente() {
        this.tvGuidePageAction.viewEmissions();
    }

    @Cuando("El usuario accede a la sección 'Emisiones' de un contenido del tipo 'Próximo a emitirse'")
    public void elUsuarioAccedeALaSeccionEmisionesDeUnContenidoDelTipoProximoAEmitirse() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomFutureProgram();
        this.tvGuidePageAction.accessEmissionsSection();
    }

    @Cuando("El usuario accede a la sección 'Emisiones' de un contenido del tipo 'ya emitido'")
    public void elUsuarioAccedeALaSeccionEmisionesDeUnContenidoDelTipoYaEmitido() {
        this.tvGuidePageAction.waitEpgProgramLiveLoad();
        this.tvGuidePageAction.selectRandomProgramAlreadyIssued();
        this.tvGuidePageAction.accessEmissionsSection();
    }

    @Entonces("Se visualizan todas de secciones de la Guía")
    public void seVisualizanElTotalDeSeccionesDeLaGuia() {
        this.tvGuidePageAction.viewAllSectionsTvGuide();
    }

    @Cuando("El usuario accede a la sección 'Mis canales' de la Guía de TV")
    public void elUsuarioAccedeALaSeccionMisCanalesDeLaGuiaDeTV() {
        this.tvGuidePageAction.accessFiltersEpg(this.tvGuidePageFlow.filterFavorites);
    }

    @Entonces("Los canales favoritos se visualizan correctamente")
    public void losCanalesFavoritosSeVisualizanCorrectamente() {
        this.tvGuidePageAction.viewChannelsFavoritesEpg();
    }

    @Entonces("Se visualiza el mensaje indicando que no hay canales en favoritos")
    public void seVisualizaElMensajeIndicandoQueNoHayContenidosEnFavoritos() {
        this.tvGuidePageAction.viewMessageWithoutContentsSavedInFavorites();
    }

    @Cuando("El usuario accede a la sección 'Deportes' de la Guía de TV")
    public void elUsuarioAccedeALaSeccionDeportesDeLaGuiaDeTV() {
        this.tvGuidePageAction.accessFiltersEpg(this.tvGuidePageFlow.filterSports);
    }

    @Entonces("La seccion 'Deportes' se visualiza correctamente")
    public void laSeccionDeportesSeVisualizaCorrectamente() {
        this.tvGuidePageAction.sectionIsDisplayed("Deportes");
    }

    @Cuando("El usuario accede a un DIP de la sección 'Deportes' de la Guía de TV")
    public void elUsuarioAccedeAUnDIPDeLaSeccionDeportesDeLaGuiaDeTV() {
        this.tvGuidePageAction.accessDipOnFilter(this.tvGuidePageFlow.filterSports);
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Deportes' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoDeportesSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelLive();
    }

    @Cuando("El usuario accede a la sección 'Cine' de la Guía de TV")
    public void elUsuarioAccedeALaSeccionCineDeLaGuiaDeTV() {
        this.tvGuidePageAction.accessFiltersEpg(this.tvGuidePageFlow.filterCinema);
    }

    @Entonces("La seccion 'Cine' se visualiza correctamente")
    public void laSeccionCineSeVisualizaCorrectamente() {
        this.tvGuidePageAction.sectionIsDisplayed("Cine");
    }

    @Cuando("El usuario accede a un DIP de la sección 'Cine' de la Guía de TV")
    public void elUsuarioAccedeAUnDIPDeLaSeccionCineDeLaGuiaDeTV() throws AWTException {
        this.tvGuidePageAction.accessDipOnFilter(this.tvGuidePageFlow.filterCinema);
    }

    @Cuando("El usuario accede a la sección 'Aire' de la Guía de TV")
    public void elUsuarioAccedeALaSeccionAireDeLaGuiaDeTV() {
        this.tvGuidePageAction.accessFiltersEpg(this.tvGuidePageFlow.filtroAire);
    }

    @Entonces("La seccion 'Aire' se visualiza correctamente")
    public void laSeccionAireSeVisualizaCorrectamente() {
        this.tvGuidePageAction.sectionIsDisplayed("Aire");
    }

    @Cuando("El usuario accede a un DIP de la sección 'Aire' de la Guía de TV")
    public void elUsuarioAccedeAUnDIPDeLaSeccionAireDeLaGuiaDeTV() throws AWTException {
        this.tvGuidePageAction.accessDipOnFilter(this.tvGuidePageFlow.filtroAire);
    }

    @Cuando("El usuario accede a una sección del menu 'Otros'")
    public void elUsuarioAccedeAUnaSeccionDelMenuOtros() {
        this.tvGuidePageAction.accessOthersSectionEpg();
    }

    @Entonces("La seccion seleccionada se visualiza correctamente")
    public void laSeccionSeleccionadaSeVisualizaCorrectamente() {
        this.tvGuidePageAction.othersSectionIsDisplayed();
    }

    @Cuando("El usuario accede a un DIP de una sección del menu 'Otros'")
    public void elUsuarioAccedeAUnDIPDeUnaSeccionDelMenuOtros() {
        this.tvGuidePageAction.accessOthersDipFilter();
    }

    @Entonces("Se muestra la ficha técnica del contenido 'Otros' seleccionado")
    public void seMuestraLaFichaTecnicaDelContenidoOtrosSeleccionado() {
        this.tvGuidePageAction.checkDipElementsChannelLive();
    }
}