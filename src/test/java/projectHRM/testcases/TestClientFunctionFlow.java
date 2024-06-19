package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderClients;
import org.testng.annotations.BeforeMethod;
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

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_ClientFunctionFlowSpecified(@Optional("8") int row){
        loginPage.loginAdminHRM()
            .verifyDashboardPage()
            .goManageClients()
            .addClient(row)//Add Client
            .searchClient(row)
            .editClient(row)//Edit Client
            .searchClient(row)
            .verifyDataClient(row)
            .logOut()
            .loginClientHRM( row)//Login With Client
            .verifyDashboardPage()
            .logOut()
            .loginAdminHRM()
            .goManageClients()
            .deleteClient(row)//Delete Client
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
