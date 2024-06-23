package flow.pageActions;

import flow.pageObjects.ContentPageFlow;
import flow.pageObjects.HomePageFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HomePageAction extends HomePageFlow {
    private PlayerPageAction playerPageAction;
    protected ContentPageFlow contentPageFlow;

    public HomePageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
        this.contentPageFlow = new ContentPageFlow(Hooks.getDriver());
    }

    public void pageIsDisplayed() {
        // Wait base elements load superHero Carrusel
        this.commonPageAction.superHeroCarruselIsDisplayed();
        // navbarContent
        this.commonPageAction.navbarContentsIsDisplayed(
                true,
                true,
                true,
                true,
                true,
                !Hooks.props.product().equals("dibox"),
                true);
        assertTrue("Element Search not displayed",
                this.commonPageAction.buttonSearchWrapperIsDisplayed());
        assertTrue("Element Dropdown not displayed", this.commonPageAction.dropdownUserIsDisplayed());
        logger.info("Elements are displayed");
    }

    public boolean checkOnboardingStar() {
        webDriver.waitUntil(5, wd -> onboardingModalContent.isDisplayed());
        webDriver.isElementDisplayed(onboardingStarCloseButton);
        return webDriver.areElementsDisplayed(onboardingStarCloseButton, onboardingStarImgStar, onboardingStarTitle,
                onboardingStarDescription, onboardingStarButtonGo);
    }

    public void checkBannerOnboardingStar() {
        webDriver.isElementDisplayed(onboardingModalContent);
        logger.info("Element is displayed");
    }

    public void clickNavbarHome() {
        this.webDriver.elementClick(navbarHome);
    }

    public void clickTopLeftNavbarHome() {
        webDriver.elementClickTopLeftAction(navbarHome);
    }

    public void clickButtonCloseModalStartPlus() {
        webDriver.waitUntil(3, wd -> buttonCloseModalStarPlus.isDisplayed());
        webDriver.clickOn(buttonCloseModalStarPlus);
    }

    public void viewStripeDestacadosFlow() {
        this.webDriver.elementWaitDisplayed(getSuperHero);
        this.webDriver.searchPageDownForElement(stripe);
        this.webDriver.scrollToElement(stripe);
    }

    public void checkStripeDestacadosFlow() {
        destacadosFlow = this.webDriver.findElements(stripeBy);
        for (WebElement contenidoDestacado : destacadosFlow) {
            slicksActive = contenidoDestacado.findElements(getSlickActive);
            for (WebElement slickActive : slicksActive) {
                this.webDriver.elementWaitToBeClickable(slickActive);
                this.webDriver.elementMoveTo(slickActive);
                String slickTitle = slickActive.findElement(getSlickText).getAttribute("innerHTML");
                logger.info(slickTitle);
            }
        }
    }

    public void accessToStripeDestacadosFlow() {
        this.webDriver.elementWaitDisplayed(getSuperHero);
        this.webDriver.searchPageDownForElement(stripe);
        this.webDriver.scrollToElement(stripe);
        stripe = this.webDriver.findElement(stripeBy);
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                contenidoAleatorio = stripe.findElements(getSlickActive);
                Random random = new Random();
                int randomValue = random.nextInt(contenidoAleatorio.size());
                WebElement elementRandom = contenidoAleatorio.get(randomValue);
                this.webDriver.elementPositionClick(elementRandom, -0.7, -0.5);
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public void selectTheMostWatchingLiveTv() {
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));
                theMostTVWatchingInFlow.isDisplayed();
                this.webDriver.scrollPageToElement(theMostTVWatchingInFlow);
                this.webDriver.clickOn(theMostTVWatchingInFlow);
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

    public void checkDipContent() {
        assertTrue("Element 'tituloContenido' is not displayed", tituloContenido.isDisplayed());
        logger.info("Element 'tituloContenido' is displayed");

        assertTrue("Element 'getRowDescription' is not displayed", getDescription.isDisplayed());
        logger.info("Element 'getRowDescription' is displayed");

        assertTrue("Element 'genero' is not displayed", genero.isDisplayed());
        logger.info("Element 'genero' is displayed");

        assertTrue("Element 'tipoDeGenero' is not displayed", tipoDeGenero.isDisplayed());
        logger.info("Element 'tipoDeGenero' is displayed");
    }

    public void viewToStripeCategorias() {
        this.webDriver.elementWaitDisplayed(getSuperHero);

        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                miraEnFlow.isDisplayed();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                this.webDriver.scrollPageToElement(miraEnFlow);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });

    }

    public void checkStripeCategorias() {
        stripeCategorias = this.webDriver.findElements(getStripeMiraEnFlow);
        for (WebElement categorias : stripeCategorias) {
            contenidoStripe = categorias.findElements(getSlickActive);
            for (WebElement contenidoCategoria : contenidoStripe) {
                this.webDriver.elementWaitToBeClickable(contenidoCategoria);
                this.webDriver.elementMoveTo(contenidoCategoria);
                String slickTitle = contenidoCategoria.findElement(getImgText)
                        .getAttribute("alt");
                logger.info(slickTitle);
            }
        }
    }

    public void accessToStripeMiraEnFlow() {
        this.webDriver.elementWaitDisplayed(getStripeMiraEnFlow);
        categorias = this.webDriver.findElement(getStripeMiraEnFlow);
        String titleStripe = categorias.findElement(getStripeTitle).getText();
        logger.info("Stripe: " + titleStripe);
        selectedCategory = categorias.findElement(getSlickSilde);
        this.webDriver.elementMoveTo(selectedCategory, 5);
        this.webDriver.elementClick(selectedCategory);

    }

    public void checkContentStripeMiraEnFlow() {
        var stripesBoxSize = this.webDriver.findElements(getStripeBox).size();
        assertTrue(stripesBoxSize >= 1);
        logger.info("Stripes found: " + stripesBoxSize);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        var slickActivesSize = this.webDriver.findElements(getSlickActive).size();
        assertTrue(slickActivesSize >= 1);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Slick actives: " + slickActivesSize);
    }

    public void accessRandomStripeContent() {
        this.webDriver.elementWaitDisplayed(getSuperHero);
        this.webDriver.scrollToELementPageDownAwaitility(stripeRecommended);
        contentRandom = stripeRecommended.findElements(getSlickActive);
        Random random = new Random();
        int randomValue = random.nextInt(contentRandom.size());
        WebElement elementRandom = contentRandom.get(randomValue);
        this.webDriver.mouseMoveElement(elementRandom);
        this.webDriver.elementPositionClick(elementRandom, -0.7, -0.5);
    }

    public void accessRandomContent(WebElement stripe) {
        this.webDriver.scrollToElement(stripe);
        contentRandom = stripe.findElements(getSlickActive);
        Random random = new Random();
        int randomValue = random.nextInt(contentRandom.size());
        WebElement elementRandom = contentRandom.get(randomValue);
        this.webDriver.elementMoveTo(elementRandom, 2);
        WebElement accessToElement = elementRandom.findElement(getButtonPlay);
        this.webDriver.elementClick(accessToElement);
    }

    public void accessRandomContentStripeBingeWatching(WebElement stripe) {
        this.webDriver.scrollToElement(stripe);
        contentRandom = stripe.findElements(getSlickActive);
        Random random = new Random();
        int randomValue = random.nextInt(contentRandom.size());
        WebElement elementRandom = contentRandom.get(randomValue);
        this.webDriver.elementMoveTo(elementRandom, 2);
        WebElement accessToElement = elementRandom.findElement(getButtonPlay);
        this.webDriver.elementClick(accessToElement);
        try {
            this.webDriver.elementClick(this.contentPageFlow.buttonPlayDip);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void viewToStripe(WebElement stripe) {
        this.webDriver.elementWaitDisplayed(getSuperHero);
        Awaitility.await().atMost(Duration.ofSeconds(80)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(2));
                stripe.isDisplayed();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                this.webDriver.scrollPageToElement(stripe);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return false;
            }
        });
    }

    public void accessContentStripe(WebElement stripe) {
        this.webDriver.scrollToElement(stripe);
        contentRandom = stripe.findElements(getSlickActive);
        Random random = new Random();
        int randomValue = random.nextInt(contentRandom.size());
        WebElement elementRandom = contentRandom.get(randomValue);
        this.webDriver.elementMoveTo(elementRandom, 2);
        this.webDriver.elementPositionClick(elementRandom, -0.4, -0.2);
    }

    public void checkContentStripe() {
        var stripesBoxSize = this.webDriver.findElements(getStripeBox).size();
        assertTrue(stripesBoxSize > 0);
        logger.info("Stripes found: " + stripesBoxSize);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        var slickActivesSize = this.webDriver.findElements(getSlickActive).size();
        assertTrue(slickActivesSize >= 1);
        logger.info("Slick actives: " + slickActivesSize);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        List<WebElement> contents = this.webDriver.findElements(getSlickActive);
        for (WebElement content : contents) {
            this.webDriver.elementWaitDisplayed(content);
            this.webDriver.elementMoveToWithWait(content, 2);
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void waitForStripes() {
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfAllElements(stripesHome.get(0)));
                wait.until(ExpectedConditions.visibilityOfAllElements(itemImg.get(1)));
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                this.webDriver.scrollPageDown();
                return false;
            }
        });
    }

    public void postersAreShownCorrectly(WebElement stripe, By element) {
        List<WebElement> contentTop = stripe.findElements(element);
        for (WebElement poster : contentTop) {
            this.webDriver.elementWaitToBeClickable(poster);
            this.webDriver.elementMoveTo(poster);
            Awaitility.await().atMost(Duration.ofSeconds(8)).until(() -> {
                try {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
                    String slickTitle = poster.findElement(getImgText)
                            .getAttribute("alt");
                    logger.info(slickTitle);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(20));
                    return true;
                } catch (Throwable throwable) {
                    this.webDriver.scrollPageDown();
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(20));
                    return false;
                }
            });
        }
    }

    public void checkPosterStripeMisPeliculasYSeries() {
        Awaitility.await().atMost(15, SECONDS).until(() -> {
            try {
                List<WebElement> contentTop = stripeMyMoviesAndSeries.findElements(getSlickActive);
                for (WebElement poster : contentTop) {
                    this.webDriver.elementWaitToBeClickable(poster);
                    this.webDriver.elementMoveTo(poster, 10);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
                    String slickTitle = poster.findElement(getImgText)
                            .getAttribute("alt");
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    logger.info(slickTitle);
                }
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollToElement(stripeMyMoviesAndSeries);
                return false;
            }
        });
    }

    public void accessStripeMisPeliculasYSeries() {
        Awaitility.await().atMost(10, SECONDS).until(() -> {
            try {
                WebElement seeMore = stripeMyMoviesAndSeries.findElement(seeMoreBy);
                this.webDriver.elementMoveTo(seeMore, 3);
                this.webDriver.elementClick(seeMore);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                return false;
            }
        });
    }

    public void accessRandomContentStripeTodosLosTitulos() {
        Awaitility.await().atMost(10, SECONDS).until(() -> {
            try {
                accessContentStripe(stripeAllTitles);

                return true;
            } catch (Throwable throwable) {
                this.commonPageAction.scrollDownVertical();
                return false;
            }
        });
    }

    public void navegationForStripe(WebElement stripe) {
        this.webDriver.scrollToElement(stripe);
        List<WebElement> slick = stripe.findElements(getSlickActive);
        this.webDriver.elementMoveTo(slick.get(0), 5);
        this.webDriver.elementMoveTo(stripe.findElement(slickNext), 3);
        this.webDriver.elementClick(stripe.findElement(slickNext));
        this.webDriver.sleep(2);
        logger.info("Content stripe scrolled right");
        this.webDriver.elementMoveTo(stripe.findElement(slickPrev), 2);
        this.webDriver.elementClick(stripe.findElement(slickPrev));
        webDriver.sleep(2);
        logger.info("Content stripe scrolled left");
    }

    public void accessContentStripeCollections(WebElement stripe) {
        this.webDriver.scrollToElement(stripe);
        contentRandom = stripe.findElements(getSlickCollections);
        Random random = new Random();
        int randomValue = random.nextInt(contentRandom.size());
        if (randomValue > 4) {
            Awaitility.await().atMost(30, SECONDS).until(() -> {
                try {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

                    WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofMillis(500));
                    wait.until(ExpectedConditions.elementToBeClickable(contentRandom.get(3)));

                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    return true;
                } catch (Throwable throwable) {
                    stripe.findElement(slickNext).click();
                    return false;
                }
            });
        }
        this.webDriver.elementMoveTo(contentRandom.get(randomValue), 2);
        this.webDriver.elementPositionClick(contentRandom.get(randomValue), -0.4, -0.2);
    }

    public void goToStripeTopSeries() {
        Awaitility.await().atMost(10, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(50));
                WebElement contentStripe = stripeTopSeries.findElement(getSlickSilde);
                contentStripe.isDisplayed();
                this.webDriver.elementMoveTo(contentStripe, 3);
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.scrollPageDown();
                return false;
            }
        });
    }

    public void checkContentAddedMyList(String titleContent) {
        this.webDriver.scrollToElement(stripeMyMoviesAndSeries);
        webDriver.sleep(1);
        this.webDriver.elementMoveTo(stripeMyMoviesAndSeries.findElement(getSlickSilde), 3);
        assertEquals(titleContent, stripeMyMoviesAndSeries.findElement(getSlickSilde).findElement(getSlickText).getText());
    }
}
