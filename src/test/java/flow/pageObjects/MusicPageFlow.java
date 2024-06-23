package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MusicPageFlow extends Page {

    @FindBy(xpath = ".//a[contains(@class,'radios-y-musica')]")
    protected WebElement getNavbarContentMusic;
    @FindBy(xpath = ".//div[contains(@class,'sectionTitle') and contains(text(),'Radios')]")
    protected WebElement getTitleRadios;
    @FindBy(xpath = ".//div[contains(@class,'sectionTitle') and contains(text(),'Música')]")
    protected WebElement getTitleMusic;
    protected By contentRadios = By.xpath(".//div[contains(@class,'radioItem vertical-align square') and contains(@role,'button')]");
    protected By contentMusic = By.xpath("//div[contains(@class,'radioItem vertical-align square radioName')]");
    @FindBy(xpath = ".//div[@class='slick-list']")
    protected WebElement radios;
    @FindBy(xpath = ".//div[@class='slick-slider slick-initialized']")
    protected WebElement music;

    public MusicPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }


}
