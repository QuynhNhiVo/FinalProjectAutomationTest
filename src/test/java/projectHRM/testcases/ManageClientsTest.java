package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ManageClientsPage;

public class ManageClientsTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;
    public ManageClientsTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_GetClientsPage(){
        loginPage.loginAdminHRM()
            .goManageClients()
            .verifyClientsPage();
    }

    @Test
    public void TC_AddNewClient(){
        loginPage.loginAdminHRM()
            .goManageClients()
            .verifyClientsPage()
            .addClient(2);
    }

    @Test
    public void TC_Edit(){
        loginPage.loginAdminHRM()
            .goManageClients()
            .editClient(2);
    }

    @Test
    public void TC_AddEditSearch(){
        loginPage.loginAdminHRM()
            .goManageClients()
            .verifySearchClient(2);
    }

}
