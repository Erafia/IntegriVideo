import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileUpdate extends OpenCloseDriver {

    @Test
    public void testNameUpdate() {
        String name = "Updated";
        WebElement settingsButton = driver.findElement(By.xpath(xpathSettingsButton));
        WebElement userName = driver.findElement(By.xpath("//div[@class = 'integri-session-user-name']"));
        settingsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        WebElement nameField = driver.findElement(By.xpath("//input[@name= 'userName']"));
        WebElement buttonSave = driver.findElement(By.xpath(xpathSaveSettingsButton));
        nameField.sendKeys(name);
        buttonSave.click();
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class = 'integri-session-user-name']"), "Updated"));
        Assert.assertEquals(userName.getText(), name);
    }
    @Test
    public void testEmailUpdate() {
        String email = "email@mail.com";
        WebElement settingsButton = driver.findElement(By.xpath(xpathSettingsButton));
        settingsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        WebElement emailField = driver.findElement(By.xpath("//input[@name= 'userEmail']"));
        WebElement buttonSave = driver.findElement(By.xpath(xpathSaveSettingsButton));
        emailField.sendKeys(email);
        buttonSave.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        settingsButton.click();
        Assert.assertEquals(emailField.getAttribute("value"), email);
    }

    @Test
    public void testPhotoUpdate() {
        String image = "https://bugaga.ru/uploads/posts/2018-02/1517917230_podborka-2.jpg";
        WebElement settingsButton = driver.findElement(By.xpath(xpathSettingsButton));
        settingsButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        WebElement userPic = driver.findElement(By.xpath("//input[@name= 'userPic']"));
        WebElement buttonSave = driver.findElement(By.xpath(xpathSaveSettingsButton));
        userPic.sendKeys(image);
        buttonSave.click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        settingsButton.click();
        Assert.assertEquals(userPic.getAttribute("value"), image);
    }
}
