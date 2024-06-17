package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderClients;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ManageClientsPage;

import java.util.Hashtable;

public class TestClientFunctionFlow extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;

    public TestClientFunctionFlow(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"login", "client"})
    public void TC_ClientFunctionFlow(@Optional("8") int lg, @Optional("7") int cli){
        loginPage.loginAdminHRM()
            .verifyDashboardPage()
            .goManageClients()
            .addClient(7)
            .searchClient(7)
            .editClient(7)
            .searchClient(7)
            .verifyDataClient(7)
            .logOut()
            .loginClientHRM( 8)
            .verifyDashboardPage()
            .logOut()
            .loginAdminHRM()
            .goManageClients()
            .deleteClient(7)
            .logOut();
    }

    @Test(dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_ClientsFunctionFlow(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .verifyDashboardPage()
            .goManageClients()
            .addClient(data)
            .searchClient(data)
            .editClient(data)
            .searchClient(data)
            .verifyDataClient(data)
            .logOut()
            .loginClientHRM(data)
            .verifyDashboardPage()
            .logOut()
            .loginAdminHRM()
            .goManageClients()
            .deleteClient(data)
            .logOut();
    }
}
