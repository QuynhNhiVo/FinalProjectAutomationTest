package projectHRM.testcases;

import common.BaseTest;
import constants.Groups;
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

    @Test(groups = { Groups.SMOKE, Groups.REGRESSION, Groups.AUTH })
    public void TC_LoginAdminSuccess() {
        loginPage.loginAdminHRM()
                .verifyDashboardPage();
    }

    @Test(groups = { Groups.REGRESSION, Groups.AUTH, Groups.CLIENT })
    @Parameters({ "row" })
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

    @Test(groups = { Groups.REGRESSION, Groups.AUTH,
            Groups.CLIENT }, dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_LoginClientsSuccess(Hashtable<String, String> data) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .verifyClientsPage()
                .addClient(data)
                .logOut().loginClientHRM(data)
                .verifyDashboardPage()
                .logOut();
    }

    @Test(groups = { Groups.SMOKE, Groups.REGRESSION, Groups.AUTH, Groups.NEGATIVE })
    @Parameters({ "row" })
    public void TC_LoginClientWithInvalidUsername(@Optional("2") int row) {
        loginPage.loginClientInvlUser(row);
    }

    @Test(groups = { Groups.SMOKE, Groups.REGRESSION, Groups.AUTH, Groups.NEGATIVE })
    @Parameters({ "row" })
    public void TC_LoginClientWithInvalidPassword(@Optional("2") int row) {
        loginPage.loginClientInvlPass(row);
    }
}
