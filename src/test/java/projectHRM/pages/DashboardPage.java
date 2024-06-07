package projectHRM.pages;

import org.openqa.selenium.By;
import static keywords.WebUI.*;

public class DashboardPage extends CommonPage{
    private String subdir = "/erp/desk";
    private String title = "Home | HRM | Anh Tester Demo";

    private By menuClients = By.xpath("//span[normalize-space()='Manage Clients']");

    public ManageClientsPage goManageClients(){
        clickElement(menuClients);
        return new ManageClientsPage();
    }

}
