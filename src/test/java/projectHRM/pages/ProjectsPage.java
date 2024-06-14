package projectHRM.pages;

import static keywords.WebUI.*;
import org.openqa.selenium.By;

public class ProjectsPage extends CommonPage{
    private String subdir = "/erp/projects-list";
    private String title = "Projects | HRM | Anh Tester Demo";
    private String textHeadProject = "List All Projects";

    private By headingProjects = By.xpath("//h5[contains(text(),'List All')]");
    private By buttonAddNew = By.xpath("//h5[contains(text(),'List All')]/following-sibling::div/a[normalize-space()='Add New']");
    private By inputTitle = By.xpath("//input[@name='title']");
    private By buttonClients = By.xpath("//label[@for='client_id']/following-sibling::span");
    private By dropdownClients = By.xpath("//select[@id='client_id']");
    private By inputHour = By.xpath("//input[@name='budget_hours']");
    private By buttonPriority = By.xpath("//label[normalize-space()='Priority']/following-sibling::span");
    private By dropdownPriority = By.xpath("//select[@name='priority']");
    private By inputStartDate = By.xpath("//label[@for='start_date']/following-sibling::div//input");
    private By inputEndDate = By.xpath("//input[@name='end_date']");
    private By inputSummary = By.xpath("//textarea[@id='summary']");
    private By buttonTeam = By.xpath("//label[normalize-space()='Team']/following-sibling::span");
    private By dropdownTeam = By.xpath("//select[@name='assigned_to");
    private By iframeAdd = By.xpath("//td[@class='k-editable-area']//iframe");
    private By inputDescription = By.xpath("//textarea[@id='description']");
    private By buttonReset = By.xpath("//button[normalize-space()='Reset']");
    private By buttonSave = By.xpath("//div[@class='card-footer text-right']//button[@type='submit']");

    private By firstResult = By.xpath("//tbody/tr[1]/td[1]");
    private By buttonDetail = By.xpath("//tbody/tr[1]/td[1]/div/span[contains(@data-original-title,'View Details')]");
    private By buttonDelete = By.xpath("//tbody/tr[1]/td[1]/div/span[contains(@data-original-title,'Delete')]");
    private By headingDetail = By.xpath("//h5[contains(text(),'Project :')]");
    private By tabTasks = By.xpath("//a[@id='pills-tasks-tab']");
    private By headingTasks = By.xpath("//h5[normalize-space()='List All Tasks']");
    private By buttonAddTasks = By.xpath("//a[normalize-space()='Add New']");
    private By inputTitleTasks = By.xpath("//input[@name='task_name']");
    private By inputHourTasks = By.xpath("//input[@name='task_hour']");
    private By inputStartTasks = By.xpath("((//input[@name='task_name']/parent::div)/parent::div)/following-sibling::div[1]/div/div/input[@name='start_date']");
    private By inputEndTasks = By.xpath("((//input[@name='task_name']/parent::div)/parent::div)/following-sibling::div[2]/div/div/input[@name='end_date']");
    private By inputSmrTasks = By.xpath("//textarea[@id='summary']");
    private By inputDescTasks = By.xpath("//textarea[@id='description']");
    private By saveTasks = By.xpath("//form[@id='add_task']//button[@type='submit']");

    private By buttonStatus = By.xpath("//span[normalize-space()='Update Status']");

    private By tabAttach = By.xpath("//a[@id='pills-files-tab']");
    private By inputTitleAt = By.xpath("//input[@name='file_name']");
    private By buttonAddFile = By.xpath("//button[contains(.,'Add File')]");

    public ProjectsPage verifyProjectsPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        softAssertEqual(getTextElement(headingProjects), textHeadProject);
        return this;
    }

    public ProjectsPage addNewProject(){
        clickElement(buttonAddNew);
        sleep(3);
        getStartDate(inputStartDate, "Aug", "2021", "15");
        sleep(4);
        return this;
    }

    public ProjectsPage addDate(){
        clickElement(buttonAddNew);
        sleep(3);
        clickDate(inputStartDate,"13");
        sleep(4);
        return this;
    }
}
