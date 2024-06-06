package listeners;

import constants.ConfigData;
import helpers.CaptureHelpers;
import helpers.RecordHelpers;
import org.testng.ITestListener;

import com.aventstack.extentreports.Status;
import helpers.PropertiesHelpers;
import org.testng.ITestContext;
import org.testng.ITestResult;
import reports.AllureManager;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.LogUtils;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("******************Start*******************");
        PropertiesHelpers.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("******************Finish******************");
        if(ConfigData.EXTENT_REPORT.equals("true")){
            ExtentReportManager.getExtentReports().flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("********Start Test Case: " + result.getName() + "********");

        if(ConfigData.RECORD.equals("true")){
            RecordHelpers.startRecord(result.getName());
        }
        if(ConfigData.EXTENT_REPORT.equals("true")){
            ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("=====> " + " Test Success: " + result.getName());

        if(ConfigData.RECORD.equals("true")){
            RecordHelpers.stopRecord();
        }
        if(ConfigData.EXTENT_REPORT.equals("true")){
            ExtentTestManager.logMessage(Status.PASS, result.getName() + " is PASSED.");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.info("=====> " + " Test Failure: " + result.getName());
        if(ConfigData.SCREENSHOT.equals("true")){
            CaptureHelpers.takeScreenShot(result.getName());
        }
        if(ConfigData.RECORD.equals("true")){
            RecordHelpers.stopRecord();
        }
        if(ConfigData.EXTENT_REPORT.equals("true")){
            ExtentTestManager.addScreenShot(result.getName());
            ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
            ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is FAILED.");
        }
        AllureManager.saveTextLog(result.getName() + " is FAILED.");
        AllureManager.saveScreenshotPNG();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("=====> " + " Skipped Test: " + result.getName());

        if(ConfigData.RECORD.equals("true")){
            RecordHelpers.stopRecord();
        }
        if(ConfigData.EXTENT_REPORT.equals("true")){
            ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.info("=====> " + "on Test Failed But Within Success Percentage" + result.getName());
    }
}
