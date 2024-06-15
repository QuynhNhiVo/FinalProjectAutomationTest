package projectHRM.pages;

import static keywords.WebUI.*;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Hashtable;

public class ProjectsPage extends CommonPage{
    private String subdir = "/erp/projects-list";
    private String title = "Projects | HRM | Anh Tester Demo";
    private String textHeadProject = "List All Projects";

    private By headingProjects = By.xpath("//h5[contains(text(),'List All')]");
    private By buttonAddNew = By.xpath("//h5[contains(text(),'List All')]/following-sibling::div/a[normalize-space()='Add New']");
    private By inputTitle = By.xpath("//input[@name='title']");
    private By buttonClients = By.xpath("(//label[@for='client_id']/following-sibling::span)/span/span/span[@role='presentation']");
    private By dropdownClients = By.xpath("//select[@id='client_id']");
    private By inputHour = By.xpath("//input[@name='budget_hours']");
    private By buttonPriority = By.xpath("//label[normalize-space()='Priority']/following-sibling::span");
    private By dropdownPriority = By.xpath("//select[@name='priority']");
    private By inputStartDate = By.xpath("//label[@for='start_date']/following-sibling::div//input");
    private By inputEndDate = By.xpath("//label[@for='end_date']/following-sibling::div/input");
    private By inputSummary = By.xpath("//textarea[@id='summary']");
    private By buttonTeam = By.xpath("//label[normalize-space()='Team']/following-sibling::span");
    private By dropdownTeam = By.xpath("//select[@name='assigned_to[]']");
    private By iframeAdd = By.xpath("//td[@class='k-editable-area']//iframe");
    private By inputDescription = By.xpath("//body[@contenteditable='true']");
    private By buttonReset = By.xpath("//button[normalize-space()='Reset']");
    private By buttonSave = By.xpath("//button[normalize-space()='Reset']/following-sibling::button");

    private By firstResult = By.xpath("//tbody/tr[1]/td[1]");
    private By inputSearch = By.xpath("//input[@class='form-control form-control-sm']");
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
    private By iframeTask = By.xpath("(//td[@class='k-editable-area'][1])[4]/iframe");
    private By inputDescTasks = By.xpath("//textarea[@id='description']");
    private By saveTasks = By.xpath("//form[@id='add_task']//button[@type='submit']");

    private By buttonStatus = By.xpath("//span[normalize-space()='Update Status']");

    private By tabAttach = By.xpath("//a[@id='pills-files-tab']");
    private By inputTitleAt = By.xpath("//input[@name='file_name']");
    private By buttonAddFile = By.xpath("//button[contains(.,'Add File')]");

    private final ExcelHelpers excelHelpers;
    private final ExcelHelpers excelHelpersTask;

    public ProjectsPage(){
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Projects");
        this.excelHelpersTask = new ExcelHelpers();
        this.excelHelpersTask.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Tasks");
    }

    private void getDetail(){
        clickElement(buttonDetail);
        sleep(2);
    }

    private void search(int row){
        setText(inputSearch, excelHelpers.getCellData("TITLE",row));
        sleep(2);
    }

    private void search(Hashtable<String, String> data){
        setText(inputSearch, data.get("TITLE"));
    }

    public ProjectsPage verifyProjectsPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        softAssertEqual(getTextElement(headingProjects), textHeadProject);
        return this;
    }

    @Parameters({"row"})
    public ProjectsPage addNewProject(@Optional("1") int row){
        clickElement(buttonAddNew);
        setText(inputTitle, excelHelpers.getCellData("TITLE",row));
        handleDropdown(buttonClients, dropdownClients, "value", excelHelpers.getCellData("CLIENT",row).split(", "));
        setText(inputHour, excelHelpers.getCellData("HOUR",row));
        handleDropdown(buttonPriority, dropdownPriority, "text", excelHelpers.getCellData("PRIORITY",row).split(", "));
        getStartDate(inputStartDate, excelHelpers.getCellData("START_DATE",row).split(", "));
        getEndDate(inputEndDate, excelHelpers.getCellData("END_DATE",row).split(", "));
        setText(inputSummary, excelHelpers.getCellData("SUMMARY",row));
        handleDropdown(buttonTeam, dropdownTeam, "text", excelHelpers.getCellData("TEAM",row).split(", "));
        goIframe(iframeAdd);
        setText(inputDescription, excelHelpers.getCellData("DESCRIPTION",row));
        exitIframe();
        scrollClickElement(buttonSave);
        sleep(2);
        return this;
    }

    public ProjectsPage addNewProject(Hashtable<String, String> data){
        clickElement(buttonAddNew);
        setText(inputTitle, data.get("TITLE"));
        handleDropdown(buttonClients, dropdownClients, "value", data.get("CLIENT").split(", "));
        setText(inputHour, data.get("HOUR"));
        handleDropdown(buttonPriority, dropdownPriority, "text", data.get("PRIORITY").split(", "));
        getStartDate(inputStartDate, data.get("START_DATE").split(", "));
        getEndDate(inputEndDate, data.get("END_DATE").split(", "));
        setText(inputSummary, data.get("SUMMARY"));
        handleDropdown(buttonTeam, dropdownTeam, "text", data.get("TEAM").split(", "));
        goIframe(iframeAdd);
        setText(inputDescription, data.get("DESCRIPTION"));
        exitIframe();
        scrollClickElement(buttonSave);
        sleep(2);
        return this;
    }

    public ProjectsPage searchProject(int row){
        search(row);
        verifyRecordAndPagination(1, excelHelpers.getCellData("TITLE",row));
        return this;
    }

    public ProjectsPage addTask(int row){
        search(row);
        getDetail();
        clickElement(tabTasks);
        sleep(2);
        clickElement(buttonAddTasks);
        setText(inputTitleTasks, excelHelpersTask.getCellData("TITLE",row));
        getStartDate(inputStartTasks, excelHelpersTask.getCellData("START_DATE",row).split(", "));
        getEndDate(inputEndTasks, excelHelpersTask.getCellData("END_DATE",row).split(", "));
        setText(inputHourTasks, excelHelpersTask.getCellData("HOUR",row));
        setText(inputSmrTasks, excelHelpersTask.getCellData("SUMMARY",row));
        goIframe(iframeAdd);
        setText(inputDescTasks, excelHelpersTask.getCellData("DESCRIPTION",row));
        exitIframe();
        sleep(5);
        clickElement(buttonReset);
//        clickElement(saveTasks);
        return this;
    }
    public ProjectsPage addDate(){
        clickElement(buttonAddNew);
        scrollClickElement(buttonSave);
        sleep(5);
        return this;
    }


}
