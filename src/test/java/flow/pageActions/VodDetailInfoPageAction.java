package flow.pageActions;

import flow.pageObjects.VodDetailInfoPageFlow;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class VodDetailInfoPageAction extends VodDetailInfoPageFlow {

    public VodDetailInfoPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void checkNoContentMessage() {
        assertTrue("No content message is not displayed", messageNoContent.isDisplayed());
        assertTrue("No content message is not correct",
                messageNoContent.getText().toLowerCase().contains("no está disponible"));
        logger.info("No content message is displayed");
    }

    public void checkNoContentMessageContentNoExist(String profileName) {
        String messageTitleContentNoExist = ".//div[contains(@class,'messageContent')]/h3[@class='title' and text()='" + profileName + "']";
        WebElement elementMessageTitleContentNoExist = webDriver.findElement(By.xpath(messageTitleContentNoExist));
        assertTrue("No content message is not displayed", messageNoContent.isDisplayed());
        assertTrue("No content title is not displayed", elementMessageTitleContentNoExist.isDisplayed());
        assertTrue("No content message is not correct",
                messageNoContent.getText().toLowerCase().contains("no está disponible"));
        logger.info("No content message is displayed");
    }

    public void rentedElementsAreDisplayed() {
        assertFalse("Element Play Button text don't contains 'Alquilar'",
                vodPrimaryButton.getText().toLowerCase().contains("alquilar"));
        logger.info("Element Play Button: " + vodPrimaryButton.getText());
    }

    public void pageIsDisplayedContentMovies(String price, Boolean rented) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(2));
        this.pageIsDisplayedContentAll();
        if (price.equals("0") || price.equals("0.0")) {
            assertTrue("Element Play Button text don't contains 'Reproducir'",
                    vodPrimaryButton.getText().toLowerCase().contains("reproducir") || vodPrimaryButton
                            .getText().toLowerCase().contains("continuar"));
            logger.info("Element Play Button: " + vodPrimaryButton.getText());

        } else if (rented) {
            assertTrue("Element Play Button text don't contains 'Reproducir'",
                    vodPrimaryButton.getText()
                            .toLowerCase().contains("reproducir")
                            || vodPrimaryButton.getText()
                            .toLowerCase().contains("continuar"));
            logger.info("Element Play Button: " + vodPrimaryButton.getText());

            assertTrue("Element Play Button text don't contains 'Disponible por'",
                    vodDueTime.getText().contains("Disponible por"));
        } else {
            assertTrue("Element Play Button text don't contains 'Alquilar'",
                    vodPrimaryButton.getText().toLowerCase().contains("alquilar"));
            logger.info("Element Play Button: " + vodPrimaryButton.getText());

            logger.info("Api content price: " + price + " DIP button: " +
                    vodRentPriceInfoButton.getText());
        }

        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            assertTrue("Element cast not displayed",
                    vodCast.isDisplayed());
            //assertTrue(webDriver.findElement(this.getVodCast()).isDisplayed()); //Not in all contents
            logger.info("Element cast is available");
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (Throwable throwable) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.error("Element Cast not displayed");
        }

        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            assertTrue("Element Director not displayed",
                    vodDirector.isDisplayed());
            logger.info("Element Director is displayed");
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (Throwable throwable) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.error("Element Director not displayed");
        }

        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            for (WebElement vodCastBadge : vodCastBadges) {
                wait.until(ExpectedConditions.elementToBeClickable(vodCastBadge));
                logger.info("Cast is clickable: " + vodCastBadge.getText());
            }
            logger.info("Element Cast is displayed");
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (Throwable throwable) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.error("Element Cast not displayed");
        }

        //Check stripe You might also like
        assertTrue("Element stripe title You might also like is not matched",
                vodContentStripeTitle.getText().contains("También podría gustarte"));
        logger.info("Element stripe title You might like is matched");
    }

    public void pageIsDisplayedContentAll() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        webDriver.waitUntil(20, wd -> vodTitle.isDisplayed());
        webDriver.waitUntil(20, wd -> vodImg.isDisplayed());
        webDriver.waitUntil(20, wd -> stripeRelatedContentTitle.isDisplayed());

        assertTrue("Element Title not displayed", vodTitle.isDisplayed());
        assertTrue("Element Cover Image not displayed", vodImg.isDisplayed());
        logger.info("Element Cover Image is displayed");
        assertTrue("Element Play Button not displayed",
                vodPrimaryButton.isDisplayed());
        logger.info("Element Play Button is displayed");
        assertTrue("Element My List Button not displayed",
                myListButton.isDisplayed());
        logger.info("Element Favorite Button is displayed");
        assertTrue("Element Duration not displayed",
                vodDuration.isDisplayed());
        logger.info("Element Duration is displayed");
        assertTrue("Element Age Badge not displayed",
                vodBadge.isDisplayed());
        logger.info("Element Age Badge is displayed");
        assertTrue("Element Description not displayed",
                vodDescription.isDisplayed());
        logger.info("Element Description is displayed");
        // Check recommended stripe
        assertTrue("Element Stripe not displayed", stripeRelatedContent.isDisplayed());
        logger.info("Recommended stripe is displayed");
        //this.webdriverUtils.elementMoveTo(stripeRecommended);
        assertTrue("First element of Stripe not displayed",
                slickItem.isDisplayed());
        logger.info("Recommended stripe is showing elements");
        logger.info("Elements are displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void pageIsDisplayedContentSeries() {
        this.pageIsDisplayedContentAll();
        assertTrue("Elements Episodes not displayed",
                vodEpisodesButton.isDisplayed());
        logger.info("Elements Episodes are displayed");
    }

    public void detailInfoPageIsDisplayed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        webDriver.waitUntil(20, wd -> vodTitle.isDisplayed());
        webDriver.waitUntil(20, wd -> vodImg.isDisplayed());
        webDriver.waitUntil(20, wd -> stripeRelatedContentTitle.isDisplayed());

        assertTrue("Element Title not displayed", vodTitle.isDisplayed());
        assertTrue("Element Cover Image not displayed", vodImg.isDisplayed());
        logger.info("Element Cover Image is displayed");
        assertTrue("Element Play Button not displayed",
                vodPrimaryButton.isDisplayed());
        logger.info("Element Play Button is displayed");
    }

    public void addFavoriteContent() {
        try {
            this.checkContentIsNotFavorite();
            this.webDriver.elementClick(vodFavoriteButton);
            webDriver.waitUntil(10, wd -> vodFavoriteButtonActive.isDisplayed());
            webDriver.waitUntil(10, wd -> vodFavoriteButton.isDisplayed());
            webDriver.sleep(1);
            logger.info("Content added to favorites");
        } catch (TimeoutException timeoutException) {
            logger.info("This content is already favorite");
        }
    }

    public void removeFavoriteContent() {
        this.webDriver.elementClick(vodFavoriteButton);
        webDriver.waitUntil(10, wd -> vodFavoriteButtonInactive.isDisplayed());
        webDriver.waitUntil(10, wd -> vodFavoriteButton.isDisplayed());
        webDriver.sleep(1);
        logger.info("Content removed from favorites");
    }

    public void removeFavoriteContentTvGuide() {
        this.webDriver.elementClick(vodFavoriteButtonActive);
        webDriver.waitUntil(10, wd -> vodFavoriteButtonInactive.isDisplayed());
        webDriver.sleep(1);
        logger.info("Content removed from favorites");
    }


    public void checkContentIsFavorite() {
        webDriver.waitUntil(10, wd -> vodFavoriteButtonActive.isDisplayed());
        assertTrue("Content is not favorite", vodFavoriteButtonActive.isDisplayed());
        logger.info("Content is favorite");
    }

    public void checkContentIsNotFavorite() {
        webDriver.waitUntil(10, wd -> vodFavoriteButtonInactive.isDisplayed());
        assertTrue("Content is still favorite", vodFavoriteButtonInactive.isDisplayed());
        logger.info("Content is not favorite");
    }

    public void modalIsDisplayed(String price) {
        // Check rent process
        webDriver.waitUntil(10, wd -> modalCancelButton.isDisplayed());
        //WebElement modal = this.webDriver.findElement(this.getModalContent());
        assertTrue("Element Modal Close not displayed",
                modalCloseLinkButton.isDisplayed());
        assertTrue("Element Modal Cancel Button not displayed",
                modalCancelButton.isDisplayed());
        assertTrue("Element Modal Confirm Button not displayed",
                modalConfirmButton.isDisplayed());
        logger.info("Modal is displayed");
    }

    public void clickPlayButton() {
        webDriver.elementClick(vodPlayButton);
        logger.info("Clicked Play content button");
    }

    public void clickOnPlayBtn() {
        vodPlayButton.click();
        logger.info("Clicked Play content button");
    }

    public void clickRentButton() {
        webDriver.elementClick(vodRentButton);
        logger.info("Clicked Rent content button");
    }

    public void clickModalCancelButton() {
        this.webDriver.elementClick(modalCancelButton);
        logger.info("Clicked Model Cancel button");
    }

    public void clickModalConfirmButton() {
        this.webDriver.elementClick(modalConfirmButton);
        logger.info("Clicked Modal Confirm button");
    }

    public boolean isRentConfirmButtonPresent() {
        try {
            this.webDriver.waitUntil(1, wd -> modalConfirmButton.isDisplayed());
            return true;
        } catch (NoSuchElementException | TimeoutException it) {
            return false;
        }
    }

    public void clickBackButton() {
        webDriver.elementClick(buttonBack);
        logger.info("Clicked Back button");
    }

    public void clickDirectorButton() throws AWTException {
        webDriver.pressKeyDown();
        webDriver.elementClick(vodDirectorName);
    }

    public String getDirectorName() {
        String directorName = vodDirectorName.getText();
        logger.info("Director name is " + directorName);
        return directorName;
    }

    public void clickActorButton(int actorItem) throws AWTException {
        this.webDriver.pressKeyDown();
        try {
            // Select Actor
            webDriver.elementClick(vodElencoName.get(actorItem));
            logger.info("Selected Cast name " + actorItem);
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            logger.info(String.valueOf(e));
        }
    }

    public String getCastName() {
        String castName = vodElencoName.get(0).getText();
        logger.info("Director name is " + castName);
        return castName;
    }

    public void setPinAndConfirmPurhasesBlocked() {
        webDriver.waitUntil(10, wd -> elementCancelButtonPurchasesBlocked.isDisplayed());
        webDriver.elementSendText(elementPinPurchasesBlocked, Hooks.props.pinParental());
        webDriver.elementClick(elementConfirmButtonPurchasesBlocked);
    }

    public void openVodUrl(String baseUrl, String type, String id) {
        this.webDriver.openVodUrl(baseUrl, type, id);
    }

    public void openVodUrlSkipIntro() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.skipIntro());
    }

    public void openVodUrlSkipResume() {
        var listOfSeriesWithSkipResume = new ArrayList<String>();
        listOfSeriesWithSkipResume.add("series/VODx25385519/episodio/ken-fry/40000518/play");
        var randomEpisode = UtilsRandom.getRandom(0, listOfSeriesWithSkipResume.size());
        this.webDriver.get(Hooks.props.baseUrl() + listOfSeriesWithSkipResume.get(randomEpisode));
    }

    public void openVodUrlSubtitles() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.vodSubtitles());
    }

    public void openVodUrlWithoutSubtitles() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.vodWithoutSubtitles());
    }

    public void openVodMovieUrl(String id) {
        this.webDriver.get(Hooks.props.baseUrl() + "peliculas/none/" + id + "/play");
        logger.info("URL -> " + Hooks.props.baseUrl() + "peliculas/none/" + id + "/play");
    }

    public void openVodSeriesUrl(String id, String vod, String title) {
        this.webDriver.get(Hooks.props.baseUrl() + "series/" + vod + "/episodio/" + title + "/" + id + "/play");
        logger.info("URL -> " + Hooks.props.baseUrl() + "series/" + vod + "/episodio/" + title + "/" + id + "/play");
    }

    public void openTvGuideChannel(String numberChannel) {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.tvGuidePath() + numberChannel);
    }

    public void openMovieFreeUrlWithSubtitles() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.movieSubtitles());
    }

    public void openMovieFreeUrlWithoutSubtitles() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.movieWithoutSubtitles());
    }

    public void openVodUrlWithOneMoreSeasons() {
        this.webDriver.get(Hooks.props.baseUrl() + Hooks.props.serieSeasons());
    }

    public void openVodUrlFavoriteContent() {
        var content = Hooks.dataProvider.getMovieFreeAllAgesInFavorite(Hooks.props.loginUsername(), Hooks.props.loginPassword());
        this.webDriver.openVodUrl(Hooks.props.baseUrl(),
                content.getType(),
                content.getId());
        Hooks.props.setProperty("titleContentInFavorite", content.getTitle());
    }

    public void openCatchUpUrl(String title, String id) {
        logger.info("URL: " + Hooks.props.baseUrl() + "catchup/" + title + "/" + id + "/play");
        this.webDriver.get(Hooks.props.baseUrl() + "catchup/" + title + "/" + id + "/play");
    }

    public void openVodUrlWithChineseAudio() {
        this.webDriver.openUrl(Hooks.props.baseUrl() + Hooks.props.movieWithChineseAudio());
    }

    public void openVodUrlWithAlemanAudio() {
        this.webDriver.openUrl(Hooks.props.baseUrl() + Hooks.props.serieWithGermanAudio());
    }

    public void openVodUrlWithFrenchAudio() {
        this.webDriver.openUrl(Hooks.props.baseUrl() + Hooks.props.movieWithFrenchAudio());
    }

    public void openVodUrlWithKoreanAudio() {
        this.webDriver.openUrl(Hooks.props.baseUrl() + Hooks.props.movieWithKoreanAudio());
    }

    public void openVodUrlWithItalianAudio() {
        this.webDriver.openUrl(Hooks.props.baseUrl() + Hooks.props.seriesWithItalianAudio());
    }

    public void clickPlayButtonVodDetailInfo() {
        this.webDriver.elementClick(playButtonVodDetailInfo);
    }

    public void clickEpisodesButton() {
        webDriver.elementClick(vodEpisodesButton);
    }

    public void selectLastSeason() {
        buttonSeasons.get(0).isDisplayed();
        webDriver.clickOn(buttonSeasons.get((int) buttonSeasons.stream().count() - 1));
    }

    public void selectFirstSeason() {
        buttonSeasons.get(0).isDisplayed();
        webDriver.clickOn(buttonSeasons.get(0));
    }

    public void selectLastEpisode() {
        episodes.get(0).isDisplayed();
        webDriver.clickOn(playButtonsEpisode.get((int) playButtonsEpisode.stream().count() - 1));
    }

    public void selectFirstEpisode() {
        episodes.get(0).isDisplayed();
        webDriver.clickOn(playButtonsEpisode.get(0));
    }

    public void checkPageElementsEpisodes() {
        webDriver.waitForElementVisible(pageEpidodesSeries);
    }

    public void clickOnStartPlayer() {
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.clickOn(vodPrimaryButton);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (NoSuchElementException noSuchElementException) {
            logger.info(noSuchElementException.getMessage());
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public void clickOnRestart() {
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.webDriver.clickOn(vodRestartButton);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        } catch (NoSuchElementException noSuchElementException) {
            logger.info(noSuchElementException.getMessage());
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public void selectRandomEpisode() {
        try {
            var randomEpisode = UtilsRandom.getRandom(1, playButtonEpisodesList.size() - (playButtonEpisodesList.size() > 2 ? 1 : 0));
            this.webDriver.elementClick(playButtonEpisodesList.get(randomEpisode));
        } catch (IllegalArgumentException ia) {
            this.webDriver.elementClick(playButtonEpisodesList.get(0));
        }
    }

    public void accessDipLive() {
        this.webDriver.scrollToELementPageDownAwaitility(stripeTvLive);
        Awaitility.await().atMost(Duration.ofSeconds(50)).until(() -> {
            try {
                List<WebElement> dipLive = stripeTvLive.findElements(itemBy);
                Random random = new Random();
                int randomValue = random.nextInt(dipLive.size());
                WebElement elementRandom = dipLive.get(randomValue);
                this.webDriver.mouseMoveElement(elementRandom);
                this.webDriver.elementPositionClick(elementRandom, -0.5, -0.3);
                return true;
            } catch (Throwable throwable) {
                return false;
            }

        });
    }

    public void getUrlLiveDip() {
        setUrl = this.webDriver.getCurrentUrl();
        this.webDriver.elementWaitDisplayed(elementDropDowUset);
        this.webDriver.elementClick(elementDropDowUset);
        this.webDriver.elementWaitDisplayed(elementLogout);
        this.webDriver.elementClick(elementLogout);
    }

    public void goToUrl() {
        this.webDriver.get(setUrl);
    }

    public void dipLiveAnotherAccountIsDisplayed() {
        this.webDriver.elementWaitDisplayed(title);
        String currentUrl = this.webDriver.getCurrentUrl();
        Assert.assertEquals("Urls don't match", setUrl, currentUrl);
    }

    public void channelDipIsDisplayed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        webDriver.waitUntil(10, wd -> vodTitle.isDisplayed());
        webDriver.waitUntil(10, wd -> vodImg.isDisplayed());

        assertTrue("Element 'Title' not displayed",
                vodTitle.isDisplayed());
        logger.info("Element 'Title' is displayed");

        assertTrue("Element 'Image' not displayed",
                vodImg.isDisplayed());
        logger.info("Element 'Image' is displayed");

        assertTrue("Element 'Play Button' not displayed",
                vodPrimaryButton.isDisplayed());
        logger.info("Element 'Play Button' is displayed");

        assertTrue("Element 'Duration' not displayed",
                vodDuration.isDisplayed());
        logger.info("Element 'Duration' is displayed");

        assertTrue("Element 'Age' not displayed",
                vodBadge.isDisplayed());
        logger.info("Element 'Age' is displayed");

        assertTrue("Element 'Description' not displayed",
                vodDescription.isDisplayed());
        logger.info("Element 'Description' is displayed");

    }

    public void clickOnTrailerButton() {
        this.webDriver.waitUntil(10, wd -> vodPlayTrailerButton.isDisplayed());
        this.webDriver.clickOn(vodPlayTrailerButton);
    }

    public void pageIsDisplayedContentPrimeVideo() {
        this.webDriver.waitForElementNotVisible(myListButton, 2);

        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        webDriver.waitUntil(10, wd -> vodTitle.isDisplayed());
        webDriver.waitUntil(10, wd -> vodCoverImg.isDisplayed());

        assertTrue("Element Title not displayed", vodTitle.isDisplayed());
        assertTrue("Element Cover Image not displayed", vodCoverImg.isDisplayed());
        logger.info("Element Cover Image is displayed");
        assertTrue("Element Play Button not displayed",
                vodPrimaryButton.isDisplayed());
        logger.info("Element Play Button is displayed");
        assertTrue("Element Duration not displayed",
                vodDuration.isDisplayed());
        logger.info("Element Duration is displayed");
        assertTrue("Element Age Badge not displayed",
                vodBadge.isDisplayed());
        logger.info("Element Age Badge is displayed");
        assertTrue("Element Description not displayed",
                vodDescription.isDisplayed());
        logger.info("Element Description is displayed");
    }

    public void selectShare() {
        this.webDriver.elementWaitDisplayed(buttonShare);
        this.webDriver.elementClick(buttonShare);
    }

    public void sharingOptionsIsDisplayed() {
        this.webDriver.elementWaitDisplayed(optionToShare);
        this.webDriver.elementMoveTo(optionToShare);
        logger.info("Options are displayed correctly " + optionToShare.getText());
    }

    public void recommendationsStripeIsDisplayed() {
        this.webDriver.elementWaitDisplayed(dipStripeRecommendations);
        assertTrue("Recommendation stripe is not visible",
                dipStripeRecommendations.isDisplayed());
        assertTrue("Stripe title is not visible",
                stripeRelatedContentTitle.isDisplayed());
        this.webDriver.scrollToElement(stripeRelatedContentTitle);
        List<WebElement> contents = dipStripeRecommendations.findElements(itemBy);
        for (WebElement slickActive : contents) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            assertTrue(slickActive.findElement(img).isDisplayed());
            this.webDriver.elementMoveTo(slickActive);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

    public void contentRentedIsEnabled() {
        this.pageIsDisplayedContentAll();
        assertTrue("Content is not enabled",
                vodRentPriceInfoButton.getText().contains("Disponible por"));
        logger.info("Rented content is enabled");
    }

    public void pageIsDisplayedContentAdult() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.waitUntil(20, wd -> stripeRelatedContentTitle.isDisplayed());

        assertTrue("Element Title not displayed", vodTitle.isDisplayed());
        assertTrue("Element Cover Image not displayed", vodCoverImg.isDisplayed());
        logger.info("Element Cover Image is displayed");
        assertTrue("Element Play Button not displayed",
                vodPrimaryButton.isDisplayed());
        logger.info("Element Play Button is displayed");
        logger.info("Element Favorite Button is displayed");
        assertTrue("Element Duration not displayed",
                vodDuration.isDisplayed());
        logger.info("Element Duration is displayed");
        assertTrue("Element Age Badge not displayed",
                vodBadge.isDisplayed());
        logger.info("Element Age Badge is displayed");
        assertTrue("Element Description not displayed",
                vodDescription.isDisplayed());
        logger.info("Element Description is displayed");
        // Check recommended stripe
        assertTrue("Element Stripe not displayed",stripeRelatedContent.isDisplayed());
        logger.info("Recommended stripe is displayed");

        assertTrue("First element of Stripe not displayed",
                slickItem.isDisplayed());
        logger.info("Recommended stripe is showing elements");
        logger.info("Elements are displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
