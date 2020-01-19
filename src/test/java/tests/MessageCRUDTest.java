package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.IntegriChatPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class MessageCRUDTest extends BaseTest {
    IntegriChatPage chat;
    String message = "testmessage";
    String longMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce dapibus orci vel urna fringilla efficitur. Praesent dapibus ex in tortor auctor, quis venenatis turpis elementum. Nam mauris tellus, ultrices sed ex vel, efficitur maximus nulla. Sed rhoncus pulvinar faucibus. Suspendisse potenti. Phasellus tincidunt magna sit amet magna iaculis, vel pulvinar elit venenatis. Vestibulum congue imperdiet nisl sed hendrerit. Morbi nec enim non dui tempor rutrum nec ut nulla. + Quisque venenatis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lacinia fringilla vitae vitae dui. Curabitur nec tellus odio. In luctus justo rutrum ligula bibendum, sed porttitoruisque venenatis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lacinia fringilla vitae vitae dui. Curabitur nec tellus odio. In luctus justo rutrum ligula bibendum, sed porttitoratis risus porttitor lacus pretium, quis auctor purus suscipit. Praesent id neque at leo lac";
    String link = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    String javaScriptContainingMessage = "javascript:void(document.cookie=“authorization=true“);";
    String updatedMessage = "Edited";

    @BeforeMethod
    public void initChatPage() {
        chat = new IntegriChatPage(driver);
        chat.openPage();
    }

    @Test
    public void validateMessageSending() {
        chat.typeMessage(message);
        chat.sendMessageUsingButton();
        chat.messageShouldContainText(message, 1);
    }

    @Test
    public void validateMessageSendingViaEnter() {
        chat.typeMessage(message);
        chat.sendMessageUsingEnter();
        chat.messageShouldContainText(message, 1);
    }

    @Test
    public void validateSendingLongMessage() {
        chat.typeMessage(longMessage);
        chat.sendMessageUsingButton();
        chat.messageShouldContainText(longMessage, 1);
    }

    @Test
    public void validateSendingMessageWithLink() {
        chat.typeMessage(link);
        chat.sendMessageUsingButton();
        chat.messageShouldContainText(link, 1);
    }

    @Test
    public void validateSendingMessageWithJavaScript() {
        chat.typeMessage(javaScriptContainingMessage);
        chat.sendMessageUsingButton();
        chat.messageShouldContainText(javaScriptContainingMessage, 1);
    }

    @Test
    public void validateSendingMessagesMoreThanLimit() {
        chat.typeAndSendMessage(message, 11);
        chat.demoVersionIsDisplayed();
    }

    @Test
    public void validateEditingMessage() {
        chat.typeAndSendMessage(message, 1);
        chat.editMessage(updatedMessage);
        chat.messageShouldContainText(this.updatedMessage, 1);
    }

    @Test
    public void validateEditingMessageWithEmptyValue() {
        chat.typeAndSendMessage(message, 1);
        chat.editMessage("");
        chat.validationMessageIsDisplayed();
    }

    @Test
    public void validateDeletingMessage() {
        chat.typeAndSendMessage(message, 1);
        chat.deleteMessage();
    }

    @Test
    public void validateInvitationLinkGenerating() throws IOException, UnsupportedFlavorException {
        chat.clickInviteButton();
        chat.validateInvitationLink();
    }

    @Test
    public void validateCodeCopy() throws IOException, UnsupportedFlavorException {
        chat.clickCopyCodeButton();
        chat.validateCopiedCode();
    }

}
