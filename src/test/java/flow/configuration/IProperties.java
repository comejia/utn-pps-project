package flow.configuration;

import flow.utils.networks.Networks;
import flow.utils.networks.RequestTypes;
import flow.utils.networks.events.Devices;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.Mutable;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:properties/country/${country}/${environment}.properties",
        "classpath:properties/country/${country}/defaults-${country}.properties",
        "classpath:properties/dataProvider.properties",
        "classpath:properties/dataProviderPin.properties",
        "classpath:properties/stylesProvider.properties",
        "classpath:properties/defaults.properties"})
public interface IProperties extends Mutable {

    String influxHostname();

    String influxUsername();

    String influxPassword();

    String influxDatabase();

    String influxMeasurement();

    Boolean influxSendMetrics();

    String fileNameVideo();

    String stepBroken();

    String jiraBaseUrl();

    String jiraUsername();

    String jiraPassword();

    String jiraPathIssue();

    Boolean jiraCreateBugs();
    String dataProviderPort();

    String product();

    String environment();

    String suite();

    String deviceType();

    String country();

    String browser();

    Boolean browserFullScreen();

    String browserSizeX();

    String browserSizeY();

    Boolean browserHeadless();

    Boolean browserProxy();

    Boolean browserNetworkLogs();

    Boolean errorLog();

    Boolean videoLog();

    String cloudService();

    String cloudUser();

    String cloudUserKey();

    String cloudBrowserName();

    String cloudBrowserVersion();

    String cloudOptionsOs();

    String cloudOptionsOsVersion();

    String cloudOptionsResolution();

    Boolean cloudLocal();

    String cloudLocalIdentifier();

    Boolean cloudVideoLog();

    Boolean cloudNetworkLog();

    String cloudOptionsSeleniumVersion();

    String cloudOptionsFirefoxDriver();

    String cloudBuildName();

    String reserveDevice();

    String buildName();

    String testName();

    Boolean cloudAnalytics();

    Networks cloudRequestType();

    RequestTypes cloudRequestAction();
    Devices deviceTypes();
    Boolean isMobile();
    String buildId();

    String sessionId();

    String searchWrapper();

    String provisionType();

    String loginUsernameStarUnlinked();

    String loginUsernameDisneyUnlinked_ProfileName();

    String loginUsernameDisneyUnlinked();

    String loginPasswordDisneyUnlinked_ProfileName();

    String loginPasswordStarLinked();

    String loginPasswordDisneyLinked();

    String loginPasswordDeactivated_temp();

    String loginUsernameDisneyDeactivated_ProfileName();

    String loginUsernameDisneyDeactivatedRecidivist();

    String loginUsernameDisneyDeactivatedOffer();

    String loginPasswordDeactivated();

    String loginUsernameAdult();
    String loginPasswordAdult();
    String loginUsernameAdultProfileName();

    String loginUsernameComboUnlinked_ProfileName();

    String loginUsernameComboUnlinked();

    String loginPasswordComboLinked();

    String loginUserNameAvailableRecord();

    String loginPasswordAvailableRecord();

    String loginUserNameAvailableRecord_ProfileName();

    String loginUsernameFlex();

    String loginPasswordFlexI();

    String loginUsarNameFlexProfile();

    String pageTitleLegal();

    String pageUrlLegal();

    String userAgent();

    String version();

    String apiSwagger();

    String urlAuth();

    String versionAuth();

    String baseUrl();

    String inputCode();

    String loginUsername();

    String Username();

    String UserIncorrectFormat();

    String loginPasswordIncorrect();

    String loginPassword();

    String loginUsername_ProfileName1();

    String loginUsernameProfile();

    String loginPasswordProfile();

    String loginUsernameOtherSite();

    String loginPasswordOtherSite();

    String loginUserNameMora();
    String loginPasswordMora();

    String loginUsernamePhoneNumber();

    String loginUsernameProfile_ProfileName1();

    String loginUsernameProfile_ProfileName2();

    String loginUsernameProfile_ProfileName3();

