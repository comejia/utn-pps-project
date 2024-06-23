package flow.webdriverUtils;

import ch.qos.logback.classic.Logger;
import flow.core.style.Styles;
import flow.stepDefinitions.Hooks;
import flow.utils.UtilsRandom;
import flow.utils.browserstack.BrowserstackService;
import org.awaitility.Awaitility;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExtendedWebDriver implements WebDriver, JavascriptExecutor {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(ExtendedWebDriver.class));

    protected WebDriver webDriver;

    public ExtendedWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getBrowserName() {
        Capabilities caps = ((RemoteWebDriver) this.webDriver).getCapabilities();
        return caps.getBrowserName();
    }

    public String getBrowserVersion() {
        Capabilities caps = ((RemoteWebDriver) this.webDriver).getCapabilities();
        return caps.getBrowserVersion();
    }

    public String getBrowserUserAgent() {
        return (String) executeScript("return navigator.userAgent;");
    }

    public void setBrowserSize(int sizeX, int sizeY) {
        this.webDriver.manage().window().setSize(new Dimension(sizeX, sizeY));
        logger.info("Browser window resized to " + sizeX + "x" + sizeY);
    }

    public void setBrowserMaximize() {
        this.webDriver.manage().window().maximize();
        logger.info("Browser window maximized");
    }

    public void setBrowserMinimize() {
        Dimension n = new Dimension(360, 592);
        this.webDriver.manage().window().setSize(n);
        logger.info("Browser window nimized");
    }

    public void setBrowserFullscreen() {
        this.webDriver.manage().window().fullscreen();
        logger.info("Browser window fullscreen");
    }

    public void scrollPageTo(int positionX, int positionY) {
        executeScript("window.scrollBy(" + positionX + "," + positionY + ")");
    }

    public void scrollPageToTop() {
        executeScript("window.scrollTo(0, 0)");
    }

    public void scrollPageToBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void scrollPageToElement(WebElement element) {
        executeScript("arguments[0].scrollIntoView();", element);
        logger.info("Element located: " + element);
    }

    public void scrollToElement(WebElement element) {
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                WebElement elemento = new WebDriverWait(this.webDriver, Duration.ofSeconds(15))
                        .until(ExpectedConditions.visibilityOf(element));
                ((JavascriptExecutor) this.webDriver).executeScript("arguments[0].scrollIntoView();", elemento);
                return true;
            } catch (Throwable throwable) {
                scrollPageDown();
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void elementPositionClick(WebElement element, double width, double height) {
        Dimension size = element.getSize();
        int widthElement = (int) (size.width * width);
        int heightElement = (int) (size.height * height);
        new Actions(this.webDriver).moveToElement(element, widthElement, heightElement).click().build().perform();
    }


    public void scrollPageToElementAwaitility(WebElement element) {
        // Wait for condition
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                executeScript("arguments[0].scrollIntoView();", element);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
        logger.info("Element located: " + element);
    }

    public void scrollToELementPageDownAwaitility(WebElement element) {
        Awaitility.await().atMost(Duration.ofSeconds(80)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
                element.isDisplayed();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            } catch (Throwable throwable) {
                scrollPageDown();
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void pageDown(WebElement webElement, int iLoopCount) {
        try {
            Actions dragger = new Actions(this.webDriver);
            for (int i = 0; i <= iLoopCount; i++) {
                dragger.moveToElement(webElement).click().sendKeys(Keys.PAGE_DOWN).build().perform();
            }
            logger.info("Move to scroll page_down '" + iLoopCount + "' times");
        } catch (Exception e) {
            logger.info("Not able to do page down");
        }
    }

    public void pageUp(WebElement webElement, int iLoopCount) {
        try {
            Actions dragger = new Actions(this.webDriver);
            int numberOfPixelsToDragTheScrollbarUp = -10;
            for (int i = iLoopCount; i > 10; i = i + numberOfPixelsToDragTheScrollbarUp) {
                dragger.moveToElement(webElement).clickAndHold().moveByOffset(0, numberOfPixelsToDragTheScrollbarUp).release(webElement).build().perform();
            }
        } catch (Exception e) {
            logger.info("Not able to do page up");
        }
    }

    public void scrollPageUp() {
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_UP);
    }

    public void scrollPageDown() {
        executeScript("window.scrollBy(0,150)");
        logger.info("window scrolled down");
    }

    public void scrollPageKeyUp() {
        executeScript("window.scrollBy(0,-150)");
        logger.info("window scrolled up");
    }

    public void scrollPageKeyDown() {
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.ARROW_DOWN);
    }

    public void scrollPageKeyLeft() {
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.ARROW_LEFT);
    }

    public void scrollPageKeyRight() {
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.ARROW_RIGHT);
    }

    public void searchPageDownForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
        // Wait for condition
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                executeScript("window.scrollBy(0,500)");
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (Throwable throwable) {
                logger.info("Scroll to search element");
                throwable.printStackTrace();
                return false;
            }
        });
        logger.info("Element located: " + element);
    }

    public void searchElement(WebElement element) {
        Awaitility.await().atMost(Duration.ofSeconds(40)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                executeScript("window.scrollBy(0,250)");
                this.waitUntil(1, wd -> element.isDisplayed());
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
        logger.info("Element located: " + element);
    }

    public void searchPageUpForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
        // Wait for condition
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                executeScript("window.scrollBy(0,-500)");
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
        logger.info("Element located: " + element);
    }

    public void elementWaitDisplayed(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void elementWaitToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void elementWaitClickable(WebElement element, int timeOut) {
        Awaitility.await().atMost(Duration.ofSeconds(timeOut)).until(() -> {
            try {
                this.webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofMillis(500));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return true;
            } catch (Throwable throwable) {
                return false;
            }
        });
    }

    public void elementWaitDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementWaitToBeClickable(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void elementWaitToBeClickable(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void elementClickByCoordinates(WebElement element, double posX, double posY) {
        Rectangle rect = element.getRect();
        Actions builder = new Actions(webDriver);
        Action action = builder.moveToElement(element, (int) (rect.width * posX), (int) (rect.height * posY)).click().build();
        action.perform();
    }

    public void elementClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void elementClick(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        this.webDriver.findElement(element).click();
    }

    public void clickOn(WebElement webElement) {
        executeScript("arguments[0].click();", webElement);
    }

    public String getSubtitles() {
        return (String) executeScript("return document.querySelector('.shaka-text-container').textContent");
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {

        }
    }

    public void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

        }
    }

    public void elementClickTopLeftAction(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        Actions builder = new Actions(webDriver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        Action action = builder.moveToElement(element, 0, 0).click().build();
        action.perform();
    }

    public void elementPressKeyAndClick(CharSequence pressKey, By element) {
        Actions actions = new Actions(webDriver);
        actions.keyDown(pressKey)
                .click(this.webDriver.findElement(element))
                .keyUp(pressKey)
                .build()
                .perform();
    }

    public void elementPressKeyAndClick(CharSequence pressKey, WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.keyDown(pressKey)
                .click(element)
                .keyUp(pressKey)
                .build()
                .perform();
    }

    public void elementDoubleClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(webDriver).doubleClick(element).build().perform();
    }

    public void elementDoubleClick(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        new Actions(webDriver).doubleClick(this.webDriver.findElement(element)).build().perform();
    }

    public void elementMoveTo(By element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void elementMoveTo(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(timeout));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementSendText(By element, String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        this.webDriver.findElement(element).sendKeys(text);
    }

    public void elementSendText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    public void elementClear(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        this.webDriver.findElement(element).clear();
    }

    public void elementClearValueWithBackspace(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(this.webDriver.findElement(element)).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        // Get element value string
        String elementValueString = getAttributeOfGivenElement(
                this.webDriver.findElement(element), "value");
        // Focus element
        this.elementClick(element);
        // Get element value string length
        int elementValueLength = elementValueString.length();
        // Press backspace until value is empty
        while (!elementValueString.isBlank()) {
            this.pressKeyRight();
            this.pressKeyBackspace();
            elementValueString = getAttributeOfGivenElement(
                    this.webDriver.findElement(element), "value");
        }
        logger.info("Element value cleared using backspace key");
    }

    public void elementClearValueWithBackspace(WebElement element) {
        elementMoveTo(element, 10);
        waitUntil(10, wd -> element.isDisplayed());
        // Get element value string
        String elementValueString = element.getAttribute("value");
        // Focus element
        this.elementClick(element);
        // Press backspace until value is empty
        while (!elementValueString.isBlank()) {
            this.pressKeyRight();
            this.pressKeyBackspace();
            elementValueString = element.getAttribute("value");
        }
        logger.info("Element value cleared using backspace key");
    }

    public WebElement elementGetParent(WebElement element) {
        return element.findElement(By.xpath("./.."));
    }

    public void elementSendKeyEnter(By element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        elementMoveTo(element, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.RETURN);
    }

    public void elementSendKeyEnter(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        elementMoveTo(element, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        this.webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.RETURN);
    }

    public String getAttributeOfGivenElement(By element, String attributeName) {
        WebElement webElement = this.webDriver.findElement(element);
        if (webElement.getAttribute(attributeName) != null) {
            logger.info("element attribute '" + attributeName + "' value obtained");
            return webElement.getAttribute(attributeName);
        } else {
            logger.info("element do not have " + attributeName + "attribute");
            return null;
        }
    }

    public String getAttributeOfGivenElement(WebElement element, String attributeName) {
        if (element.getAttribute(attributeName) != null) {
            logger.info("element attribute '" + attributeName + "' value obtained");
            return element.getAttribute(attributeName);
        } else {
            logger.info("element do not have " + attributeName + "attribute");
            return null;
        }
    }

    public void openUrl(String url) {
        this.webDriver.get(url);
    }

    public void refreshUrl() {
        this.webDriver.navigate().refresh();
        logger.info("Page is refreshed");
    }

    public void navigateBack() {
        this.webDriver.navigate().back();
        logger.info("Browser 'navigate back' action performed");
    }

    public void checkTitleEquals(String title) {
        Awaitility.await().atMost(Duration.ofSeconds(20)).until(() -> {
            try {
                assertEquals(title, this.webDriver.getTitle());
                logger.info("Browser Title " + this.webDriver.getTitle() + " equals " + title);

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkTitleContains(String title) {
        assertTrue(this.webDriver.getTitle() + " doesn´t contains " + title,
                this.webDriver.getTitle().contains(title));
        logger.info("Browser Title " + this.webDriver.getTitle() + " equals " + title);
    }

    public void checkUrlContains(String text) {
        Awaitility.await().atMost(Duration.ofSeconds(5)).until(() -> {
            try {
                assertTrue(this.webDriver.getCurrentUrl() + " doesn´t contains " + text,
                        this.webDriver.getCurrentUrl().contains(text));
                logger.info(this.webDriver.getCurrentUrl() + " contains " + text);

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void checkUrlEquals(String text) {
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                assertEquals(text, this.webDriver.getCurrentUrl());
                logger.info(this.webDriver.getCurrentUrl() + " equals " + text);

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void switchToNewTab(int tabIndex) {
        //  set tabIndex to the number of window/tab you want. 0 is the first tab
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                System.out.println("PANTALLA: "+(this.webDriver.getWindowHandles().size()));
                ArrayList<String> browserTabs = new ArrayList<String>(this.webDriver.getWindowHandles());
                this.webDriver.switchTo().window(browserTabs.get(tabIndex));

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void pressKey(Keys key) {
        var actions = new Actions(webDriver);
        actions.sendKeys(key).build().perform();
    }

    public boolean notElement(By element) {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        if (this.webDriver.findElements(element).size() > 0) {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            return true;
        } else {
            this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            return false;
        }
    }

    public void checkUrlRedirection(String redirectionTitle, String redirectionUrl)  {
        this.checkTitleEquals(redirectionTitle);
        this.checkUrlContains(redirectionUrl);
    }

    public void openUrlRedirection(By urlButton) {
        this.elementPressKeyAndClick(Keys.LEFT_CONTROL, urlButton);
        this.switchToNewTab(1);
    }

    public void openUrlRedirection(WebElement urlButton) {
        this.elementPressKeyAndClick(Keys.LEFT_CONTROL, urlButton);
        this.switchToNewTab(1);
    }

    public void openUrlRedirectionCurrentPage(By urlButton) {
        this.elementPressKeyAndClick(Keys.LEFT_CONTROL, urlButton);
    }

    public void openUrlRedirectionCurrentPage(WebElement urlButton) {
        this.elementPressKeyAndClick(Keys.LEFT_CONTROL, urlButton);
    }

    public void closeCurrentTab() {
        this.webDriver.close();
        logger.info("Closed current browser tab");
    }

    public void createNewTab() {
        executeScript("window.open()");
        logger.info("Created new browser tab");
    }

    public void switchToIFrame(WebElement element) {
        this.webDriver.switchTo().frame(element);
        logger.info("Switched to iframe");
    }

    public void switchToIFrame(By locator) {
        this.webDriver.switchTo().frame(this.webDriver.findElement(locator));
        logger.info("Switched to iframe");
    }

    public void openVodUrl(String url, String type, String id) {
        String contentType;
        if (type == null || type.isBlank() || type.isEmpty()) {
            type = "vod";
        }
        if (type.equals("vod")) {
            contentType = "peliculas";
        } else if (type.equals("vod_series")) {
            contentType = "series";
        } else {
            contentType = "vivo/programa";
        }
        String contentUrl = url + contentType + "/" + "title" + "/" + id;

        var content = Hooks.dataProvider.getLiveChannel(Hooks.props.loginUsername(), Hooks.props.loginPassword());

        if (type.contains("tv_schedule")) {
            String liveChannel = content.getTitle().replaceAll(" ", "-");
            String channelName = content.getChannelName().replaceAll(" ", "-");
            contentUrl = url + "vivo/canal/" + channelName + "/" + liveChannel + "/" + id;
        }
        this.webDriver.get(contentUrl);

        logger.info("Content Url : " + contentUrl);
    }


    public Set<Cookie> getAllCookies() {
        Set<Cookie> sessionCookies = this.webDriver.manage().getCookies();
        for (Cookie sessionCookie : sessionCookies) {
            logger.info("Saved cookie: " + sessionCookie);
        }
        return sessionCookies;
    }

    public void setAllCookies(Set<Cookie> sessionCookies) {
        for (Cookie sessionCookie : sessionCookies) {
            this.webDriver.manage().addCookie(sessionCookie);
            logger.info("Added cookie: " + sessionCookie);
        }
    }

    public void deleteAllCookies() {
        this.webDriver.manage().deleteAllCookies();
        logger.info("Deleted all cookies");
    }

    public String getBrowserLocalStorage(String key) {
        String keyValue = String.valueOf(executeScript("return window.localStorage.getItem('" + key + "');"));

        logger.info("Saved local storage: " + key + ": " + keyValue);
        return keyValue;
    }

    public void setBrowserLocalStorage(String key, String value) {
        executeScript("return window.localStorage.setItem('" + key + "','" + value + "');");

        logger.info("Added local storage: " + key + ": " + value);
    }

    public void clearBrowserLocalStorage() {
        this.removeItemFromLocalStorage("radios");
        this.removeItemFromLocalStorage("dateradios");
        this.removeItemFromLocalStorage("vCasId");
        this.removeItemFromLocalStorage("id_token");
        this.removeItemFromLocalStorage("userData");
        this.removeItemFromLocalStorage("deviceId");
        logger.info("Clear all localStorage");
    }

    public void removeItemFromLocalStorage(String item) {
        executeScript(String.format("window.localStorage.removeItem('%s');", item));
    }

    public String getBrowserSessionStorage(String key) {
        String keyValue = String.valueOf(executeScript("return window.sessionStorage.getItem('" + key + "');"));

        logger.info("Saved session storage: " + key + ": " + keyValue);
        return keyValue;
    }

    public void setBrowserSessionStorage(String key, String value) {
        executeScript("return window.sessionStorage.setItem('" + key + "','" + value + "');");

        logger.info("Added session storage: " + key + ": " + value);
    }

    public void setBrowserSessionStorageShowOnboarding(boolean showOnboarding, String profileId) {
        if (showOnboarding) {
            // TODO get profileId value via profile 'name' parameter
            // Get sessionStorage
            String onboardingProfileValue = getBrowserSessionStorage("onBoardingProfiles");
            // Get json object from sessionStorage
            JSONObject json = new JSONObject(onboardingProfileValue);

            // Get childObject json
            JSONObject childObject = json.getJSONObject(profileId);

            // Modify childObject json values
            JSONObject modifiedJson = new JSONObject();
            modifiedJson.put("name", childObject.get("name"));
            modifiedJson.put("type", childObject.get("type"));
            modifiedJson.put("visit", false);
            modifiedJson.put("timestamp", 0);
            json.put(profileId, modifiedJson);

            // Set new sessionStorage
            this.setBrowserSessionStorage("onBoardingProfiles", json.toString());
            this.refreshUrl();

            // Show new sessionStorage
            this.getBrowserSessionStorage("onBoardingProfiles");
        }
    }

    public void setBrowserLocalStorageAll(String authClientCasId) {
        this.setBrowserLocalStorage("vCasId", authClientCasId + UtilsRandom.getRandom(1, 1000000));
        this.setBrowserLocalStorage("livePlayer", "0"); //remove tour helper
        this.setBrowserLocalStorage("radios", "0"); //remove radios and music session
        this.setBrowserLocalStorage("dateradios", "2019-12-02"); //remove radios and music session
        this.setBrowserLocalStorage("datelivePlayer", "2019-07-01"); //remove tour helper
        this.setBrowserLocalStorage("intertitial", "{\"shown\":false}"); //popup announcements
        this.webDriver.navigate().refresh();
        logger.info("Local storage values added");
    }

    public void setBrowserLocalStorageWithToken() {
        this.setBrowserLocalStorage("radios", "1"); //remove radios and music session
        this.setBrowserLocalStorage("dateradios", "2019-12-02"); //remove radios and music session
        this.setBrowserLocalStorage("vCasId", Hooks.localStorage.get("vCasId")); //cast id
        this.setBrowserLocalStorage("id_token", Hooks.localStorage.get("id_token")); //object user data
        this.setBrowserLocalStorage("userData", Hooks.localStorage.get("userData")); //object user data
        this.setBrowserLocalStorage("deviceId", Hooks.localStorage.get("deviceId")); //device id host
        this.setBrowserLocalStorage("intertitial", "{\"shown\":false}"); //popup announcements
        this.webDriver.navigate().refresh();
    }

    public void getBrowserLocalStorageAll() {
        Hooks.localStorage.put("vCasId", this.getBrowserLocalStorage("vCasId"));
        Hooks.localStorage.put("id_token", this.getBrowserLocalStorage("id_token"));
        Hooks.localStorage.put("userData", this.getBrowserLocalStorage("userData"));
        Hooks.localStorage.put("deviceId", this.getBrowserLocalStorage("deviceId"));
    }

    public LogEntries getBrowserConsoleLogs(String logType) {
        LogEntries logs = this.webDriver.manage().logs().get(logType);
        for (LogEntry log : logs.getAll()) {
            logger.info("Get browser Log: " + log.getTimestamp() + " " + log.getMessage());
        }
        return logs;
    }

    public void waitBrowserConsoleLog(String logType, String logName) {
        // Wait for condition
        Awaitility.await().atMost(Duration.ofSeconds(30)).until(() -> {
            try {
                LogEntries logs = this.webDriver.manage().logs().get(logType);
                logger.info("Successfully get browser logs");
                boolean consoleLogFound = false;
                for (LogEntry log : logs.getAll()) {
                    logger.info("Checking browser Log: " + log.getTimestamp() + " " + log.getMessage());
                    if (log.getMessage().contains(logName)) {
                        consoleLogFound = true;
                        logger.info("Console log entry found: " + log.toString());
                    }
                }
                assertTrue("Console log entry not found: " + logName, consoleLogFound);
                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public void pressKeyDown() {
        pressKey(Keys.ARROW_DOWN);
        logger.info("Key Down is pressed");
    }

    public void pressKeyUp() {
        pressKey(Keys.ARROW_UP);
        logger.info("Key Up is pressed");
    }

    public void pressKeyLeft() {
        pressKey(Keys.ARROW_LEFT);
        logger.info("Key Left is pressed");
    }

    public void pressKeyRight() {
        pressKey(Keys.ARROW_RIGHT);
        logger.info("Key Right is pressed");
    }

    public void pressKeyEsc() {
        pressKey(Keys.ESCAPE);
        logger.info("Key Esc is pressed");
    }

    public void pressKeySpaceBar() {
        pressKey(Keys.SPACE);
        logger.info("Key Space is pressed");
    }

    public void pressKeyBackspace() {
        pressKey(Keys.BACK_SPACE);
        logger.info("Key Backspace is pressed");
    }

    public void pressKeyF11() {
        pressKey(Keys.F11);
        logger.info("Key F11 is pressed");
    }

    public void moveToElementUntilShowed(WebElement element) {
        Awaitility.await().atMost(Duration.ofSeconds(10)).until(() -> {
            try {
                WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(1));
                new Actions(webDriver).moveToElement(element).perform();
                wait.until(ExpectedConditions.visibilityOf(element));

                return true;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return false;
            }
        });
    }

    public List<WebElement> getVisibleElementsFromList(List<WebElement> elements) {
        List<WebElement> visibleElements = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                visibleElements.add(element);
            }
        }
        logger.info("Visible elements amount in list: " + visibleElements.size());
        return visibleElements;
    }

    public List<WebElement> getClickableElementsFromList(List<WebElement> elements) {
        List<WebElement> clickableElements = new ArrayList<>();
        for (WebElement element : elements) {
            if (element.isDisplayed() && element.isEnabled()) {
                clickableElements.add(element);
            }
        }
        logger.info("Visible elements amount in list: " + clickableElements.size());
        return clickableElements;
    }

    public void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void verifyElementVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed());
    }

    public boolean areElementsDisplayed(WebElement... webElements) {
        return Arrays.stream(webElements).allMatch(this::isElementDisplayed);
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void mouseOver(WebElement element) {
        try {
            new Actions(webDriver).moveToElement(element).perform();
            sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void waitUntil(int timeOutInSeconds, Function<WebDriver, Boolean> isTrue) {
        new WebDriverWait(this, Duration.ofSeconds(timeOutInSeconds))
                .ignoring(StaleElementReferenceException.class)
                .until(isTrue);
    }

    public void elementMoveTo(WebElement element) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void elementMoveToWithWait(WebElement element, int timeOut) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofMillis(timeOut));
        new Actions(webDriver).moveToElement(element).perform();
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void mouseMoveElement(WebElement element) {
        Actions a = new Actions(this.webDriver);
        a.moveToElement(element).perform();
    }

    public void mouseMoveElement(By element) {
        WebElement ele = this.webDriver.findElement(element);
        Actions a = new Actions(webDriver);
        a.moveToElement(ele).perform();
    }


    public void elementClickWithoutWait(WebElement element) {
        new Actions(webDriver).moveToElement(element).perform();
        element.click();
    }

    public void waitForElementVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementNotVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofMillis(timeout));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public String getCssValue(WebElement element, Styles property) {
        return element.getCssValue(String.valueOf(property)).replaceAll("\\s", "");
    }

    public WebDriver getAugmentedDriver() {
        return new Augmenter().augment(webDriver);
    }

    /********************************************* Browserstack methods *********************************************/

    public String getSessionId() {
        return Hooks.props.cloudAnalytics() ? Arrays.stream(new BrowserstackService(Hooks.props.cloudUser(),
                        Hooks.props.cloudUserKey(), Hooks.props.isMobile())
                        .getSessions(Hooks.props.buildId()))
                .filter(f -> f.getAutomation_session().getStatus().contains("running")
                        && f.getAutomation_session().getName().contains(Hooks.props.testName()))
                .findFirst()
                .get()
                .getAutomation_session().getHashed_id() : "";
    }

    public String getBuildId() {
        return Arrays.stream(new BrowserstackService(Hooks.props.cloudUser(), Hooks.props.cloudUserKey(),
                        Hooks.props.isMobile())
                        .getBuilds())
                .filter(x -> x.getAutomation_build().getName().contains(Hooks.props.buildName()))
                .findFirst()
                .get()
                .getAutomation_build().getHashed_id();
    }

    /************ Override methods implemented for extends JavascriptExecutor and WebDriver interfaces *************/
    /* Override methods implemented for extends JavascriptExecutor and WebDriver interfaces */
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor) this.webDriver).executeScript(s, objects);
    }

    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor) this.webDriver).executeAsyncScript(s, objects);
    }

    public void get(String url) {
        webDriver.get(url);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    public String getPageSource() {
        return webDriver.getPageSource();
    }

    public void close() {
        webDriver.close();
    }

    public void quit() {
        webDriver.quit();
    }

    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    public Navigation navigate() {
        return webDriver.navigate();
    }

    public Options manage() {
        return webDriver.manage();
    }

}