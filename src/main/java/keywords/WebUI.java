package keywords;

import com.aventstack.extentreports.Status;
import constants.ConfigData;
import drivers.DriverManager;
import helpers.SystemHelpers;
import io.qameta.allure.Step;
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
        try {
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

            //Check Error Navigate
            for (int i = 1; i <= 3; i++) {
                try {
                    String text = getWebElement(By.xpath("//h1[1]")).getText().toLowerCase().trim();
                    if (text.contains("whoops!")) {
                        LogUtils.warn("⚠️ " + getWebElement(By.xpath("//h1[1]")).getText());
                        ExtentTestManager.logMessage(Status.WARNING, "⚠️ " + getWebElement(By.xpath("//h1[1]")).getText());
                        AllureManager.saveTextLog("⚠️ " + getWebElement(By.xpath("//h1[1]")).getText());
                        getDriver().navigate().refresh();
                        LogUtils.warn(i + " 🔃 Reload Page");
                        ExtentTestManager.logMessage(Status.WARNING, i + " 🔃 Reload Page");
                        AllureManager.saveTextLog(i + " 🔃 Reload Page");
                        sleep(2);
                    } else {
                        js.executeScript("location.reload()");
                        LogUtils.warn("🔃 Reload Page with Javascript");
                        ExtentTestManager.logMessage(Status.WARNING, "🔃 Reload Page with Javascript");
                        AllureManager.saveTextLog("🔃 Reload Page with Javascript");
                        sleep(1000);
                    }
                } catch (Exception ignored) {
                    break;
                }
            }

        } catch (Exception e) {
            LogUtils.error(e.getMessage());
            ExtentTestManager.logMessage(Status.WARNING, e.getMessage());
            AllureManager.saveTextLog(e.getMessage());
        }
    }

    @Step("Enter Element: {0}")
    public static void clickTextBox(By by) {
        waiForPageLoad();
        clickElement(by);
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ENTER).perform();
        LogUtils.info("Enter Element: " + by);
        ExtentTestManager.logMessage(Status.INFO, "Enter Element: " + by);
        AllureManager.saveTextLog("Enter Element: " + by);
    }

    @Step("Verify {0} display and click {1}")
    public static void clickHamburgerButton(By by, By button){
        while (!getDriver().findElement(by).isDisplayed()) {
            waitForElementClickable(button);
            LogUtils.info(getDriver().findElement(by).getAttribute("class")+ " - NOT DISPLAY - Click element: " + getDriver().findElement(button).getAttribute("class"));
            ExtentTestManager.logMessage(Status.INFO, getDriver().findElement(by).getAttribute("class")+ " - NOT DISPLAY - Click element: " + getDriver().findElement(button).getAttribute("class"));
            AllureManager.saveTextLog(getDriver().findElement(by).getAttribute("class")+ " - NOT DISPLAY - Click element: " + getDriver().findElement(button).getAttribute("class"));
            clickElement(button);
        }
    }

    @Step("Click button Detail {0}")
    public static void clickDetailButton(By by){
        if (!getDriver().getTitle().trim().toLowerCase().contains("detail")) {
            clickElement(by);
            LogUtils.info("Navigate to Detail page");
        }
    }

    @Step("Set text : {1} For Element: {0} ")
    public static void setText(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(Keys.chord(Keys.CONTROL, "a"), text);
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
        LogUtils.info("Get attribute " + atb + ":  " + getDriver().findElement(by).getAttribute(atb) + " - Of element" + by);
        ExtentTestManager.logMessage(Status.INFO, "Get attribute " + atb + ":  " + getDriver().findElement(by).getAttribute(atb) + " - Of element" + by);
        AllureManager.saveTextLog("Get attribute " + atb + ":  " + getDriver().findElement(by).getAttribute(atb) + " - Of element" + by);
        return getDriver().findElement(by).getAttribute(atb);
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
        sleep(1);
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

    @Step("Exit iframe")
    public static void exitIframe() {
        getDriver().switchTo().parentFrame();
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


    /// /HANDLE PAGE/////////////////////////////////////////////////////////////////////////
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
                    String id = getDriver().findElement(By.xpath("(//label[@for='client_id']/following-sibling::select)//option[normalize-space()='" + option[i] + "']")).getAttribute("value");
                    select.selectByValue(id);
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

    public static void sendKeysDropdown(By button, By by) {
        clickElement(button);
        Select select = new Select(getWebElement(by));
        waiForPageLoad();
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

    public static void verifyNumberOfResults(int col, String value) {
        // Retrieve the list of elements represending search results
        List<WebElement> list = getWebElements(By.xpath("//table[@id='xin_table']//tbody/tr"));
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
                String rs = getTextElement(By.xpath("//table[@id='xin_table']//tbody/tr[" + i + "]/td[" + col + "]"));
//                scrollToElement(By.xpath("//table[@id='xin_table']//tbody/tr[" + i + "]/td[" + col + "]"));
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

    @Step("Verify data and pagination after search keyword {2} with column {1} and page have {0} results")
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

    @Step("Verify data and pagination after search keyword {1} with column {0}")
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

    public static void verifyTeam(By result, By firstname, By lastname, int col, String value){
        getWebElement(result).getText();
        LogUtils.info("Name Team: " + getWebElement(result).getText());
        String nameTeam = (getWebElement(firstname).getText() + " " + getWebElement(lastname).getText()).toLowerCase().trim();
        boolean check = getWebElement(result).getText().toLowerCase().trim().contains(nameTeam);
        if (check) {
            LogUtils.info("Team has not been created !!");
            return;
        }
        if (!check) {

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
        waiForPageLoad();
        By path = By.xpath("//a[contains(@data-rating-text,'" + status + "')]");
        getWebElement(path).click();
        sleep(2);
        verifyContain(getAttributeElement(path, "data-rating-text"), status, "Choose the wrong status :" + status);
        LogUtils.info("Choose status: " + status);
        ExtentTestManager.logMessage(Status.INFO, "Choose status: " + status);
        AllureManager.saveTextLog("Choose status: " + status);
    }

    @Step("Set the element {0} start date {1}")
    public static void getStartDate(By by, String[] date, By save) {
        clickElement(by);
        //Choose Month/////////////////////////////////////////////////
        String start = "(//div[@class='dtp-date'])[1]";
        String presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
        //Get default month
        LogUtils.info("Present Month: " + presentMonth);
        while (!presentMonth.trim().toLowerCase().equals(date[0].trim().toLowerCase())) {
            getDriver().findElement(By.xpath(start + "/div[1]/div[@class='left center p10']")).click();
            presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
        }
        LogUtils.info("Choose month start: " + presentMonth);
        ExtentTestManager.logMessage(Status.INFO, "Choose month start: " + presentMonth);
        AllureManager.saveTextLog("Choose month start: " + presentMonth);
        sleep(2);

        //Choose Year/////////////////////////////////////////////////
        String presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        //Get default year
        LogUtils.info("Present year: " + presentYear);
        while (!date[1].equals(presentYear.trim())) {
            if (Integer.parseInt(date[1]) < Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='left center p10']")).click();
            } else if (Integer.parseInt(date[1]) > Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='right center p10']")).click();
            }
            presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        }
        LogUtils.info("Choose year start: " + presentYear);
        ExtentTestManager.logMessage(Status.INFO, "Choose year start: " + presentYear);
        AllureManager.saveTextLog("Choose year start: " + presentYear);
        sleep(2);

        //Choose Day/////////////////////////////////////////////////
        String picker = "";
        //Check the order of element picker in the calendar of tabs
//        for(int i = 1; i<=8;){
        for (int i : new int[]{1, 5}) {
            if (verifyVisible(By.xpath("(//div[@class='dtp-picker'])[" + i + "]"))) {
                picker = "(//div[@class='dtp-picker'])[" + i + "]";
                break;
            }
//            i++;
        }
        LogUtils.info("picker = " + picker);
        List<WebElement> listDate = getDriver().findElements(By.xpath(picker + "//tbody/tr/td/a"));
        int totalDate = listDate.size();
        int row = totalDate / 7;
        int add = totalDate % 7;
        if (add > 0) {
            row = row + 1;
        }
//        LogUtils.info("Have: " + totalDate + " date and: " + row + " week.");

        for (int i = 2; i <= row + 1; i++) {
            List<WebElement> dateInRow = getDriver().findElements(By.xpath(picker + "//tbody/tr[" + i + "]/td"));
            int dateRow = dateInRow.size();
            LogUtils.info("Week " + (i - 1) + " have " + dateRow + " date.");
            for (int j = (7 - dateRow + 1); j <= dateRow; j++) {
                WebElement getDate = getDriver().findElement(By.xpath(picker + "//tbody/tr[" + i + "]/td[" + j + "]"));
                if (getDate.getText().trim().equals(date[2])) {
                    getDate.click();
                    LogUtils.info("Choose day start: " + getDate.getText());
                    ExtentTestManager.logMessage(Status.INFO, "Choose day start: " + getDate.getText());
                    AllureManager.saveTextLog("Choose day start: " + getDate.getText());
                    break;
                }
            }
        }

        //Check Final date in the calendar
        WebElement dayNum = getDriver().findElement(By.xpath(start + "/div[2]"));
        WebElement monthYear = getDriver().findElement(By.xpath(picker + "/div[1]/div"));
        WebElement day = null;
        //Check the order of headers in the calendar of tabs
//        for(int i=1; i<= 8;){
        for (int i : new int[]{1, 5, 6}) {
            if (verifyVisible(By.xpath("(//header[@class='dtp-header'])[" + i + "]"))) {
                day = getDriver().findElement(By.xpath("(//header[@class='dtp-header'])[" + i + "]/div[1]"));
                break;
            }
//            i++;
        }
        verifyEqual(dayNum.getText().trim(), date[2], "Date choose is incorrect!");
        LogUtils.info("Start Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
        ExtentTestManager.logMessage(Status.INFO, "Start Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
        AllureManager.saveTextLog("Start Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
        sleep(2);
        clickElement(save);
    }

    @Step("Set the element {0} end date {1}")
    public static void getEndDate(By by, String[] date, By save) {
        clickElement(by);
        //Choose Month/////////////////////////////////////////////////
        String start = "(//div[@class='dtp-date'])[2]";
        String presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
        //Get default month
        LogUtils.info("Present Month: " + presentMonth);
        while (!presentMonth.trim().toLowerCase().equals(date[0].trim().toLowerCase())) {
            getDriver().findElement(By.xpath(start + "/div[1]/div[@class='left center p10']")).click();
            presentMonth = getDriver().findElement(By.xpath(start + "/div[1]/div[@class='dtp-actual-month p80']")).getText();
        }
        LogUtils.info("Choose month end: " + presentMonth);
        ExtentTestManager.logMessage(Status.INFO, "Choose month end: " + presentMonth);
        AllureManager.saveTextLog("Choose month end: " + presentMonth);
        sleep(2);

        //Choose Year/////////////////////////////////////////////////
        String presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        //Get default year
        LogUtils.info("Present year: " + presentYear);
        while (!date[1].equals(presentYear.trim())) {
            if (Integer.parseInt(date[1]) < Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='left center p10']")).click();
            } else if (Integer.parseInt(date[1]) > Integer.parseInt(presentYear.trim())) {
                getDriver().findElement(By.xpath(start + "/div[3]/div[@class='right center p10']")).click();
            }
            presentYear = getDriver().findElement(By.xpath(start + "/div[3]/div[@class='dtp-actual-year p80']")).getText();
        }
        LogUtils.info("Choose year end: " + presentYear);
        ExtentTestManager.logMessage(Status.INFO, "Choose year end: " + presentYear);
        AllureManager.saveTextLog("Choose year end: " + presentYear);
        sleep(2);

        //Choose Day/////////////////////////////////////////////////
        String picker = "";
        //Check the order of element picker in the calendar of tabs
//        for(int i = 1; i<=8;){
        for (int i : new int[]{2, 6}) {
            if (verifyVisible(By.xpath("(//div[@class='dtp-picker'])[" + i + "]"))) {
                picker = "(//div[@class='dtp-picker'])[" + i + "]";
                break;
            }
        }
        List<WebElement> listDate = getDriver().findElements(By.xpath(picker + "//tbody/tr/td/a"));
        int totalDate = listDate.size();
        int row = totalDate / 7;
        int add = totalDate % 7;
        if (add > 0) {
            row = row + 1;
        }
        LogUtils.info("Have: " + totalDate + " date and: " + row + " week.");

        for (int i = 2; i <= row + 1; i++) {
            List<WebElement> dateInRow = getDriver().findElements(By.xpath(picker + "//tbody/tr[" + i + "]/td"));
            int dateRow = dateInRow.size();
            LogUtils.info("Week " + (i - 1) + " have " + dateRow + " date.");
            for (int j = (7 - dateRow + 1); j <= dateRow; j++) {
                WebElement getDate = getDriver().findElement(By.xpath(picker + "//tbody/tr[" + i + "]/td[" + j + "]"));
                if (getDate.getText().trim().equals(date[2])) {
                    getDate.click();
                    LogUtils.info("Choose day start: " + getDate.getText());
                    ExtentTestManager.logMessage(Status.INFO, "Choose day start: " + getDate.getText());
                    AllureManager.saveTextLog("Choose day start: " + getDate.getText());
                    break;
                }
            }
        }

//        WebElement dayNum = getDriver().findElement(By.xpath(start + "/div[2]"));
//        WebElement monthYear = getDriver().findElement(By.xpath(picker+"/div[1]/div"));
//        WebElement day = null;
//        //Check the order of headers in the calendar of tabs
////        for(int i=1; i<= 8;){
//        for(int i : new int[] {1, 5, 6}){
//            if (verifyVisible(By.xpath("(//header[@class='dtp-header'])["+i+"]"))){
//                day = getDriver().findElement(By.xpath("(//header[@class='dtp-header'])["+i+"]/div[1]"));
//                break;
//            }
////            i++;
//        }
//        verifyEqual(dayNum.getText().trim(), date[2], "Date choose is incorrect!");
//        LogUtils.info("End Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
//        ExtentTestManager.logMessage(Status.INFO, "End Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
//        AllureManager.saveTextLog("End Day: " + day.getText() + " " + dayNum.getText() + " " + monthYear.getText());
        sleep(2);
        clickElement(save);
    }

    /// /VERIFY///////////////////////////////////////////////////////////////////////////////
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

    public static boolean verifyVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
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

    public static boolean verifyExist(By by) {
        List<WebElement> list = getWebElements(by);
        if (list.size() > 0) {
            return true;
        } else {
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

    public static void softAssertUrl(String value1, String value2){
        waitForContain(value1);
        if (softAssert == null) {
            softAssert = new SoftAssert();
        }
        softAssert.assertTrue(value1.contains(value2), value1 + " Not Contain " + value2);
        LogUtils.info("Assert " + value1 + " -Contain- " + value2);
        ExtentTestManager.logMessage(Status.INFO, "Assert " + value1 + " -Contain- " + value2);
        AllureManager.saveTextLog("Assert " + value1 + " -Contain- " + value2);

    }

    public static void endAssert() {
        softAssert.assertAll();
    }

    /// /WAIT///////////////////////////////////////////////////////////////////////////////
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

    public static Boolean waitForContain(String text) {
        waiForPageLoad();
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(ConfigData.TIMEOUT), Duration.ofMillis(ConfigData.STEP_TIME));
            return wait.until(ExpectedConditions.urlContains(text));
        } catch (Throwable e) {
            LogUtils.error("Timeout waiting for subdir of URL. " + e.getMessage());
            Assert.fail("Timeout waiting for the element ready to click. " + e.getMessage());
            return false;
        }
    }

    public static void sleep(double time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
