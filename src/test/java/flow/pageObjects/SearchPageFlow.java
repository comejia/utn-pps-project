package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPageFlow extends Page {

    protected ContentPageFlow contentPageFlow;
    protected By stripeTitleString = By.xpath(".//h3");
    protected By stripeSearchStripe = By.className("searchStripe");
    protected By contentInfo = By.className("contentInfo");
    protected By contentStripeItem = By.xpath(".//div[@data-testid='contentStripeItem']");
    protected By itemPlayIcon = By.id("playIcon");
    protected By contentType = By.className("contentType");
    protected By itemInfoTruncateLinesH4 = By.xpath(".//div[@class='itemInfo']/h4");
    protected By itemInfoTruncateLines = By.className("truncate2lines");
    protected By itemInfoDuration = By.xpath(".//div[contains(@class,'itemDuration')]//p");
    protected By parentalRating = By.className("parentalRating");
    protected By buttonPlayBy = By.xpath(".//div[@class='playIcon' and @role='button']");
    @FindBy(id = "search")
    protected WebElement pageLocator;
    @FindBy(id = "noResults")
    protected WebElement noResults;
    @FindBy(id = "searchWrapper")
    protected WebElement buttonSearchWrapper;
    @FindBy(xpath = ".//input[contains(@class,'searchInput') and contains(@class,'open') and @type='text']")
    protected WebElement inputSearchInput;
    @FindBy(className = "stripeTitle")
    protected WebElement stripeTitle;
    @FindBy(className = "recommendedStripe")
    protected WebElement stripeRecommended;
    @FindBy(xpath = ".//a[contains(@class,'channelItem')]")
    protected WebElement stripeChannelItem;
    @FindBy(className = "gridItemsWrapper")
    protected WebElement slickList;
    @FindBy(className = "item")
    protected List<WebElement> slickItem;
    @FindBy(xpath = ".//img[contains(@src,'images/vod')]")
    protected WebElement itemInfoTypeTag;
    @FindBy(xpath = ".//div[@class='typeTagWrapper']/p")
    protected WebElement itemInfoTypeTagP;
    @FindBy(className = "ageWrapper")
    protected WebElement itemInfoAgeWrapper;
    @FindBy(xpath = ".//div[contains(@class,'itemDuration')]//p")
    protected WebElement itemInfoAgeBadge;
    @FindBy(xpath = ".//button[contains(@class,'moreInfo')]")
    protected WebElement itemInfoButtonMoreInfo;
    @FindBy(className = "playIconWrapper")
    protected WebElement itemPlayWrapper;

    @FindBy(xpath = ".//div[contains(@class,'stripeTitle') and .//h3[contains(.,'Canales')]]")
    protected WebElement stripeChannels;
    @FindBy(xpath = ".//div[contains(@class,'searchStripeHeader') and .//h3[contains(.,'Películas')]]")
    protected WebElement stripeMovies;
    @FindBy(xpath = ".//div[contains(@class,'searchStripeHeader') and .//h3[contains(.,'TV')]]")
    protected WebElement stripeTv;
    @FindBy(xpath = ".//div[@class='seeMore']/a")
    protected WebElement stripeSeeMore;
    @FindBy(xpath = ".//div[contains(@class,'channelItemWrapper') and contains(@class,'undefined')]")
    protected List<WebElement> stripeChannelsElement;
    @FindBy(xpath = ".//a[contains(@class,'channelItem')]")
    protected List<WebElement> contentStripeChannelElement;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow') and contains(@class,'slick-next')]")
    protected WebElement stripeSlickArrowNext;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow') and contains(@class,'slick-prev')]")
    protected WebElement stripeSlickArrowPrev;
    @FindBy(xpath = ".//div[contains(@class,'channelStripe') and contains(@class,'search')]")
    protected WebElement stripeChannelSearch;
    @FindBy(xpath = ".//div[@class='itemWrapper  Portrait']//ancestor::div[contains(@class,'contentTitle')]")
    protected WebElement firstSearchStripeTitleMovie;
    @FindBy(xpath = "//*[@id='searchWrapper']/input")
    protected WebElement searchTxt;
    @FindBy(id = "searchWrapper")
    protected WebElement searchButton;
    @FindBy(xpath = "//*[contains(@resource-id,'pbSearch')]")
    protected WebElement loadingSearch;
    @FindBy(xpath = "//button[@class='searchIcon']")
    protected WebElement searchIcon;
    @FindBy(xpath = ".//h3[text()='Películas, series y programas emitidos']//ancestor::div[@class='searchStripe']")
    public WebElement stripePeliculasSeriesYProgramas;
    @FindBy (xpath= ".//div[@class='playIcon' and @role='button']")
    protected WebElement buttonPlay;
    @FindBy(xpath = ".//h3[text()='Prime video']//ancestor::div[@class='searchStripe']")
    public WebElement searchStripePrimeVideo;
    @FindBy(id = "flow-lazy-img")
    protected WebElement imageChannelCard;
    @FindBy(xpath = ".//*[text()='FILTROS']//ancestor::button")
    protected WebElement filtersButton;
    @FindBy(xpath = ".//*[text()='Género']//ancestor::button")
    protected WebElement filterGender;
    @FindBy(xpath = ".//button[@id='closeFilters']")
    protected WebElement closeFilter;
    @FindBy(xpath = ".//*[@class='breadcrumb-item']")
    protected WebElement titleSection;
    @FindBy(xpath = ".//*[@class='breadcrumb-item active']")
    protected WebElement titleStripeActive;
    @FindBy(xpath = ".//div[@class='gridItemWrapper']")
    protected List<WebElement> gridItem;
    @FindBy(xpath = ".//p[@class='info-item']")
    protected WebElement checkFilterDip;
    @FindBy(xpath = ".//button[@class='clearFilter']")
    protected WebElement clearFilter;
    @FindBy(xpath = ".//h3[text()='Guía de TV']//ancestor::div[@class='searchStripe']")
    public WebElement searchStripeGuiaDeTv;
    public static String filter;
    public static String contentSearchTitle;
    protected By filtersAppliedBy = By.xpath(".//button[contains(@class,'filterItem')]");
    protected By filterBy = By.xpath(".//div[@role='button']");
    public By stripeTvGuide = By.xpath(".//*[text()='Guía de TV']//ancestor::div[@class='searchStripe']");
    protected By titleStripePrimeVideo = By.xpath(".//*[contains(@class, 'stripeTitle')]//ancestor::div[@class='gridItemWrapper']");

    public By itemWrapper = By.xpath(".//div[contains(@class,'itemWrapper')]");
    public By contentTitle = By.xpath(".//div[contains(@class,'contentTitle truncate')]");
    protected By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");
    protected By titleStripe = By.xpath(".//*[contains(@class, 'stripeTitle')]//ancestor::div[@class='gridItemWrapper']");

    public SearchPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
        this.contentPageFlow = new ContentPageFlow(webDriver);
    }

    public By getByStripeTitleString() {
        return this.stripeTitleString;
    }

    public By getByStripeSearchStripe() {
        return this.stripeSearchStripe;
    }

    public By getByContentInfo() {
        return this.contentInfo;
    }

    public By getContentStripeItem() {
        return contentStripeItem;
    }

    public By getItemPlayIcon() {
        return this.itemPlayIcon;
    }

    public By getContentType() {
        return contentType;
    }

    public By getByItemInfoTruncateLinesH4() {
        return this.itemInfoTruncateLinesH4;
    }

    public By getByItemInfoTruncateLines() {
        return this.itemInfoTruncateLines;
    }

    public By getByItemInfoDuration() {
        return this.itemInfoDuration;
    }

    public By getParentalRating() {
        return parentalRating;
    }

}
