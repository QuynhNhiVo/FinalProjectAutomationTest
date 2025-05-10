package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderProjects;
import org.testng.annotations.*;
import projectHRM.pages.LoginPage;

import java.util.Hashtable;

public class TaskPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void initData(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_DeleteTaskSpecified(@Optional("2") int row){
        loginPage.loginAdminHRM()
            .goTasks()
            .verifyTasksPage()
            .deleteTask(row);
    }

    @Test(dataProvider = "data_add_tasks", dataProviderClass = DataProviderProjects.class)
    public void TC_DeleteTasks(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
                .goTasks()
                .verifyTasksPage()
                .deleteTask(data);
    }
}
