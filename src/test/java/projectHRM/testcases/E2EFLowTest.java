package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projectHRM.pages.*;

public class E2EFLowTest extends BaseTest {
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
    public void TC_E2E(@Optional("3") int row){
        loginPage.loginAdminHRM()
                .goEmployees()
                .verifyTeam(row)
                .goManageClients()
                .addClient(row)
                .goManageClients()
                .editClient(row)//Edit Client
                .searchClient(row)
                .verifyDataClient(row)
                .logOut()
                .loginClientHRM(row)//Login With Client
                .verifyDashboardPage()
                .logoutClient()
                .loginAdminHRM()
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
                .goManageClients()
                .deleteClient(row)//Delete Client
                .logOut();
    }
}
