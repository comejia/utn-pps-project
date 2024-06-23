package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StripePageFlow extends Page {

    @FindBy(xpath = ".//ol[@class='breadcrumb home']")
    protected WebElement breadcrumHome;
    @FindBy(xpath = ".//li[@class='breadcrumb-item']/a")
    protected WebElement breadcrumHomeItem;
    @FindBy(xpath = ".//li[@class='active breadcrumb-item']")
    protected WebElement breadcrumHomeItemActive;
    @FindBy(className = "gridItemsWrapper")
    protected WebElement itemGrid;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]")
    protected List<WebElement> itemWrapper;
    @FindBy(xpath = ".//div[contains(@class,'item')]")
    protected WebElement itemWrapperItem;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]/.//div[contains(@class,'item')]")
    protected List<WebElement> itemWrapperList;
    @FindBy(xpath = ".//div[@id='flow-lazy-img']/img")
    protected WebElement itemImg;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]/.//div[contains(@class,'item')]/.//div[@id='flow-lazy-img']/img")
    protected List<WebElement> itemImgList;
    @FindBy(xpath = ".//h3[text()='Star+']//ancestor::div[@class='contentStripe Películas']")
    public WebElement stripeStarMovies;
    @FindBy(xpath = ".//h3[text()='Disney+']//ancestor::div[@class='contentStripe Películas']")
    public WebElement stripeDisneyMovies;
    @FindBy(xpath = ".//div[@id='grid']")
    public WebElement stripeOnSeriesAndMovies;
    @FindBy(xpath = ".//h3[text()='Star+']//ancestor::div[@class='contentStripe Series']")
    public WebElement stripeStarSeries;
    @FindBy(xpath = ".//h3[text()='Disney+']//ancestor::div[@class='contentStripe Series']")
    public WebElement stripeDisneySeries;
    @FindBy(xpath = ".//h3[text()='Series']//ancestor::div[@class='contentStripe']" )
    public WebElement stripeSeries;
    @FindBy(xpath = ".//h3[text()='Películas']//ancestor::div[@class='contentStripe']")
    public WebElement stripePeliculas;
    @FindBy(xpath = ".//div[@class='playIcon' and @role='button']")
    protected WebElement buttonPlay;
    @FindBy(xpath = ".//div[@class='contentStripe Series']")
    protected  List<WebElement> stripes;
    @FindBy(xpath = "(.//a[@class='titleLink'])[1]//ancestor::*[@class='contentStripe Series']" )
    protected WebElement selectedStripe;
    @FindBy(xpath = "(.//a[@class='titleLink'])[1]")
    protected WebElement seeMore;
    public By seeMoreBy = By.xpath(".//a[@class='titleLink']");
    public By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");
    public By getItemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");
    public StripePageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
