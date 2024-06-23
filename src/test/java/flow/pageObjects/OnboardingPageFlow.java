package flow.pageObjects;

import flow.pageActions.HomePageAction;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OnboardingPageFlow extends Page {

    protected HomePageAction homePageAction;

    @FindBy(xpath = ".//div[contains(@class,'onboarding-slider')]")
    protected WebElement onboardingSlider;
    @FindBy(xpath = ".//div[contains(@class,'slick-slide') and @data-index]")
    protected List<WebElement> slides;
    @FindBy(xpath = ".//div[contains(@class,'flowModalBackdrop onboardingBackdrop show')]")
    protected WebElement onboardingBackdrop;
    @FindBy(xpath = ".//div[contains(@class,'flowModalContent')]")
    protected WebElement onboardingModal;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-prev') and not(contains(@class,'slick-disabled'))]")
    protected WebElement arrowPrevEnabled;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-prev slick-disabled')]")
    protected WebElement arrowPrevDisabled;
    @FindBy(xpath = ".//img[@src='/img/onboarding/arrow-left.svg']/..")
    protected WebElement arrowPrev;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-next') and not(contains(@class,'slick-disabled'))]")
    protected WebElement arrowNextEnabled;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-next slick-disabled')]")
    protected WebElement arrowNextDisabled;
    @FindBy(xpath = ".//img[@src='/img/onboarding/arrow-right.svg']/..")
    protected WebElement arrowNext;
    @FindBy(xpath = ".//button[contains(@class,'sc') and text()='COMENZAR']")
    protected WebElement buttonContinue;
    @FindBy(xpath = ".//img[@src='/img/onboarding/onboarding-devices.gif']")
    protected WebElement onboardingImg;
    @FindBy(xpath = ".//ul[contains(@class,' slick-dots')]//button[text()='4']")
    protected WebElement buttonLastSlide;
    protected String slideNumber = ".//div[@class='slick-slide slick-active slick-center slick-current' and @data-index='NUMBER']";
    @FindBy(xpath = ".//div[@class='slick-slide slick-active slick-center slick-current']")
    protected WebElement slideActive;
    @FindBy(xpath = ".//h4[contains(@class,'sc-')]")
    protected WebElement slideTitle;
    @FindBy(xpath = ".//p[contains(@class,'sc-')]")
    protected WebElement slideDescription;
    @FindBy(xpath = ".//img[contains(@class,'sc-')]")
    protected WebElement slideImage;
    @FindBy(xpath = ".//ul[contains(@class,'sc-')]//li[@class]")
    protected List<WebElement> slideDots;
    @FindBy(xpath = ".//button[@type='button' and contains(@class,'sc-') and text()='COMENZAR']")
    protected WebElement closeOnboarding;
    @FindBy(xpath = ".//div[@role='button' and contains(@class,'onboardingBackdrop')]")
    protected WebElement onboardingOutSide;

    public OnboardingPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
        this.homePageAction = new HomePageAction(webDriver);
    }

    public By getSlideNumber(int number) {
        return By.xpath(slideNumber.replace("NUMBER", String.valueOf(number)));
    }


}
