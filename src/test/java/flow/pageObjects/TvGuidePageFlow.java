package flow.pageObjects;

import flow.pageActions.PlayerPageAction;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TvGuidePageFlow extends Page {

    protected PlayerPageAction playerPageAction;
    protected boolean isActiveChannelGreater;

    protected String title;
    protected String titles;
    protected String titleProgramamFututre;

    @FindBy(xpath = ".//h3[text()='TV en vivo']")
    protected WebElement tvLive;
    @FindBy(xpath = ".//div[@id='flow-lazy-img']")
    protected WebElement flowLazyImg;
    @FindBy(xpath = ".//h1[contains(@class,'title truncate')]")
    protected WebElement dipTitle;
    @FindBy(xpath = ".//img[contains(@class,'dipLiveLogoChannel')]")
    protected WebElement dipChannelLogo;
    @FindBy(xpath = ".//div[contains(@class,'badge undefined')]")
    protected WebElement dipChannelAge;
    @FindBy(xpath = ".//div[contains(@class,'row description')]")
    protected WebElement dipChannelDescription;
    @FindBy(xpath = ".//button[contains(@class,'backToPlayer')]")
    protected WebElement btnBackToPlayer;
    @FindBy(css = "button > span")
    protected WebElement backToTvGuide;
    @FindBy(xpath = ".//button[@class='backButton backToPlayer']")
    protected WebElement btnHideGuide;
    @FindBy(xpath = ".//button[@class='backButton']")
    protected WebElement btnShowGuide;
    @FindBy(xpath = ".//button[contains(@class,'backButton')]")
    protected WebElement backButton;
    @FindBy(id = "epg")
    protected WebElement epg;
    protected By epgHeader = By.className("EPGHeader");
    protected By epgFilters = By.id("epgFilters");
    protected By epgFilterItem = By.xpath(".//div[contains(@class,'filter')]");
    protected By epgFilterDropdown = By.xpath(".//div[contains(@class,'flowDropDown') and contains(@class,'epgFiltersDropdown')]");
    protected By epgFilterDropdownItem = By.xpath(".//span[@class='epgFiltersDropdownItem']");
    protected By epgGrid = By.xpath(".//div[@aria-label='grid' and contains(@class,'ReactVirtualized__List')]");
    @FindBy(xpath = ".//div[contains(@class,'ReactVirtualized__Grid__innerScrollContainer')]")
    protected WebElement epgScrollableGrid;
    @FindBy(className = "miniEPG")
    protected WebElement miniEpg;
    @FindBy(xpath = ".//div[@class='vop-left-controls']")
    protected WebElement btnVopLeftControls;
    @FindBy(xpath = ".//div[contains(@class,'active')]/div[@class='channelHeader']")
    protected List<WebElement> wrapperEpgChannelWrapper;
    @FindBy(xpath = "//*[contains(@data-testid,'miniEPG')]/div/div/div/div/div/div[contains(@class,'channelWrapper')]")
    protected List<WebElement> wrapperMiniEpgChannelWrapper;
    @FindBy(css = "div.channelPrograms > div.epgProgram.vertical-align.past")
    protected List<WebElement> epgProgramPast;
    @FindBy(css = "div.channelPrograms > div.epgProgram.vertical-align.past.active")
    protected WebElement epgProgramPastActive;

    protected String wrapperEpgChannelWrapperByNumber = ".//p[@class='channelNumber' and text()='NUMBER']/../../../..";
    protected By wrapperEpgChannelNumber = By.xpath(".//p[@class='channelNumber']");
    protected By wrapperEpgChannelLogo = By.xpath(".//div[@class='channelLogo']");
    protected By wrapperEpgChannelImg = By.tagName("img");
    protected By getEmisiones = By.xpath(".//div[text()='EMISIONES']//ancestor::button");
    protected By getArrow = By.xpath(".//div[@class='arrowImg ']");
    protected By thisEmissions = By.xpath(".//div[contains(@class,'buttonInnerWrapper')]//span[text()='ESTA EMISIÓN']");
    protected By allEmissions = By.xpath(".//*[contains(text(),'Grabar todas') or contains(text(),'TODAS LAS EMISIONES')]");
    protected By getRecord = By.xpath(".//div[text()='GRABAR']//ancestor::button");
    protected By thisEmission = By.xpath(".//*[contains(text(),'Grabar esta emisión') or contains(text(),'ESTA EMISIÓN')]");
    protected By confirmRecording = By.xpath(".//*[text()='CONTINUAR']//ancestor::button");

    @FindBy(xpath = ".//*[@class='modalButtons']//button")
    protected List<WebElement>modalButtons;
    protected By getCancelRecording = By.xpath(".//*[text()='CANCELAR']//ancestor::button");
    @FindBy(xpath = ".//*[@class='recStatus']")
    protected WebElement recStatus;
    @FindBy(xpath = ".//*[@class='modalDescription']")
    protected WebElement modalDescription;
    @FindBy(xpath = ".//*[@class='modalTitle']")
    protected WebElement modalTitle;
    @FindBy(className = "channelPrograms")
    protected WebElement wrapperEpgChannelChannelPrograms;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'live')]")
    protected List<WebElement> wrapperEpgChannelEpgProgramLive;
    @FindBy(xpath = ".//div[@class='channelPrograms']/..//ancestor::div[contains(@class,'epgProgram') and contains(@class,'epgProgram vertical-align past')]")
    protected List<WebElement> wrapperEpgChannelEpgProgramAlreadyIssued;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'restricted')]")
    protected List<WebElement> wrapperEpgChannelEpgProgramRestricted;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'restricted') and contains(@class,'past')]")
    protected WebElement wrapperEpgChannelEpgProgramRestrictedPast;
    protected By wrapperEpgChannelEpgProgramRestrictedLive = By.xpath(".//div[contains(@class,'epgProgram') and contains(@class,'restricted') and contains(@class,'live')]");
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'restricted') and contains(@class,'future')]")
    protected WebElement wrapperEpgChannelEpgProgramRestrictedFuture;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'live')]/preceding-sibling::div[contains(@class,'epgProgram vertical-align past')][1]")
    protected List<WebElement> epgPrecedingProgramsAlreadyIssued;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'live')]/following-sibling::div[contains(@class,'epgProgram vertical-align future')][1]")
    protected List<WebElement> epgPrecedingProgramsNextBroadcast;

    protected By wrapperMiniEpgChannelNumber = By.xpath(".//div[contains(@class,'channelNumber')]");
    protected By wrapperMiniEpgChannelImg = By.tagName("img");
    @FindBy(xpath = ".//div[@class='currentProgram']/h5")
    protected WebElement wrapperMiniEpgChannelTitle;
    @FindBy(xpath = ".//div[contains(@class,'activeChannel')]/ancestor::div[@class='channelWrapper']")
    protected WebElement channelWrapperActive;
    @FindBy(xpath = ".//div[contains(@class,'activeChannel')]")
    protected WebElement channelHeaderActive;
    @FindBy(xpath = "//*[@id='epg']/.//div[contains(@class,'activeChannel')]")
    protected WebElement epgChannelHeaderActive;
    @FindBy(xpath = ".//div[contains(@class,'channelWrapper active')]")
    protected WebElement channelMiniEpgActive;
    @FindBy(xpath = "//*[@id='player-container']/.//div[contains(@class,'channelWrapper active')]")
    protected WebElement miniEpgChannelActive;
    @FindBy(xpath = ".//div[contains(@class,'activeChannel')]/following-sibling::div")
    protected WebElement channelProgramsActive;
    protected By dipEpgChannelEpgProgramInfo = By.xpath(".//div[@class='detailInfo']");
    protected By dipEpgChannelEpgProgramTitle = By.xpath(".//div[@class='col']/h1");
    protected By dipEpgChannelEpgProgramAttributes = By.xpath(".//div[contains(@class,'attributes')]");
    @FindBy(xpath = ".//div[contains(@class,'time')]")
    protected WebElement dipEpgChannelEpgProgramAttributesTime;
    @FindBy(xpath = ".//p[contains(@class,'callSign')]")
    protected WebElement dipEpgChannelEpgProgramAttributesCallSign;
    @FindBy(xpath = ".//div[contains(@class,'badge')]")
    protected WebElement dipEpgChannelEpgProgramAttributesRating;
    @FindBy(xpath = ".//div[contains(@class,'description')]")
    protected WebElement dipEpgChannelEpgProgramDescription;
    @FindBy(xpath = "detailInfoClose")
    protected WebElement btnDipEpgChannelEpgProgramClose;
    @FindBy(xpath = ".//button[contains(@class,'flowPrimaryButton') and contains(@class,'primaryDIPButton play')]")
    protected WebElement btnDipEpgChannelEpgProgramPlay;
    @FindBy(xpath = ".//div[contains(@class,'past')]")
    protected List<WebElement> programsPastEPG;
    @FindBy(xpath = ".//div[contains(@class,'contentIcon') and contains(@class,'iconRec')]")
    protected WebElement btnDipEpgChannelEpgProgramRecord;
    @FindBy(xpath = ".//button[contains(@class,'restart') and contains(@type,'submit')]")
    protected WebElement btnEpgChannelRestart;
    @FindBy(id = "player-container")
    protected WebElement playerContainer;
    @FindBy(xpath = ".//div[contains(@class,'prevButton')]")
    protected WebElement btnEpgControlsPrevButton;
    @FindBy(xpath = ".//div[contains(@class,'nextButton')]")
    protected WebElement btnEpgControlsNextButton;
    @FindBy(xpath = ".//div[contains(@class,'backToLiveButton') and contains(@class,'show')]")
    protected WebElement btnEpgControlsBackToLiveButton;
    @FindBy(xpath = ".//span[contains(text(), 'AHORA')]/ancestor::button[@type='submit']")
    protected WebElement btnEpgControlsBackToLiveButtonClickable;
    @FindBy(id = "epgDaySelector")
    protected WebElement dropdownEpgDaySelector;
    @FindBy(xpath = ".//button[contains(@class,'dropdown-item')]")
    protected List<WebElement> dropdownEpgDaySelectorItem;
    @FindBy(xpath = ".//span[@role='button'][text()='Ayer']")
    protected WebElement yesterdayEpgDaySelectorItem;
    @FindBy(css = "[src=\"/img/uikit/epg/arrowLeft.svg\"]")
    protected WebElement arrowLeftButton;
    @FindBy(xpath = ".//span[@role='button'][text()='Mañana']")
    protected WebElement tomorrowEpgDaySelectorItem;
    @FindBy(xpath = ".//div[contains(@class,'favoriteIcon')]")
    protected WebElement btnEpgChannelFavoriteIcon;
    protected By btnEpgChannelFavoriteInactive = By.xpath(".//div[@data-testid='notFavorite']");
    protected By btnEpgChannelsFavoriteActive = By.xpath(".//div[@data-testid='isFavorite']");
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'vertical-align') and contains(@class,'live')]")
    protected WebElement epgProgramVerticalAlignLive;
    @FindBy(xpath = ".//div[contains(@class,'epgProgram') and contains(@class,'vertical-align') and contains(@class,'future')]")
    protected WebElement epgProgramVerticalAlignFuture;
    @FindBy(xpath = ".//p[contains(@class,'truncate') and contains(@class,'title')]")
    protected WebElement epgProgramVerticalTruncateTitle;
    @FindBy(xpath = ".//div[contains(@class,'activeChannel')]/following-sibling::div//div[contains(@class,'epgProgram') and contains(@class,'vertical-align') and contains(@class,'live')]//p[contains(@class,'truncate') and contains(@class,'title')]")
    protected List<WebElement> epgProgramVerticalTruncateTitleLive;
    @FindBy(xpath = ".//div[contains(@class,'activeChannel')]/following-sibling::div//div[contains(@class,'epgProgram') and contains(@class,'vertical-align') and contains(@class,'future')]//p[contains(@class,'truncate') and contains(@class,'title')]")
    protected WebElement epgProgramVerticalTruncateTitleFuture;
    protected By contextualButtonIdle = By.xpath(".//div[contains(@class,'contextualButton') and contains(@class,'idle')]");
    @FindBy(xpath = ".//div[@class='contextualButton ']")
    protected WebElement contextualButton;
    protected By labelStatistics = By.xpath(".//span[contains(@class,'label')]");
    @FindBy(xpath = ".//div[@id='sportStatistics']")
    protected WebElement sportStatistics;
    protected By panelWrapper = By.xpath(".//div[contains(@class,'panelWrapper') and contains(@class,'panel-enter-done')]");
    protected By panelHeaderTitle = By.xpath(".//div[contains(@class,'panelHeader')]//span");
    protected By panelHeaderDate = By.xpath(".//div[contains(@class,'scoreHeader')]");
    protected By gridPanel = By.xpath(".//div[contains(@class,'gridPanel')]//p");
    protected By scoreWrapper = By.xpath(".//div[contains(@class,'scoreWrapper')]");
    @FindBy(xpath = ".//div[@class='score']")
    protected WebElement scoreWrapperScore;
    @FindBy(xpath = ".//div[contains(@class,'scoreHeader')]")
    protected WebElement scoreWrapperScoreHeader;
    protected By scoreWrapperLeftTeamName = By.xpath(".//div[contains(@class,'score__')]//span\n");
    protected By scoreWrapperLeftTeamImg = By.xpath(".//img[contains(@src,'contextual')]");
    protected By scoreWrapperLeftTeamScore = By.xpath(".//div[contains(@class,'leftScore')]//h1");
    protected By scoreWrapperRightTeamName = By.xpath(".//div[contains(@class,'score__')]//span\n");
    protected By scoreWrapperRightTeamImg = By.xpath(".//img[contains(@src,'contextual')]");
    protected By scoreWrapperRightTeamScore = By.xpath(".//div[contains(@class,'rightScore')]//h1");
    protected By scoreWrapperScoreFooter = By.xpath(".//div[contains(@class,'scoreFooter')]");
    @FindBy(xpath = ".//div[contains(@class,'closeButton')]/button")
    protected WebElement scoreWrapperCloseButton;
    protected By statsWrapper = By.xpath(".//div[contains(@class,'statsWrapper')]");
    protected By statsWrapperSeeMoreHeader = By.xpath(".//div[contains(@class,'seeMoreHeader')]");
    protected By statsWrapperHeaderTitle = By.xpath(".//div[@class='headerTitle']");
    @FindBy(xpath = ".//div[contains(@class,'seeMoreWrapper') and contains(@class,'game-enter-done')]")
    protected WebElement statsWrapperSeeMoreWrapper;
    protected By statsWrapperTabsWrapper = By.xpath(".//div[@class='tabsWrapper']");
    protected By statsWrapperTab = By.xpath(".//div[@class='tab']");
    @FindBy(xpath = ".//div[@class='tab']/span[ text()='Tablas']")
    protected WebElement statsWrapperTabTables;
    @FindBy(xpath = ".//div[@class='headerItem']/span[ text()='Goleadores']")
    protected WebElement statsWrapperHeaderItemScorers;
    @FindBy(xpath = ".//div[@class='headerItem']/span[ text()='Posiciones']")
    protected WebElement statsWrapperHeaderItemPositions;
    @FindBy(xpath = ".//div[@class='headerItem']/span[ text()='Promedios']")
    protected WebElement statsWrapperHeaderItemAverage;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-prev']/span[ text()='Goleadores']")
    protected WebElement statsWrapperHeaderItemScorersPrev;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-prev']/span[ text()='Promedios']")
    protected WebElement statsWrapperHeaderItemAveragePrev;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-prev']/span[ text()='Posiciones']")
    protected WebElement statsWrapperHeaderItemPositionsPrev;
    @FindBy(xpath = ".//div[contains(@class,'carouselHeader')]")
    protected WebElement statsWrapperCarouselHeader;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow') and contains(@class,'slick-prev')]")
    protected WebElement statsWrapperSlickArrowSlickPrev;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow') and contains(@class,'slick-next')]")
    protected WebElement statsWrapperSlickArrowSlickNext;
    @FindBy(xpath = "noContent")
    protected WebElement statsWrapperNoContent;
    @FindBy(xpath = ".//div[contains(@class,'carouselWrapper')]")
    protected WebElement carouselWrapper;
    @FindBy(xpath = ".//div[@class='homeTeam']")
    protected WebElement carouselWrapperHomeTeam;
    protected By carouselWrapperHomeTeamName = By.xpath(".//div[contains(@class,'localTeamName')]//span");
    protected By carouselWrapperHomeTeamImg = By.xpath(".//img[contains(@src,'contextual')]");
    protected By carouselWrapperHomeTeamScore = By.xpath(".//div[@class='scores']");
    protected By carouselWrapperAwayTeam = By.xpath(".//div[@class='awayTeam']");
    protected By carouselWrapperAwayTeamName = By.xpath(".//div[contains(@class,'visitorTeamName')]//span");
    protected By carouselWrapperAwayTeamImg = By.xpath(".//img[contains(@src,'contextual')]");
    protected By carouselWrapperAwayTeamScore = By.xpath(".//div[@class='scores']");
    @FindBy(xpath = ".//div[@class='referee']")
    protected WebElement carouselWrapperReferee;
    @FindBy(xpath = ".//div[@class='gameStatus']")
    protected WebElement carouselWrapperGameStatus;
    @FindBy(xpath = ".//div[@class='stadium']")
    protected WebElement carouselWrapperStadium;
    @FindBy(xpath = ".//tr[@class='rf-table-header-row']")
    protected WebElement carouselWrapperTableHeader;
    @FindBy(xpath = ".//div[@class='rf-table-header']")
    protected List<WebElement> carouselWrapperTableHeaderItem;
    @FindBy(xpath = ".//tbody[@class = 'alternateBg']/tr")
    protected List<WebElement> carouselWrapperAlternateBg;
    @FindBy(xpath = ".//div[@class='carouselHeader']//div[@class='headerItem']/span")
    protected List<WebElement> tabsWrapperCarouselComparativeHeaderItem;
    @FindBy(xpath = ".//div[@class='carouselHeader']//div[@class='slick-arrow slick-next']/span")
    protected WebElement tabsWrapperCarouselComparativeHeaderSlickNext;
    @FindBy(xpath = ".//div[@class='carouselHeader']//div[@class='slick-arrow slick-prev']/span")
    protected WebElement tabsWrapperCarouselComparativeHeaderSlickPrev;
    @FindBy(xpath = ".//div[@class='comparative']//div[@class='localTeamName']/span")
    protected WebElement tabsWrapperCarouselComparativeLocalTeamName;
    @FindBy(xpath = ".//div[@class='comparative']//div[@class='visitorTeamName']/span")
    protected WebElement tabsWrapperCarouselComparativeVisitorTeamName;
    @FindBy(xpath = ".//div[@class='homeTeam']//div[@class='scores']")
    protected WebElement tabsWrapperCarouselComparativeLocalTeamScore;
    @FindBy(xpath = ".//div[@class='awayTeam']//div[@class='scores']")
    protected WebElement tabsWrapperCarouselComparativeVisitorTeamScore;
    @FindBy(xpath = ".//div[@class='comparative']//img")
    protected List<WebElement> tabsWrapperCarouselComparativeImg;
    @FindBy(xpath = ".//div[@class='boardRow']")
    protected List<WebElement> boardWrapperBoardRow;
    protected By boardWrapperItemLabel = By.xpath(".//div[@class='itemLabel']");
    protected By boardWrapperStatsRowWrapper = By.xpath(".//div[@class='statsRowWrapper']");
    protected By boardWrapperStatsRowWrapperBoardValue = By.xpath(".//div[@class='boardValue']/span");
    // Mini Epg Elements
    @FindBy(xpath = ".//div[@class='miniEPGButton ']")
    protected WebElement btnMiniEPGButton;
    @FindBy(xpath = ".//button[@id='closeMiniEPG']")
    protected WebElement btnCloseMiniEPG;
    @FindBy(xpath = ".//div[@class='channelWrapper active']")
    protected List<WebElement> miniEpgChannelWrapper;
    @FindBy(xpath = ".//*[@class='row vertical-align']//*[@class='title truncate']")
    protected WebElement titleProgram;
    @FindBy(xpath = ".//*[text()='Alcanzaste el límite de horas de grabación']//ancestor::div[@class='flowModalBody']")
    protected WebElement recordingLimitMessage;
    @FindBy(xpath = ".//*[contains(text(),'Mis canales') or contains(text(),'Favoritos')]//ancestor::div[@class='filter ']")
    public WebElement filterFavorites;
    @FindBy(xpath = ".//div[@role='rowgroup']")
    protected WebElement rowgroupFavorites;
    protected By getEpgChannel = By.xpath(".//div[@class='channelWrapper']");
    protected By btnEpgChannelFavoriteActive = By.xpath(".//div[@data-testid='isFavorite']");
    protected By getRecordEmision = By.xpath(".//div[text()='GRABAR']//ancestor::button");
    protected By recordingTitle = By.xpath("//*[@class='contentTitle truncate2lines']");
    protected By cancelRecording = By.xpath(".//div[text()='CANCELAR']//ancestor::button");
    protected By getConfirmDelete = By.xpath(".//span[text()='ACEPTAR']//ancestor::div[contains(@class,'buttonInnerWrapper')]");
    protected By miniEpgChannelNumber = By.xpath(".//div[@class='channelNumber']");
    protected By miniEpgChannelImgName = By.xpath(".//img[contains(@src,'flow')]");
    protected By miniEpgChannelCurrentProgram = By.xpath(".//div[@class='currentProgram']");
    protected By miniEpgChannelCurrentProgramTitle = By.xpath(".//*[contains(@class,'channelTitle')]");
    protected By miniEpgChannelCurrentProgramTags = By.xpath(".//*[contains(@class,'programTitle')]");
    protected By miniEpgChannelCurrentProgramProgress = By.xpath(".//div[@class='progress']");
    protected By miniEpgChannelNextProgram = By.xpath(".//div[@class='nextProgram']");
    protected By miniEpgChannelNextProgramText = By.xpath(".//*[contains(@class,'channelTitle')]");
    protected By miniEpgChannelNextProgramTitle = By.xpath(".//*[contains(@class,'programTitle')]");
    @FindBy(xpath = ".//div[@class='gridItemsWrapper clearfix container-flui]/.//ancestor::div[@class='contentTitle truncate2lines']")
    protected WebElement elementEpgTitleChannelDIP;
    @FindBy(xpath = ".//div[@class='col-7 col-md-8']/.//h1[@class='title truncate']")
    protected WebElement elementTitleDipChannel;
    @FindBy(xpath = ".//div[@class='pill live']/.//p[text()='AHORA']")
    protected WebElement elementLiveDipChannel;
    @FindBy(xpath = ".//img[@class='logoChannelDetailInfo' and @alt='Channel Logo']")
    protected WebElement elementLogoChannelDipChannel;
    @FindBy(xpath = ".//p[@class='callSign']")
    protected WebElement elementChannelDipChannel;
    @FindBy(xpath = ".//div[@class='col-7 col-md-8']/..//ancestor::div[@class='time']")
    protected WebElement elementTimeDipChannel;
    @FindBy(xpath = ".//div[@class='timeLineDay']")
    protected WebElement elementEPGTimeLineDay;
    @FindBy(xpath = ".//div[@class='badge undefined']")
    protected WebElement elementAgeDipChannel;
    @FindBy(xpath = ".//div[@class='col-7 col-md-8']/..//ancestor::div[@class='row description']")
    protected WebElement elementDescriptionDipChannel;
    @FindBy(xpath = ".//div[@id='flow-lazy-img']")
    protected WebElement elementImageDipChannel;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'play')]")
    protected WebElement elementButtonPlayDipChannel;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'restart')]")
    protected WebElement elementButtonRestartDipChannel;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'record')]")
    protected WebElement elementButtonRecordingDipChannel;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'episodes')]")
    protected WebElement elementButtonEmissionsDipChannel;
    @FindBy(xpath = ".//img[@class='detailInfoClose' and @alt='close']")
    protected WebElement elementButtonCloseDipChannel;
    @FindBy(xpath = ".//a[@class='titleLink' and @href='/guia-de-tv']")
    protected WebElement elementViewGuide;
    @FindBy(xpath = ".//div[contains(@class,'itemWrapper')]")
    protected WebElement getItemWrapper;
    @FindBy(xpath = ".//a[contains(@class,'EmptyState')]")
    protected WebElement getEmptyState;
    @FindBy(xpath =  ".//h1[contains(@class,'seriesTitle container')]")
    protected WebElement getTitle;
    @FindBy(xpath = ".//p[@class='otherSchedulesTitle']")
    protected WebElement titleSeccionEmisiones;
    @FindBy(xpath = ".//button[contains(@class,'flowPrimaryButton')]")
    protected WebElement primaryButton;
    @FindBy(xpath = ".//button[contains(@class,'record')]")
    protected WebElement recordButton;
    @FindBy(xpath = ".//p[@class='description']")
    protected WebElement description;
    @FindBy(xpath = ".//div[@class='row liveEpisode']")
    protected List<WebElement> listEmisiones;

    protected By getRecordingEmision = By.xpath(".//div[text()='GRABAR']//ancestor::button");
    protected By getEstaEmision = By.xpath(".//div[contains(@class,'buttonInnerWrapper')]//span[text()='ESTA EMISIÓN']");
    protected By mensaje = By.xpath(".//div[@class='Toastify__toast-body']");
    protected By getTitleProgram = By.xpath(".//div[@class='row vertical-align']//h1[@class='title truncate']");
    protected By myContents = By.xpath(".//div[@role='button' and @class='flowDropDown myContentDropdown nav-item']");
    protected By getRecordings = By.xpath(".//a[@class='nav-link  grabaciones']");
    protected By getItemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");
    protected By getEmptyStateBy = By.xpath(".//a[contains(@class,'EmptyState')]");
    protected By getScheduledRecordingsStripe = By.xpath(".//h3[text()='Grabaciones programadas']//ancestor::div[@class='searchStripe']");
    protected By guiaDeTV = By.xpath(".//a[@class='nav-link  guia-de-tv']");
    protected By getProgramFuture = By.xpath(".//div[@class='epgProgram vertical-align future ']");
    protected By getCancelRecordButton = By.xpath(".//*[text()='CANCELAR']//ancestor::button");
    protected By delete = By.xpath(".//div[text()='ELIMINAR']");
    protected By deleteRecording = By.xpath("(.//*[@class='modalButtons']//div[contains(@class,'buttonInnerWrapper')])[2]");
    protected By elementEgpProgramLive = By.xpath(".//div[contains(@class,'epgProgram vertical-align live ') and @role='button']");
    protected By elementAcceptDelete = By.xpath(".//span[text()='ACEPTAR']//ancestor::div[contains(@class,'buttonInnerWrapper')]");
    @FindBy(xpath = ".//img[@alt='prev']//ancestor::button")
    protected WebElement buttonPrev;
    protected By acceptDelete = By.xpath(".//span[text()='ACEPTAR']//ancestor::div[contains(@class,'buttonInnerWrapper')]");
    protected By getMessage = By.xpath(".//p[contains(@class,'RecordingsEmptyState_expirationNotice')]");
    @FindBy(id = "flowPlayerContainer")
    protected WebElement flowPlayerContainer;
    @FindBy(xpath = ".//button[@data-testid='playButton']")
    protected WebElement playButton;
    @FindBy(xpath = "(.//*[@class='flowDropDown epgFiltersDropdown active']//span)[1]")
    protected WebElement othersSectionActive;
    @FindBy(xpath = ".//*[@class='modalTitle']")
    protected WebElement limitTitle;
    @FindBy(xpath = ".//*[@class='modalDescription']")
    protected WebElement limitDescription;
    @FindBy(xpath = ".//*[text()='Cancelar grabación' or contains(text(),'ACEPTAR')]//ancestor::button")
    protected WebElement buttonCancelRecording;
    @FindBy(xpath = ".//*[@class='flowDropDown epgFiltersDropdown']")
    protected WebElement buttonOthers;
    @FindBy(xpath = ".//span[@class='flowDropDownMenu show active']")
    protected WebElement flowDropDownMenuActive;
    @FindBy(xpath = ".//div[@class='message']")
    protected WebElement messageNoResults;
    @FindBy(xpath = ".//div[@id='noResults']")
    protected WebElement noResults;
    @FindBy(xpath = ".//*[contains(text(),'Deportes')]//ancestor::div[@class='filter ']")
    public WebElement filterSports;
    @FindBy(xpath = ".//*[@class='filter active']//h4")
    protected WebElement filterActive;
    @FindBy(xpath = ".//*[contains(text(),'Cine')]//ancestor::div[@class='filter ']")
    public WebElement filterCinema;
    @FindBy(xpath = ".//*[contains(text(),'Aire')]//ancestor::div[@class='filter ']")
    public WebElement filtroAire;
    public TvGuidePageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
        this.playerPageAction = new PlayerPageAction(webDriver);
    }

    protected By getChanel(String channelName) {
        By channel = By.xpath(".//img[@alt='" + channelName + "']//ancestor::div[@class='channelWrapper']");
        return channel;
    }
    protected By getGrabar = By.xpath(".//div[text()='GRABAR']//ancestor::button");
    protected By RowDropdownSections = By.xpath(".//div[@class='flowDropDownRow']");
    public By getWrapperEpgChannelEpgProgramRestrictedLive() {
        return wrapperEpgChannelEpgProgramRestrictedLive;
    }

    public By getByEpgHeader() {
        return epgHeader;
    }

    public By getByEpgFilters() {
        return epgFilters;
    }

    public By getByEpgFilterItem() {
        return epgFilterItem;
    }

    public By getByEpgFilterDropdown() {
        return epgFilterDropdown;
    }

    public By getByEpgFilterDropdownItem() {
        return epgFilterDropdownItem;
    }

    public By getMiniEpgChannelCurrentProgram() {
        return miniEpgChannelCurrentProgram;
    }

    public By getMiniEpgChannelNextProgram() {
        return miniEpgChannelNextProgram;
    }

    public By getMiniEpgChannelNumber() {
        return miniEpgChannelNumber;
    }

    public By getByWrapperEpgChannelNumber() {
        return this.wrapperEpgChannelNumber;
    }

    public By getMiniEpgChannelImgName() {
        return miniEpgChannelImgName;
    }

    public By getMiniEpgChannelCurrentProgramTitle() {
        return miniEpgChannelCurrentProgramTitle;
    }

    public By getMiniEpgChannelCurrentProgramTags() {
        return miniEpgChannelCurrentProgramTags;
    }

    public By getMiniEpgChannelCurrentProgramProgress() {
        return miniEpgChannelCurrentProgramProgress;
    }

    public By getMiniEpgChannelNextProgramText() {
        return miniEpgChannelNextProgramText;
    }

    public By getMiniEpgChannelNextProgramTitle() {
        return miniEpgChannelNextProgramTitle;
    }

    public By getByEpgGrid() {
        return this.epgGrid;
    }

    public By getByDipEpgChannelEpgProgramAttributes() {
        return dipEpgChannelEpgProgramAttributes;
    }

    public By getByDipEpgChannelEpgProgramTitle() {
        return dipEpgChannelEpgProgramTitle;
    }

    public By getScoreWrapperLeftTeamName() {
        return scoreWrapperLeftTeamName;
    }

    public By getByWrapperMiniEpgChannelNumber() {
        return wrapperMiniEpgChannelNumber;
    }

    public By getByWrapperMiniEpgChannelImg() {
        return wrapperMiniEpgChannelImg;
    }

    public By getByWrapperEpgChannelLogo() {
        return wrapperEpgChannelLogo;
    }

    public By getByWrapperEpgChannelImg() {
        return wrapperEpgChannelImg;
    }

    public WebElement getWrapperEpgChannelWrapperByNumber(String number) {
        return webDriver.findElement(By.xpath(wrapperEpgChannelWrapperByNumber.replaceAll("NUMBER", number)));
    }

    public By getByDipEpgChannelEpgProgramInfo() {
        return dipEpgChannelEpgProgramInfo;
    }

    public By getByBtnEpgChannelFavoriteActive() {
        return btnEpgChannelsFavoriteActive;
    }

    public By getByBtnEpgChannelFavoriteInactive() {
        return btnEpgChannelFavoriteInactive;
    }

    public By getContextualButtonIdle() {
        return contextualButtonIdle;
    }

    public By getPanelWrapper() {
        return panelWrapper;
    }

    public By getLabelStatistics() {
        return labelStatistics;
    }

    public By getPanelHeaderTitle() {
        return panelHeaderTitle;
    }

    public By getGridPanel() {
        return gridPanel;
    }

    public By getPanelHeaderDate() {
        return panelHeaderDate;
    }

    public By getScoreWrapper() {
        return scoreWrapper;
    }

    public By getScoreWrapperLeftTeamScore() {
        return scoreWrapperLeftTeamScore;
    }

    public By getScoreWrapperLeftTeamImg() {
        return scoreWrapperLeftTeamImg;
    }

    public By getScoreWrapperRightTeamName() {
        return scoreWrapperRightTeamName;
    }

    public By getScoreWrapperRightTeamScore() {
        return scoreWrapperRightTeamScore;
    }

    public By getScoreWrapperRightTeamImg() {
        return scoreWrapperRightTeamImg;
    }

    public By getScoreWrapperScoreFooter() {
        return scoreWrapperScoreFooter;
    }

    public By getStatsWrapper() {
        return statsWrapper;
    }

    public By getStatsWrapperSeeMoreHeader() {
        return statsWrapperSeeMoreHeader;
    }

    public By getStatsWrapperHeaderTitle() {
        return statsWrapperHeaderTitle;
    }

    public By getStatsWrapperTabsWrapper() {
        return statsWrapperTabsWrapper;
    }

    public By getStatsWrapperTab() {
        return statsWrapperTab;
    }

    public By getCarouselWrapperHomeTeamName() {
        return carouselWrapperHomeTeamName;
    }

    public By getCarouselWrapperHomeTeamImg() {
        return carouselWrapperHomeTeamImg;
    }

    public By getCarouselWrapperHomeTeamScore() {
        return carouselWrapperHomeTeamScore;
    }

    public By getCarouselWrapperAwayTeam() {
        return carouselWrapperAwayTeam;
    }

    public By getCarouselWrapperAwayTeamName() {
        return carouselWrapperAwayTeamName;
    }

    public By getCarouselWrapperAwayTeamImg() {
        return carouselWrapperAwayTeamImg;
    }

    public By getCarouselWrapperAwayTeamScore() {
        return carouselWrapperAwayTeamScore;
    }

    public By getBoardWrapperItemLabel() {
        return boardWrapperItemLabel;
    }

    public By getBoardWrapperStatsRowWrapper() {
        return boardWrapperStatsRowWrapper;
    }

    public By getBoardWrapperStatsRowWrapperBoardValue() {
        return boardWrapperStatsRowWrapperBoardValue;
    }

    protected By getSchedules = By.xpath(".//div[contains(@class, 'availableSchedules')]//p");
    protected By scheduleTransmission = By.xpath(".//p[@class='truncate']");

    protected By getArrowImgShowHiddenInfo = By.xpath(".//*[@class='arrowImg show']//ancestor::div[contains(@class, 'liveEpisodeWrapper') and @role='button']");
    protected By getShowInfo = By.xpath(".//div[contains(@class, 'liveEpisodeWrapper') and @role='button']");
}
