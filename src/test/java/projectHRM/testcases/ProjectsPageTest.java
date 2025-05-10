package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderCombine;
import dataprovider.DataProviderProjects;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ProjectsPage;

import java.util.Hashtable;

public class ProjectsPageTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectsPage projectsPage;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
    }

    @Test
    public void TC_ProjectsPage() {
        loginPage.loginAdminHRM()
                .goProjects()
                .verifyProjectsPage();
    }

    @Test
    @Parameters({"row"})
    public void TC_AddNewProjectSpecified(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .goEmployees()
                .verifyTeam(row)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(row)
                .verifyResults(row);
    }

    @Test
    @Parameters({"row"})
    public void TC_DeleteProjectSpecified(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goProjects()
                .deleteProject(row);
    }

    @Test
    @Parameters({"row"})
    public void TC_AddAttachUpdateStatus(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .goEmployees()
                .verifyTeam(row)
                .goProjects()
                .search(row)
                .addAttachFiles(row)
                .updateStatus(row);
    }

    @Test
    @Parameters({"row"})
    public void TC_AddTask(@Optional("2") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .goEmployees()
                .verifyTeam(row)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(row)
                .search(row)
                .addTask(row);
    }

    @Test
    @Parameters({"row"})
    public void TC_AddTaskAddAttachUpdateStatusSpecified(@Optional("3") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .goEmployees()
                .verifyTeam(row)
                .goProjects()
                .addNewProject(row)
                .search(row)
                .addTask(row)
                .addAttachFiles(row)
                .updateStatus(row);
    }

    @Test(dataProvider = "data_combine_CP", dataProviderClass = DataProviderCombine.class)
    public void TC_AddNewProject(Hashtable<String, String> client, Hashtable<String, String> project) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(client)
                .goEmployees()
                .verifyTeam(project)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(project)
                .verifyResults(project);
    }

    @Test(dataProvider = "data_combine_CPT", dataProviderClass = DataProviderCombine.class)
    public void TC_AddTasks(Hashtable<String, String> clients, Hashtable<String, String> projects, Hashtable<String, String> tasks) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(clients)
                .goEmployees()
                .verifyTeam(projects)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(projects)
                .goProjects()
                .search(projects)
                .addTask(tasks);
    }

    @Test(dataProvider = "data_add_projects", dataProviderClass = DataProviderProjects.class)
    public void TC_DeleteProjects(Hashtable<String, String> data) {
        loginPage.loginAdminHRM()
                .goProjects()
                .deleteProject(data);
    }

    @Test(dataProvider = "data_combine_CPT", dataProviderClass = DataProviderCombine.class)
    public void TC_AddTask_Attach_UpdateStatus(Hashtable<String, String> clients, Hashtable<String, String> projects, Hashtable<String, String> tasks) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(clients)
                .goEmployees()
                .verifyTeam(projects)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(projects)
                .goProjects()
                .search(projects)
                .addTask(tasks)
                .addAttachFiles(tasks)
                .updateStatus(tasks);
    }
}