    String loginUsernameProfile_ProfileName4();

    String loginUsernameProfile_ProfileId();

    String loginUsernameDeactivated();

    String loginUsernameDeactivated_temp();

    String loginUsernameDeactivated_ProfileName();

    String loginPasswordDisneyDeactivatedOffer();

    String loginPasswordDisneyDeactivatedRecidivist();

    String loginUsernameDisneyLinked();

    String loginUsernameDisneyLinked_ProfileName();

    String loginPasswordDisneyUnlinked();

    String loginUsernameComboLinked();

    String loginUsernameComboLinked_ProfileName();

    String loginPasswordComboUnlinked();

    String loginUsernameStarLinked();

    String loginUsernameStarLinked_ProfileName();

    String loginPasswordStarUnlinked();

    String loginUsernameStarUnlinked_ProfileName();

    String loginUsernameActiveParamount();

    String loginPasswordActiveParamount();

    String loginUsernameDisneyLinkedTemp();

    String loginPasswordDisneyLinkedTemp();

    String loginUsernameDisneyLinked_ProfileNameTemp();

    String loginUserNamePurchasesBlocked();

    String loginPasswordPurchasesBlocked();

    String loginUserNamePurchasesBlocked_ProfileName();

    String providerUsername();
    String titleContentIsNotFavorite();
    String typeContentMovieFreeAllAge();
    String idContentMovieFreeAllAge();
    String titleContentInFavorite();
    String providerPassword();

    String pinParental();

    String incorrectPinParental();

    String authDeviceName();

    String authDeviceType();

    String authDeviceModel();

    String authVersion();

    String authClientCasId();

    String provisionDevicePlatform();

    String provisionDeviceType();

    String provisionCompany();

    String contentFilterTvOrMovie_Movie();

    String contentFilterPrice_Rentals();

    String pageTitleRegister();

    String pageUrlRegister();

    String pageUrlOtpCodeValidation();

    String pageTitleOpteCodeValidation();

    String pageUrlOtpTranstion();

    String pageUrlOtpRecoveryUser();

    String pageTitleOtpTitleRecoveryUser();

    String pageTitleRecoveryPassword();

    String pageUrlRecoveryPassword();

    String pageTitleRecoveryUsername();

    String pageUrlRecoveryUsername();

    String loginPacksHboAndFutbol();

    String loginPasswordPacksHboAndFutbol();

    String pageTitleHelpCenter();

    String pageUrlHelpCenter();

    String pageTitleIhaveCode();

    String pageUrlIhaveCode();

    String pageUrlFlowConfig();

    String pageTitleFlowConfig();

    String loginErrorOtherSiteMessage();

    String moviesPageAllTitles();

    String homePageContinueWatching();

    String moviesPageAllMovies();

    String homePageRents();

    String seriesPageAllTitles();

    String rentPageAllTitles();

    String seriesPageRomance();

    String seriesCadaSemanaNuevosEpisodios();

    String promoChannel();

    String homePageFilmsSeriesAndPrograms();

    String homePageLiveTv();

    String destacadosFlow();
    String stripeSeriesOriginalsDeFlow();
    String stripeHomeTodoParamount();
    String stripeHomeCollections();
    String revivedTheBest();
    String serieSeasons();

    String lookAtIt();

    String featuredPlays();

    String players();

    String pageUrlContentDisney();

    String commonPageElements_pageLocator();

    String commonPageElements_channelStripe();

    String commonPageElements_channelItem();

    String commonPageElements_itemPlayIcon();

    String commonPageElements_itemPlayWrapper();

    String commonPageElements_superhero();

    String commonPageElements_superheroCarouselActive();

    String commonPageElements_superheroCarouselInfo();

    String commonPageElements_superheroCarouselPlayButton();

    String commonPageElements_superheroCarouselMoreInfoButton();

    String commonPageElements_superHeroCarouselPlayButtonRental();

    String commonPageElements_dropdownParentalControlCheckbox();

    String commonPageElements_stripeContentSeeMore();

