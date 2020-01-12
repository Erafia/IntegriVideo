import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteMessage extends OpenCloseDriver {
    @Test
    public void testDeleteMessage() throws InterruptedException {
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        WebElement deleteButton = driver.findElement(By.xpath(xpathDeleteButton));
        deleteButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        wait.until(ExpectedConditions.textToBe(By.xpath(xpathMessagesReceived), "removed..."));
    }
}
