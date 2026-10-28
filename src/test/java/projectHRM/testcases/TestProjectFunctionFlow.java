package projectHRM.testcases;

import common.BaseTest;
import constants.Groups;
import dataprovider.DataProviderCombine;
import dataprovider.DataProviderProjects;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.EmployeesPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ProjectsPage;

import java.util.Hashtable;

public class TestProjectFunctionFlow extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    EmployeesPage employeesPage;
    ProjectsPage projectsPage;

    @BeforeMethod
    public void initData() {
        loginPage = new LoginPage();
    }

    @Test(groups = { Groups.REGRESSION, Groups.PROJECT, Groups.TASK })
    @Parameters({ "row" })
    public void TC_Project_TaskFlowSpecified(@Optional("9") int row) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(row)
                .goEmployees()// Add Team of Project
                .verifyTeam(row)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(row)// Add Project
                .verifyResults(row)
                .addTask(row)// Add Task
                .getTabEdit()// Tab Edit
                .addAttachFiles(row)// Add Attach
                .updateStatus(row)// Update status
                .goTasks()
                .deleteTask(row)// Delete Task
                .goProjects()
                .deleteProject(row)// Delete Project
                .logOut();
    }

    @Test(groups = { Groups.REGRESSION, Groups.PROJECT,
            Groups.TASK }, dataProvider = ("data_combine_CPT"), dataProviderClass = DataProviderCombine.class)
    public void TC_Project_TaskFlow(Hashtable<String, String> clients, Hashtable<String, String> projects,
            Hashtable<String, String> tasks) {
        loginPage.loginAdminHRM()
                .goManageClients()
                .addClient(clients)
                .goEmployees()// Add Team of Project
                .verifyTeam(projects)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(projects)// Add Project
                .verifyResults(projects)
                .addTask(tasks)// Add Task
                .getTabEdit()// Tab Edit
                .addAttachFiles(tasks)// Add Attach
                .updateStatus(tasks)// Update status
                .goTasks()
                .deleteTask(tasks)// Delete Task
                .goProjects()
                .deleteProject(projects)// Delete Project
                .logOut();
    }

}
