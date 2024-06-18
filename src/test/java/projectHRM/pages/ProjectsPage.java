package projectHRM.pages;

import static keywords.WebUI.*;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;

import java.util.Hashtable;

public class ProjectsPage extends CommonPage {
    private String subdir = "/erp/projects-list";
    private String title = "Projects | HRM | Anh Tester Demo";
    private String textHeadProject = "List All Projects";
    private String textTaskAdd = "Task added.";
    private String textUpFile = "Project file added.";
    private String textStatus = "Project status updated.";
    private String textDelProj = "Project deleted.";

    private By headingProjects = By.xpath("//h5[contains(text(),'List All')]");
    private By buttonAddNew = By.xpath("//h5[contains(text(),'List All')]/following-sibling::div/a[normalize-space()='Add New']");
    private By inputTitle = By.xpath("//input[@name='title']");
    private By buttonClients = By.xpath("(//label[@for='client_id']/following-sibling::span)/span/span/span[@role='presentation']");
    private By dropdownClients = By.xpath("//select[@id='client_id']");
    private By inputHour = By.xpath("//input[@name='budget_hours']");
    private By buttonPriority = By.xpath("//label[normalize-space()='Priority']/following-sibling::span");
    private By dropdownPriority = By.xpath("//select[@name='priority']");
    private By inputStartDate = By.xpath("//label[@for='start_date']/following-sibling::div//input");
    private By saveStartDate = By.xpath("(//div[@class='dtp-date-view'])[1]/following-sibling::div//button[.='OK']");
    private By inputEndDate = By.xpath("//label[@for='end_date']/following-sibling::div/input");
    private By saveEndDate = By.xpath("(//div[@class='dtp-date-view'])[2]/following-sibling::div//button[.='OK']");
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
    private By headingDetail = By.xpath("//h5[contains(text(),'Project :')]");
    private By tabTasks = By.xpath("//a[@id='pills-tasks-tab']");
    private By headingTasks = By.xpath("//h5[normalize-space()='List All Tasks']");
    private By buttonAddTasks = By.xpath("//a[normalize-space()='Add New']");
    private By inputTitleTasks = By.xpath("//input[@name='task_name']");
    private By inputHourTasks = By.xpath("//input[@name='task_hour']");
    private By inputStartTasks = By.xpath("((//input[@name='task_name']/parent::div)/parent::div)/following-sibling::div[1]/div/div/input[@name='start_date']");
    private By saveStartTasks= By.xpath("(//div[@class='dtp-buttons'])[5]/button[contains(.,'OK')]");
    private By inputEndTasks = By.xpath("((//input[@name='task_name']/parent::div)/parent::div)/following-sibling::div[2]/div/div/input[@name='end_date']");
    private By saveEndTasks= By.xpath("(//div[@class='dtp-buttons'])[6]/button[contains(.,'OK')]");
    private By inputSmrTasks = By.xpath("//textarea[@id='summary']");
    private By iframeTask = By.xpath("(//td[@class='k-editable-area'][1])[4]/iframe");
    private By saveTasks = By.xpath("//form[@id='add_task']//button[@type='submit']");
    private By message = By.xpath("//div[@class='toast-message']");

    private By buttonDelete = By.xpath("//tbody/tr[1]/td[1]/div/span[contains(@data-original-title,'Delete')]");
    private By confirmDel = By.xpath("//span[normalize-space()='Confirm']");

