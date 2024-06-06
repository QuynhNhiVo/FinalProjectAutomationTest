package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    public LoginTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_LoginAdmin(){
        loginPage.loginAdminHRM();
    }
}
