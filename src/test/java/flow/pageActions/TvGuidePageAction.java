package flow.pageActions;

import flow.pageObjects.TvGuidePageFlow;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsRandom;
import flow.webdriverUtils.ExtendedWebDriver;
import org.awaitility.Awaitility;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class TvGuidePageAction extends TvGuidePageFlow {

    public TvGuidePageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed() {
        assertTrue("Button back to played not displayed",
                btnBackToPlayer.isDisplayed());
        assertTrue("Epg filters not displayed",
                this.webDriver.findElement(epgFilters).isDisplayed());
        assertTrue("Button search wrapper not displayed",
                this.commonPageAction.buttonSearchWrapperIsDisplayed());
        logger.info("Elements are displayed");
        // wait for player playback
        //this.playerPageAction.waitPlayerLoad();
        logger.info("Player is loaded");
        // wait for epg grid contents
        assertTrue(wrapperEpgChannelChannelPrograms.isDisplayed());
        assertTrue(wrapperEpgChannelEpgProgramLive.get(0).isDisplayed());
        logger.info("Epg grid contents Displayed");
    }

    public void waitEpgProgramLiveLoad() {
        webDriver.waitUntil(10, wd -> wrapperEpgChannelEpgProgramLive.get(0).isDisplayed());
        logger.info("Epg Live Program displayed");
    }

    public void getBtnDipEpgChannelPlay() {
        this.webDriver.mouseMoveElement(programsPastEPG.get(0));
        this.webDriver.clickOn(programsPastEPG.get(0));
        this.webDriver.waitUntil(10, wd -> btnDipEpgChannelEpgProgramPlay.isDisplayed());
        this.webDriver.clickOn(btnDipEpgChannelEpgProgramPlay);
        logger.info("Epg Live Program play");
    }

    public void checkEpgElements() {
        assertTrue("Active channel is not displayed", channelHeaderActive.isDisplayed());
        logger.info("Active channel number: " + epgChannelHeaderActive.findElement(this.getByWrapperEpgChannelNumber()).getText());

        assertFalse("Active Channel name is empty", epgChannelHeaderActive.findElement(this.getByWrapperEpgChannelImg()).getAttribute("alt").isEmpty());
        logger.info("Active channel name: " + epgChannelHeaderActive.findElement(this.getByWrapperEpgChannelImg()).getAttribute("alt"));

        assertTrue("Favorite icon button not displayed", btnEpgChannelFavoriteIcon.isDisplayed());
        logger.info("Favorite icon button is displayed");

        assertTrue("Epg Channel Live not present", epgProgramVerticalTruncateTitleLive.size() > 0);

        logger.info("Epg elements are displayed");
    }

    public void selectLiveChannel() {
        epgProgramVerticalAlignLive.click();
    }

    public void restartEpgChannel() {
        btnEpgChannelRestart.click();
    }

    public void checkEpgRestrictedElements() {
        webDriver.waitUntil(10, wd -> wrapperEpgChannelEpgProgramRestricted.size() > 0);

        assertTrue("Restricted Content not present",
                wrapperEpgChannelEpgProgramRestricted.size() > 0);
        logger.info("Restricted Content is present");
    }

    public void accessRandomRestrictedProgramFromEpg() {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
                List<WebElement> restrictedPrograms = epg.findElements(this.getWrapperEpgChannelEpgProgramRestrictedLive());
                int randomProgram = UtilsRandom.getRandom(0, restrictedPrograms.size() - 1);
                wait.until(ExpectedConditions.elementToBeClickable(restrictedPrograms.get(randomProgram)));
                this.webDriver.elementClick(restrictedPrograms.get(randomProgram));
                assertTrue("Parental control modal is not displayed",
                        this.commonPageAction.parentalControlModalElementIsDisplayed());
                logger.info("restricted Program: " + randomProgram);
                return true;
            } catch (Throwable throwable) {
                if (this.commonPageAction.parentalControlModalElementIsDisplayed()) {
                    this.commonPageAction.clickParentalControlModalCloseButton();
                }
                goPageDownEpg(1);
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void clickOnFirstProgramPastActive() {
        this.webDriver.sleep(2);
        List<WebElement> programs = this.webDriver.findElements(By.cssSelector("div.channelPrograms > div.epgProgram.vertical-align.past"));
        this.webDriver.mouseOver(programs.get(0));
        this.webDriver.elementClick(programs.get(0));
    }

    public void getFirstEpgProgram() {
        try {
            goPageDownEpg(0);
            this.getBtnDipEpgChannelPlay();
            logger.info("First program is selected");
        } catch (Throwable throwable) {
            goPageDownEpg(1);
            this.getBtnDipEpgChannelPlay();
            logger.info("Retry First program is selected");
            throwable.printStackTrace();
        }
    }

    public void getProgram() {
        this.webDriver.waitUntil(20, wd -> btnDipEpgChannelEpgProgramPlay.isDisplayed());
        this.webDriver.clickOn(btnDipEpgChannelEpgProgramPlay);
        logger.info("Epg Live Program play");
    }

    public void getRandomEpgProgram() {
        try {
            var random = UtilsRandom.getRandom(1, 4);
            goPageDownEpg(random);
            this.getBtnDipEpgChannelPlay();
            logger.info("First program is selected");
        } catch (Throwable throwable) {
            goPageDownEpg(1);
            this.getBtnDipEpgChannelPlay();
            logger.info("Retry First program is selected");
            throwable.printStackTrace();
        }
    }

    public void checkEpgFiltersAmount() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(epg.findElement(this.getByEpgHeader())));
        List<WebElement> epgFilterItems = epg.findElement(this.getByEpgHeader())
                .findElement(this.getByEpgFilters())
                .findElements(this.getByEpgFilterItem());
        // assert filters displayed
        assertTrue("Item Filter is not displayed", epgFilterItems.size() > 0);
        logger.info("EPG filters displayed amount: " + epgFilterItems.size());
        // assert dropdown filter elements
        WebElement epgFilterDropdown = epg.findElement(this.getByEpgHeader())
                .findElement(this.getByEpgFilters())
                .findElement(this.getByEpgFilterDropdown());
        // open filters dropdown
        this.webDriver.elementClick(epgFilterDropdown);
        wait.until(ExpectedConditions.visibilityOfAllElements(epgFilterDropdown
                .findElements(this.getByEpgFilterDropdownItem())));
        // assert filters dropdown items displayed
        List<WebElement> epgFilterDropdownItems = epgFilterDropdown
                .findElements(this.getByEpgFilterDropdownItem());
        assertTrue("Item Subfilter is not displayed", epgFilterDropdownItems.size() > 0);
        this.webDriver.elementClick(epgFilterDropdown);
        logger.info("EPG filters dropdown amount: " + epgFilterDropdownItems.size());
    }

    public void filterChannelsEpg(String filter, String subFilter) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(epg.findElement(this.getByEpgHeader())));
        List<WebElement> epgFilterItems = epg
                .findElement(this.getByEpgHeader())
                .findElement(this.getByEpgFilters())
                .findElements(this.getByEpgFilterItem());
        for (WebElement epgFilterItem : epgFilterItems) {
            if (epgFilterItem.getText().equals(filter)) {
                this.webDriver.elementClick(epgFilterItem);
                int numberChannelSize = this.checkNumberChannelsWithFilter();
                assertTrue("No channel displayed for filter", numberChannelSize > 0);
                logger.info("Channels amount displayed with filter " + filter + " : " + numberChannelSize);
                break;
            }
        }
        if (filter.equals("Otros")) {
            WebElement epgFilterDropdown = epg.findElement(this.getByEpgHeader())
                    .findElement(this.getByEpgFilters())
                    .findElement(this.getByEpgFilterDropdown());
            // open filters dropdown
            this.webDriver.elementClick(epgFilterDropdown);
            wait.until(ExpectedConditions.visibilityOfAllElements(epgFilterDropdown
                    .findElements(this.getByEpgFilterDropdownItem())));
            // assert filters dropdown items displayed
            List<WebElement> epgFilterDropdownItems = epgFilterDropdown
                    .findElements(this.getByEpgFilterDropdownItem());
            for (WebElement epgFilterDropdownItem : epgFilterDropdownItems) {
                if (subFilter.equals(epgFilterDropdownItem.getAttribute("innerHTML"))) {
                    this.webDriver.elementClick(epgFilterDropdownItem);
                    int numberChannelSize = this.checkNumberChannelsWithFilter();
                    assertTrue("No channel displayed for filter", numberChannelSize > 0);
                    logger.info("Channels amount displayed with filter "
                            + subFilter + " : " + numberChannelSize);
                    break;
                }
            }
        }
    }

    public void filterChannelsEpgDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(epg.findElement(this.getByEpgHeader())));
        List<WebElement> epgFilterItems = epg
                .findElement(this.getByEpgHeader())
                .findElement(this.getByEpgFilters())
                .findElements(this.getByEpgFilterItem());
        // assert filters displayed
        for (WebElement epgFilterItem : epgFilterItems) {
            logger.info("Epg Filter: " + epgFilterItem.getText());
        }
        // assert dropdown filter elements
        WebElement epgFilterDropdown = epg.findElement(this.getByEpgHeader())
                .findElement(this.getByEpgFilters())
                .findElement(this.getByEpgFilterDropdown());
        // open filters dropdown
        this.webDriver.elementClick(epgFilterDropdown);
        wait.until(ExpectedConditions.visibilityOfAllElements(epgFilterDropdown
                .findElements(this.getByEpgFilterDropdownItem())));
        // assert filters dropdown items displayed
        List<WebElement> epgFilterDropdownItems = epgFilterDropdown
                .findElements(this.getByEpgFilterDropdownItem());
        for (WebElement epgFilterDropdownItem : epgFilterDropdownItems) {
            logger.info("Epg Subfilter: " + epgFilterDropdownItem.getText());
        }
        this.webDriver.elementClick(epgFilterDropdown);
    }

    public int checkNumberChannelsWithFilter() {
        int numberChannelSize = 0;
        List<WebElement> channelNumbers = webDriver.findElements(this.getByEpgGrid());
        for (WebElement channelNumber : channelNumbers) {
            List<WebElement> numbers = channelNumber.findElements(
                    this.getByWrapperEpgChannelNumber());

            assertTrue("Channels not available with filter apply"
                    , numbers.size() > 0);
            numberChannelSize = numbers.size();
        }
        return numberChannelSize;
    }

    public void navigateEpgGridScheduleNext() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // navigate using Next and Prev buttons
        this.webDriver.elementClick(btnEpgControlsNextButton);
        wait.until(ExpectedConditions.elementToBeClickable(btnEpgControlsBackToLiveButtonClickable));
        logger.info("Epg grid schedule scrolled to right");
    }

    public void navigateEpgGridSchedulePrev() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // navigate using Next and Prev buttons
        this.webDriver.elementClick(btnEpgControlsPrevButton);
        wait.until(ExpectedConditions.elementToBeClickable(btnEpgControlsNextButton));
        logger.info("Epg grid schedule scrolled to left");
    }

    public void navigateEpgGridScheduleBack() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // navigate using Next and BackToLiveButton buttons
        this.webDriver.elementClick(btnEpgControlsBackToLiveButtonClickable);
        wait.until(ExpectedConditions.elementToBeClickable(btnEpgControlsNextButton));
        logger.info("Epg grid schedule scrolled to now");
    }

    public void selectYesterdayDayEpgProgram() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        //to click on  day selector dropdown elements
        wait.until(ExpectedConditions.elementToBeClickable(dropdownEpgDaySelector));
        webDriver.elementClick(dropdownEpgDaySelector);
        //Yesterday program is selected
        wait.until(ExpectedConditions.elementToBeClickable(yesterdayEpgDaySelectorItem));
        webDriver.elementClick(yesterdayEpgDaySelectorItem);
    }

    public void refreshUrl() {
        this.webDriver.refreshUrl();
    }

    public void clickOnArrowLeftButton() {
        this.webDriver.waitUntil(10, wd -> arrowLeftButton.isDisplayed());
        this.webDriver.clickOn(arrowLeftButton);
        this.webDriver.clickOn(arrowLeftButton);
        this.webDriver.clickOn(arrowLeftButton);
    }

    public void selectTomorrowDayEpgProgram() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        //to click on  day selector dropdown elements
        wait.until(ExpectedConditions.elementToBeClickable(dropdownEpgDaySelector));
        webDriver.elementClick(dropdownEpgDaySelector);
        //Tomorrow program is selected
        wait.until(ExpectedConditions.elementToBeClickable(tomorrowEpgDaySelectorItem));
        webDriver.elementClick(tomorrowEpgDaySelectorItem);
    }

    public void checkEpgGridDaySelectorDropdown() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // get day selector dropdown elements
        logger.info("Day selector dropdown items: " + dropdownEpgDaySelectorItem.size());
        // open every element in day selector dropdown
        for (int i = 0; i < dropdownEpgDaySelectorItem.size(); i++) {
            wait.until(ExpectedConditions.elementToBeClickable(dropdownEpgDaySelector));
            // open dropdown before clicking dropdown item
            webDriver.elementClick(dropdownEpgDaySelector);
            // get item from dropdown
            WebElement item = dropdownEpgDaySelectorItem.get(i);
            logger.info("Navigating day dropdown selector: "
                    + item.getAttribute("innerHTML"));
            // assert dropdown element text
            switch (i) {
                case 0:
                    assertEquals("Ayer", item.getAttribute("innerHTML"));
                    break;
                case 1:
                    assertEquals("Hoy", item.getAttribute("innerHTML"));
                    break;
                case 2:
                    assertEquals("Mañana", item.getAttribute("innerHTML"));
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    assertTrue(item.getAttribute("innerHTML").contains("/"));
                    break;
            }
            // open date dropdown item
            item.click();
        }
    }

    public void openMiniEpg() {
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(2));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

                // make miniEpgButton visible by moving the mouse cursor
                this.webDriver.moveToElementUntilShowed(playerContainer);
                webDriver.waitUntil(2, wd -> playerContainer.isDisplayed());
                webDriver.elementMoveTo(playerContainer);
                wait.until(ExpectedConditions.visibilityOf(playerContainer));

                // open mini EPG
                wait.until(ExpectedConditions.elementToBeClickable(btnMiniEPGButton));
                webDriver.elementClick(btnMiniEPGButton);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkMiniEpgElements() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));

        // Get first channel main elements
        WebElement miniEpgChannel = miniEpgChannelWrapper.get(0);
        WebElement miniEpgChannelCurrentProgram = miniEpgChannel.findElement(
                this.getMiniEpgChannelCurrentProgram());
        WebElement miniEpgChannelNextProgram = miniEpgChannel.findElement(
                this.getMiniEpgChannelNextProgram());

        wait.until(ExpectedConditions.visibilityOf(miniEpgChannel.findElement(
                this.getMiniEpgChannelNumber())));

        // Assert channel number
        assertTrue("Mini Epg channel number is not displayed", miniEpgChannel.findElement(
                this.getMiniEpgChannelNumber()).isDisplayed());

        // Assert channel name
        assertTrue("Mini Epg channel image is not displayed", miniEpgChannel.findElement(
                this.getMiniEpgChannelImgName()).isDisplayed());
        logger.info("Mini Epg Channel name: "
                + this.webDriver.getAttributeOfGivenElement(miniEpgChannel.findElement(
                this.getMiniEpgChannelImgName()), "alt"));

        // Assert channel current program name
        assertTrue("Mini Epg channel current program title is not displayed",
                miniEpgChannelCurrentProgram.findElement(
                        this.getMiniEpgChannelCurrentProgramTitle()).isDisplayed());

        // Assert channel current program tags
        assertTrue("Mini Epg channel current program tags not displayed",
                miniEpgChannelCurrentProgram.findElement(
                        this.getMiniEpgChannelCurrentProgramTags()).isDisplayed());

        // Assert channel current program progress
        assertTrue("Mini Epg channel current program progress bar not displayed",
                miniEpgChannelCurrentProgram.findElement(
                        this.getMiniEpgChannelCurrentProgramProgress()).isDisplayed());

        // Assert channel next program text
        assertTrue("Mini Epg channel next program text is not displayed",
                miniEpgChannelNextProgram.findElement(
                        this.getMiniEpgChannelNextProgramText()).isDisplayed());

        // Assert channel next program title
        assertTrue("Mini Epg channel next program title is not displayed", this.webDriver
                .findElements(this.getMiniEpgChannelNextProgramTitle()).size() > 0);

        // Assert mini epg close button
        assertTrue("Mini Epg close button is not displayed", btnCloseMiniEPG.isDisplayed());

        logger.info("Mini Epg Elements are displayed");
    }

    public void closeMiniEpg() {
        Awaitility.await().atMost(Duration.ofSeconds(5)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
                this.webDriver.elementClick(btnCloseMiniEPG);
                wait.until(ExpectedConditions.invisibilityOfAllElements(wrapperEpgChannelWrapper.get(0)));
                logger.info("Mini EPG is closed");

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void openEpg() {
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
                // make Show Epg Button visible by moving the mouse cursor
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                // open EPG
                webDriver.mouseOver(btnVopLeftControls);
                webDriver.elementClick(btnVopLeftControls);
                // wait for close EPG button
                wait.until(ExpectedConditions.elementToBeClickable(btnBackToPlayer));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                logger.info("EPG is displayed");
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void openEpgWithNavbarTvGuide() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        this.commonPageAction.clickNavbarContentTvGuide();
        wait.until(ExpectedConditions.elementToBeClickable(btnBackToPlayer));
        logger.info("EPG is displayed");
    }

    public void closeEpgWithBackButton() {
        try {
            WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(btnBackToPlayer));
            webDriver.elementClick(btnBackToPlayer);
            logger.info("EPG is closed with back button");
        } catch (ElementClickInterceptedException elementClickInterceptedException) {
            logger.info("Element Click Intercepted Exception");
            this.commonPageAction.clickTourCloseButton();
            WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(btnBackToPlayer));
            webDriver.elementClick(btnBackToPlayer);
            logger.info("EPG is closed with back button");
        }
    }

    public void clickOnWatchTvGuide() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(backToTvGuide);
    }

    public void closeEpgWithEscKey() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnHideGuide));
        this.webDriver.pressKeyEsc();
        logger.info("EPG is closed with ESC key");
    }

    public void CloseEpgWithBackToPlayer() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnHideGuide));
        this.webDriver.elementClick(btnHideGuide);
        logger.info("Back to player");
    }

    public int getRandomChannelFromMiniEpg() {
        // Get only from the first 5 channels
        int selectedChannel = UtilsRandom.getRandomThreadLocal(1, 5);
        logger.info("Selected random channel from list: " + selectedChannel);
        return selectedChannel;
    }

    public WebElement getActiveChannelHeader() {
        // Get active channel element and text
        WebElement activeChannelHeader = channelHeaderActive;
        String activeChannelHeaderNumberText = activeChannelHeader.findElement(
                this.getByWrapperEpgChannelNumber()).getText();
        logger.info("Current active channel number: " + activeChannelHeaderNumberText);
        return activeChannelHeader;
    }

    public WebElement getActiveChannelWrapper() {
        // Get active channel element and text
        WebElement activeChannelWrapper = channelWrapperActive;
        String activeChannelHeaderNumberText = activeChannelWrapper.findElement(
                this.getByWrapperEpgChannelNumber()).getText();
        logger.info("Current active channel number: " + activeChannelHeaderNumberText);
        return activeChannelWrapper;
    }

    public void selectChannelFromMiniEpg(int channel) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Get selected channel elements
        List<WebElement> channelList = wrapperMiniEpgChannelWrapper;
        WebElement selectedChannel = channelList.get(channel);
        WebElement selectedChannelNumber = selectedChannel.findElement(this.getByWrapperMiniEpgChannelNumber());
        WebElement selectedChannelImg = selectedChannel.findElement(this.getByWrapperMiniEpgChannelImg());
        // Click selected channel
        wait.until(ExpectedConditions.elementToBeClickable(selectedChannelImg));
        logger.info("Channel changed to (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel changed to (name): " + selectedChannelImg.getAttribute("alt"));
        selectedChannelImg.click();
    }

    public WebElement getRandomChannelFromGrid() {
        // Get channel list
        List<WebElement> channelList = wrapperEpgChannelWrapper;
        int channelAmount = channelList.size();
        // Get only from the first 5 channels
        int selectedChannel = UtilsRandom.getRandomThreadLocal(1, channelAmount - 1);
        logger.info("Selected random channel from list: " + selectedChannel);
        return channelList.get(selectedChannel);
    }

    public String getFirstChannelNumberTextFromGrid() {
        // Get channel list
        List<WebElement> channelElementList = epg
                .findElements(this.getByWrapperEpgChannelNumber());

        // Get only from the first 5 channels
        logger.info("Selected first item from list channel number text: " + channelElementList.get(0).getText());
        return channelElementList.get(0).getText();
    }

    public void moveToActiveChannelElementFromGrid() {
        // Get active channel
        WebElement activeChannelHeader = channelHeaderActive;
        String activeChannelHeaderNumberText = activeChannelHeader.findElement(
                this.getByWrapperEpgChannelNumber()).getText();

        // Move to active element
        this.webDriver.elementMoveTo(activeChannelHeader, 10);

        logger.info("Moved to active channel: " + activeChannelHeaderNumberText);
    }

    public String getRandomChannelNumberFromGrid() {
        // Get channel list
        List<WebElement> channelElementList = epg.findElements(this.getByWrapperEpgChannelNumber());

        // Get only from the first N channels
        List<WebElement> firstNElementsChannelElementList = channelElementList.stream().limit(10).collect(Collectors.toList());
        int selectedChannelNumber = UtilsRandom.getRandomThreadLocal(1, firstNElementsChannelElementList.size() - 1);

        logger.info("Selected random item from list: " + selectedChannelNumber);
        logger.info("Selected random item from list channel number text: " + channelElementList.get(selectedChannelNumber).getText());
        return channelElementList.get(selectedChannelNumber).getText();
    }

    public String getActiveChannelNumberFromGrid() {
        WebElement activeChannelHeader = this.getActiveChannelHeader();
        String activeChannelHeaderNumberText = activeChannelHeader.findElement(
                this.getByWrapperEpgChannelNumber()).getText();
        logger.info("Current channel Selected: " + activeChannelHeaderNumberText);
        return activeChannelHeaderNumberText;
    }

    public void getChannelFromGrid(WebElement channel) {
        logger.info("Selected random channel from list: " + channel.findElement(
                this.getByWrapperEpgChannelNumber()).getAttribute("innerHTML"));
        this.selectChannelFromGrid(channel);
    }

    public void selectChannelFromGrid(WebElement channel) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Get selected channel elements
        WebElement selectedChannelLogo = channel
                .findElement(this.getByWrapperEpgChannelLogo());
        WebElement selectedChannelNumber = channel
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = channel
                .findElement(this.getByWrapperEpgChannelImg());
        // Click selected channel
        wait.until(ExpectedConditions.elementToBeClickable(selectedChannelLogo));
        logger.info("Channel changed to (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel changed to (name): " + selectedChannelImg.getAttribute("alt"));
        this.webDriver.elementClick(selectedChannelLogo);
    }

    public void selectChannelFromGridByNumber(String number) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        WebElement channel = this.getWrapperEpgChannelWrapperByNumber(number);

        // Get selected channel elements
        WebElement selectedChannelLogo = channel
                .findElement(this.getByWrapperEpgChannelLogo());
        WebElement selectedChannelNumber = channel
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = channel
                .findElement(this.getByWrapperEpgChannelImg());
        // Click selected channel
        wait.until(ExpectedConditions.elementToBeClickable(selectedChannelLogo));
        logger.info("Channel changed to (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel changed to (name): " + selectedChannelImg.getAttribute("alt"));
        this.webDriver.elementClick(selectedChannelLogo);
    }

    public String getChannelNumber(WebElement channel) {
        String channelNumber = channel.findElement(this.getByWrapperEpgChannelNumber())
                .getAttribute("innerHTML");
        logger.info("Channel number is: " + channelNumber);
        return channelNumber;
    }

    public void checkChannelFromGrid(WebElement channel) {
        WebElement channelNumber = channel
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement activeChannel = epgChannelHeaderActive;
        //Check current channel selected vs channel random selected
        logger.info("Active channel in EPG grid: " + activeChannel.findElement(
                this.getByWrapperEpgChannelNumber()).getText());
        logger.info("Reference channel selected: "
                + channelNumber.getAttribute("innerHTML"));
        assertEquals("The channel selected are not the same to the random selection",
                activeChannel.findElement(this.getByWrapperEpgChannelNumber())
                        .getText(), channelNumber.getAttribute("innerHTML"));
        logger.info("The channel are the same");
    }

    public String getActiveChannelFromGrid() {
        // Get active channel element
        WebElement activeChannel = epgChannelHeaderActive;
        WebElement selectedChannelImg = activeChannel
                .findElement(this.getByWrapperEpgChannelImg());
        logger.info("Active channel number: " + activeChannel.findElement(
                this.getByWrapperEpgChannelNumber()).getText());

        // Get active channel text number
        String numberChannel = activeChannel.findElement(
                this.getByWrapperEpgChannelNumber()).getText();
        logger.info("Active channel name: " + selectedChannelImg.getAttribute("alt"));

        return numberChannel;
    }

    public String getActiveChannelFromMiniEpg() {
        // Get active channel element
        WebElement activeChannel = miniEpgChannelActive;
        WebElement selectedChannelImg = activeChannel
                .findElement(this.getByWrapperEpgChannelImg());
        logger.info("Active channel number: " + activeChannel.findElement(
                this.getMiniEpgChannelNumber()).getText());

        // Get active channel text number
        String numberChannel = activeChannel.findElement(
                this.getMiniEpgChannelNumber()).getText();
        logger.info("Active channel name: " + selectedChannelImg.getAttribute("alt"));

        return numberChannel;
    }

    public void checkEpgActiveChannelNumber(String activeChannelFromGrid, String channel) {
        assertEquals(activeChannelFromGrid, channel);
        logger.info("Channel numbers match");
    }

    public void checkActiveChannelNumbersEquals(String activeChannel, String channel) {
        assertEquals(activeChannel, channel);
        logger.info("Channel numbers match");
    }

    public void checkActiveChannelNumbersNotEquals(String activeChannel, String channel) {
        assertNotEquals(activeChannel, channel);
        logger.info("Channel numbers don't match");
    }

    public void checkMiniEpgActiveChannelTitleEquals(String activeChannel, String channel) {
        assertEquals(activeChannel, channel);
        logger.info("Channel title match");
    }

    public void checkPlayerActiveChannelNumberNotEquals(String activeChannel, String channel) {
        assertNotEquals(activeChannel, channel);
        logger.info("Channel title don't match");
    }

    public void tvDipPageIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(flowLazyImg));
        assertTrue("Element Title not displayed", dipTitle.isDisplayed());
        logger.info("Element Title is displayed: " + dipTitle.getText());
        assertTrue("Element Cover Image not displayed", flowLazyImg.isDisplayed());
        logger.info("Element cover image is displayed");
        assertTrue("Element channel logo not displayed", dipChannelLogo.isDisplayed());
        assertTrue("Element time not displayed", dipEpgChannelEpgProgramAttributesTime.isDisplayed());
        logger.info("Element time is displayed : " + dipEpgChannelEpgProgramAttributesTime.getText());
        assertTrue("Element age rating not displayed", dipChannelAge.isDisplayed());
        logger.info("Element age rating is displayed: " + dipChannelAge.getText());
        assertTrue("Element description text not displayed", dipChannelDescription.isDisplayed());
        logger.info("Element description text is displayed: " + dipChannelDescription.getText());
    }

    public void setFavoriteEpgChannel(WebElement channel) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Get selected channel elements
        WebElement selectedChannelLogo = channel
                .findElement(this.getByWrapperEpgChannelLogo());
        WebElement selectedChannelNumber = channel
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = channel
                .findElement(this.getByWrapperEpgChannelImg());
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                WebElement selectedChannelFavoriteInactive = channel
                        .findElement(this.getByBtnEpgChannelFavoriteInactive());

                // Click selected channel
                wait.until(ExpectedConditions.elementToBeClickable(selectedChannelFavoriteInactive));
                assertTrue("Channel is favorite before set", selectedChannelFavoriteInactive.isDisplayed());
                this.webDriver.elementClick(selectedChannelFavoriteInactive);
                return true;
            } catch (NoSuchElementException throwable) {
                throwable.printStackTrace();
                this.unsetFavoriteEpgChannel(channel);
                return false;
            }
        });
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Check notification alert
        this.commonPageAction.checkNotificationAlert("Canal agregado a favoritos.");

        wait.until(ExpectedConditions.elementToBeClickable(channel
                .findElement(this.getByBtnEpgChannelFavoriteActive())));
        WebElement selectedChannelFavoriteActive = channel
                .findElement(this.getByBtnEpgChannelFavoriteActive());
        assertTrue("Channel is not favorite after set", selectedChannelFavoriteActive.isDisplayed());

        logger.info("Channel set as favorite (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel set as favorite (name): " + selectedChannelImg.getAttribute("alt"));

    }

    public void setFavoriteActiveChannel() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Get active channel element and text
        WebElement activeChannelHeader = this.getActiveChannelHeader();
        WebElement selectedChannelNumber = activeChannelHeader
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = activeChannelHeader
                .findElement(this.getByWrapperEpgChannelImg());
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                WebElement selectedChannelFavoriteInactive = activeChannelHeader
                        .findElement(this.getByBtnEpgChannelFavoriteInactive());
                // Click selected channel
                wait.until(ExpectedConditions.elementToBeClickable(selectedChannelFavoriteInactive));
                assertTrue("Channel is favorite before set", selectedChannelFavoriteInactive.isDisplayed());
                this.webDriver.elementClick(selectedChannelFavoriteInactive);
                return true;
            } catch (NoSuchElementException throwable) {
                throwable.printStackTrace();
                this.unsetFavoriteEpgChannel(activeChannelHeader);
                return false;
            }
        });
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Check notification alert
        this.commonPageAction.checkNotificationAlert("Agregado a Mis canales");
        wait.until(ExpectedConditions.elementToBeClickable(activeChannelHeader
                .findElement(this.getByBtnEpgChannelFavoriteActive())));
        WebElement selectedChannelFavoriteActive = activeChannelHeader
                .findElement(this.getByBtnEpgChannelFavoriteActive());
        assertTrue("Channel is not favorite after set", selectedChannelFavoriteActive.isDisplayed());
        logger.info("Channel set as favorite (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel set as favorite (name): " + selectedChannelImg.getAttribute("alt"));
    }

    public void unsetFavoriteEpgChannel(WebElement channel) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Get selected channel elements
        WebElement selectedChannelLogo = channel
                .findElement(this.getByWrapperEpgChannelLogo());
        WebElement selectedChannelNumber = channel
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = channel
                .findElement(this.getByWrapperEpgChannelImg());
        WebElement selectedChannelFavoriteActive = channel
                .findElement(this.getByBtnEpgChannelFavoriteActive());

        // Click selected channel
        wait.until(ExpectedConditions.elementToBeClickable(selectedChannelFavoriteActive));
        assertTrue("Channel is not favorite before unset", selectedChannelFavoriteActive.isDisplayed());
        this.webDriver.elementClick(selectedChannelFavoriteActive);

        // Check notification alert
        this.commonPageAction.checkNotificationAlert("Eliminado de Mis canales");

        wait.until(ExpectedConditions.visibilityOf(channel
                .findElement(this.getByBtnEpgChannelFavoriteInactive())));
        WebElement selectedChannelFavoriteInactive = channel
                .findElement(this.getByBtnEpgChannelFavoriteInactive());
        assertTrue("Channel is favorite after unset", selectedChannelFavoriteInactive.isDisplayed());

        logger.info("Channel unset as favorite (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel unset as favorite (name): " + selectedChannelImg.getAttribute("alt"));
    }

    public void unsetFavoriteActiveChannel() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        // Get active channel element and text
        WebElement activeChannelHeader = this.getActiveChannelHeader();
        WebElement selectedChannelNumber = activeChannelHeader
                .findElement(this.getByWrapperEpgChannelNumber());
        WebElement selectedChannelImg = activeChannelHeader
                .findElement(this.getByWrapperEpgChannelImg());

        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                WebElement selectedChannelFavoriteActive = activeChannelHeader
                        .findElement(btnEpgChannelsFavoriteActive);
                // Click selected channel
                wait.until(ExpectedConditions.elementToBeClickable(selectedChannelFavoriteActive));
                assertTrue("Channel is favorite before set", selectedChannelFavoriteActive.isDisplayed());
                this.webDriver.elementClick(selectedChannelFavoriteActive);

                return true;
            } catch (NoSuchElementException throwable) {
                throwable.printStackTrace();
                setFavoriteActiveChannel();
                return false;
            }
        });

        // Check notification alert
        this.commonPageAction.checkNotificationAlert("Eliminado de Mis canales");

        WebElement selectedChannelFavoriteActive = activeChannelHeader
                .findElement(this.getByBtnEpgChannelFavoriteInactive());
        assertTrue("Channel is favorite after unset", selectedChannelFavoriteActive.isDisplayed());

        logger.info("Channel unset as favorite (number): " + selectedChannelNumber.getAttribute("innerHTML"));
        logger.info("Channel unset as favorite (name): " + selectedChannelImg.getAttribute("alt"));
    }

    public void waitSportStatisticsIsHidden() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLabelStatistics()));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Sports Statistics is hidden");
    }

    public void openSportStatistics() {
        this.webDriver.elementMoveTo(playerContainer, 10);
        this.webDriver.elementClick(labelStatistics);
        logger.info("Sports Statistics Opened");
    }

    public void viewSportStatisticsButton() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Contextual button idle
        wait.until(ExpectedConditions.visibilityOf(sportStatistics.findElement(
                this.getContextualButtonIdle())));
        logger.info("Contextual button displayed");


        // Contextual button active
        Awaitility.await().atMost(20, SECONDS).until(() -> {
            try {
                this.webDriver.elementMoveTo(this.getContextualButtonIdle(), 10);
                wait.until(ExpectedConditions.visibilityOf(sportStatistics.findElement(
                        this.getLabelStatistics())));
                wait.until(ExpectedConditions.invisibilityOf(sportStatistics.findElement(
                        this.getLabelStatistics())));
                logger.info("Contextual button not displayed");
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void closeSportStatisticsCloseButton() {
        this.webDriver.elementClick(scoreWrapperCloseButton);
        logger.info("Sports Statistics Closed");
    }

    public void closeSportStatisticsCloseKeyEscButton() {
        this.webDriver.pressKeyEsc();
        logger.info("Sports Statistics Closed by ESC Keyword Button");
    }

    public void sportStatisticsIsClosed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getPanelWrapper()));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Sport Statistics is closed");
    }

    public void sportStatisticsIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));

        // Panel Wrapper checks
        WebElement panelWrapper = sportStatistics.findElement(this.getPanelWrapper());
        assertTrue("Panel Header Title not displayed",
                panelWrapper.findElement(this.getPanelHeaderTitle()).isDisplayed());
        assertTrue("Grid Panel Title not displayed",
                panelWrapper.findElement(this.getGridPanel()).isDisplayed());
        assertTrue("Panel Header Date not displayed",
                panelWrapper.findElement(this.getPanelHeaderDate()).isDisplayed());

        // Score Wrapper checks
        List<WebElement> scoreWrappers = panelWrapper.findElements(this.getScoreWrapper());
        logger.info("Found scoreWrapper elements: " + scoreWrappers.size());
        int scoreWrappersSize = scoreWrappers.size();
        assertTrue("Score wrappers not found", scoreWrappers.size() > 0);

        List<WebElement> scoreWrapperLeftTeamName = panelWrapper
                .findElements(this.getScoreWrapperLeftTeamName());
        assertTrue("Missing Score wrappers Left Team Names",
                scoreWrapperLeftTeamName.size() > 0);
        List<WebElement> scoreWrapperLeftTeamScore = panelWrapper
                .findElements(this.getScoreWrapperLeftTeamScore());
        assertTrue("Missing Score wrappers Left Team Score", scoreWrapperLeftTeamScore.size() > 0);
        List<WebElement> scoreWrapperLeftTeamImg = panelWrapper
                .findElements(this.getScoreWrapperLeftTeamImg());
        assertTrue("Missing Score wrappers Left Team Img", scoreWrapperLeftTeamImg.size() > 0);

        List<WebElement> scoreWrapperRightTeamName = panelWrapper
                .findElements(this.getScoreWrapperRightTeamName());
        assertTrue("Missing Score wrappers Right Team Names",
                scoreWrapperRightTeamName.size() > 0);
        List<WebElement> scoreWrapperRightTeamScore = panelWrapper
                .findElements(this.getScoreWrapperRightTeamScore());
        assertTrue("Missing Score wrappers Right Team Score", scoreWrapperRightTeamScore.size() > 0);
        List<WebElement> scoreWrapperRightTeamImg = panelWrapper
                .findElements(this.getScoreWrapperRightTeamImg());
        assertTrue("Missing Score wrappers Right Team Img", scoreWrapperRightTeamImg.size() > 0);

        assertTrue("Score wrapper footer not found", panelWrapper
                .findElements(this.getScoreWrapperScoreFooter()).size() > 0);

        // Stats wrapper checks
        WebElement statsWrapper = sportStatistics.findElement(this.getStatsWrapper());
        //Open SeeMore left panel
        this.webDriver.elementClick(statsWrapper.findElement(this.getStatsWrapperSeeMoreHeader()));
        logger.info("See More Opened");

        wait.until(ExpectedConditions.visibilityOf(
                statsWrapper.findElement(this.getStatsWrapperSeeMoreHeader())));

        WebElement statsWrapperHeaderTitle = statsWrapper.findElement(this.getStatsWrapperHeaderTitle());

        assertTrue("See more header not match", statsWrapperHeaderTitle.getText().equals("Ver menos"));
        logger.info("See more header match: " + statsWrapperHeaderTitle.getText());

        WebElement wrapperTabsWrapper = statsWrapper.findElement(this.getStatsWrapperTabsWrapper());
        List<WebElement> tabsWrappers = wrapperTabsWrapper.findElements(this.getStatsWrapperTab());

        assertTrue("Tab Estadisticas del partido is not displayed", tabsWrappers.get(0)
                .getText().equals("Estadísticas de partido"));
        logger.info("Tab Estadisticas del partido is displayed");

        assertTrue("Tab Formaciones is not displayed", tabsWrappers.get(1).getText().equals("Formaciones"));
        logger.info("Tab Formaciones del partido is displayed");

        assertTrue("Tab Fixture is not displayed", tabsWrappers.get(2).getText().equals("Fixture"));
        logger.info("Tab Fixture del partido is displayed");

        assertTrue("Tab Tablas is not displayed", tabsWrappers.get(3).getText().equals("Tablas"));
        logger.info("Tab Tablas del partido is displayed");

        try {
            Awaitility.await().atMost(10, SECONDS).until(() -> {
                try {
                    webDriver.waitUntil(10, wd -> statsWrapperNoContent.isDisplayed());
                    assertTrue("The information is available", statsWrapperNoContent.isDisplayed());
                    logger.info("The information is not available");
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    return true;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    return false;
                }
            });
        } catch (Exception exception) {
            logger.info("The information is available");
            assertTrue("Slick Arrow Prev is not displayed", statsWrapperSlickArrowSlickPrev.isDisplayed());
            logger.info("Slick Arrow Prev is displayed");

            assertTrue("Slick Arrow Next is not displayed", statsWrapperSlickArrowSlickNext.isDisplayed());
            logger.info("Slick Arrow Next is displayed");
            //check local team
            assertEquals("Home Team Name is not displayed", 1, carouselWrapperHomeTeam.findElements(
                    this.getCarouselWrapperHomeTeamName()).size());
            logger.info("Home Team Name: " + carouselWrapperHomeTeam.findElement(
                    this.getCarouselWrapperHomeTeamName()).getAttribute("innerHTML") + " is displayed");
            assertEquals("Home Team Img is not displayed", 1, carouselWrapperHomeTeam.findElements(
                    this.getCarouselWrapperHomeTeamImg()).size());
            logger.info("Home Team Img is displayed");
            assertEquals("Home Team Score is not displayed", 1, carouselWrapperHomeTeam.findElements(
                    this.getCarouselWrapperHomeTeamScore()).size());
            logger.info("Home Team Score: " + carouselWrapperHomeTeam.findElement(
                    this.getCarouselWrapperHomeTeamScore()).getAttribute("innerHTML") + " is displayed");
            //check visitor team
            WebElement awayTeam = this.webDriver.findElement(this.getCarouselWrapperAwayTeam());
            assertEquals("Away Team Name is not displayed", 1, awayTeam.findElements(
                    this.getCarouselWrapperAwayTeamName()).size());
            logger.info("Away Team Name: " + awayTeam.findElement(
                    this.getCarouselWrapperAwayTeamName()).getAttribute("innerHTML") + " is displayed");
            assertEquals("Away Team Img is not displayed", 1, awayTeam.findElements(
                    this.getCarouselWrapperAwayTeamImg()).size());
            logger.info("Away Team Img is displayed");
            assertEquals("Away Team Score is not displayed", 1, awayTeam.findElements(
                    this.getCarouselWrapperAwayTeamScore()).size());
            logger.info("Away Team Score: " + awayTeam.findElement(
                    this.getCarouselWrapperAwayTeamScore()).getAttribute("innerHTML") + " is displayed");
        }
        //Close SeeMore left panel
        this.webDriver.elementClick(statsWrapper.findElement(this.getStatsWrapperSeeMoreHeader()));
        logger.info("See left Closed");
    }

    public void openSeeMoreSportStatistics() {
        // Stats wrapper checks
        WebElement statsWrapper = sportStatistics.findElement(this.getStatsWrapper());
        //Open SeeMore left panel
        this.webDriver.elementClick(statsWrapper.findElement(this.getStatsWrapperSeeMoreHeader()));
        logger.info("See More Opened");
    }

    public void allTablesIsDisplayed() {
        //Open tab tables
        this.webDriver.elementClick(statsWrapperTabTables);

        //Check Positions
        assertTrue("Positions is not displayed", statsWrapperHeaderItemPositions.isDisplayed());
        logger.info("Positions header item is displayed");
        this.loopTableStatistics();

        //Check Score
        this.webDriver.elementClick(statsWrapperHeaderItemScorersPrev);
        assertTrue("Scorers is not displayed", statsWrapperHeaderItemScorers.isDisplayed());
        logger.info("Scorers header item is displayed");
        this.loopTableStatistics();

        //Check Average
        this.webDriver.elementClick(statsWrapperHeaderItemAveragePrev);
        assertTrue("Average is not displayed", statsWrapperHeaderItemAverage.isDisplayed());
        logger.info("Average header item is displayed");
        this.loopTableStatistics();


    }

    public void loopTableStatistics() {
        ArrayList<String> itemTableHeaderColumns = new ArrayList<>();

        for (WebElement tableHeaderItem : carouselWrapperTableHeaderItem) {
            if (!tableHeaderItem.getText().isEmpty()) {
                itemTableHeaderColumns.add(tableHeaderItem.getText());
            }
        }
        assertTrue("Item table header is not displayed", itemTableHeaderColumns.size() > 0);
        logger.info("Item table header is displayed");

        logger.info(itemTableHeaderColumns
                .stream().collect(Collectors.joining(", ", "[", "]")));

        ArrayList<String> alternateBgItemColumns = new ArrayList<>();

        for (WebElement alternateBgItem : carouselWrapperAlternateBg) {
            alternateBgItemColumns.add(alternateBgItem.getText());
        }
        assertTrue("Item table is not displayed", alternateBgItemColumns.size() > 0);
        logger.info("Item table is displayed");

        logger.info(alternateBgItemColumns
                .stream().collect(Collectors.joining(", ", "[", "]")));
    }

    public void matchStatisticsIsDisplayed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        if (tabsWrapperCarouselComparativeHeaderItem.size() == 0) {
            logger.info("The information is not available");
        } else {
            logger.info("The information is available");
            // check header item comparative
            assertTrue("Carousel header item comparative is not displayed",
                    tabsWrapperCarouselComparativeHeaderItem.get(0).isDisplayed());
            logger.info("Header Item displayed: " + tabsWrapperCarouselComparativeHeaderItem.get(0).getText());
            // check header slick prev team name
            assertTrue("Header slick prev team name is not displayed", tabsWrapperCarouselComparativeHeaderSlickPrev.isDisplayed());
            logger.info("Header slick prev team name is displayed: " + tabsWrapperCarouselComparativeHeaderSlickPrev.getText());
            // check header slick next team name
            assertTrue("Header slick prev team name is not displayed", tabsWrapperCarouselComparativeHeaderSlickNext.isDisplayed());
            logger.info("Header slick next team name is displayed: " + tabsWrapperCarouselComparativeHeaderSlickNext.getText());

            // check local team name
            assertTrue("Local team name is not displayed", tabsWrapperCarouselComparativeLocalTeamName.getText().isEmpty());
            logger.info("Local team name is displayed: " + tabsWrapperCarouselComparativeLocalTeamName.getAttribute("innerHTML"));
            // check local team image
            assertTrue("Local team image is not displayed", tabsWrapperCarouselComparativeImg.get(0).isDisplayed());
            logger.info("Local team image is displayed");
            // check local team score
            assertTrue("Local team score is not displayed", tabsWrapperCarouselComparativeLocalTeamScore.getText().isEmpty());
            logger.info("Local team score is displayed: " + tabsWrapperCarouselComparativeLocalTeamScore.getAttribute("innerHTML"));

            // check visitor team name
            assertTrue("Visitor team name is not displayed", tabsWrapperCarouselComparativeVisitorTeamName.getText().isEmpty());
            logger.info("Visitor team name is displayed: " + tabsWrapperCarouselComparativeVisitorTeamName.getAttribute("innerHTML"));
            // check visitor team image
            assertTrue("Visitor team image is not displayed", tabsWrapperCarouselComparativeImg.get(1).isDisplayed());
            logger.info("Visitor team image is displayed");
            // check visitor team score
            assertTrue("Local team score is not displayed", tabsWrapperCarouselComparativeVisitorTeamScore.getText().isEmpty());
            logger.info("Visitor team score is displayed: " + tabsWrapperCarouselComparativeVisitorTeamScore.getAttribute("innerHTML"));
            // check board wrapper item Label
            for (WebElement boardRow : boardWrapperBoardRow) {
                if (!boardRow.findElement(
                        this.getBoardWrapperItemLabel()).getText().isEmpty()) {
                    assertTrue("Item label is not displayed",
                            boardRow.findElement(
                                    this.getBoardWrapperItemLabel()).isDisplayed());
                    logger.info("Item label: " + boardRow.findElement(
                            this.getBoardWrapperItemLabel()).getText());
                    List<WebElement> statsRowWrappers = boardRow.findElements(
                            this.getBoardWrapperStatsRowWrapper());
                    ArrayList<String> listBoardValues = new ArrayList<>();
                    for (WebElement statsRowWrapper : statsRowWrappers) {
                        List<WebElement> boardValues = statsRowWrapper.findElements(
                                this.getBoardWrapperStatsRowWrapperBoardValue());
                        for (WebElement boardValue : boardValues) {
                            listBoardValues.add(boardValue.getText());
                        }
                    }
                    assertTrue("Board value is not displayed", listBoardValues.size() > 0);
                    logger.info("Board value: " + listBoardValues.stream()
                            .collect(Collectors.joining(", ", "[", "]")));
                }
            }
        }
    }

    public void zappingAllChannelsMiniEpg(String firstChannel) {
        Awaitility.await().atMost(10, MINUTES).until(() -> {
            try {
                while (true) {
                    this.webDriver.pressKeyDown();
                    String channelNumber = this.webDriver.findElement(
                            this.playerPageAction.getVopLogo()).getAttribute("innerHTML");
                    logger.info("Channel Selected: " + channelNumber);

                    // Check channel playback
                    try {
                        Awaitility.await().atMost(10, SECONDS).until(() -> {
                            try {
                                // wait for player playback
                                this.playerPageAction.waitPlayerLoadAndPlayback();
                                this.playerPageAction.isPlaying();
                                logger.info("Player OK");
                                return true;
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                                return false;
                            }
                        });
                    } catch (ConditionTimeoutException conditionTimeoutException) {
                        // Add channel number to failed channel playback array and continue
                        Hooks.listNumberChannelsPlayBackFail.add(channelNumber);
                    }
                    // Break if channel is same as firstChannel
                    if (channelNumber.equals(firstChannel)) {
                        break;
                    }
                }
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkAllChannelsZappingResult() {
        logger.info("Amount of Channels to fail playing: " + Hooks.listNumberChannelsPlayBackFail.size());
        assertEquals("Channels not playing: " + Hooks.listNumberChannelsPlayBackFail
                .stream().collect(Collectors.joining(", ", "[", "]")), 0, Hooks.listNumberChannelsPlayBackFail.size());
    }

    public void scrollChannelsDownInMiniEpg(int amount) throws AWTException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnCloseMiniEPG));
        this.playerPageAction.isPlaying();
        for (int i = 0; i <= amount; i++) {
            this.webDriver.pressKeyDown();
        }
        this.playerPageAction.isPlaying();
        logger.info("Zapping Down channels OK");
    }

    public void checkChannelsInMiniEPG(int randomEpgChannel, String currentChannelMiniEpgNumber, String currentChannelPlayerNumber) {
        // Scroll up and check channel number and title are equals
        this.openMiniEpg();
        this.scrollChannelsUpInMiniEpg(randomEpgChannel);
        var newChannelMiniEpgNumber = this.getActiveChannelFromMiniEpg();
        this.closeMiniEpg();
        var newChannelPlayerNumber = this.playerPageAction.getPlayerChannelNumber();
        System.out.println("currentChannelMiniEpgNumber: " + currentChannelMiniEpgNumber);
        System.out.println("newChannelMiniEpgNumber: " + newChannelMiniEpgNumber);
        this.checkActiveChannelNumbersEquals(currentChannelMiniEpgNumber, newChannelMiniEpgNumber);
        System.out.println("currentChannelPlayerNumber: " + currentChannelPlayerNumber);
        System.out.println("newChannelPlayerNumber: " + newChannelPlayerNumber);
        this.checkActiveChannelNumbersEquals(currentChannelPlayerNumber, newChannelPlayerNumber);
    }

    public String getChannelInMiniEPG(int randomEpgChannel, String currentChannelMiniEpgNumber) throws InterruptedException, AWTException {
        // Scroll down and check channel number and title are not equals
        this.closeMiniEpg();
        String currentChannelPlayerNumber = this.playerPageAction.getPlayerChannelNumber();
        this.openMiniEpg();
        this.scrollChannelsDownInMiniEpg(randomEpgChannel);
        String newChannelMiniEpgNumber = this.getActiveChannelFromMiniEpg();
        this.closeMiniEpg();
        String newChannelPlayerNumber = this.playerPageAction.getPlayerChannelNumber();
        this.checkActiveChannelNumbersNotEquals(currentChannelMiniEpgNumber, newChannelMiniEpgNumber);
        this.checkActiveChannelNumbersNotEquals(currentChannelPlayerNumber, newChannelPlayerNumber);
        return newChannelMiniEpgNumber;
    }

    public void scrollChannelsUpInMiniEpg(int amount) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(btnCloseMiniEPG));
        this.playerPageAction.isPlaying();
        for (int i = 0; i <= amount; i++) {
            this.webDriver.pressKeyUp();
        }
        this.playerPageAction.isPlaying();
        logger.info("Zapping Up channels OK");
    }

    public void scrollToChannelEpg(String firstChannel, String searchChannel) {
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                while (true) {
                    this.webDriver.pressKeyDown();
                    logger.info("Current channel Selected: " + this.webDriver.findElement(
                            this.playerPageAction.getVopLogo()).getAttribute("innerHTML"));
                    String channelNumber = this.webDriver.findElement(
                            this.playerPageAction.getVopLogo()).getAttribute("innerHTML");

                    try {
                        Awaitility.await().atMost(1, SECONDS).until(() -> {
                            try {
                                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                                // wait for player playback
                                this.playerPageAction.waitPlayerLoad();
                                //logger.info("Player OK");
                                return true;
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                                return false;
                            }
                        });
                    } catch (ConditionTimeoutException conditionTimeoutException) {
                        conditionTimeoutException.printStackTrace();
                    }
                    // Check if current channel number matches desired channel for channel found
                    if (searchChannel.equals(channelNumber)) {
                        break;
                    }
                    // Check if current channel number matches first channel to avoid zapping loop
                    if (channelNumber.equals(firstChannel)) {
                        break;
                    }
                }
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
        logger.info("Scroll to channel " + searchChannel + " completed");
    }

    public void scrollToEpgGridRow(String searchChannel) {
        // Click active element from grid
        this.moveToActiveChannelElementFromGrid();
        // Scroll to desired channel from it
        logger.info("Scrolling to channel: " + searchChannel);
        Awaitility.await().atMost(60, SECONDS).until(() -> {
            try {
                // Wait not loading channel video

                // Get active channel element and text
                WebElement activeChannelHeader = this.getActiveChannelHeader();
                String activeChannelHeaderNumberText = activeChannelHeader.findElement(
                        this.getByWrapperEpgChannelNumber()).getAttribute("innerHTML");
                logger.info("Current channel Selected: " + activeChannelHeaderNumberText);
                isActiveChannelGreater = Integer.parseInt(searchChannel) < Integer.parseInt(activeChannelHeaderNumberText);
                // Check that current active channel matches desired channel
                assertEquals(searchChannel, activeChannelHeaderNumberText);
                logger.info("Scroll to channel " + searchChannel + " completed");
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                if (isActiveChannelGreater) {
                    this.webDriver.pressKeyUp();
                } else {
                    this.webDriver.pressKeyDown();
                }
                this.playerPageAction.waitPlaybackAfterChannelChange();
                //this.playerPageAction.waitNoSeeking();
                return false;
            }
        });
    }

    public void scrollChannelsDownInTvGuide(int amount) {
        for (int i = 0; i <= amount; i++) {
            this.webDriver.pressKeyDown();
        }
    }

    public void scrollToChannel() {
        this.webDriver.findElements(By.xpath(".//p[@class='channelNumber']"))
                .stream()
                .filter(wd -> wd.getText().equals("10"))
                .findFirst().get().click();
    }

    public List<WebElement> getChannelsLive() {
        return wrapperEpgChannelEpgProgramLive;
    }

    public List<WebElement> getChannelsAlreadyIssued() {
        return wrapperEpgChannelEpgProgramAlreadyIssued;
    }

    public void selectRandomChannelLive() {
        int randomEpgChannelLive = UtilsRandom.getRandom(8, 13);
        this.webDriver.elementMoveTo(getChannelsLive().get(randomEpgChannelLive), 3);
        getChannelsLive().get(randomEpgChannelLive).click();
    }

    public void selectRandomChannelAlreadyIssued() {
        Awaitility.await().atMost(20, SECONDS).until(() -> {
            try {
                int randomEpgChannelAlreadyIssued = UtilsRandom.getRandom(20, 25);
                getChannelsAlreadyIssued().get(randomEpgChannelAlreadyIssued).click();
                return true;
            } catch (ElementNotInteractableException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public void selectRandomProgramAlreadyIssued() {
        Awaitility.await().atMost(30, SECONDS).until(() -> {
            try {
                this.webDriver.elementWaitDisplayed(buttonPrev);
                this.webDriver.elementClick(buttonPrev);
                WebElement randomVisiblePastProgram = epgPrecedingProgramsAlreadyIssued.get(UtilsRandom.getRandom(0, epgPrecedingProgramsAlreadyIssued.size()));
                this.webDriver.elementWaitDisplayed(randomVisiblePastProgram);
                this.webDriver.elementMoveTo(randomVisiblePastProgram, 5);
                this.webDriver.elementClick(randomVisiblePastProgram);

                return true;
            } catch (ElementNotInteractableException e) {

                e.printStackTrace();
                return false;
            }
        });
    }

    public void scrollChannelDownInPlayer() {
        this.webDriver.pressKeyDown();
        // wait for player playback
        this.playerPageAction.waitPlayerLoad();
        logger.info("Scroll to channel completed");
    }

    public void scrollChannelsDownInPlayer(int amount) {
        for (int i = 0; i < amount; ++i) {
            this.webDriver.pressKeyDown();
            this.playerPageAction.isPlaying();
            logger.info("Scroll to channel completed" + i + "times");
        }
    }

    public void scrollChannelsUpInPlayer(int amount) {
        for (int i = 0; i < amount; ++i) {
            this.webDriver.pressKeyUp();
            this.playerPageAction.isPlaying();
            logger.info("Scroll to channel completed" + i + "times");
        }
    }

    public void scrollChannelsDownInEpg(int amount) {
        for (int i = 0; i < amount; ++i) {
            this.webDriver.pressKeyDown();
            logger.info("Scroll to channel completed" + i + "times");
        }
    }

    public void scrollChannelsUpInEpg(int amount) {
        for (int i = 0; i < amount; ++i) {
            this.webDriver.pressKeyUp();
            logger.info("Scroll to channel completed" + i + "times");
        }
    }

    public void scrollVerticalDown(String channelFound) {
        Awaitility.await().atMost(30, SECONDS).until(() -> {
            try {
                logger.info("" + webDriver.findElements(this.getByWrapperEpgChannelNumber()).size());
                boolean found = false;
                for (WebElement channelNumber : webDriver.findElements(this.getByWrapperEpgChannelNumber())) {
                    logger.info(channelNumber.getText());
                    if (channelNumber.getText().equals(channelFound)) {
                        found = true;
                    }
                }
                assertTrue("Expected channelNumber not found", found);
                return true;
            } catch (Throwable throwable) {
                this.webDriver.pressKeyDown();
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void playerHide() {
        this.webDriver.pressKeyBackspace();
        logger.info("hide player");
    }

    public void checkDipElementsChannelPast() {
        this.webDriver.elementWaitDisplayed(elementTitleDipChannel);
        logger.info("Program title in DIP is displayed");
//        this.webDriver.elementWaitDisplayed(elementLogoChannelDipChannel);
//        logger.info("Channel logo in DIP is displayed");
//        this.webDriver.elementWaitDisplayed(elementChannelDipChannel);
//        logger.info("Channel in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementTimeDipChannel);
        logger.info("Channel time in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementAgeDipChannel);
        logger.info("Channel age in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementDescriptionDipChannel);
        logger.info("Channel description in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementImageDipChannel);
        logger.info("Channel image in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonPlayDipChannel);
        logger.info("Button play in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonEmissionsDipChannel);
        logger.info("Button emissions in DIP is diplayed");
        this.webDriver.elementWaitDisplayed(elementButtonCloseDipChannel);
        logger.info("Button close in DIP is displayed");
    }

    public void checkDipElementsChannelLive() {
        this.webDriver.elementWaitDisplayed(elementTitleDipChannel);
        logger.info("Program title in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementLiveDipChannel);
        logger.info("Program 'live' in DIP is displayed");
        //se comentan los elementos que ya no existen en el detalle del canal en emisión
//        this.webDriver.elementWaitDisplayed(elementLogoChannelDipChannel);
//        logger.info("Channel logo in DIP is displayed");
//        this.webDriver.elementWaitDisplayed(elementChannelDipChannel);
//        logger.info("Channel in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementTimeDipChannel);
        logger.info("Channel time in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementAgeDipChannel);
        logger.info("Channel age in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementDescriptionDipChannel);
        logger.info("Channel description in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementImageDipChannel);
        logger.info("Channel image in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonPlayDipChannel);
        logger.info("Button play in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonRestartDipChannel);
        logger.info("Button restart in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonRecordingDipChannel);
        logger.info("Button recording in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonEmissionsDipChannel);
        logger.info("Button emissions in DIP is displayed");
        this.webDriver.elementWaitDisplayed(elementButtonCloseDipChannel);
        logger.info("Button close in DIP is displayed");
    }

    public void restartChannelLiveDIP() {
        this.webDriver.elementWaitDisplayed(elementButtonRestartDipChannel);
        logger.info("Button restart in DIP is displayed");
        this.webDriver.elementClick(elementButtonRestartDipChannel);
    }

    public void checkElementsEPG() {
        this.webDriver.elementWaitDisplayed(this.webDriver.findElement(this.getByEpgGrid()));
        logger.info("EPG is displayed");
        this.webDriver.elementWaitDisplayed(epgFilters);
        logger.info("EGP filters is displayed");
        this.webDriver.elementWaitDisplayed(epgScrollableGrid);
        logger.info("EPG Scrollable Grid is displayed");
        this.webDriver.elementWaitDisplayed(epgProgramVerticalAlignFuture);
        logger.info("EPG program vertical align future is displayed");
        this.webDriver.elementWaitDisplayed(epgProgramVerticalAlignLive);
        logger.info("EPG program vertical align live is displayed");
        this.webDriver.elementWaitDisplayed(epgProgramVerticalTruncateTitle);
        logger.info("EPG program vertical truncate title");
        this.webDriver.elementWaitDisplayed(epgHeader);
        logger.info("EPG header is displayed");
        this.webDriver.elementWaitDisplayed(btnEpgControlsNextButton);
        logger.info("Button EPG 'Controls Next' is displayed");
        this.webDriver.elementWaitDisplayed(btnEpgControlsPrevButton);
        logger.info("Button EPG'Controls Previous' is displayed");
        this.webDriver.elementWaitDisplayed(dropdownEpgDaySelector);
        logger.info("Dropdown EPG day selector is displayed");
        this.webDriver.elementWaitDisplayed(elementEPGTimeLineDay);
        logger.info("EPG time line day is displayed");
        this.webDriver.elementWaitDisplayed(btnHideGuide);
        logger.info("'Hide Guide' is displayed");
    }

    public void goPageDownEpg(int count) {
        webDriver.pageDown(epg, count);
    }

    public void clickViewGuide() {
        webDriver.elementClick(elementViewGuide);
    }

    public void accessToStripeTVLive() {
        Awaitility.await().atMost(50, SECONDS).until(() -> {
            try {
                webDriver.waitForElementVisible(tvLive);
                webDriver.elementClick(tvLive);
                return true;
            } catch (Throwable throwable) {
                commonPageAction.scrollToContentStripe(Hooks.props.homePageLiveTv());
                return false;
            }
        });
    }

    public void pressContentNextEmissions(String content) {
        this.webDriver.elementWaitDisplayed(getChanel(content));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement chanel = this.webDriver.findElement(getChanel(content));
        WebElement programaFuturo = chanel.findElement(getProgramFuture);
        programaFuturo.click();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void recordProgram() {
        this.webDriver.elementWaitDisplayed(getRecordingEmision);
        this.webDriver.elementClick(getRecordingEmision);
    }

    public void selectThisEmissionButton() {
        this.webDriver.elementWaitDisplayed(getEstaEmision);
        this.webDriver.elementClick(getEstaEmision);
        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void pressDeleteButtonRecord() {
        try {
            this.webDriver.elementClick(deleteRecording);
        } catch (Throwable throwable) {
            this.webDriver.elementClick(elementAcceptDelete);
        }
        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void pressCancelButton() {
        this.webDriver.elementWaitDisplayed(getCancelRecordButton);
        this.webDriver.elementClick(getCancelRecordButton);
    }

    public void selectRandomFutureProgram() {
        Awaitility.await().atMost(30, SECONDS).until(() -> {
            try {
                WebElement randomVisiblePastProgram = epgPrecedingProgramsNextBroadcast.get(UtilsRandom.getRandom(0, epgPrecedingProgramsNextBroadcast.size()));
                this.webDriver.elementClick(randomVisiblePastProgram);

                return true;
            } catch (ElementNotInteractableException e) {
                e.printStackTrace();
                return false;
            }
        });
    }


    public void accessEmissionsSection() {
        this.webDriver.elementWaitDisplayed(getEmisiones);
        this.webDriver.elementClick(getEmisiones);
    }

    public void emissionsSectionIsDisplayed() {
        assertTrue("Program title is visible",
                getTitle.isDisplayed());
        assertTrue("Title seccion emisiones not displayed",
                titleSeccionEmisiones.isDisplayed());
        assertTrue("Description is not visible",
                description.isDisplayed());
        assertTrue("Record button is not visible",
                primaryButton.isDisplayed());

        logger.info("The 'Emissions' section is displayed correctly");
        logger.info("Cantidad de emisiones " + listEmisiones.size());

    }

    public void selectButtonRecord() {
        this.webDriver.elementWaitDisplayed(getRecordingEmision);
        this.webDriver.elementClick(getRecordingEmision);
    }

    public void recordingOptionsIsDisplayed() {
        this.webDriver.elementWaitDisplayed(modalTitle);
        assertTrue(modalTitle.isDisplayed());
        logger.info("Modal title is displayed");
        assertTrue(modalDescription.isDisplayed());
        logger.info("Modal description is displayed");
        assertEquals(modalButtons.size(), 2);
        logger.info("Buttons is displayed");
    }

    public void recordAllEmissions() {
        this.webDriver.elementWaitDisplayed(allEmissions);
        this.webDriver.elementClick(allEmissions);
    }

    public void recordingIsDisplayed() {
        this.webDriver.elementWaitDisplayed(getTitleProgram);
        title = this.webDriver.findElement(getTitleProgram).getText();

        this.webDriver.elementWaitDisplayed(myContents);
        this.webDriver.elementClick(myContents);
        this.webDriver.elementWaitDisplayed(getRecordings);
        this.webDriver.elementClick(getRecordings);
        try {
            this.webDriver.elementWaitDisplayed(getItemWrapper);
            this.webDriver.mouseMoveElement(getItemWrapper);
            WebElement program = this.webDriver.findElement(getItemWrapperBy);
            assertTrue("Element not displayed", program.isDisplayed());

            titles = this.webDriver.findElement(getItemWrapperBy).getText();
            assertTrue(titles.contains(title));
        } catch (Throwable throwable) {
            this.webDriver.elementWaitDisplayed(getScheduledRecordingsStripe);
            WebElement scheduledrecordings = this.webDriver.findElement(getScheduledRecordingsStripe);
            WebElement recording = scheduledrecordings.findElement(getItemWrapperBy);
            this.webDriver.mouseMoveElement(recording);
            titles = recording.getText();
            assertTrue(titles.contains(title));
        }
        logger.info("Recording is displayed");
    }

    public void recordThisEmissions() {
        this.webDriver.elementWaitDisplayed(thisEmissions);
        this.webDriver.elementClick(thisEmissions);
    }

    public void pressCurrentProgram(String channel) {
        this.webDriver.elementWaitDisplayed(getChanel(channel));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement chanel = this.webDriver.findElement(getChanel(channel));
        WebElement programLive = chanel.findElement(elementEgpProgramLive);
        programLive.click();
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void checkRecordingIsNotAvailable() {
        this.webDriver.elementWaitDisplayed(myContents);
        this.webDriver.elementClick(myContents);
        this.webDriver.elementWaitDisplayed(getRecordings);
        this.webDriver.elementClick(getRecordings);
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            this.webDriver.findElement(getMessage).isDisplayed();
        } catch (Throwable throwable) {
            List<WebElement> recordingsItems = this.webDriver.findElements(getItemWrapperBy);
            int items = recordingsItems.size();
            int x = 0;
            while (x < items) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
                WebElement contents = this.webDriver.findElements(getItemWrapperBy).get(0);
                this.webDriver.mouseMoveElement(contents);
                contents.click();
                try {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    this.webDriver.mouseMoveElement(cancelRecording);
                    this.webDriver.elementWaitDisplayed(cancelRecording);
                    this.webDriver.elementClick(cancelRecording);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    this.webDriver.elementWaitDisplayed(acceptDelete);
                    this.webDriver.elementClick(acceptDelete);
                } catch (Throwable throwable1) {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    this.webDriver.mouseMoveElement(acceptDelete);
                    this.webDriver.elementWaitDisplayed(delete);
                    this.webDriver.elementClick(delete);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    this.webDriver.elementWaitDisplayed(deleteRecording);
                    this.webDriver.elementClick(deleteRecording);
                }
                x++;
            }
        }
    }

    public void recordAllBroadcasts() {
        this.webDriver.elementWaitDisplayed(allEmissions);
        this.webDriver.elementClick(allEmissions);

        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void recordedProgramIsDisplayedInRecordings() {
        this.commonPageAction.clickNavbarContentMyContent();
        this.commonPageAction.clickNavbarRecordings();
        this.webDriver.elementWaitDisplayed(getItemWrapper);
        this.webDriver.mouseMoveElement(getItemWrapper);
    }

    public void removeRecordingNextProgramToEmit() {
        this.webDriver.elementWaitDisplayed(cancelRecording);
        this.webDriver.elementClick(cancelRecording);
        this.webDriver.elementWaitDisplayed(deleteRecording);
        this.webDriver.elementClick(deleteRecording);

        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void theRecordingIsNotAvailable() {
        this.webDriver.elementWaitDisplayed(myContents);
        this.webDriver.elementClick(myContents);
        this.webDriver.elementWaitDisplayed(getRecordings);
        this.webDriver.elementClick(getRecordings);
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
            this.webDriver.findElement(getMessage).isDisplayed();
        } catch (Throwable throwable) {
            List<WebElement> recordings = this.webDriver.findElements(getItemWrapperBy);
            int elements = recordings.size();
            int x = 0;
            while (x < elements) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
                WebElement contents = this.webDriver.findElements(getItemWrapperBy).get(0);
                this.webDriver.mouseMoveElement(contents);
                contents.click();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                try {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    this.webDriver.mouseMoveElement(cancelRecording);
                    this.webDriver.elementWaitDisplayed(cancelRecording);
                    this.webDriver.elementClick(cancelRecording);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    this.webDriver.elementWaitDisplayed(acceptDelete);
                    this.webDriver.elementClick(acceptDelete);
                } catch (Throwable throwable1) {
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                    this.webDriver.mouseMoveElement(delete);
                    this.webDriver.elementWaitDisplayed(delete);
                    this.webDriver.elementClick(delete);
                    this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    this.webDriver.elementWaitDisplayed(deleteRecording);
                    this.webDriver.elementClick(deleteRecording);
                }
                x++;
            }
        }
    }

    public void removeRecordingFromCurrentProgram() {
        this.commonPageAction.accessNavbarElementTvGuide();
        this.pressCurrentProgram("METRO");
        this.webDriver.elementWaitDisplayed(cancelRecording);
        this.webDriver.elementClick(cancelRecording);
        this.webDriver.elementWaitDisplayed(deleteRecording);
        this.webDriver.elementClick(deleteRecording);

        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void clickAllEmissions() {
        this.webDriver.elementWaitDisplayed(allEmissions);
        this.webDriver.elementClick(allEmissions);
    }

    public void recordingLimitIsDisplayed() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assertTrue("Recording limit message not displayed ", recordingLimitMessage.isDisplayed());
        logger.info("Recording limit message is displayed " + recordingLimitMessage.getText());
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void mouseOverPlayer() {
        Awaitility.await().atMost(120, SECONDS).until(() -> {
            try {
                logger.info("mouse over player container");

                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

                this.webDriver.waitUntil(1, wd -> flowPlayerContainer.isDisplayed());

                this.webDriver.mouseOver(flowPlayerContainer);

                this.webDriver.waitUntil(1, wd -> playButton.isDisplayed());

                this.webDriver.mouseOver(flowPlayerContainer);

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkLiveChannelIsLoading() {
        String jsCommand = String.join("", "return " + String.join("", "document.getElementsByTagName('video')[0]" + ".seeking;"));
        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        var loading = By.xpath(".//div[@class='loading']");
        Awaitility.await().atMost(Duration.ofSeconds(60)).until(() -> {
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(loading));
                Boolean seekingState;
                seekingState = (Boolean) webDriver.executeScript(jsCommand);
                assertFalse("Seeking state is true", seekingState);
                logger.info("Player is not seeking");

                return true;
            } catch (Throwable throwable) {
                this.webDriver.refreshUrl();
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void clickOnPlayButton() {
        this.checkLiveChannelIsLoading();
        this.mouseOverPlayer();
        playButton.click();
    }

    public void selectRecord() {
        this.webDriver.elementWaitDisplayed(getRecord);
        this.webDriver.elementClick(getRecord);
    }

    public void recordThisBroadcast() {
        this.webDriver.elementWaitDisplayed(thisEmission);
        this.webDriver.elementClick(thisEmission);
    }

    public void recordingLimitDisplayingCorrectly() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assertTrue("Recording limit message not displayed ",
                recordingLimitMessage.isDisplayed());
        logger.info("Recording limit message is displayed " + recordingLimitMessage.getText());
        assertTrue("Limit Title is not displayed",
                limitTitle.isDisplayed());
        logger.info("Limit Title is displayed");
        assertTrue("Limit description is not displated ",
                limitDescription.isDisplayed());
        logger.info("Limit discription is displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void recordingCancelled() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        assertTrue(webDriver.findElement(getRecord).isDisplayed());
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void clickAllBroadcasts() {
        this.webDriver.elementWaitDisplayed(allEmissions);
        this.webDriver.elementClick(allEmissions);
    }

    public void confirmRecording() {
        this.webDriver.elementWaitDisplayed(confirmRecording);
        this.webDriver.elementClick(confirmRecording);
    }

    public void checkRecordingAllBroadcasts() {
        this.webDriver.elementWaitDisplayed(recStatus);
        assertTrue("Recording status is not visible",
                recStatus.isDisplayed());
        assertTrue(recStatus.getText().contains("Grabación"));
        assertTrue("Button 'Cancelar' is not displayed",
                webDriver.findElement(getCancelRecording).isDisplayed());
        logger.info("Button 'Cancelar' is displayed");
        this.webDriver.elementWaitDisplayed(mensaje);
    }

    public void cancelRecording() {
        this.webDriver.elementWaitDisplayed(getCancelRecording);
        this.webDriver.elementClick(getCancelRecording);
        webDriver.sleep(3);
        this.webDriver.elementWaitDisplayed(buttonCancelRecording);
        this.webDriver.elementClick(buttonCancelRecording);
    }

    public void checkRecordingCancelled() {
        this.webDriver.elementWaitDisplayed(mensaje);
        assertTrue(this.webDriver.findElement(mensaje).getText().contains("Cancelaste tu grabación") ||
                this.webDriver.findElement(mensaje).getText().contains("Se ha cancelado la grabación programada"));
        this.webDriver.elementWaitDisplayed(getGrabar);
        assertTrue("Button 'Grabar' is not displayed",
                webDriver.findElement(getGrabar).isDisplayed());
        logger.info("Button 'Grabar' is displayed");
    }

    public void displayRecordButtonInBroadcasts() {
        this.webDriver.sleep(2);
        this.webDriver.elementWaitDisplayed(getArrowImgShowHiddenInfo);
        this.webDriver.elementClick(getArrowImgShowHiddenInfo);
        for (int cont = 1; cont < listEmisiones.size(); ) {
            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(getShowInfo));
            listEmisiones.get(cont).findElement(getShowInfo).click();

            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(getGrabar));
            assertTrue("Button 'Grabar' is not displayed ",
                    listEmisiones.get(cont).findElement(getGrabar).isDisplayed());
            logger.info("Button 'Grabar' is displayed");

            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(getSchedules));
            assertTrue("Schedule information is not displayed",
                    listEmisiones.get(cont).findElement(getSchedules).isDisplayed());
            logger.info(listEmisiones.get(cont).findElement(getSchedules).getText());

            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(scheduleTransmission));
            assertTrue("Transmission schedules is not displayed",
                    listEmisiones.get(cont).findElement(scheduleTransmission).isDisplayed());
            logger.info(listEmisiones.get(cont).findElement(scheduleTransmission).getText());
            this.webDriver.scrollPageDown();
            cont++;
        }
    }

    public void recordABroadcastFromBroadcasts() throws AWTException {
        this.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(10, 15);
        this.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.selectRandomChannelLive();
        this.accessEmissionsSection();
        this.webDriver.sleep(2);
        this.webDriver.elementWaitDisplayed(getArrowImgShowHiddenInfo);
        this.webDriver.elementClick(getArrowImgShowHiddenInfo);
        this.webDriver.elementWaitDisplayed(listEmisiones.get(1).findElement(getShowInfo));
        listEmisiones.get(1).findElement(getShowInfo).click();
        this.webDriver.elementWaitDisplayed(listEmisiones.get(1).findElement(getGrabar));
        listEmisiones.get(1).findElement(getGrabar).click();
    }

    public void viewEmissions() {
        this.webDriver.sleep(2);
        this.webDriver.elementWaitDisplayed(getArrowImgShowHiddenInfo);
        this.webDriver.elementClick(getArrowImgShowHiddenInfo);

        for (int cont = 0; cont < listEmisiones.size(); ) {
            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(getShowInfo));

            listEmisiones.get(cont).findElement(getShowInfo).click();
            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(getSchedules));
            assertTrue("Schedule information is not displayed",
                    listEmisiones.get(cont).findElement(getSchedules).isDisplayed());
            logger.info(listEmisiones.get(cont).findElement(getSchedules).getText());

            this.webDriver.elementWaitDisplayed(listEmisiones.get(cont).findElement(scheduleTransmission));
            assertTrue("Transmission schedules is not displayed",
                    listEmisiones.get(cont).findElement(scheduleTransmission).isDisplayed());
            logger.info(listEmisiones.get(cont).findElement(scheduleTransmission).getText());
            this.webDriver.scrollPageDown();
            cont++;
        }
    }

    public void viewAllSectionsTvGuide() {
        this.webDriver.elementWaitDisplayed(buttonOthers);
        this.webDriver.elementClick(buttonOthers);
        List<WebElement> dropdownRow = flowDropDownMenuActive.findElements(RowDropdownSections);
        for (WebElement sections : dropdownRow) {
            this.webDriver.elementWaitDisplayed(sections);
            this.webDriver.elementMoveTo(sections, 5);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            String sectionName = sections.getText();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            logger.info(sectionName);
        }
    }

    public void accessFiltersEpg(WebElement element) {
        this.webDriver.elementWaitDisplayed(element);
        this.webDriver.elementClick(element);
    }

    public void viewChannelsFavoritesEpg() {
        assertTrue("Favorite row group not available",
                rowgroupFavorites.isEnabled());
        List<WebElement> channelFavorites = rowgroupFavorites.findElements(getEpgChannel);
        assertTrue(channelFavorites.size() > 1);
        for (WebElement channelWrapper : channelFavorites) {
            this.webDriver.elementWaitDisplayed(channelWrapper);
            this.webDriver.elementMoveTo(channelWrapper, 5);
            assertTrue("Icon favorite is not displayed",
                    channelWrapper.findElement(btnEpgChannelsFavoriteActive).isDisplayed());
        }
    }

    public void viewMessageWithoutContentsSavedInFavorites() {
        this.webDriver.elementWaitDisplayed(messageNoResults);
        assertTrue("Favorite channels are displayed",
                noResults.isEnabled());
        assertTrue(messageNoResults.isDisplayed());
    }

    public void sectionIsDisplayed(String filter) {
        this.webDriver.elementWaitDisplayed(filterActive);

        assertEquals("Filter '" + filter + " ' not active",
                filterActive.getText(), filter);
        logger.info("Filter '" + filter + " ' active correcty");

        assertTrue("Favorite row group not available",
                rowgroupFavorites.isEnabled());
        List<WebElement> channelFavorites = rowgroupFavorites.findElements(getEpgChannel);
        assertTrue(channelFavorites.size() > 1);
        for (WebElement channelWrapper : channelFavorites) {
            this.webDriver.elementWaitDisplayed(channelWrapper);
            this.webDriver.elementMoveTo(channelWrapper, 5);
        }
    }

    public void accessDipOnFilter(WebElement filter) {
        this.accessFiltersEpg(filter);
        this.waitEpgProgramLiveLoad();
        int randomEpgChannel = UtilsRandom.getRandom(0, 5);
        this.scrollChannelsDownInTvGuide(randomEpgChannel);
        this.webDriver.elementMoveTo(getChannelsLive().get(randomEpgChannel), 3);
        getChannelsLive().get(randomEpgChannel).click();
    }

    public void recordIsDisplayed() {
        this.webDriver.elementWaitDisplayed(recordButton);
        webDriver.sleep(3);
        if (recordButton.getText().contains("GRABAR")) {
            logger.info("button 'GRABAR' is displayed");
        } else {
            this.webDriver.elementClick(recordButton);
            this.webDriver.elementWaitDisplayed(deleteRecording);
            this.webDriver.elementClick(deleteRecording);
            this.checkRecordingCancelled();
        }
    }

    public void accessOthersSectionEpg() {
        this.webDriver.elementWaitDisplayed(buttonOthers);
        this.webDriver.elementClick(buttonOthers);
        List<WebElement> dropdownRow = flowDropDownMenuActive.findElements(RowDropdownSections);
        Random random = new Random();
        int randomValue = random.nextInt(dropdownRow.size());
        WebElement elementRandom = dropdownRow.get(randomValue);
        this.webDriver.elementMoveTo(elementRandom, 2);
        this.webDriver.elementClick(elementRandom);
    }

    public void othersSectionIsDisplayed() {
        this.webDriver.elementWaitDisplayed(othersSectionActive);
        logger.info("Section '" + othersSectionActive.getText() + " ' active correcty");

        assertTrue("Favorite row group not available",
                rowgroupFavorites.isEnabled());
        List<WebElement> channelFavorites = rowgroupFavorites.findElements(getEpgChannel);
        assertTrue(channelFavorites.size() > 1);
        for (WebElement channelWrapper : channelFavorites) {
            this.webDriver.elementWaitDisplayed(channelWrapper);
            this.webDriver.elementMoveTo(channelWrapper, 5);
        }
    }

    public void accessOthersDipFilter() {
        this.accessFiltersEpg(buttonOthers);
        List<WebElement> dropdownRow = flowDropDownMenuActive.findElements(RowDropdownSections);
        Random random = new Random();
        int randomValue = random.nextInt(dropdownRow.size());
        WebElement elementRandom = dropdownRow.get(randomValue);
        this.webDriver.elementMoveTo(elementRandom, 5);
        this.webDriver.elementClick(elementRandom);
        this.waitEpgProgramLiveLoad();
        Random randomEpg = new Random();
        int randomValueEpg = randomEpg.nextInt(wrapperEpgChannelEpgProgramLive.size());
        this.scrollChannelsDownInTvGuide(randomValueEpg);
        this.webDriver.elementMoveTo(getChannelsLive().get(randomValueEpg), 3);
        getChannelsLive().get(randomValueEpg).click();
    }
}
