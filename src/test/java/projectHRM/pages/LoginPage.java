package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Hashtable;

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
    private final ExcelHelpers excelHelpersCLient;

    public LoginPage(){
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Login");
        this.excelHelpersCLient = new ExcelHelpers();
        this.excelHelpersCLient.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Clients");
    }

    private void verifyLoginPage(){
        verifyContain(getTextElement(headline), textHeadline);
        verifyEqual(getTextElement(paragraph), tParagraph);
        verifyEqual(getTitlePage(), title, "The title of sign in page not match.");
        verifyEqual(getAttributeElement(ipUsername, "placeholder"), plhUser, "Placeholder of the username field not match");
        verifyEqual(getAttributeElement(ipPassword, "placeholder"), plhPass, "Placeholder of the password field not match");
    }

    //Sheet Login
    public DashboardPage loginAdminHRM(){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USERNAME", 1));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", 1));
        clickElement(buttonLogin);
        sleep(4);
        return new DashboardPage();
    }

    //Sheet Login

    public DashboardPage loginClientHRM( int row){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpersCLient.getCellData("USERNAME", row));
        setText(ipPassword, excelHelpersCLient.getCellData("PASSWORD", row));
        clickElement(buttonLogin);
        sleep(4);
        return new DashboardPage();
    }

    //Sheet Client dataProvider
    public DashboardPage loginClientHRM(Hashtable<String, String> data){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, data.get("USERNAME"));
        setText(ipPassword, data.get("PASSWORD"));
        clickElement(buttonLogin);
        sleep(4);
        return new DashboardPage();
    }

    public DashboardPage loginClientInvlUser(int row){
        openURL(ConfigData.URL);
        verifyLoginPage();
        setText(ipUsername, excelHelpers.getCellData("USWRONG", row));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", row));
        clickElement(buttonLogin);
        verifyEqual(getTextElement(alertInvaid), messageInvalid, "Alert not display correct message!");
        waiForPageLoad(10);
        return new DashboardPage();
    }

    public DashboardPage loginClientInvlPass(@Optional("2") int row){
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
