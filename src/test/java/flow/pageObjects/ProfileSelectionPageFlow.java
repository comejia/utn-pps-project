package flow.pageObjects;

import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfileSelectionPageFlow extends Page {

    @FindBy(xpath = ".//div[@id='addProfileButton']")
    protected WebElement addProfileUser;
    @FindBy(className = "profileWrapper")
    protected WebElement profileWrapper;
    @FindBy(xpath = ".//div[@class='profileWrapper']/h1")
    protected WebElement profileWrapperText;
    @FindBy(xpath = "//p[@title='PRUEBA4']")
    protected WebElement newProfile;
    @FindBy(xpath = ".//div[@id='testAvatar']")
    protected List<WebElement> profileAvatar;
    @FindBy(xpath = ".//div[@id='testAvatar']/p")
    protected List<WebElement> avatarName;
    @FindBy(xpath = ".//div[@class=\"slick-slide slick-active slick-center slick-current\"]//img[@class='avatar']")
    protected WebElement currentProfileAvatar;
    @FindBy(xpath = ".//div[@class='editProfiles'][@role='button']")
    protected WebElement editAdminProfiles;
    @FindBy(xpath = ".//img[@class='avatar']")
    protected WebElement avatarPicture;
    @FindBy(xpath = ".//img[@class='avatarFocus']")
    protected WebElement avatarPictureFocus;
    @FindBy(xpath = ".//img[@class='avatar']")
    protected WebElement avatarPictureProfileName;
    @FindBy(xpath = "//p[@title='HOLA']")
    protected WebElement nameProfile;
    protected String avatarPictureFocusProfileName = ".//img[@class='avatarFocus'][@alt='NAME']";
    protected String avatarNameProfileName = ".//p[contains(@class,'username') and @title='NAME']";
    // Manage profiles elements
    protected String avatarEditIcon = ".//img[@class='avatarFocus'][@alt='NAME']/../img[@class='editIcon']";
    @FindBy(xpath = ".//div[@class='adminProfileHeader']")
    protected WebElement manageProfileEditProfileHeader;
    @FindBy(xpath = ".//span[@class='title']")
    protected WebElement manageProfileEditProfileTitle;
    @FindBy(xpath = ".//span[@class='detail']")
    protected WebElement manageProfileEditProfileDetail;
    @FindBy(xpath = ".//div[@class='profileStripeWrapper']")
    protected WebElement manageProfileEditProfileStripeWrapper;
    @FindBy(xpath = ".//div[contains(@class,'slick-active slick-center slick-current')]")
    protected WebElement manageProfileEditProfileStripeCenterPicture;
    @FindBy(xpath = ".//div[contains(@class,'slick-active slick-center slick-current')]/.//img[@class='avatar']")
    protected WebElement manageProfileEditProfileStripeCenterPictureAvatar;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-prev')]")
    protected WebElement manageProfileEditProfileStripeArrowLeft;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-next')]")
    protected WebElement manageProfileEditProfileStripeArrowRight;
    @FindBy(xpath = ".//label[contains(@class='Mui-focused Mui-focused labelFocused') and text()='Nombre de usuario']")
    protected WebElement manageProfileEditProfileUsernameFieldDescriptionFocused;
    @FindBy(xpath = ".//label[not(@class='Mui-focused Mui-focused labelFocused') and text()='Nombre de usuario']")
    protected WebElement manageProfileEditProfileUsernameFieldDescriptionUnfocused;
    @FindBy(xpath = ".//input[@id='username']")
    protected WebElement manageProfileEditProfileNameInput;
    @FindBy(xpath = ".//span[text()='CANCELAR']/ancestor::button[@type='submit']")
    protected WebElement manageProfileEditProfileButtonCancel;
    @FindBy(xpath = ".//span[text()='GUARDAR']/ancestor::button[@type='submit']")
    protected WebElement manageProfileEditProfileButtonConfirm;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-prev vertical-align']")
    protected WebElement manageProfileEditProfilePrevArrowButton;
    @FindBy(xpath = ".//div[@class='slick-arrow slick-next vertical-align']")
    protected WebElement manageProfileEditProfileNextArrowButton;
    @FindBy(xpath = ".//span[text()='AGREGAR']/ancestor::button[contains(@class,'flowPrimaryButton') and @type='submit']")
    protected WebElement manageProfileCreateProfileCreateButton;
    @FindBy(xpath = ".//span[text()='CANCELAR']/ancestor::button[contains(@class,'flowPrimaryButton') and @type='submit']")
    protected WebElement manageProfileCreateProfileCancelButton;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-prev')]")
    protected WebElement manageProfileCreateProfileStripeArrowLeft;
    @FindBy(xpath = ".//div[contains(@class,'slick-arrow slick-next')]")
    protected WebElement manageProfileCreateProfileStripeArrowRight;
    @FindBy(xpath = ".//div[contains(@class,'slick-active slick-center slick-current')]")
    protected WebElement manageProfileCreateProfileStripeCenterPicture;
    @FindBy(xpath = ".//input[@id='username']")
    protected WebElement manageProfileCreateProfileNameInput;
    @FindBy(xpath = ".//span[text()='AGREGAR']/ancestor::button[@type='submit']")
    protected WebElement manageProfileAddNewProfile;
    @FindBy(xpath = ".//div[@class='deleteProfiles']")
    protected WebElement manageProfileDeleteProfile;
    @FindBy(xpath = "//span[normalize-space()='ELIMINAR']")
    protected WebElement manageProfileConfirmDeleteProfile;


    public ProfileSelectionPageFlow(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public By getAvatarNameProfileName(String profileName) {
        return By.xpath(avatarNameProfileName.replaceAll("NAME", profileName));
    }


}
