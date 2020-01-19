package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.IntegriSetttingsModal;

public class ProfileUpdateTest extends BaseTest {
    IntegriSetttingsModal settings;
    String name = "Updated";
    String email = "email@mail.com";
    String photo = "https://sun9-10.userapi.com/c855628/v855628290/d7786/w_jbbuM27Xw.jpg";

    @BeforeMethod
    public void initChatPage() {
        settings = new IntegriSetttingsModal(driver);
        settings.openSettings();
    }

    @Test
    public void validateNameUpdated() {
        settings.updateUserField(name, "name");
        settings.validateTheFieldUpdated(name, "name");
        settings.clickCancelButton();
        settings.validateDisplayedNameEqualSetName(name);
    }

    @Test
    public void validateEmailUpdated() {
        settings.updateUserField(email, "email");
        settings.validateTheFieldUpdated(email, "email");
    }

    @Test
    public void validatePhotoUpdated() {
        settings.updateUserField(photo, "photo");
        settings.validateTheFieldUpdated(photo, "photo");
    }

}
