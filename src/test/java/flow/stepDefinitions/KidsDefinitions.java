package flow.stepDefinitions;

import flow.pageActions.KidsPageAction;
import flow.pageActions.PlayerPageAction;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import java.util.logging.Logger;

public class KidsDefinitions {

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    protected KidsPageAction kidsPageAction;
    protected PlayerPageAction playerPageAction;

    public KidsDefinitions() {
        this.kidsPageAction = new KidsPageAction(Hooks.getDriver());
        this.playerPageAction = new PlayerPageAction(Hooks.getDriver());
    }

    @Cuando("El usuario reproduce un canal en vivo")
    public void elUsuarioReproduceUnCanalEnVivo() {
        this.kidsPageAction.waitToBeChannelLiveIsClickable();
        this.kidsPageAction.clickChannelLive();
    }

    @Entonces("El usuario es redirigido al player")
    public void elUsuarioEsRedirigidoAlPlayer() {
        this.playerPageAction.isPlaying();
    }
}
