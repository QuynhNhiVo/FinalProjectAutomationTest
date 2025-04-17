package projectHRM.pages;

import org.openqa.selenium.By;
import static keywords.WebUI.*;

public class DashboardPage extends CommonPage{
    private String subdir = "/erp/desk";
    private String title = "Home | HRM | Anh Tester Demo";

    private By navigationBars = By.xpath("//ul[@class='pc-navbar']");

    public DashboardPage verifyDashboardPage() {
        waiForPageLoad(4);
        sleep(4);
        softAssertContain(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        endAssert();
        verifyElementVisible(navigationBars);
        getTextListElement(navigationBars);
        return this;
    }
}
