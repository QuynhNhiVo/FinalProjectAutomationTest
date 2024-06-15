package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static keywords.WebUI.*;

public class LoginPage extends CommonPage{
    private String textHeadline = "HRM | Anh Tester Demo";
    private String tParagraph = "Welcome back, Please login into an account";
    private String plhUser = "Your Username";
    private String plhPass = "Enter Password";
    private String title = "HRM | Anh Tester Demo | Log in";
    private String messageInvalid = "Invalid Login Credentials.";

    private By ipUsername = By.xpath("//input[@id='iusername']");
    private By ipPassword = By.xpath("//input[@id='ipassword']");
    private By buttonLogin = By.xpath("//button[@type='submit']");
    private By forgotPass = By.xpath("//span[normalize-space()='Forgot password?']");
    private By headline = By.xpath("//div[@class='text-left']");
    private By paragraph = By.xpath("//p[@class='text-muted']");
    private By alertInvaid = By.xpath("//div[@class='toast-message']");


    private final ExcelHelpers excelHelpers;

    public LoginPage(){
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Login");
    }

    private void verifyLoginPage(){
        verifyElementVisible(headline);
        verifyContain(getTextElement(headline), textHeadline);
        verifyEqual(getTextElement(paragraph), tParagraph);
        verifyEqual(getTitlePage(), title, "The title of sign in page not match.");
        verifyEqual(getAttributeElement(ipUsername, "placeholder"), plhUser, "Placeholder of the username field not match");
        verifyEqual(getAttributeElement(ipPassword, "placeholder"), plhPass, "Placeholder of the password field not match");
    }

    public DashboardPage loginAdminHRM(){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USERNAME", 1));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", 1));
        clickElement(buttonLogin);
        sleep(4);
        return new DashboardPage();
    }

    @Parameters({"row"})
    public DashboardPage loginClientHRM(@Optional("2") int row){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USERNAME", row));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", row));
        clickElement(buttonLogin);
        waiForPageLoad(10);
        return new DashboardPage();
    }

    public DashboardPage loginClientIvlUser(@Optional("2") int row){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USWRONG", row));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", row));
        clickElement(buttonLogin);
        verifyEqual(getTextElement(alertInvaid), messageInvalid, "Alert not display correct message!");
        waiForPageLoad(10);
        return new DashboardPage();
    }

    public DashboardPage loginClientIvlPass(@Optional("2") int row){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USERNAME", row));
        setText(ipPassword, excelHelpers.getCellData("PWWRONG", row));
        clickElement(buttonLogin);
        verifyEqual(getTextElement(alertInvaid), messageInvalid, "Alert not display correct message!");
        waiForPageLoad(10);
        return new DashboardPage();
    }

    public DashboardPage loginWithPassLessThan6(){
        return new DashboardPage();
    }

}
