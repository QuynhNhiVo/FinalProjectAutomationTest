package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import static keywords.WebUI.*;

public class LoginPage extends CommonPage{
    private String textHeadline = "HRM | Anh Tester Demo";
    private String tParagraph = "Welcome back, Please login into an account";
    private String plhUser = "Your Username";
    private String plhPass = "Enter Password";
    private String title = "HRM | Anh Tester Demo | Log in";

    private By ipUsername = By.xpath("//input[@id='iusername']");
    private By ipPassword = By.xpath("//input[@id='ipassword']");
    private By buttonLogin = By.xpath("//button[@type='submit']");
    private By forgotPass = By.xpath("//span[normalize-space()='Forgot password?']");
    private By headline = By.xpath("//div[@class='text-left']");
    private By paragraph = By.xpath("//p[@class='text-muted']");


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
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Login");
        setText(ipUsername, excelHelpers.getCellData("USERNAME", 1));
        setText(ipPassword, excelHelpers.getCellData("PASSWORD", 1));
        verifyLoginPage();
        clickElement(buttonLogin);
        waiForPageLoad(10);
        return new DashboardPage();
    }
}
