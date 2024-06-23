package flow.pageActions;

import flow.pageObjects.OnboardingPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OnboardingPageAction extends OnboardingPageFlow {

    public OnboardingPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void closeOnboarding() {
        Awaitility.await().atMost(Duration.ofSeconds(5)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                if (onboardingModal.isDisplayed()) {

                    while (!buttonContinue.isDisplayed()) {
                        arrowNext.click();
                    }

                    buttonContinue.click();
                    logger.info("Onboarding present and closed");
                } else {
                    logger.info("Onboarding not present");
                }
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

    public void closeOnboardingStarPlus() {
        this.homePageAction.clickButtonCloseModalStartPlus();
    }

    public void onboardingNextSlide() {
        if (arrowNext.isDisplayed()) {
            // Get current slide number
            int slideActiveDataIndex = Integer.parseInt(this.webDriver.getAttributeOfGivenElement(slideActive, "data-index"));
            logger.info("Current slide data-index is: " + slideActiveDataIndex);
            this.webDriver.elementClick(arrowNext);
            // Get current slide number after click
            int slideActiveDataIndexNew = Integer.parseInt(this.webDriver.getAttributeOfGivenElement(slideActive, "data-index"));
            logger.info("Current slide data-index is: " + slideActiveDataIndexNew);
            assertTrue(slideActiveDataIndex < slideActiveDataIndexNew);
        }
    }

    public void onboardingPrevSlide() {
        if (arrowPrev.isDisplayed()) {
            // Get current slide number
            int slideActiveDataIndex = Integer.parseInt(this.webDriver.getAttributeOfGivenElement(slideActive, "data-index"));
            logger.info("Current slide data-index is: " + slideActiveDataIndex);
            this.webDriver.elementClick(arrowPrev);
            // Get current slide number after click
            int slideActiveDataIndexNew = Integer.parseInt(this.webDriver.getAttributeOfGivenElement(slideActive, "data-index"));
            logger.info("Current slide data-index is: " + slideActiveDataIndexNew);
            assertTrue(slideActiveDataIndex < slideActiveDataIndexNew);
        }
    }

    public void onboardingMoveToLastSlide() {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                while (arrowNext.isDisplayed()) {
                    this.onboardingNextSlide();
                }
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void onboardingMoveToFirstSlide() {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                while (arrowPrev.isDisplayed()) {
                    this.onboardingPrevSlide();
                }
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void onboardingPageNextAction() {
        onboardingMoveToLastSlide();
    }

    public void onboardingPagePreviousAction() {
        onboardingMoveToFirstSlide();
    }

    public void onboardingPageStartAction() {
        onboardingMoveToLastSlide();
        webDriver.elementClick(buttonContinue);
    }

    public void onboardingPageExitAction() {
        webDriver.elementClick(arrowNext);
        webDriver.pressKeyEsc();
    }

    public void checkOnboardingIsDisplayed() {

        //this.webDriver.elementWaitDisplayed(arrowNext);
        logger.info("Arrow right is displayed");

        logger.info("Slides amount: " + slides.size());
        assertEquals(slides.size(), slideDots.size());
        for (WebElement slide : slides) {
            this.webDriver.elementWaitDisplayed(slide);
            assertTrue(slideTitle.isDisplayed());
            assertTrue(slideImage.isDisplayed());
            assertTrue(slideDescription.isDisplayed());
            logger.info("Slide is displayed");
        }
        this.webDriver.elementWaitDisplayed(arrowPrev);
        logger.info("Arrow left is displayed");
        this.webDriver.elementWaitDisplayed(buttonContinue);
        logger.info("Button 'Comenzar' is displayed");
    }

    public void closeOnboardingInOnboarding() {
        this.webDriver.elementWaitDisplayed(closeOnboarding);
        this.webDriver.elementClick(closeOnboarding);
    }

    public void checkOnboardingIsNotDisplayed() {
        Awaitility.await().atMost(Duration.ofSeconds(5)).until(() -> {
            try {
                webDriver.waitUntil(20, wd -> !onboardingSlider.isDisplayed());
                assertEquals(0, slides.size());
                logger.info("Onboarding is not displayed");
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void closeOnboardingNoCloseButton() {
        this.webDriver.elementWaitDisplayed(onboardingOutSide);
        this.homePageAction.clickTopLeftNavbarHome();
    }

    public void setIntoBrowserSessionStorage(boolean state, String profile) {
        webDriver.setBrowserSessionStorageShowOnboarding(state, profile);
    }
}
