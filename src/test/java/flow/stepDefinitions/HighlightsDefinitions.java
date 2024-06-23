package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.HighlightsPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;

public class HighlightsDefinitions {

    protected HighlightsPageAction highlightsPageAction;
    protected CommonPageAction commonPageAction;

    public HighlightsDefinitions() {
        this.highlightsPageAction = new HighlightsPageAction(Hooks.getDriver());
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
    }

    @Y("El usuario accede al stripe 'Reviví lo mejor'")
    public void elUsuarioAccedeAlStripeReviviLoMejor() {
        this.highlightsPageAction.selectRevivedTheBestItemStripeActive("Inicio");
    }

    @Y("El usuario accede al stripe 'Reviví lo mejor' en la seccion 'Deportes'")
    public void elUsuarioAccedeAlStripeReviviLoMejorEnLaSeccionDeportes() {
        this.highlightsPageAction.selectRevivedTheBestItemStripeActive("Deportes");
    }

    @Cuando("El usuario reproduce un contenido sección 'Miralo en'")
    public void elUsuarioReproduceUnContenidoSeccionMiraloEn() {
        this.highlightsPageAction.selectItemStripeActive(Hooks.props.lookAtIt());
    }

    @Cuando("El usuario reproduce un contenido sección 'Jugadas destacadas'")
    public void elUsuarioReproduceUnContenidoSeccionJugadasDestacadas() {
        this.highlightsPageAction.selectItemStripeActive(Hooks.props.featuredPlays());
    }

    @Cuando("El usuario reproduce un contenido sección 'Jugadores'")
    public void elUsuarioReproduceUnContenidoSeccionJugadores() {
        this.highlightsPageAction.selectItemStripeActive(Hooks.props.players());
    }
}
