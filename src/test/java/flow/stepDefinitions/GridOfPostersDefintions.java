package flow.stepDefinitions;

import flow.pageActions.ContentPageAction;
import flow.pageActions.GridOfPostersActions;
import flow.pageActions.SportsPageAction;
import io.cucumber.java.es.Entonces;

import java.util.logging.Logger;

public class GridOfPostersDefintions {

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));
    protected SportsPageAction sportsPageAction;
    protected GridOfPostersActions gridOfPostersActions;
    protected ContentPageAction contentPageAction;

    public GridOfPostersDefintions() {
        this.sportsPageAction = new SportsPageAction(Hooks.getDriver());
        this.gridOfPostersActions = new GridOfPostersActions(Hooks.getDriver());
        this.contentPageAction = new ContentPageAction(Hooks.getDriver());
    }

    @Entonces("El 'GOP' del stripe seleccionado se muestra correctamente")
    public void elGOPDelStripeSeleccionadoSeMuestraCorrectamente() {
        this.gridOfPostersActions.gopIsDisplayed();
    }

    @Entonces("El 'GOP' del stripe 'Todos los Alquileres' se visualiza correctamente")
    public void elGOPDelStripeTodosLosAlquileresSeVisualizaCorrectamente() {
        this.gridOfPostersActions.gopIsDisplayed();
    }

}
