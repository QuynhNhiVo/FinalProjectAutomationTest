package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderClients;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

import java.util.Hashtable;

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
        loginPage.loginClientHRM(3)
            .verifyDashboardPage()
            .logOut();
    }

    @Test(dataProvider = "data_add_clients", dataProviderClass = DataProviderClients.class)
    public void TC_LoginClientsSuccess(Hashtable<String, String> data){
        loginPage.loginClientHRM(data)
            .verifyDashboardPage()
            .logOut();
    }

    @Test
    public void TC_LoginClientWithInvalidUsername(){
        loginPage.loginClientInvlUser(2);
    }

    @Test
    public void TC_LoginClientWithInvalidPassword(){
        loginPage.loginClientInvlPass(2);
    }
}
