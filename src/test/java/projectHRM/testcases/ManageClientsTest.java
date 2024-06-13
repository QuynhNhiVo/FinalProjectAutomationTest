package projectHRM.testcases;

import common.BaseTest;
import constants.ConfigData;
import dataprovider.DataProviderClients;
import helpers.ExcelHelpers;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ManageClientsPage;

import java.util.Hashtable;

public class ManageClientsTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;

    public ManageClientsTest() {
        loginPage = new LoginPage();
    }

    @Test
    public void TC_GetClientsPage() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .verifyClientsPage();
    }

    @Test
    public void TC_AddNewClient() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .verifyClientsPage()
            .addClient(2);
    }

    @Test
    public void TC_EditClient() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .editClient(2);
    }

    @Test
    public void TC_VerifyClient() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .searchClient(2)
            .verifyDataClient(2);
    }

    @Test
    public void TC_DeleteClient() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .deleteClient(5);
    }

    @Test
    public void TC_FlowVerifyFunctionClient() {
        loginPage.loginAdminHRM()
            .goManageClients()
            .addClient(1)
            .editClient(1)
            .searchClient(1)
            .verifyDataClient(1);
    }

    @Test(dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_FlowVerifyFunctionClients(Hashtable<String, String> data) {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        loginPage.loginAdminHRM()
            .goManageClients()
            .addClients(data)
            .searchClients(data)
            .editClients(data);
    }

    @Test(dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_FlowDeleteClients(Hashtable<String, String> data) {
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        loginPage.loginAdminHRM()
            .goManageClients()
            .deleteClients(data);
    }

}
