package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

public class IntegriUploadFileModal extends BasePage {
    private final static String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final By START_UPLOAD_BUTTON = By.xpath("//button[text()= 'Start']");
    private static final By UPLOAD_BUTTON = By.xpath("//span[@class = 'integri-chat-manual-upload integri-pointer']");
    private static final By UPLOAD_MODAL = By.xpath("//div[@class = 'integri-modal integri-modal-shown']");
    private static final By PATH_TO_FIELD = By.xpath("//input[@type = 'file']");
    private static final By MESSAGE_TEXT = By.xpath("//div[@class= 'integri-chat-message-text']");


    public IntegriUploadFileModal(WebDriver driver) {
        super(driver);
    }

    public void openUploadWindow() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPLOAD_BUTTON));
        driver.findElement(UPLOAD_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(UPLOAD_MODAL));
    }

    public void uploadFile(String... pathsToFile) {
        for (String path : pathsToFile) {
            driver.findElement(PATH_TO_FIELD).sendKeys(path);
            wait.until(ExpectedConditions.elementToBeClickable(START_UPLOAD_BUTTON));
        }
        driver.findElement(START_UPLOAD_BUTTON).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(UPLOAD_MODAL));

    }

    public void validateImagesUploaded(int imagesNumber) {
        wait.until(ExpectedConditions.numberOfElementsToBe(MESSAGE_TEXT, imagesNumber));
        assertEquals(imagesNumber, driver.findElements(MESSAGE_TEXT), "Images were not uplaoded");
    }
}
