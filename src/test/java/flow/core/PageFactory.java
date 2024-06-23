package flow.core;

import flow.configuration.IProperties;
import flow.core.builder.BuilderPageAr;
import flow.core.countries.Countries;
import flow.core.countries.PageTypes;
import flow.pageObjects.Page;
import flow.webdriverUtils.ExtendedWebDriver;

public class PageFactory {
    private static ExtendedWebDriver webDriver;
    private static String country;
    private static IProperties props;


    public static <T extends Page> T build(PageTypes pageType) {
        switch (Countries.valueOf(PageFactory.country)) {
            case AR: {
                return BuilderPageAr.build(pageType, webDriver);
            }
            case UY: {
                return BuilderPageUy.build(pageType, webDriver);
            }
            case PY: {
                return BuilderPagePy.build(pageType, webDriver);
            }
            default:
                return null;
        }
    }

    public static ExtendedWebDriver getWebDriver() {
        return PageFactory.webDriver;
    }

    public static void setWebDriver(ExtendedWebDriver webDriver) {
        PageFactory.webDriver = webDriver;
    }

    public static void setCountry(String country) {
        PageFactory.country = country;
    }

    public static IProperties getProps() {
        return props;
    }

    public static void setProps(IProperties props) {
        PageFactory.props = props;
    }

}
