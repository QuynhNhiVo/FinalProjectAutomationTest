package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderProjects;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ProjectsPage;

import java.util.Hashtable;

public class TestProjectFunctionFlow extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectsPage projectsPage;

    public TestProjectFunctionFlow(){
        loginPage = new LoginPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_Project_TaskFunctionFlow(@Optional("9") int row){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(row)//Add Project
            .verifyResults(row)
            .addTask(row)//Add Task
            .getTabEdit()//Tab Edit
            .addAttachFiles(row)//Add Attach
            .updateStatus(row)//Update status
            .goTasks()
            .deleteTask(row)//Delete Task
            .goProjects()
            .deleteProject(row)//Delete Project
            .logOut();
    }

    @Test(dataProvider = ("data_add_projects"), dataProviderClass = DataProviderProjects.class)
    public void TC_ProjectsFunctionFlow(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .addNewProject(data)
            .verifyResults(data)
            .goProjects()
            .deleteProject(data)
            .logOut();
    }

    @Test(dataProvider = ("data_add_tasks"), dataProviderClass = DataProviderProjects.class)
    public void TC_FlowAddTask_Attach_UpdateStatus(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .search(data)
            .getTabEdit()
            .addTask(data)
            .addAttachFiles(data)
            .updateStatus(data)
            .goTasks()
            .deleteTask(data)
            .logOut();
    }

}
