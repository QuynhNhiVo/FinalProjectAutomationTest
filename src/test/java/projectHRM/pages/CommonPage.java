package projectHRM.pages;

public class CommonPage {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    public LoginPage goLoginPage(){
        if(loginPage ==null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public DashboardPage goDashboardPage(){
        if (dashboardPage==null){
            dashboardPage = new DashboardPage();
        }
        return dashboardPage;
    }
}
