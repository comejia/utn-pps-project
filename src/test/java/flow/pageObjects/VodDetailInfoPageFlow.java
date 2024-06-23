package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VodDetailInfoPageFlow extends Page {
    public String setUrl;
    protected By itemBy = (By.xpath(".//div[@data-index and contains(@class,'slick-active')]"));

    @FindBy(id = "dipVOD")
    protected WebElement pageLocator;
    @FindBy(xpath = ".//*[@id=\"dipVOD\"]/div[1]/h1")
    protected WebElement pageLocatorTitle;
    @FindBy(xpath = ".//button[contains(@class,'backButton')]")
    protected WebElement buttonBack;

    @FindBy(xpath = ".//div[@class='infoWrapper ']")
    protected WebElement vodInfoWrapper;
    @FindBy(id = "flow-lazy-img")
    protected WebElement vodImg;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    public WebElement vodTitle;

    @FindBy(xpath = "releasedYear")
    protected WebElement vodReleasedYear;
    @FindBy(xpath = ".//span[contains(@class,'labelButton')]/ancestor::button[@type='submit'][contains(@class,'flowPrimaryButton')]")
    protected WebElement vodPrimaryButton;
    @FindBy(xpath = "//span[contains(@class,'labelButton')]/ancestor::button[@type='submit'][contains(@class,'restart')]")
    protected WebElement vodRestartButton;
    @FindBy(xpath = ".//span[contains(@class,'labelButton')]/ancestor::button[@type='submit'][contains(@class,'flowPrimaryButton')]//div//span[@class='labelButton']")
    protected WebElement labelPrimaryButton;
    @FindBy(xpath = ".//span[contains(@class,'labelButton') and contains(text(),'REPRODUCIR') or contains(text(),'CONTINUAR')]/ancestor::button[@type='submit'][contains(@class,'flowPrimaryButton')]")
    protected WebElement vodPlayButton;
    @FindBy(xpath = ".//div[contains(@class,'iconTrailer')]/ancestor::button[@type='submit'][contains(@class,'flowPrimaryButton')]")
    protected WebElement vodPlayTrailerButton;
    @FindBy(xpath = ".//span[contains(@class,'labelButton') and contains(text(),'$') or contains(text(),'ALQUILAR')]/ancestor::button[@type='submit'][contains(@class,'flowPrimaryButton')]")
    protected WebElement vodRentButton;
    @FindBy(xpath = ".//p[@class='rentPrice']")
    protected WebElement vodRentPriceInfoButton;
    @FindBy(xpath = ".//div[contains(@class,'dueTime')]")
    protected WebElement vodDueTime;
    @FindBy(xpath = ".//div[contains(@class,'Episodes')]/ancestor::button[@type='submit']")
    protected WebElement vodEpisodesButton;

    @FindBy(xpath = ".//div[@id='seriesEpisodes']")
    protected WebElement pageEpidodesSeries;
    @FindBy(xpath = ".//button[contains(@class,'MyListButton')]")
    protected WebElement vodFavoriteButton;
    @FindBy(xpath = ".//button[contains(@class,'MyListButton')]")
    protected WebElement myListButton;
    @FindBy(xpath = ".//div[@data-testid='notFavorite']")
    protected WebElement vodFavoriteButtonInactive;
    @FindBy(xpath = ".//div[@data-testid='isFavorite']")
    protected WebElement vodFavoriteButtonActive;
    @FindBy(xpath = ".//div[contains(@class,'attributes')]")
    protected WebElement vodDuration;
    @FindBy(xpath = ".//div[@class='badge undefined']")
    protected WebElement vodBadge;
    @FindBy(xpath = ".//div[contains(@class,'description')]")
    protected WebElement vodDescription;
    @FindBy(xpath = ".//p[@class='info-title' and contains(text(), 'Género')]")
    protected WebElement vodGenre;
    @FindBy(xpath = ".//p[@class='info-title' and contains(text(), 'Elenco')]")
    protected WebElement vodCast;
    @FindBy(xpath = ".//p[@class='info-title' and contains(text(), 'Elenco')]/../..")
    protected WebElement vodCastRow;
    @FindBy(xpath = ".//p[@class='info-title' and contains(text(), 'Director')]")
    protected WebElement vodDirector;
    @FindBy(xpath = ".//p[@class='info-title' and contains(text(), 'Director')]/../..")
    protected WebElement vodDirectorRow;
    @FindBy(xpath = ".//*[contains(text(), 'Director')]/../following-sibling::*/p")
    protected WebElement vodDirectorName;
    @FindBy(xpath = ".//*[contains(text(), 'Elenco')]/../following-sibling::*/p")
    protected List<WebElement> vodElencoName;
    @FindBy(xpath = ".//p[@class='badge-item cast']")
    protected List<WebElement> vodCastBadges;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe') and contains(@class,'DIP')]")
    protected WebElement vodContentStripeDip;
    @FindBy(xpath = ".//div[@class='stripeTitle']//h3")
    protected WebElement vodContentStripeTitle;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe')]")
    protected WebElement stripeRelatedContent;
    @FindBy(xpath = ".//div[contains(@class,'contentStripeHeader')]")
    protected WebElement stripeRelatedContentHeader;
    @FindBy(xpath = ".//div[@class='stripeTitle']")
    protected WebElement stripeRelatedContentTitle;
    @FindBy(className = "slick-list")
    protected WebElement slickList;
    @FindBy(xpath = ".//div[@data-index and contains(@class,'slick-active')]")
    protected WebElement slickItem;
    @FindBy(xpath = ".//div[contains(@class,'flowModalContent') and contains(@class,'purchaseModal')]")
    protected WebElement modalContent;
    @FindBy(xpath = ".//button[@class='closeButon']")
    protected WebElement modalCloseLinkButton;
    @FindBy(xpath = ".//button[contains(@class,'secondaryButton') and contains(text(),'CANCELAR')]")
    protected WebElement modalCancelButton;
    @FindBy(xpath = ".//button[contains(@class,'primaryButton') and contains(text(),'CONFIRMAR')]")
    protected WebElement modalConfirmButton;
    @FindBy(xpath = ".//div[contains(@class,'flowModalBody')]")
    protected WebElement modalBody;
    @FindBy(xpath = ".//div[contains(@class,'messageContent')]")
    protected WebElement messageNoContent;

    @FindBy(xpath = ".//input[@id='pin' and @title='Ingresa tu PIN']")
    protected WebElement elementPinPurchasesBlocked;
    @FindBy(xpath = ".//button[@type='button' and contains(@class,'secondaryButton') and text()='CANCELAR']")
    protected WebElement elementCancelButtonPurchasesBlocked;
    @FindBy(xpath = ".//button[@type='button' and contains(@class,'primaryButton') and text()='CONFIRMAR']")
    protected WebElement elementConfirmButtonPurchasesBlocked;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'flowPrimaryButton') and contains(@class,'play')]")
    protected WebElement playButtonVodDetailInfo;

    @FindBy(xpath = "//button[contains(@class,'seasonTitle')]")
    protected List<WebElement> buttonSeasons;
    @FindBy(xpath = "//div[contains(@class,'row episode')]")
    protected List<WebElement> episodes;

    @FindBy(xpath = "//div[contains(@class,'playIconWrapper')]")
    protected List<WebElement> playButtonsEpisode;
    @FindBy(css = "#play")
    protected List<WebElement> playButtonEpisodesList;
    @FindBy(xpath = ".//h3[text()='Lo más visto en TV']//ancestor::div[@class='contentStripe Inicio']")
    protected WebElement stripeTvLive;
    @FindBy(xpath = ".//div[@class='flowDropDown userDropDownMenu']")
    protected WebElement elementDropDowUset;
    @FindBy(xpath = ".//a[text()='Cerrar sesión']")
    protected WebElement elementLogout;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    public WebElement title;
    @FindBy(id = "flow-lazy-img")
    protected WebElement vodCoverImg;
    @FindBy(xpath = ".//h3[text()='TV en vivo']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeLiveTv;
    @FindBy(xpath = ".//h3[text()='Lo más visto en TV']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeLoMasVistoEnTV;
    @FindBy(xpath = ".//button[contains(@class, 'share')]")
    protected WebElement buttonShare;
    @FindBy(xpath = ".//span[contains(@class, 'flowDropDownMenu show active')]")
    protected WebElement optionToShare;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe DIP')]")
    public WebElement dipStripeRecommendations;
    @FindBy(xpath = ".//*[text()='CONTINUAR']//ancestor::button")
    public WebElement vodButtonContinuar;

    protected By img = By.xpath(" .//div[@id='flow-lazy-img']/img");
    public VodDetailInfoPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
