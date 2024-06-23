package flow.pageActions;

import flow.pageObjects.MyAccountPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.Assert.*;

public class MyAccountPageAction extends MyAccountPageFlow {

    public MyAccountPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void selectBannerSinPacks() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboPromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        this.webDriver.elementClick(locatorBy);
    }

    public void selectBannerDisneyLinked() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboUpgradePromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        this.webDriver.elementClick(locatorBy);
    }

    public void selectBannerDisneyUninked() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboUpgradePromotion + "')]";
        By locatorBy = By.xpath(bannerLocator);
        this.webDriver.elementClick(locatorBy);
    }

    public void unlinkedPackDisneyIsDisplayed(){
        imageDisneyPlus.isDisplayed();
        logger.info("Logo of Disney+");
        titleDisneyUnlinked.isDisplayed();
        logger.info("Title pack Disney+");
        descriptionUnlinked.isDisplayed();
        logger.info("Description del pack to Disney+");
        buttonActivePackUnlinked.isDisplayed();
        logger.info("button activar pack Disney+");
    }

    public void unlinkedPackComboIsDisplayed(){
        imageComboPlusUnlinked.isDisplayed();
        logger.info("Logo of Disney+ y Star+");
        titleComboPlusUnlinked.isDisplayed();
        logger.info("Title pack Combo+");
        descriptionComboUnlinkend.isDisplayed();
        logger.info("Description del pack to Combo+");
    }

    public void unlinkedPackStarIsDisplayed(){
        imageStarPlus.isDisplayed();
        logger.info("Logo of Star+");
        checkTitlePackActive.isDisplayed();
        logger.info("Title pack Star+");
        descriptionUnlinked.isDisplayed();
        logger.info("Description del pack to Star+");
        buttonActivePackUnlinked.isDisplayed();
        logger.info("button activar pack Disney+");
    }

    public void checkElementsHeaderComboPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerHeaderStarPlus + "')]";
        By locatorBy = By.xpath(bannerLocator);
        this.webDriver.elementWaitDisplayed(locatorBy);
        logger.info("'Banner' header promotion is displayed");
        descriptionHeaderComboPromotion.isDisplayed();
        logger.info("'Description' header promotion is displayed");
        priceHeaderComboPromotion.isDisplayed();
        logger.info("'Price' header promotion is displayed");
        this.webDriver.elementWaitDisplayed(getButtonTermsHeaderComboPromotion());
        logger.info("Button 'terms' header promotion is displayed");
        this.webDriver.elementWaitDisplayed(getCheckmarckTermsHeaderComboPromotion());
        logger.info("'Checkmark' header promotion is displayed");
        buttonSuscribeHeaderComboPromotion.isDisplayed();
        logger.info("Button 'Subscribe' header promotion is displayed");
    }

    public void checkElementsHeaderStarPromotion() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerHeaderStarPlus + "')]";
        By locatorBy = By.xpath(bannerLocator);
        this.webDriver.elementWaitDisplayed(locatorBy);
        logger.info("'Banner' header promotion is displayed");
        descriptionHeaderStarPromotion.isDisplayed();
        logger.info("'Description' header promotion is displayed");
        priceHeaderComboPromotion.isDisplayed();
        logger.info("'Price' header promotion is displayed");
        this.webDriver.elementWaitDisplayed(getButtonTermsHeaderComboPromotion());
        logger.info("Button 'terms' header promotion is displayed");
        this.webDriver.elementWaitDisplayed(getCheckmarckTermsHeaderComboPromotion());
        logger.info("'Checkmark' header promotion is displayed");
        buttonSuscribeHeaderComboPromotion.isDisplayed();
        logger.info("Button 'Subscribe' header promotion is displayed");
    }

    public void checkElementsDisneySubscriptionLinked() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboDisneyLinkedMySubscriptions + "')]";
        By bannerBy = By.xpath(bannerLocator);
        this.webDriver.elementWaitDisplayed(bannerBy);
        logger.info("Banner Combo+ is displayed");
        titleComboMySubscriptions.isDisplayed();
        logger.info("Title Combo+ is displayed");
        descriptionComboMySubscriptions.isDisplayed();
        logger.info("Description Combo+ is displayed");
        comboPlusSubmit.isDisplayed();
        logger.info("Button 'SUSCRIBITE' Combo+ is displayed");
    }

    public void checkPackDisneyPlusActive() {
        this.webDriver.isElementDisplayed(packActiveDisney);
    }

    public void checkPackStarPlusActive() {
        checkTitlePackActive.isDisplayed();
        logger.info("check title 'Star+'");
        starPackActive.isDisplayed();
        logger.info("Star+ 'Pack activo' is displayed");
    }

    public void checkPackComboPlusActive() {
        this.webDriver.elementWaitDisplayed(imageComboPlusUnlinked);
        logger.info("Image Logo Disney+ y Star+ is displayed");
        titleComboPlusUnlinked.isDisplayed();
        logger.info("Title 'Disney+ y Star+' is displayed");
        PackActiveIconMySubscripton.isDisplayed();
        logger.info("Disney+ y Star+'Pack Active' icon is displayed");
    }

    public void checkPackComboPlusInactive() {
        String bannerLocator = ".//img[contains(@src, '" + textBannerComboMySubscriptions + "')]";
        By bannerBy = By.xpath(bannerLocator);
        this.webDriver.elementWaitDisplayed(bannerBy);
        logger.info("Banner Combo+ is displayed");
        titleComboPlusMySubscription.isDisplayed();
        logger.info("Title 'Combo+' is displayed");
        this.webDriver.elementWaitDisplayed(getPackInactiveIconMySubscriptions());
        titleComboPlusMySubscriptionInactive.isDisplayed();
        logger.info("Combo+ 'Pack Inactive' icon is displayed");
        titleComboPlusMySubscriptionActivateAccount.isDisplayed();
        logger.info("Button 'ACTIVAR CUENTA' is displayed");
    }

    public void checkPositionDisneyLinked() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).findElement(getTitlePosition()).getText();
        assertEquals("disney+", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).findElement(getTitlePosition()).getText();
        assertEquals("combo+", titleSecondPosition);
    }

    public void checkPositionDisneyUnlinked() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).findElement(getTitlePosition()).getText();
        assertEquals("disney+", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).findElement(getTitlePosition()).getText();
        assertEquals("combo+", titleSecondPosition);
    }

    public void checkPositionStarLinked() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).findElement(getTitlePosition()).getText();
        assertEquals("star+", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).findElement(getTitlePosition()).getText();
        assertEquals("combo+", titleSecondPosition);
    }

    public void checkPositionStarUnlinked() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).findElement(getTitlePosition()).getText();
        assertEquals("star+", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).findElement(getTitlePosition()).getText();
        assertEquals("combo+", titleSecondPosition);
    }

    public void checkPositionDeactivated() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).getText();
        assertEquals("HBO", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).getText();
        assertEquals("Paramount+", titleSecondPosition);
        String titleThirdPosition = positionsOffertMySubscriptions.get(2).getText();
        assertEquals("Star+", titleThirdPosition);
        String titleFourPosition = positionsOffertMySubscriptions.get(3).getText();
        assertEquals("Disney+", titleFourPosition);
        String titleFivePosition = positionsOffertMySubscriptions.get(4).getText();
        assertEquals("Disney+ y Star+", titleFivePosition);
        String titleSixPosition = positionsOffertMySubscriptions.get(5).getText();
        assertEquals("Fútbol", titleSixPosition);
    }

    public void checkPositionDeStarActive() {
        String titleFirstPosition = positionsOffertMySubscriptions.get(0).getText();
        assertEquals("Star+", titleFirstPosition);
        String titleSecondPosition = positionsOffertMySubscriptions.get(1).getText();
        assertEquals("HBO", titleSecondPosition);
        String titleThirdPosition = positionsOffertMySubscriptions.get(2).getText();
        assertEquals("Paramount+", titleThirdPosition);
        String titleFourPosition = positionsOffertMySubscriptions.get(3).getText();
        assertEquals("Disney+ y Star+", titleFourPosition);
        String titleFivePosition = positionsOffertMySubscriptions.get(4).getText();
        assertEquals("Fútbol", titleFivePosition);
    }

    public void selectButtonSubscribeComboPlusMySubscriptions() {
        titleComboMySubscriptions.isDisplayed();
        webDriver.clickOn(comboPlusSubmit);
    }

    public void selectButtonSubscribeDisneyPlusMySubscriptions() {
        titleDisneyPlusMySubscription.isDisplayed();
        webDriver.clickOn(disneyPlusSubmit);
    }

    public void checkPopUpPromotion(){
            try {
                if(popUpPromotion.isDisplayed()){
                    webDriver.clickOn(closePopUp);
                }
            }catch (Throwable throwable) {
                return;
            }
    }

    public void checkOfferItemsDisneyPlusMySubscriptions() {
        disneyLogo.isDisplayed();
        logger.info("Image 'Disney+' is displayed");
        offerItemsCheckTermsCondDisneyPlus.isDisplayed();
        logger.info("Checkbox 'Terms Conditions' Disney+ is displayed");
        offerItemsTextTermsCondDisneyPlus.isDisplayed();
        logger.info("Text 'Terms Conditions' Disney+ is displayed");
        offerItemsTitleDisneyPlus.isDisplayed();
        logger.info("Title offer 'Disney+' is displayed");
        offerItemsButtonSubscribe.isDisplayed();
        logger.info("Button 'Subscribe' Disney+ is displayed");
        offerItemsButtonLegal.isDisplayed();
        logger.info("Button 'Legales' Disney+ is displayed");
    }

    public void checkElementsLandingDisneyPlus(){
        disneyLogo.isDisplayed();
        logger.info("Image 'Disney+' is displayed");
        titleDisney.isDisplayed();
        logger.info("Title 'Disney+' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureDisneyAccess.isDisplayed();
        logger.info("Description 'Disney Access' is displayed");
        featureAvailableScreens.isDisplayed();
        logger.info("Description 'Up tp 4 Screens available' is displayed");
        featureLimitDowndload.isDisplayed();
        logger.info("Description 'Limited Downloads to 10 devices'");
        featureContents4k.isDisplayed();
        logger.info("Descripton 'Available contents in 4k'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");
    }

    public void checkElementsLandingStartPlus(){
        starLogo.isDisplayed();
        logger.info("Image 'Star+' is displayed");
        titleStar.isDisplayed();
        logger.info("Title 'Star+' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureAvailableScreens.isDisplayed();
        logger.info("Description 'Up tp 4 Screens available' is displayed");
        featureLimitDowndload.isDisplayed();
        logger.info("Description 'Limited Downloads to 10 devices'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");
    }

    public void checkOfferItemsStarPlusMySubscriptions() {
        starLogo.isDisplayed();
        logger.info("Image 'Star+' is displayed");
        offerItemsTitleStarPlus.isDisplayed();
        logger.info("Title offer 'Star+' is displayed");
        offerItemsCheckTermsCondStarPlus.isDisplayed();
        logger.info("Checkbox 'Terms Conditions' Star+ is displayed");
        offerItemsTextTermsCondStarPlus.isDisplayed();
        logger.info("Text 'Terms Conditions' Star+ is displayed");
        offerItemsButtonSubscribe.isDisplayed();
        logger.info("Button 'Subscribe' Star+ is displayed");
    }

    public void checkElementsLandingParamountPlus(){
        paramountLogo.isDisplayed();
        logger.info("Image 'Paramount+' is displayed");
        titleParamount.isDisplayed();
        logger.info("Title 'Paramount+' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureParamountAccess.isDisplayed();
        logger.info("Description 'Paramount+ Access' is displayed");
        featureMovieSeriesAndMore.isDisplayed();
        logger.info("Description 'Movies,Series and More'");
        featureSeriesShowExclusive.isDisplayed();
        logger.info("Descripton 'Series exclusive, show and movie'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");
    }

    public void checkOfferItemsComboPlusMySubscriptions() {
        comboLogo.isDisplayed();
        logger.info("Image 'Combo+' is displayed");
        offerItemsTitleComboPlus.isDisplayed();
        logger.info("Title offer 'Combo+' is displayed");
        offerItemsCheckTermsCondComboPlusMySubscriptions.isDisplayed();
        logger.info("Checkbox 'Terms Conditions' Combo+ is displayed");
        offerItemsTextTermsCondComboPlusMySubscriptions.isDisplayed();
        logger.info("Text 'Terms Conditions' Combo+ is displayed");
        offerItemsButtonSubscribeMySubscriptions.isDisplayed();
        logger.info("Button 'Subscribe' is displayed");
    }

    public void checkElementsLandingComboPlus(){
        comboLogo.isDisplayed();
        logger.info("Image 'Combo+' is displayed");
        titleCombo.isDisplayed();
        logger.info("Title 'Disney+ y Star+' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureDisneyYstarAccess.isDisplayed();
        logger.info("Description 'Disney+ y Star+ Access' is displayed");
        featureAvailable4ScreensCombo.isDisplayed();
        logger.info("Description 'Up tp 4 Screens available' is displayed'");
        featureDownloadDevices.isDisplayed();
        logger.info("Descripton 'Download up 10 devices'");
        featureCustomPerfil.isDisplayed();
        logger.info("Desciption 'Custom perfil'");
        featureEventsDeportive.isDisplayed();
        logger.info("Description 'Events Deportive ESPN'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");
    }

    public void checkElementsLandingHBO(){
        hboLogo.isDisplayed();
        logger.info("Image 'HBO' is displayed");
        titleHBO.isDisplayed();
        logger.info("Title 'HBO' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureAvailableChannels.isDisplayed();
        logger.info("Description '8 channels available in HD");
        featureContentOndemand.isDisplayed();
        logger.info("Description 'Contend On Demand'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");

    }

    public void checkElementsLandingPackFutbol(){
        futbolLogo.isDisplayed();
        logger.info("Image 'Pack Futbol' is displayed");
        titlePackFutbol.isDisplayed();
        logger.info("Title 'Pack Futbol' is displayed");
        packStatusInfo.isDisplayed();
        logger.info("Payment method is displayed");
        descriptionPaymentMethod.isDisplayed();
        logger.info("Text 'Is added to your account' is displayed");
        packFeatures.isDisplayed();
        logger.info("Checkbox 'available features' is displayed");
        featureAvailableAllMachesPackFutbol.isDisplayed();
        logger.info("Description 'All Maches available of Argetine Futbol'");
        cancelSubscription.isDisplayed();
        logger.info("Description '¿Do you want cancel subscription?' is displayed");
    }

    public void pageIsDisplayedLandingPackTvEnVivo(){
        tvEnVivoLogo.isDisplayed();
        logger.info("Image 'Pack Tv en vivo' is displayed");
        titlePackTvEnVivo.isDisplayed();
        logger.info("Title 'Pack Tv en vivo' is displayed");
        titleSubscriptionMonthly.isDisplayed();
        logger.info("offert monthly subscription");
        buttonSubscriptionLandingTvEnVivo.isDisplayed();
        logger.info("Button of 'Subscription' is displayed");
        titlePackFor30days.isDisplayed();
        logger.info("Title ' Pack 30 days available' is displayed");
        buttonPackFor30Days.isDisplayed();
        logger.info("button 'Activated for 30 days' is displayed ");
        titlePackFor15Days.isDisplayed();
        logger.info("Title ' Pack 15 days available' is displayed");
        buttonPackFor15Days.isDisplayed();
        logger.info("button 'Activated for 15 days' is displayed ");
        webDriver.clickOn(HeroLandingPackTvEnVivo);
        titlePackFor7Days.isDisplayed();
        logger.info("Title ' Pack 7 days available' is displayed");
        buttonPackFor30Days.isDisplayed();
        logger.info("button 'Activated for 7 days' is displayed ");
        titlePackFor3Days.isDisplayed();
        logger.info("Title ' Pack 3 days available' is displayed");
        buttonPackFor3Days.isDisplayed();
        logger.info("button 'Activated for 3 days' is displayed ");
    }

    public void checkOfferItemsPackHboUserFlex() {
        hboLogo.isDisplayed();
        logger.info("Image 'HBO' is displayed");
        offerItemsTitlePackHbo.isDisplayed();
        logger.info("Title offer 'Suscribite a HBO' is displayed");
        buttonSubscriptionMonthly.isDisplayed();
        logger.info("Button 'Subscribe monthly' Pack Paramount is displayed");
        buttonSubscription30Days.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 30 days is displayed");
    }

    public void checkOfferItemsPackParamountUserFlex() {
        paramountPlusLogo.isDisplayed();
        logger.info("Image 'Pack Tv en vivo' is displayed");
        offerItemsTitlePackParamountPlus.isDisplayed();
        logger.info("Title offer 'Paramount' is displayed");
        buttonSubscriptionMonthly.isDisplayed();
        logger.info("Button 'Subscribe monthly' Pack Paramount is displayed");
        buttonSubscription30Days.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 30 days is displayed");
    }

    public void checkOfferItemsPackTvLiveUserFlex() {
        tvLiveLogo.isDisplayed();
        logger.info("Image 'Pack Tv en vivo' is displayed");
        offerItemsTitlePackTvLive.isDisplayed();
        logger.info("Title offer 'Tv en vivo' is displayed");
        buttonSubscriptionMonthly.isDisplayed();
        logger.info("Button 'Subscribe monthly' Pack Fútbol is displayed");
        buttonSubscription15Days.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 15 day is displayed");
        buttonSubscription30Days.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 30 days is displayed");
    }

    public void checkOfferItemsPackFutbolUserFlex() {
        futbolLogo.isDisplayed();
        logger.info("Image 'Pack Fútbol' is displayed");
        offerItemsTitlePackFutbol.isDisplayed();
        logger.info("Title offer 'Suscribete a Fútbol' is displayed");
        buttonSubscriptionMonthly.isDisplayed();
        logger.info("Button 'Subscribe monthly' Pack Fútbol is displayed");
        buttonSubscriptionOneDay.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 1 day is displayed");
        buttonSubscription30Days.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol for 30 days is displayed");
    }


    public void checkOfferItemsHboMySubscriptions() {
        hboLogo.isDisplayed();
        logger.info("Image 'HBO PACK' is displayed");
        offerItemsTitlePackHbo.isDisplayed();
        logger.info("Title offer 'Suscribite a HBO' is displayed");
        offerItemsDescriptionPackHbo.isDisplayed();
        logger.info("Description offer 'Pack HBO' is displayed");
        offerItemsButtonSubscribeMySubscriptions.isDisplayed();
        logger.info("Button 'Subscribe' Combo+ is displayed");
    }


    public void checkOfferItemsPackFutbol() {
        futbolLogo.isDisplayed();
        logger.info("Image 'Pack Fútbol' is displayed");
        offerItemsTitlePackFutbol.isDisplayed();
        logger.info("Title offer 'Suscribete a Fútbol' is displayed");
        offerItemsButtonFutbolSubscribe.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol is displayed");
    }

    public void checkOfferItemsParamountPlus() {
        paramountPlusLogo.isDisplayed();
        logger.info("Image 'Pack Paramount Plus' is displayed");
        offerItemsTitlePackParamountPlus.isDisplayed();
        logger.info("Title offer 'Suscribete a Fútbol' is displayed");
        offerItemsDescriptionPackParamountPlus.isDisplayed();
        logger.info("Description offer 'Pack Fútbol' is displayed");
        offerItemsButtonParamountPlusSubscribe.isDisplayed();
        logger.info("Button 'Subscribe' Pack Fútbol is displayed");
    }

    public void pageIsDisplayed(){
        assertFalse("stripe not displayed",stripes.isEmpty());
        assertFalse("stripe contents is not displayed",contents.isEmpty());
        assertTrue("Element Search Button not displayed",commonPageAction.buttonSearchWrapperIsDisplayed());
    }

    public void pageIsDisplayedMyAccount(){
        assertTrue("My account title not displayed", titleMyAccount.isDisplayed());
        assertTrue("Menu configuration not displayed",menuConfiguration.isDisplayed());
    }

    public void pageIsDisplayedSubcription(){
        assertTrue("Subscriptions title not displayed", titleSubscription.isDisplayed());
        assertTrue("Subscribe and enhance your experience", packsDisplayed.isDisplayed());
    }

    public void checkFormLoginStarPlus() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        formLoginStarPlus.isDisplayed();
        logger.info("Element 'Form Login Star+' is displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void checkFormLoginDisneyPlus() {
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        formLoginStarPlus.isDisplayed();
        logger.info("Element 'Form Login Star+' is displayed");
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void cancelModalPackIsDisplayed(){
     this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
     assertTrue("Element Title You are about to unsubscribe from Disney+ not displayed",this.getTitleMessagecancelPack().isEnabled());
     assertTrue("Element Description warning message not displayed",this.getWarnigMenssage().isDisplayed());
     assertTrue("Element Button Cancel not displayed",this.getButtonCancel().isEnabled());
     assertTrue("Element Button Confirmar not displayed",this.getButtonConfirm().isEnabled());
    }

    public void clickTitleDisneyPlusMySubscriptionActive() {
        webDriver.clickOn(titleDisneyPlusMySubscriptionActive);
    }

    public void clickActivateAccountMySubscriptions() {
        webDriver.clickOn(buttonActivateAccountMySubscriptions);
    }

    public void clickDropdownEditProfile() {
        webDriver.clickOn(dropdownEditProfile);
    }

    public void clickDropdownManage() {
        webDriver.clickOn(dropdownButtonManage);
    }

    public void clickButtonExploreContent(){ this.webDriver.clickOn(buttonExploreContent); }

    public void  clickModalDisneyPlus(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.clickOn(imageDisneyPlus);
    }

    public void clickModalStarPlus(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.clickOn(imageStarPlus);
    }

    public void clickModalHBO(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.clickOn(packActiveHBO);
    }

    public void clickModalPackFutbol(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.clickOn(modalFutbol);
    }

    public void clickModalParamountPlus(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        this.webDriver.clickOn(packActiveParamountPlus);
    }

    public void clickModalPackTvEnVivo(){
        this.webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        webDriver.clickOn(modalTvEnVivo);
    }

    public void clickLegal(){
        webDriver.waitUntil(10, wd -> offerItemsButtonLegal.isDisplayed());
        webDriver.clickOn(offerItemsButtonLegal);
    }

    public void clickCancelPackStarPlus(){
        this.webDriver.clickOn(cancelSubscription);
    }

    public void clickCancelPackSubscription(){
        this.webDriver.clickOn(cancelSubscription);
    }

    public void clickCancelPackDisneyPlus(){
        this.webDriver.clickOn(cancelSubscription);
    }

    public void clickModalDisneyStarPlus(){
        this.webDriver.clickOn(packActiveDisneyStarPlus);
    }

    public void clickButtonDeviceManage() {
        webDriver.clickOn(buttonDeviceManage);
    }

    public void clickConfigurationButton() {
        webDriver.clickOn(configurationButton);
    }

    public void clickConfigurationTextRedirect() {
        webDriver.clickOn(configurationTextRedirect);
    }

    public void clickConfigurationButtonRedirect() {
        webDriver.clickOn(configurationButtonRedirect);
    }

    public void clickConfigurationRedirectHomePage() {
        webDriver.clickOn(configurationRedirectHomePage);
    }

    public void configurationRedirectHomePageIsDisplayed() {
        webDriver.waitUntil(10, wd -> configurationRedirectHomePage.isDisplayed());
    }

    public boolean configurationElementsAreDisplayed() {
        return webDriver.areElementsDisplayed(configurationRedirectHomePage, configurationIconFlow, configurationTextFlow,
                configurationCallCenterFlow, configurationLoginFlow, configurationRegisterFlow);
    }

    public void clickDropdownParentalControl() {
        webDriver.elementClick(dropDownParentalControl);
    }

    public void clickPopUpConfigureButton() {
        webDriver.elementClick(popUpConfigureButton);
    }

    public void clickBannerHomeComboUnlinked() {
        webDriver.elementClick(this.getBannerHomeComboUnlinked());
    }

    public void clickBannerHomeStarUnlinked() {
        webDriver.elementClick(this.getBannerHomeStarUnlinked());
    }
}
