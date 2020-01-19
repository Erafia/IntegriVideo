package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

public class IntegriSetttingsModal extends BasePage {
    private final static String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final By SETTINGS_BUTTON = By.xpath("//span[@class = 'integri-chat-settings integri-pointer']");
    private static final By DISPLAYED_NAME = By.xpath("//div[@class = 'integri-session-user-name']");
    private static final By NAME_FIELD = By.name("userName");
    private static final By EMAIL_FIELD = By.name("userEmail");
    private static final By IMAGE_FIELD = By.name("userPic");
    private static final By SAVE_BUTTON = By.xpath("//button[text()= 'Save']");
    private static final By CANCEL_BUTTON = By.xpath("//a[contains(text(), 'Cancel')]");
    private static final By SETTINGS_MODAL = By.xpath("//div[@class = 'integri-modal integri-modal-shown']");

    public IntegriSetttingsModal(WebDriver driver) {
        super(driver);
    }

    public void openSettings() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SETTINGS_BUTTON));
        driver.findElement(SETTINGS_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SETTINGS_MODAL));
    }

    public void updateUserField(String value, String field) {
        if (field.equalsIgnoreCase("name")) {
            driver.findElement(NAME_FIELD).clear();
            driver.findElement(NAME_FIELD).sendKeys(value);
        } else if (field.equalsIgnoreCase("email")) {
            driver.findElement(EMAIL_FIELD).sendKeys(value);
        } else if (field.equalsIgnoreCase("photo")) {
            driver.findElement(IMAGE_FIELD).sendKeys(value);
        }
        driver.findElement(SAVE_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SETTINGS_MODAL));
    }

    public void validateTheFieldUpdated(String value, String field) {
        driver.findElement(SETTINGS_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SETTINGS_MODAL));
        if (field.equalsIgnoreCase("name")) {
            assertEquals(driver.findElement(NAME_FIELD).getAttribute("value"), value);
        } else if (field.equalsIgnoreCase("email")) {
            assertEquals(driver.findElement(EMAIL_FIELD).getAttribute("value"), value);
        } else if (field.equalsIgnoreCase("photo")) {
            assertEquals(driver.findElement(IMAGE_FIELD).getAttribute("value"), value);
        }
    }

    public void clickCancelButton() {
        driver.findElement(CANCEL_BUTTON).click();
    }

    public void validateDisplayedNameEqualSetName(String value) {
        String displayedName = driver.findElement(DISPLAYED_NAME).getText();
        assertEquals(displayedName, value);
    }
}
