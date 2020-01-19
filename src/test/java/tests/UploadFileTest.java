package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.IntegriUploadFileModal;

import java.io.File;

public class UploadFileTest extends BaseTest {
    IntegriUploadFileModal upload;
    String path;
    String path2;

    @BeforeSuite
    public void getFilesAbsolute() {
        File file = new File("src\\test\\resources\\files\\dunno1.jpg");
        File file2 = new File("src\\test\\resources\\files\\home2.jpg");
        path = file.getAbsolutePath();
        path2 = file2.getAbsolutePath();
    }


    @BeforeMethod
    public void initChatPage() {
        upload = new IntegriUploadFileModal(driver);
        upload.openUploadWindow();
    }

    @Test
    public void uploadOneImage() {
        upload.uploadFile(path);
        upload.validateImagesUploaded(1);
    }

    @Test
    public void uploadMultipleImages() {
        upload.uploadFile(path, path2);
        upload.validateImagesUploaded(2);

    }
}
