package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    public LoginTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_LoginAdminSuccess(){
        loginPage.loginAdminHRM()
            .verifyDashboardPage();
    }

    @Test
    public void TC_LoginClientSuccess(){
        loginPage.loginClientHRM(2)
            .verifyDashboardPage();
    }

    @Test
    public void TC_LoginClientWithInvalidUsername(){
        loginPage.loginClientIvlUser(2);
    }

    @Test
    public void TC_LoginClientWithInvalidPassword(){
        loginPage.loginClientIvlPass(2);
    }
}
