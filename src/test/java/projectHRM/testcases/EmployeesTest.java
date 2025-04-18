package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projectHRM.pages.EmployeesPage;
import projectHRM.pages.LoginPage;

public class EmployeesTest extends BaseTest {
    LoginPage loginPage;
    EmployeesPage employeesPage;

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_AddNewEmployees(){
        loginPage.loginAdminHRM()
                .getEmployees()
                .addNewEmployees(1);
    }
}
