package projectHRM.pages;

import static keywords.WebUI.*;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Hashtable;

public class TasksPage extends CommonPage{
    private String subdir = "/erp/tasks-list";
    private String title = "Tasks | HRM | Anh Tester Demo";
    private String textHeadProject = "List All Tasks";
    private String textMessage = "Task deleted.";

    private By headingTasks = By.xpath("//h5[contains(text(),'List All')]");
    private By inputSearch = By.xpath("//input[@type='search']");
    private By buttonDelete = By.xpath("//tbody//tr[1]//td[1]//div//span[@data-original-title='Delete']");
    private By buttonConfirm = By.xpath("//span[normalize-space()='Confirm']");
    private By message = By.xpath("//div[@class='toast-message']");
    private By tabEdit = By.xpath("//a[@id='pills-edit-tab']");

    private final ExcelHelpers excelHelpers;

    public TasksPage(){
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Tasks");
    }
    public TasksPage verifyTasksPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        softAssertEqual(getTextElement(headingTasks), textHeadProject);
        return this;
    }

    public TasksPage deleteTask(int row){
        clearSetText(inputSearch, excelHelpers.getCellData("TITLE", row));
        clickElement(buttonDelete);
        sleep(2);
        clickElement(buttonConfirm);
        verifyEqual(getTextElement(message), textMessage, "Not show correct message");
        return this;
    }

    public TasksPage deleteTask(Hashtable<String, String> data){
        clearSetText(inputSearch, data.get("TITLE"));
        clickElement(buttonDelete);
        sleep(2);
        clickElement(buttonConfirm);
        verifyEqual(getTextElement(message), textMessage, "Not show correct message");
        return this;
    }
}
