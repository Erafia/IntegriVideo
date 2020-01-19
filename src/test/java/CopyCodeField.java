import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CopyCodeField extends OpenCloseDriver {

    @Test
    public void testCodeCopy() throws IOException, UnsupportedFlavorException {
        WebElement copyButton = driver.findElement(By.xpath("//code[@class = 'component-code']"));
        String expectedText = copyButton.getText().replaceAll("\n|\r\n", "");
        copyButton.click();
        String copiedText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        Assert.assertEquals(copiedText, expectedText);
    }
}