    String commonPageElements_stripeHeader();

    String commonPageElements_stripeSlickItemActive();
    String titleContentParamout();

    String commonPageElements_stripeSlickArrowNext();

    String commonPageElements_stripeSlickArrowPrev();

    String commonPageElements_slickItem();

    String stripeCategory();
    String stripeHomeCategoriasYGeneros();

    String sportsEvents();
    String loginNoChannelsFavorites();

    String loginPasswordNoChannelsFavorites();

    String loginNoChannelsFavorites_ProfileName();
    String stripeHomeTop10Movies();
    String stripeHomeTop10Serie();

    String loginUserFlex();

    String loginPasswordFlex();
    String loginUserWithContents();
    String loginPasswordWithContents();
    String loginUsernameWithContents_ProfileName();
    //DataProvider
    @Config.Key("channel")
    String channel();
    @Config.Key("stripeAdult")
    String stripeAdult();
    @Config.Key("live_channel")
    String liveChannel();

    @Config.Key("movie_free_all_ages")
    String movieFreeAllAges();

    @Config.Key("movie_free_all_ages_type")
    String movieFreeAllAgesType();

    @Config.Key("movie_free_all_ages_id")
    String movieFreeAllAgesId();

    @Config.Key("movie_free_all_ages_price")
    String movieFreeAllAgesPrice();

    @Config.Key("movie_rental_all_ages_to_rent")
    String movieRentalAllAgesToRent();

    @Config.Key("movie_rental_pin_all_ages_to_rent")
    String movieRentalPinAllAgesToRent();

    @Config.Key("movie_rental_pin_all_ages_to_rent_id")
    String movieRentalPinAllAgesToRentId();

    @Config.Key("movie_rental_pin_all_ages_to_rent_type")
    String movieRentalPinAllAgesToRentType();

    @Config.Key("movie_rental_pin_all_ages_to_rent_price")
    String movieRentalPinAllAgesToRentPrice();

    @Config.Key("movie_rental_pin_all_ages_to_rent")
    String movieRentIdalAllAgesToRent();

    @Config.Key("movie_rental_all_ages_to_rent_type")
    String movieRentalAllAgesToRentType();

    @Config.Key("movie_rental_all_ages_to_rent_id")
    String movieRentalAllAgesToRentId();

    @Config.Key("movie_rental_all_ages_to_rent_price")
    String movieRentalAllAgesToRentPrice();

    @Config.Key("movie_rental_all_ages_to_verify")
    String movieRentalAllAgesToVerify();

    @Config.Key("movie_rental_all_ages_to_verify_type")
    String movieRentalAllAgesToVerifyType();

    @Config.Key("movie_rental_all_ages_to_verify_id")
    String movieRentedlAllAgesToVerifyId();

    @Config.Key("movie_rental_all_ages_to_verify_price")
    String movieRentalAllAgesToVerifyPrice();

    @Config.Key("movie_free_all_ages_binge_watching")
    String movieFreeAllAgesBingeWatching();

    @Config.Key("movie_free_all_ages_binge_watching_id")
    String movieFreeAllAgesBingeWatchingId();

    @Config.Key("movie_free_all_ages_binge_watching_type")
    String movieFreeAllAgesBingeWatchingType();

    @Config.Key("movie_free_all_ages_binge_watching_end_credits_duration")
    String movieFreeAllAgesBingeWatchingEndCreditsDuration();

    @Config.Key("movie_free_all_ages_binge_watching_price")
    String movieFreeAllAgesBingeWatchingPrice();

    @Config.Key("movie_free_restricted")
    String movieFreeRestricted();

    @Config.Key("movie_free_restricted_type")
    String movieFreeRestrictedType();

    @Config.Key("movie_free_restricted_id")
    String movieFreeRestrictedId();

    @Config.Key("movie_free_restricted_price")
    String movieFreeRestrictedPrice();

    @Config.Key("tv_free_all_ages")
    String tvFreeAllAges();

    @Config.Key("tv_free_all_ages_type")
    String tvFreeAllAgesType();

