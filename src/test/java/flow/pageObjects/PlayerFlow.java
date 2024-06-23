package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PlayerFlow extends Page {

    protected Double playbackTime;
    protected Double seekTime;
    protected long timeSleep = 50;
    protected int waitTime = 60;

    protected By videoPlayer = By.tagName("video");
    protected By videoPlayerClass = By.xpath(".//div[contains(@class,'html5-video-player')]");
    protected By videoPlayerVopHidden = By.xpath(".//div[contains(@class,'vop-autohide')]");
    protected String videoPlayerUI = "window.playerUI";
    protected String videoPlayerStringLocator = "document.getElementsByTagName('video')[0]";
    protected String videoPlayerStringGetCurrentTime = String.join("", videoPlayerStringLocator + ".currentTime;");
    protected String videoPlayerStringSetCurrentTime = String.join("", videoPlayerStringLocator + ".currentTime=");
    protected String videoPlayerStringSetCurrentTimeForward = String.join("", videoPlayerStringLocator + ".currentTime+=");
    protected String videoPlayerStringSetCurrentTimeRewind = String.join("", videoPlayerStringLocator + ".currentTime+=");
    protected String videoPlayerStringSetVolumeLower = String.join("", videoPlayerStringLocator + ".volume-=");
    protected String videoPlayerStringSetVolumeHigher = String.join("", videoPlayerStringLocator + ".volume+=");
    protected String videoPlayerStringSetStatePause = String.join("", videoPlayerStringLocator + ".pause();");
    protected String videoPlayerStringSetStatePlay = String.join("", videoPlayerStringLocator + ".play();");
    protected String videoPlayerStringGetStatePaused = String.join("", videoPlayerStringLocator + ".paused;");
    protected String videoPlayerStringGetStateSeeking = String.join("", videoPlayerStringLocator + ".seeking;");
    protected String videoPlayerStringGetContentDuration = String.join("", videoPlayerStringLocator + ".duration;");
    protected String videoPlayerUIStringIsFullscreen = videoPlayerUI + ".isFullscreen()";
    protected String videoPlayerUiStringPipSupported = videoPlayerUI + ".flagPipSupported";

    protected String audioPlayerStringLocator = "document.getElementsByTagName('audio')[0]";
    protected String audioPlayerStringGetCurrentTime = String.join("", audioPlayerStringLocator + ".currentTime;");
    protected String audioPlayerStringSetStatePause = String.join("", audioPlayerStringLocator + ".pause();");
    protected String audioPlayerStringSetStatePlay = String.join("", audioPlayerStringLocator + ".play();");
    protected String audioPlayerStringGetStatePaused = String.join("", audioPlayerStringLocator + ".paused;");
    protected String audioPlayerStringGetStateSeeking = String.join("", audioPlayerStringLocator + ".seeking;");


    protected By spinnerLoading = By.xpath(".//div[@class='vop-spinner ']");
    protected By loading = By.xpath(".//div[@class='loading']");
    protected By elementRestartButton = By.xpath("//button[contains(@data-testid,'restartButton')]");

    @FindBy(xpath = ".//button[contains(@data-testid,'restartButton')]")
    protected WebElement restartButton;
    protected By elementVolumeButton = By.xpath(".//div[@class='volumeIcon']");
    @FindBy(css = ".volumeIcon")
    protected WebElement volIcon;
    protected By elementVolumeMuteButton = By.xpath(".//img[@alt='VolumeButton'][contains(@src,'muted')]");
    protected By elementVolumeSlider = By.xpath(".//div[@class='volume-progress-container']//input[@type='range']");
    protected By elementInfoButton = By.xpath(".//div[contains(@class,'vop-info-button')]");
    protected By elementReplayButton = By.xpath(".//button[@data-testid='replay10Button']");
    protected By elementForwardButton = By.xpath(".//button[@data-testid='forward10Button']");
    protected By elementPlaybackButton = By.xpath(".//button[contains(@class,'vop-play-button')]");
    protected By elementPlayButton = By.xpath(".//button[contains(@class,'vop-play-button') and contains(@class,'paused')]");
    @FindBy(xpath = ".//button[contains(@class,'vop-play-button') and contains(@class,'paused')]")
    protected WebElement playBtn;
    protected By elementPauseButton = By.xpath(".//button[contains(@class,'vop-play-button') and contains(@class,'playing')]");
    @FindBy(xpath = ".//button[contains(@class,'vop-play-button') and contains(@class,'playing')]")
    protected WebElement pauseBtn;
    protected By elementPlayProgressSlider = By.xpath(".//div[@class='whish-progress-container']//input");
    @FindBy(xpath = ".//div[@class='whish-progress-container']")
    protected WebElement progressBarContainer;
    protected By elementCastButton = By.xpath(".//google-cast-launcher[@title='Ver en la TV']");
    protected By elementPipButton = By.xpath(".//button[contains(@class,'vop-pip-button')]");
    protected By elementSettingsButton = By.xpath(".//div[contains(@class,'vop-settings-button')]");
    protected By elementFullscreenButton = By.xpath(".//button[contains(@class,'vop-fullscreen-button')]");
    @FindBy(xpath = ".//button[contains(@class,'vop-fullscreen-button')]")
    protected WebElement fullScreenBtn;
    protected By elementFullscreenInactiveButton = By.xpath(".//button[contains(@class,'vop-fullscreen-button') and @title='Pantalla completa']");
    @FindBy(xpath = ".//button[contains(@class,'vop-fullscreen-button') and @title='Pantalla completa']")
    protected WebElement openFullScreen;
    @FindBy(css = "[data-testid=\"fullScreenButton\"]")
    protected WebElement fullScreen;
    protected By elementFullscreenActiveButton = By.xpath(".//button[contains(@class,'vop-fullscreen-button') and @title='Salir pantalla completa']");
    @FindBy(xpath = ".//button[contains(@class,'vop-fullscreen-button') and @title='Salir pantalla completa']")
    protected WebElement fullScreenClose;
    @FindBy(css = ".whish-time-text-left")
    protected WebElement progressBarTimer;
    @FindBy(css = "[data-testid=\"fullScreenButton\"][title=\"Salir de pantalla completa\"]")
    protected WebElement closeFullScreen;
    @FindBy(xpath = ".//button[@type='submit' and contains(@class,'flowPrimary')]")
    protected List<WebElement> bingeWatchingButtons;
    @FindBy(xpath = ".//span[contains(text(),'CANCELAR')]")
    protected WebElement bingeWatchingCancelBtnText;
    @FindBy(css = "#player-container > div.vop-top-control-bar > div > div.vop-left-controls > button")
    protected WebElement backButton;
    protected By elementBackButton = By.xpath(".//button[contains(@class,'backButton')]");
    protected By elementLangButton = By.xpath(".//div[@class='langButton']");
    protected By elementLangButtonDisabled = By.xpath(".//div[@class='langButton disabled']");
    protected By elementPlayerProgressBarTime = By.xpath(".//span[@class='whish-time-text-left']");
    protected By elementPlayerProgressBar = By.xpath(".//div[@class='whish-progress-container']//input[@type='range']");
    protected By pausedInfo = By.xpath(".//div[@class='vertical-align show']");
    protected By pausedInfoNowWatching = By.xpath(".//div[contains(@class,'nowWatching')]");
    protected By pausedInfoTitle = By.xpath(".//div[contains(@class,'titleWrapper')]");
    protected By pausedInfoReleasedYear = By.className("releasedYear");
    protected By pausedInfoAttributesTime = By.xpath(".//div[contains(@class,'attributes')]/div/p");
    protected By pausedInfoAttributesAge = By.xpath(".//div[@class='badge age']");
    protected By pausedInfoDescription = By.xpath(".//div[@class='row description']");

    protected By vopLogo = By.xpath(".//div[contains(@class,'vop-logo')]");
    protected By elementNowButton = By.xpath(".//button[contains(@class,'liveButton')]");
    protected By elementNowButtonDisabled = By.xpath(".//button[contains(@class,'liveButton')][@disabled]");
    protected By elementWhishTime = By.xpath(".//span[@class='whish-time-text-left']");

    protected By bingeWatching = By.id("bingeWatching");
    protected By bingeWatchingLogo = By.xpath(".//img[contains(@class,'img')]");
    protected By bingeWatchingLogoSerie = By.xpath(".//div[contains(@class,'photo')]");
    protected By bingeWatchingTittle = By.xpath(".//div[contains(@class,'title')]/..//ancestor::div[@class='info']");
    protected By bingeWatchingDueTimeHour = By.xpath(".//div[contains(@class,'subTitle truncate')]/..//ancestor::div[@class='info']");
    protected By bingeWatchingAge = By.xpath(".//div[contains(@class,'ageWrapper')]");
    protected By bingeWatchingDescription = By.xpath(".//div[contains(@class,'info')]");

    protected By moreDetailsButton = By.xpath(".//span[contains(@class,'labelButton') and contains(text(),'MÁS INFO')]");
    protected By cancelButton = By.xpath(".//span[contains(@class,'labelButton') and text()='CANCELAR']");
    protected By elementLastChannels = By.xpath(".//div[@id='channelsWrapper']");
    protected By elementChannelLastChannels = By.xpath(".//div[@class='channelNumber']");

    protected By elementEgpProgramLive = By.xpath(".//div[contains(@class,'epgProgram vertical-align live ') and @role='button']");
    protected By elementEpgRecordProgram = By.xpath(".//button[contains(@class,'record') and @type='submit']");
    protected By elementEpgRecordAllEmissions = By.xpath(".//span[contains(@class,'labelButton') and @text()='TODAS LAS EMISIONES']");

    protected By elementTimeBingeWatching = By.xpath(".//div[@class='nextEpisode' and contains(text(),'Próximo episodio')]");

    protected By elementPlayNowBingeWatching = By.xpath(".//button[@type='submit' and contains(@class,'play')]");

    protected By elementImgMusicPlayer = By.xpath(".//div[@class='streamInfo']/img[@class='radioPoster' and contains(@src,'/img/radios')]");
    protected By elementTitleMusicPlayer = By.xpath(".//div[@class='streamInfo']/div[@class='scheduleDetail']");
    @FindBy(xpath = ".//div[@class='playButton']/img[@alt='PlayButton' and contains(@src,'pause')]")
    protected WebElement elementPauseMusicPlayer;
    @FindBy(xpath = ".//div[@class='playButton']/img[@alt='PlayButton' and contains(@src,'play')]")
    protected WebElement elementPlayMusicPlayer;
    protected By elementVolumeMusicPlayer = By.xpath(".//div[@class='volume-progress-container']");
    protected By elementCloseMusicPlayer = By.xpath(".//div[@class='closeButton']/img[contains(@src,'close')]");
    protected By elementSoundBarsMusicPlayer = By.xpath(".//div[contains(@class,'radioItemWrapper selected')]/div[contains(@class,'radioItem vertical-align square')]/div[@class='img']/img[@src='/img/uikit/radio/soundBars.gif']");
    protected By elementSoundBarsPausedMusicPlayer = By.xpath(".//div[contains(@class,'radioItemWrapper selected')]/div[contains(@class,'radioItem vertical-align square')]/div[@class='img']/img[contains(@src,'soundBar-paused')]");

    protected By myContents = By.xpath(".//div[@role='button' and @class='flowDropDown myContentDropdown nav-item']");
    protected By getRecordings = By.xpath(".//a[@class='nav-link  grabaciones']");
    protected By getItemWrapperBy = By.xpath(".//div[contains(@class,'itemWrapper')]");
    protected By buttonRemove = By.xpath(".//div[text()='ELIMINAR']//ancestor::button");
    protected By buttonDeleteRecording = By.xpath(".//span[text()='ELIMINAR']//ancestor::button");
    @FindBy(xpath = ".//article[contains(@class,'RecordingsEmptyState')]")
    protected WebElement elementRecordingEmpetyState;
    @FindBy(id = "skipButton")
    protected WebElement skipIntroButton;
    @FindBy(id = "skipButton")
    protected WebElement skipResumeButton;
    @FindBy(css = "#skipButton > div > button > div > span")
    protected WebElement skipIntroButtonLabel;
    @FindBy(css = "[class=\"vop-play-button paused\"]")
    protected WebElement pausedButton;
    @FindBy(xpath = ".//button[@data-testid='playButton']")
    protected WebElement playButton;
    @FindBy(css = "#player-container")
    protected WebElement playerContainer;
    @FindBy(xpath = ".//img[contains(@alt, 'Volver')]")
    protected WebElement backArrowButton;
    @FindBy(xpath = ".//span[@class='whish-time-text-left']")
    protected WebElement wishTimeLeft;
    @FindBy(xpath = ".//span[@class='whish-time-text-right']")
    protected WebElement wishTimeRight;
    protected By loadingPlayer = By.xpath("//*[contains(@resource-id,'progressBufferPlayer')]");
    @FindBy(xpath = ".//button[@data-testid=\"nextButton\"]")
    protected WebElement nextEpisode;
    @FindBy(xpath = ".//h1[contains(@class,'title')]")
    protected WebElement serieTitle;

    public PlayerFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public By getElementImgMusicPlayer() {
        return elementImgMusicPlayer;
    }

    public By getElementTitleMusicPlayer() {
        return elementTitleMusicPlayer;
    }

    public By getElementVolumeMusicPlayer() {
        return elementVolumeMusicPlayer;
    }

    public By getElementCloseMusicPlayer() {
        return elementCloseMusicPlayer;
    }

    public By getElementSoundBarsMusicPlayer() {
        return elementSoundBarsMusicPlayer;
    }

    public By getElementSoundBarsPausedMusicPlayer() {
        return elementSoundBarsPausedMusicPlayer;
    }

    public By getElementTimeBingeWatching() {
        return elementTimeBingeWatching;
    }

    public By getElementPlayNowBingeWatching() {
        return elementPlayNowBingeWatching;
    }

    public By getElementEgpProgramLive() {
        return elementEgpProgramLive;
    }

    public By getElementEpgRecordProgram() {
        return elementEpgRecordProgram;
    }

    public By getElementeEpgRecordAllEmissions() {
        return elementEpgRecordAllEmissions;
    }

    public By getVideoPlayer() {
        return this.videoPlayer;
    }

    public By getVideoPlayerClass() {
        return this.videoPlayerClass;
    }

    public By getVideoPlayerVopHidden() {
        return videoPlayerVopHidden;
    }

    public String getVideoPlayerStringLocator() {
        return this.videoPlayerStringLocator;
    }

    public String getVideoPlayerStringGetCurrentTime() {
        return this.videoPlayerStringGetCurrentTime;
    }

    public String getVideoPlayerStringSetCurrentTime() {
        return this.videoPlayerStringSetCurrentTime;
    }

    public String getVideoPlayerStringSetCurrentTimeForward() {
        return this.videoPlayerStringSetCurrentTimeForward;
    }

    public String getVideoPlayerStringSetCurrentTimeRewind() {
        return this.videoPlayerStringSetCurrentTimeRewind;
    }

    public String getVideoPlayerStringSetVolumeLower() {
        return this.videoPlayerStringSetVolumeLower;
    }

    public String getVideoPlayerStringSetVolumeHigher() {
        return this.videoPlayerStringSetVolumeHigher;
    }

    public String getVideoPlayerStringSetStatePause() {
        return this.videoPlayerStringSetStatePause;
    }

    public String getVideoPlayerStringSetStatePlay() {
        return this.videoPlayerStringSetStatePlay;
    }

    public String getVideoPlayerStringGetStatePaused() {
        return this.videoPlayerStringGetStatePaused;
    }

    public String getVideoPlayerStringGetStateSeeking() {
        return this.videoPlayerStringGetStateSeeking;
    }

    public String getVideoPlayerStringGetContentDuration() {
        return this.videoPlayerStringGetContentDuration;
    }

    public String getVideoPlayerUI() {
        return videoPlayerUI;
    }

    public String getVideoPlayerUIStringIsFullscreen() {
        return videoPlayerUIStringIsFullscreen;
    }

    public String getVideoPlayerUiStringPipSupported() {
        return videoPlayerUiStringPipSupported;
    }

    public By getSpinnerLoading() {
        return spinnerLoading;
    }

    public By getLoading() {
        return loading;
    }

    public By getElementRestartButton() {
        return elementRestartButton;
    }

    public By getElementVolumeButton() {
        return elementVolumeButton;
    }

    public By getElementVolumeMuteButton() {
        return elementVolumeMuteButton;
    }

    public By getElementVolumeSlider() {
        return elementVolumeSlider;
    }

    public By getElementInfoButton() {
        return elementInfoButton;
    }

    public By getElementReplayButton() {
        return elementReplayButton;
    }

    public By getElementForwardButton() {
        return elementForwardButton;
    }

    public By getElementPlaybackButton() {
        return elementPlaybackButton;
    }

    public By getElementPlayButton() {
        return elementPlayButton;
    }

    public By getElementPauseButton() {
        return elementPauseButton;
    }

    public By getElementCastButton() {
        return elementCastButton;
    }

    public String getAudioPlayerStringSetStatePause() {
        return audioPlayerStringSetStatePause;
    }

    public String getAudioPlayerStringSetStatePlay() {
        return audioPlayerStringSetStatePlay;
    }

    public String getAudioPlayerStringGetStatePaused() {
        return audioPlayerStringGetStatePaused;
    }

    public String getAudioPlayerStringGetStateSeeking() {
        return audioPlayerStringGetStateSeeking;
    }

    public By getElementPipButton() {
        return elementPipButton;
    }

    public By getElementPlayProgressSlider() {
        return elementPlayProgressSlider;
    }

    public By getElementSettingsButton() {
        return elementSettingsButton;
    }

    public By getElementFullscreenButton() {
        return elementFullscreenButton;
    }

    public By getElementFullscreenInactiveButton() {
        return elementFullscreenInactiveButton;
    }

    public By getElementFullscreenActiveButton() {
        return elementFullscreenActiveButton;
    }

    public By getElementBackButton() {
        return elementBackButton;
    }

    public By getElementLangButton() {
        return elementLangButton;
    }

    public By getElementLangButtonDisabled() {
        return elementLangButtonDisabled;
    }

    public By getPausedInfo() {
        return pausedInfo;
    }

    public By getPausedInfoNowWatching() {
        return pausedInfoNowWatching;
    }

    public By getPausedInfoTitle() {
        return pausedInfoTitle;
    }

    public By getPausedInfoReleasedYear() {
        return pausedInfoReleasedYear;
    }

    public By getPausedInfoAttributesTime() {
        return pausedInfoAttributesTime;
    }

    public By getPausedInfoAttributesAge() {
        return pausedInfoAttributesAge;
    }

    public By getPausedInfoDescription() {
        return pausedInfoDescription;
    }

    public By getVopLogo() {
        return vopLogo;
    }

    public By getElementNowButton() {
        return elementNowButton;
    }

    public By getElementNowButtonDisabled() {
        return elementNowButtonDisabled;
    }

    public By getElementWhishTime() {
        return elementWhishTime;
    }

    public By getBingeWatching() {
        return bingeWatching;
    }

    public By getBingeWatchingLogo() {
        return bingeWatchingLogo;
    }

    public By getBingeWatchingTittle() {
        return bingeWatchingTittle;
    }

    public By getBingeWatchingDueTimeHour() {
        return bingeWatchingDueTimeHour;
    }

    public By getBingeWatchingAge() {
        return bingeWatchingAge;
    }

    public By getBingeWatchingDescription() {
        return bingeWatchingDescription;
    }

    public By getBingeWatchingLogoSerie() {
        return bingeWatchingLogoSerie;
    }

    public By getMoreDetailsButton() {
        return moreDetailsButton;
    }

    public By getCancelButton() {
        return cancelButton;
    }

    public By getLoadingPlayer() {
        return loadingPlayer;
    }

    public By getElementChannelLastChannels() {
        return elementChannelLastChannels;
    }

    public By getElementLastChannels() {
        return elementLastChannels;
    }

    public String getAudioPlayerStringGetCurrentTime() {
        return audioPlayerStringGetCurrentTime;
    }
    @FindBy(xpath = ".//*[text()='REPRODUCIR']//ancestor::button")
    public WebElement buttonPlay;

    @FindBy(xpath = ".//div[@class='radioPlayer']")
    protected WebElement radioPlayer;
    @FindBy(xpath = ".//div[@data-testid=\"settingsButton\"]")
    protected WebElement settingButton;
    @FindBy(xpath = ".//span[@class=\"label\" and contains(text(), 'Audio')]/following-sibling::img")
    protected WebElement arrowButtonAudio;
    @FindBy(css = "[class=\"closeButton\"]")
    protected WebElement closeModalSubtitleButton;
    @FindBy(css = "[class=\"menuItem\"]")
    protected List<WebElement> menuItemElements;
    @FindBy(css = "[class=\"arrow\"][src=\"/img/uikit/arrowRight.svg\"]")
    protected List<WebElement> arrowRightButtons;
    @FindBy(css = "[class=\"menuItem header\"]")
    protected WebElement menuItemHeader;
    @FindBy(xpath = ".//span[contains(text(), \"Subtítulos\")]/following-sibling::img[@class='arrow']")
    protected WebElement arrowMenuSettings;
    @FindBy(xpath = ".//div[@role='button']//span[contains(text(), \"Español descriptivo\")]")
    protected WebElement spanishDescribedSubtitle;
    @FindBy(xpath = ".//span[text()='Subtítulos']/parent::div/parent::div/following-sibling::div/span[text()='Español']")
    protected WebElement spanishSubtitle;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Español descriptivo']")
    protected WebElement descriptiveSpanishAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Español']")
    protected WebElement spanishAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Chino']")
    protected WebElement chineseAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Alemán']")
    protected WebElement GermanAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Francés']")
    protected WebElement FrenchAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Coreano']")
    protected WebElement KoreanAudio;
    @FindBy(xpath = ".//span[text()='Audio']/parent::div/parent::div/following-sibling::div/span[text()='Italiano']")
    protected WebElement ItalianAudio;
    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Español descriptivo')]")
    protected WebElement descriptiveSpanishItemMenu;

    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Español')]")
    protected WebElement spanishItemMenu;
    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Chino')]")
    protected WebElement chineseItemMenu;
    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Alemán')]")
    protected WebElement GermanItemMenu;

    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Francés')]")
    protected WebElement FrenchItemMenu;
    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Coreano')]")
    protected WebElement KoreanItemMenu;
    @FindBy(xpath = ".//span[text()='Audio']/following-sibling::span[@class='option' and contains(text(),'Italiano')]")
    protected WebElement ItalianItemMenu;
    @FindBy(xpath = ".//span[text()='Subtítulos']/parent::div/parent::div/following-sibling::div/span[text()='Ingles']")
    protected WebElement englishSubtitle;
    @FindBy(xpath = ".//span[text()='Subtítulos']/parent::div/parent::div/following-sibling::div/span[text()='Desactivar']")
    protected WebElement deactivateSubtitle;
    @FindBy(xpath = ".//span[@class=\"option\" and contains(text(),'Desactivar')]")
    protected WebElement deactivateSubtititleDescription;
    @FindBy(xpath = ".//span[contains(text(), \"Subtítulos\")]/following-sibling::span")
    protected WebElement subtitleDescription;
    @FindBy(css = "[data-testid=\"settingsButton\"][title=\"Sin opciones de audio y subtítulos\"]")
    protected WebElement subtitleOptionUnAvailable;
}