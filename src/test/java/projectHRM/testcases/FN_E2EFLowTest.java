package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderCombine;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.*;

import java.util.Hashtable;

public class FN_E2EFLowTest extends BaseTest {
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
    @Parameters({"row"})
    public void TC_E2E_Spec(@Optional("3") int row){
        loginPage.loginAdminHRM()
                .verifyDashboardPage()
                .goManageClients()
                .addClient(row)//Add Client
                .goManageClients()
                .editClient(row)//Edit Client
                .searchClient(row)
                .verifyDataClient(row)
                .logOut()
                .loginClientHRM(row)//Login With Client
                .verifyDashboardPage()
                .logoutClient()
                .loginAdminHRM()
                .goEmployees()//Add Team of Project
                .verifyTeam(row)
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
                .deleteProject(row)
                .goManageClients()
                .deleteClient(row)//Delete Client
                .logOut();
    }

    @Test(dataProvider = "data_combine_CPT", dataProviderClass = DataProviderCombine.class)
    public void TC_E2E(Hashtable<String, String> clients, Hashtable<String, String> projects, Hashtable<String, String> tasks){
        loginPage.loginAdminHRM()
                .verifyDashboardPage()
                .goManageClients()
                .addClient(clients)//Add Client
                .goManageClients()
                .editClient(clients)//Edit Client
                .searchClient(clients)
                .verifyDataClient(clients)
                .logOut()
                .loginClientHRM(clients)//Login With Client
                .verifyDashboardPage()
                .logoutClient()
                .loginAdminHRM()
                .goEmployees()//Add Team of Project
                .verifyTeam(projects)
                .goProjects()
                .verifyProjectsPage()
                .addNewProject(projects)//Add Project
                .verifyResults(projects)
                .addTask(tasks)//Add Task
                .getTabEdit()//Tab Edit
                .addAttachFiles(tasks)//Add Attach
                .updateStatus(tasks)//Update status
                .goTasks()
                .deleteTask(tasks)//Delete Task
                .goProjects()
                .deleteProject(projects)
                .goManageClients()
                .deleteClient(clients)//Delete Client
                .logOut();
    }
}
