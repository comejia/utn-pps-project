package flow.stepDefinitions;

import flow.pageActions.*;
import flow.webdriverUtils.ExtendedWebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.awt.*;

public class PlayerDefinitions {

    protected PlayerPageAction playerPageAction;
    protected TvGuidePageAction tvGuidePageAction;
    protected ExtendedWebDriver extendedWebDriver;
    protected HomePageAction homePageFlowAction;

    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected ContentPageAction contentPageAction;

    private double currentTime;
    private double newTime;

    public PlayerDefinitions() {
        this.tvGuidePageAction = new TvGuidePageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.contentPageAction = new ContentPageAction(Hooks.getDriver());
        this.extendedWebDriver = new ExtendedWebDriver(Hooks.getDriver());
        this.homePageFlowAction = new HomePageAction(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
    }

    @Y("El usuario pausa la reproducción")
    public void elUsuarioPausaLaReproduccion() {
        this.playerPageAction.actionPlaybackPause();
        this.playerPageAction.isPaused();
    }

    @Y("El usuario finaliza la reproducción")
    public void elUsuarioFinalizaLaReproduccion() {
        this.playerPageAction.gotoToNextChannel();
    }

    @Y("El usuario cambia al proximo canal de la guia de programacion")
    public void elUsuarioCambiaAlProximoCanalDeLaGuiaDeProgramacion() {
        this.playerPageAction.gotoToNextChannel();
    }

    @Y("Usuario pausa la reproducción con Skip Intro")
    public void elUsuarioPausaLaReproduccionConSkipIntro() {
        this.playerPageAction.restartTimeOfPlayer();
        this.playerPageAction.waitSkipButtonIsDisplayed();
        this.playerPageAction.clickOnPlaybackPause();
        this.playerPageAction.isPaused();
    }

    @Y("El usuario pausa la reproducción con 'Skip Resume'")
    public void elUsuarioPausaLaReproduccionConSkipResume() {
        this.playerPageAction.restartTimeOfPlayer();
        this.playerPageAction.waitSkipResumeButtonIsDisplayed();
        this.playerPageAction.clickOnPlaybackPause();
        this.playerPageAction.isPaused();
    }

    @Entonces("El contenido se reproduce correctamente")
    public void elContenidoSeReproduceCorrectamente() {
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Entonces("El contenido ya alquilado se reproduce correctamente")
    public void elContenidoYaAlquiladoSeReproduceCorrectamente() {
        this.playerPageAction.basicCheckPlayerIsDisplayed();
    }

    @Entonces("Se muestran los elementos del reproductor")
    public void seMuestranLosElementosDelReproductor() {
        this.playerPageAction.playerIsDisplayed();
    }

    @Entonces("El contenido sigue reproduciendo correctamente")
    public void elContenidoSigueReproduceCorrectamente() {
        this.playerPageAction.isPlaying();
    }

    @Entonces("El contenido se reproduce correctamente desde el punto anterior")
    public void elContenidoSeReproduceCorrectamenteDesdeElPuntoAnterior() {
        this.playerPageAction.waitPlayerNotLoading();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se muestran los elementos del reproductor del contenido 'Película gratuita' seleccionado")
    public void seMuestranLosElementosDelReproductorDelContenidoPeliculaGratuitaSeleccionado() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se muestran los elementos del reproductor del contenido 'Ya alquilado' seleccionado")
    public void seMuestranLosElementosDelReproductorDelContenidoYaAlquidoSeleccionado() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se ocultan automáticamente los elementos del reproductor luego de unos segundos")
    public void seOcultanAutomaticamenteLosElementosDelReproductorLuegoDeUnosSegundos() {
        this.playerPageAction.waitPlayerLoadAndPlayback();
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.isPlaying();
        this.playerPageAction.playerIsNotDisplayed();
    }

    @Entonces("Se muestran los elementos del reproductor en pausa del contenido 'Película gratuita' seleccionado")
    public void seMuestranLosElementosDelReproductorEnPausaDelContenidoPeliculaGratuitaSeleccionado() {
        this.playerPageAction.actionPlaybackPlay();
    }

    @Entonces("Se muestran los elementos del reproductor pausado del contenido 'Guía de Tv' seleccionado")
    public void seMuestranLosElementosDelReproductorPausadoDelContenidoGuiaDeTvSeleccionado() {
        this.playerPageAction.playerPausedInfoIsDisplayed();
    }

    @Y("El usuario reinicia la reproducción del contenido 'Vivo'")
    public void elUsuarioReiniciaLaReproduccionDelContenidoVivo() {
        this.playerPageAction.actionProgramRestart();
    }

    @Entonces("Se reinicia la reproducción del contenido 'Vivo' seleccionado")
    public void seReiniciaLaReproduccionDelContenidoVivoSeleccionado() {
        this.playerPageAction.playerIsDisplayed();
    }

    @Cuando("El usuario utiliza la función 'Ahora' del reproductor")
    public void elUsuarioUtilizaLaFuncionAhoraDelReproductor() {
        this.playerPageAction.actionProgramNow();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se reproduce el contenido 'Vivo' al momento presente")
    public void seReproduceElContenidoVivoAlMomentoPresente() {
        this.playerPageAction.checkButtonProgramNowDisable();
        this.playerPageAction.playerIsDisplayed();
    }

    @Cuando("El usuario utiliza la función de 'adelantar y retroceder' del reproductor para contenido trailer")
    @Cuando("El usuario utiliza la función de 'adelantar y retroceder' del reproductor")
    public void elUsuarioUtilizaLaFuncionDeAdelantarYRetrocederDelReproductor() {
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.playerCurrentTimeForward();
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.playerCurrentTimeRewind();
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
    }

    @Cuando("El usuario utiliza la función de 'retroceder y adelantar' del reproductor")
    public void elUsuarioUtilizaLaFuncionDeRetrocederYAdelantarDelReproductor() {
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.playerCurrentTimeRewind();
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
        this.playerPageAction.playerCurrentTimeForward();
        this.playerPageAction.waitNoSeekingWithoutRefresh();
        this.playerPageAction.playerIsPlaying();
    }

    @Entonces("Se verifica el funcionamiento de 'adelantar y retroceder' del reproductor")
    public void seVerificaElFuncionamientoDeAdelantarYRetrocederDelReproductor() {
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se verifica el funcionamiento de 'retroceder y adelantar' del reproductor")
    public void seVerificaElFuncionamientoDeRetrocederYAdelantarDelReproductor() {
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario utiliza la barra de progreso del reproductor para 'adelantar y retroceder' el contenido")
    public void elUsuarioUtilizaLaBarraDeProgresoDelReproductorParaAdelantarYRetrocederElContenido() {
        this.playerPageAction.playerProgressBarSlideTo(60, "forward");
        this.playerPageAction.isPlaying();
        this.playerPageAction.playerProgressBarSlideTo(60, "rewind");
    }

    @Entonces("Se verifica el funcionamiento de la 'barra de progreso' del reproductor")
    public void seVerificaElFuncionamientoDeLaBarraDeProgresoDelReproductor() {
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se verifica el funcionamiento de la reproducción del contenido 'Vivo' seleccionado")
    public void seVerificaElFuncionamientoDeLaReproduccionDelContenidoVivoSeleccionado() {
        this.playerPageAction.actionProgramRestart();
        this.playerPageAction.playerIsDisplayed();
    }

    @Cuando("Cuando El usuario comienza la reproducción del contenido con estadísticas deportivas")
    public void cuandoElUsuarioComienzaLaReproduccionDelContenidoConEstadisticasDeportivas() {
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario activa la funcionalidad de 'Pantalla completa' del reproductor")
    public void elUsuarioActivaLaFuncionalidadDePantallaCompletaDelReproductor() {
        this.playerPageAction.setPlayerFullScreenButton();
    }

    @Entonces("El contenido se reproduce correctamente a pantalla completa")
    public void elContenidoSeReproduceCorrectamenteAPantallaCompleta() {
        this.playerPageAction.isPlaying();
        this.playerPageAction.checkPlayerFullScreenIsActive();
        this.playerPageAction.checkPlayerFullScreen();
    }

    @Cuando("El usuario desactiva la funcionalidad de 'Pantalla completa' del reproductor")
    public void elUsuarioDesactivaLaFuncionalidadDePantallaCompletaDelReproductor() {
        this.playerPageAction.unsetPlayerFullScreenButton();
    }

    @Entonces("El contenido se reproduce correctamente sin pantalla completa")
    public void elContenidoSeReproduceCorrectamenteSinPantallaCompleta() {
        this.playerPageAction.isPlaying();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.checkPlayerFullScreenIsInactive();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.checkPlayerIsNotInFullScreen();
    }

    @Cuando("El usuario activa la funcionalidad de 'Pantalla completa' del reproductor mediante doble click")
    public void elUsuarioActivaLaFuncionalidadDePantallaCompletaDelReproductorMedianteDobleClick() {
        this.playerPageAction.setPlayerFullScreenDoubleClick();
    }

    @Cuando("El usuario desactiva la funcionalidad de 'Pantalla completa' del reproductor mediante doble click")
    public void elUsuarioDesactivaLaFuncionalidadDePantallaCompletaDelReproductorMedianteDobleClick() {
        this.playerPageAction.unsetPlayerFullScreenDoubleClick();
    }

    @Y("Finaliza la reproducción del contenido")
    public void finalizaLaReproduccionDelContenido() {
        this.playerPageAction.checkPlaybackFullContent();
    }

    @Y("Finaliza la reproducción del contenido vivo")
    public void finalizaLaReproduccionDelContenidoVivo() {
        this.playerPageAction.checkPlaybackFullContentLive();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
    }

    @Y("Finaliza la reproducción del contenido ya emitido")
    public void finalizaLaReproduccionDelContenidoYaEmitido() {
        this.playerPageAction.checkPlaybackFullContentReverseEPG();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
    }

    @Y("Finaliza la reproducción del contenido grabado")
    public void finalizaLaReproduccionDelContenidoGrabado() {
        this.playerPageAction.checkPlaybackFullContentRecordings();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
    }

    @Y("Finaliza la reproducción parcial del contenido grabado")
    public void finalizaLaReproduccionParcialDelContenidoGrabado() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
    }

    @Y("Finaliza la reproducción del contenido catchup")
    public void finalizaLaReproduccionDelContenidoCatchup() {
        this.playerPageAction.checkPlaybackFullContentCatchup();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickBackButton();
    }

    @Y("El usuario avanza hasta la visualizacion del BingeWatching")
    public void elUsuarioAvanzaHastaLaVisualizacionDelBingeWatching() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
    }

    @Entonces("Se muestran todos los elementos del 'BingeWatching' de tipo 'Peliculas'")
    public void seMuestranTodosLosElementosDelBingeWatchingDeTipoPeliculas() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.bingeWatchingIsDisplayed();
    }

    @Entonces("Se muestran todos los elementos del BingeWatching de tipo 'Series'")
    public void seMuestranTodosLosElementosDelBingeWatchingDeTipoSeries() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.bingeWatchingIsDisplayed();
    }

    @Cuando("El usuario utiliza la función de 'pausar y reanudar' del reproductor")
    public void elUsuarioUtilizaLaFuncionDePausarYReanudarDelReproductor() {
        this.playerPageAction.actionPlaybackPause();
        this.playerPageAction.isPaused();
        this.playerPageAction.actionPlaybackPlay();
        this.playerPageAction.isPlaying();
    }

    @Entonces("Se verifica el funcionamiento de 'pausar y reanudar' del reproductor")
    public void seVerificaElFuncionamientoDePausarYReanudarDelReproductor() {
        this.playerPageAction.isPlayingProgressBar();
    }

    @Entonces("El usuario utiliza la barra de progreso para adelantar y retroceder el contenido")
    public void elUsuarioUtilizaLaBarraDeProgresoParaAdelantarYRetrocederElContenido() {
        this.playerPageAction.isPlaying();
        // TODO add 'adelantar y retroceder' action
        this.playerPageAction.isPlaying();
    }

    @Cuando("El usuario ingresa a 'Últimos canales' y selecciona un canal")
    public void elUsuarioIngresaAUltimosCanalesYSeleccionaUnCanal() throws InterruptedException, AWTException {
        this.tvGuidePageAction.scrollChannelsDownInPlayer(1);
        this.playerPageAction.actionMouseOverPlayer();

        this.playerPageAction.openLastChannels();
        this.playerPageAction.checkChannelLastChannels();
    }

    @Entonces("El contenido se reproduce correctamente desde 'Últimos canales'")
    public void elContenidoSeReproduceCorrectamenteDesdeUltimosCanales() {
        this.playerPageAction.isPlaying();
    }

    @Y("La cuenta regresiva del BingeWatching finaliza")
    public void laCuentaRegresivaDelBingeWatchingFinaliza() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.waitTimeBingeWatchingNotExist();
    }

    @Entonces("El siguiente episodio se reproduce correctamente")
    public void elSiguienteEpisodioSeReproduceCorrectamente() {
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario reproduce el siguiente episodio desde el BingeWatching")
    public void elUsuarioReproduceElSiguienteEpisodioDesdeElBingeWatching() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.nextEpisodeBingeWatching();
    }

    @Y("El usuario utiliza la función 'Cancelar' desde el BingeWatching")
    public void elUsuarioUtilizaLaFuncionCancelarDesdeElBingeWatching() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.bingeWatchingIsDisplayed();
        this.playerPageAction.clickOnCancelBingeWatchingButton();
    }

    @Cuando("El usuario minimiza el contenido")
    public void elUsuarioMinimizaElContenido() {
        this.playerPageAction.minimizarBrowser();
    }

    @Y("El usuario vuelve a maximizarlo")
    public void elUsuarioVuelveAMaximizarlo() {
        this.playerPageAction.maximizarBrowser();
    }

    @Y("El usuario accede a la ficha tecnica desde el BingeWatching")
    public void elUsuarioAccedeALaFichaTecnicaDesdeElBingeWatching() {
        this.playerPageAction.playerSetTimeStart();
        this.playerPageAction.playerSeekContentBingeWatching();
        this.playerPageAction.bingeWatchingIsDisplayed();
        this.playerPageAction.clickMoreDetailsButton();
    }

    @Entonces("El contenido alquilado se reproduce correctamente")
    public void elContenidoAlquiladoSeReproduceCorrectamente() {
        if (this.contentPageAction.statusResult == true) {
            this.playerPageAction.playerIsDisplayed();
            this.playerPageAction.isPlaying();
        }
    }

    @Entonces("El usuario visualiza los trickmodes desde el botón 'skip Resume'")
    public void elUsuarioVisualizaLosTrickmodesDesdeElBotonSkipResume() {
        this.playerPageAction.mouseOverSkipResume();
        this.playerPageAction.playerIsDisplayed();
    }

    @Entonces("El usuario visualiza los trickmodes desde el botón 'skip intro'")
    public void elUsuarioVisualizaLosTrickmodesDesdeElBotonSkipIntro() {
        this.playerPageAction.mouseOverSkipIntro();
        this.playerPageAction.playerIsDisplayed();
    }

    @Entonces("El usuario salta la intro y se reproduce el capitulo correctamente")
    public void elUsuarioSaltaLaIntroYSeReproduceElCapituloCorrectamente() {
        this.playerPageAction.clickOnSkipIntro();
        this.playerPageAction.skipIntroButtonIsNotDisplayed();
        this.playerPageAction.isPlaying();
    }

    @Y("El usuario salta la intro presionando el botón de skip intro")
    public void elUsuarioSaltaLaIntroPresionandoElBotonDeSkipIntro() {
        this.currentTime = this.playerPageAction.getCurrentTime();
        this.playerPageAction.clickOnSkipIntro();
        this.newTime = this.playerPageAction.getCurrentTime();
        this.playerPageAction.introIsSkiped(this.currentTime, this.newTime);
    }

    @Y("El usuario salta el resumen presionando el botón de 'Skip Resume'")
    public void elUsuarioSaltaElResumenPresionandoElBotonDeSkipResume() {
        this.currentTime = this.playerPageAction.getCurrentTime();
        this.playerPageAction.clickOnSkipResume();
        this.newTime = this.playerPageAction.getCurrentTime();
        this.playerPageAction.introIsSkiped(this.currentTime, this.newTime);
    }

    @Cuando("El usuario utiliza la función de 'adelantar' 60 segundos del reproductor")
    public void elUsuarioUtilizaLaFuncionDeAdelantar60SegundosDelReproductor() {
        var count = 0;
        var loop = 6;
        while (count < loop) {
            this.playerPageAction.playerCurrentTimeForward();
            this.playerPageAction.waitNoSeekingWithoutRefresh();
            this.playerPageAction.playerIsPlaying();
            count++;
        }
    }

    @Entonces("Se saltea la intro correctamente")
    public void seSaltaLaIntroCorrectamente() {
        this.playerPageAction.introIsSkiped(this.currentTime, this.newTime);
    }

    @Entonces("El usuario saltea 'Skip Resume' correctamente")
    public void elUsuarioSalteaSkipResumeCorrectamente() {
        this.playerPageAction.introIsSkiped(this.currentTime, this.newTime);
    }

    @Entonces("La opción de 'Skip Intro' desaparece luego de 10 segundos")
    public void laOpcionDeSkipIntroDesapareceLuegoDe10Segundos() {
        this.playerPageAction.waitForSkipIntroIsNotVisible();
    }

    @Entonces("La opción de 'Skip Resume' desaparece luego de 10 segundos")
    public void laOpcionDeSkipResumeDesapareceLuegoDe10Segundos() {
        this.playerPageAction.waitForSkipResumeIsNotVisible();
    }

    @Entonces("La opción de 'Skip Resume' desaparece correctamente")
    public void laOpcionDeSkipResumeDesapareceCorrectamente() {
        this.playerPageAction.skipResumeIsNotVisible();
    }

    @Entonces("Se visualizan los atributos de los elementos del reproductor")
    public void seVisualizanLosAtributosDeLosElementosDelReproductor() {
        this.playerPageAction.styleOfPlayerElementsAreDisplayed();
    }

    @Entonces("NO se deberá mostrar botón de siguiente episodio en el reproductor")
    public void noSeDeberaMostrarBotonDeSiguienteEpisodioEnElReproductor() {
        this.playerPageAction.checkNextEpisodeIsNotDisplayed();
    }

    @Entonces("el contenido se reanuda correctamente")
    public void elContenidoSeReanudaCorrectamente() {
        this.playerPageAction.isPlaying();
        this.playerPageAction.actionMouseOverPlayer();
        this.playerPageAction.isPlayingProgressBar();
    }

    @Y("El usuario pasa al primer episodio de la siguiente temporada")
    public void elUsuarioPasaAlPrimerEpisodioDeLaSiguienteTemporada() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnNextEpisode();
    }

    @Y("El usuario cambia al siguiente capitulo de la serie")
    public void elUsuarioCambiaAlSiguienteCapituloDeLaSerie() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.clickOnNextEpisode();
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.checkTitleOfNextEpisode();
    }

