package flow.pageActions;

import flow.core.style.Styles;
import flow.pageObjects.PlayerFlow;
import flow.stepDefinitions.Hooks;
import flow.webdriverUtils.ExtendedWebDriver;
import org.assertj.core.api.SoftAssertions;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static flow.utils.networks.Networks.*;
import static flow.utils.networks.RequestTypes.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.*;

public class PlayerPageAction extends PlayerFlow {

    SoftAssertions softly = new SoftAssertions();

    String subtitle;

    public PlayerPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public Boolean waitNoSeeking() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());

        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
                Boolean seekingState;
                seekingState = (Boolean) webDriver.executeScript(jsCommand);
                assertFalse("Seeking state is true", seekingState);
                logger.info("Player is not seeking");

                return true;
            } catch (Throwable throwable) {
                //this.webDriver.refreshUrl();
                logger.info("Throwable");
                return false;
            }
        });
        return false;
    }

    public Boolean waitNoSeekingWithoutRefresh() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());

        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
                Boolean seekingState;
                seekingState = (Boolean) webDriver.executeScript(jsCommand);
                assertFalse("Seeking state is true", seekingState);
                logger.info("Player is not seeking");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
        return false;
    }

    public Double getCurrentTime() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetCurrentTime());
        Object currentTime = webDriver.executeScript(jsCommand);

        Double d = (double) 0;

        if (currentTime instanceof Double) {
            d = (Double) currentTime;
        }

        if (currentTime instanceof Long) {
            d = ((Long) currentTime).doubleValue();
        }
        logger.info("Player current playback time is: " + d);

        return d;
    }

    public void setCurrentTime(Double time) {
        String timeNew = String.valueOf(time);
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringSetCurrentTime() + timeNew, ";");
        playbackTime = getCurrentTime();

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                webDriver.executeScript(jsCommand);
                webDriver.sleep(this.timeSleep);
                waitNoSeeking();

                seekTime = getCurrentTime();
                assertFalse("seekTime equals currentTime", seekTime.equals(time));

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void setCurrentTimeForward(String time) {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringSetCurrentTimeForward() + time, ";");
        playbackTime = getCurrentTime();

        webDriver.executeScript(jsCommand);
        assertFalse(waitNoSeeking());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                seekTime = getCurrentTime();
                assertTrue("seekTime smaller than playbackTime", seekTime > playbackTime);

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void playerCurrentTimeForward() {
        playbackTime = getCurrentTime();
        this.mouseOverPlayer();
        this.webDriver.elementClick(this.getElementForwardButton());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                seekTime = getCurrentTime();
                assertTrue("seekTime smaller than playbackTime", seekTime > playbackTime);

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void setCurrentTimeRewind(String time) {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringSetCurrentTimeRewind() + time, ";");
        playbackTime = getCurrentTime();
        webDriver.executeScript(jsCommand);
        waitNoSeeking();
        webDriver.sleep(this.timeSleep);
        seekTime = getCurrentTime();
        assertTrue(seekTime < playbackTime);
    }

    public void playerCurrentTimeRewind() {
        playbackTime = getCurrentTime();
        this.mouseOverPlayer();
        this.webDriver.elementClick(this.getElementReplayButton());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                seekTime = getCurrentTime();
                assertTrue("playbackTime smaller than seekTime", seekTime < playbackTime);

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                this.mouseOverPlayer();
                this.webDriver.elementClick(this.getElementReplayButton());
                return false;
            }
        });
    }

    public void isPlaying() {
        assertFalse(waitNoSeeking());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTime();
                webDriver.sleep(this.timeSleep);

                Double newTime = getCurrentTime();

                logger.info("playbackTime: " + playbackTime);

                logger.info("newTime: " + newTime);

                assertTrue("newTime greater or equal to playbackTime",
                        newTime >= playbackTime);
                logger.info("Player is playing");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void playerIsPlaying() {
        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTime();
                webDriver.sleep(this.timeSleep);

                Double newTime = getCurrentTime();

                logger.info("playbackTime: " + playbackTime);

                logger.info("newTime: " + newTime);

                assertTrue("newTime greater or equal to playbackTime",
                        newTime >= playbackTime);
                logger.info("Player is playing");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public Double getCurrentTimeMusic() {
        String jsCommand = String.join("", "return " + this.getAudioPlayerStringGetCurrentTime());
        Object currentTime = webDriver.executeScript(jsCommand);

        Double d = (double) 0;

        if (currentTime instanceof Double) {
            d = (Double) currentTime;
        }

        if (currentTime instanceof Long) {
            d = ((Long) currentTime).doubleValue();
        }
        logger.info("Player current playback time is: " + d);

        return d;
    }

    public void isPlayingRadio() {
        assertFalse(waitNoSeekingRadios());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTimeMusic();
                webDriver.sleep(this.timeSleep);

                Double newTime = getCurrentTimeMusic();

                assertTrue("newTime greater or equal to playbackTime",
                        newTime >= playbackTime);
                logger.info("Music player is playing");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void playerMusicIsPaused() {
        assertFalse(waitNoSeekingRadios());

        playbackTime = getCurrentTimeMusic();
        webDriver.sleep(this.timeSleep);

        Double newTime = getCurrentTimeMusic();

        assertTrue("newTime equal to playbackTime",
                newTime.equals(playbackTime));
        logger.info("Music player is paused");
    }

    public void isPlayingMusic() {
        assertFalse(waitNoSeekingMusic());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTimeMusic();
                webDriver.sleep(this.timeSleep);

                Double newTime = getCurrentTimeMusic();

                assertTrue("newTime greater or equal to playbackTime",
                        newTime >= playbackTime);
                logger.info("Music player is playing");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public Boolean waitNoSeekingRadios() {
        String jsCommand = String.join("", "return " + this.getAudioPlayerStringGetStateSeeking());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                Boolean seekingState;
                seekingState = (Boolean) webDriver.executeScript(jsCommand);
                assertFalse("Seeking state is true", seekingState);
                logger.info("Player is not seeking");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
        return false;
    }

    public Boolean waitNoSeekingMusic() {
        String jsCommand = String.join("", "return " + this.getAudioPlayerStringGetStateSeeking());

        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                Boolean seekingState;
                seekingState = (Boolean) webDriver.executeScript(jsCommand);
                assertFalse("Seeking state is true", seekingState);
                logger.info("Player is not seeking");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
        return false;
    }

    public void waitPlayerNotLoading() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.numberOfElementsToBe(
                this.getLoadingPlayer(), 0));
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        logger.info("Player is not loading");
    }

    public void isPaused() {
        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTime();
                webDriver.sleep(this.timeSleep);

                Double newTime = getCurrentTime();

                assertEquals(newTime, playbackTime);
                logger.info("Player is paused");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public Double setVolumeLower(Double volume) {
        String volumeNew = String.valueOf(volume);
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringSetVolumeLower() + volumeNew, ";");
        Object currentVolume = webDriver.executeScript(jsCommand);

        Double d = (double) 0;

        if (currentVolume instanceof Double)
            d = (Double) currentVolume;

        if (currentVolume instanceof Long)
            d = ((Long) currentVolume).doubleValue();

        logger.info("Player audio volume is: " + d);
        return d;
    }

    public Double setVolumeHigher(Double volume) {
        String volumeNew = String.valueOf(volume);
        String jsCommand = String.join("", this.getVideoPlayerStringSetVolumeHigher() + volumeNew, ";");
        Object currentVolume = webDriver.executeScript(jsCommand);

        Double d = (double) 0;

        if (currentVolume instanceof Double)
            d = (Double) currentVolume;

        if (currentVolume instanceof Long)
            d = ((Long) currentVolume).doubleValue();

        logger.info("Player audio volume is: " + d);
        return d;
    }

    public void setStatePause() {
        String jsCommand = String.join("", this.getVideoPlayerStringSetStatePause());
        webDriver.executeScript(jsCommand);
        waitNoSeeking();
        assertTrue(getStatePaused());
        logger.info("Player is paused");
    }

    public Boolean getStatePaused() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStatePaused());
        return (Boolean) webDriver.executeScript(jsCommand);
    }

    public void setStatePlay() {
        String jsCommand = String.join("", this.getVideoPlayerStringSetStatePlay());
        webDriver.executeScript(jsCommand);
        waitNoSeeking();
        assertFalse(getStatePaused());
        logger.info("Player is playing");
    }

    public Boolean waitPlaybackStart() {
        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                playbackTime = getCurrentTime();

                assertTrue("playbackTime not greater than 0.1",
                        playbackTime > 0.1);
                logger.info("Player: playback started");

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
        return true;
    }

    public Double getContentDuration() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetContentDuration());
        Double duration;
        Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
            try {
                assertNotNull("Content duration is null", webDriver.executeScript(jsCommand));

                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
        duration = Double.valueOf(webDriver.executeScript(jsCommand).toString());

        logger.info("Player: content duration: " + duration);
        return duration;
    }

    public void waitPlayerLoad() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        // Wait for player class
        wait.until(ExpectedConditions.presenceOfElementLocated(this.getVideoPlayerClass()));

        logger.info("Player is loaded");
    }

    public void waitPlaybackAfterChannelChange() {
        // Wait for loading spinner to show
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getLoading()));
        // Wait for loading spinner to hide
        wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
        waitNoSeeking();
    }

    public void waitPlayerLoadAndPlayback() {
        waitPlayerLoad();
        waitNoSeeking();
        assertTrue(waitPlaybackStart());
    }

    public void checkPlaybackFullContent() {
        double contentDuration = getContentDuration();
        double newTime = getCurrentTime();

        while (this.webDriver.getCurrentUrl().contains("/play")) {
            waitNoSeeking();

            newTime = getCurrentTime();

            webDriver.sleep(5);
        }
        assertTrue("Content not played entirely", newTime > contentDuration - 20);
    }

    public void checkPlaybackFullContentLive() {
        double fourHours = 1.44e+7;
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        logger.info("startTime: " + startTime);
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());
        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        while (this.webDriver.getCurrentUrl().contains("/vivo") && (currentTime - startTime) < fourHours) {
            Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
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
            webDriver.sleep(5);
            currentTime = System.currentTimeMillis();
            logger.info("Elapsed time: " + ((currentTime - startTime) / 1000) / 60 + " minutes");
        }
    }

    public void checkPlaybackFullContentReverseEPG() {
        double oneHour = 3.6e+6;
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        logger.info("startTime: " + startTime);
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());
        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        while (currentTime - startTime < oneHour) {
            Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
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
            webDriver.sleep(5);
            currentTime = System.currentTimeMillis();
            logger.info("Elapsed time: " + ((currentTime - startTime) / 1000) / 60 + " minutes");
        }
    }

    public void checkPlaybackFullContentCatchup() {
        double oneHour = 1.8e+6;
        long startTime = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        logger.info("startTime: " + startTime);
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());
        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        while (currentTime - startTime < oneHour) {
            Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
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
            webDriver.sleep(5);
            currentTime = System.currentTimeMillis();
            logger.info("Elapsed time: " + ((currentTime - startTime) / 1000) / 60 + " minutes");
        }
    }

    public void checkPlaybackFullContentRecordings() {
        var isPausedButton = false;
        String jsCommand = String.join("", "return " + this.getVideoPlayerStringGetStateSeeking());
        // Wait for loading spinner to hide
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        while (!isPausedButton) {
            Awaitility.await().atMost(Duration.ofSeconds(waitTime)).until(() -> {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(this.getLoading()));
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
            webDriver.sleep(5);
            this.mouseOverPlayer();
            try {
                isPausedButton = pausedButton.isDisplayed();
            } catch (Throwable throwable) {
                isPausedButton = false;
            }
        }
    }

    public void playerSetTimeStart() {
        setCurrentTime((double) 0);
        waitNoSeeking();
    }

    public void playerSetTimeHalf() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                // Get content total duration
                Double duration = getContentDuration();
                // Get content half duration
                Double halfDuration = duration / 2;

                // Seek to halfDuration
                setCurrentTimeForward(halfDuration.toString());
                webDriver.sleep(this.timeSleep);
                waitNoSeeking();

                // Check that current currenttime is saved
                Double currentTime = getCurrentTime();

                assertNotSame("currentTime and halfDuration are same", currentTime, halfDuration);
                return true;
            } catch (Throwable throwable) {
                logger.info("Throwable");
                return false;
            }
        });
    }

    public void playerSeekContentBingeWatching() {
        Double contentDuration = getContentDuration();
        Double contentBingeWatching = contentDuration - 15;

        setCurrentTimeForward(String.valueOf(contentBingeWatching));
        waitNoSeeking();
    }

    public void playerSeekContentTime(double seconds, String directionOfTimeMoved) {
        double time = 0.0;
        Double currentTime = getCurrentTime();
        if (directionOfTimeMoved.equals("forward")) {
            time = currentTime + seconds;
        } else if (directionOfTimeMoved.equals("rewind")) {
            time = currentTime - seconds;
        }
        setCurrentTime(time);
    }

    public void playerSeekContentTimeRewind(double minutes) {
        Double currentTime = getCurrentTime();
        Double rewindTime = currentTime - minutes;

        setCurrentTimeRewind(String.valueOf(rewindTime));
        waitNoSeeking();
    }

    public void actionMouseOverPlayer() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                logger.info("Mouse over back button");
                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofMillis(500));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

