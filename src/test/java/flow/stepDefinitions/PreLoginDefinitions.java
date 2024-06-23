package flow.stepDefinitions;


import flow.pageActions.PreLoginPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

public class PreLoginDefinitions {


    protected PreLoginPageAction preLoginPageAction;

    public PreLoginDefinitions() {
        this.preLoginPageAction = new PreLoginPageAction(Hooks.getDriver());
    }

    @Entonces("La pantalla de 'Pre Login' se muestra correctamente")
    public void laPantallaDePreLoginSeMuestraCorrectamente() {
        this.preLoginPageAction.pageIsDisplay();
    }

    @Cuando("El usuario accede a 'Activa tu cuenta'")
    public void elUsuarioAccedeAActivaTuCuenta() {
        this.preLoginPageAction.activatedAccount();
    }
}
