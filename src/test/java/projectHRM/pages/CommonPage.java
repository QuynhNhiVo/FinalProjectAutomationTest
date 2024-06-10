package projectHRM.pages;

import org.openqa.selenium.By;
import static keywords.WebUI.*;

public class CommonPage {

    private By buttonLogout = By.xpath("//i[@class='feather icon-power']");
    private By menuHome = By.xpath("//span[normalize-space()='Home']");
    private By menuClients = By.xpath("//span[normalize-space()='Manage Clients']");

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;

    public LoginPage logOut(){
        clickElement(buttonLogout);
        return new LoginPage();
    }

    public ManageClientsPage goManageClients(){
        scrollDownMenuBar(menuHome);
        clickElement(menuClients);
        return new ManageClientsPage();
    }

    public LoginPage getLoginPage(){
        if(loginPage ==null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage(){
        if (dashboardPage==null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public ManageClientsPage getManagerClientsPage(){
        if(manageClientsPage==null){
            manageClientsPage = new ManageClientsPage();
        }
        return manageClientsPage;
    }
}
