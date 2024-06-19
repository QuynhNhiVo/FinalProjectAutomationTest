package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

public class TaskPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_DeleteTask(@Optional("1") int row){
        loginPage.loginAdminHRM()
            .goTasks()
            .verifyTasksPage()
            .deleteTask(row);
    }
}
