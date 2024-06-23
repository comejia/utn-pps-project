package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.HomePageAction;
import flow.pageActions.SportsPageAction;
import flow.pageObjects.SportsPageFlow;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.util.logging.Logger;

public class SportsDefinitions {

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    protected SportsPageFlow sportsPageFlow;
    protected SportsPageAction sportsPageAction;
    protected CommonPageAction commonPageAction;
    protected HomePageAction homePageAction;

    public SportsDefinitions() {
        this.sportsPageAction = new SportsPageAction(Hooks.getDriver());
        this.sportsPageFlow = new SportsPageFlow(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
    }

    @Cuando("El usuario accede a la sección 'Deportes'")
    public void elUsuarioAccedeALaSeccionDeportes() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.sportsPageAction.accessToSportsSection();
    }

    @Entonces("Se muestran los stripes de la sección 'Deportes'")
    public void seMuestranLosStripesDeLaSeccionDeportes() {
        this.sportsPageAction.checkSportsSection();
    }

    @Entonces("Es posible navegar horizontalmente el stripe 'Eventos Deportivos'")
    public void esPosibleNavegarHorizontalmenteElStripeEventosDeportivos() {
        this.sportsPageAction.navStripeSportsEvents();
    }

    @Y("Accede a un contenido del stripe 'Eventos Deportivos'")
    public void accedeAUnContenidoDelStripeEventosDeportivos() {
        this.homePageAction.accessContentStripe(this.sportsPageFlow.stripeSportEvents);
    }

    @Y("Selecciona 'Ver Más' desde un stripe")
    public void seleccionaVerMasDesdeUnStripe() {
        this.homePageAction.waitForStripes();
        this.homePageAction.viewToStripe(SportsPageFlow.stripeBiographiesAndDocumentaries);
        this.commonPageAction.clickStripeContentSeeMore(SportsPageFlow.stripeBiographiesAndDocumentaries);
    }
}
