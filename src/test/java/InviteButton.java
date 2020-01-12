import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class InviteButton extends OpenCloseDriver {

    @Test
    public void testInvitation() throws IOException, UnsupportedFlavorException {
        WebElement inviteButton = driver.findElement(By.id("invite-users-to-chat"));
        String currentURL = driver.getCurrentUrl();
        inviteButton.click();
        String invitationURL = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        Assert.assertEquals(currentURL, invitationURL);
    }
}
