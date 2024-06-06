package helpers;

import constants.ConfigData;
import drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CaptureHelpers {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

    public static void takeScreenShot(String screenshotName){
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            //Kiểm tra folder nếu không tồn tại thì tạo folder
            File theDir = new File(SystemHelpers.getCurrentDir() + ConfigData.SCREENSHOT_PATH);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
            // Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
            FileHandler.copy(source, new File(SystemHelpers.getCurrentDir() + ConfigData.SCREENSHOT_PATH + File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));
            LogUtils.info("Screenshot taken: " + screenshotName);
            LogUtils.info("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl());
        } catch (Exception e) {
            LogUtils.info("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
