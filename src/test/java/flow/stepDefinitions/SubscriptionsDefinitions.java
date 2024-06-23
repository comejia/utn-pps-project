package flow.stepDefinitions;

import flow.pageActions.CommonPageAction;
import flow.pageActions.HomePageAction;
import flow.pageActions.MyAccountPageAction;
import flow.webdriverUtils.ExtendedWebDriver;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class SubscriptionsDefinitions {

    protected Logger logger = Logger.getLogger(String.valueOf(this.getClass()));

    protected MyAccountPageAction myAccountPageAction;
    protected CommonPageAction commonPageAction;
    protected ExtendedWebDriver webDriver;
    protected HomePageAction homePageAction;

    public SubscriptionsDefinitions() {
        this.commonPageAction = new CommonPageAction(Hooks.getDriver());
        this.myAccountPageAction = new MyAccountPageAction(Hooks.getDriver());
        this.homePageAction = new HomePageAction(Hooks.getDriver());
        this.webDriver = new ExtendedWebDriver(Hooks.getDriver());
    }

    @Cuando("El usuario visualiza el banner de Disney")
    public void elUsuarioVisualizaElBannerDeDisney() {
        this.commonPageAction.disneyBannerIsDisplayed();
        logger.info("User views generic banner");
    }

    @Entonces("El banner promocional de Disney se muestra correctamente")
    public void elBannerPromocionalDeDisneySeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerPromotion();
        logger.info("Disney promotional banner displays correctly");
    }

    @Entonces("El banner contratado de Disney se muestra correctamente")
    public void elBannerContratadoDeDisneySeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerActive();
        logger.info("Contracted Disney banner is displayed correctly");
    }

    @Cuando("El usuario ingresa al banner de Disney")
    public void elUsuarioIngresaAlBannerDeDisney() {
        this.commonPageAction.clickDisneyBanner();
        logger.info("User accesses and views Disney banner");
    }

    @Entonces("El usuario accede a la pantalla de suscripción de Disney en Flow")
    public void elUsuarioAccedeALaPantallaDeSuscripcionDeDisneyEnFlow() {
        this.myAccountPageAction.checkBannerSubscription();
        assertTrue("Title 'Tus historias favoritas, cuando vos quieras.' is not displayed", this.myAccountPageAction.checkContentTitleSubscribe());
        assertTrue("'Términos y condiciones' is not displayed", this.myAccountPageAction.checkContentTitleTermsConditionsSubscribe());
        assertTrue("Check 'Términos y condiciones' is not displayed", this.myAccountPageAction.checkContentCheckTermsConditionsSubscribe());
        assertTrue("'Suscribite a Disney+' is not displayed", this.myAccountPageAction.checkContentButtonDisneySubscriptionSubscribe());
        assertTrue("Button 'MAS INFO' is not diplayed", this.myAccountPageAction.checkContentTitleMoreInfoSubscribe());
        assertTrue("'LEGALES' is not displayed", this.myAccountPageAction.checkContentTitleLegalSubscribe());
        logger.info("User accesses Disney subscription screen in Flow and views all content");
    }

    @Entonces("El usuario accede al login de Disney")
    public void elUsuarioAccedeAlLoginDeDisney() {
        this.commonPageAction.switchToNewTab(1);
        this.commonPageAction.clickExternalLoginDisney();
        logger.info("User accesses and views Disney login");
    }

    @Entonces("El usuario accede al registro de Disney")
    public void elUsuarioAccedeAlRegistroDeDisney() {
        this.commonPageAction.switchToNewTab(1);
        this.commonPageAction.clickExternalSubscriptionDisney();
        logger.info("User accesses and views Disney register");
    }

    @Cuando("El usuario ingresa a Mis Suscripciones")
    public void elUsuarioIngresaAMisSuscripciones() {
        this.commonPageAction.clickDropdownUser();
        this.commonPageAction.clickDropdownMyAccount();
        logger.info("User enters and views 'My Subscriptions'");
    }

    @Entonces("No se muestran suscripciones de pack Disney para el usuario")
    public void noSeMuestranSuscripcionesDePackDisneyParaElUsuario() {
        this.commonPageAction.waitMenuAccount();
        this.myAccountPageAction.checkDisneySubscriptionNotExists();
        this.myAccountPageAction.checkContentImageDisneyMySubscriptions();
        assertTrue("'Mis Suscripciones' is not displayed", this.myAccountPageAction.checkContentTitleMySubscriptions());
        assertTrue("Button 'Disney+' is not displayed", this.myAccountPageAction.checkContentTitleDisneyPlusMySubscriptions());
        assertTrue("'Tus historias favoritas, cuando vos quieras.' is not displayed",
                this.myAccountPageAction.checkContentDescriptionDeactivatedDisneyMySubscriptions());
        assertTrue("Button 'SUSCRIBITE' is not displayed", this.myAccountPageAction.checkContentButtonSubscribeMySubscriptions());
//        this.myAccountPageAction.checkContentButtonMoreInfoMySubscriptions();
        logger.info("Disney pack subscriptions not displayed for user and views all content");
    }

    @Entonces("Se muestra la suscripción pack Disney activa para el usuario")
    public void seMuestraLaSuscripcionPackDisneyActivaParaElUsuario() {
        this.commonPageAction.waitMenuAccount();
        this.myAccountPageAction.checkDisneySubscriptionExists();
        this.myAccountPageAction.checkContentImageDisneyMySubscriptions();
        assertTrue("'Mis Suscripciones' is not displayed", this.myAccountPageAction.checkContentTitleMySubscriptions());
        assertTrue("Button 'Disney+' is not displayed", this.myAccountPageAction.checkContentTitleDisneyPlusMySubscriptions());
        this.commonPageAction.waitSubscriptionDisneyActive();
        assertTrue("Button 'CANCELAR PACK' is not displayed", this.myAccountPageAction.checkContentButtonCancelMySubscriptions());
        logger.info("Active Disney pack subscription is displayed for user and views all content");
    }

    @Entonces("Se muestra la suscripción pack Disney inactiva para el usuario")
    public void seMuestraLaSuscripcionPackDisneyInactivaParaElUsuario() {
        this.commonPageAction.waitMenuAccount();
        this.myAccountPageAction.checkContentImageDisneyMySubscriptions();
        assertTrue("'Mis Suscripciones' is not displayed", this.myAccountPageAction.checkContentTitleMySubscriptions());
        assertTrue("Button 'Disney+' is not displayed", this.myAccountPageAction.checkContentTitleDisneyPlusMySubscriptions());
        this.myAccountPageAction.checkDisneySubscriptionPending();
        assertTrue("Button 'ACTIVAR CUENTA' is not displayed", this.myAccountPageAction.checkContentButtonActivateMySubscriptions());
        logger.info("Inactive Disney pack subscription is displayed for the user and views all content");
    }

    @Entonces("El usuario accede a Mis Suscripciones")
    public void elUsuarioAccedeAMisSuscripciones() {
        this.commonPageAction.waitMenuAccount();
        logger.info("User accesses 'My Subscriptions', views an active subscription and views all content");
    }

    @Entonces("Se muestra oferta 30 días para el usuario")
    public void seMuestraOfertaDiasParaElUsuario() {
        this.commonPageAction.waitMenuAccount();
        assertTrue("'Te regalamos 30 días de suscripción.' is not displayed", this.myAccountPageAction.checkContentOfferDescriptionMySubscriptions());
        assertTrue("Button 'SUSCRIBITE' is not displayed", this.myAccountPageAction.checkContentButtonSubscribeMySubscriptions());
        logger.info("User views the 30-day offer ");
    }

    @Entonces("El usuario visualiza la oferta de Disney")
    public void elUsuarioVisualizaLaOfertaDeDisney() {
        this.commonPageAction.waitMenuAccount();
        this.myAccountPageAction.checkDisneySubscriptionNotExists();
        this.myAccountPageAction.checkContentImageDisneyMySubscriptions();
        assertTrue("'Mis Suscripciones' is not displayed", this.myAccountPageAction.checkContentTitleMySubscriptions());
        assertTrue("Button 'Disney+' is not displayed", this.myAccountPageAction.checkContentTitleDisneyPlusMySubscriptions());
        assertTrue("'Tus historias favoritas, cuando vos quieras.' is not displayed",
                this.myAccountPageAction.checkContentDescriptionDeactivatedDisneyMySubscriptions());
        assertTrue("Button 'SUSCRIBITE' is not displayed", this.myAccountPageAction.checkContentButtonSubscribeMySubscriptions());
        logger.info("User views Disney offer and all content");
    }

    @Entonces("El banner promocional de Combo+ con pack Disney+ linked se muestra correctamente")
    public void elBannerPromocionalDeComboSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerComboUpgrade();
        logger.info("Combo+ upgrade promotional banner displays correctly");
    }

    @Entonces("El banner promocional de Combo+ sin packs se muestra correctamente")
    public void elBannerPromocionalDeComboSinPacksSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerComboPromotion();
        logger.info("Combo+ promotion banner displays correctly");
    }

    @Entonces("El banner promocional de Combo+ con pack Combo+ linked se muestra correctamente")
    public void elBannerPromocionalDeComboConPackComboLinkedSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerComboLinkedPromotion();
        logger.info("Combo+ linked promotion banner displays correctly");
    }


    @Entonces("El banner promocional de Combo+ con pack Combo+ unlinked se muestra correctamente")
    public void elBannerPromocionalDeComboConPackComboUnlinkedSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerComboUnlinkedPromotion();
        logger.info("Combo+ unlinked promotion banner displays correctly");
    }

    @Entonces("El banner promocional de Combo+ con pack Disney+ unlinked se muestra correctamente")
    public void elBannerPromocionalDeComboConPackDisneyUnlinkedSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerComboUpgrade();
        logger.info("Combo+ upgrade promotional banner displays correctly");
    }

    @Entonces("El banner promocional de Combo+ con pack Star+ linked se muestra correctamente")
    public void elBannerPromocionalDeComboConPackStarLinkedSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerStarLinkedPromotion();
        logger.info("Star+ linked promotional banner displays correctly");
    }

    @Entonces("El banner promocional de Combo+ con pack Star+ unlinked se muestra correctamente")
    public void elBannerPromocionalDeComboConPackStarUnlinkedSeMuestraCorrectamente() {
        this.myAccountPageAction.checkBannerStarUnlinkedPromotion();
        logger.info("Star+ unlinked promotional banner displays correctly");
    }

    @Cuando("El usuario ingresa al banner promocional Star+ sin packs")
    public void elUsuarioIngresaAlBannerPromocionalStarSinPacks() {
        this.myAccountPageAction.selectBannerSinPacks();
        logger.info("Star+ promotion banner selected correctly");
    }

    @Entonces("Los elementos de la oferta Star+ sin packs se muestran correctamente")
    public void losElementosDeLaOfertaStarSinPacksSeMuestranCorrectamente() {
        this.myAccountPageAction.checkElementsHeaderStarPromotion();
        logger.info("Header promotions elements is displayed");
    }

    @Cuando("El usuario ingresa al banner promocional Combo+ upgrade con Disney+ linked")
    public void elUsuarioIngresaAlBannerPromocionalComboUpgradeConDisneyLinked() {
        this.myAccountPageAction.selectBannerDisneyLinked();
        logger.info("Combo+ promotion banner selected correctly");
    }

    @Entonces("Los elementos de la oferta Combo+ upgrade con Disney+ linked se muestran correctamente")
    public void losElementosDeLaOfertaComboUpgradeConDisneyLinkedSeMuestranCorrectamente() {
        this.myAccountPageAction.checkElementsHeaderComboPromotion();
        logger.info("Header promotions elements is displayed");
    }

    @Cuando("El usuario ingresa al banner promocional Combo+ upgrade con Disney+ unlinked")
    public void elUsuarioIngresaAlBannerPromocionalComboUpgradeConDisneyUnlinked() {
        this.myAccountPageAction.selectBannerDisneyUninked();
        logger.info("Combo+ promotion banner selected correctly");
    }

    @Entonces("Los elementos de la oferta Combo+ upgrade con Disney+ unlinked se muestran correctamente")
    public void losElementosDeLaOfertaComboUpgradeConDisneyUnlinkedSeMuestranCorrectamente() {
        this.myAccountPageAction.checkElementsHeaderComboPromotion();
        logger.info("Header promotions elements is displayed");
    }

    @Entonces("Se muestran la oferta de Combo+ en Mis Suscripciones")
    public void seMuestranLaOfertaDeComboEnMisSuscripciones() {
        this.myAccountPageAction.checkElementsDisneySubscriptionLinked();
        logger.info("Combo+ promotion elements is displayed");
    }

    @Entonces("Se muestra el pack de 'Disney+' activo en 'Suscripciones'")
    public void SemuestraelpackdeDisneyactivoenSuscripciones() {
        this.myAccountPageAction.checkPackDisneyPlusActive();
        logger.info("Pack 'Disney+ Active' is displayed");
    }

    @Entonces("Se muestra el pack de 'Star+' activo en 'Suscripciones'")
    public void SemuestraelpackdeStaractivoenSuscripciones() {
        this.myAccountPageAction.checkPackStarPlusActive();
    }

    @Entonces("Se muestra el pack de 'Disney+ y Star+' activo en 'Suscripciones'")
    public void seMuestraElPackDeDisneyYStarActivoEnSuscripciones() {
        this.myAccountPageAction.checkPackComboPlusActive();
    }

    @Entonces("Se muestran el pack de Combo+ inactivo en Mis Suscripciones")
    public void seMuestranElPackDeComboInactivoEnMisSuscripciones() {
        this.myAccountPageAction.checkPackComboPlusInactive();
    }

    @Entonces("Se muestran las ofertas en 'Suscripciones' en el orden Disney+, HBO, Paramount+, Disney+ y Star+, Fútbol")
    public void seMuestranLasOfertasEnSuscripcionesEnElOrdenDineyHboParamountDineyYstarFutbol() {
        this.myAccountPageAction.checkPositionDisneyLinked();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestran las ofertas en Mis Suscripciones en el orden Disney+ y Combo+ para Disney+ unlinked")
    public void seMuestranLasOfertasEnMisSuscripcionesEnElOrdenDisneyYComboParaDisneyUnlinked() {
        this.myAccountPageAction.checkPositionDisneyUnlinked();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestran las ofertas en Mis Suscripciones en el orden Star+ y Combo+ para Star+ linked")
    public void seMuestranLasOfertasEnMisSuscripcionesEnElOrdenStarYComboParaStarLinked() {
        this.myAccountPageAction.checkPositionStarLinked();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestran las ofertas en Mis Suscripciones en el orden Star+ y Combo+ para Star+ unlinked")
    public void seMuestranLasOfertasEnMisSuscripcionesEnElOrdenStarYComboParaStarUnlinked() {
        this.myAccountPageAction.checkPositionStarUnlinked();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestran las ofertas en 'Suscripciones' en el orden HBO, Paramount+, Star+, Disney+, Disney+ y Star+, Fútbol")
    public void SemuestranLasOfertasEnSuscripcionesEnElOrdenHboParamountStarDisneyDisneyYstarFutbol() {
        this.myAccountPageAction.checkPositionDeactivated();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestran las ofertas en 'Suscripciones' en el orden Star+, HBO, Paramount+, Disney+ y Star+, Fútbol")
    public void seMuestranLasOfertasEnSuscripcionesEnElOrdenStarHBOParamountDisneyYStarFutbol() {
        this.myAccountPageAction.checkPositionDeStarActive();
        logger.info("Offers displayed in order");
    }

    @Entonces("Se muestra la oferta Star+ en la Home")
    public void seMuestraLaOfertaStarEnLaHome() {
        this.homePageAction.checkBannerOnboardingStar();
        logger.info("Onboarding Star+ is displayed");
    }

    @Y("Selecciona 'Suscribirme' en pack Combo+")
    public void seleccionaSuscribirmeEnPackCombo() {
        this.myAccountPageAction.selectButtonSubscribeComboPlusMySubscriptions();
    }

    @Y("Selecciona 'Suscribirme' en pack Disney+")
    public void seleccionaSuscribirmeEnPackDisney() {
        this.myAccountPageAction.selectButtonSubscribeDisneyPlusMySubscriptions();
    }

    @Entonces("Los elementos de la oferta Combo+ sin packs se muestran correctamente desde Mis Suscripciones")
    public void losElementosDeLaOfertaComboSinPacksSeMuestranCorrectamenteDesdeMisSuscripciones() {
        this.myAccountPageAction.checkOfferItemsComboPlusMySubscriptions();
        logger.info("Combo+ offer items is displayed");
    }

    @Entonces("Los elementos de la oferta Star+ sin packs se muestran correctamente desde Mis Suscripciones")
    public void losElementosDeLaOfertaStarSinPacksSeMuestranCorrectamenteDesdeMisSuscripciones() {
        this.myAccountPageAction.checkOfferItemsStarPlusMySubscriptions();
        logger.info("Star+ offer items is displayed");
    }

    @Cuando("El usuario con Combo+ unlinked ingresa al banner promocional")
    public void elUsuarioConComboUnlinkedIngresaAlBannerPromocional() {
        this.myAccountPageAction.clickBannerHomeComboUnlinked();
    }

    @Entonces("La oferta de activación se muestra correctamente en Star+")
    public void laOfertaDeActivacionSeMuestraCorrectamenteEnStar() {
        this.myAccountPageAction.checkFormLoginStarPlus();
    }

    @Cuando("El usuario con Combo+ unlinked ingresa a 'Activar Cuenta'")
    public void elUsuarioConComboUnlinkedIngresaAActivarCuenta() {
        this.myAccountPageAction.clickActivateAccountMySubscriptions();
        logger.info("Combo+ Element 'Activar Cuenta' is displayed");
    }

    @Cuando("El usuario con pack Star+ unlinked ingresa al banner promocional")
    public void elUsuarioConPackStarUnlinkedIngresaAlBannerPromocional() {
        this.myAccountPageAction.clickBannerHomeStarUnlinked();
    }

    @Cuando("El usuario con Disney+ unlinked ingresa a 'Activar Cuenta'")
    public void elUsuarioConDisneyUnlinkedIngresaAActivarCuenta() {
        this.myAccountPageAction.clickTitleDisneyPlusMySubscriptionActive();
        logger.info("Star+ - Element 'Activar Cuenta' is displayed");
    }

    @Entonces("La oferta de activación se muestra correctamente en Disney+")
    public void laOfertaDeActivacionSeMuestraCorrectamenteEnDisney() {
        this.myAccountPageAction.checkFormLoginDisneyPlus();
    }

    @Cuando("El usuario ingresa a 'Suscripciones'")
    public void elUsuarioIngresaASuscripciones() {
        this.commonPageAction.isPopUpHomeClosedParamount();
        this.commonPageAction.clickSubscriptions();
        logger.info("User enters and views 'My Subscriptions'");
    }

    @Y("El usuario solicita la baja del pack 'Disney+'")
    public void elUsuarioSolicitaLaBajaDelPackDisney() {
        this.myAccountPageAction.clickModalDisneyPlus();
        this.myAccountPageAction.clickCancelPackDisneyPlus();
    }

    @Y("El usuario solicita la baja del pack 'Star+'")
    public void elUsuarioSolicitaLaBajaDelPackStar() {
        this.myAccountPageAction.clickModalStarPlus();
        this.myAccountPageAction.clickCancelPackStarPlus();
    }

    @Y("El usuario solicita la baja del pack 'Disney+ y Star+'")
    public void elUsuarioSolicitaLaBajaDelPackDisneyYStar() {
        this.myAccountPageAction.clickModalDisneyStarPlus();
        this.myAccountPageAction.clickCancelPackSubscription();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'Disney+'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackDisney() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'Star+'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackStar() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'Disney+ y Star+'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackDisneyYStar() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Y("El usuario ingresa a la landing del pack 'Disney+'")
    public void elUsuarioIngresaALaLandingDelPackDisney() {
        this.myAccountPageAction.clickModalDisneyPlus();
    }

    @Entonces("Se muestra la landing del pack 'Disney+' activo")
    public void seMuestraLaLandingDelPackDisneyActivo() {
        this.myAccountPageAction.checkElementsLandingDisneyPlus();
    }

    @Y("El usuario ingresa a la landing del pack 'Star+'")
    public void elUsuarioIngresaALaLandingDelPackStar() {
        this.myAccountPageAction.clickModalStarPlus();
    }

    @Entonces("Se muestra la landing del pack 'Star+' activo")
    public void seMuestraLaLandingDelPackStarActivo() {
        this.myAccountPageAction.checkElementsLandingStartPlus();
    }

    @Y("El usuario accede a explorar contenidos del pack 'Disney+'")
    public void elUsuarioAccedeAExplorarContenidosDelPackDisney() {
        this.myAccountPageAction.clickModalDisneyPlus();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Entonces("La sección Disney+ se muestra correctamente")
    public void laSeccionDisneySeMuestraCorrectamente() {
        Hooks.getDriver().checkUrlEquals(Hooks.props.pageUrlContentDisney());
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Y("El usuario accede a explorar contenidos del pack 'Star+'")
    public void elUsuarioAccedeAExplorarContenidosDelPackStar() {
        this.myAccountPageAction.clickModalStarPlus();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Entonces("La sección Star+ se muestra correctamente")
    public void laSecciónStarSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Y("El usuario ingresa a la landing del pack 'Paramount+'")
    public void elUsuarioIngresaALaLandingDelPackParamount() {
        this.myAccountPageAction.clickModalParamountPlus();
    }

    @Entonces("Se muestra la landing del pack 'Paramount+' activo")
    public void seMuestraLaLandingDelPackParamountActivo() {
        this.myAccountPageAction.checkElementsLandingParamountPlus();
    }

    @Y("El usuario accede a explorar contenidos del pack 'Paramount+'")
    public void elUsuarioAccedeAExplorarContenidosDelPackParamount() {
        this.myAccountPageAction.clickModalParamountPlus();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Entonces("La sección Paramount+ se muestra correctamente")
    public void laSecciónParamountSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("La sección Disney+ y Star+ se muestra correctamente")
    public void laSeccionDisneyYStarSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Y("El usuario accede a explorar contenidos del pack 'Disney+ y Star+'")
    public void elUsuarioAccedeaExplorarContenidosDelPackDisneyYStar() {
        this.myAccountPageAction.clickModalDisneyStarPlus();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Y("El usuario ingresa a la landing del pack 'Disney+ y Star+'")
    public void elUsuarioIngresaALaLandingDelPackDisneyYStar() {
        this.myAccountPageAction.clickModalDisneyStarPlus();
    }

    @Entonces("Se muestra la landing del pack 'Disney+ y Star+' activo")
    public void seMuestraLaLandingDelPackDisneyYStarActivo() {
        this.myAccountPageAction.checkElementsLandingComboPlus();
    }

    @Y("El usuario ingresa a la landing del pack 'HBO'")
    public void elUsuarioIngresaALaLandingDelPackHBO() {
        this.myAccountPageAction.clickModalHBO();
    }

    @Entonces("Se muestra la landing del pack 'HBO' activo")
    public void seMuestraLaLandingDelPackHBOActivo() {
        this.myAccountPageAction.checkElementsLandingHBO();
    }

    @Y("El usuario accede a explorar contenidos del pack 'HBO'")
    public void ElUsuarioAccedeAexplorarContenidosDelPackHBO() {
        this.myAccountPageAction.clickModalHBO();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Entonces("La sección HBO se muestra correctamente")
    public void laSecciónHBOSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Se muestra la landing de oferta del pack 'Disney+'")
    public void seMuestraLaLandingDeOfertaDelPackDisney() {
        this.myAccountPageAction.checkOfferItemsDisneyPlusMySubscriptions();
        logger.info("Disney+ offer items is displayed");
    }

    @Entonces("Se muestra la landing de oferta del pack 'Star+'")
    public void seMuestraLaLandingDeOfertaDelPackStar() {
        this.myAccountPageAction.checkOfferItemsStarPlusMySubscriptions();
    }

    @Entonces("Se muestra la landing de oferta del pack 'Disney+ y Star+'")
    public void seMuestraLaLandingDeOfertaDelPackDisneyYStar() {
        this.myAccountPageAction.checkOfferItemsComboPlusMySubscriptions();
    }

    @Y("El usuario ingresa a la landing del pack 'Fútbol'")
    public void elUsuarioIngresaALaLandingDelPackFutbol() {
        this.myAccountPageAction.clickModalPackFutbol();
    }

    @Entonces("Se muestra la landing de oferta del pack 'Fútbol'")
    public void seMuestraLaLandingDeOfertaDelPackFutbol() {
        this.myAccountPageAction.checkOfferItemsPackFutbol();
    }

    @Entonces("Se muestra la landing de oferta del pack 'Paramount+'")
    public void seMuestraLaLandingDeOfertaDelPackParamount() {
        this.myAccountPageAction.checkOfferItemsParamountPlus();
    }

    @Entonces("Se muestra la landing de oferta del pack 'HBO'")
    public void seMuestraLaLandingDeOfertaDelPackHBO() {
        this.myAccountPageAction.checkOfferItemsHboMySubscriptions();
    }

    @Y("El usuario accede a explorar contenidos del pack 'Fútbol'")
    public void elUsuarioAccedeAExplorarContenidosDelPackFutbol() {
        this.myAccountPageAction.clickModalPackFutbol();
        this.myAccountPageAction.clickButtonExploreContent();
    }

    @Entonces("Se muestra la landing del pack 'Fútbol' activo")
    public void seMuestraLaLandingDelPackFutbolActivo() {
        this.myAccountPageAction.checkElementsLandingPackFutbol();
    }

    @Entonces("Se muestra el pack de 'Disney+' inactivo en 'Suscripciones'")
    public void seMuestraElPackDeDisneyInactivoEnSuscripciones() {
        this.myAccountPageAction.unlinkedPackDisneyIsDisplayed();
    }

    @Entonces("Se muestra el pack de 'Disney+ y Star+' inactivo en 'Suscripciones'")
    public void seMuestraElPackDeDisneyYStarInactivoEnSuscripciones() {
        this.myAccountPageAction.unlinkedPackComboIsDisplayed();
    }

    @Entonces("Se muestra el pack de 'Star+' inactivo en 'Suscripciones'")
    public void seMuestraElPackDeStarInactivoEnSuscripciones() {
        this.myAccountPageAction.unlinkedPackStarIsDisplayed();
    }

    @Entonces("Se muestran las ofertas en 'Suscripciones' en el orden Disney+ y Star+, HBO, Paramount+, Fútbol")
    public void seMuestranLasOfertasEnSuscripcionesEnElOrdenDisneyYStarHBOParamountFutbol() {
        this.myAccountPageAction.checkPositionDisneyLinked();
        logger.info("Offers displayed in order");
    }

    @Y("El usuario ingresa a la landing del pack 'TV en vivo'")
    public void elUsuarioIngresaALaLandingDelPackTVEnVivo() {
        this.myAccountPageAction.clickModalPackTvEnVivo();
    }

    @Entonces("Se muestra la landing de oferta del pack 'TV en vivo'")
    public void seMuestraLaLandingDeOfertaDelPackTVEnVivo() {
        this.myAccountPageAction.pageIsDisplayedLandingPackTvEnVivo();
    }

    @Y("El usuario accede a Legales")
    public void elUsuarioAccedeALegales() {
        this.myAccountPageAction.clickLegal();
    }

    @Entonces("El usuario es redireccionado a la pantalla de Legales")
    public void elUsuarioEsRedireccionadoALaPantallaDeLegales() {
        webDriver.switchToNewTab(1);
        webDriver.checkUrlEquals("https://www.personal.com.ar/terminos-y-condiciones");
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'Fútbol'")
    public void seMuestraLaLandingDeOfertaDelPackFlexFutbol() {
        this.myAccountPageAction.checkOfferItemsPackFutbolUserFlex();
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'TV en vivo'")
    public void seMuestraLaLandingDeOfertaDelPackFlexTVEnVivo() {
        this.myAccountPageAction.checkOfferItemsPackTvLiveUserFlex();
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'Paramount+'")
    public void seMuestraLaLandingDeOfertaDelPackFlexParamount() {
        this.myAccountPageAction.checkOfferItemsPackParamountUserFlex();
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'HBO'")
    public void seMuestraLaLandingDeOfertaDelPackFlexHBO() {
        this.myAccountPageAction.checkOfferItemsPackHboUserFlex();
    }

    @Y("El usuario solicita la baja del pack 'Fútbol'")
    public void elUsuarioSolicitaLaBajaDelPackFutbol() {
        this.myAccountPageAction.clickModalPackFutbol();
        this.myAccountPageAction.clickCancelPackSubscription();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'Fútbol'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackFutbol() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Y("El usuario solicita la baja del pack 'Paramount+'")
    public void elUsuarioSolicitaLaBajaDelPackParamount() {
        this.myAccountPageAction.clickModalHBO();
        this.myAccountPageAction.clickCancelPackSubscription();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'Paramount+'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackParamount() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Entonces("La pantalla 'Suscripciones' se muestra correctamente")
    public void laPantallaSuscripcionesSeMuestraCorrectamente() {
        this.myAccountPageAction.pageIsDisplayedSubcription();
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'Star+'")
    public void seMuestraLaLandingDeOfertaDelPackFlexStar() {
        this.myAccountPageAction.checkOfferItemsStarPlusMySubscriptions();
    }

    @Y("El usuario solicita la baja del pack 'HBO'")
    public void elUsuarioSolicitaLaBajaDelPackHBO() {
        this.myAccountPageAction.clickModalHBO();
        this.myAccountPageAction.clickCancelPackSubscription();
    }

    @Entonces("Se solicita la confirmacion de la baja del pack 'HBO'")
    public void seSolicitaLaConfirmacionDeLaBajaDelPackHBO() {
        this.myAccountPageAction.cancelModalPackIsDisplayed();
    }

    @Entonces("Se muestra la landing de oferta del pack flex 'Disney+ y Star+'")
    public void seMuestraLaLandingDeOfertaDelPackFlexDisneyYStar() {
        this.myAccountPageAction.checkOfferItemsComboPlusMySubscriptions();
    }

    @Entonces("Los contenidos del pack Disney+ y Star+ se muestran correctamente")
    public void losContenidosDelPackDisneyYStarSeMuestranCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Los contenidos del pack Star+ se muestran correctamente")
    public void losContenidosDelPackStarSeMuestranCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Los contenidos del pack Disney+ se muestran correctamente")
    public void losContenidosDelPackDisneySeMuestranCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Los contenidos del pack HBO se muestran correctamente")
    public void losContenidosDelPackHBOSeMuestranCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Los contenidos del pack Paramount+ se muestran correctamente")
    public void losContenidosDelPackParamountSeMuestranCorrectamente() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Se muestra el pack 'Disney+ y Star+' activo en Suscripciones")
    public void seMuestraElPackDisneyYStarActivoEnSuscripciones() {
        this.myAccountPageAction.pageIsDisplayed();
    }

    @Entonces("Se muestra el pack 'Star+' activo en Suscripciones")
    public void seMuestraElPackStarActivoEnSuscripciones() {
        this.myAccountPageAction.checkPackStarPlusActive();
    }

    @Entonces("Se muestra el pack 'Disney+' activo en Suscripciones")
    public void seMuestraElPackDisneyActivoEnSuscripciones() {
        this.myAccountPageAction.checkPackDisneyPlusActive();
    }
}