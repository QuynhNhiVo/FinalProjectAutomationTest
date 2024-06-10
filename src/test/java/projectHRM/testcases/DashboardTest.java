package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    public DashboardTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_Dashboarrd(){
        loginPage.loginAdminHRM()
            .verifyDashboardPage();
    }
}
