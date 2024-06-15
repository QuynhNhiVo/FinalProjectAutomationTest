package projectHRM.testcases;

import common.BaseTest;
import dataprovider.DataProviderProjects;
import org.testng.annotations.DataProvider;
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
    public void TC_AddProduct(){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(3);
    }

    @Test(dataProvider = "data_add_projects", dataProviderClass = DataProviderProjects.class)
    public void TC_AddProducts(Hashtable<String, String> data){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addNewProject(data);
    }

    @Test
    public void TC_AddTask(){
        loginPage.loginAdminHRM()
            .goProjects()
            .addTask(1);
    }

    @Test
    public void TC_TestDate1(){
        loginPage.loginAdminHRM()
            .goProjects()
            .verifyProjectsPage()
            .addDate();
    }
}