    private By buttonStatus = By.xpath("//span[normalize-space()='Update Status']");
    private By tabEdit = By.xpath("//a[@id='pills-edit-tab']");
    private By tabAttach = By.xpath("//a[@id='pills-files-tab']");
    private By uploadFiles = By.xpath("//input[@id='attachment_file']");
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
        waiForPageLoad();
        clickElement(buttonDetail);
        sleep(2);
    }

    private void getTabTask(){
        waiForPageLoad();
        clickElement(tabTasks);
        sleep(2);
    }

    private void getTabAttach(){
        waiForPageLoad();
        clickElement(tabAttach);
        sleep(2);
    }

    public ProjectsPage getTabEdit(){
        waiForPageLoad();
        clickElement(tabEdit);
        return this;
    }

    public ProjectsPage search(int row){
        goProjects();
        clearSetText(inputSearch, excelHelpers.getCellData("CLIENT",row));
        sleep(2);
        return this;
    }

    public ProjectsPage search(Hashtable<String, String> data){
        goProjects();
        clearSetText(inputSearch, data.get("CLIENT"));
        sleep(2);
        return this;
    }

    public ProjectsPage verifyProjectsPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        softAssertEqual(getTextElement(headingProjects), textHeadProject);
        return this;
    }

    public ProjectsPage addNewProject(@Optional("1") int row){
        clickElement(buttonAddNew);
        setText(inputTitle, excelHelpers.getCellData("TITLE",row));
        handleDropdown(buttonClients, dropdownClients, "value", excelHelpers.getCellData("CLIENT",row).split(", "));
        setText(inputHour, excelHelpers.getCellData("HOUR",row));
        handleDropdown(buttonPriority, dropdownPriority, "text", excelHelpers.getCellData("PRIORITY",row).split(", "));
        getStartDate(inputStartDate, excelHelpers.getCellData("START_DATE",row).split(", "), saveStartDate);
        getEndDate(inputEndDate, excelHelpers.getCellData("END_DATE",row).split(", "), saveEndDate);
        setText(inputSummary, excelHelpers.getCellData("SUMMARY",row));
        handleDropdown(buttonTeam, dropdownTeam, "text", excelHelpers.getCellData("TEAM",row).split(", "));
        goIframe(iframeAdd);
        setText(inputDescription, excelHelpers.getCellData("DESCRIPTION",row));
        exitIframe();
        clickElement(buttonSave);
        sleep(2);
        return this;
    }

    public ProjectsPage addNewProject(Hashtable<String, String> data){
        clickElement(buttonAddNew);
        setText(inputTitle, data.get("TITLE"));
        handleDropdown(buttonClients, dropdownClients, "value", data.get("CLIENT").split(", "));
        setText(inputHour, data.get("HOUR"));
        handleDropdown(buttonPriority, dropdownPriority, "text", data.get("PRIORITY").split(", "));
        getStartDate(inputStartDate, data.get("START_DATE").split(", "), saveStartDate);
        getEndDate(inputEndDate, data.get("END_DATE").split(", "), saveEndDate);
        setText(inputSummary, data.get("SUMMARY"));
        handleDropdown(buttonTeam, dropdownTeam, "text", data.get("TEAM").split(", "));
        goIframe(iframeAdd);
        setText(inputDescription, data.get("DESCRIPTION"));
        exitIframe();
        clickElement(buttonSave);
        sleep(2);
        return this;
    }

    public ProjectsPage verifyResults(int row){
        search(row);
        verifyRecordAndPagination(2, excelHelpers.getCellData("CLIENT",row));
        return this;
    }

    public ProjectsPage verifyResults(Hashtable<String, String> data){
        search(data);
        verifyRecordAndPagination(2, data.get("CLIENT"));
        return this;
    }

    public ProjectsPage deleteProject(int row){
        search(row);
        clickElement(buttonDelete);
        sleep(1);
        clickElement(confirmDel);
        verifyEqual(getTextElement(message), textDelProj, "Not show correct message");
        sleep(5);
        return this;
    }

    public ProjectsPage deleteProject(Hashtable<String, String> data){
        clearSetText(inputSearch, data.get("CLIENT"));
        clickElement(buttonDelete);
        sleep(1);
        clickElement(confirmDel);
        verifyEqual(getTextElement(message), textDelProj, "Not show correct message");
        sleep(5);
        return this;
    }

    public ProjectsPage addTask(int row){
        getDetail();
        getTabTask();
        verifyContain(getTextElement(headingDetail), excelHelpers.getCellData("TITLE",row), "This not the project page detail!");
        clickElement(buttonAddTasks);
        setText(inputTitleTasks, excelHelpersTask.getCellData("TITLE",row));
        getStartDate(inputStartTasks, excelHelpersTask.getCellData("START_DATE",row).split(", "), saveStartTasks);
        getEndDate(inputEndTasks, excelHelpersTask.getCellData("END_DATE",row).split(", "), saveEndTasks);
        setText(inputHourTasks, excelHelpersTask.getCellData("HOUR",row));
        setText(inputSmrTasks, excelHelpersTask.getCellData("SUMMARY",row));
        goIframe(iframeTask);
        setText(inputDescription, excelHelpersTask.getCellData("DESCRIPTION",row));
        exitIframe();
        sleep(1);
        clickElement(saveTasks);
        verifyEqual(getTextElement(message), textTaskAdd, "Not show correct alert");
        sleep(2);
        return this;
    }

    public ProjectsPage addTask(Hashtable<String, String> data){
        setText(inputSearch, data.get("CLIENT"));
        sleep(2);
        getDetail();
        getTabTask();
        verifyContain(getTextElement(headingDetail), data.get("PROJECT"), "This not the project page detail!");
        clickElement(buttonAddTasks);
        setText(inputTitleTasks, data.get("TITLE"));
        getStartDate(inputStartTasks, data.get("START_DATE").split(", "), saveStartTasks);
        getEndDate(inputEndTasks, data.get("END_DATE").split(", "), saveEndTasks);
        setText(inputHourTasks, data.get("HOUR"));
        setText(inputSmrTasks, data.get("SUMMARY"));
        goIframe(iframeTask);
        setText(inputDescription, data.get("DESCRIPTION"));
        exitIframe();
        sleep(2);
        clickElement(saveTasks);
        verifyEqual(getTextElement(message), textTaskAdd, "Not show correct alert");
        sleep(4);
        return this;
    }

    public ProjectsPage addAttachFiles(int row){
        getTabAttach();
        uploadFileSendKey(uploadFiles, excelHelpersTask.getCellData("ATTACH", row));
        setText(inputTitleAt,  excelHelpersTask.getCellData("TITLE_FILE", row));
        clickElement(buttonAddFile);
        verifyEqual(getTextElement(message), textUpFile,"Not show correct alert");
        sleep(4);
        return this;
    }

    public ProjectsPage addAttachFiles(Hashtable<String, String> data){
        getTabAttach();
        uploadFileSendKey(uploadFiles, data.get("ATTACH"));
        setText(inputTitleAt,  data.get("TITLE_FILE"));
        clickElement(buttonAddFile);
        verifyEqual(getTextElement(message), textUpFile,"Not show correct alert");
        sleep(4);
        return this;
    }

    public ProjectsPage updateStatus(int row){
        waiForPageLoad();
        chooseStatus(excelHelpersTask.getCellData("STATUS", row));
        clickElement(buttonStatus);
        verifyEqual(getTextElement(message), textStatus,"Not show correct alert");
        return this;
    }

    public ProjectsPage updateStatus(Hashtable<String, String> data){
        waiForPageLoad();
        chooseStatus(data.get("STATUS"));
        clickElement(buttonStatus);
        verifyEqual(getTextElement(message), textStatus,"Not show correct alert");
        sleep(5);
        return this;
    }
}