    @Entonces("El reproductor de 'Radios y Música' se oculta correctamente")
    public void elReproductorDeRadiosYMúsicaSeOcultaCorrectamente() {
        this.playerPageAction.playerRadioNotDisplayed();
    }

    @Entonces("Se reproduce el contenido con subtitulos")
    public void seReproduceElContenidoConSubtitulos() {
        this.playerPageAction.isSubtitleActivated();
    }

    @Entonces("Se reproduce el contenido sin subtitulos")
    public void seReproduceElContenidoSinSubtitulos() {
        this.playerPageAction.isSubtitleDeactivated();
        this.playerPageAction.restartTimeOfPlayer();
        this.playerPageAction.setCurrentTimeForward("30");
        this.playerPageAction.checkSubtitlesIsNotDisplayed();
    }

    @Entonces("Se muestra activado el audio seleccionado")
    public void seMuestraActivadoElAudioSeleccionado() {
        this.playerPageAction.checkSpanishDescriptiveIsActivate();
    }

    @Entonces("Se reproduce el contenido con el audio 'Chino' desactivado")
    public void seReproduceElContenidoConElAudioChinoDesactivado() {
        this.playerPageAction.checkAudioIsNotActive(this.playerPageAction.isChineseAudioActive());
    }

    @Cuando("Se reproduce el contenido con el audio 'Aleman' desactivado")
    public void seReproduceElContenidoConElAudioAlemanDesactivado() {
        this.playerPageAction.checkAudioIsNotActive(this.playerPageAction.isGermanAudioActive());
    }

