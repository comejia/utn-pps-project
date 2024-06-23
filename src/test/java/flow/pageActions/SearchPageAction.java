package flow.pageActions;

import flow.pageObjects.SearchPageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class SearchPageAction extends SearchPageFlow {

    @FindBy(xpath = ".//div[@class='stripeTitle']")
    protected By searchStripeTitleChannelLive;

    @FindBy(xpath = ".//a[@class='channelItem vertical-align null square' and @href]")
    protected By searchSlickStripeTitleChannelLive;

    @FindBy(xpath = ".//div[contains(@class,'slick-slide slick-active')]")
    protected By searchSlickActiveChannelLive;

    @FindBy(xpath = ".//div[@id='flow-lazy-img']//img")
    protected By searchSlickTextChannelLive;
    String titleContentDisneyPlus;


    public SearchPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed(String title) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(this.commonPageAction.getPageLocator(title)));
        wait.until(ExpectedConditions.visibilityOf(
                this.webDriver.findElement(this.getByStripeSearchStripe())));

        // assert that correct page is displayed
        assertTrue("Required page is not displayed",
                this.commonPageAction.getPageLocator(title).isDisplayed());
        logger.info("Required page is displayed");

        // assert at last one stripe with one item displayed
        assertTrue("No Stripe with contents is displayed",
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem().isDisplayed());

        this.commonPageAction.getPageLocator(title)
                .findElements(this.getByStripeTitleString())
                .stream()
                .limit(3)
                .forEach(webElement -> {
                    logger.info("Stripe title: " + webElement.getAttribute("innerHTML"));
                    assertTrue(
                            webElement.getAttribute("innerHTML").contains("Películas, series y programas emitidos") ||
                                    webElement.getAttribute("innerHTML").contains("Guía de TV") ||
                                    webElement.getAttribute("innerHTML").contains("Prime video"));
                });

        var stripeItemsFound = (int) this.commonPageAction.getPageLocator(title)
                .findElements(this.getByStripeTitleString())
                .stream().count();

        if (stripeItemsFound > 0) {
            // focus first stripe first item
            WebElement firstStripeFirstItem = this.commonPageAction.getPageLocator(title)
                    .findElements(this.getByStripeSearchStripe()).get(0);
            WebElement firstStripeFirstItemInfo = firstStripeFirstItem
                    .findElements(this.getByContentInfo()).get(0);
            WebElement firstStripeFirstContentStripeItem = firstStripeFirstItem
                    .findElements(this.getContentStripeItem()).get(0);
            webDriver.mouseOver(firstStripeFirstContentStripeItem
                    .findElement(this.getItemPlayIcon()));
            wait.until(ExpectedConditions.visibilityOf(firstStripeFirstContentStripeItem
                    .findElement(this.getItemPlayIcon())));

            // assert first stripe first item elements
            assertTrue("Item Type is not displayed",
                    firstStripeFirstItemInfo.findElement(this.getContentType()).isDisplayed());
            logger.info("Content type is displayed");
            assertTrue("Item Title is not displayed",
                    firstStripeFirstItemInfo.findElement(this.getByItemInfoTruncateLines()).isDisplayed());
            logger.info("Item title is displayed");

            if (firstStripeFirstItemInfo
                    .findElement(this.getContentType()).getText().equalsIgnoreCase("película")) {
                logger.info("Content type is " + firstStripeFirstItemInfo
                        .findElement(this.getContentType()).getText().toLowerCase());
                assertTrue("Item Duration is not displayed",
                        firstStripeFirstItemInfo.findElement(this.getByItemInfoDuration()).isDisplayed());
                logger.info("Item duration is displayed");
            }
            assertTrue("Parental rating is not displayed",
                    firstStripeFirstItemInfo.findElement(this.getParentalRating()).isDisplayed());
            logger.info("Parental rating is displayed: " + firstStripeFirstItemInfo
                    .findElement(this.getParentalRating()).getText());
            assertTrue("Item Play Icon is not displayed",
                    firstStripeFirstContentStripeItem.findElement(this.getItemPlayIcon()).isDisplayed());
            logger.info("Item play icon is displayed");
        }

        if (stripeItemsFound > 1) {
            // focus second stripe first item
            WebElement secondStripeFirstItemInfo = this.commonPageAction.getPageLocator(title)
                    .findElements(this.getByStripeSearchStripe()).get(1)
                    .findElements(this.getByContentInfo()).get(0);
            webDriver.mouseOver(secondStripeFirstItemInfo);

            assertTrue("Item Title is not displayed",
                    secondStripeFirstItemInfo.findElement(this.getByItemInfoTruncateLines()).isDisplayed());
            assertTrue("Item Age Badge is not displayed",
                    secondStripeFirstItemInfo.findElement(this.getParentalRating()).isDisplayed());
            logger.info("Elements are displayed");
        }
    }

    public void checkNoResultsMessage() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(noResults));
        // assert that there are no results for entered text
        assertTrue("No results message not displayed", noResults.isDisplayed());
    }

    public void checkRecommendedContents() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
        // assert that there are recommended contents
        assertTrue("Recommended contents not found", stripeRecommended.isDisplayed());
    }

    public void checkSearchResult(String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem()));
        logger.info("First content title contains: " + text);
    }

    public void checkSearchChannelResult(String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeChannelFirstSlickItem()));
        logger.info("First content title contains: " + text);
    }

    public void accessFirstItemFromVodSearchResults(String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem()));
        this.webDriver.elementClick(slickItem.get(0));
        logger.info("First content title contains: " + text);
    }

    public void checkSearchChannelsAmount() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        Boolean channelItem = this.commonPageAction.getChannelItem().equals(true);
        logger.info("El stripe de Tv en vivo es : " + channelItem);

        if (channelItem == true) {
            wait.until(ExpectedConditions.elementToBeClickable(
                    this.commonPageAction.getFirstSearchContentChannelItem()));
            assertTrue(this.webDriver.findElement(this.commonPageAction.getChannelStripe())
                    .findElements(this.commonPageAction.getChannelItem()).size() > 0);
        } else {
            logger.info("El contenido buscado esta en el stripe Guía de Tv");
        }

    }

    public void accessSearchChannelsFirstResult() {
        this.webDriver.elementClick(this.commonPageAction.getFirstSearchContentChannelItem());
    }

    public void checkSearchChannelResult() {
        webDriver.waitUntil(10, wd -> contentStripeChannelElement.size() > 0);
        logger.info("Searched channels elements to be than more 0");
    }

    public void selectChannelElement(int channel) {
        WebElement selectedChannel = stripeChannelsElement.get(channel);
        logger.info("Selected Channel: " + selectedChannel.getAttribute("href"));
        this.webDriver.elementClick(selectedChannel);
    }

    public void clickButtonSearchWrapper() {
        this.webDriver.elementClick(buttonSearchWrapper);
    }

    public void refreshUrl() {
        this.webDriver.refreshUrl();
    }

    public void waitSearchNotLoading() {
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            this.webDriver.waitForElementNotVisible(loadingSearch, 2);
            logger.info("Search is not loading");
        } catch (NoSuchElementException e) {
            logger.info("Search loading is not showed");
        }
    }

    public void searchContent(String title) {
        this.webDriver.waitForElementVisible(searchButton, 10);
        this.webDriver.elementClick(searchButton);
        logger.info("Title:  " + title);
        searchButton.sendKeys(title);
        this.waitSearchNotLoading();
        logger.info("Searching content: " + title);
    }

    public void searchContentPromoChannelInStripeLive() {
        webDriver.elementWaitDisplayed(searchStripeTitleChannelLive);
        webDriver.elementWaitDisplayed(searchSlickStripeTitleChannelLive);
        List<WebElement> stripeChannelsLive = webDriver.findElements(searchStripeTitleChannelLive);

        for (WebElement titleStripe : stripeChannelsLive) {
            String titleChannelsLive = titleStripe.findElement(searchStripeTitleChannelLive)
                    .getText().toUpperCase();
            logger.info("STRIPE :" + titleChannelsLive);
            List<WebElement> slicksActive = titleStripe.findElements(searchSlickActiveChannelLive);
            for (WebElement slickActive : slicksActive) {
                webDriver.elementWaitToBeClickable(slickActive);
                String slickText = slickActive.findElement(searchSlickTextChannelLive)
                        .getAttribute("alt");
                System.out.println(slickText);
                if (slickText.contains(Hooks.props.promoChannel())) {
                    System.out.println("CHECKEO BUSQUEDA CORRECTO");
                } else {
                    fail("NO CONTIENE TEXTO DE BUSQUEDA");
                }
            }
        }
    }

    public void checkResultsContentParamountInStripe() {
        this.webDriver.elementWaitDisplayed(stripePeliculasSeriesYProgramas);
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = stripePeliculasSeriesYProgramas.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(Hooks.props.titleContentParamout())) {
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

    public void checkPostersWithoutPlayButton(WebElement element, String title, By elementContent, By elementTitle) {
        this.webDriver.elementWaitDisplayed(element);
        List<WebElement> content = element.findElements(elementContent);
        for ( int cont = 0; cont < content.size(); ) {
            this.webDriver.mouseMoveElement(content.get(cont));
            String titleContent = content.get(cont).findElement(elementTitle).getText();
            if (titleContent.contains(title)) {
                try {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    content.get(cont).findElement(buttonPlayBy).isDisplayed();
                } catch (Exception e) {
                    cont = content.size();
                    logger.info(titleContent);
                }
            }
            cont++;
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void stripePrimeVideoIsDisplayed() {
        this.webDriver.scrollToElement(searchStripePrimeVideo);
        List<WebElement> stripeList = this.webDriver.findElements(titleStripePrimeVideo);
        stripeList.get(3).isDisplayed();
        assertTrue("Stripe 'Prime video' is not displayed in the fourth position", stripeList.get(3)
                .getAttribute("innerHTML").contains("Prime video"));
        WebElement contentStripe = stripeList.get(3).findElement(itemWrapper);
        this.webDriver.mouseMoveElement(contentStripe);
        logger.info("Stripe 'Prime video' is displayed in the fourth position");
    }

    public void viewPostersStripePrimeVideo() {
        var content = Hooks.dataProvider.getContentPrime(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.webDriver.scrollToElement(searchStripePrimeVideo);
        checkPostersWithoutPlayButton(searchStripePrimeVideo,
                content.getTitle(),
                itemWrapper,
                contentTitle);
    }

    public void accessSearchContentPrimeVideo(String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem()));
        WebElement searchContent = searchStripePrimeVideo.findElement(itemWrapper);
        this.webDriver.mouseMoveElement(searchContent);
        searchContent.click();
        logger.info("First content title contains: " + text);
    }

    public void accessSearchLiveTVFirstResult() {
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                this.webDriver.elementClick(this.webDriver.findElement(stripeTvGuide)
                        .findElements(getItemPlayIcon()).get(0));
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkOrderSearchResults() {
        List<WebElement> stripeList = this.webDriver.findElements(titleStripe);
        String stripeName = "Canales";
        switch (stripeName) {
            case "Canales": {
                Assert.assertTrue(stripeList.get(0)
                        .getAttribute("innerHTML").contains("Canales"));
                WebElement result = stripeList.get(0).findElement(getSlickActive);
                this.webDriver.mouseMoveElement(result);
                stripeName = "Películas, series y programas";
            }
            case "Películas, series y programas": {
                Assert.assertTrue(stripeList.get(1)
                        .getAttribute("innerHTML").contains("Películas, series y programas"));
                WebElement result = stripeList.get(1).findElement(itemWrapper);
                this.webDriver.mouseMoveElement(result);
                stripeName = "Guía de TV";
            }
            case "Guía de TV": {
                Assert.assertTrue(stripeList.get(2)
                        .getAttribute("innerHTML").contains("Guía de TV"));
                WebElement result = stripeList.get(2).findElement(itemWrapper);
                this.webDriver.mouseMoveElement(result);
            }

        }
    }

    public void displayCastResults(String name) {
        this.webDriver.elementWaitDisplayed(stripePeliculasSeriesYProgramas);
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = stripePeliculasSeriesYProgramas.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(name)) {
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

    public void accessToCastResults(WebElement stripe ,String nameContent) {
        this.webDriver.elementWaitDisplayed(stripe);
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = stripe.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(nameContent)) {
                        logger.info(titleContent);
                        this.webDriver.elementPositionClick(content.get(cont), -0.5, -0.3);
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

    public void clickOnFirstElementOfContentSearched() {
        this.webDriver.waitUntil(10, wd -> imageChannelCard.isDisplayed());
        this.webDriver.mouseOver(imageChannelCard);
        this.webDriver.clickOn(imageChannelCard);
    }

    public void searchContentWithFilter(String filter) {
        webDriver.sleep(1);
        this.webDriver.mouseMoveElement(gridItem.get(0));
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);

        this.webDriver.elementWaitDisplayed(filterGender);
        this.webDriver.elementClick(filterGender);

        this.webDriver.elementWaitDisplayed(By.xpath(".//*[text()='" + filter + "']//ancestor::button"));
        this.webDriver.elementClick(By.xpath(".//*[text()='" + filter + "']//ancestor::button"));

        List<WebElement> filters = webDriver.findElement(By.xpath(".//*[text()='" + filter + "']//ancestor::div[@class='filterCategoryWrapper']")).findElements(filterBy);
        Random random = new Random();
        int randomValue = random.nextInt(filters.size());
        WebElement filterRandom = filters.get(randomValue);
        this.webDriver.elementClick(filterRandom);

        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
    }

    public boolean checkSearchFiltersResult() {
        return gridItem.size() > 0;
    }

    public void checkResultsSearchFilters(String quantityFilters) {
        this.webDriver.elementWaitDisplayed(titleSection);
        this.webDriver.elementWaitDisplayed(titleStripeActive);
        assertTrue(filtersButton.getText().contains(quantityFilters));
        assertTrue("Search not displayed", this.checkSearchFiltersResult());
        this.webDriver.mouseMoveElement(gridItem.get(1));
    }

    public void applyFilter(String filter, String nameFilter) {
        this.webDriver.elementWaitDisplayed(By.xpath(".//*[text()='" + filter + "']//ancestor::button"));
        this.webDriver.elementClick(By.xpath(".//*[text()='" + filter + "']//ancestor::button"));
        WebElement selectFilter = this.webDriver.findElement(By.xpath(".//*[text()='" + filter + "']//ancestor::div[@class='filterCategoryWrapper']")).
                findElement(By.xpath("//p[text()='" + nameFilter + "']"));
        this.webDriver.elementWaitDisplayed(selectFilter);
        this.webDriver.elementClick(selectFilter);

    }

    public void applyAllFilters() {
        this.webDriver.elementWaitDisplayed(gridItem.get(0));
        this.webDriver.mouseMoveElement(gridItem.get(0));
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);

        this.webDriver.elementWaitDisplayed(filterGender);
        this.webDriver.elementClick(filterGender);
        this.applyFilter("Precio", "Sin Cargo");
        this.applyFilter("Año de lanzamiento", "Último año");
        this.applyFilter("Audiencia", "General");
        this.applyFilter("Canales", "HBO OD");
        this.applyFilter("Categoría", "Películas");
        this.applyFilter("Género", "Acción");

        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
    }

    public void viewFilters() {
        this.checkResultsSearchFilters("6");
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);
        List<WebElement> listAppliedFilters = webDriver.findElements(filtersAppliedBy);
        this.webDriver.elementWaitDisplayed(listAppliedFilters.get(1));
        for (int cont = 0; cont < listAppliedFilters.size(); ) {
            this.webDriver.mouseMoveElement(listAppliedFilters.get(cont));
            assertTrue(listAppliedFilters.get(cont).isDisplayed());
            logger.info(listAppliedFilters.get(cont).getText());
            cont++;
        }
    }

    public void accessFilters() {
        this.webDriver.elementWaitDisplayed(gridItem.get(0));
        this.webDriver.mouseMoveElement(gridItem.get(0));
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);

        this.webDriver.elementWaitDisplayed(filterGender);
        this.webDriver.elementClick(filterGender);
    }

    public void accessDipResult() {
        filter = webDriver.findElement(filtersAppliedBy).getText();
        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
        this.webDriver.mouseMoveElement(gridItem.get(0));
        this.webDriver.elementPositionClick(gridItem.get(0), -0.4, -0.2);
    }

    public void checkFilterDip() {
        this.webDriver.elementWaitDisplayed(checkFilterDip);
        assertTrue(checkFilterDip.getText().contains(filter));
        logger.info(filter, checkFilterDip.getText());
    }

    public void removeFilter() {
        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
        this.checkResultsSearchFilters("1");
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);
        this.webDriver.elementWaitDisplayed(clearFilter);
        this.webDriver.elementClick(clearFilter);
    }

    public void checkRemoveFilter() {
        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
        assertEquals("FILTROS",filtersButton.getText());
    }

    public void accessLiveContentSearch() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem()));
        this.webDriver.scrollPageToElement(searchStripeGuiaDeTv);
        WebElement searchContent = searchStripeGuiaDeTv.findElement(itemWrapper);
        this.webDriver.mouseMoveElement(searchContent);
        this.webDriver.elementPositionClick(searchContent, -0.4, -0.3);
    }

    public void displayCastResults(WebElement stripe ,String name) {
        this.webDriver.elementWaitDisplayed(stripe);
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                boolean status = false;
                List<WebElement> content = stripe.findElements(itemWrapper);
                for (int cont = 0; cont < content.size(); ) {
                    this.webDriver.mouseMoveElement(content.get(cont));
                    String titleContent = content.get(cont).findElement(contentTitle).getText();
                    if (titleContent.contains(name)) {
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

    public void checkFilterDipAdult() {
        this.webDriver.elementWaitDisplayed(checkFilterDip);
        assertTrue(filter.contains(checkFilterDip.getText()));
        logger.info(filter, checkFilterDip.getText());
    }

    public void applyAllFiltersAdult() {
        this.webDriver.elementWaitDisplayed(gridItem.get(0));
        this.webDriver.mouseMoveElement(gridItem.get(0));
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);

        this.webDriver.elementWaitDisplayed(filterGender);
        this.webDriver.elementClick(filterGender);
        this.applyFilter("Año de lanzamiento", "Último año");
        this.applyFilter("Género", "XXX Acción Extrema");

        this.webDriver.elementWaitDisplayed(closeFilter);
        this.webDriver.elementClick(closeFilter);
    }
    public void viewFiltersAdult() {
        this.checkResultsSearchFilters("2");
        this.webDriver.elementWaitDisplayed(filtersButton);
        this.webDriver.elementClick(filtersButton);
        List<WebElement> listAppliedFilters = webDriver.findElements(filtersAppliedBy);
        this.webDriver.elementWaitDisplayed(listAppliedFilters.get(1));
        for (int cont = 0; cont < listAppliedFilters.size(); ) {
            this.webDriver.mouseMoveElement(listAppliedFilters.get(cont));
            assertTrue(listAppliedFilters.get(cont).isDisplayed());
            logger.info(listAppliedFilters.get(cont).getText());
            cont++;
        }
    }

    public void accessContentSearchResult(String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(
                this.commonPageAction.getFirstSearchContentStripeFirstSlickItem()));
        this.webDriver.elementMoveTo(slickItem.get(0),3);
        this.webDriver.elementPositionClick(slickItem.get(0), -0.4, -0.3);
        logger.info("First content title contains: " + text);
    }

}
