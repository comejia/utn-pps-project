package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyContentRecordingsPageFlow extends Page {

    @FindBy(id = "recordings")
    protected WebElement pageLocator;
    @FindBy(xpath = ".//*[@id=\"recordings\"]/div[1]/h1")
    protected WebElement pageLocatorTitle;

    public MyContentRecordingsPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }


}