    @Cuando("Se reproduce el contenido con el audio 'Frances' desactivado")
    public void seReproduceElContenidoConElAudioFrancesDesactivado() {
        this.playerPageAction.checkAudioIsNotActive(this.playerPageAction.isFrenchAudioActive());
    }

    @Cuando("Se reproduce el contenido con el audio 'Frances' activado")
    public void seReproduceElContenidoConElAudioFrancesActivado() {
        this.playerPageAction.checkAudioIsActive(this.playerPageAction.isFrenchAudioActive());
    }

    @Cuando("Se reproduce el contenido con el audio 'Coreano' desactivado")
    public void seReproduceElContenidoConElAudioCoreanoDesactivado() {
        this.playerPageAction.checkAudioIsNotActive(this.playerPageAction.isKoreanAudioActive());
    }

    @Cuando("Se reproduce el contenido con el audio 'Italiano' desactivado")
    public void seReproduceElContenidoConElAudioItalianoDesactivado() {
        this.playerPageAction.checkAudioIsNotActive(this.playerPageAction.isItalianAudioActive());
    }
    @Cuando("Se reproduce el contenido con el audio 'Coreano' activado")
    public void seReproduceElContenidoConElAudioCoreanoActivado() {
        this.playerPageAction.checkAudioIsActive(this.playerPageAction.isKoreanAudioActive());
    }
    @Cuando("Se reproduce el contenido con el audio 'Aleman' activado")
    public void seReproduceElContenidoConElAudioAlemanActivado() {
        this.playerPageAction.checkAudioIsActive(this.playerPageAction.isGermanAudioActive());
    }

