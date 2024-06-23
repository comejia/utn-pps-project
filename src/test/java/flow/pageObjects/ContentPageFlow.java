package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContentPageFlow extends Page {

    public static By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");
    @FindBy(xpath = ".//*[text()='FILTROS']//ancestor::button")
    protected WebElement filtersButton;
    @FindBy(className = "contentStripe")
    public List<WebElement> stripeContentStripe;
    @FindBy(className = "stripeTitle")
    protected WebElement stripeTitle;
    @FindBy(className = "slick-list")
    protected WebElement slickList;
    @FindBy(xpath = ".//div[@data-index and contains(@class,'slick-active')]")
    protected List<WebElement> slickItem;
    @FindBy(id = "closeFilters")
    protected WebElement closeFilters;
    @FindBy(xpath = ".//div[contains(@class,'filtersModal')]/div")
    protected WebElement filtersModal;
    @FindBy(xpath = ".//button[contains(@class,'vertical-align') and contains(@class,'show')]")
    protected WebElement filterVerticalAlignShow;
    @FindBy(xpath = ".//div[@class='filterCategoryWrapper']")
    protected WebElement filterCategoryWrapper;
    @FindBy(xpath = ".//div[@class='hidden']")
    protected WebElement filterCategoryWrapperHidden;
    @FindBy(xpath = ".//div[@class='collapse']")
    protected WebElement filterCategoryWrapperCollapse;
    @FindBy(xpath = ".//div[@class='collapsing']")
    protected WebElement filterCategoryWrapperCollapsing;
    @FindBy(xpath = ".//div[contains(@class,'Collapse') and contains(@class,'entered')]")
    protected WebElement filterCategoryWrapperCollapseShow;
    @FindBy(xpath = ".//p[contains(@class,'filterItem')]")
    protected List<WebElement> filterCategoryWrapperCollapseRowFilterItem;
    @FindBy(xpath = ".//div[@class='selectedFiltersWrapper']")
    protected WebElement selectedFiltersWrapper;
    @FindBy(xpath = ".//button[@type='button' and @class='clearFilter']")
    protected WebElement clearFilterButton;
    @FindBy(xpath = ".//div[@class='filterItemWrapper']")
    protected WebElement filterItemWrapper;
    @FindBy(xpath = ".//div[@data-testid='contentStripeItem']")
    protected List<WebElement> contentItems;
    @FindBy(xpath = ".//div[@class='col']/..//ancestor::span[contains(@class,'labelButton') and contains(text(),'REPRODUCIR') or contains(text(),'CONTINUAR')]")
    protected WebElement playButtonDIPSuperHero;
    @FindBy(xpath = " .//div[text()='Radios']/..//ancestor::div[@class='sectionHeader']/following-sibling::div[contains(@class,'radioStripe')][1]")
    protected WebElement radioStripe;
    @FindBy(xpath = " .//div[text()='Música']/..//ancestor::div[@class='sectionHeader']/following-sibling::div[contains(@class,'radioStripe')][1]")
    protected WebElement morePopularStripe;
    @FindBy(xpath = ".//div[@id='grid']")
    protected WebElement musicListGrid;
    @FindBy(xpath = ".//div[contains(@class,'radioItem')]")
    protected WebElement radioItem;
    @FindBy(xpath = ".//div[@class='carousel']")
    protected WebElement getSuperHero;
    @FindBy(xpath = ".//button[contains(@class,'play flowBigButton')]")
    protected WebElement getFlowPrimaryButton;
    @FindBy(xpath = ".//div[@class='carousel']//div[@class='slick-arrow slick-next vertical-align']")
    protected WebElement getNextSuperHero;
    @FindBy(xpath = ".//input[@id='pin']")
    protected WebElement campoPin;
    @FindBy(xpath = ".//button[@type='button' and @class='primaryButton mediumButton ']")
    protected WebElement confirmarPin;
    @FindBy
    protected WebElement contenido;
    @FindBy
    protected WebElement superHero;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    protected WebElement tituloContenido;
    @FindBy(xpath = ".//div[@class='description']")
    protected WebElement fichaTecnica;
    @FindBy(xpath = ".//p[@class='info-item']")
    protected WebElement tipoDeGenero;
    @FindBy(xpath = "(.//button[contains(@class,'flowPrimaryButton')])[1]")
    protected WebElement getButtonAlquilar;
    @FindBy(xpath = ".//div[@class='purchaseContent']")
    protected WebElement getPurchaseContent;
    @FindBy(xpath = ".//div[contains(@class,'gridItemsWrapper')]")
    public WebElement gridItemsWrapper;
    @FindBy(xpath = ".//div[@class= 'contentStripe Series']")
    public List<WebElement> contentStripeSeries;
    @FindBy(xpath = ".//h3[text()='Populares en Flow']//ancestor::div[@class='contentStripe Series']")
    public WebElement stripePopularSeries;
    @FindBy(xpath = ".//*[@class='breadcrumb-item']")
    protected WebElement titleSection;
    @FindBy(xpath = ".//*[@class='breadcrumb-item active']")
    protected WebElement titleStripeActive;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    protected WebElement vodTitle;
    @FindBy(xpath = ".//h3[text()='Mis películas y series']//ancestor::div[@id='grid']")
    protected WebElement stripeMyMoviesAndSeries;
    protected By getSlickText = By.xpath(".//div[@class='contentTitle truncate2lines']");
    protected By buttonDelete = By.xpath(".//div[@class='favoriteIcon ' and  @role='button']");
    protected By img = By.xpath(" .//div[@id='flow-lazy-img']/img");
    protected String pageLocator = ".//div[@id='NAME']";
    protected String filterCategoryFilterItem = ".//button[@class='vertical-align show' and contains(text(),'NAME')]/following-sibling::div[contains(@class,'MuiCollapse-entered')]";
    protected String filterNameButton = ".//button[contains(text(),'NAME')]";
    protected String filterNameButtonShow = ".//button[@class='vertical-align show' and contains(text(),'NAME')]";
    protected String filterNameItem = ".//*[contains(text(),'NAME')]";
    protected String primaryButtonFilterItem = "//span[contains(text(), 'NAME')]/ancestor::button[@type='submit']";
    protected By getSuperHeroBy = By.xpath(".//div[@class='carousel']");
    protected By getSlickSilde = By.xpath(".//div[@class='slick-slide slick-active slick-current']");
    protected By getFlowPrimaryButtonBy = By.xpath(".//button[@class='flowPrimaryButton___34v3n play flowBigButton']");
    protected By radioItemDisplayed = By.xpath(".//div[contains(@class,'slick-active')]//div[contains(@class,'radioItem ')]");
    protected By getSuperHeroAut = By.xpath(".//div[@class='carousel']");
    protected By getCarruselInfo = By.xpath(".//div[@class='carouselInfo']");
    protected By getSlickDots = By.xpath("(.//*[@class='slick-dots']//button)[last()]//ancestor::li[@class='slick-active']");
    protected By myContents = By.xpath(".//div[@role='button' and @class='flowDropDown myContentDropdown nav-item']");

    protected By recordings = By.xpath(".//a[@class='nav-link  grabaciones']");
    protected By getItemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");
    protected By slickText = By.xpath(".//div[@class='contentTitle truncate2lines']");
    protected By buttonSearch = By.xpath(".//div[@id='searchWrapper' and @class='search ']");
    protected By navSearch = By.xpath(".//input[@class='searchInput vertical-align open']");

    @FindBy(xpath = ".//div[@data-testid=\"contentStripeItem\"]")
    protected List<WebElement> contentStripeItem;
    public By contentSearch = By.xpath(".//h3[text()='Grabaciones realizadas']//ancestor::div[@class='searchStripe']//div[contains(@class,'itemWrapper')]");
    protected By radioItemBy = By.xpath(".//div[contains(@class,'radioItem ')]");
    @FindBy(xpath = ".//h3[text()='Grabaciones realizadas']//ancestor::div[@class='searchStripe']")
    public WebElement stripeRecordings;
    @FindBy(xpath = ".//h3[text()='Grabaciones']//ancestor::div[@class='searchStripe']//div[contains(@class,'itemWrapper')]")
    public WebElement contentRecorded;
    @FindBy (xpath= ".//div[@class='playIcon' and @role='button']")
    protected WebElement buttonPlay;
    @FindBy (id= "playHover")
    protected List<WebElement> playHover;

    protected By itemWrapper = By.xpath(".//div[contains(@class,'itemWrapper')]");

    @FindBy (xpath= ".//span[text()=' REPRODUCIR']//ancestor::button")
    protected WebElement playContent;
    public By buttonPlayDip = By.xpath(".//*[text()='REPRODUCIR']//ancestor::button");


    public ContentPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public By getRadioItemDisplayed() {
        return radioItemDisplayed;
    }

    public By getFilterCategoryFilterItem(String name) {
        return By.xpath(filterCategoryFilterItem.replaceAll("NAME", name));
    }

    public WebElement getPageLocator(String name) {
        return this.webDriver.findElement(By.xpath(pageLocator.replaceAll("NAME", name)));
    }

    public By getFilterNameButton(String name) {
        String filterNameButtonLocator = filterNameButton.replaceAll("NAME", name);
        return By.xpath(filterNameButtonLocator);
    }

    public By getFilterNameButtonShow(String name) {
        String filterNameButtonShowLocator = filterNameButtonShow.replaceAll("NAME", name);
        return By.xpath(filterNameButtonShowLocator);
    }

    public By getFilterNameItem(String name) {
        String filterNameItemLocator = filterNameItem.replaceAll("NAME", name);
        return By.xpath(filterNameItemLocator);
    }

    public By getPrimaryButtonFilterItem(String name) {
        String primaryButtonFilterItemLocator = primaryButtonFilterItem.replaceAll("NAME", name);
        return By.xpath(primaryButtonFilterItemLocator);
    }

    public By getRadioItemBy(){
        return radioItemBy;
    }
    public By contentTitle = By.xpath(".//div[contains(@class,'contentTitle truncate')]");

    @FindBy(xpath = ".//*[@id='continueWatching']")
    protected WebElement continueWatching;

}
