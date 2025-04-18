package projectHRM.pages;

import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class CommonPage {

    private By buttonLogout = By.xpath("//i[@class='feather icon-power']");
    private By logoutClient = By.xpath("//li[10]//a[1]");
    private By menuHome = By.xpath("//span[normalize-space()='Home']");
    private By menuClients = By.xpath("//span[normalize-space()='Manage Clients']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    private By menuEmployees = By.xpath("//span[normalize-space()='Employees']");
    private By hamburgerButton = By.xpath("//div[@class='hamburger-inner']");

    LoginPage loginPage;
    DashboardPage dashboardPage;
    ManageClientsPage manageClientsPage;
    ProjectsPage projectsPage;
    TasksPage tasksPage;
    EmployeesPage employeesPage;

    public LoginPage logOut(){
        clickElement(buttonLogout);
        return new LoginPage();
    }

    public LoginPage logoutClient(){
        verifyAndClick(menuHome, hamburgerButton);
        clickElement(logoutClient);
        return new LoginPage();
    }

    public ManageClientsPage goManageClients(){
        verifyAndClick(menuHome, hamburgerButton);
        scrollDownMenuBar(menuHome);
        clickElement(menuClients);
        return new ManageClientsPage();
    }

    public ProjectsPage goProjects(){
        verifyAndClick(menuProjects, hamburgerButton);
        clickElement(menuProjects);
        return new ProjectsPage();
    }

    public TasksPage goTasks(){
        verifyAndClick(menuTasks, hamburgerButton);
        clickElement(menuTasks);
        return new TasksPage();
    }

    public EmployeesPage getEmployees(){
        verifyAndClick(menuEmployees, hamburgerButton);
        clickElement(menuEmployees);
        return new EmployeesPage();
    }

    public LoginPage getLoginPage(){
        if(loginPage ==null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage getDashboardPage(){
        if (dashboardPage==null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }

    public ManageClientsPage getManagerClientsPage(){
        if(manageClientsPage==null){
            manageClientsPage = new ManageClientsPage();
        }
        return manageClientsPage;
    }

    public ProjectsPage getProjectsPage(){
        if(projectsPage==null){
            projectsPage = new ProjectsPage();
        }
        return projectsPage;
    }

    public TasksPage getTasksPage(){
        if(tasksPage==null){
            tasksPage = new TasksPage();
        }
        return tasksPage;
    }

    public EmployeesPage getEmployeesPage(){
        if (employeesPage == null) {
            employeesPage = new EmployeesPage();
        }
        return employeesPage;
    }
}
