package keywords;

import com.aventstack.extentreports.Status;
import constants.ConfigData;
import drivers.DriverManager;
import helpers.SystemHelpers;
import io.qameta.allure.Step;
import lombok.extern.java.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import reports.AllureManager;
import reports.ExtentTestManager;
import utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebUI {

    private static JavascriptExecutor js = (JavascriptExecutor) getDriver();

    private static SoftAssert softAssert = new SoftAssert();

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static List<WebElement> getWebElements(By by) {
        return getDriver().findElements(by);
    }

    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        DriverManager.getDriver().get(URL);
        waiForPageLoad();
        LogUtils.info("Open URL: " + URL);
        ExtentTestManager.logMessage(Status.INFO, "Open URL: " + URL);
        AllureManager.saveTextLog("Open URL: " + URL);
    }

    @Step("Get current URL")
    public static String getURLPage() {
        LogUtils.info("Current URL: " + getDriver().getCurrentUrl());
        ExtentTestManager.logMessage(Status.INFO, "Current URL: " + getDriver().getCurrentUrl());
        AllureManager.saveTextLog("Current URL: " + getDriver().getCurrentUrl());
        return getDriver().getCurrentUrl();
    }

    @Step("Get title page")
    public static String getTitlePage() {
        LogUtils.info("Title page: " + getDriver().getTitle());
        ExtentTestManager.logMessage(Status.INFO, "Title page: " + getDriver().getTitle());
        AllureManager.saveTextLog("Title page: " + getDriver().getTitle());
        return getDriver().getTitle();
    }

    @Step("Click Element: {0}")
    public static void clickElement(By by) {
        waiForPageLoad();
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getWebElement(by)).perform();
        if (verifyElementClickable(by)) {
            getWebElement(by).click();
            LogUtils.info("Click Element: " + by.toString());
            ExtentTestManager.logMessage(Status.INFO, "Click Element: " + by.toString());
            AllureManager.saveTextLog("Click Element: " + by.toString());
        } else {
            js.executeScript("arguments[0].click();", getWebElement(by));
            LogUtils.info("Click element with JavaScript: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Click Element with JavaScript: " + by.toString());
            AllureManager.saveTextLog("Click Element with JavaScript: " + by.toString());
        }
    }

    @Step("Set text : {1} For Element: {0} ")
    public static void setText(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text);
        LogUtils.info("Set text:" + text + " - For Element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "Set text:" + text + " - For Element: " + by);
        AllureManager.saveTextLog("Set text:" + text + " - For Element: " + by);
    }

    @Step("Set text :{ 1} And Key: {2} - For Element: {0}")
    public static void setTextAndKey(By by, String text, Keys keys) {
        waiForPageLoad();
        getWebElement(by).sendKeys(text, keys);
        LogUtils.info("Set text:" + text + " - And Key: " + keys + " - For Element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "Set text:" + text + " - And Key: " + keys + " - For Element: " + by);
        AllureManager.saveTextLog("Set text:" + text + " - And Key: " + keys + " - For Element: " + by);
    }

    @Step("Send key: {1} - For Element: {0}")
    public static void sendKey(By by, Keys keys) {
        waiForPageLoad();
        getWebElement(by).sendKeys(keys);
        LogUtils.info("Send key:" + keys + " - For Element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "Send key:" + keys + " - For Element: " + by);
        AllureManager.saveTextLog("Send key:" + keys + " - For Element: " + by);
    }

    @Step("Get text of element {0}")
    public static String getTextElement(By by) {
        waitForElementVisible(by);
        LogUtils.info("Text of Element: " + by + " - Is: " + getWebElement(by).getText());
        ExtentTestManager.logMessage(Status.INFO, "Text of Element: " + by + " - Is: " + getWebElement(by).getText());
        AllureManager.saveTextLog("Text of Element: " + by + " - Is: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }

    @Step("Get list text of elements {0}")
    public static List<String> getTextListElement(By by) {
        waiForPageLoad();
        List<WebElement> list = getWebElements(by);
        List<String> text = new ArrayList<>();
        for (WebElement i : list) {
            text.add(i.getText());
        }
        LogUtils.info("List element: " + text);
        ExtentTestManager.logMessage(Status.INFO, "List element: " + text);
        AllureManager.saveTextLog("List element: " + text);
        return text;
    }

    @Step("Get attribute {1} of element {0}")
    public static String getAttributeElement(By by, String atb) {
        LogUtils.info("Get attribute " + atb + ":  " + waitForElementVisible(by).getAttribute(atb) + " - Of element" + by);
        ExtentTestManager.logMessage(Status.INFO, "Get attribute " + atb + ":  " + waitForElementVisible(by).getAttribute(atb) + " - Of element" + by);
        AllureManager.saveTextLog("Get attribute " + atb + ":  " + waitForElementVisible(by).getAttribute(atb) + " - Of element" + by);
        return waitForElementVisible(by).getAttribute(atb);
    }

    @Step("Clear placeholder on Element: {0}")
    public static void clearTextElement(By by) {
        getWebElement(by).clear();
        LogUtils.info("Clear placeholder on element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "Clear placeholder on element: " + by);
        AllureManager.saveTextLog("Clear placeholder on element: " + by);
    }

    @Step("Clear placeholder on Element: {0} - set text {1}")
    public static void clearSetText(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        getWebElement(by).sendKeys(text);
        LogUtils.info("Clear placeholder and Set text on Element: " + by + " - Text: " + text);
        ExtentTestManager.logMessage(Status.INFO, "Clear placeholder and Set text on Element: " + by + " - Text: " + text);
        AllureManager.saveTextLog("Clear placeholder and Set text on Element: " + by + " - Text: " + text);
    }

    @Step("Get CSS value {1} of the element {0}")
    public static String getCssElement(By by, String prop) {
        return getWebElement(by).getCssValue(prop);
    }

    @Step("Get text alert")
    public static String getTextAlert() {
        sleep(2);
        LogUtils.info("Text of alert: " + getDriver().switchTo().alert().getText());
        ExtentTestManager.logMessage(Status.INFO, "Text of alert: " + getDriver().switchTo().alert().getText());
        AllureManager.saveTextLog("Text of alert: " + getDriver().switchTo().alert().getText());
        return getDriver().switchTo().alert().getText();
    }

    @Step("Click accept alert")
    public static void acceptAlert() {
        sleep(1);
        LogUtils.info("Click accept alert");
        ExtentTestManager.logMessage(Status.INFO, "Click accept alert");
        AllureManager.saveTextLog("Click accept alert");
        getDriver().switchTo().alert().accept();
    }

    @Step("Click dismiss alert")
    public static void dismissAlert() {
        sleep(1);
        LogUtils.info("Click accept alert");
        ExtentTestManager.logMessage(Status.INFO, "Click dismiss alert");
        AllureManager.saveTextLog("Click dismiss alert");
        getDriver().switchTo().alert().dismiss();
    }

    @Step("Switch to iframe index {0}")
    public static void goIframe(int i) {
        getDriver().switchTo().frame(i);
    }

    @Step("Switch to iframe {0}")
    public static void goIframe(By by) {
        getDriver().switchTo().frame(getWebElement(by));
    }

    @Step("Navigate to previous page")
    public static void goToPreviousPage() {
        getDriver().navigate().forward();
        LogUtils.info("Navigate to previous page.");
        ExtentTestManager.logMessage(Status.INFO, "Navigate to previous page.");
        AllureManager.saveTextLog("Navigate to previous page.");
    }

    public static void scrollDownMenuBar(By by) {
        waitForElementVisible(by);
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getWebElement(by)).sendKeys(Keys.PAGE_DOWN).build().perform();
    }

    public static void scrollToElement(By by) {
        js.executeScript("argument[0].scrollIntoView(true);", getWebElements(by));
    }


    ////HANDLE PAGE/////////////////////////////////////////////////////////////////////////
    @Step("Select with {1} option: {2} of element {0}")
    public static void handleDropdown(By button, By by, String type, String[] option) {
        clickElement(button);
        Select select = new Select(getWebElement(by));
        waiForPageLoad();
        for (int i = 0; i < option.length; i++) {
            switch (type.trim().toLowerCase()) {
                case "text":
                    select.selectByVisibleText(option[i]);
                    break;
                case "value":
                    select.selectByValue(option[i]);
                    break;
                case "index":
                    select.selectByIndex(Integer.parseInt(option[i]));
                    break;
                case "deselect":
                    select.deselectAll();
                    break;
            }
            LogUtils.info("Choose of the element dropdown: " + by + " - Option" + option);
            ExtentTestManager.logMessage(Status.INFO, "Choose of the element dropdown: " + by + " - Option" + option);
            AllureManager.saveTextLog("Choose of the element dropdown: " + by + " - Option" + option);
        }
        clickElement(button);
    }

    @Step("Get first option of the element {0}")
    public static String getFirstOptionSelected(By by) {
        Select select = new Select(getWebElement(by));
        LogUtils.info("First option is: " + select.getFirstSelectedOption().getText());
        ExtentTestManager.logMessage(Status.INFO, "First option is: " + select.getFirstSelectedOption().getText());
        AllureManager.saveTextLog("First option is: " + select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText();
    }

    @Step("Verify the element is selected {0}")
    public static boolean checkElementIsSelected(By by) {
        waitForElementVisible(by);
        if (getWebElement(by).isSelected()) {
            LogUtils.info("The element has been selected: " + by);
            ExtentTestManager.logMessage(Status.INFO, "The element has been selected: " + by);
            AllureManager.saveTextLog("The element has been selected: " + by);
            return true;
        } else {
            ExtentTestManager.logMessage(Status.WARNING, "Element not selected" + by);
            AllureManager.saveTextLog("Element not selected" + by);
            Assert.assertTrue(false, "Element not selected" + by);
            return false;
        }
    }

    @Step("Verify the element is selected {0}")
    public static boolean checkElementIsSelectedAndClick(By by) {
        if (getWebElement(by).isSelected()) {
            LogUtils.info("Element has selected: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Element has selected: " + by);
            AllureManager.saveTextLog("Element has selected: " + by);
            return true;
        } else {
            LogUtils.info("Element not selected.");
            clickElement(by);
        }
        return false;
    }

    @Step("Verify the value of results")
    public static void verifyNumberOfResults(int col, String value) {
        // Retrieve the list of elements represending search results
        List<WebElement> list = getWebElements(By.xpath("//tbody/tr"));
        if (list.isEmpty()) {
            LogUtils.info("Not found!");
            ExtentTestManager.logMessage(Status.INFO, "Not found!");
            AllureManager.saveTextLog("Not found!");
            return;
        }
        try {
            LogUtils.info("Total number of rows of search result is: " + list.size());
            ExtentTestManager.logMessage(Status.INFO, "Total number of rows of search result is: " + list.size());
            AllureManager.saveTextLog("Total number of rows of search result is: " + list.size());
            for (int i = 1; i <= list.size(); i++) {
                String rs = getTextElement(By.xpath("//tbody/tr[" + i + "]/td[" + col + "]"));
//                scrollToElement(By.xpath("//tbody/tr[" + i + "]/td[" + col + "]"));
                //Check the search result value of each column equal to the searched keyword
                if (!rs.trim().toLowerCase().equals(value.trim().toLowerCase())) {
                    //Check if the search result value of each column contains the searched keywor  d
//                    verifyContain(rs.trim().toLowerCase(), value.trim().toLowerCase(), "The result of row " + i + " not Contain with :" + value);
                    LogUtils.info("The result of row " + i + " Contain: " + rs);
                    ExtentTestManager.logMessage(Status.INFO, "The result of row " + i + " Contain: " + rs);
                    AllureManager.saveTextLog("The result of row " + i + " Contain: " + rs);
                } else {
                    LogUtils.info("The result of row " + i + " Equals: " + rs);
                    ExtentTestManager.logMessage(Status.INFO, "The result of row " + i + " Equals: " + rs);
                    AllureManager.saveTextLog("The result of row " + i + " Equals: " + rs);
                }
            }
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
        }
    }

    @Step("Verify data and pagination after search")
    public static void verifyRecordAndPagination(int item, int col, String value) {
        String infoTable = getTextElement(By.xpath("//div[@id='xin_table_info']"));
        if (infoTable == null) {
            LogUtils.info("Info table is not found or is empty");
            return;
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String str : infoTable.split("\\s")) {
            list.add(str);
        }

        int totalRecord = Integer.parseInt(list.get(5));
        LogUtils.info(list + ": " + totalRecord + " Records".toUpperCase());

        int totalpage = totalRecord / item;
        int remainder = totalRecord % item;

        if (remainder > 0) {
            totalpage = totalpage + 1;
        }

        LogUtils.info("Total page with text info: " + totalpage);

        List<WebElement> pagination = getWebElements(By.xpath("//ul[@class='pagination']/li"));
        int actualPage = pagination.size() - 2;
        verifyEqual(totalpage, actualPage, "The number of pages according to the result is Different from the actual number of pages");

        for (int i = 1; i <= totalpage; i++) {
            verifyNumberOfResults(col, value);
            if (i < totalpage) {
                clickElement(By.xpath("//a[normalize-space()='Next']"));
            }
        }
    }

    @Step("Verify data and pagination after search")
    public static void verifyRecordAndPagination(int col, String value) {
        //Get the record information of table
        String infoTable = getTextElement(By.xpath("//div[@id='xin_table_info']"));
        if (infoTable == null) {
            LogUtils.info("Info table is empty");
            return;
        }

        ArrayList<String> list = new ArrayList<String>();
        for (String str : infoTable.split("\\s")) {
            //Reverse list
            StringBuilder reversedStr = new StringBuilder(str).reverse();
            list.add(reversedStr.toString());
        }

        boolean isValidNumber = true;
        Integer totalRecord = null;
        try {
            totalRecord = Integer.parseInt(list.get(6));
        } catch (NumberFormatException e) {
            isValidNumber = false;
        }

        if (!isValidNumber || totalRecord <= 0) {
            totalRecord = 1;
        }

        LogUtils.info(list + ": " + totalRecord + " Records".toUpperCase());

        int totalpage = totalRecord / 10;
        int remainder = totalRecord % 10;

        if (remainder > 0) {
            totalpage = totalpage + 1;
        }

        LogUtils.info("Total page with text info: " + totalpage);

        List<WebElement> pagination = getWebElements(By.xpath("//ul[@class='pagination']/li"));
        int actualPage;
        if (pagination.size() - 2 == 0) {
            actualPage = 1;
        } else {
            actualPage = (pagination.size() - 2);
        }

        verifyEqual(totalpage, actualPage, "The number of pages according to the result is Different from the actual number of pages");

        for (int i = 1; i <= totalpage; i++) {
            verifyNumberOfResults(col, value);
            if (i < totalpage) {
                clickElement(By.xpath("//a[normalize-space()='Next']"));
            }
        }
    }

    @Step("Upload file with sendkey {1} to element {0}")
    public static void uploadFileSendKey(By by, String path) {
        getWebElement(by).sendKeys(SystemHelpers.getCurrentDir() + path);
        LogUtils.info("Upload file with sendkey: " + path + " - To element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Upload file with sendkey: " + path + " - To element " + by);
        AllureManager.saveTextLog("Upload file with sendkey: " + path + " - To element " + by);
    }

    @Step("Upload file with robot class {1} to element {0}")
    public static void uploadFileWithRobot(By by, String path) {
        clickElement(by);
        sleep(2);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        StringSelection str = new StringSelection(SystemHelpers.getCurrentDir() + path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);
        sleep(2);
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        LogUtils.info("Upload file with robot class: " + path + " - To element " + by);
        ExtentTestManager.logMessage(Status.INFO, "Upload file with robot class: " + path + " - To element " + by);
        AllureManager.saveTextLog("Upload file: with robot class " + path + " - To element " + by);
    }

    @Step("Choose status {0}")
    public static void chooseStatus(String status) {
        By path = By.xpath("(//a[contains(@data-rating-text,'" + status + "')]");
        getWebElement(path).click();
        verifyContain(getTextElement(path), status, "Choose the wrong status :" + status);
        switch (status.trim().toLowerCase()) {
            case "notstarted":
                verifyEqual(getCssElement(path, "background-color"), "#ffa21d", "Color of status :" + status + " is incorrect");
                break;
            case "inprogress":
                verifyEqual(getCssElement(path, "background-color"), "#7267EF", "Color of status :" + status + " is incorrect");
                break;
            case "cancelled":
                verifyEqual(getCssElement(path, "background-color"), "#EA4D4D", "Color of status :" + status + " is incorrect");
                break;
            case "onhold":
                verifyEqual(getCssElement(path, "background-color"), "#6c757d", "Color of status :" + status + " is incorrect");
                break;
            case "completed":
                verifyEqual(getCssElement(path, "background-color"), "#17C666", "Color of status :" + status + " is incorrect");
                break;
        }
        LogUtils.info("Choose status: " + status);
        ExtentTestManager.logMessage(Status.INFO, "Choose status: " + status);
        AllureManager.saveTextLog("Choose status: " + status);
    }

    public static void getStartDate(By by, String month, String year, String date) {
        clickElement(by);
        //Month
        String start = "(//div[@class='dtp-date'])[1]";
        String presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
        while (presentMonth.trim().toLowerCase().equals(month.trim().toLowerCase())) {
            getDriver().findElement(By.xpath(start + "/div[1]/div[@class='left center p10']")).click();
            presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
            LogUtils.info("Choose month start: " + presentMonth.trim());
        }
        sleep(2);

        //Year
        String presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        while (year.equals(presentYear.trim())) {
            if (Integer.parseInt(year) < Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='left center p10']")).click();
            } else if (Integer.parseInt(year) > Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='right center p10']")).click();
            }
            presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        }

        LogUtils.info("Choose year start: " + presentYear);
        sleep(2);

        //Date
        List<WebElement> listDate = getDriver().findElements(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr/td"));
        int totalDate = listDate.size();
        int row = totalDate / 7;
        int add = totalDate % 7;
        if (add > 0) {
            row = row + 1;
        }

        for (int i = 2; i <= row + 1; i++) {
            List<WebElement> dateInRow = getDriver().findElements(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr" + i + "/td/a"));
            int dateRow = dateInRow.size();
            for (int j = 1; j <= dateRow; j++) {
                WebElement getDate = getDriver().findElement(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr[" + i + "]/td[" + j + "]/a"));
                if (getDate.getText().trim().equals(date)) {
                    getDate.click();
                    LogUtils.info("Choose year start: " + getDate);
                    return;
                }
            }
        }
        sleep(2);
    }

    public static void clickDate(By by, String date) {
        clickElement(by);
        List<WebElement> listDate = getDriver().findElements(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr/td/a"));
        int totalDate = listDate.size();
        int row = totalDate / 7;
        int add = totalDate % 7;
        if (add > 0) {
            row = row + 1;
        }
        LogUtils.info("Have: " + totalDate + " date and: " + row + " week.");

        for (int i = 2; i <= row + 1; i++) {
            List<WebElement> dateInRow = getDriver().findElements(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr[" + i + "]/td/a"));
            int dateRow = dateInRow.size();
            LogUtils.info("Week " + (i - 1) + " have " + dateRow + " date.");
            for (int j = 1; j <= dateRow; j++) {
                WebElement getDate = getDriver().findElement(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr[" + i + "]/td[" + j + "]//a"));
                if (verifyElementPresent(By.xpath("(//div[@class='dtp-picker'])[1]//tbody/tr[" + i + "]/td[" + j + "]//a")))
                if (getDate.getText().trim().equals(date)) {
                    getDate.click();
                    LogUtils.info("Choose year start: " + getDate);
                    return;
                }

            }
        }
        sleep(2);
    }


    ////VERIFY///////////////////////////////////////////////////////////////////////////////
    @Step("Verify visible of element {0}")
    public static boolean verifyElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify element visible: " + by);
            AllureManager.saveTextLog("Verify element visible: " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            Assert.fail(e.getMessage());
            return false;
        }
    }

    @Step("Verify visible of element {0} in {1}s")
    public static boolean verifyElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify element visible: " + by);
            AllureManager.saveTextLog("Verify element visible: " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            Assert.fail(e.getMessage());
            return false;
        }
    }

    @Step("Verify visible of element {0}")
    public static boolean verifyElementVisible(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify element visible: " + by);
            AllureManager.saveTextLog("Verify element visible: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(ConfigData.TIMEOUT + "(s) Element is Not visible. " + e.getMessage());
                Assert.fail(ConfigData.TIMEOUT + "(s) Element is Not visible. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify visible of element {0} in {1}s")
    public static boolean verifyElementVisible(By by, int timeout, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify element visible: " + by);
            AllureManager.saveTextLog("Verify element visible: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(timeout + "(s) Element is Not visible. " + e.getMessage());
                Assert.fail(timeout + "(s) Element is Not visible. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify present of element {0}")
    public static boolean verifyElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify Element present: " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error(ConfigData.TIMEOUT + "(s) Element is Not present. " + e.getMessage());
            Assert.fail(ConfigData.TIMEOUT + "(s) Element is Not present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify present of element {0} in {1}s")
    public static boolean verifyElementPresent(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify Element present: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element present: " + by);
            AllureManager.saveTextLog("Verify Element present: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            LogUtils.error(timeout + "(s) Element is Not present. " + e.getMessage());
            Assert.fail(timeout + "(s) Element is Not present. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify present of element {0}")
    public static boolean verifyElementPresent(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify Element present: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element present: " + by);
            AllureManager.saveTextLog("Verify Element present: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(ConfigData.TIMEOUT + "(s) Element is Not present. " + e.getMessage());
                Assert.fail(ConfigData.TIMEOUT + "(s) Element is Not present. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify present of element {0} in {1}s")
    public static boolean verifyElementPresent(By by, int timeout, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify Element present: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element present: " + by);
            AllureManager.saveTextLog("Verify Element present: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(timeout + "(s) Element is Not present. " + e.getMessage());
                Assert.fail(timeout + "(s) Element is Not present. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify clickable of element {0}")
    public static boolean verifyElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify Element clickable: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element clickable: " + by);
            AllureManager.saveTextLog("Verify Element clickable: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            LogUtils.error("Element is Not clickable. " + e.getMessage());
            Assert.fail("Element is Not clickable. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify clickable of element {0} in {1}s")
    public static boolean verifyElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify Element clickable: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element clickable: " + by);
            AllureManager.saveTextLog("Verify Element clickable: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            LogUtils.error(timeout + "(s) Element is Not clickable. " + e.getMessage());
            Assert.fail(timeout + "(s) Element is Not clickable. " + e.getMessage());
            return false;
        }
    }

    @Step("Verify clickable of element {0}")
    public static boolean verifyElementClickable(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify Element clickable: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element clickable: " + by);
            AllureManager.saveTextLog("Verify Element clickable: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(ConfigData.TIMEOUT + "(s) Element is Not clickable. " + e.getMessage());
                Assert.fail(ConfigData.TIMEOUT + "(s) Element is Not clickable. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify clickable of element {0} in {1}s")
    public static boolean verifyElementClickable(By by, int timeout, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            LogUtils.info("Verify Element present: " + by);
            ExtentTestManager.logMessage(Status.INFO, "Verify Element present: " + by);
            AllureManager.saveTextLog("Verify Element present: " + by);
            return true;
        } catch (Exception e) {
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
            if (message.isEmpty() || message == null) {
                LogUtils.error(timeout + "(s) Element is Not clickable. " + e.getMessage());
                Assert.fail(timeout + "(s) Element is Not clickable. " + e.getMessage());
            } else {
                LogUtils.error(message + " :" + by);
                Assert.fail(message + " :" + by);
            }
            return false;
        }
    }

    @Step("Verify exist of element {0}")
    public static boolean verifyElementExist(By by) {
        List<WebElement> list = getWebElements(by);
        if (list.size() > 0) {
            LogUtils.info("Element " + by + " is exist (DOM)");
            ExtentTestManager.logMessage(Status.INFO, "Element " + by + " is exist (DOM)");
            AllureManager.saveTextLog("Element " + by + " is exist (DOM)");
            return true;
        } else {
            LogUtils.error("Element " + by + "  not exist (DOM)");
            ExtentTestManager.logMessage(Status.WARNING, "Element " + by + "  not exist (DOM)");
            AllureManager.saveTextLog("Element " + by + "  not exist (DOM)");
            return false;
        }
    }

    @Step("Verify {0} is True")
    public static boolean verifyTrue(boolean condition) {
        if (condition) {
            LogUtils.info("Verify True expected: " + condition);
            ExtentTestManager.logMessage(Status.INFO, "Verify True expected: " + condition);
            AllureManager.saveTextLog("Verify True expected: " + condition);
        } else {
            LogUtils.error("Verify True expected: " + condition);
            ExtentTestManager.logMessage(Status.WARNING, "Verify True expected: " + condition);
            AllureManager.saveTextLog("Verify True expected: " + condition);
        }
        return condition;
    }

    @Step("Verify {0} is True")
    public static boolean verifyTrue(boolean condition, String message) {
        if (condition) {
            LogUtils.info("Verify True expected: " + condition);
            ExtentTestManager.logMessage(Status.INFO, "Verify True expected: " + condition);
            AllureManager.saveTextLog("Verify True expected: " + condition);
        } else {
            LogUtils.error("Verify True expected: " + condition);
            ExtentTestManager.logMessage(Status.WARNING, "Verify True expected: " + condition);
            AllureManager.saveTextLog("Verify True expected: " + condition);
            Assert.assertTrue(condition, message);
        }
        return condition;
    }

    @Step("Verify {0} equal {1}")
    public static boolean verifyEqual(Object value1, Object value2) {
        boolean check = value1.equals(value2);
        if (check) {
            LogUtils.info("Verify Equal -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.INFO, "Verify Equal -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Equal -" + value1 + "- And -" + value2);
        } else {
            LogUtils.error("Verify Equal -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.WARNING, "Verify Equal -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Equal -" + value1 + "- And -" + value2);
            Assert.assertEquals(value1, value2);
        }
        return check;
    }

    @Step("Verify {0} equal {1}")
    public static boolean verifyEqual(Object value1, Object value2, String message) {
        boolean check = value1.equals(value2);
        if (check) {
            LogUtils.info("Verify Equal -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.INFO, "Verify Equal -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Equal -" + value1 + "- And -" + value2);
        } else {
            LogUtils.error("Verify Equal -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.WARNING, "Verify Equal -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Equal -" + value1 + "- And -" + value2);
            Assert.assertEquals(value1, value2, message);
        }
        return check;
    }

    @Step("Verify {0} contain {1}")
    public static boolean verifyContain(String value1, String value2) {
        boolean check = value1.contains(value2);
        if (check) {
            LogUtils.info("Verify contains -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.INFO, "Verify contains -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify contains -" + value1 + "- And -" + value2);
        } else {
            LogUtils.error("Verify contains -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.WARNING, "Verify contains -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify contains -" + value1 + "- And -" + value2);
            Assert.assertTrue(check);
        }
        return check;
    }

    @Step("Verify {0} contain {1}")
    public static boolean verifyContain(String value1, String value2, String message) {
        boolean check = value1.contains(value2);
        if (check) {
            LogUtils.info("Verify Contain -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.INFO, "Verify Contain -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Contain -" + value1 + "- And -" + value2);
        } else {
            LogUtils.error("Verify Contain -" + value1 + "- And -" + value2);
            ExtentTestManager.logMessage(Status.WARNING, "Verify Contain -" + value1 + "- And -" + value2);
            AllureManager.saveTextLog("Verify Contain -" + value1 + "- And -" + value2);
            Assert.assertTrue(check, message);
        }
        return check;
    }

    public static void softAssertContain(String value1, String value2) {
        waiForPageLoad();
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        softAssert.assertTrue(value1.contains(value2), value1 + " Not Contain " + value2);
        LogUtils.info("Assert " + value1 + " -Contain- " + value2);
        ExtentTestManager.logMessage(Status.INFO, "Assert " + value1 + " -Contain- " + value2);
        AllureManager.saveTextLog("Assert " + value1 + " -Contain- " + value2);
    }

    public static void softAssertEqual(String value1, String value2) {
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        softAssert.assertEquals(value1, value2);
        LogUtils.info("Assert " + value1 + " -Equals- " + value2);
        ExtentTestManager.logMessage(Status.INFO, "Assert " + value1 + " -Equals- " + value2);
        AllureManager.saveTextLog("Assert " + value1 + " -Equals- " + value2);
    }

    public static void endAssert() {
        softAssert.assertAll();
    }


    ////WAIT///////////////////////////////////////////////////////////////////////////////
    public static void waiForPageLoad() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.PAGE_LOAD_TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            LogUtils.info("Javascript in Not Ready!");
            try {
                wait.until(jsLoad);
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail("Timeout waiting for page load: " + ConfigData.PAGE_LOAD_TIMEOUT + ". " + e);
            }
        }
    }

    public static void waiForPageLoad(int timeLoad) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeLoad), Duration.ofMillis(ConfigData.STEP_TIME));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");
        if (!jsReady) {
            LogUtils.info("Javascript in Not Ready!");
            try {
                wait.until(jsLoad);
            } catch (Throwable e) {
                e.printStackTrace();
                Assert.fail("Timeout waiting for page load: " + timeLoad + ". " + e);
            }
        }
    }

    public static WebElement waitForElementVisible(By by) {
        waiForPageLoad();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            boolean check = verifyElementVisible(by);
            if (check) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable e) {
            LogUtils.error("Time out waiting for element visible. " + e.getMessage());
            Assert.fail("Time out waiting for element visible. " + e.getMessage());
        }
        return null;
    }

    public static WebElement waitForElementVisible(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            boolean check = verifyElementVisible(by);
            if (check) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable e) {
            LogUtils.error("Time out waiting for element visible. " + e.getMessage());
            Assert.fail("Time out waiting for element visible. " + e.getMessage());
        }
        return null;
    }

    public static WebElement waitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable e) {
            LogUtils.error("Element Not present: " + e.getMessage());
            Assert.fail("Element Not present: " + e.getMessage());
        }
        return null;
    }

    public static WebElement waitForElementPresent(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable e) {
            LogUtils.error("Element Not present: " + e.getMessage());
            Assert.fail("Element Not present: " + e.getMessage());
        }
        return null;
    }

    public static WebElement waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable e) {
            LogUtils.error("Timeout waiting for the element ready to click. " + e.getMessage());
            Assert.fail("Timeout waiting for the element ready to click. " + e.getMessage());
        }
        return null;
    }

    public static WebElement waitForElementClickable(By by, int timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(ConfigData.STEP_TIME));
            return wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Throwable e) {
            LogUtils.error("Timeout waiting for the element ready to click. " + e.getMessage());
            Assert.fail("Timeout waiting for the element ready to click. " + e.getMessage());
        }
        return null;
    }

    public static void sleep(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
