package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ExtentReportManager.getExtentReports();

    public static ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest saveToReport(String testName, String desc) {
        if (extent != null) {
            ExtentTest test = extent.createTest(testName, desc);
            extentTestMap.put((int) Thread.currentThread().getId(), test);
            return test;
        }else {
            return null;
        }
    }

    public static void addScreenShot(String message) {
        if (extent != null) {
            String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

            getTest().log(Status.INFO, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        }
    }

    public static void addScreenShot(Status status, String message) {
        if (extent != null) {
            String base64Image = "data:image/png;base64,"
                + ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);

            getTest().log(status, message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        }
    }

    public static void logMessage(String message) {
        if (extent != null) {
            getTest().log(Status.INFO, message);
        }
    }

    public static void logMessage(Status status, String message) {
        if (extent != null) {
            getTest().log(status, message);
        }
    }

}
