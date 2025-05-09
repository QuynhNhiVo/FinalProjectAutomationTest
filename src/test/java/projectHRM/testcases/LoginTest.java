package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderClients;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

import java.util.Hashtable;

public class LoginTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
    }

    @Test
    public void TC_LoginAdminSuccess() {
        loginPage.loginAdminHRM()
                .verifyDashboardPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_LoginClientSuccess(@Optional("8") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .verifyClientsPage()
                .addClient(row)
                .logOut()
                .loginClientHRM(row)
                .verifyDashboardPage()
                .logoutClient();
    }

    @Test(dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_LoginClientsSuccess(Hashtable<String, String> data) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .verifyClientsPage()
                .addClient(data)
                .logOut().loginClientHRM(data)
                .verifyDashboardPage()
                .logOut();
    }

    @Test
    @Parameters({"row"})
    public void TC_LoginClientWithInvalidUsername(@Optional("2") int row) {
        loginPage.loginClientInvlUser(row);
    }

    @Test
    @Parameters({"row"})
    public void TC_LoginClientWithInvalidPassword(@Optional("2") int row) {
        loginPage.loginClientInvlPass(row);
    }
}
