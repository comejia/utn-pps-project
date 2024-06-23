package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HighlightsFlow extends Page {

    @FindBy(xpath = ".//div[contains(@class,\"contentStripe\")]")
    protected List<WebElement> stripes;
    @FindBy(xpath = ".//h3[text()='Reviví lo mejor de']")
    protected WebElement revivedTheBest;

    @FindBy(xpath = ".//h3[text()='Miralo en']")
    protected WebElement lookAtIt;

    protected String stripe = ".//h3[text()='Miralo en']";

    @FindBy(xpath = ".//h3[text()='Miralo en']//ancestor::div[contains(@class,'SmartHighlights')]//div[@class=\"slick-slide slick-active slick-current\"]")
    protected WebElement lookAtItPlayIcon;

    protected String itemStripeActive =  ".//h3[text()='NAME']//ancestor::div[contains(@class,'SmartHighlights')]//div[@class=\"slick-slide slick-active slick-current\"]";

    protected By playIcon = By.xpath("//img[contains(@class,'icon')]");
    protected String revivedTheBestItemStripePlayIcon = ".//h3[text()='Reviví lo mejor de']//ancestor::div[@class='contentStripe NAME']//div[@class=\"slick-slide slick-active slick-current\"]//div[@class='playIcon']";

    protected String contentStripe = "//ancestor::div[@class='contentStripe NAME']";

    public By getPlayIcon(){
        return playIcon;
    }

    public By getContentStripe(String stripeName) {
        return By.xpath(contentStripe.replaceAll("NAME", stripeName));
    }

    public By getPlayIconActiveSlick(String stripeName) {
        return By.xpath(revivedTheBestItemStripePlayIcon.replaceAll("NAME", stripeName));
    }

    public By getItemStripeActive(String itemStripeName) {
        return By.xpath(itemStripeActive.replaceAll("NAME", itemStripeName));
    }

    public By getStripe(String stripeName){
        return By.xpath(stripe.replaceAll("NAME", stripeName));
    }

    public HighlightsFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
