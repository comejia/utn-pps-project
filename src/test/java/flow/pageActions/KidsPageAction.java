package flow.pageActions;

import flow.pageObjects.KidsPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;

public class KidsPageAction extends KidsPageFlow {

    public KidsPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void waitToBeChannelLiveIsClickable() {
        webDriver.elementWaitToBeClickable(channelLive);
    }

    public void clickChannelLive() {
        webDriver.clickOn(channelLive);
    }
}