    @Entonces("Se reproduce el contenido con el audio 'Chino' activado")
    public void seReproduceElContenidoConElAudioChinoActivado() {
        this.playerPageAction.checkAudioIsActive(this.playerPageAction.isChineseAudioActive());
    }

    @Entonces("Se reproduce el contenido con el audio 'Italiano' activado")
    public void seReproduceElContenidoConElAudioItalianoActivado() {
        this.playerPageAction.checkAudioIsActive(this.playerPageAction.isItalianAudioActive());
    }


    @Entonces("Se reproduce el contenido sin Opcion de Subtitulos")
    public void seReproduceElContenidoSinOpcionDeSubtitulos() {
        this.playerPageAction.playerIsDisplayed();
        this.playerPageAction.subtitleOpcionIsDesactivated();
    }

    @Cuando("El usuario activa subtitulos 'Español Descriptivo'")
    public void elUsuarioActivaSubtitulosEspañolDescriptivo() {
        this.vodDetailInfoPageAction.openTvGuideChannel("14");
        this.playerPageAction.activateSpanishDescribedSubtitles();
    }

    @Cuando("El usuario activa audio 'Español Descriptivo'")
    public void elUsuarioActivaAudioEspañolDescriptivo() {
        this.playerPageAction.activateAudioDescriptiveSpanish();
    }

