import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class SendMessage extends OpenCloseDriver {

    @Test
    public void testSendingMessageViaEnter(){
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        inputArea.sendKeys(message);
        inputArea.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), message);
    }

    @Test
    public void testSendingMessageViaClick() {
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), message);
    }

    @Test
    public void testSendingLongMessage() {
        String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce dapibus orci vel urna fringilla efficitur. Praesent dapibus ex in tortor auctor, quis venenatis turpis elementum. Nam mauris tellus, ultrices sed ex vel, efficitur maximus nulla. Sed rhoncus pulvinar faucibus. Suspendisse potenti. Phasellus tincidunt magna sit amet magna iaculis, vel pulvinar elit venenatis. Vestibulum congue imperdiet nisl sed hendrerit. Morbi nec enim non dui tempor rutrum nec ut nulla. + Quisque venenatis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lacinia fringilla vitae vitae dui. Curabitur nec tellus odio. In luctus justo rutrum ligula bibendum, sed porttitoruisque venenatis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lacinia fringilla vitae vitae dui. Curabitur nec tellus odio. In luctus justo rutrum ligula bibendum, sed porttitoratis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lac";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), message);
    }

    @Test
    public void testSendingMessageWithLink() {
        String message = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), message);
    }

    @Test
    public void testSendingMessageWithJavaScript() {
        String message = "javascript:void(document.cookie=“authorization=true“);";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        inputArea.sendKeys(message);
        buttonSend.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathMessagesReceived)));
        ArrayList<WebElement> messagesReceived = (ArrayList<WebElement>)driver.findElements(By.xpath(xpathMessagesReceived));
        Assert.assertEquals(messagesReceived.get(0).getText(), message);
    }

    @Test
    public void testSendingMessageLimit() throws InterruptedException {
        String message = "MessageToSent";
        WebElement inputArea = driver.findElement(By.xpath(xpathInputArea));
        WebElement buttonSend = driver.findElement(By.xpath(xpathButtonSend));
        for (int i = 0; i < 11; i++){
            inputArea.sendKeys(message);
            buttonSend.click();
            Thread.sleep(1500); //все равно часть сообщений иногда "слипается" :(
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathDemoVersion)));
        Assert.assertTrue(driver.findElement(By.xpath(xpathDemoVersion)).isDisplayed());
    }
}
