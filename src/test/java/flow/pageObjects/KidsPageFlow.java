package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KidsPageFlow extends Page {

    @FindBy(xpath = ".//a[@class='channelItem vertical-align kids square' and contains(@href, '/vivo/kids?channel=')]")
    protected WebElement channelLive;

    public KidsPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }
}
