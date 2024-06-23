package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdultPageFlow extends Page {

    @FindBy(xpath = ".//div[@class='flowModalBody']")
    protected WebElement modalBody;
    @FindBy(xpath = ".//button[@class='primaryButton mediumButton ']")
    protected WebElement enterButton;
    @FindBy(xpath = ".//*[text()='ALQUILAR']//ancestor::button")
    protected WebElement rentButton;
    @FindBy(xpath = ".//*[text()='CANCELAR']//ancestor::button")
    protected WebElement cancelButton;
    @FindBy(xpath = ".//*[text()='CONFIRMAR']//ancestor::button")
    protected WebElement confirmButton;
    @FindBy(xpath = ".//h3[text()='Adultos']//ancestor::div[@class='contentStripe Películas']")
    public WebElement stripeAdult;
    @FindBy(xpath = ".//h3[text()='Películas, series y programas emitidos']//ancestor::div[@class='searchStripe']")
    public WebElement stripePeliculasSeriesYProgramas;
    @FindBy(xpath = ".//input[@class='searchInput vertical-align open']")
    protected WebElement searchWrapper;
    @FindBy(xpath = ".//p[@class='rentPrice']")
    protected WebElement rentPrice;
    @FindBy(xpath = ".//h1[@class='title truncate']")
    protected WebElement titleContentRented;
    @FindBy(xpath = ".//*[contains(text(),'REPRODUCIR') or contains(text(),'CONTINUAR')]//ancestor::button")
    protected WebElement flowPrimaryButton;
    public By itemWrapper = By.xpath(".//div[contains(@class,'itemWrapper')]");
    public By contentTitle = By.xpath(".//div[contains(@class,'contentTitle truncate')]");
    protected By getSlickActive = By.xpath(".//div[contains(@class,'slick-slide slick-active')]");

    public AdultPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

}
