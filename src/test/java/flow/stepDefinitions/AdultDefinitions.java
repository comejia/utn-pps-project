package flow.stepDefinitions;

import flow.pageActions.*;
import flow.pageObjects.AdultPageFlow;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.awaitility.Awaitility;

import java.time.Duration;

public class AdultDefinitions {
    protected AdultPageAction adultPageAction;
    protected AdultPageFlow adultPageFlow;
    protected VodDetailInfoPageAction vodDetailInfoPageAction;
    protected CommonPageAction commonPageAction;
    protected PlayerPageAction playerPageAction;
    protected HomePageAction homePageAction;

    public AdultDefinitions() {
        this.adultPageAction = new AdultPageAction(Hooks.getDriver());
        this.adultPageFlow = new AdultPageFlow(Hooks.getDriver());
        this.vodDetailInfoPageAction = new VodDetailInfoPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Adulto'")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAdulto() {
        var content = Hooks.dataProvider.getContentAdult(Hooks.props.providerUsername(), Hooks.props.providerPassword());

        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.adultPageAction.enterDipAdult();
    }

    @Entonces("La ficha técnica de contenido tipo 'Adulto' se muestra correctamente")
    public void laPantallaDeFichaTecnicaDeContenidoAdultoSeMuestraCorrectamente() {
        this.vodDetailInfoPageAction.pageIsDisplayedContentAdult();
    }

    @Y("El usuario selecciona 'Alquilar'")
    public void elUsuarioSeleccionaAlquilar() {
        this.adultPageAction.selectRent();
    }

    @Entonces("Se visualizan las opciones de alquiler")
    public void seVisualizanLasOpcionesDeAlquiler() {
        this.adultPageAction.adultRentalOptionsDisplayed();
    }

    @Y("Se dirige al Stripe 'Adultos'")
    public void seDirigeAlStripeAdultos() {
        this.commonPageAction.goToStripe(Hooks.props.stripeAdult());
        this.commonPageAction.scrollDownVertical();
    }

    @Entonces("Se visualiza el stripe 'Adultos' correctamente")
    public void SeVisualizaElStripeAdultosCorrectamente() {
        this.adultPageAction.censoredPostersDisplayed();
    }

    @Cuando("El usuario realiza una búsqueda de un contenido 'Adulto'")
    public void elUsuarioRealizaUnaBusquedaDeUnContenidoAdulto() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        var content = Hooks.dataProvider.getContentAdult(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.commonPageAction.searchContent(content.getTitle());
    }

    @Entonces("No se muestran los resultados de la búsqueda de un contenido 'Adulto' realizada")
    public void noSeMuestranLosResultadosDeLaBusquedaDeUnContenidoAdultoRealizada() {
        this.adultPageAction.noSearchResultsDisplayedAdult();
    }

    @Entonces("El contenido se muestra disponible para ser alquilado")
    public void elContenidoSeMuestraDisponibleParaSerAlquilado() {
        this.adultPageAction.contentDisplayedAvailableToRent();
    }

    @Cuando("El usuario accede a la ficha técnica de un contenido del tipo 'Adulto' ya alquilado")
    public void elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAdultoYaAlquilado() {
        var content = Hooks.dataProvider.getContentAdultPurchased(Hooks.props.providerUsername(), Hooks.props.providerPassword());
        this.vodDetailInfoPageAction.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        this.adultPageAction.enterDipAdult();
    }

    @Y("EL usuario reproduce el contenido alquilado")
    public void elUsuarioReproduceElContenidoAlquilado() {
        this.adultPageAction.playContentRented();
        this.playerPageAction.playerIsDisplayed();

    }

    @Entonces("El contenido adulto no se muestra en la seccion 'Continuar viendo'")
    public void elContenidoAdultoNoSeMuestraEnLaSeccionContinuarViendo() {
        this.playerPageAction.clickBackButton();
        this.adultPageAction.adultContentNotDisplayedInContinueWatchingSection();
    }

    @Y("El usuario confirma el alquiler del contenido adulto")
    public void elUsuarioConfirmaElAlquilerDelContenidoAdulto() {
        Awaitility.await().atMost(Duration.ofSeconds(60)).until(() -> {
            try {
                this.vodDetailInfoPageAction.clickRentButton();
                this.vodDetailInfoPageAction.modalIsDisplayed("");
                this.vodDetailInfoPageAction.clickModalConfirmButton();
                return true;
            } catch (Throwable throwable) {
                this.elUsuarioAccedeALaFichaTecnicaDeUnContenidoDelTipoAdulto();
                return false;
            }
        });
        this.playerPageAction.playerIsDisplayed();
    }
    @Entonces("El contenido adulto se muestra como alquilado y disponible para reproducir")
    public void elContenidoAdultoSeMuestraComoAlquiladoYDisponibleParaReproducir() {
        this.playerPageAction.clickBackButton();
        this.adultPageAction.enterDipAdult();
        this.vodDetailInfoPageAction.pageIsDisplayedContentAdult();
        this.vodDetailInfoPageAction.rentedElementsAreDisplayed();
        this.adultPageAction.playButtonIsDisplayed();
    }

    @Y("Accede al stripe 'Adultos'")
    public void accedeAlStripeAdultos() {
        this.commonPageAction.goToStripe(Hooks.props.stripeAdult());
        this.commonPageAction.scrollDownVertical();
        this.commonPageAction.accessSeeMoreToStripe(Hooks.props.stripeAdult());
    }

    @Cuando("El usuario accede a un contenido del stripe 'Adultos'")
    public void elUsuarioAccedeAUnContenidoDelStripeAdultos() {
        this.commonPageAction.goToStripe(Hooks.props.stripeAdult());
        this.commonPageAction.scrollDownVertical();
        this.homePageAction.accessContentStripe(this.adultPageFlow.stripeAdult);
    }
}
