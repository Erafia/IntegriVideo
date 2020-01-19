import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class EditMessage extends OpenCloseDriver {
    @Test
    public void testEditingMessage() {
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        WebElement editButton = driver.findElement(By.xpath(xpathEditButton));
        editButton.click();
        WebElement editableArea = driver.findElement(By.xpath("//textarea[contains(text(), '" + message + "')]"));
        editableArea.clear();
        editableArea.sendKeys("Edited");
        editableArea.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), "Edited");
    }

    @Test
    public void testEditingMessageEmpty() {
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        WebElement editButton = driver.findElement(By.xpath(xpathEditButton));
        editButton.click();
        WebElement editableArea = driver.findElement(By.xpath("//textarea[contains(text(), '" + message + "')]"));
        editableArea.clear();
        editableArea.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathErrorMessageEmpty)));
        Assert.assertTrue(driver.findElement(By.xpath(xpathErrorMessageEmpty)).isDisplayed());
    }
}
