package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SportsPageFlow extends Page {
    @FindBy(xpath = ".//a[@class='nav-link  deportes']")
    protected WebElement getDeportesMenu;
    @FindBy(xpath = ".//h1[text()='Deportes']")
    protected WebElement getTitleDeportes;
    @FindBy(xpath = ".//div[@class='contentStripe Deportes']")
    protected List<WebElement> stripesBox;
    @FindBy(xpath = ".//div[@class='contentStripe Deportes']")
    protected WebElement getStripeBoxDeportes;
    @FindBy(xpath = ".//*[text()='Eventos deportivos']//ancestor::div[@class='contentStripe Deportes']")
    public WebElement stripeSportEvents;
    @FindBy(xpath = ".//*[text()='Biografías, documentales y especiales deportivos']//ancestor::div[@class='contentStripe Deportes']")
    public static WebElement stripeBiographiesAndDocumentaries;

    @FindBy(xpath = ".//*[text()='Eventos deportivos']//ancestor::div[@class='contentStripe Deportes']")
    public WebElement stripeSportsEvents;
    protected By itemWrapper = By.xpath(".//div[contains(@class,'itemWrapper')]");
    protected By getStripeTitle = By.xpath(".//*[@class='stripeTitle']");
    protected By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");
    protected By getSlickText = By.xpath(".//div[@class='contentTitle truncate2lines']");

    public SportsPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

}
