package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projectHRM.pages.*;

public class E2EFLowTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;
    EmployeesPage employeesPage;
    ProjectsPage projectsPage;

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_E2E(int row){

    }
}
