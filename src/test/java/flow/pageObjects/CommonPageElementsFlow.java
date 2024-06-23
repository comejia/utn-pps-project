package flow.pageObjects;

import ch.qos.logback.classic.Logger;
import flow.configuration.IProperties;
import flow.utils.UtilsProperties;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommonPageElementsFlow {

    protected final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(this.getClass()));

    protected IProperties props;

    protected ExtendedWebDriver webDriver;
    @FindBy(xpath = ".//a[contains(@class,'grabaciones') and text()='Grabaciones']")
    protected WebElement navBarRecordings;
    @FindBy(xpath = ".//div[contains(@class,'loadingWrapper')]")
    protected WebElement buttonPlaySuperHero;
    @FindBy(xpath = ".//div[contains(@class,'loadingWrapper')]")
    protected WebElement loadingWrapper;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'flowPrimaryButton')]")
    protected WebElement playButtonDIP;
    @FindBy(xpath = ".//div[@class='seeMore']/a")
    protected WebElement contentStripe;
    @FindBy(className = "stripeTitle")
    protected WebElement contentStripeStripeTitle;
    @FindBy(xpath = ".//div[contains(@class,'channelStripe')]")
    protected WebElement channelStripe;
    @FindBy(xpath = ".//div[contains(@class,'channelItemWrapper')]")
    protected WebElement channelItemWrapper;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]")
    protected WebElement itemWrapper;
    @FindBy(xpath = ".//div[contains(@class,'item') and contains(@class,'undefined')]")
    protected WebElement itemUndefined;
    @FindBy(xpath = ".//div[contains(@class,'item') and contains(@class,'restricted')]")
    protected WebElement itemRestricted;
    @FindBy(xpath = ".//div[contains(@class,'slick-slide') and contains(@class,'slick-active')]//div[contains(@class,'item') and contains(@class,'restricted')]/../..")
    protected List<WebElement> itemRestrictedActive;
    @FindBy(className = "truncate2lines")
    protected WebElement itemInfoTruncateLines;
    @FindBy(xpath = ".//div[@class='itemInfo']")
    protected WebElement itemInfo;
    @FindBy(xpath = ".//div[@class='itemDuration']/p[@class='duration']")
    protected WebElement itemDuration;
    @FindBy(className = "ageWrapper")
    protected WebElement itemInfoAgeWrapper;
    @FindBy(className = "carousel-fade")
    protected WebElement carouselFade;
    @FindBy(xpath = ".//a[contains(@class,'nav-link') and @href='/inicio']")
    protected WebElement navbarContentHome;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-prev vertical-align']")
    protected WebElement superheroCarouselArrowPrev;
    @FindBy(css = "[class=\"carousel\"] [class='slick-arrow slick-next vertical-align']")
    protected WebElement superheroCarouselArrowNext;
    @FindBy(xpath = ".//div[@role='alert']")
    protected WebElement notificationAlert;
    @FindBy(xpath = ".//div[@role='alert']")
    protected WebElement navbarContentHomeInactive;
    @FindBy(xpath = ".//a[@href='/guia-de-tv']")
    protected WebElement navbarContentTvGuide;
    @FindBy(xpath = ".//label[@class='inputErrorMessage visible']")
    protected WebElement messageErrorIncorrectPinParentalControl;
    @FindBy(xpath = ".//a[@href='/on-demand']")
    protected WebElement navbarContentOnDemand;
    @FindBy(xpath = ".//a[@href='/peliculas']")
    protected WebElement navbarContentMovies;
    @FindBy(xpath = ".//a[@href='/series']")
    protected WebElement navbarContentSeries;
    @FindBy(xpath = ".//a[@href='/dyn/alquileres/244']")
    protected WebElement navbarContentRentedItems;
    @FindBy(xpath = ".//a[@href='/kids']")
    protected WebElement navbarContentKids;
    @FindBy(xpath = ".//a[@href='/programas-emitidos']")
    protected WebElement navbarContentTvProgramsAired;
    @FindBy(xpath = ".//div[contains(@class,'nav-link ')]")
    protected WebElement navbarContentMyContent;
    @FindBy(xpath = ".//a[text()='Mi Lista']")
    protected WebElement navbarMyContentFavorites;
    @FindBy(xpath = ".//button[@type='submit']/div[contains(@class,'button')]/span[text()='ESTA EMISIÓN']")
    protected WebElement elementThisEmission;
    @FindBy(xpath = ".//a[text()='Grabaciones']")
    protected WebElement navbarMyContentRecordings;
    @FindBy(xpath = ".//a[text()='Continuar viendo']")
    protected WebElement navbarMyContentContinueWatching;
    @FindBy(id = "searchWrapper")
    protected WebElement buttonSearchWrapper;
    @FindBy(xpath = "//input[contains(@class,'searchInput') and contains(@class,'open') and @type='text']")
    protected WebElement inputSearchInput;
    @FindBy(xpath = ".//*[contains(@class,'userDrop')]")
    protected WebElement dropdownUser;
    @FindBy(xpath = ".//button[contains(@class,'Modal_intertitialModal__closeButon__3Vrzs') and contains(@type,'button')]")
    protected WebElement closePopUpHome;
    @FindBy(xpath = "//button[@class='Modal_intertitialModal__closeButon__3Vrzs']")
    protected WebElement closePopUpHomeParamount;
    @FindBy(xpath = ".//img[contains(@src, '/img/disneyplus')]")
    protected WebElement bannerDisney;
    @FindBy(xpath = "//a[contains(@class,'suscripciones')]")
    protected WebElement subscriptions;
    @FindBy(xpath = ".//button[contains(@data-testid, 'login')]")
    protected WebElement externalLoginDisney;
    @FindBy(xpath = ".//button[contains(@data-testid, 'signup')]")
    protected WebElement externalSubscriptionDisney;
    @FindBy(xpath = ".//img[contains(@src,'disneyplus/pack')]")
    protected WebElement subscriptionDisneyActive;
    @FindBy(xpath = ".//ul[contains(@class,'menu--account')]")
    protected WebElement menuAccount;
    @FindBy(xpath = "//a[text()='Mi cuenta']")
    protected WebElement dropDownMyAccount;
    @FindBy(xpath = ".//a[@class='user truncate']")
    protected WebElement dropdownEditProfile;
    @FindBy(xpath = ".//div[@class='flowDropDownRow']/a[contains(@href,'ayudaysoporte.personal.com.ar')]")
    protected WebElement dropdownConfiguration;
    @FindBy(xpath = ".//a[contains(@href,'ayudaysoporte.personal.com.ar')]")
    protected WebElement dropdownHelp;
    @FindBy(xpath = ".//a[contains(@class,'changeProfile')]")
    protected WebElement dropdownChangeProfile;
    @FindBy(xpath = ".//div[contains(@class,'parentalControl')]")
    protected WebElement dropdownParentalControl;
    @FindBy(xpath = "//p[@class='style_generic__modal__message__3bGnW']")
    protected WebElement MessageSettingControlParental;
    @FindBy(xpath = "//h3[@class='style_generic__modal__header__2DMf_']")
    protected WebElement titleControlParental;
    @FindBy(xpath = "//button[@class='UIKit_flowPrimaryButton__1nZBC style_primary__30ETo']")
    protected WebElement buttonSetting;
    @FindBy(xpath = "//span[contains(@class,UIKit_labelButton__2lELi) and text()='CANCELAR']")
    protected WebElement buttonClose;
    @FindBy(xpath = ".//a[text()='Cerrar sesión']")
    protected WebElement dropdownItemLogout;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe')]")
    public List<WebElement> stripe;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe ')]")
    public List<WebElement> stripes;
    @FindBy(xpath = ".//div[@class='contentStripe']")
    public List<WebElement> stripesRent;
    @FindBy(xpath = ".//a[@class='stripeTitle']/h3")
    protected WebElement stripeTitle;
    @FindBy(xpath = ".//a[@class='stripeTitle']/h3")
    protected List<WebElement> stripeTitles;
    @FindBy(className = "searchStripe")
    protected List<WebElement> stripeSearchStripe;
    @FindBy(xpath = ".//div[contains(@class,'flowModalContent') and contains(@class,'parentalPinModal')]")
    protected WebElement parentalControlModal;
    @FindBy(xpath = ".//div[contains(@class,'flowModalHead')]")
    protected WebElement parentalControlModalHead;
    @FindBy(xpath = ".//button[@class='closeButon']")
    protected WebElement parentalControlModalCloseButton;
    @FindBy(id = "pin")
    protected WebElement parentalControlModalPinInput;
    @FindBy(xpath = ".//button[contains(@class,'secondaryButton') and text()='CANCELAR']")
    protected WebElement parentalControlModalCancelButton;
    @FindBy(xpath = ".//button[contains(@class,'primaryButton') and text()='CONFIRMAR']")
    protected WebElement parentalControlModalConfirmButton;
    @FindBy(xpath = ".//img[@alt='close button']")
    protected WebElement tourCloseButton;
    @FindBy(xpath = ".//button[@type='submit'][contains(@class,'secondaryButton') and contains(@class,'text-uppercase')]")
    protected WebElement bingeCancelButton;
    @FindBy(xpath = ".//div[@class='contentStripe Inicio' and .//h3[text()='TV en vivo']]/div//div[contains(@class, 'progressBar liveContent')]")
    protected WebElement progressBarLiveTv;
    @FindBy(xpath = ".//div[@class='contentStripe Inicio' and .//h3[text()='Continuar viendo']]/div//div[@class='progressBar']")
    protected WebElement progressBarContinueWatching;
    @FindBy(xpath = ".//div[@class='slick-slide slick-active slick-current']//button[@type='submit'][contains(@class, 'flowPrimaryButton') and contains(@class,'flowBigButton')]//.//span[@class='labelButton']")
    protected WebElement carouselLabelButton;
    @FindBy(xpath = ".//div[@class='slick-slide slick-active slick-current']//button[@type='submit'][contains(@class, 'flowPrimaryButton') and contains(@class,'flowBigButton')]")
    protected WebElement currentActiveCarouselPlayButton;
    @FindBy(xpath = ".//div[@class='progressBar']")
    protected WebElement progressBar;
    @FindBy(xpath = ".//div[@class='flowModalBody']")
    protected WebElement messageConfiguration;

    @FindBy(xpath = ".//div[@class='flowModalHead']")
    protected WebElement titleMessageConfiguration;

    @FindBy(xpath = ".//*[text()='CANCELAR']//ancestor::button")
    protected WebElement buttonCancel;

    @FindBy(xpath = ".//*[text()='CONFIGURAR']//ancestor::button")
    protected WebElement buttonConfigure;
    @FindBy(xpath = "//title[text()='Centro de Ayuda de Facturación en Personal']")
    protected WebElement titleHelp;

    protected By itemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");

    public static By img = By.xpath(" .//div[@id='flow-lazy-img']/img");
    public WebElement elementStripe;
    public CommonPageElementsFlow(ExtendedWebDriver webDriver) {
        //this.props = new Properties();
        this.webDriver = webDriver;
        this.props = flow.core.PageFactory.getProps();
        PageFactory.initElements(webDriver, this);
        //this.loadProperties();
    }

    public By getChannelStripe() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_channelStripe());
    }

    public By getChannelItem() {
        return UtilsProperties.getByLocator(props.commonPageElements_channelItem());
    }

    public By getItemPlayIcon() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_itemPlayIcon());
    }
    public By seeMoreBy = By.xpath(".//a[@class='titleLink']");

    public By getItemPlayWrapper() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_itemPlayWrapper());
    }

    public By getSuperhero() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superhero());
    }

    public By getSuperheroCarouselActive() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superheroCarouselActive());
    }

    public By getSuperheroCarouselInfo() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superheroCarouselInfo());
    }

    public By getSuperheroCarouselPlayButton() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superheroCarouselPlayButton());
    }

    public By getSuperheroCarouselMoreInfoButton() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superheroCarouselMoreInfoButton());
    }

    public By getSuperHeroCarouselPlayButtonRental() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_superHeroCarouselPlayButtonRental());
    }

    public By getDropdownParentalControlCheckbox() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_dropdownParentalControlCheckbox());
    }

    public By getStripeContentSeeMore() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_stripeContentSeeMore());
    }

    public By getStripeHeader() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_stripeHeader());
    }

    public By getStripeSlickItemActive() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_stripeSlickItemActive());
    }

    public By getStripeSlickArrowNext() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_stripeSlickArrowNext());
    }

    public By getStripeSlickArrowPrev() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_stripeSlickArrowPrev());
    }

    public By getBySlickItem() {
        return UtilsProperties.getByLocator(this.props.commonPageElements_slickItem());
    }

    public WebElement getPageLocator(String name) {
        return this.webDriver.findElement(By.xpath(props.commonPageElements_pageLocator()
                .replaceAll("NAME", name)));
    }

    protected By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");

}