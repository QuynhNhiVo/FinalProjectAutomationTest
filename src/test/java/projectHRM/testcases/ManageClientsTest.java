package projectHRM.testcases;

import common.BaseTest;
import constants.Groups;
import dataprovider.DataProviderClients;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ManageClientsPage;

import java.util.Hashtable;

public class ManageClientsTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
    }

    @Test(groups = { Groups.SMOKE, Groups.REGRESSION, Groups.CLIENT })
    public void TC_GetClientsPage() {
        loginPage.loginAdminHRM()
                .goManageClients()
                .verifyClientsPage();
    }

    @Test(groups = { Groups.REGRESSION, Groups.CLIENT })
    public void TC_AddNewClient() {
        loginPage.loginAdminHRM()
                .goManageClients()
                .verifyClientsPage()
                .addClient(2);
    }

    @Test(groups = { Groups.REGRESSION, Groups.CLIENT, Groups.DEPENDENT })
    public void TC_EditClient() {
        loginPage.loginAdminHRM()
                .goManageClients()
                .editClient(2);
    }

    @Test(groups = { Groups.REGRESSION, Groups.CLIENT, Groups.DEPENDENT })
    public void TC_VerifyClient() {
        loginPage.loginAdminHRM()
                .goManageClients()
                .searchClient(2)
                .verifyDataClient(2);
    }

    @Test(groups = { Groups.REGRESSION, Groups.CLIENT, Groups.DEPENDENT })
    @Parameters({ "row" })
    public void TC_DeleteClient(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .deleteClient(row);
    }

    @Test(groups = { Groups.REGRESSION, Groups.CLIENT })
    @Parameters({ "row" })
    public void TC_FlowVerifyFunctionClient(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .editClient(row)
                .searchClient(row)
                .verifyDataClient(row);
    }

    @Test(groups = { Groups.REGRESSION,
            Groups.CLIENT }, dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_FlowVerifyFunctionClients(Hashtable<String, String> data) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(data)
                .editClient(data)
                .searchClient(data)
                .verifyDataClient(data);
    }

    @Test(groups = { Groups.SMOKE, Groups.CLIENT,
            Groups.DEPENDENT }, dataProvider = "data_delete_clients", dataProviderClass = DataProviderClients.class)
    public void TC_FlowDeleteClients(Hashtable<String, String> data) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .deleteClient(data);
    }

}
