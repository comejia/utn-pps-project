package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyContentContinueWatchingPageFlow extends Page {

    @FindBy(id = "continueWatching")
    protected WebElement pageLocator;
    @FindBy(xpath = ".//h1[text()='Continuar viendo']")
    protected WebElement pageLocatorTitle;
    @FindBy(xpath = ".//div[@id='playIcon'][@class='playIconWrapper']")
    protected WebElement pageLocatorItems;
    public MyContentContinueWatchingPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(id = "grid")
    protected WebElement grid;
    protected By getItemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");
    @FindBy(xpath = ".//a[contains(@title, 'explorar contenido')]")
    protected WebElement exploreContent;

}
