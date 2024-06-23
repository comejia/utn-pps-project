package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Map;


public class HomePageFlow extends Page {

    protected Map<String, String> config;
    @FindBy(className = "homeWrapper")
    protected WebElement pageLocator;
    @FindBy(xpath = ".//h3[text()='Explorá categorías y géneros']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeHomeCategoriasYGeneros;
    @FindBy(xpath = ".//a[contains(@class,'inicio') and contains(@href,'inicio')]")
    protected WebElement navbarHome;
    @FindBy(xpath = ".//button[@class='closeButon' and @type='button']")
    protected WebElement buttonCloseModalStarPlus;
    @FindBy(xpath = ".//a[contains(@class,'banner') and contains(@href,'starplus')]")
    protected WebElement onboardingModalContent;
    @FindBy(xpath = ".//div[@class='flowModalHead']/button[@class='closeButon']/img[contains(@src,'close2')]")
    protected WebElement onboardingStarCloseButton;
    @FindBy(xpath = ".//div[@class='flowModalBody']//img[contains(@src,'image-starplus')]")
    protected WebElement onboardingStarImgStar;
    @FindBy(xpath = ".//div[@class='flowModalBody']//h3[contains(text(),'Star+')]")
    protected WebElement onboardingStarTitle;
    @FindBy(xpath = ".//div[@class='flowModalBody']//p[contains(text(),'Star+')]")
    protected WebElement onboardingStarDescription;
    @FindBy(xpath = ".//div[@class='flowModalFooter']//button[contains(text(),'VER')]")
    protected WebElement onboardingStarButtonGo;
    @FindBy(xpath = ".//div[@class='carousel']")
    protected WebElement getSuperHero;
    @FindBy(xpath = ".//h3[text()='Destacados Flow']//ancestor::div[@class='contentStripe Inicio']")
    protected WebElement stripe;
    @FindBy(xpath = ".//h3[text()='Mis Canales']//ancestor::div[@class='channelStripe  Inicio']")
    public WebElement stripeMisCanales;
    @FindBy(xpath = ".//h3[contains(text(),'visto en TV')]//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeTheMostWatchingInLiveTv;
    @FindBy
    protected List<WebElement> destacadosFlow;
    @FindBy
    protected List<WebElement> contenidoAleatorio;
    @FindBy
    protected List<WebElement> slicksActive;
    @FindBy
    protected List<WebElement> stripeCategorias;
    @FindBy
    protected List<WebElement> contenidoStripe;
    @FindBy
    protected WebElement categorias;
    @FindBy
    protected WebElement selectedCategory;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    protected WebElement tituloContenido;
    @FindBy(xpath = ".//div[contains(@class,'description')]")
    protected WebElement getDescription;
    @FindBy(xpath = ".//p[@class='info-title']")
    protected WebElement genero;
    @FindBy(xpath = ".//p[@class='info-item']")
    protected WebElement tipoDeGenero;
    @FindBy(xpath = ".//*[@class='stripeTitle']//h3[text()= 'Todas las series']")
    protected WebElement getTodasLasSeriesTitle;
    protected List<WebElement> contentRandom;
    @FindBy(xpath = ".//h3[text()='Recomendados para vos']//ancestor::div[@class='contentStripe Inicio']")
    protected WebElement stripeRecommended;
    @FindBy
    protected List<WebElement> stripesBox;
    @FindBy
    protected List<WebElement> contenidoDisney;
    @FindBy(xpath = ".//h3[text()='Mirá en Flow']//ancestor::div[@class='contentStripe Inicio']")
    protected WebElement miraEnFlow;
    @FindBy(xpath = ".//*[text()='REPRODUCIR']//ancestor::button")
    public WebElement buttonReproducir;
    @FindBy(xpath = ".//h3[text()='Todos los títulos']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeAllTitles;
    @FindBy(xpath = ".//h3[text()='Lo más visto en Flow']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeMostViewedInFlow;
    @FindBy(xpath = ".//h3[text()='Continuar viendo']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement continueWatching;
    @FindBy(xpath = ".//h3[text()='Géneros']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeGeneros;

    @FindBy(xpath = ".//h3[text()='Todo HBO']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeTodoHBO;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]//img")
    protected List<WebElement> itemImg;
    @FindBy(xpath = ".//h3[text()='Top 10 de películas']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeTopMovies;
    @FindBy(xpath = ".//h3[text()='Top 10 de series']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeTopSeries;
    @FindBy(xpath = ".//div[contains(@class,'contentStripe ')]")
    protected List<WebElement> stripesHome;
    @FindBy(xpath = ".//h3[text()='Mis películas y series']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeMyMoviesAndSeries;
    protected By getButtonPlay = By.xpath(".//div[@class='playIcon' and @role='button']");
    protected By getSlickText = By.xpath(".//div[@class='contentTitle truncate2lines']");
    public By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");
    protected By stripeBy = By.xpath(".//h3[text()='Destacados Flow']//ancestor::div[@class='contentStripe Inicio']");
    protected By getStripeMiraEnFlow = By.xpath(".//h3[text()='Mirá en Flow']//ancestor::div[@class='contentStripe Inicio']");

    protected By stripeByMostWatchingLiveTv = By.xpath(".//h3[contains(text(),'visto en TV')]//ancestor::div[@class='contentStripe Inicio']");
    @FindBy(xpath = ".//h3[contains(text(),'visto en TV')]")
    protected WebElement theMostTVWatchingInFlow;
    protected By getStripeTitle = By.xpath(".//*[@class='stripeTitle']");
    protected By getSlickSilde = By.xpath(".//div[@class='slick-slide slick-active slick-current']");
    protected By getNavLinkSeriesActive = By.xpath(".//a[@class='nav-link active series']");
    protected By getStripeBox = By.xpath(".//div[@class='contentStripe']");
    protected By getImgText = By.xpath(".//div[@id='flow-lazy-img']//img");
    protected By seeMoreBy = By.xpath(".//div[@class='seeMore']");
    @FindBy(xpath = ".//h3[text()='Series Originales Flow']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeSerieOriginalsFlow;
    @FindBy(xpath = ".//h3[text()='Todo Paramount+']//ancestor::div[@class='contentStripe Inicio']")
    public  WebElement stripeHomeTodoParamount;
    @FindBy(xpath = ".//h3[text()='Destacados Flow']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeDestacadosFlow;
    @FindBy(xpath = ".//h3[text()='Lo más visto en TV']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement homePageMostViewedTV;
    @FindBy(xpath = ".//h3[text()='Recomendados para vos']//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeRecomendadosParaVos;

    protected By slickNext = By.xpath(".//div[@class='slick-arrow slick-next vertical-align']");
    protected By slickPrev = By.xpath(".//div[@class='slick-arrow slick-prev vertical-align']");
    public By getSlickCollections = By.xpath(".//div[contains(@class,'slick-slide')]");
    @FindBy(xpath = ".//h3[contains(text(),'Colecciones')]//ancestor::div[@class='contentStripe Inicio']")
    public WebElement stripeColeccionesImperdibles;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    public WebElement titleContent;
    public HomePageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

}