//                this.webDriver.elementWaitDisplayed(getElementBackButton());

                webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
                this.webDriver.elementMoveTo(this.getElementPlaybackButton(), 1);

                wait.until(ExpectedConditions.visibilityOf(
                        this.webDriver.findElement(this.getElementPlaybackButton())));

                logger.info("Player mouse over action performed");
                webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void mouseOverPlayer() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                logger.info("mouse over player container");

                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(1));
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

                this.webDriver.waitUntil(1, wd -> playerContainer.isDisplayed());

                this.webDriver.mouseOver(playerContainer);

                wait.until(ExpectedConditions.visibilityOf(
                        this.webDriver.findElement(this.getElementPlaybackButton())));
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public String playerSupportsPip() {
        String jsCommand = String.join("", "return " + this.getVideoPlayerUiStringPipSupported());
        return String.valueOf(webDriver.executeScript(jsCommand));
    }

    public void tvLivePlayerIsDisplayed() {
        this.playerIsDisplayed();

        // Check getElementNowButton
        this.mouseOverPlayer();
        assertTrue("Now button not displayed",
                this.webDriver.findElement(this.getElementNowButton()).isDisplayed());
        logger.info("Now button is displayed");

        // Check getElementTimeDisplayContainer
        this.mouseOverPlayer();

        assertTrue("Time display container not displayed",
                this.webDriver.findElement(this.getElementWhishTime()).isDisplayed());
        logger.info("Whish Time is displayed");

        logger.info("Tv Player elements are displayed");
    }

    public void tvCatchupPlayerIsDisplayed() {
        this.playerIsDisplayed();

        // Check getElementTimeDisplayContainer
        this.mouseOverPlayer();
        assertTrue("Time display container not displayed",
                this.webDriver.findElement(this.getElementWhishTime()).isDisplayed());
        logger.info("Whish Time is displayed");

        logger.info("Tv Player elements are displayed");
    }

    public void playerIsDisplayed() {
        // Check getElementVolumeButton
        this.mouseOverPlayer();
        this.webDriver.areElementsDisplayed(volIcon,
                this.webDriver.findElement(this.getElementVolumeSlider()),
                this.webDriver.findElement(this.getElementReplayButton()),
                this.webDriver.findElement(this.getElementPlaybackButton()),
                this.webDriver.findElement(this.getElementForwardButton()),
                progressBarContainer,
                fullScreenBtn,
                this.webDriver.findElement(this.getElementBackButton())
        );

        // Check getElementPipButton
        if (this.playerSupportsPip().equals("true")) {
            this.mouseOverPlayer();

            assertTrue("Picture In Picture button not displayed",
                    this.webDriver.findElement(this.getElementPipButton()).isDisplayed());
            logger.info("Picture In Picture");
        }

        // Check getElementSettingsButton
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        if (this.webDriver.findElements(this.getElementLangButton()).size() > 0) {
            this.mouseOverPlayer();

            assertTrue("Settings button not displayed",
                    this.webDriver.findElement(this.getElementSettingsButton()).isDisplayed());
            logger.info("Settings button");
        }
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        logger.info("Player elements are displayed");
    }

    public void basicCheckPlayerIsDisplayed() {
        this.mouseOverPlayer();
        this.webDriver.isElementDisplayed(this.webDriver.findElement(this.getElementPlaybackButton()));
    }

    public void playerIsNotDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                this.getElementPlaybackButton()));
        assertFalse("Mute button is displayed",
                this.webDriver.findElement(this.getElementVolumeButton()).isDisplayed());
        assertFalse("Volume slider is displayed",
                this.webDriver.findElement(this.getElementVolumeSlider()).isDisplayed());
        assertFalse("Rewind button is displayed",
                this.webDriver.findElement(this.getElementReplayButton()).isDisplayed());
        assertFalse("Playback button is displayed",
                this.webDriver.findElement(this.getElementPlaybackButton()).isDisplayed());
        assertFalse("Forward button is displayed",
                this.webDriver.findElement(this.getElementForwardButton()).isDisplayed());
        assertFalse("Progress bar is displayed",
                this.webDriver.findElement(this.getElementPlayProgressSlider()).isDisplayed());
        if (this.playerSupportsPip().equals("true")) {
            assertFalse("Picture In Picture button is displayed",
                    this.webDriver.findElement(this.getElementPipButton()).isDisplayed());
        }
        assertFalse("Settings button is displayed",
                this.webDriver.findElement(this.getElementSettingsButton()).isDisplayed());
        assertFalse("Fullscreen button is displayed",
                this.webDriver.findElement(this.getElementFullscreenButton()).isDisplayed());
        assertFalse("Back button is displayed",
                this.webDriver.findElement(this.getElementBackButton()).isDisplayed());
        logger.info("Player elements are hidden");
    }

    public void playerPausedInfoIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(this.getPausedInfo()));
        assertTrue(this.webDriver.findElement(this.getPausedInfo()).isDisplayed());
        assertTrue(this.webDriver.findElement(this.getPausedInfoNowWatching()).isDisplayed());
        assertTrue(this.webDriver.findElement(this.getPausedInfoTitle()).isDisplayed());
        wait.until(ExpectedConditions.presenceOfElementLocated(this.getPausedInfoReleasedYear()));
        assertTrue(this.webDriver.findElement(this.getPausedInfoAttributesTime()).isDisplayed());
        assertTrue(this.webDriver.findElement(this.getPausedInfoAttributesAge()).isDisplayed());
        assertTrue(this.webDriver.findElement(this.getPausedInfoDescription()).isDisplayed());
        logger.info("Elements are displayed");
        assertTrue(this.webDriver.findElement(this.getPausedInfoTitle()).isDisplayed());
        logger.info("Title info: " + this.webDriver.findElement(this.getPausedInfoTitle()).getText());
    }

    public void checkPlayerFullScreenIsActive() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                this.mouseOverPlayer();
                this.webDriver.waitUntil(5, wd -> closeFullScreen.isDisplayed());
                this.mouseOverPlayer();
                assertTrue(closeFullScreen.isDisplayed());
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                this.mouseOverPlayer();
                return false;
            }
        });
    }

    public void checkPlayerFullScreen() {
        Boolean fullScreen = (Boolean) webDriver.executeScript("return document.fullscreen;");
        assertTrue("Video playback is not in Full screen", fullScreen);
    }

    public void checkPlayerIsNotInFullScreen() {
        Boolean fullScreen = (Boolean) webDriver.executeScript("return document.fullscreen;");
        assertFalse("Video playback is in Full screen", fullScreen);
    }


    public void checkPlayerFullScreenIsInactive() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                this.mouseOverPlayer();
                this.webDriver.waitUntil(5, wd -> openFullScreen.isDisplayed());
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                this.mouseOverPlayer();
                return false;
            }
        });
    }

    public void setPlayerFullScreenButton() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                this.mouseOverPlayer();
                this.webDriver.waitUntil(10, wd -> fullScreen.isDisplayed());
                this.webDriver.elementClick(fullScreen);
                logger.info("Player is in fullscreen mode");
                return true;
            } catch (Throwable throwable) {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void unsetPlayerFullScreenButton() {
        this.mouseOverPlayer();
        this.webDriver.waitUntil(10, wd -> fullScreen.isDisplayed());
        this.webDriver.elementClick(fullScreen);
        logger.info("Player is not in fullscreen mode");
    }

    public void setPlayerFullScreenDoubleClick() {
        this.webDriver.elementDoubleClick(this.getVideoPlayerClass());
        this.webDriver.elementMoveTo(this.getElementFullscreenButton(), 10);
        logger.info("Player is in fullscreen mode");
    }

    public void unsetPlayerFullScreenDoubleClick() {
        this.webDriver.elementDoubleClick(this.getVideoPlayerClass());
        this.webDriver.elementMoveTo(this.getElementFullscreenButton(), 10);
        this.webDriver.elementDoubleClick(this.getVideoPlayerClass());
        logger.info("Player is not in fullscreen mode");
    }

    public void actionPlaybackPause() {
        this.mouseOverPlayer();
        this.clickOnPauseBtn();
        this.webDriver.mouseOver(playerContainer);
        assertTrue("Pause Button element not displayed", playBtn.isDisplayed());
        logger.info("Playback paused");
    }

    private void clickOnPauseBtn() {
        this.webDriver.mouseOver(playerContainer);
        this.webDriver.clickOn(pauseBtn);
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                this.webDriver.waitUntil(1, wd -> playBtn.isDisplayed());
                return true;
            } catch (Throwable throwable) {
                logger.info("playback button is not displayed");
                this.webDriver.mouseOver(playerContainer);
                this.webDriver.clickOn(pauseBtn);
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void clickOnPlaybackPause() {
        this.webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
        assertTrue("Pause Button element not displayed",
                this.webDriver.findElement(this.getElementPauseButton()).isDisplayed());
        this.webDriver.sleep(2);
        this.webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
        this.webDriver.elementClick(this.getElementPauseButton());
        assertTrue("Pause Button element not displayed",
                this.webDriver.findElement(this.getElementPlayButton()).isDisplayed());
        logger.info("Playback paused");
    }

    public void restartTimeOfPlayer() {
        setCurrentTime((double) 0);
    }

    public void actionPlaybackPlay() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(playBtn);
        this.mouseOverPlayer();
        assertTrue("Pause Button element not displayed", pauseBtn.isDisplayed());
        logger.info("Playback resumed");
    }

    public void bingeWatchingIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.getBingeWatching()));
        assertTrue("Pause Button element not displayed",
                this.webDriver.findElement(this.getBingeWatching()).isDisplayed());
        logger.info("page Is Displayed BingeWatching");
        assertTrue("Element Title not displayed",
                this.webDriver.findElement(this.getBingeWatchingTittle()).isDisplayed());
        logger.info("Element Title is displayed");
        webDriver.waitUntil(20, wd -> webDriver.findElement(this.getBingeWatchingLogo()).isDisplayed());
        assertTrue("Element Cover Image not displayed",
                webDriver.findElement(this.getBingeWatchingLogo()).isDisplayed());
        logger.info("Element cover image is displayed");
        assertTrue("Element time duration not displayed", this.webDriver.findElement(
                this.getBingeWatchingDueTimeHour()).isDisplayed());
        logger.info("Element time duration is displayed : " +
                this.webDriver.findElement(this.getBingeWatchingDueTimeHour()).getText());
        assertTrue("Element description text not displayed",
                this.webDriver.findElement(this.getBingeWatchingDescription()).isDisplayed());
        logger.info("Element description text is displayed: " +
                this.webDriver.findElement(this.getBingeWatchingDescription()).getText());
        assertTrue("Element cancel button not displayed",
                this.webDriver.findElement(this.getCancelButton()).isDisplayed());
    }

    public void actionProgramRestart() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(restartButton);
        logger.info("Restart program");
    }

    public void actionProgramNow() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.mouseOverPlayer();

        assertTrue("Element Now button not displayed",
                this.webDriver.findElement(this.getElementRestartButton()).isDisplayed());
        webDriver.clickOn(webDriver.findElement(this.getElementNowButton()));
        logger.info("Now program");
    }

    public void checkButtonProgramNowDisable() {

        this.mouseOverPlayer();
        assertTrue("Element Now button is enabled",
                this.webDriver.findElement(this.getElementNowButtonDisabled()).isDisplayed());
        logger.info("Button Now program is disabled");
    }

    public void openLastChannels() {
        this.webDriver.findElement(getElementLastChannels()).click();
        logger.info("'Últimos canales' is displayed and selected");
    }

    public List<WebElement> getLastChannelsLocator() {
        String lastChannelLocator = ".//div[@class='channelNumber']";
        By locatorBy = By.xpath(lastChannelLocator);
        return this.webDriver.findElements(locatorBy);
    }

    public void checkChannelLastChannels() {
        List<WebElement> channels = getLastChannelsLocator();
        assertEquals("Last channels is not displayed ", 2, channels.size());
        this.webDriver.elementMoveTo(this.webDriver.findElement(getElementChannelLastChannels()), 2);
        this.webDriver.findElement(getElementChannelLastChannels()).click();
        logger.info("Select channel from 'Últimos canales'");
    }

    public String getPlayerChannelNumber() {
        Awaitility.await().atMost(waitTime, SECONDS).until(() -> {
            try {
                this.mouseOverPlayer();

                String channelNumber = this.webDriver.findElement(getVopLogo()).getText();
                assertFalse(channelNumber.isEmpty());

                logger.info("channel number from player is " + channelNumber);
                return true;
            } catch (Throwable throwable) {
                logger.info("channel number from player is " + this.webDriver.findElement(getVopLogo()).getText());
                throwable.printStackTrace();
                return false;
            }
        });
        return this.webDriver.findElement(getVopLogo()).getText();
    }

    public String getPlayerProgressBarCurrentTime() {
        String textPlayerPoint = webDriver.findElement(elementPlayerProgressBarTime).getText();
        return textPlayerPoint;
    }

    public By getElementPlayerProgressBar() {
        return elementPlayerProgressBar;
    }

    public void isPlayingProgressBar() {
        Awaitility.await().atMost(10, SECONDS).until(() -> {
            try {
                this.mouseOverPlayer();
                String textPlayerPoint = getPlayerProgressBarCurrentTime();
                assertNotNull(textPlayerPoint);
                webDriver.sleep(this.timeSleep);
                assertNotSame("Progress bar not changing", textPlayerPoint, getPlayerProgressBarCurrentTime());
                logger.info("Player is playing");

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public int getPixelsToMove(WebElement Slider, float Amount, float SliderMax, float SliderMin) {
        int pixels = 0;
        float tempPixels = Slider.getSize().width;
        tempPixels = tempPixels / (SliderMax - SliderMin);
        tempPixels = tempPixels * (Amount - SliderMin);
        pixels = (int) tempPixels;
        return pixels;
    }

    public void playerProgressBarSlideTo(int seconds, String directionOfTimeMoved) {
        this.playerSeekContentTime(seconds, directionOfTimeMoved);
    }

    public void waitTimeBingeWatchingNotExist() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.webDriver.findElements(getElementTimeBingeWatching());
        logger.info("'Binge Watching' is diplayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        new WebDriverWait(webDriver, Duration.ofSeconds(20)).until(ExpectedConditions.invisibilityOfElementLocated(getElementTimeBingeWatching()));
        logger.info("'Binge Watching' is not diplayed");
    }

    public void nextEpisodeBingeWatching() {
        this.webDriver.findElements(getElementTimeBingeWatching());
        logger.info("'Binge Watching' is diplayed");
        this.webDriver.elementWaitToBeClickable(getElementPlayNowBingeWatching());
        this.webDriver.elementClick(getElementPlayNowBingeWatching());
        logger.info("Advance next episode from 'Play Now' on 'Binge Watching'");
    }

    public void checkElementsMusicPlayer() {
        this.webDriver.elementWaitDisplayed(getElementImgMusicPlayer());
        logger.info("Music player image is displayed");
        this.webDriver.elementWaitDisplayed(getElementTitleMusicPlayer());
        logger.info("Music player title is displayed");
        this.webDriver.elementWaitDisplayed(elementPauseMusicPlayer);
        logger.info("Button 'pause' music player is displayed");
        this.webDriver.elementWaitDisplayed(getElementVolumeMusicPlayer());
        logger.info("Volume music player is displayed");
        this.webDriver.elementWaitDisplayed(getElementCloseMusicPlayer());
        logger.info("Button 'close' music player is displayed");
        this.webDriver.elementWaitDisplayed(getElementSoundBarsMusicPlayer());
        logger.info("Music player sound bars is displayed");
    }

    public void clickPauseMusicPlayer() {
        webDriver.elementClick(elementPauseMusicPlayer);
    }

    public void waitPlayMusicPlayerIsDisplayed() {
        webDriver.waitUntil(5, wd -> elementPlayMusicPlayer.isDisplayed());
    }

    public void clickBackButton() {
        webDriver.sleep(1);
        webDriver.elementClick(backButton);
    }

    public void clickEpgProgramLive() {
        webDriver.elementClick(this.getElementEgpProgramLive());
    }

    public void clickEpgRecordProgram() {
        webDriver.elementWaitDisplayed(this.getElementEpgRecordProgram());
        webDriver.elementClick(this.getElementEpgRecordProgram());
    }

    public void minimizarBrowser() {
        webDriver.setBrowserMinimize();
        webDriver.sleep(2);
    }

    public void maximizarBrowser() {
        webDriver.setBrowserMaximize();
    }

    public void clickMoreDetailsButton() {
        webDriver.clickOn(webDriver.findElement(this.getMoreDetailsButton()));
    }

    public void clickOnCancelBingeWatchingButton() {
        this.webDriver.waitUntil(10, wd -> bingeWatchingCancelBtnText.isDisplayed());
        logger.info("Cancel button in Binge Watching is displayed");
        this.webDriver.clickOn(bingeWatchingButtons.get(0));
    }

    public void mouseOverSkipIntro() {
        this.webDriver.mouseOver(skipIntroButton);
    }

    public void mouseOverSkipResume() {
        this.webDriver.waitUntil(10, wd -> skipResumeButton.isDisplayed());
        this.webDriver.mouseOver(skipResumeButton);
    }

    public void clickOnSkipIntro() {
        logger.info("Click on skip intro");
        this.webDriver.waitUntil(15, wd -> skipIntroButton.isDisplayed());
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.webDriver.elementClick(skipIntroButton);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.webDriver.sleep(2);
    }

    public void clickOnSkipResume() {
        logger.info("Click on skip resume");
        this.webDriver.waitUntil(15, wd -> skipResumeButton.isDisplayed());
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.webDriver.elementClick(skipResumeButton);
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.webDriver.sleep(2);
    }

    public void waitSkipButtonIsDisplayed() {
        this.webDriver.waitUntil(20, wd -> skipIntroButton.isDisplayed());
    }

    public void waitSkipResumeButtonIsDisplayed() {
        this.webDriver.waitUntil(20, wd -> skipResumeButton.isDisplayed());
    }

    public void skipIntroButtonIsDisplayed() {
        assertTrue("Skip Intro button not displayed",
                skipIntroButton.isDisplayed());
    }

    public void skipResumeButtonIsDisplayed() {
        assertTrue("Skip Resume button not displayed",
                skipResumeButton.isDisplayed());
    }

    public void skipIntroButtonIsNotDisplayed() {
        this.webDriver.waitForElementNotVisible(skipIntroButton, 500);
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            skipIntroButton.isDisplayed();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            fail();
        } catch (NoSuchElementException noSuchElementException) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertTrue("Skip Intro element is not displayed", true);
        }
    }

    public void introIsSkiped(double currentTime, double newTime) {
        assertTrue("Intro is skipped", currentTime < newTime);
    }

    public void waitForSkipIntroIsNotVisible() {
        try {
            this.webDriver.sleep(11);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.skipIntroButton.isDisplayed();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (NoSuchElementException noSuchElementException) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertTrue("Skip Intro element is not displayed", true);
        }
    }

    public void waitForSkipResumeIsNotVisible() {
        try {
            this.webDriver.sleep(15);
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.skipResumeButton.isDisplayed();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertFalse("Skip Resume element is not displayed", skipResumeButton.isDisplayed());
            logger.info("Skip Resume is not displayed");
        } catch (NoSuchElementException noSuchElementException) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertTrue("Skip Resume element is not displayed", true);
            logger.info("Skip Resume is not displayed");
        }
    }

    public void skipResumeIsNotVisible() {
        try {
            this.mouseOverPlayer();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            this.skipResumeButton.isDisplayed();
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertFalse("Skip Resume element is not displayed", skipResumeButton.isDisplayed());
            logger.info("Skip Resume is not displayed");
        } catch (NoSuchElementException noSuchElementException) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            assertTrue("Skip Resume element is not displayed", true);
        }
    }

    public void styleOfPlayerElementsAreDisplayed() {
        var skipIntroBtnName = "Skip Intro Button";
        this.checkStyle(skipIntroBtnName, skipIntroButton, Hooks.props.playerSkipIntroBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(skipIntroBtnName, skipIntroButton, Hooks.props.playerSkipIntroBtnWidth(), Styles.WIDTH);
        this.checkStyle(skipIntroBtnName, skipIntroButton, Hooks.props.playerSkipIntroBtnHeight(), Styles.HEIGHT);

        var skipIntroLblName = "Skip Intro Label";
        this.checkStyle(skipIntroLblName, skipIntroButtonLabel, Hooks.props.playerSkipIntroTextColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(skipIntroLblName, skipIntroButtonLabel, Hooks.props.playerSkipIntroTextWidth(), Styles.WIDTH);
        this.checkStyle(skipIntroLblName, skipIntroButtonLabel, Hooks.props.playerSkipIntroTextHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var playbackBtn = this.webDriver.findElement(this.getElementPlaybackButton());

        this.webDriver.clickOn(playbackBtn);

        playbackBtn = this.webDriver.findElement(this.getElementPlaybackButton());

        var playbackName = "Playback button";
        this.checkStyle(playbackName, playbackBtn, Hooks.props.playerPlaybackBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(playbackName, playbackBtn, Hooks.props.playerPlaybackBtnWidth(), Styles.WIDTH);
        this.checkStyle(playbackName, playbackBtn, Hooks.props.playerPlaybackBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var replayBtn = this.webDriver.findElement(this.getElementReplayButton());

        var replayBtnName = "Replay button";
        this.checkStyle(replayBtnName, replayBtn, Hooks.props.playerReplayBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(replayBtnName, replayBtn, Hooks.props.playerReplayBtnWidth(), Styles.WIDTH);
        this.checkStyle(replayBtnName, replayBtn, Hooks.props.playerReplayBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var forwardBtn = this.webDriver.findElement(this.getElementForwardButton());

        var forwardBtnName = "Forward button";
        this.checkStyle(forwardBtnName, forwardBtn, Hooks.props.playerForwardBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(forwardBtnName, forwardBtn, Hooks.props.playerForwardBtnWidth(), Styles.WIDTH);
        this.checkStyle(forwardBtnName, forwardBtn, Hooks.props.playerForwardBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));

        var backArrowBtnName = "Back arrow button";
        this.checkStyle(backArrowBtnName, backArrowButton, Hooks.props.playerArrowBackBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(backArrowBtnName, backArrowButton, Hooks.props.playerArrowBackBtnWidth(), Styles.WIDTH);
        this.checkStyle(backArrowBtnName, backArrowButton, Hooks.props.playerArrowBackBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var playerBackBtn = this.webDriver.findElement(this.getElementBackButton());

        var backBtnName = "Back button";
        this.checkStyle(backBtnName, playerBackBtn, Hooks.props.playerBackBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(backBtnName, playerBackBtn, Hooks.props.playerBackBtnWidth(), Styles.WIDTH);
        this.checkStyle(backBtnName, playerBackBtn, Hooks.props.playerBackBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var playerFullScreenBtn = this.webDriver.findElement(this.getElementFullscreenButton());

        var playerFullScreenBtnName = "Full screen button";
        this.checkStyle(playerFullScreenBtnName, playerFullScreenBtn, Hooks.props.playerFullScreenBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(playerFullScreenBtnName, playerFullScreenBtn, Hooks.props.playerFullScreenBtnWidth(), Styles.WIDTH);
        this.checkStyle(playerFullScreenBtnName, playerFullScreenBtn, Hooks.props.playerFullScreenBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var pipBtn = this.webDriver.findElement(this.getElementPipButton());

        var pipBtnName = "Pip button";
        this.checkStyle(pipBtnName, pipBtn, Hooks.props.playerPipBtnColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(pipBtnName, pipBtn, Hooks.props.playerPipBtnWidth(), Styles.WIDTH);
        this.checkStyle(pipBtnName, pipBtn, Hooks.props.playerPipBtnHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var progressBar = this.webDriver.findElement(this.getElementPlayerProgressBar());

        var progressBarName = "Progress bar";
        this.checkStyle(progressBarName, progressBar, Hooks.props.playerProgressBarColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(progressBarName, progressBar, Hooks.props.playerProgressBarWidth(), Styles.WIDTH);
        this.checkStyle(progressBarName, progressBar, Hooks.props.playerProgressBarHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));
        var volumenIcon = this.webDriver.findElement(this.getElementVolumeButton());

        var volumenName = "Volumen icon";
        this.checkStyle(volumenName, volumenIcon, Hooks.props.playerVolumenIconColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(volumenName, volumenIcon, Hooks.props.playerVolumenIconWidth(), Styles.WIDTH);
        this.checkStyle(volumenName, volumenIcon, Hooks.props.playerVolumenIconHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));

        var wishTimeLeftName = "Wish time left";
        this.checkStyle(wishTimeLeftName, wishTimeLeft, Hooks.props.playerWishTimeLeftColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(wishTimeLeftName, wishTimeLeft, Hooks.props.playerWishTimeLeftWidth(), Styles.WIDTH);
        this.checkStyle(wishTimeLeftName, wishTimeLeft, Hooks.props.playerWishTimeLeftHeight(), Styles.HEIGHT);

        this.webDriver.mouseOver(this.webDriver.findElement(this.getVideoPlayer()));

        var wishTimeRightName = "Wish time right";
        this.checkStyle(wishTimeRightName, wishTimeRight, Hooks.props.playerWishTimeRightColor(), Styles.BACKGROUNDCOLOR);
        this.checkStyle(wishTimeRightName, wishTimeRight, Hooks.props.playerWishTimeRightWidth(), Styles.WIDTH);
        this.checkStyle(wishTimeRightName, wishTimeRight, Hooks.props.playerWishTimeRightHeight(), Styles.HEIGHT);

        softly.assertAll();

        logger.info("Styles of elements are visualized");
    }

    private void checkStyle(String webElementName, WebElement webElement, String color, Styles styles) {
        softly.assertThat(this.webDriver.getCssValue(webElement, styles))
                .as(webElementName + " " + styles + " style is not equal")
                .isEqualTo(color);
    }

    public void removeRecording() {
        this.webDriver.elementWaitDisplayed(myContents);
        this.webDriver.elementClick(myContents);
        this.webDriver.elementClick(getRecordings);
        this.webDriver.elementWaitDisplayed(getItemWrapperBy);
        this.webDriver.mouseMoveElement(getItemWrapperBy);
        this.webDriver.elementClick(getItemWrapperBy);
        this.webDriver.elementClick(buttonRemove);
        this.webDriver.elementWaitDisplayed(buttonDeleteRecording);
        this.webDriver.elementClick(buttonDeleteRecording);
    }

    public void contentNotDisplayedInRecordingsSection() {
        assertTrue(elementRecordingEmpetyState.isDisplayed());
    }

    public void checkNextEpisodeIsNotDisplayed() {
        assertFalse(this.webDriver.isElementDisplayed(nextEpisode));
    }

    public void clickOnNextEpisode() {
        webDriver.waitUntil(10, wd -> nextEpisode.isDisplayed());
        webDriver.clickOn(nextEpisode);
    }

    public void clickOnPausedButton() {
        this.webDriver.mouseOver(webDriver.findElement(this.getElementBackButton()));
        this.webDriver.waitUntil(10, wd -> pausedButton.isDisplayed());
        this.webDriver.elementClick(pausedButton);
    }

    public void clickOnPlayButton() {
        Awaitility.await().atMost(50, SECONDS).until(() -> {
            try {
                this.webDriver.mouseOver(playerContainer);
                this.webDriver.waitUntil(20, wd -> playButton.isDisplayed());
                this.webDriver.elementClick(playButton);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkTitleOfNextEpisode() {
        this.webDriver.mouseOver((playerContainer));
        this.webDriver.waitUntil(30, wd -> playButton.isDisplayed());
        this.webDriver.mouseOver((playerContainer));
        this.webDriver.clickOn(playButton);
        this.webDriver.waitUntil(30, wd -> serieTitle.isDisplayed());
        logger.info("serie current title: " + serieTitle.getText() + " and serie title expected: " + Hooks.props.titleSerie());
        assertTrue(serieTitle.getText().contains(Hooks.props.titleSerie()));
    }

    public void playerRadioNotDisplayed() {
        this.webDriver.waitForElementNotVisible(radioPlayer, 3);
    }

    public void activateSpanishDescribedSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(spanishDescribedSubtitle);
        this.subtitle = "Español descriptivo";
    }

    public void activateSpanishSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(spanishSubtitle);
        this.subtitle = "Español";
    }

    public void activateEnglishSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(englishSubtitle);
        this.subtitle = "Ingles";
    }

    public void desactivateSpanishSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(deactivateSubtitle);
        this.subtitle = "Desactivados";
    }

    public void desactivateDescriptiveSpanishSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(deactivateSubtititleDescription);
        this.subtitle = "Desactivados";
    }

    public void activateAndDeactivateSpanishDescriptiveSubtitles() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowMenuSettings);
        this.webDriver.clickOn(spanishDescribedSubtitle);
        this.webDriver.clickOn(deactivateSubtititleDescription);
        this.subtitle = "Desactivados";
    }

    public void activateAudioDescriptiveSpanish() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(descriptiveSpanishAudio);
    }

    public void activateAudioSpanish() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(spanishAudio);
    }

    public void activateAudioChinese() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(chineseAudio);
    }

    public void activateAudioGerman() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(GermanAudio);
    }

    public void activateAudioFrench() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(FrenchAudio);
    }

    public void activateAudioKorean() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(KoreanAudio);
    }

    public void activateAudioItalian() {
        this.webDriver.sleep(5);
        this.webDriver.refreshUrl();
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
        this.webDriver.clickOn(arrowButtonAudio);
        this.webDriver.clickOn(ItalianAudio);
    }

    public void openSubtitleModal() {
        this.mouseOverPlayer();
        this.webDriver.clickOn(settingButton);
    }

    public void subtitleElementsAreDisplayed() {
        this.webDriver.mouseOver(closeModalSubtitleButton);
        assertTrue("Close modal button is not displayed", closeModalSubtitleButton.isDisplayed());
        logger.info("Close modal button is displayed");
        assertTrue("Menu Item header is not displayed", menuItemHeader.isDisplayed());
        logger.info("Menu Item header is displayed");
        assertEquals("Menu Items are not displayed", 2, menuItemElements.size());
        logger.info("Menu Items are displayed");
        assertEquals("Arrow right buttons are not displayed", 2, arrowRightButtons.size());
        logger.info("Arrow right buttons are displayed");
        this.webDriver.mouseOver(closeModalSubtitleButton);
    }

    public void closeSubtitlesModalFromOutside() {
        this.webDriver.waitUntil(1, wd -> closeModalSubtitleButton.isDisplayed());
        this.webDriver.mouseOver(settingButton);
        this.webDriver.clickOn(settingButton);
    }

    public void closeSubtitlesModal() {
        this.webDriver.waitUntil(1, wd -> closeModalSubtitleButton.isDisplayed());
        this.webDriver.mouseOver(closeModalSubtitleButton);
        this.webDriver.clickOn(closeModalSubtitleButton);
    }

    public void modalSubtitlesClosedSuccess() {
        try {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            assertFalse("Close Modal subtitle fail", closeModalSubtitleButton.isDisplayed());
        } catch (Exception e) {
        }
    }

    public void isSubtitleActivated() {
        assertTrue(subtitleDescription.getAttribute("innerHTML").contains(this.subtitle));
    }

    public void isSubtitleDeactivated() {
        assertTrue(subtitleDescription.getAttribute("innerHTML").contains(this.subtitle));
    }

    public void checkSubtitlesIsDisplayed() {
        logger.info("Subtitles: " + this.webDriver.getSubtitles());
        assertFalse(this.webDriver.getSubtitles().isEmpty());
    }

    public void checkSubtitlesIsNotDisplayed() {
        logger.info("Subtitles: " + this.webDriver.getSubtitles());
        assertTrue(this.webDriver.getSubtitles().isEmpty());
    }

    public void checkSpanishDescriptiveIsActivate() {
        assertTrue(descriptiveSpanishItemMenu.isDisplayed());
    }

    public void checkSpanishAudioIsActivate() {
        assertTrue(spanishItemMenu.isDisplayed());
    }

    public boolean isChineseAudioActive() {
        try {
            chineseItemMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    public boolean isGermanAudioActive() {
        try {
            GermanItemMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    public boolean isFrenchAudioActive() {
        try {
            FrenchItemMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    public boolean isKoreanAudioActive() {
        try {
            KoreanItemMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    public boolean isItalianAudioActive() {
        try {
            ItalianItemMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException ne) {
            return false;
        }
    }

    public void checkAudioIsActive(boolean isActive) {
        assertTrue(isActive);
    }

    public void checkAudioIsNotActive(boolean isActive) {
        assertFalse(isActive);
    }

    public void subtitleOpcionIsDesactivated() {
        assertTrue(subtitleOptionUnAvailable.isDisplayed());
    }

    public void checkYouboraRequestStart() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(youbora));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(start));
        }
    }

    public void checkYouboraRequestPause() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(youbora));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(pause));
        }
    }

    public void checkYouboraRequestSeek() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(youbora));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(seek));
        }
    }

    public void checkYouboraRequestStop() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(youbora));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(stop));
        }
    }

    public void checkYouboraRequestResume() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(youbora));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(resume));
        }
    }

    public void checkAnalyticsVod() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(vod));
        }
    }

    public void checkAnalyticsLiveChannel() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(live_channel));
        }
    }

    public void checkAnalyticsReverseEpg() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(reverse_epg));
        }
    }

    public void checkAnalyticsSearchAll() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(search_all));
        }
    }

    public void checkAnalyticsPurchase() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(purchase));
        }
    }

    public void checkAnalyticsTrailerEvent() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(analytics));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(trailer));
        }
    }

    public void checkLogglyInvalidCredentialsRequestSuccess() {
        if (Hooks.props.cloudAnalytics()) {
            Hooks.props.setProperty("buildId", webDriver.getBuildId());
            Hooks.props.setProperty("sessionId", webDriver.getSessionId());
            Hooks.props.setProperty("cloudRequestType", String.valueOf(loggly));
            Hooks.props.setProperty("cloudRequestAction", String.valueOf(invalid_credentials));
        }
    }

    public void gotoToNextChannel() {
        this.webDriver.pressKeyDown();
    }

    public void checkProgressBarTimerForwardOk() {
        this.mouseOverPlayer();
        this.webDriver.waitUntil(10, wd -> progressBarTimer.isDisplayed());
        this.mouseOverPlayer();
        var timer = progressBarTimer.getText();
        this.logger.info("Progress bar timer count: " + timer);
        this.webDriver.sleep(3);
        this.mouseOverPlayer();
        this.webDriver.waitUntil(10, wd -> progressBarTimer.isDisplayed());
        this.logger.info("Progress bar current timer count: " + progressBarTimer.getText());
        assertNotEquals(timer, progressBarTimer.getText());
    }

    public void checkProgressBarTimerRestartOk() {
        this.mouseOverPlayer();
        this.webDriver.waitUntil(10, wd -> progressBarTimer.isDisplayed());
        this.mouseOverPlayer();
        var timer = progressBarTimer.getText();
        this.logger.info("Progress bar timer count: " + timer);
        assertTrue(timer.contains("00:00:1"));
    }

    public void playLessThan60seconds() {
        this.webDriver.elementWaitDisplayed(buttonPlay);
        this.webDriver.elementClick(buttonPlay);
        this.isPlaying();
        this.actionMouseOverPlayer();
        this.webDriver.elementMoveTo(this.webDriver.findElement(elementPlaybackButton));
        this.webDriver.elementClick(elementPlaybackButton);
    }

    public void playbackMoreThan60Seconds() {
        this.webDriver.elementWaitDisplayed(buttonPlay);
        this.webDriver.elementClick(buttonPlay);
        this.isPlaying();
        Awaitility.await().atMost(90, SECONDS).until(() -> {
            try {
                assertTrue(getPlayerProgressBarCurrentTime().contains("00:01:1"));
                return true;
            } catch (Throwable throwable) {
                this.actionMouseOverPlayer();
                this.webDriver.elementClick(elementForwardButton);
                return false;
            }
        });
        this.webDriver.elementMoveTo(this.webDriver.findElement(elementPlaybackButton));
        this.webDriver.elementClick(elementPlaybackButton);
    }
}
