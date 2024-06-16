package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderProjects;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ProjectsPage;

import java.util.Hashtable;

public class ProjectsPageTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectsPage projectsPage;

    public ProjectsPageTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_ProjectsPage(){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage();
    }

    @Test
    public void TC_AddTask(){
        loginPage.loginAdminHRM()
            .goProjects()
            .addTask(3);
    }

    @Test(dataProvider = "data_add_tasks", dataProviderClass = DataProviderProjects.class)
    public void TC_AddTasks(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .addTask(data);
    }

    @Test
    public void TC_AddAttachUpdateStatus(){
        loginPage.loginAdminHRM()
            .goProjects()
            .search(1)
            .addAttachFiles(1)
            .updateStatus(1);
    }

    @Test
    public void TC_AddProject(){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(3);
    }

    @Test(dataProvider = "data_add_projects", dataProviderClass = DataProviderProjects.class)
    public void TC_AddProjects(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(data);
    }

    @Test
    public void TC_FlowAddTaskAddAttachUpdateStatus(){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(3)
            .verifyResults(3)
            .addTask(3)
            .addAttachFiles(3)
            .updateStatus(3);
    }

    @Test(dataProvider = "data_add_tasks", dataProviderClass = DataProviderProjects.class)
    public void TC_FlowAddTaskAddAttachUpdateStatus(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .addTask(data)
            .addAttachFiles(3)
            .updateStatus(3);
    }
}
