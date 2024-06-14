package projectHRM.testcases;

import common.BaseTest;
import org.testng.annotations.Test;
import projectHRM.pages.DashboardPage;
import projectHRM.pages.LoginPage;
import projectHRM.pages.ProjectsPage;

public class ProjectsPageTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectsPage projectsPage;

    public ProjectsPageTest(){
        loginPage = new LoginPage();
    }

    @Test
    public void TC_TestDate(){
        loginPage.loginAdminHRM()
            .goProjects()
            .addDate();
    }
}