    @Config.Key("tv_free_all_ages_id")
    String tvFreeAllAgesId();

    @Config.Key("serie_free_restricted")
    String serieFreeRestricted();

    @Config.Key("serie_free_restricted_type")
    String serieFreeRestrictedType();

    @Config.Key("serie_free_restricted_id")
    String serieFreeRestrictedId();

    @Config.Key("serie_free_all_ages")
    String serieFreeAllAges();

    @Config.Key("serie_free_all_ages_type")
    String serieFreeAllAgesType();

    @Config.Key("serie_free_all_ages_id")
    String serieFreeAllAgesId();

    @Config.Key("movie_purchased_all_ages")
    String moviePurchasedAllAges();

    @Config.Key("movie_purchased_all_ages_type")
    String moviePurchasedAllAgesType();

    @Config.Key("movie_purchased_all_ages_id")
    String moviePurchasedAllAgesId();

    @Config.Key("movie_purchased_all_ages_price")
    String moviePurchasedAllAgesPrice();

    @Config.Key("movie_free_all_ages_paramount_plus")
    String movieFreeAllAgesParamountPlus();

    @Config.Key("movie_free_all_ages_star_plus")
    String contentStar();

    @Config.Key("movie_free_all_ages_disney_plus")
    String contentDisney();

    @Config.Key("live_channel_id")
    String liveChannelId();

    @Config.Key("live_channel_type")
    String liveChannelType();

    @Config.Key("live_channel_name")
    String liveChannelName();

    @Config.Key("content_prime_video")
    String contentPrimeVideo();
    @Config.Key("movie_prime_type")
    String moviePrimeType();
    @Config.Key("movie_prime_video_id")
    String moviePrimeId();


    String director();

    String actor();

    String failedStep();

    String skipIntro();
    String tvGuidePath();

    String vodSubtitles();
    String vodWithoutSubtitles();
    String movieSubtitles();
    String movieWithoutSubtitles();
    String movieWithChineseAudio();
    String serieWithGermanAudio();
    String movieWithFrenchAudio();
    String movieWithKoreanAudio();
    String seriesWithItalianAudio();

    String playerArrowBackBtnColor();
    String playerArrowBackBtnHeight();
    String playerArrowBackBtnWidth();
    String playerBackBtnColor();
    String playerBackBtnHeight();
    String playerBackBtnWidth();
    String playerForwardBtnColor();
    String playerForwardBtnHeight();
    String playerForwardBtnWidth();
    String playerFullScreenBtnColor();
    String playerFullScreenBtnHeight();
    String playerFullScreenBtnWidth();
    String playerPipBtnColor();
    String playerPipBtnHeight();
    String playerPipBtnWidth();
    String playerPlaybackBtnColor();
    String playerPlaybackBtnHeight();
    String playerPlaybackBtnWidth();
    String playerProgressBarColor();
    String playerProgressBarHeight();
    String playerProgressBarWidth();
    String playerReplayBtnColor();
    String playerReplayBtnHeight();
    String playerReplayBtnWidth();
    String playerSkipIntroBtnColor();
    String playerSkipIntroBtnHeight();
    String playerSkipIntroBtnHoverColor();
    String playerSkipIntroBtnHoverHeight();
    String playerSkipIntroBtnHoverWidth();
    String playerSkipIntroBtnWidth();
    String playerSkipIntroTextColor();
    String playerSkipIntroTextHeight();
    String playerSkipIntroTextWidth();
    String playerVolumenIconColor();
    String playerVolumenIconHeight();
    String playerVolumenIconWidth();
    String playerWishTimeLeftColor();
    String playerWishTimeLeftHeight();
    String playerWishTimeLeftWidth();
    String playerWishTimeRightColor();
    String playerWishTimeRightHeight();
    String playerWishTimeRightWidth();
    String titleSerie();
    String stripeRecommended();
    String homePageMostViewedTV();
    String stripeDisney();
    String stripeStar();
    String stripeMovies();
    String stripeSeries();
    String stripeHBO();

}