import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class OpenCloseDriver {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String xpathInputArea = "//textarea[@placeholder = 'Start typing here']";
    protected String xpathMessagesReceived = "//div[@class= 'integri-chat-message-text']";
    protected String xpathButtonSend = "//button[@title= 'Send message']";
    protected String xpathEditButton = "//span[@class= 'iv-icon iv-icon-pencil integri-chat-edit-message']";
    protected String xpathDeleteButton = "//span[@class= 'iv-icon iv-icon-trash2 integri-chat-remove-message']";
    protected String xpathErrorMessageEmpty = "//div[contains(text(), 'Message cannot be empty!')]";
    protected String xpathDemoVersion = "//div[@class= 'integri-demo-version']";
    protected String xpathSettingsButton = "//span[@class = 'integri-chat-settings integri-pointer']";
    protected String xpathSettingsForm = "//div[@class = 'integri-modal integri-modal-shown']";
    protected String xpathSaveSettingsButton = "//button[text()= 'Save']";
    protected String xpathStartUploadButton = "//button[text()= 'Start']";
    protected String xpathUploadButton = "//span[@class = 'integri-chat-manual-upload integri-pointer']";

    @BeforeMethod
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.get("https://dev.integrivideo.com/demo/chat/new");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathInputArea)));
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
