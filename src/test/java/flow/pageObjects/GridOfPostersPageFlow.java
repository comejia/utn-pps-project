package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GridOfPostersPageFlow extends Page {

    public GridOfPostersPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//div[@class='row']/h1")
    protected WebElement titleStripeSelected;
    @FindBy(xpath = ".//div[@class= 'contentStripe']")
    protected WebElement getStripe;
    @FindBy(xpath = ".//*[@class='breadcrumb-item']")
    protected WebElement titleSection;
    @FindBy(xpath = ".//*[@class='breadcrumb-item active']")
    protected WebElement titleStripeActive;
    @FindBy(xpath = ".//div[@class='row assets']")
    protected WebElement rowAssets;

    protected By itemWrapper = By.xpath(".//div[contains(@class,'itemWrapper')]");

    protected By img = By.xpath(" .//div[@id='flow-lazy-img']/img");
}
