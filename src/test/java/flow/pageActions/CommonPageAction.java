package flow.pageActions;

import com.google.common.collect.Lists;
import flow.pageObjects.CommonPageElementsFlow;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;
import static org.openqa.selenium.remote.http.Contents.utf8String;

public class CommonPageAction extends CommonPageElementsFlow {

    public CommonPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void loadPage(String baseUrl, String authClientCasId) {
        this.webDriver.openUrl(baseUrl);
        this.webDriver.setBrowserLocalStorageAll(authClientCasId);
        this.webDriver.openUrl(baseUrl); //TODO check if its needed after previous method refresh
    }

    public void loadPageAuthenticated(String baseUrl) {
        this.webDriver.openUrl(baseUrl);
        this.webDriver.setBrowserLocalStorageWithToken();
    }

    public void setLocalStorageValues() {
        if (Hooks.props.cloudService() == null || Hooks.props.cloudService().isEmpty()) {
            this.webDriver.getBrowserLocalStorageAll();
        }
    }

    public void clearLocalStorageValues() {
        if (Hooks.props.cloudService() == null || Hooks.props.cloudService().isEmpty()) {
            Hooks.localStorage.clear();
            this.webDriver.clearBrowserLocalStorage();
            this.webDriver.navigate().refresh();
        }
    }

    public void checkTitle(){
        webDriver.waitUntil(10, wd -> titleHelp.isEnabled());
        assertTrue("Element is not displayed",titleHelp.isEnabled());
    }

    public void pageIsDisplayed() {
        // Wait base elements load superHero Carrusel
        this.superHeroCarruselIsDisplayed();
        // navbarContent
        this.navbarContentsIsDisplayed(
                true,
                true,
                true,
                true,
                true,
                true,
                true);
        assertTrue("Element Search not displayed", buttonSearchWrapperIsDisplayed());
        assertTrue("Element Dropdown not displayed", dropdownUser.isDisplayed());
        logger.info("Elements are displayed");
    }

