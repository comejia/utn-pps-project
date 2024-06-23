package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyContentFavoritesPageFlow extends Page {

    @FindBy(xpath = ".//*[@id=\"favorites\"]/div[1]/h1")
    protected WebElement pageLocatorTitle;

    public MyContentFavoritesPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
