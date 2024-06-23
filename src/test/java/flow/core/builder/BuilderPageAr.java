package flow.core.builder;

import flow.core.countries.PageTypes;
import flow.pageObjects.LoginPageFlow;
import flow.pageObjects.Page;
import flow.webdriverUtils.ExtendedWebDriver;

public class BuilderPageAr {
    public static <T extends Page> T build(PageTypes pageType, ExtendedWebDriver webDriver) {

        switch (pageType) {
            case LoginPage: {
                return (T) new LoginPageFlow(webDriver);
            }
            default:
                return null;
        }
    }
}
