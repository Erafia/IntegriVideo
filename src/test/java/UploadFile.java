import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class UploadFile extends OpenCloseDriver {

    @Test
    public void testUploadOneImage() {
        String pathToFile = "C:\\Users\\hoshi\\Downloads\\test.jpg";
        WebElement uploadButton = driver.findElement(By.xpath(xpathUploadButton));
        uploadButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(pathToFile);
        driver.findElement(By.xpath(xpathStartUploadButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathSettingsForm)));
    }

    @Test
    public void testUploadTwoImages() {
        String pathToFile = "C:\\Users\\hoshi\\Downloads\\test.jpg";
        String pathToFile2 = "C:\\Users\\hoshi\\Downloads\\154004391414922977.jpg";
        WebElement uploadButton = driver.findElement(By.xpath(xpathUploadButton));
        uploadButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathSettingsForm)));
        driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(pathToFile);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathStartUploadButton)));
        driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(pathToFile2);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathStartUploadButton)));
        driver.findElement(By.xpath(xpathStartUploadButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathSettingsForm)));
    }
}