    @Cuando("El usuario activa audio 'Español'")
    public void elUsuarioActivaAudioEspañol() {
        this.playerPageAction.activateAudioSpanish();
    }
    @Cuando("El usuario selecciona audio 'Chino'")
    public void elUsuarioSeleccionaAudioChino() {
        this.playerPageAction.activateAudioChinese();
    }


    @Cuando("El usuario selecciona audio 'Alemán'")
    public void elUsuarioSeleccionaAudioAleman() {
        this.playerPageAction.activateAudioGerman();
    }

    @Cuando("El usuario selecciona audio 'Frances'")
    public void elUsuarioSeleccionaAudioFrances() {
        this.playerPageAction.activateAudioFrench();
    }

    @Cuando("El usuario selecciona audio 'Coreano'")
    public void elUsuarioSeleccionaAudioCoreano() {
        this.playerPageAction.activateAudioKorean();
    }

    @Cuando("El usuario selecciona audio 'Italiano'")
    public void elUsuarioSeleccionaAudioItaliano() {
        this.playerPageAction.activateAudioItalian();
    }

    @Y("El usuario desactiva subtitulos 'Español Descriptivo'")
    public void elUsuarioDesactivaSubtitulosEspañolDescription() {
        this.playerPageAction.desactivateDescriptiveSpanishSubtitles();
    }

