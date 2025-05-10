package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import utils.LogUtils;

import java.util.Hashtable;

import static keywords.WebUI.*;

public class EmployeesPage extends CommonPage {

    private String subdir = "/erp/staff-list";
    private String title = "Employees | HRM | Anh Tester Demo";
    private String textSubHeading = "List All Employees";
    private String textHeadAddNew = "Add New Client";
    private String textAlertEdit = "Client updated.";
    private String textClose = "You won't be able to revert this!";
    private String textConfirm = "Client deleted.";

    private By subHeading = By.xpath("//h5[contains(text(),'List All')]");
    private By inputSearch = By.xpath("//input[@type='search']");
    private By firstItemResult = By.xpath("//tr[1]//td//div//div//h6");

    private By buttonAddNew = By.xpath("(//h5[contains(text(),'List All')]/following-sibling::div)/a[2]");
    private By inputFirstName = By.xpath("//input[@placeholder='First Name']");
    private By inputLastName = By.xpath("//input[@placeholder='Last Name']");
    private By inputContact = By.xpath("//input[@placeholder='Contact Number']");
    private By inputEmail = By.xpath("//input[@placeholder='Email']");
    private By inputUsername = By.xpath("//input[@placeholder='Username']");
    private By inputPassword = By.xpath("//input[@placeholder='Password']");
    private By inputOfficeShift = By.xpath("//span[contains(text(),'Office Shift')]");
    private By inputRole = By.xpath("//span[contains(text(),'Role')]");
    private By buttonDepartment = By.xpath("(//label[normalize-space()='Department']/following-sibling::span[2])/span/span/span/b");
    private By dropdownDepartment = By.xpath("//select[@id='department_id']");
    private By inputDesignation = By.xpath("//span[contains(text(),'Designation')]");
    private By inputPicture = By.xpath("//input[@name='file']");
    private By buttonSave = By.xpath("//span[normalize-space()='Save']");


    private final ExcelHelpers excelHelpers;
    private final ExcelHelpers excelHelpers1;

    public EmployeesPage() {
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Employees");
        this.excelHelpers1 = new ExcelHelpers();
        this.excelHelpers1.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "Projects");
    }

    public EmployeesPage verifyEmployeesPage() {
        softAssertUrl(getURLPage(), subdir);
        softAssertContain(getTitlePage(), title);
        softAssertContain(getTextElement(subHeading), textSubHeading);
        endAssert();
        return this;
    }

    public EmployeesPage searchTeam(int row) {
        setText(inputSearch, excelHelpers1.getCellData("TEAM", row));

        return this;
    }

    public EmployeesPage addNewEmployees(int row) {
        clickElement(buttonAddNew);
        setText(inputFirstName, excelHelpers.getCellData("FirstName", row));
        setText(inputLastName, excelHelpers.getCellData("LastName", row));
        setText(inputContact, excelHelpers.getCellData("Contact", row));
        setText(inputEmail, excelHelpers.getCellData("Email", row));
        setText(inputUsername, excelHelpers.getCellData("Username", row));
        setText(inputPassword, excelHelpers.getCellData("Password", row));
        clickTextBox(inputOfficeShift);
        clickTextBox(inputRole);
        handleDropdown(buttonDepartment, dropdownDepartment, "text", excelHelpers.getCellData("Department", row).split(" "));
        clickTextBox(inputDesignation);
        uploadFileSendKey(inputPicture, excelHelpers.getCellData("Attach", row));
        clickElement(buttonSave);
        return this;
    }

    public EmployeesPage verifyTeam(int row) {
        setText(inputSearch, excelHelpers1.getCellData("TEAM", row));

        String nameTeam = (excelHelpers.getCellData("FirstName", row) + " " + excelHelpers.getCellData("LastName", row));
        boolean check =
                getWebElement(firstItemResult).getText().toLowerCase().trim().contains(nameTeam.toLowerCase().trim());
        LogUtils.info(getWebElement(firstItemResult).getText());

        if (check) {
            return this;
        }
        if (excelHelpers1.getCellData("TEAM", row).contains("Project Manager")) {
            addNewEmployees(1);
        } else {
            addNewEmployees(2);
        }

        return this;
    }

    public EmployeesPage verifyTeam(Hashtable<String, String> data) {
        setText(inputSearch, data.get("TEAM"));

        String nameTeam = (data.get("FirstName") + " " + data.get("LastName"));
        boolean check =
                getWebElement(firstItemResult).getText().toLowerCase().trim().contains(nameTeam.toLowerCase().trim());

        if (check) {
            return this;
        } else {
            if (data.get("TEAM").contains("Project Manager")) {
                addNewEmployees(1);
            } else {
                addNewEmployees(2);
            }
        }
        return this;
    }


}
