package flow.pageActions;

import flow.pageObjects.ProfileSelectionPageFlow;
import flow.webdriverUtils.ExtendedWebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ProfileSelectionPageAction extends ProfileSelectionPageFlow {

    public ProfileSelectionPageAction(ExtendedWebDriver webDriver) {
        super(webDriver);
    }

    public void pageIsDisplayed() {
        // Check elements
        webDriver.waitUntil(10, wd -> profileWrapper.isDisplayed());
        assertTrue("Element Profile Wrapper not displayed", profileWrapper.isDisplayed());
        logger.info("Elements are displayed");
        // Check text
        assertTrue("Profile selection message incorrect", profileWrapperText.getText().contains("¿Quién está viendo?"));
        logger.info("Text displayed correctly: " + profileWrapperText);
        // Check amount of user profiles
        webDriver.waitUntil(10, wd -> profileAvatar.size() > 0);
        assertTrue("Profiles amount is 0", profileAvatar.size() > 0);
        logger.info("User profiles: " + profileAvatar.size());
    }

    public void selectUserProfile(String profileName) {
        try {
            // Select profile
            this.webDriver.elementClick(this.getAvatarNameProfileName(profileName));
            logger.info("Selected profile " + profileName + "");
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void checkNameProfile(){
        nameProfile.isDisplayed();
    }

    public void selectUserProfile(int profileNumber) {
        try {
            // Select profile
            this.webDriver.elementClick(profileAvatar.get(profileNumber));
            logger.info("Selected profile " + profileNumber + "");
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            logger.info(String.valueOf(e));
        }
    }

    public void createNewProfile(){
        webDriver.clickOn(addProfileUser);
        logger.info("create new profile");
        this.webDriver.elementSendText(manageProfileEditProfileNameInput,"new");
        this.webDriver.elementClick(manageProfileAddNewProfile);
    }

    public void accessManageProfiles() {
        webDriver.clickOn(editAdminProfiles);
        logger.info("Accessed 'Manage my profiles' page");
    }

    public void clickEditProfileIcon(int profileNumber) {
        // Select profile name
        this.webDriver.elementClick(profileAvatar.get(profileNumber));
        logger.info("Clicked Edit profile button for profile " + profileNumber);
    }

    public void checkAvatarIcon(String profileName) {
        assertTrue(this.webDriver.findElement(getAvatarNameProfileName(profileName)).isDisplayed());
        logger.info("'Edit profile' button for profile '" + profileName + "' is displayed");
    }

    public void pageIsDisplayedEditProfile() {
        webDriver.waitUntil(10, wd -> manageProfileEditProfileHeader.isDisplayed());
        assertTrue("Manage profile page title not displayed", manageProfileEditProfileTitle.isDisplayed());
        assertTrue("Manage profile page detail not displayed", manageProfileEditProfileDetail.isDisplayed());
        assertTrue("Manage profile page 'profile images' not displayed", manageProfileEditProfileStripeWrapper.isDisplayed());
        assertTrue("Manage profile page 'left arrow' not displayed", manageProfileEditProfileStripeArrowLeft.isDisplayed());
        assertTrue("Manage profile page 'right arrow' not displayed", manageProfileEditProfileStripeArrowRight.isDisplayed());
        assertTrue("Manage profile page 'name input' not displayed", manageProfileEditProfileNameInput.isDisplayed());
        assertTrue("Manage profile page 'cancel button' not displayed", manageProfileEditProfileButtonCancel.isDisplayed());
        assertTrue("Manage profile page 'confirm button' not displayed", manageProfileEditProfileButtonConfirm.isDisplayed());
        logger.info("Elements are displayed");
    }

    public void modifyProfilePicture(String direction) {
        // Get current image 'src' values
        avatarPicture.getAttribute("src");
        String imageDataIndex = this.webDriver.getAttributeOfGivenElement(
                manageProfileEditProfileStripeCenterPictureAvatar, "src");
        logger.info("Current avatar picture for profile is: " + imageDataIndex);
        String newImageDataIndex;

        if (direction.equals("right")) {
            webDriver.waitUntil(10, wd -> manageProfileEditProfileNextArrowButton.isDisplayed());
            webDriver.clickOn(manageProfileEditProfileNextArrowButton);
        } else if (direction.equals("left")) {
            webDriver.waitUntil(10, wd -> manageProfileEditProfilePrevArrowButton.isDisplayed());
            webDriver.clickOn(manageProfileEditProfilePrevArrowButton);
        }

        // Wait until image 'src' change
        newImageDataIndex = this.webDriver.getAttributeOfGivenElement(
                manageProfileEditProfileStripeCenterPictureAvatar, "src");
        int counter = 0;
        while (imageDataIndex.equals(newImageDataIndex) && counter < 10) {
            newImageDataIndex = this.webDriver.getAttributeOfGivenElement(
                    manageProfileEditProfileStripeCenterPictureAvatar, "src");
            logger.info("New avatar picture for profile is: " + newImageDataIndex);
            logger.info("Waiting for avatar image to change");
            counter++;
        }
        logger.info("New avatar picture for profile is: " + newImageDataIndex);

        // Check images are not the same
        assertNotEquals(imageDataIndex, newImageDataIndex);
        webDriver.sleep(1); // Needed because it will not change picture otherwise

        webDriver.waitUntil(10, wd -> manageProfileEditProfileButtonConfirm.isDisplayed());
        webDriver.clickOn(manageProfileEditProfileButtonConfirm);
        webDriver.sleep(1); // Needed because it will not change picture otherwise
        logger.info("profile avatar picture Changed");
    }

    public void modifyProfileName(String profileNewName) throws AWTException, InterruptedException {
        webDriver.waitUntil(10, wd -> manageProfileEditProfileNameInput.isDisplayed());
        String previousName = this.webDriver.getAttributeOfGivenElement(manageProfileEditProfileNameInput,
                "value");
        this.webDriver.elementClearValueWithBackspace(manageProfileEditProfileNameInput);
        this.webDriver.elementSendText(manageProfileEditProfileNameInput, profileNewName);
        Thread.sleep(500); // Needed because it will not change picture otherwise
        this.webDriver.elementClick(manageProfileEditProfileButtonConfirm);
        logger.info("Changed profile name from '" + previousName + "' to '" + profileNewName + "'");
    }

    public void changeUserProfileName(int profileNumber, String newProfileName) throws AWTException, InterruptedException {
        this.clickEditProfileIcon(profileNumber);
        this.pageIsDisplayedEditProfile();
        this.modifyProfileName(newProfileName);
        this.checkAvatarIcon(newProfileName);
    }

    public void changeUserProfilePicture(int profileNumber, String direction) {
        String avatarPicture = this.webDriver.getAttributeOfGivenElement(
                profileAvatar.get(profileNumber), "src");
        logger.info("Current avatar picture for profile '" + profileNumber + "' is: " + avatarPicture);
        this.clickEditProfileIcon(profileNumber);
        this.pageIsDisplayedEditProfile();
        this.modifyProfilePicture(direction);
    }

    public void pageIsDisplayedCreateProfile() {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(manageProfileCreateProfileCancelButton));
        assertTrue("Element 'Current profile picture' is not displayed", manageProfileCreateProfileStripeCenterPicture.isDisplayed());
        assertTrue("Element 'Left arrow' button is not displayed", manageProfileCreateProfileStripeArrowLeft.isDisplayed());
        assertTrue("Element 'right arrow' button is not displayed", manageProfileCreateProfileStripeArrowRight.isDisplayed());
        assertTrue("Element 'Name' input is not displayed", manageProfileCreateProfileNameInput.isDisplayed());
        assertTrue("Element 'Cancel' button is not displayed", manageProfileCreateProfileCancelButton.isDisplayed());
        assertTrue("Element 'Create' button is not displayed", manageProfileCreateProfileCreateButton.isDisplayed());
    }

    public void createProfile(String newProfileName) throws AWTException {
        webDriver.clickOn(addProfileUser);
        pageIsDisplayedCreateProfile();
        this.webDriver.elementSendText(manageProfileCreateProfileNameInput, newProfileName);
        webDriver.sleep(1); // Needed because it will not change picture otherwise
        this.webDriver.elementClick(manageProfileCreateProfileCreateButton);
        logger.info("Created new profile " + newProfileName);
    }

    public void removeProfile() {
        // Select profile name
        webDriver.clickOn(editAdminProfiles);
        this.webDriver.elementClick(newProfile);
        logger.info("Clicked Edit profile button for profile");
        // Delete profile avatar
        webDriver.clickOn(manageProfileDeleteProfile);
        webDriver.clickOn(manageProfileConfirmDeleteProfile);
        // Check profile deletion
        logger.info("Profile avatar deleted");
    }

    public void pageDisplayedNewProfile(){
        assertTrue("Element Button 'CANCELAR' in not displayed",manageProfileCreateProfileCancelButton.isDisplayed());
    }
}