    @Y("El usuario activa y desactiva subtitulos 'Español Descriptivo'")
    public void elUsuarioActivaYDesactivaSubtitulosEspañolDescriptivo() {
        this.playerPageAction.activateAndDeactivateSpanishDescriptiveSubtitles();
    }

    @Y("El usuario activa subtitulos 'Español'")
    public void elUsuarioActivaSubtitulosEspañol() {
        this.playerPageAction.activateSpanishSubtitles();
    }

    @Y("El usuario desactiva subtitulos 'Español'")
    public void elUsuarioDeactivaSubtitulosEspañol() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.desactivateSpanishSubtitles();
    }

    @Entonces("El usuario activa subtitulos 'Ingles'")
    public void elUsuarioActivaSubtitulosIngles() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.activateEnglishSubtitles();
    }

    @Y("El usuario abre el modal de subtitulos")
    public void elUsuarioAbreElModalDeSubtitulos() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.openSubtitleModal();
    }

    @Entonces("Se muestran los elementos de subtitulos")
    public void seMuestranLosElementosDeSubtitulos() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.subtitleElementsAreDisplayed();
    }

    @Y("El usuario cierra el modal de subtitulos desde afuera del modal")
    public void elUsuarioCierraElModalDeSubtitulosDesdeAfueraDelModal() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.closeSubtitlesModalFromOutside();
    }

    @Y("El usuario cierra el modal de subtitulos desde el modal")
    public void elUsuarioCierraElModalDeSubtitulosDesdeElModal() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.closeSubtitlesModal();
    }

    @Entonces("El modal de subtitulos se cierra correctamente")
    public void elModalDeSubtitulosSeCierraCorrectamente() {
        this.playerPageAction.modalSubtitlesClosedSuccess();
    }

    @Entonces("El usuario desactiva subtitulos 'Ingles'")
    public void elUsuarioDesactivaSubtitulosIngles() {
        this.playerPageAction.mouseOverPlayer();
        this.playerPageAction.desactivateSpanishSubtitles();
    }

    @Entonces("Se muestran los elementos de start youbora")
    public void seMuestranLosElementosDeStartYoubora() {
        this.playerPageAction.checkYouboraRequestStart();
    }

    @Entonces("Se muestran los elementos de pause youbora")
    public void seMuestranLosElementosDePauseYoubora() {
        this.playerPageAction.checkYouboraRequestPause();
    }

    @Entonces("Se muestran los elementos de seek youbora")
    public void seMuestranLosElementosDeSeekYoubora() {
        this.playerPageAction.checkYouboraRequestSeek();
    }

    @Entonces("Se muestran los elementos de stop youbora")
    public void seMuestranLosElementosDeStopYoubora() {
        this.playerPageAction.checkYouboraRequestStop();
    }

    @Entonces("Se muestran los elementos de resume youbora")
    public void seMuestranLosElementosDeResumeYoubora() {
        this.playerPageAction.checkYouboraRequestResume();
    }

    @Entonces("Los eventos analytics 'VOD' fueron enviados correctamente")
    public void losEventosAnalyticsAnalyticsVodFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsVod();
    }

    @Entonces("Los eventos analytics 'live channel' fueron enviados correctamente")
    public void losEventosAnalyticsLiveChannelFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsLiveChannel();
    }

    @Entonces("Los eventos analytics 'Reverse EPG' fueron enviados correctamente")
    public void losEventosAnalyticsReverseEPGFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsReverseEpg();
    }

    @Entonces("Los eventos analytics 'Search All' fueron enviados correctamente")
    public void losEventosAnalyticsSearchAllFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsSearchAll();
    }

    @Entonces("Los eventos analytics 'Purchase' fueron enviados correctamente")
    public void losEventosAnalyticsPurchaseFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsPurchase();
    }

    @Entonces("Los eventos analytics 'Trailer' fueron enviados correctamente")
    public void losEventosAnalyticsStartTrailerFueronEnviadosCorrectamente() {
        this.playerPageAction.checkAnalyticsTrailerEvent();
    }

    @Entonces("La barra de progreso avanza correctamente")
    public void laBarraDeProgresoAvanzaCorrectamente() {
        this.playerPageAction.waitPlayerLoad();
        this.playerPageAction.checkProgressBarTimerForwardOk();
    }

    @Entonces("La barra de progreso comienza desde el inicio correctamente")
    public void laBarraDeProgresoComienzaDesdeElInicioCorrectamente() {
        this.playerPageAction.waitPlayerLoad();
        this.playerPageAction.checkProgressBarTimerRestartOk();
    }

    @Entonces("Los eventos loggly 'invalid credentials' son enviados correctamente")
    public void losEventosLogglySonEnviadosCorrectamente() {
        this.playerPageAction.checkLogglyInvalidCredentialsRequestSuccess();
    }
}
