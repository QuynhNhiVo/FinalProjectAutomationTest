package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;

import static keywords.WebUI.*;

public class ManageClientsPage extends CommonPage{

    private String subdir = "/erp/clients-list";
    private String title = "Manage Clients | HRM | Anh Tester Demo";
    private String textSubHeading = "List All Clients";
    private String textHeadAddNew = "Add New Client";
    private String totalRecord = "//div[@id='xin_table_info']";
    private String textAlert = "Client updated.";

    private By subHeading = By.xpath("//h5[contains(text(),'List All')]");
    private By inputSearch = By.xpath("//input[@type='search']");
    private By firstItemResult = By.xpath("//tbody/tr[1]/td/div[1]");
    private By buttonDetail = By.xpath("//tbody/tr[1]/td/div[2]//button[@class='btn icon-btn btn-sm btn-light-primary waves-effect waves-light']");
    private By buttonDelete = By.xpath("//tbody/tr[1]/td/div[2]//button[@data-target='.delete-modal']");

    private By buttonAddNew = By.xpath("//div[@class='card-header-right']/a[contains(.,'Add New')]");
    private By subHeadAddNew = By.xpath("//h5[contains(text(),'Add New')]");
    private By inputFirstName = By.xpath("//input[@name='first_name']");
    private By inputLastName = By.xpath("//input[@name='last_name']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By inputContactNumber = By.xpath("//input[@name='contact_number']");
    private By buttonGender = By.xpath("//span[@role='presentation']");
    private By dropdownGender = By.xpath("//select[@name='gender']");
    private By inputEmail = By.xpath("//input[@name='email']");
    private By inputUsername  = By.xpath("//input[@name='username']");
    private By inputAttachment  = By.xpath("//input[@name='file']");
    private By buttonSaveAdd = By.xpath("//span[normalize-space()='Save']");

    private By buttonStatus= By.xpath("//select[@name='status']/following-sibling::span");
    private By dropdownStatus = By.xpath("//select[@name='status']");
    private By buttonCountry = By.xpath("//select[@name='country']/following-sibling::span");
    private By dropdownCountry = By.xpath("//select[@name='country']");
    private By inputAddress1 = By.xpath("//input[@name='address_1']");
    private By inputAddress2 = By.xpath("//input[@name='address_2']");
    private By inputCity = By.xpath("//input[@name='city']");
    private By inputState = By.xpath("//input[@name='state']");
    private By inputZip = By.xpath("//input[@name='zipcode']");
    private By buttonSaveIf = By.xpath("//div[@class='card-body']//button");

    public ManageClientsPage verifyClientsPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertEqual(getTitlePage(), title);
        softAssertEqual(getTextElement(subHeading), textSubHeading);
        endAssert();
        return this;
    }

    public ManageClientsPage addClient(int row){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        clickElement(buttonAddNew);
        verifyEqual(getTextElement(subHeadAddNew), textHeadAddNew, "Heading not match");
        setText(inputFirstName, excelHelpers.getCellData("FIRSTNAME", row));
        setText(inputLastName, excelHelpers.getCellData("LASTNAME", row));
        setText(inputPassword, excelHelpers.getCellData("PASSWORD", row));
        setText(inputContactNumber, excelHelpers.getCellData("CONTACT", row));
        handleDropdown(buttonGender, dropdownGender, "text", excelHelpers.getCellData("GENDER", row).split(", "));
        setText(inputEmail, excelHelpers.getCellData("EMAIL", row));
        setText(inputUsername, excelHelpers.getCellData("USERNAME", row));
        uploadFileSendKey(inputAttachment, excelHelpers.getCellData("ATTACHMENT", row));
        clickElement(buttonSaveAdd);
        return this;
    }

    public ManageClientsPage editClient(int row){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        setText(inputSearch, excelHelpers.getCellData("FIRSTNAME", row));
        sleep(2);
        verifyRecordAndPagination(1, excelHelpers.getCellData("FIRSTNAME", row));
        clickElement(buttonDetail);
        sleep(2);
        handleDropdown(buttonStatus, dropdownStatus, "text", excelHelpers.getCellData("STATUS", row).split(", "));
        handleDropdown(buttonCountry, dropdownCountry, "text", excelHelpers.getCellData("COUNTRY", row).split(", "));
        setText(inputAddress1, excelHelpers.getCellData("ADDRESS", row));
        setText(inputAddress2, excelHelpers.getCellData("ADDRESS2", row));
        setText(inputCity, excelHelpers.getCellData("CITY", row));
        setText(inputState, excelHelpers.getCellData("STATE", row));
        setText(inputZip, excelHelpers.getCellData("ZIPCODE", row));
        clickElement(buttonSaveIf);
        verifyEqual(getTextAlert(), textAlert, "Not show correct alert");
        goToPreviousPage();
        return this;
    }

    public ManageClientsPage verifySearchClient(int row){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        setText(inputSearch, excelHelpers.getCellData("FIRSTNAME", row));
        sleep(2);
        verifyRecordAndPagination(1, excelHelpers.getCellData("FIRSTNAME", row));
        clickElement(buttonDetail);
        sleep(2);
        softAssertEqual(getAttributeElement(inputFirstName, "value"), excelHelpers.getCellData("FIRSTNAME", row));
        softAssertEqual(getAttributeElement(inputLastName, "value"), excelHelpers.getCellData("LASTNAME", row));
        softAssertEqual(getAttributeElement(inputEmail, "value"), excelHelpers.getCellData("EMAIL", row));
        softAssertEqual(getAttributeElement(inputUsername, "value"), excelHelpers.getCellData("USERNAME", row));
        softAssertEqual(getAttributeElement(inputContactNumber, "value"), excelHelpers.getCellData("CONTACT", row));
        softAssertEqual(getFirstOptionSelected(dropdownGender), excelHelpers.getCellData("GENDER", row));
        softAssertEqual(getFirstOptionSelected(dropdownCountry), excelHelpers.getCellData("GENDER", row));
        softAssertEqual(getAttributeElement(inputAddress1, "value"), excelHelpers.getCellData("ADDRESS", row));
        softAssertEqual(getAttributeElement(inputAddress2, "value"), excelHelpers.getCellData("ADDRESS2", row));
        softAssertEqual(getAttributeElement(inputCity, "value"), excelHelpers.getCellData("CITY", row));
        softAssertEqual(getAttributeElement(inputState, "value"), excelHelpers.getCellData("STATE", row));
        softAssertEqual(getAttributeElement(inputZip, "value"), excelHelpers.getCellData("ZIPCODE", row));
        return this;
    }
    
}
