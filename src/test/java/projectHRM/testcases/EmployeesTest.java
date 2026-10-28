package projectHRM.testcases;

import common.BaseTest;
import constants.Groups;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projectHRM.pages.EmployeesPage;
import projectHRM.pages.LoginPage;

public class EmployeesTest extends BaseTest {
    LoginPage loginPage;
    EmployeesPage employeesPage;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
    }

    @Test(groups = { Groups.REGRESSION, Groups.EMPLOYEE })
    public void TC_AddNewEmployees() {
        loginPage.loginAdminHRM()
                .goEmployees()
                .addNewEmployees(1);
    }
}
