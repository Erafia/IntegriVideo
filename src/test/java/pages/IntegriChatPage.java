package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class IntegriChatPage extends BasePage {
    private final static String URL = "https://dev.integrivideo.com/demo/chat/new";
    private static final By INPUT_AREA = By.xpath("//textarea[@placeholder = 'Start typing here']");
    private static final By SEND_BUTTON = By.xpath("//button[@title= 'Send message']");
    private static final By EDIT_BUTTON = By.xpath("//span[@class= 'iv-icon iv-icon-pencil integri-chat-edit-message']");
    private static final By EDITABLE_AREA = By.xpath("//div[@class = 'integri-chat-message-text']/following-sibling::textarea");
    private static final By DELETE_BUTTON = By.xpath("//span[@class= 'iv-icon iv-icon-trash2 integri-chat-remove-message']");
    private static final By ERROR_EMPTY_MESSAGE = By.xpath("//div[contains(text(), 'Message cannot be empty!')]");
    private static final By DEMO_VERSION_SCREEN = By.xpath("//div[@class= 'integri-demo-version']");
    private static final By MESSAGE_TEXT = By.xpath("//div[@class= 'integri-chat-message-text']");

    public IntegriChatPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOfElementLocated(INPUT_AREA));
    }

    public void typeMessage(String message) {
        driver.findElement(INPUT_AREA).sendKeys(message);
    }

    public void sendMessageUsingButton() {
        driver.findElement(SEND_BUTTON).click();
    }

    public void sendMessageUsingEnter() {
        driver.findElement(INPUT_AREA).sendKeys(Keys.ENTER);
    }

    public void typeAndSendMessage(String message, int numberOfMessages) {
        for (int i = 0; i < numberOfMessages; i++) {
            typeMessage(message);
            sendMessageUsingButton();
            if (i < 10) {
                wait.until(ExpectedConditions.numberOfElementsToBe(MESSAGE_TEXT, i + 1));
            }
        }
    }

    public void editMessage(String updatedMessage) {
        driver.findElement(EDIT_BUTTON).click();
        driver.findElement(EDITABLE_AREA).clear();
        driver.findElement(EDITABLE_AREA).sendKeys(updatedMessage);
        driver.findElement(EDITABLE_AREA).sendKeys(Keys.ENTER);
        if (updatedMessage.length() > 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(EDITABLE_AREA));
        }
    }

    public void demoVersionIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DEMO_VERSION_SCREEN));
        assertTrue(driver.findElement(DEMO_VERSION_SCREEN).isDisplayed(), "The blocking Demo Version layout is not displayed");
    }

    public void validationMessageIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_EMPTY_MESSAGE));
        assertTrue(driver.findElement(ERROR_EMPTY_MESSAGE).isDisplayed(), "Possible to update message with empty value");
    }

    public void deleteMessage() {
        driver.findElement(DELETE_BUTTON).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.textToBe((MESSAGE_TEXT), "removed..."));
        //стоит ли использовать такой wait, если при фейле теста, падение произойдет именно на этой строке?
        String text = driver.findElements(MESSAGE_TEXT).get(0).getText();
        assertEquals("removed...", text, "Message was not deleted");
    }

    public void clickInviteButton() {
        driver.findElement(By.id("invite-users-to-chat")).click();
    }

    public void validateInvitationLink() throws IOException, UnsupportedFlavorException {
        String currentURL = driver.getCurrentUrl();
        String invitationURL = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        assertEquals(currentURL, invitationURL);
    }

    public void clickCopyCodeButton() {
        driver.findElement(By.xpath("//code[@class = 'component-code']")).click();
    }

    public void validateCopiedCode() throws IOException, UnsupportedFlavorException {
        String expectedText = driver.findElement(By.xpath("//code[@class = 'component-code']"))
                .getText()
                .replaceAll("\n|\r\n", "");
        String copiedText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        assertEquals(copiedText, expectedText);
    }

    /**
     * @param message
     * @param messageNumber starts from 1
     */
    public void messageShouldContainText(String message, int messageNumber) {
        wait.until(ExpectedConditions.numberOfElementsToBe(MESSAGE_TEXT, messageNumber));
        String text = driver.findElements(MESSAGE_TEXT).get(messageNumber - 1).getText();
        assertEquals(message, text, "Text is different");
    }
}