    public void checkSearchButtonText() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputSearchInput));
        assertEquals("¿Qué estás buscando?",
                inputSearchInput.getAttribute("placeholder"));
        logger.info(" placeholder is Displayed");
    }


    public void searchContent(String title) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        this.webDriver.elementClick(buttonSearchWrapper);
        wait.until(ExpectedConditions.elementToBeClickable(inputSearchInput));
        assertEquals("¿Qué estás buscando?",
                inputSearchInput.getAttribute("placeholder"));
        this.webDriver.elementSendText(inputSearchInput, title);
        logger.info("Searching for: " + title);
    }

    public void clickOnInputSearch() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        this.webDriver.elementClick(buttonSearchWrapper);
        wait.until(ExpectedConditions.elementToBeClickable(inputSearchInput));
        assertEquals("¿Qué estás buscando?",
                inputSearchInput.getAttribute("placeholder"));
    }

    public void sendCharacter(String character){
        inputSearchInput.sendKeys(character);
        logger.info("Searching for character: " + character);
    }


    public WebElement getContentRestrictedRandom() {
        WebElement visibleRestrictedRandomContent = null;
        try {
            webDriver.waitUntil(10, wd -> itemRestrictedActive.size() > 0);
            logger.info("Restricted elements found");
            List<WebElement> visibleRestrictedContents = itemRestrictedActive;
            int restrictedRandomItemNumber = UtilsRandom.getRandomThreadLocal(0, visibleRestrictedContents.size() - 1);
            visibleRestrictedRandomContent = visibleRestrictedContents.get(restrictedRandomItemNumber);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.info("Random restricted content selected: " + restrictedRandomItemNumber);
        } catch (Throwable e) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.info("No Random restricted content found");
        }
        return visibleRestrictedRandomContent;
    }

    public void checkContentRestrictedNotExist() {
        assertEquals("Restricted content not exist", 0, itemRestrictedActive.size());
    }


    public void scrollUpVertical() {
        webDriver.executeScript("window.scroll(0,-250)");
    }

    public void scrollDownVertical() {
        webDriver.executeScript("window.scrollBy(0,450)");
        logger.info("window scrolled down");
    }

    public void superHeroCarruselIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(50));

        Awaitility.await().atMost(20, SECONDS).until(() -> {
            try {
                wait.until(ExpectedConditions.visibilityOf(
                        this.webDriver.findElement(this.getSuperhero())));
                return true;
            } catch (Throwable throwable) {
                scrollUpVertical();
                throwable.printStackTrace();
                return false;
            }
        });

        WebElement superhero = this.webDriver.findElement(this.getSuperhero());

        // Check superhero visibility
        wait.until(ExpectedConditions.visibilityOf(
                superhero.findElement(this.getSuperheroCarouselActive())));
        assertTrue("Element Superhero Banner not displayed", superhero.isDisplayed());

        // Check elements
        this.webDriver.scrollPageToElementAwaitility(
                this.webDriver.findElement(this.getSuperheroCarouselActive()));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                this.getSuperheroCarouselActive()));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                this.getSuperheroCarouselPlayButton()));
        wait.until(ExpectedConditions.elementToBeClickable(
                superhero.findElement(this.getSuperheroCarouselActive())
                        .findElement(this.getSuperheroCarouselPlayButton())));
        assertTrue("Element Superhero Active Carousel Play Button not displayed",
                superhero.findElement(this.getSuperheroCarouselActive())
                        .findElement(this.getSuperheroCarouselPlayButton()).isDisplayed());
        assertTrue("Element Superhero Active Carousel MoreInfo Button not displayed",
                superhero.findElement(this.getSuperheroCarouselActive())
                        .findElement(this.getSuperheroCarouselMoreInfoButton()).isDisplayed());
        this.webDriver.elementMoveTo(superhero.findElement(this.getSuperheroCarouselActive()));
        logger.info("Superhero elements are displayed");
    }

    public void navbarContentsIsDisplayed(boolean hasContentHome,
                                          boolean hasContentTvGuide,
                                          boolean hasContentMovies,
                                          boolean hasContentSeries,
                                          boolean hasContentKids,
                                          boolean hasContentRentedItems,
                                          boolean hasContentMyContent) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(navbarContentHome));

        if (hasContentHome) {
            assertTrue("Element Navbar Home not displayed", navbarContentHome.isDisplayed());
        }
        if (hasContentTvGuide) {
            assertTrue("Element Navbar TvGuide not displayed", navbarContentTvGuide.isDisplayed());
        }
        if (hasContentMovies) {
            assertTrue("Element Navbar Movies not displayed", navbarContentMovies.isDisplayed());
        }
        if (hasContentSeries) {
            assertTrue("Element Navbar Series not displayed", navbarContentSeries.isDisplayed());
        }
        if (hasContentKids) {
            assertTrue("Element Navbar Kids not displayed", navbarContentKids.isDisplayed());
        }
        if (hasContentRentedItems) {
            assertTrue("Element Navbar Rented Items not displayed", navbarContentRentedItems.isDisplayed());
        }
        if (hasContentMyContent) {
            assertTrue("Element Navbar MyContent not displayed", navbarContentMyContent.isDisplayed());
        }
        logger.info("Navbar contents elements are displayed");
    }

    public void loadStripe(String title) {
        // load all stripes
        // Wait for condition
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                this.webDriver.searchPageDownForElement(stripe.get(0));
                WebElement stripe = getContentStripe(title);
                this.webDriver.scrollPageToElementAwaitility(stripe);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
        this.webDriver.scrollPageToTop();
        logger.info("Stripes are displayed");
    }

    public void accessSeeMoreToStripe(String titleStripe) {
        Awaitility.await().atMost(90, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
                WebElement stripe = this.webDriver.findElement(By.xpath(".//h3[text()='"
                        + titleStripe
                        + "']//ancestor::div[contains(@class, 'contentStripe') and not(contains(@class,'row'))]"));
                WebElement seeMore = stripe.findElement(seeMoreBy);
                this.webDriver.elementMoveTo(seeMore,3);
                this.webDriver.elementClick(seeMore);
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                this.webDriver.scrollPageDown();
                Thread.sleep(500);
                return false;
            }
        });
        this.webDriver.scrollPageToTop();
        logger.info("Stripe "+ titleStripe + " are displayed");
    }

    public void navigateBack(){
        this.webDriver.navigate().back();
    }

    public String getTitleStripe(){
        return stripeTitles.stream().findFirst().get().getText();
    }

    public void goToStripe(String titleStripe) {
        Awaitility.await().atMost(90, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1));
                WebElement stripe = this.webDriver.findElement(By.xpath(".//h3[text()='"
                        + titleStripe
                        + "']//ancestor::div[contains(@class, 'contentStripe') and not(contains(@class,'row'))]"));
                stripe.isDisplayed();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                this.webDriver.scrollPageDown();
                Thread.sleep(150);
                return false;
            }
        });
        logger.info("Stripe "+ titleStripe + " are displayed");
    }

    public void navToStripe(String titleStripe) {
        WebElement stripe = this.webDriver.findElement(By.xpath(".//h3[text()='"
                + titleStripe
                + "']//ancestor::div[contains(@class, 'contentStripe') and not(contains(@class,'row'))]"));
        stripeContentNavigationNext(stripe);
        stripeContentSlickItemsActive(stripe);
        stripeContentNavigationPrev(stripe);
        stripeContentSlickItemsActive(stripe);
    }

    public void navPosterStripe(WebElement stripe, By element) {
        this.webDriver.scrollToElement(stripe);
        List<WebElement> contentTop = stripe.findElements(element);
        for (WebElement poster : contentTop) {
            this.webDriver.elementWaitToBeClickable(poster);
            this.webDriver.elementMoveTo(poster, 15);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
    }

    public void checkNotificationAlert(String message) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Wait notification shown
        webDriver.waitUntil(10, wd -> notificationAlert.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(notificationAlert));
        assertEquals("Alert message incorrect", message, notificationAlert.getAttribute("innerHTML"));
        logger.info("Notification alert message is displayed: " + notificationAlert.getAttribute("innerHTML"));
        logger.info("Notification alert message is correct: " + notificationAlert.getAttribute("innerHTML"));
        // Wait notification hidden
        wait.until(ExpectedConditions.invisibilityOf(notificationAlert));
    }

    //
    public void userIsLogged() {
        assertTrue(dropdownUser.isDisplayed());
        logger.info("The user is logged in");
    }

    public void logoutUser() {
        IsPopUpHomeClosed();
        clickDropdownUser();
        this.webDriver.elementClick(dropdownItemLogout);
        logger.info("The user is logged out");
    }

    public void checkParentalControlCheckbox(Boolean enabled) {
        webDriver.waitUntil(10, wd -> dropdownParentalControl.isDisplayed());
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        WebElement parentalControl = dropdownParentalControl;
        WebElement parentalControlCheckbox = parentalControl.findElement(this.getDropdownParentalControlCheckbox());
        if (enabled) {
            wait.until(ExpectedConditions.elementSelectionStateToBe(parentalControlCheckbox, true));
            assertTrue("Parental Control Checkbox not enabled", parentalControlCheckbox.isSelected());
            logger.info("Parental control checkbox is enabled");
        } else {
            wait.until(ExpectedConditions.elementSelectionStateToBe(parentalControlCheckbox, false));
            assertFalse("Parental Control Checkbox is enabled", parentalControlCheckbox.isSelected());
            logger.info("Parental control checkbox not enabled");
        }
    }

    public void enterParentalControlPinAndConfirm(String pin) {
        this.parentalControlModalIsDisplayed();
        this.webDriver.elementSendText(parentalControlModalPinInput, pin);
        this.webDriver.elementClick(parentalControlModalConfirmButton);
    }

    public void enterParentalControlIncorrectPinAndConfirm(String pin) {
        this.parentalControlModalIsDisplayed();
        this.webDriver.elementSendText(parentalControlModalPinInput, pin);
        this.webDriver.elementClick(parentalControlModalConfirmButton);
    }

    public void enableParentalControlIfDisabled() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        clickDropdownUser();
        wait.until(ExpectedConditions.visibilityOf(dropdownParentalControl));
        WebElement parentalControl = dropdownParentalControl;
        WebElement parentalControlCheckbox = parentalControl
                .findElement(this.getDropdownParentalControlCheckbox());
        if (!parentalControlCheckbox.isSelected()) {
            this.webDriver.elementClick(parentalControl);
        }
        checkParentalControlCheckbox(true);
        clickDropdownUser();
        logger.info("Parental control checkbox is enabled");
    }

    public void settingsControlParental(){
        webDriver.waitUntil(10, wd -> dropdownUser.isDisplayed());
        clickDropdownUser();
        webDriver.waitUntil(10, wd -> dropdownParentalControl.isDisplayed());
        webDriver.elementClick(dropdownParentalControl);
    }

    public void pageIsDisplayedSettingsControlParental(){
        assertTrue("Element title not displayed", titleControlParental.isDisplayed());
        assertTrue("Element message setting control parental ", MessageSettingControlParental.isDisplayed());
        assertTrue("Element button close",buttonClose.isDisplayed());
        assertTrue("Element button setting",buttonSetting.isDisplayed());
    }

    public void disableParentalControl(String pinParental) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        clickDropdownUser();
        wait.until(ExpectedConditions.visibilityOf(dropdownParentalControl));
        WebElement parentalControl = dropdownParentalControl;
        WebElement parentalControlCheckbox = parentalControl
                .findElement(this.getDropdownParentalControlCheckbox());
        // Check that parental control is enabled
        wait.until(ExpectedConditions.elementSelectionStateToBe(parentalControlCheckbox, true));
        assertTrue("Parental Control Checkbox is not enabled", parentalControlCheckbox.isSelected());
        // Disable parental control
        this.webDriver.elementClick(parentalControl);
        enterParentalControlPinAndConfirm(pinParental);
        logger.info("Disabling Parental control");
    }

    public void checkParentalControlIsDisable() {
        clickDropdownUser();
        checkParentalControlCheckbox(false);
        logger.info("Parental control checkbox is disabled");
    }


    public void parentalControlModalIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(parentalControlModal));
        assertTrue(parentalControlModal.isDisplayed());
        assertTrue(parentalControlModalHead.isDisplayed());
        assertTrue(parentalControlModalPinInput.isDisplayed());
        assertTrue(parentalControlModalCancelButton.isDisplayed());
        assertTrue(parentalControlModalConfirmButton.isDisplayed());
        logger.info("Elements parental control modal are displayed");
    }

    public void superheroNavigationPrev() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        WebElement superHero = this.webDriver.findElement(this.getSuperhero());
        WebElement bannerSuperheroCarouselActive = superHero.findElement(this.getSuperheroCarouselActive());
        wait.until(ExpectedConditions.visibilityOf(
                bannerSuperheroCarouselActive));
        // test superhero navigation Prev
        this.webDriver.elementClick(superheroCarouselArrowPrev);
        wait.until(ExpectedConditions.elementToBeClickable(superheroCarouselArrowNext));
        wait.until(ExpectedConditions.visibilityOf(bannerSuperheroCarouselActive));
        logger.info("Superhero navigation prev");
    }

    public void superheroNavigationNext() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        WebElement superHero = this.webDriver.findElement(this.getSuperhero());
        WebElement bannerSuperheroCarouselActive = superHero.findElement(this.getSuperheroCarouselActive());
        wait.until(ExpectedConditions.visibilityOf(bannerSuperheroCarouselActive));
        // test superhero navigation Next
        this.webDriver.elementClick(superheroCarouselArrowNext);
        wait.until(ExpectedConditions.elementToBeClickable(superheroCarouselArrowPrev));
        wait.until(ExpectedConditions.visibilityOf(bannerSuperheroCarouselActive));
        logger.info("Superhero navigation next");
    }

    public void checkSuperheroNavigation() {
        this.superheroNavigationNext();
        this.superheroNavigationNext();
        this.superheroNavigationPrev();
        this.superheroNavigationPrev();
    }

    public WebElement getFirstContentStripe() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(stripe.get(1)));
        return stripe.get(1);
    }

    public WebElement getFirstContentStripe(String stripe) {
        String items = "//div[@class='contentStripe Inicio' and .//h3[text()='%s']]//div[@class='slick-track']//div[@class='item']";
        By locator = By.xpath(String.format(items, stripe));
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).get(0);
    }

    public WebElement getFirstContentStripeFirstSlickItem() {
        return getFirstContentStripe().findElements(this.getStripeSlickItemActive()).get(0);
    }

    public void stripeRandomItemPlay(WebElement stripe) {
        List<WebElement> itemElementList = stripe.findElements(this.getItemPlayWrapper());
        WebElement itemElement = itemElementList.get(UtilsRandom.getRandom(0, itemElementList.size()));
        WebElement itemElementPlayButton = itemElement.findElement(this.getItemPlayIcon());
        this.webDriver.scrollPageToElementAwaitility(stripe);
        this.webDriver.elementClick(itemElementPlayButton);
    }

    public WebElement getRandomContentStripe() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // wait for stripes to be present
        webDriver.waitUntil(10, wd -> stripe.size() > 0);
        List<WebElement> contentStripes = stripe;
        int contentStripeAmount = contentStripes.size();
        int randomContentStripe = UtilsRandom.getRandom(0, contentStripeAmount);
        logger.info("Random Content Stripe: " + randomContentStripe);
        return contentStripes.get(randomContentStripe);
    }

    public void getContentStripeVod(String title) {
        // Search content stripe, random item and select it
        this.webDriver.elementClick(this.getRandomContentStripe());
    }

    public List<WebElement> getChannel(String channelNumber) {
        String contentTitle = ".//a[@href='/vivo?channel=" + channelNumber + "']";
        By contentBy = By.xpath(contentTitle);
        return this.webDriver.findElements(contentBy);
    }

    public void checkChannelExists(String channelNumber) {
        Awaitility.await().atMost(Duration.ofSeconds(20)).until(() -> {
            try {
                List<WebElement> contentItems = getChannel(channelNumber);
                assertEquals("Channel is not found " + channelNumber, 0, contentItems.size());
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
        logger.info("Channel is found " + channelNumber);
    }

    public List<WebElement> getContentElement(String title) {
        String contentTitle = ".//img[@alt='" + title + "']";
        By contentBy = By.xpath(contentTitle);
        return this.webDriver.findElements(contentBy);
    }

    public void checkContentExists(String title) {
        List<WebElement> contentItems = getContentElement(title);
        assertEquals(1, contentItems.size());
        logger.info("Content is found " + title);
    }

    public void checkContentNotExists(String title) {
        Awaitility.await().atMost(20, SECONDS).until(() -> {
            try {
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                assertEquals(0, getContentElement(title).size());
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                webDriver.navigate().refresh();
                return false;
            }
        });
        logger.info("Content is found " + title);
    }

    public WebElement getContentStripe(String title) {
        WebElement matchingStripe = null;
        // Get a list of available stripes
        List<WebElement> stripes = stripe;
        logger.info("Size stripe: " + stripes.size());
        // Loop for every stripe in list
        for (WebElement stripe : stripes) {
            // Obtain stripe title
            WebElement stripeHeader = stripe.findElement(this.getStripeHeader());
            logger.info("stripe title: " + stripeHeader.getText());
            String stripeHeaderText = stripeHeader.getText().toLowerCase();
            // Compare current stripe header title with method parameter
            if (stripeHeaderText.contains(title.toLowerCase())) {
                logger.info("stripe title matches: " + stripeHeader.getText());
                matchingStripe = stripe;
                break;
            }
        }
        assertNotNull("No stripe title matches: " + title, matchingStripe);
        return matchingStripe;
    }

    public void accessContentStripe(String title) {
        this.webDriver.elementClick(getContentStripe(title));
    }

    public void scrollToContentStripe(String title) {
        boolean found = false;
        List<WebElement> stripes = stripe;
        List<WebElement> reverseStripes = Lists.reverse(stripes);

        for (WebElement stripe : reverseStripes) {
            String stripeTitle = stripe.findElement(this.getStripeHeader()).getText();
            if (stripeTitle.contains(title)) {
                found = true;
                this.webDriver.scrollPageToElement(stripe);
                this.webDriver.scrollPageToElement(stripe.findElement(
                        this.getStripeSlickItemActive()));
                this.webDriver.elementWaitToBeClickable(stripe.findElement(
                        this.getStripeSlickItemActive()), 1);
                break;
            }
        }
        if (!found) {
            Awaitility.await().atMost(50, SECONDS).until(() -> {
                try {
                    boolean foundAwaitility = false;
                    List<WebElement> stripesAwaitility = stripe;
                    List<WebElement> reverseStripesAwaitility = Lists.reverse(stripesAwaitility);
                    WebElement stripe = reverseStripesAwaitility.get(0);
                    String stripeTitle = stripe.findElement(this.getStripeHeader()).getText();
                    logger.info("stripeTitle: " + stripeTitle);
                    if (stripeTitle.contains(title)) {
                        foundAwaitility = true;
                        this.webDriver.scrollPageToElement(stripe);
                        this.webDriver.scrollPageToElement(stripe.findElement(
                                this.getStripeSlickItemActive()));
                        this.webDriver.elementWaitToBeClickable(stripe.findElement(
                                this.getStripeSlickItemActive()), 1);
                    }
                    assertTrue("Stripe not found", foundAwaitility);
                    return true;
                } catch (Throwable throwable) {
                    this.webDriver.scrollPageDown();
                    return false;
                }
            });
        }
    }

    public void checkStripeIsDisplayed(String title) {
        logger.info("Searching stripe: " + title);
        Awaitility.await().atMost(30, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

                // Get active stripe title
                WebElement stripeTitleActive = stripeTitle;
                logger.info("Active stripe title: " + stripeTitleActive.getText());

                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public WebElement getRandomItemFromRandomContentStripe() {
        List<WebElement> slickItem = this.webDriver.findElements(this.getStripeSlickItemActive());
        int slickItemAmount = slickItem.size();
        int randomSlickItem = UtilsRandom.getRandom(0, slickItemAmount);
        logger.info("Random Slick Item number: " + randomSlickItem);
        return slickItem.get(randomSlickItem);
    }

    public WebElement getFirstSearchContentStripe() {
        return stripeSearchStripe.get(0);
    }

    public WebElement getFirstSearchContentStripeFirstSlickItem() {
        return getFirstSearchContentStripe()
                .findElements(this.getBySlickItem()).get(0);
    }

    public WebElement getFirstSearchContentStripeChannelFirstSlickItem() {
        return this.webDriver.findElements(this.getBySlickItem()).get(0);
    }

    public WebElement getFirstSearchContentChannelItem() {
        WebElement channelStripe = this.webDriver.findElement(getChannelStripe());
        return channelStripe.findElements(getChannelItem()).get(0);
    }

    public void stripeContentSlickItemsActive(WebElement contentStripe) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(
                contentStripe.findElements(this.getStripeSlickItemActive())));
        int stripeSlickItems = contentStripe.findElements(this.getStripeSlickItemActive()).size();
        logger.info("Stripe slick items: " + stripeSlickItems);
    }

    public void stripeContentNavigationNext(WebElement contentStripe) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Scroll to element
        this.webDriver.scrollPageToElementAwaitility(contentStripe);

        // Make next button visible
        this.webDriver.mouseMoveElement(this.getStripeSlickArrowNext());

        // Click next button
        this.webDriver.elementClick(
                contentStripe.findElement(this.getStripeSlickArrowNext()));

        // Check prev button (prev arrow is enabled when stripe is scrolled)
        wait.until(ExpectedConditions.elementToBeClickable(
                contentStripe.findElement(this.getStripeSlickArrowPrev())));

        this.webDriver.sleep(1);
        logger.info("Content stripe scrolled right");
    }

    public void stripeContentNavigationPrev(WebElement contentStripe) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Scroll to element
        this.webDriver.scrollPageToElementAwaitility(contentStripe);

        // Make prev button visible
        this.webDriver.mouseMoveElement(this.getStripeSlickArrowPrev());

        // Click prev button
        this.webDriver.elementClick(
                contentStripe.findElement(this.getStripeSlickArrowPrev()));

        webDriver.sleep(1);
        logger.info("Content stripe scrolled left");
    }

    public void clickStripeContentSeeMore(WebElement contentStripe) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        //String stripeTitle = contentStripe.findElement(this.homePageFlow.getStripeTitle()).getText();
        // make button visible & clickable
        WebElement contentStripeSeeMore = contentStripe.findElement(
                this.getStripeContentSeeMore());
        try {
            this.webDriver.searchPageUpForElement(contentStripe);
            wait.until(ExpectedConditions.elementToBeClickable(contentStripeSeeMore));
            // click content stripe see more button
            this.webDriver.scrollPageToElementAwaitility(contentStripeSeeMore);
            this.webDriver.elementClick(contentStripeSeeMore);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            this.webDriver.searchPageUpForElement(contentStripe);
            wait.until(ExpectedConditions.elementToBeClickable(contentStripeSeeMore));
            // click content stripe see more button
            this.webDriver.elementClick(contentStripeSeeMore);
        }
    }

    public void clickActiveSuperHeroInfo() {
        WebElement bannerSuperHero = this.webDriver.findElement(
                this.getSuperhero());
        WebElement caruselItemActive = bannerSuperHero.findElement(
                this.getSuperheroCarouselActive());
        WebElement caruselInfo = caruselItemActive.findElement(
                this.getSuperheroCarouselInfo());
        this.webDriver.elementClick(caruselInfo);
        logger.info("Clicked SuperHero info button");
    }

    public void clickActiveSuperHeroButtonPlay() {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                if (carouselLabelButton.getText().contains("ALQUILAR") || carouselLabelButton.getText().contains("VER EN STAR+")
                        || carouselLabelButton.getText().contains("VER EN DISNEY+")
                        || carouselLabelButton.getText() == null) {
                    webDriver.clickOn(superheroCarouselArrowNext);
                    return false;
                } else {
                    webDriver.clickOn(currentActiveCarouselPlayButton);
                }
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public void accessChangeProfilePage() {
        clickDropdownUser();
        this.webDriver.elementClick(dropdownChangeProfile);
        logger.info("Accessed to 'Change profile user' page");
    }

    public void accessNavbarElementStart(){
        this.webDriver.elementClick(navbarContentHome);
        logger.info("Accessed to Navbar > 'Inicio'");
    }

    public void accessNavbarElementTvGuide() {
        this.webDriver.elementClick(navbarContentTvGuide);
        logger.info("Accessed to Navbar > 'TV Guide'");
    }

    public void accessNavbarElementMovies() {
        this.webDriver.elementClick(navbarContentMovies);
        logger.info("Accessed to Navbar > 'Movies'");
    }

    public void accessNavbarElementSeries() {
        this.webDriver.elementClick(navbarContentSeries);
        logger.info("Accessed to Navbar > 'Series'");
    }

    public void accessNavbarElementKids() {
        this.webDriver.elementClick(navbarContentKids);
        logger.info("Accessed to Navbar > 'Kids'");
    }

    public void accessNavbarElementRentedItems() {
        this.webDriver.elementClick(navbarContentRentedItems);
        logger.info("Accessed to Navbar > 'Rented Items'");
    }

    public void accessParentalControl() {
        clickDropdownUser();
        this.checkParentalControlCheckbox(true);
        this.webDriver.elementClick(dropdownParentalControl);
        logger.info("Accessed to DropDown user > 'Parental Control'");
    }

    public void accessNavbarContentMyContent() {
        this.webDriver.elementClick(navbarContentMyContent);
        logger.info("Accessed to Navbar Content Home Inactive");
    }

    public void accessNavbarMyContentContinueWatching() {
        this.webDriver.elementClick(navbarMyContentContinueWatching);
        logger.info("Accessed to Navbar Continue watching");
    }

    public void accessNavbarMyContentFavorites() {
        this.webDriver.elementClick(navbarMyContentFavorites);
        logger.info("Accessed to Navbar my content favorites");
    }

    public void accessNavbarMyContentRecordings() {
        this.webDriver.elementClick(navbarMyContentRecordings);
        logger.info("Accessed to Navbar my content Recordings");
    }

    public void checkProgressBarStripe() {
        webDriver.waitUntil(10, wd -> progressBarLiveTv.isDisplayed());
        assertTrue("Progress bar is not displayed", progressBarLiveTv.isDisplayed());
        logger.info("Progress bar is displayed");
    }

    public void checkProgressBarStripeContinueWatching() {
        webDriver.waitUntil(10, wd -> progressBarContinueWatching.isDisplayed());
        assertTrue("Progress bar is not displayed", progressBarContinueWatching.isDisplayed());
        logger.info("Progress bar is displayed");
    }

    public void accesRandomStripeContent(String stripe) {
        this.webDriver.elementClickByCoordinates(
                this.getFirstContentStripe(stripe), 0.5, 0.2
        );
    }

    public void checkMenuItems() {
        this.webDriver.elementWaitDisplayed(dropdownEditProfile);
        assertTrue("Menu element user is not displayed", dropdownUser.isDisplayed());
        assertTrue("Menu element user profile is not displayed", dropdownEditProfile.isDisplayed());
        assertTrue("Menu element change user profile is not displayed", dropdownChangeProfile.isDisplayed());
        assertTrue("Menu element user parental control is not displayed", dropdownParentalControl.isDisplayed());
        assertTrue("Menu element user parental control checkbox is not displayed",
                this.webDriver.findElement(this.getDropdownParentalControlCheckbox()).isEnabled());
        assertTrue("Menu element user configuration is not displayed", dropdownConfiguration.isDisplayed());
        assertTrue("Menu element user help is not displayed", dropdownHelp.isDisplayed());
        assertTrue("Menu element user logout is not displayed", dropdownItemLogout.isDisplayed());
    }

    public void clickDropdownUser() {
        this.webDriver.elementClick(dropdownUser);
    }

    public void IsPopUpHomeClosed(){
        try {
            webDriver.waitUntil(15,wd -> closePopUpHome.isDisplayed());
            webDriver.clickOn(closePopUpHome);
        } catch (TimeoutException | NoSuchElementException ignore) {
        }
    }

    public void isPopUpHomeClosedParamount(){
        try {
            webDriver.waitUntil(10,wd -> closePopUpHomeParamount.isDisplayed());
            webDriver.clickOn(closePopUpHomeParamount);
        } catch (TimeoutException | NoSuchElementException ignore) {
        }
    }

    public void disneyBannerIsDisplayed() {
        webDriver.elementWaitDisplayed(bannerDisney);
    }

    public void clickDisneyBanner() {
        webDriver.elementClick(bannerDisney);
    }

    public void switchToNewTab(int tabNumber) {
        webDriver.switchToNewTab(tabNumber);
    }

    public void createNewTab() {
        webDriver.createNewTab();
    }

    public void closeCurrentTab() {
        webDriver.closeCurrentTab();
    }

    public void clickExternalLoginDisney() {
        webDriver.elementClick(this.externalLoginDisney);
    }

    public void clickExternalSubscriptionDisney() {
        webDriver.elementClick(externalSubscriptionDisney);
    }

    public void waitMenuAccount() {
        webDriver.elementWaitDisplayed(menuAccount);
    }

    public void clickDropdownMyAccount() {
        webDriver.elementClick(dropDownMyAccount);
    }

    public void clickSubscriptions(){
        webDriver.elementClick(subscriptions);
    }

    public void waitSubscriptionDisneyActive() {
        webDriver.elementWaitDisplayed(subscriptionDisneyActive);
    }

    public void clickNavbarContentMyContent() {
        webDriver.elementWaitDisplayed(navbarContentMyContent);
        webDriver.elementClick(navbarContentMyContent);
    }

    public void clickThisEmission() {
        webDriver.elementWaitDisplayed(elementThisEmission);
        webDriver.elementClick(elementThisEmission);
    }

    public void clickNavbarRecordings() {
        webDriver.elementWaitDisplayed(navBarRecordings);
        webDriver.elementClick(navBarRecordings);
    }

    public void clickNavbarContentTvGuide() {
        webDriver.elementClick(navbarContentTvGuide);
    }

    public Boolean checkMessageErrorIncorrectPinParentalControl() {
        return messageErrorIncorrectPinParentalControl.isDisplayed();
    }

    public void checkEpisodieProgressBar() {
        webDriver.waitUntil(10, wd -> progressBar.isDisplayed());
        assertTrue("Progress bar is not displayed", progressBar.isDisplayed());
        logger.info("Progress bar is displayed");
    }

    public boolean buttonSearchWrapperIsDisplayed() {
        return buttonSearchWrapper.isDisplayed();
    }

    public boolean dropdownUserIsDisplayed() {
        return dropdownUser.isDisplayed();
    }

    public boolean parentalControlModalElementIsDisplayed() {
        return parentalControlModal.isDisplayed();
    }

    public void clickParentalControlModalCloseButton() {
        this.webDriver.elementClick(parentalControlModalCloseButton);
    }

    public void clickTourCloseButton() {
        this.webDriver.elementClick(tourCloseButton);
    }

    public void mockResponse(String endpoint, String valueToResponse) {
        Route route = Route.matching(req -> req.getUri().contains(endpoint))
                .to(() -> req -> new HttpResponse().setContent(utf8String(valueToResponse)));
        NetworkInterceptor interceptor = new NetworkInterceptor(Hooks.getDriver().getAugmentedDriver(), route);
    }

    public void activateParentalControl() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dropdownParentalControl));
        WebElement parentalControl = dropdownParentalControl;
        this.webDriver.elementClick(parentalControl);
        logger.info("Activate Parental control");
    }

    public void checkMessageConfigurationParentalControl() {
        webDriver.waitUntil(10, wd -> messageConfiguration.isDisplayed());
        assertTrue(titleMessageConfiguration.isDisplayed());
        logger.info("Parental pin configuration message displayed");
    }

    public void cancelParentalControlActivation() {
        webDriver.elementWaitDisplayed(buttonCancel);
        webDriver.elementClick(buttonCancel);
    }

    public void accessNavbarHome() {
        this.webDriver.mouseMoveElement(navbarContentHome);
        this.webDriver.elementClick(navbarContentHome);
        logger.info("Accessed to Navbar > 'Home'");
    }

    public void scrollStripes(WebElement stripe, WebElement content) {
        Awaitility.await().atMost(30, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                WebElement element = new WebDriverWait(this.webDriver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOf(stripe));
                ((JavascriptExecutor) this.webDriver).executeScript("arguments[0].scrollIntoView();", element);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
                this.webDriver.scrollPageDown();
                this.webDriver.elementMoveTo(content, 3);
                return false;
            }
        });
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }


    public void checkPostersIsDisplayed(String stripePage, List<WebElement> stripe) {
        this.goToStripe(stripePage);
        for (int cont = 0; cont < stripe.size(); ) {
            List<WebElement> contents = stripe.get(cont).findElements(itemWrapperBy);
            this.scrollStripes(stripe.get(cont), contents.get(0));
            for (int x = 0; x < contents.size(); ) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                contents.get(x).isDisplayed();
                this.webDriver.elementMoveTo(contents.get(x));
                assertTrue(contents.get(x).findElement(img).isDisplayed());
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                x++;
            }
            cont++;
        }
    }
}
