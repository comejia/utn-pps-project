package flow.pageActions;

import flow.pageObjects.ContentPageFlow;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContentPageAction extends ContentPageFlow {

    public boolean statusResult;
    int contador = 0;

    public ContentPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed(String title) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Check current page locator
        if (title != "Alquileres") {
            this.webDriver.elementMoveTo(this.commonPageAction.getPageLocator(title), 10);
            wait.until(ExpectedConditions.visibilityOf(this.commonPageAction.getPageLocator(title)));
            assertTrue("Required Page not displayed",
                    this.commonPageAction.getPageLocator(title).isDisplayed());
        }

        logger.info("Navigating category: " + title);

        // Check search button
        assertTrue("Element Search Button not displayed",
                this.commonPageAction.buttonSearchWrapperIsDisplayed());

        // Check superhero
        this.commonPageAction.superHeroCarruselIsDisplayed();

        // Check content stripes
//        assertTrue("No Contents shown for first Stripe",
//                this.commonPageAction.getFirstContentStripeFirstSlickItem().isDisplayed());
        assertTrue("No Contents shown for random Stripe",
                this.commonPageAction.getRandomItemFromRandomContentStripe().isDisplayed());

        logger.info("Elements are displayed");
    }

    public void filtersIsDisplayed(String page) {
        checkFilterElements("Género");
        //checkFilterElements("Canales");
        checkFilterElements("Año de lanzamiento");
        checkFilterElements("Audiencia");
        if (page.contains("Películas")) {
            checkFilterElements("Precio");
        }
        if (page.contains("Series")) {
            checkFilterElements("Canales");
        }
        //if (page.contains("Alquileres")) {
        //}
        if (page.contains("kids")) {
            checkFilterElements("Categoría");
            checkFilterElements("Precio");
        }
        logger.info("Page: " + page);
    }

    public void checkFilterElements(String filterName) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.getFilterNameButton(filterName)));
        WebElement filterElement = this.webDriver.findElement(
                this.getFilterNameButton(filterName));
        WebElement filterElementParent = this.webDriver.elementGetParent(filterElement);
        assertTrue("Filter " + filterName + " not displayed", filterElement.isDisplayed());
        logger.info("Filter displayed: " + filterElement.getText());

        // Get filters list
        // If all filter categories are collapsed, then click the 'filterName' one
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        if (this.webDriver.findElements(this.getFilterNameButtonShow(filterName)).size() == 0) {
            this.webDriver.elementClick(filterElement);
            wait.until(ExpectedConditions.numberOfElementsToBe(
                    this.getFilterNameButtonShow(filterName), 1));
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        // Get filterName sub items
        assertTrue("No filters displayed for: " + filterName,
                webDriver.findElements(getFilterCategoryFilterItem(filterName)).size() > 0);
        List<WebElement> filterCategoryFilterItem = webDriver.findElements(getFilterCategoryFilterItem(filterName));
        for (WebElement filterGenero : filterCategoryFilterItem) {
            // Wait for the subfilter to be clickable to obtain its text
            wait.until(ExpectedConditions.elementToBeClickable(filterGenero));
            logger.info("Filter " + filterName + " Subfilter: " + filterGenero.getText());
        }

        logger.info("Filter " + filterName + " sub items quantity: " + filterCategoryFilterItem.size());
        this.webDriver.elementClick(filterElement);
    }

    public void openFilterButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementMoveTo(filtersButton, 5);
        this.webDriver.elementClick(filtersButton);
        wait.until(ExpectedConditions.visibilityOf(closeFilters));
    }

    public void selectFilterElement(String filterName) {
        WebElement filterElement = this.webDriver.findElement(this.getFilterNameButton(filterName));
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
            logger.info("Size: " + this.webDriver.findElements(this.getFilterNameButtonShow(filterName)).size());
            if (this.webDriver.findElements(this.getFilterNameButtonShow(filterName)).size() == 0) {
                this.webDriver.elementClick(filterElement);
            }
        } catch (NoSuchElementException noSuchElementException) {
            noSuchElementException.printStackTrace();
            this.webDriver.elementClick(filterElement);
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void selectFilterElementItem(String filterItem) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        WebElement filterElementName = this.webDriver.findElement(this.getFilterNameItem(filterItem));
        wait.until(ExpectedConditions.elementToBeClickable(filterElementName));
        this.webDriver.elementClick(filterElementName);

        List<WebElement> listFilterElementItems = this.webDriver.findElements(
                this.getPrimaryButtonFilterItem(filterItem));
        int itemElementCount = 0;
        String filterItemNameFound = "";
        if (listFilterElementItems.size() > 1) {
            for (WebElement listFilterElementItem : listFilterElementItems) {
                if (listFilterElementItem.getText().equals(filterItem)) {

                    itemElementCount++;
                    filterItemNameFound = listFilterElementItem.getText();
                }
            }
            assertEquals("The filters are not the same to the selected filter",
                    1, itemElementCount);
            logger.info("Filter element selected: " + filterItem);
            logger.info("Current filter Element apply: " + filterItemNameFound);
        } else {
            logger.info("Filter element selected: " + filterItem);
            logger.info("Current filter Element apply: " + this.webDriver.findElement(
                    this.getPrimaryButtonFilterItem(filterItem)).getText());
            assertEquals("The filters are not the same to the selected filter",
                    filterItem, this.webDriver.findElement(
                            this.getPrimaryButtonFilterItem(filterItem)).getText());
        }
    }

    public void checkClearFilterButtonIsDisplayed() {
        assertTrue("Clear filter button is not displayed", clearFilterButton.isDisplayed());
        logger.info("Clear filter button is displayed");
    }

    public void clickClearFilterButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        clearFilterButton.click();
        logger.info("Clear filter selection");
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
            wait.until(ExpectedConditions.invisibilityOf(clearFilterButton));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("Clear filter button is not displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void clickRandomContentItem() {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                webDriver.waitForElementVisible(contentItems.get(0));
                int count = contentItems.size();
                int randomItem = UtilsRandom.getRandom(0, count);
                logger.info("Random item selected: " + randomItem);
                this.webDriver.mouseMoveElement(contentItems.get(randomItem));
                this.webDriver.elementClick(playHover.get(randomItem));
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public List<WebElement> getRadios() {
        return this.webDriver.findElements(radioItemDisplayed);
    }

    public void clickRandomRadio() {
        this.webDriver.elementWaitDisplayed(radioStripe);
        List<WebElement> getRadios = radioStripe.findElements(getRadioItemDisplayed());
        WebElement randomRadio = getRadios.get(UtilsRandom.getRandom(0, getRadios.size()));
        this.webDriver.elementWaitDisplayed(randomRadio);
        webDriver.clickOn(randomRadio);
    }

    public void clickRandomMusic() {
        List<WebElement> getMusic = morePopularStripe.findElements(getRadioItemDisplayed());
        WebElement randomMusic = getMusic.get(UtilsRandom.getRandom(0, getMusic.size()));
        this.webDriver.elementWaitDisplayed(randomMusic);
        this.webDriver.elementClick(randomMusic);
    }

    public void clickFiltersButton() {
        this.webDriver.elementClick(filtersButton);
    }

    public void accessToContentSuperHeroRents(String pin) {
        this.webDriver.elementWaitDisplayed(getSuperHero);
        Awaitility.await().atMost(Duration.ofSeconds(60)).until(() -> {
            try {
                superHero = this.webDriver.findElement(getSuperHeroBy);
                contenido = superHero.findElement(getSlickSilde);
                String boton = contenido.findElement(getFlowPrimaryButtonBy).getText();
                assertTrue(boton.contains("$"));
                this.webDriver.mouseMoveElement(getFlowPrimaryButton);
                this.webDriver.elementClick(contenido);
                statusResult = true;
                return true;
            } catch (Throwable throwable) {
                contador++;
                if (contador < 6) {
                    this.webDriver.mouseMoveElement(getNextSuperHero);
                    this.webDriver.elementClick(getNextSuperHero);
                } else {
                    statusResult = false;
                    logger.info("No rental content found");
                    return true;
                }
                return false;
            }
        });
        if (statusResult == true) {
            this.webDriver.elementWaitDisplayed(campoPin);
            this.webDriver.elementSendText(campoPin, pin);

            this.webDriver.elementWaitDisplayed(confirmarPin);
            this.webDriver.elementClick(confirmarPin);
        }
    }

    public void checkDipContectRents() {
        this.webDriver.elementWaitDisplayed(tituloContenido);
        this.webDriver.elementWaitDisplayed(fichaTecnica);
        this.webDriver.elementWaitDisplayed(tipoDeGenero);
    }

    public void pressToRent() {
        this.webDriver.elementWaitDisplayed(getButtonAlquilar);
        this.webDriver.elementClick(getButtonAlquilar);
        this.webDriver.elementWaitDisplayed(getPurchaseContent);
    }

    public void enterPinRent() {
        this.webDriver.elementWaitDisplayed(confirmarPin);
        this.webDriver.elementClick(confirmarPin);
    }

    public void checkAutomaticMovementSuperHero() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(8000));
        assertTrue("Element not displayed", this.webDriver.findElement(getSuperHeroAut).isDisplayed());
        Awaitility.await().atMost(Duration.ofSeconds(80)).until(() -> {
            try {
                WebElement superHero = this.webDriver.findElement(getSuperHeroAut);
                WebElement contenido = superHero.findElement(getSlickActive);
                String info = contenido.findElement(getCarruselInfo).getText();
                logger.info(info);

                this.webDriver.elementWaitDisplayed(getSlickDots);
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public void searchRecordContent() {
        this.webDriver.elementWaitDisplayed(myContents);
        this.webDriver.elementClick(myContents);

        this.webDriver.elementWaitDisplayed(recordings);
        this.webDriver.elementClick(recordings);
        try {
            this.webDriver.elementWaitDisplayed(getItemWrapperBy);
        } catch (Exception e) {
            Assert.fail("NO RECORDINGS AVAILABLE");
        }
        WebElement contents = this.webDriver.findElements(getItemWrapperBy).get(0);
        this.webDriver.elementWaitDisplayed(contents);
        this.webDriver.mouseMoveElement(contents);

        String titleContents = contents.findElement(slickText).getText();
        logger.info(titleContents);

        this.webDriver.elementWaitDisplayed(buttonSearch);
        this.webDriver.elementClick(buttonSearch);

        this.webDriver.elementWaitDisplayed(navSearch);
        this.webDriver.elementClick(navSearch);
        this.webDriver.elementSendText(navSearch, titleContents);

        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void playRecordContent() {
        this.webDriver.waitUntil(10, wd -> contentStripeItem.get(0).isDisplayed());
        contentStripeItem.get(0).click();
        this.webDriver.waitUntil(10, wd -> playContent.isDisplayed());
        this.webDriver.clickOn(playContent);
    }

    public void playRecordedContent() {
        this.webDriver.elementClick(contentSearch);
        this.webDriver.elementWaitDisplayed(playContent);
        this.webDriver.elementClick(playContent);
    }

    public void accessRecordContent() {
        this.webDriver.elementClick(contentSearch);
    }

    public void clickRandomListMusic() {
        this.webDriver.elementWaitDisplayed(musicListGrid);
        List<WebElement> listMusic = musicListGrid.findElements(getRadioItemBy());
        WebElement randomMusic = listMusic.get(UtilsRandom.getRandom(0, listMusic.size()));
        this.webDriver.elementWaitDisplayed(randomMusic);
        this.webDriver.elementMoveTo(randomMusic, 5);
        this.webDriver.elementClick(randomMusic);
    }

    public void closedFilterButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(closeFilters));
        closeFilters.click();
    }

    public void selectFilterItem(String filterItem) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        WebElement filterElementName = this.webDriver.findElement(this.getFilterNameItem(filterItem));
        wait.until(ExpectedConditions.elementToBeClickable(filterElementName));
        this.webDriver.elementClick(filterElementName);
    }

    public void accessContent() {
        this.webDriver.elementWaitDisplayed(getSuperHeroBy);
        List<WebElement> contentsList = stripeContentStripe.get(0).findElements(getSlickActive);
        Random random = new Random();
        int randomValue = random.nextInt(contentsList.size());
        WebElement content = contentsList.get(randomValue);
        this.webDriver.elementMoveTo(content, 5);
        this.webDriver.elementPositionClick(content, -0.5, -0.3);
    }

    public void checkRecording() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOf(webDriver.findElements(itemWrapper).get(2)));

        this.webDriver.scrollToELementPageDownAwaitility(stripeRecordings);

        Awaitility.await().atMost(50, SECONDS).until(() -> {
            try {
                this.webDriver.elementWaitDisplayed(contentSearch);
                this.webDriver.mouseMoveElement(contentSearch);
                this.webDriver.waitForElementNotVisible(buttonPlay, 5);
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void waitRecordingContentIsDisplayed() {
        this.webDriver.waitUntil(10, wd -> contentStripeItem.get(0).isDisplayed());
    }

    public void checkContentBingeWatching() {
        this.webDriver.elementWaitDisplayed(continueWatching);
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = continueWatching.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(Hooks.props.movieFreeAllAges())) {
                        logger.info(titleContent);
                        cont = content.size();
                        status = true;
                    }
                    cont++;
                }
                return status;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void gopAllTitlesIsDisplayed() {
        this.webDriver.elementWaitDisplayed(titleSection);
        assertTrue("Element Title not displayed", titleStripeActive.isDisplayed());
        List<WebElement> contents = this.webDriver.findElements(itemWrapper);
        for (int cont = 0; cont < 8; ) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.elementMoveTo(contents.get(cont));
            assertTrue(contents.get(cont).findElement(img).isDisplayed());
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            cont++;
        }
    }

    public static String getContentTitle;

    public void getContentTitle() {
        getContentTitle = vodTitle.getText();
    }

    public void checkContentContinueViewing() {
        this.webDriver.elementWaitDisplayed(continueWatching);
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = continueWatching.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(getContentTitle)) {
                        cont = content.size();
                        status = true;
                    }
                    cont++;
                }
                return status;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkContentAddedMyList(String titleContent) {
        this.webDriver.scrollToElement(stripeMyMoviesAndSeries);
        webDriver.sleep(1);
        List<WebElement> contentMyMovies = stripeMyMoviesAndSeries.findElements(itemWrapper);
        this.webDriver.elementMoveTo(contentMyMovies.get(0), 2);
        assertEquals(titleContent, contentMyMovies.get(0).findElement(getSlickText).getText());
        this.webDriver.elementClick(contentMyMovies.get(0).findElement(buttonDelete));
    }
}
