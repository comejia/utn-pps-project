package flow.pageActions;

import flow.pageObjects.MusicPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class MusicPageAction extends MusicPageFlow {

    public MusicPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void checkContentRadios() {
        List<WebElement> columns = radios.findElements(contentRadios);
        assertTrue("Expected 9 or more radios available and exists " + columns.size() + " radios", columns.size() >= 1);
        logger.info("9 or more radios available");
    }

    public void checkContentMusic() {
        List<WebElement> columns = music.findElements(contentMusic);
        assertTrue("7 songs are not present", columns.size() >= 7);
        logger.info("7 songs available");
    }

    public void waitTitleMusicDisplayed() {
        webDriver.waitUntil(10, wd -> getTitleMusic.isDisplayed());
    }

    public void waitTitleRadiosDisplayed() {
        webDriver.waitUntil(10, wd -> getTitleRadios.isDisplayed());
    }

    public void clickNavbarContentMusic() {
        webDriver.elementClick(getNavbarContentMusic);
    }

    public void waitNavbarContentMusic() {
        webDriver.waitUntil(10, wd -> getNavbarContentMusic.isDisplayed());
    }
}
