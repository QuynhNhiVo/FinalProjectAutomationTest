package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderProjects;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.LoginPage;

import java.util.Hashtable;

public class TestProjectFunctionFlow extends BaseTest {
    LoginPage loginPage;

    public TestProjectFunctionFlow(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_Project_TaskFunctionFlow(@Optional("1") int row){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(row)
            .verifyResults(row)
            .addTask(row)
            .addAttachFiles(row)
            .updateStatus(row)
            .logOut();
    }

    @Test(dataProvider = ("data_add_projects"), dataProviderClass = DataProviderProjects.class)
    public void TC_ProjectsFunctionFlow(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .addNewProject(data)
            .verifyResults(data);
    }

    @Test(dataProvider = ("data_add_tasks"), dataProviderClass = DataProviderProjects.class)
    public void TC_FlowAddTask_Attach_UpdateStatus(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .search(data)
            .addTask(data)
            .addAttachFiles(data)
            .updateStatus(data)
            .logOut();
    }

}
