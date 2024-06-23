package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyAccountPageFlow extends Page {

    protected OnboardingPageFlow onboardingPageFlow;

    protected String textBannerActive = "banner_active";
    protected String textBannerPromotion = "promotion";
    protected String textBannerComboUpgradePromotion = "banner_upgrade_combo";
    protected String textBannerComboPromotion = "banner_promotion_star";
    protected String textBannerStarLinkedPromotion = "banner_active_star_plus";
    protected String textBannerDisneyUnlinkedPromotion = "banner_upgrade_combo";
    protected String textBannerStarUnlinkedPromotion = "banner_active_star_plus";
    protected String textBannerHeaderStarPlus = "header-subscription";
    protected String textBannerComboDisneyLinkedMySubscriptions = "pack-combo";
    protected String textBannerComboLinkedPromotion = "banner_active_star_plus";
    protected String textBannerComboUnlinkedPromotion = "banner_active_star_plus";
    protected String textBannerHeaderSubscription = "header-subscription";
    protected String textBannerDisneyMySubscriptions = "pack-disney";
    protected String textBannerComboMySubscriptions = "pack-combo";
    // protected String bannerComboPromotion = "banner_promotion_combo";
    @FindBy(xpath = ".//span[contains(@class,'labelButton___3S8sm') and contains(text(),'SUSCRIBITE')]")
    protected WebElement buttonDisneySubscribe;
    @FindBy(xpath = ".//span[contains(@class,'labelButton___3S8sm') and contains(text(),'MAS INFO')]")
    protected WebElement buttonDisneyMoreInfo;
    @FindBy(xpath = ".//span[contains(@class,'labelButton___3S8sm') and contains(text(),'CANCELAR PACK')]")
    protected WebElement buttonDisneyCancel;
    @FindBy(xpath = ".//span[contains(@class,'labelButton___3S8sm') and contains(text(),'ACTIVAR CUENTA')]")
    protected WebElement buttonDisneyActivate;
    @FindBy(xpath = ".//p[contains(@class,'description') and contains(text(),'Tus historias favoritas, cuando vos quieras.')]")
    protected WebElement descriptionDisneyPack;
    @FindBy(xpath = ".//h2[contains(@class,'title') and contains(text(),'Mis Suscripciones')]")
    protected WebElement titleMySubscriptions;
    @FindBy(xpath = "//span[@class='status__pack status__pack--active']")
    protected WebElement packActiveDisney;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'DISNEY+')]/..//ancestor::article[contains(@class,'suscription')]")
    protected WebElement titleDisneyPlusMySubscription;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'DISNEY+')]/..//ancestor::article[contains(@class,'suscription')]/.//img[contains(@src,'active-pack-icon')]")
    protected WebElement titleDisneyPlusMySubscriptionActive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'DISNEY+')]/..//ancestor::article[contains(@class,'suscription')]/.//img[contains(@src,'pending-pack-icon')]")
    protected WebElement titleDisneyPlusMySubscriptionInactive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'DISNEY+')]/..//ancestor::article[contains(@class,'suscription')]/.//span[text()='CANCELAR PACK']")
    protected WebElement titleDisneyPlusMySubscriptionCancel;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'DISNEY+')]/..//ancestor::article[contains(@class,'suscription')]/..//span[text()='ACTIVAR CUENTA']")
    protected WebElement titleDisneyPlusMySubscriptionActivateAccount;
    @FindBy(xpath = "//h3[normalize-space()='Star+']")
    protected WebElement checkTitlePackActive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'STAR+')]/..//ancestor::article[contains(@class,'suscription')]/.//img[contains(@src,'pending-pack-icon')]")
    protected WebElement titleStarPlusMySubscriptionInactive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'STAR+')]/..//ancestor::article[contains(@class,'suscription')]/.//span[text()='ACTIVAR CUENTA']")
    protected WebElement titleStarPlusMySubscriptionActivateAccount;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'COMBO+')]/..//ancestor::article[contains(@class,'suscription')]")
    protected WebElement titleComboPlusMySubscription;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'COMBO+')]/..//ancestor::article[contains(@class,'suscription')]/.//img[contains(@src,'active-pack-icon')]")
    protected WebElement titleComboPlusMySubscriptionActive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'COMBO+')]/..//ancestor::article[contains(@class,'suscription')]/.//img[contains(@src,'pending-pack-icon')]")
    protected WebElement titleComboPlusMySubscriptionInactive;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'COMBO+')]/..//ancestor::article[contains(@class,'suscription')]/.//span[text()='CANCELAR PACK']")
    protected WebElement titleComboPlusMySubscriptionCancel;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'COMBO+')]/..//ancestor::article[contains(@class,'suscription')]/.//span[text()='ACTIVAR CUENTA']")
    protected WebElement titleComboPlusMySubscriptionActivateAccount;
    @FindBy(xpath = "//h3[@class='Pack_title__1V5Pk']")
    protected List<WebElement> positionsOffertMySubscriptions;
    protected By titlePosition = By.xpath("//h3[@class='title']");
    @FindBy(xpath = ".//p[contains(@class,'description') and contains(text(),'Tus historias favoritas, cuando vos quieras.')]")
    protected WebElement titleDisneyUnlinkedSubscribe;
    @FindBy(xpath = ".//button[contains(@class,'btn') and contains(text(),'términos y condiciones')]")
    protected WebElement titleTermsConditionsUnlinkedSubscribe;
    @FindBy(xpath = ".//span[contains(@class,'checkmark')]")
    protected WebElement checkTermsConditionsUnlinkedSubscribe;
    @FindBy(xpath = ".//span[contains(@class,'labelButton___3S8sm') and contains(text(),'Suscribite a Disney+')]")
    protected WebElement buttonDisneySubscriptionUnlinkedSubscribe;
    @FindBy(xpath = ".//button[contains(@class,'btn') and contains(text(),'MÁS INFO')]")
    protected WebElement titleMoreInfoUnlinkedSubscribe;
    @FindBy(xpath = ".//button[contains(@class,'btn btn--initial') and contains(text(),'LEGALES')]")
    protected WebElement titleLegalUnlinkedSubscribe;
    @FindBy(xpath = ".//p[contains(@class,'description') and contains(text(),'Te regalamos 30 días de suscripción.')]")
    protected WebElement offerDescription;
    @FindBy(xpath = "//a[@class='subscriptions']")
    protected WebElement dropdownEditProfile;
    @FindBy(xpath = "//img[@src='/img/uikit/avatar/1.svg']")
    protected WebElement dropdownButtonManage;
    @FindBy(xpath = "//h3[contains(@class,'title') and contains(text(),'Paramount+')]")
    protected WebElement packActiveParamountPlus;
    @FindBy(xpath = "//h3[contains(@class,'title') and contains(text(),'Disney+ y Star+')]")
    protected WebElement packActiveDisneyStarPlus;
    @FindBy(xpath = "//h3[contains(@class,'title') and contains(text(),'HBO')]")
    protected WebElement packActiveHBO;
    @FindBy(xpath = "//h3[normalize-space()='Disney+']")
    protected WebElement titleDisneyUnlinked;
    @FindBy(xpath = "//p[normalize-space()='Falta que actives tu cuenta']")
    protected WebElement descriptionUnlinked;
    @FindBy(xpath = "//span[@class='activate']")
    protected WebElement buttonActivePackUnlinked;
    @FindBy(xpath = "//p[normalize-space()='Accede a Disney+ y Star+ a un precio único.']")
    protected WebElement descriptionComboUnlinkend;
    @FindBy(xpath = "//img[@src='/img/packs/pack_combo.svg']")
    protected WebElement imageComboPlusUnlinked;
    @FindBy(xpath = "//h3[normalize-space()='Disney+ y Star+']")
    protected WebElement titleComboPlusUnlinked;
    @FindBy(xpath = "//img[@src='/img/packs/pack_disney.svg']")
    protected WebElement imageDisneyPlus;
    @FindBy(xpath = "//img[@src='/img/packs/pack_star.svg']")
    protected WebElement imageStarPlus;
    @FindBy(xpath = "//img[@src='/img/packs/pack_futbol.svg']")
    protected WebElement modalFutbol;
    @FindBy(xpath = "//h3[@class=\"Pack_title__1V5Pk\" and contains(text(),'TV en vivo')]")
    protected WebElement modalTvEnVivo;
    @FindBy(xpath = "//img[@src='/img/packs/grilla/logo.svg']")
    protected WebElement tvEnVivoLogo;
    @FindBy(xpath = "//h2[normalize-space()='Tv en vivo']")
    protected WebElement titlePackTvEnVivo;
    @FindBy(xpath = "//p[normalize-space()='Suscripción mensual']")
    protected WebElement titleSubscriptionMonthly;
    @FindBy(xpath = "//span[normalize-space()='suscribirme']")
    protected WebElement buttonSubscriptionLandingTvEnVivo;
    @FindBy(xpath = "//p[normalize-space()='Pack por 30 días']")
    protected WebElement titlePackFor30days;
    @FindBy(xpath = "//span[normalize-space()='activalo por 30 días']")
    protected WebElement buttonPackFor30Days;
    @FindBy(xpath = "//p[normalize-space()='Pack por 15 días']")
    protected WebElement titlePackFor15Days;
    @FindBy(xpath = "//span[normalize-space()='activalo por 15 días']")
    protected WebElement buttonPackFor15Days;
    @FindBy(xpath = "//div[contains(@class,'pack__cards')]//button[contains(@type,'button')][normalize-space()='Next']")
    protected WebElement HeroLandingPackTvEnVivo;
    @FindBy(xpath = "//p[normalize-space()='Pack por 7 días']")
    protected WebElement titlePackFor7Days;
    @FindBy(xpath = "//span[normalize-space()='activalo por 7 días']")
    protected WebElement buttonPackFor7days;
    @FindBy(xpath = "//p[normalize-space()='Pack por 3 días']")
    protected WebElement titlePackFor3Days;
    @FindBy(xpath = "//span[normalize-space()='activalo por 3 días']")
    protected WebElement buttonPackFor3Days;
    @FindBy(xpath = "//h2[normalize-space()='Suscribite a Fútbol']")
    protected WebElement offerItemsTitlePackFutbol;
    @FindBy(xpath = ".//button[@type='submit']/div[contains(@class,'button')]/span[text()='SUSCRIBIRME']")
    protected WebElement offerItemsButtonFutbolSubscribe;
    @FindBy(xpath = "//span[contains(@class,'UIKit_labelButton__2lELi') and text()='suscribirme']")
    protected WebElement buttonSubscriptionMonthly;
    @FindBy(xpath = "//span[contains(@class,'UIKit_labelButton__2lELi') and text()='activalo por 1 día']")
    protected WebElement buttonSubscriptionOneDay;
    @FindBy(xpath = "//span[contains(@class,'UIKit_labelButton__2lELi') and text()='activalo por 15 días']")
    protected WebElement buttonSubscription15Days;
    @FindBy(xpath = "//span[contains(@class,'UIKit_labelButton__2lELi') and text()='activalo por 30 días']")
    protected WebElement buttonSubscription30Days;
    @FindBy(xpath = "//div[@class='flowModalHead']")
    protected WebElement titleMessagecancelPack;
    @FindBy(xpath = ".//p[contains(@class,'style_') and contains(text(),'Tené en cuenta que puede demorar algunos días hasta que la baja se haga efectiva.')]")
    protected WebElement warnigMenssage;
    @FindBy(xpath = "//span[normalize-space()='Cancelar']")
    protected WebElement buttonCancel;
    @FindBy(xpath = "//span[normalize-space()='Confirmar']")
    protected WebElement buttonConfirm;
    @FindBy(xpath = "//button[normalize-space()='Administrar dispositivos']")
    protected WebElement buttonDeviceManage;
    @FindBy(xpath = ".//button[contains(@type,'button') and contains(text(),'Administrar')]")
    protected WebElement configurationButton;
    @FindBy(xpath = ".//p[contains(@class,'text') and contains(text(),'Ingresá a configurar tu cuenta Flow')]")
    protected WebElement configurationTextRedirect;
    @FindBy(xpath = ".//img[contains(@alt,'Ingresá a configurar tu cuenta Flow')]")
    protected WebElement configurationButtonRedirect;
    @FindBy(xpath = ".//h3[contains(@class,'primary-color') and contains(text(),'Bienvenido a la configuración de Flow')]")
    protected WebElement configurationRedirectHomePage;
    @FindBy(xpath = ".//img[contains(@src,'logo-flow')]")
    protected WebElement configurationIconFlow;
    @FindBy(xpath = ".//p[contains(@class,'description') and contains(text(),'crear perfiles de usuario, gestionar tus dispositivos')]")
    protected WebElement configurationTextFlow;
    @FindBy(xpath = ".//img[contains(@src,'phone')]")
    protected WebElement configurationCallCenterFlow;
    @FindBy(xpath = ".//input[contains(@class,'btn btn-alargado') and @value='Ingresar']")
    protected WebElement configurationLoginFlow;
    @FindBy(xpath = ".//a[contains(@class,'btn btn-alargado') and text()=' Registrarme ']")
    protected WebElement configurationRegisterFlow;
    @FindBy(xpath = ".//p[@class='description' and contains(text(),'Combo+, una oferta única con Disney+ y Star+')]")
    protected WebElement descriptionHeaderComboPromotion;
    @FindBy(xpath = ".//p[@class='description' and contains(text(),'Series, películas y eventos deportivos de ESPN')]")
    protected WebElement descriptionHeaderStarPromotion;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'disney')]")
    protected WebElement buttonSuscribeHeaderComboPromotion;
    @FindBy(xpath = ".//span[contains(text(),'finales por mes')]")
    protected WebElement priceHeaderComboPromotion;
    @FindBy(xpath = ".//p[@class='description' and contains(text(),'Combo+, una oferta única con Disney+ y Star+')]/preceding-sibling::button[@type='button' and @class='title' and text()='COMBO+']")
    protected WebElement descriptionComboMySubscriptions;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='COMBO+']")
    protected WebElement titleComboMySubscriptions;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='COMBO+']/..//ancestor::article[contains(@class,'suscription')]")
    protected WebElement contextComboPlus;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='COMBO+']/..//ancestor::article[contains(@class,'suscription')]/.//button[@type='submit']")
    protected WebElement comboPlusSubmit;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='DISNEY+']/..//ancestor::article[contains(@class,'suscription')]")
    protected WebElement contextDisneyPlus;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='DISNEY+']/..//ancestor::article[contains(@class,'suscription')]/.//button[@type='submit']")
    protected WebElement disneyPlusSubmit;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='STAR+']/..//ancestor::article[contains(@class,'suscription')]")
    protected WebElement contextStarPlus;
    @FindBy(xpath = ".//button[@type='button' and @class='title' and text()='STAR+']/..//ancestor::article[contains(@class,'suscription')]/.//button[@type='submit']")
    protected WebElement starPlusSubmit;
    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement buttonSubscribeMySubscriptions;
    @FindBy(xpath = ".//span[text()='CANCELAR PACK']")
    protected WebElement buttonCancelPackMySubscriptions;
    @FindBy(xpath = "//button[@class='closeButon']")
    protected WebElement closePopUp;
    @FindBy(xpath = "//img[contains(@src,'/img/comboplus/')]")
    protected WebElement popUpPromotion;
    protected By bannerHomeComboUnlinked = By.xpath(".//img[contains(@src, '" + textBannerComboUnlinkedPromotion + "')]");
    protected By bannerHomeDisneyUnlinked = By.xpath(".//img[contains(@src, '" + textBannerDisneyUnlinkedPromotion + "')]");
    protected By bannerHomeStarUnlinked = By.xpath(".//img[contains(@src, '" + textBannerStarUnlinkedPromotion + "')]");

    @FindBy(xpath = "//h2[normalize-space()='Suscribite a Disney+ y Star+']")
    protected WebElement offerItemsTitleComboPlus;
    @FindBy(xpath = ".//span[@class='checkmark']")
    protected WebElement offerItemsCheckTermsCondComboPlusMySubscriptions;
    @FindBy(xpath = ".//p[contains(text(),'Acepto los ')]/button[@class='btn']")
    protected WebElement offerItemsTextTermsCondComboPlusMySubscriptions;
    @FindBy(xpath = ".//button[@type='submit']/div[contains(@class,'button')]/span[text()='SUSCRIBIRME']")
    protected WebElement offerItemsButtonSubscribeMySubscriptions;
    @FindBy(xpath = "//h2[normalize-space()='Suscribite a Disney+']")
    protected WebElement offerItemsTitleDisneyPlus;

    @FindBy(xpath = ".//span[@class='checkmark']")
    protected WebElement offerItemsCheckTermsCondDisneyPlus;
    @FindBy(xpath = ".//p[@class='text' and contains(text(),'Acepto los')]/button[@class='btn' and @type='button' and text()='términos y condiciones']")
    protected WebElement offerItemsTextTermsCondDisneyPlus;
    @FindBy(xpath = "//*[@id=\"signup\"]")
    protected WebElement offerItemsButtonSubscribe;
    @FindBy(xpath = "//button[@type='button' and contains(text(),'Legales')]")
    protected WebElement offerItemsButtonLegal;
    @FindBy(xpath = ".//article[@class='disney-plus']/header[contains(@class,'disney-plus')]/img[@class='logo' and contains(@src,'starplus/header-subscription')]")
    protected WebElement offerItemsImgStarPlusMySubscriptions;
    @FindBy(xpath = "//h2[normalize-space()='Suscribite a Star+']")
    protected WebElement offerItemsTitleStarPlus;
    @FindBy(xpath = ".//span[@class='checkmark']")
    protected WebElement offerItemsCheckTermsCondStarPlus;
    @FindBy(xpath = ".//p[@class='text' and contains(text(),'Acepto los')]/button[@class='btn' and @type='button' and text()='términos y condiciones']")
    protected WebElement offerItemsTextTermsCondStarPlus;
    @FindBy(xpath = ".//button[@type='submit']/div[contains(@class,'button')]/span[contains(text(),'Star+')]")
    protected WebElement offerItemsButtonSubscribeStarPlusMySubscriptions;
    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement buttonActivateAccountMySubscriptions;
    @FindBy (xpath = "//img[@src='/img/packs/disney/logo.svg']")
    protected WebElement disneyLogo;
    @FindBy(xpath = "//h2[normalize-space()='Disney+']")
    protected WebElement titleDisney;
    @FindBy(xpath = "//img[@src='/img/packs/star/logo.svg']")
    protected WebElement starLogo;
    @FindBy(xpath = "//span[contains(text(),' Pack activo')]")
    protected WebElement starPackActive;
    @FindBy(xpath = "//h2[normalize-space()='Star+']")
    protected WebElement titleStar;
    @FindBy(xpath = "//img[@src='/img/packs/combo/logo.svg']")
    protected WebElement comboLogo;
    @FindBy(xpath = "//h2[contains(text(),'Disney+ y Star+')]")
    protected WebElement titleCombo;
    @FindBy(xpath = "//img[@src='/img/packs/grilla/logo.svg']")
    protected WebElement tvLiveLogo;
    @FindBy(xpath = "//h2[normalize-space()='Tv en vivo']")
    protected WebElement offerItemsTitlePackTvLive;
    @FindBy(xpath = "//img[@src='/img/packs/futbol/logo.svg']")
    protected WebElement futbolLogo;
    @FindBy(xpath = "//img[@src='/img/packs/paramount/logo.svg']")
    protected WebElement paramountPlusLogo;
    @FindBy(xpath = "//h2[normalize-space()='Activá Paramount+']")
    protected WebElement offerItemsTitlePackParamountPlus;
    @FindBy(xpath = ".//button[@type='submit']/div[contains(@class,'button')]/span[text()='ACTIVAR']")
    protected WebElement offerItemsButtonParamountPlusSubscribe;
    @FindBy(xpath = "//div[@class='description__container']")
    protected WebElement offerItemsDescriptionPackParamountPlus;
    @FindBy(xpath = "//p[contains(text(),'Acceso a las plataformas Disney+ y Star+.')]")
    protected WebElement featureDisneyYstarAccess;
    @FindBy(xpath = "//p[contains(text(),'Selección de contenidos de Disney+ en 4K.')]")
    protected WebElement featureContentsDisneyYstar4k;
    @FindBy(xpath = "//p[contains(text(),'Hasta 4 pantallas en simultáneo por cada plataforma.')]")
    protected WebElement featureAvailable4ScreensCombo;
    @FindBy(xpath = "//p[contains(text(),'Descargas hasta en 10 dispositivos por plataforma.')]")
    protected WebElement featureDownloadDevices;
    @FindBy(xpath = "//p[contains(text(),'7 perfiles personalizados por cada plafatorma')]")
    protected WebElement featureCustomPerfil;
    @FindBy(xpath = "//p[contains(text(),'Los mejores eventos deportivos en vivo de ESPN.')]")
    protected WebElement featureEventsDeportive;
    @FindBy(xpath = "//h2[normalize-space()='Suscribite a HBO']")
    protected WebElement offerItemsTitlePackHbo;
    @FindBy(xpath = ".//div[contains(text(),'$4000 finales por mes')]")
    protected WebElement offerItemsDescriptionPackHbo;
    @FindBy(xpath = "//img[@src='/img/packs/hbo/logo.svg']")
    protected WebElement hboLogo;
    @FindBy(xpath = "//h2[contains(text(),'HBO')]")
    protected WebElement titleHBO;
    @FindBy(xpath = "//h2[contains(text(),'Fútbol')]")
    protected WebElement titlePackFutbol;
    @FindBy(xpath = "//p[contains(text(),'8 canales HD con los últimos lanzamientos.')]")
    protected WebElement featureAvailableChannels;
    @FindBy(xpath = "//p[contains(text(),'2 canales premium HD')]")
    protected WebElement featureAvailablePackFutbol;
    @FindBy(xpath = "//p[contains(text(),'Todos los partidos del Fútbol Argentino')]")
    protected WebElement featureAvailableAllMachesPackFutbol;
    @FindBy(xpath = "//p[contains(text(),'Contenidos on demand para que veas cuando quieras.')]")
    protected WebElement featureContentOndemand;
    @FindBy(xpath = "//img[@src='/img/packs/paramount/logo.svg']")
    protected WebElement paramountLogo;
    @FindBy(xpath = "//h2[contains(text(),'Paramount+')]")
    protected WebElement titleParamount;
    @FindBy(xpath = ".//img[contains(@src, 'pack-star')]")
    protected WebElement featureStasrAccess;
    @FindBy(xpath = "//p[contains(text(),'Acceso a todo el contenido de Paramount en Flow')]")
    protected WebElement featureParamountAccess;
    @FindBy(xpath = ".//p[contains(text(),'Medio de pago:')]")
    protected WebElement packStatusInfo;
    @FindBy(xpath = ".//p[contains(text(),'Se agrega a la factura de tu cuenta')]")
    protected WebElement descriptionPaymentMethod;
    @FindBy(xpath = ".//div[@class='pack__features']")
    protected WebElement packFeatures;
    @FindBy(xpath = "//p[contains(text(),'Acceso a la plataforma de Disney+.')]")
    protected WebElement featureDisneyAccess;
    @FindBy(xpath = "//p[contains(text(),'Hasta 4 pantallas en simultáneo.')]")
    protected WebElement featureAvailableScreens;
    @FindBy(xpath = "//p[contains(text(),'Descargas ilimitadas hasta en 10 dispositivos.')]")
    protected WebElement featureLimitDowndload;
    @FindBy(xpath = "//p[contains(text(),'Películas y Series de Paramount, Nickelodeon, MTV y más!')]")
    protected WebElement featureMovieSeriesAndMore;
    @FindBy(xpath = "//p[contains(text(),'Selección de contenidos de Disney+ en 4k.')]")
    protected WebElement featureContents4k;
    @FindBy(xpath = "//p[contains(text(),'Series originales, shows exclusivos y películas')]")
    protected WebElement featureSeriesShowExclusive;
    @FindBy(xpath = ".//button[contains(text(),'Quiero dar de baja mi suscripción')]")
    protected WebElement cancelSubscription;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'flowPrimaryButton')]")
    protected WebElement buttonExploreContent;
    @FindBy(xpath = ".//div[contains(@class,'contentStripeHeader row')]")
    public List<WebElement> stripes;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe')]")
    public List<WebElement> contents;
    @FindBy(xpath = "//h1[contains(@class,'wrapper') and text()='Suscripciones']")
    public WebElement titleSubscription;
    @FindBy(xpath = "//h1[text()='Mi cuenta']")
    public WebElement titleMyAccount;
    @FindBy(xpath = "//main[@class=\"main main--configuration\"='Mi cuenta']")
    public WebElement menuConfiguration;
    @FindBy(xpath = "//h2[contains(@class,'PacksSection_title__1ZcMG') and  text()='Estas disfrutando de estos packs']")
    public WebElement packsContracted;
    @FindBy(xpath = "//h2[contains(@class,'PacksSection_title__1ZcMG') and  text()='Suscribite y potenciá tu experiencia']")
    public WebElement packsDisplayed;
    @FindBy(xpath = ".//form[@id='dssLogin']")
    protected WebElement formLoginStarPlus;
    @FindBy(xpath = ".//button[@type='submit' and @class='flowPrimaryButton___34v3n primaryButton mediumButton ']")
    protected WebElement popUpConfigureButton;
    @FindBy(xpath = ".//p[text()='Control parental']")
    protected WebElement dropDownParentalControl;
    protected By buttonActivateAccountPackMySubscriptions = By.xpath("v");
    @FindBy(xpath = "//h3[text()=\"Disney+ y Star+\"]/following-sibling::div/span[normalize-space()=\"Pack activo\"]")
    protected WebElement PackActiveIconMySubscripton;
    protected By packInactiveIconMySubscriptions = By.xpath(".//img[contains(@src,'pending-pack-icon')]");
    protected By buttonTermsHeaderComboPromotion = By.xpath(".//button[@class='btn' and contains(text(),'términos y condiciones')]");
    protected By checkmarckTermsHeaderComboPromotion = By.xpath(".//span[@class='checkmark']");

    public MyAccountPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
        this.onboardingPageFlow = new OnboardingPageFlow(webDriver);
    }

    public By getTitlePosition() {
        return titlePosition;
    }

    public By getBannerHomeComboUnlinked() {
        return bannerHomeComboUnlinked;
    }

    public By getBannerHomeDisneyUnlinked() {
        return bannerHomeDisneyUnlinked;
    }

    public By getBannerHomeStarUnlinked() {
        return bannerHomeStarUnlinked;
    }

    public By getButtonActivateAccountPackMySubscriptions() {
        return buttonActivateAccountPackMySubscriptions;
    }

    public By getPackInactiveIconMySubscriptions() {
        return packInactiveIconMySubscriptions;
    }

    public By getCheckmarckTermsHeaderComboPromotion() {
        return checkmarckTermsHeaderComboPromotion;
    }

    public By getButtonTermsHeaderComboPromotion() {
        return buttonTermsHeaderComboPromotion;
    }

    public WebElement getTitleMessagecancelPack() { return titleMessagecancelPack; }

    public WebElement getWarnigMenssage() { return warnigMenssage; }

    public WebElement getButtonCancel() { return buttonCancel; }

    public WebElement getButtonConfirm() { return buttonConfirm; }


    public List<WebElement> getTextBannerPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getTextBannerComboUpgradePromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboUpgradePromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getBannerStarPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerStarLinkedPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getTextBannerComboPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getTextBannerComboLinkedPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboLinkedPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getTextBannerComboUnlinkedPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboUnlinkedPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public List<WebElement> getBannerStarlinkedPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerStarUnlinkedPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public void checkBannerStarUnlinkedPromotion() {
        List<WebElement> banners = getBannerStarlinkedPromotion();
        assertEquals("Banner_promotion is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerStarUnlinkedPromotion + " is displayed");
    }

    public void checkBannerPromotion() {
        List<WebElement> banners = getTextBannerPromotion();
        assertEquals("Banner_promotion is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerPromotion + " is displayed");
    }

    public void checkBannerComboUpgrade() {
        List<WebElement> banners = getTextBannerComboUpgradePromotion();
        assertEquals("Combo+ upgrade promotional banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerComboUpgradePromotion + " is displayed");
    }

    public void checkBannerComboPromotion() {
        List<WebElement> banners = getTextBannerComboPromotion();
        assertEquals("Combo+ promotion banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerComboPromotion + " is displayed");
    }

    public void checkBannerComboLinkedPromotion() {
        List<WebElement> banners = getTextBannerComboUnlinkedPromotion();
        assertEquals("Combo+ linked promotion banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerComboUnlinkedPromotion + " is displayed");
    }

    public void checkBannerStarLinkedPromotion() {
        List<WebElement> banners = getBannerStarPromotion();
        assertEquals("Star+ linked promotion banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerStarLinkedPromotion + " is displayed");
    }

    public void checkBannerComboUnlinkedPromotion() {
        List<WebElement> banners = getTextBannerComboLinkedPromotion();
        assertEquals("Combo+ linked promotion banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerComboUnlinkedPromotion + " is displayed");
    }

    public List<WebElement> getTextBannerActive() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerActive + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public void checkBannerActive() {
        List<WebElement> banners = getTextBannerActive();
        assertEquals("Banner_active is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerActive + " is displayed");
    }

    public List<WebElement> getBannerSubscription() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerHeaderSubscription + "')]";
        By locatorBy = By.xpath(bannerLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public void checkBannerSubscription() {
        List<WebElement> banners = getBannerSubscription();
        assertEquals("Subscription banner is not displayed", 1, banners.size());
        logger.info("Banner " + textBannerHeaderSubscription + " is displayed");
    }

    public List<WebElement> getDisneySubscriptionActive() {
        String subscriptionActive = ".//img[contains(@src,'disneyplus/active-pack-icon')]";
        By subscriptionActiveBy = By.xpath(subscriptionActive);
        return this.webDriver.findElements(subscriptionActiveBy);
    }

    public void checkDisneySubscriptionNotExists() {
        List<WebElement> contentItems = getDisneySubscriptionActive();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        assertEquals("Active subscription icon is diplayed", 0, contentItems.size());
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public List<WebElement> getDisneySubscriptionPending() {
        String subscriptionPending = ".//img[contains(@src,'disneyplus/pending-pack-icon')]";
        By subscriptionsubscriptionPendingBy = By.xpath(subscriptionPending);
        return this.webDriver.findElements(subscriptionsubscriptionPendingBy);
    }

    public void checkDisneySubscriptionPending() {
        List<WebElement> contentItems = getDisneySubscriptionPending();
        assertEquals("Pending subscription icon is not diplayed", 1, contentItems.size());
    }

    public void checkDisneySubscriptionExists() {
        List<WebElement> contentItems = getDisneySubscriptionActive();
        assertEquals("Active subscription icon is not diplayed", 1, contentItems.size());
    }

    public List<WebElement> getContentDisneyMySubscriptions() {
        By disneyMySubscriptionsBy = By.xpath(".//img[contains(@src,'disneyplus/pack')]");
        return this.webDriver.findElements(disneyMySubscriptionsBy);
    }

    public void checkContentImageDisneyMySubscriptions() {
        List<WebElement> contentItems = getContentDisneyMySubscriptions();
        assertEquals("Banner 'My subscriptions' is not displayed ", 1, contentItems.size());
    }

    public boolean checkContentTitleMySubscriptions() {
        return titleMySubscriptions.isDisplayed();
    }

    public boolean checkContentDescriptionDeactivatedDisneyMySubscriptions() {
        return descriptionDisneyPack.isDisplayed();
    }

    public boolean checkContentButtonSubscribeMySubscriptions() {
        return buttonDisneySubscribe.isDisplayed();
    }

    public boolean checkContentButtonCancelMySubscriptions() {
        return buttonDisneyCancel.isDisplayed();
    }

    public boolean checkContentButtonActivateMySubscriptions() {
        return buttonDisneyActivate.isDisplayed();
    }

    public void checkContentButtonMoreInfoMySubscriptions() {
        assertTrue("Button 'MAS INFO' is not displayed", buttonDisneyMoreInfo.isDisplayed());
    }

    public boolean checkContentTitleDisneyPlusMySubscriptions() {
        return titleDisneyPlusMySubscription.isDisplayed();
    }

    public boolean checkContentTitleSubscribe() {
        return titleDisneyUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentTitleTermsConditionsSubscribe() {
        return titleTermsConditionsUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentCheckTermsConditionsSubscribe() {
        return checkTermsConditionsUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentButtonDisneySubscriptionSubscribe() {
        return buttonDisneySubscriptionUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentTitleMoreInfoSubscribe() {
        return titleMoreInfoUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentTitleLegalSubscribe() {
        return titleLegalUnlinkedSubscribe.isDisplayed();
    }

    public boolean checkContentOfferDescriptionMySubscriptions() {
        return offerDescription.isDisplayed();
    }
}
