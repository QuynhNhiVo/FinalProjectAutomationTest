package projectHRM.pages;

import constants.ConfigData;
import helpers.ExcelHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;

import java.util.Hashtable;

import static keywords.WebUI.*;

public class ManageClientsPage extends CommonPage{

    private String subdir = "/erp/clients-list";
    private String title = "Manage Clients | HRM | Anh Tester Demo";
    private String textSubHeading = "List All Clients";
    private String textHeadAddNew = "Add New Client";
    private String textAlertEdit = "Client updated.";
    private String textClose = "You won't be able to revert this!";
    private String textConfirm = "Client deleted.";

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

    private By buttonStatus= By.xpath("(//label[@for='membership_type']/following-sibling::span)//span//span//b");
    private By dropdownStatus = By.xpath("//select[@name='status']");
    private By buttonCountry = By.xpath("(//label[@for='country']/following-sibling::span)//span//span//span/b");
    private By dropdownCountry = By.xpath("//select[@name='country']");
    private By inputAddress1 = By.xpath("//input[@name='address_1']");
    private By inputAddress2 = By.xpath("//input[@name='address_2']");
    private By inputCity = By.xpath("//input[@name='city']");
    private By inputState = By.xpath("//input[@name='state']");
    private By inputZip = By.xpath("//input[@name='zipcode']");
    private By buttonSaveIf = By.xpath("//div[@class='card-body']//button");
    private By messEdit = By.xpath("//div[@class='toast-message']");

    private By messDel = By.xpath("//strong[1]");
    private By buttonConfirm = By.xpath("//button[@class='btn btn-light'][normalize-space()='Close']/following-sibling::button");
    private By buttonClose = By.xpath("//form[@id='delete_record']//div[@class='modal-footer']/button[text()='Close']");
    private By messConf = By.xpath("//div[@class='toast-message']");

    private final ExcelHelpers excelHelpers;

    public ManageClientsPage(){
        this.excelHelpers = new ExcelHelpers();
        this.excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
    }

    public ManageClientsPage verifyClientsPage(){
        softAssertContain(getURLPage(), subdir);
        softAssertEqual(getTitlePage(), title);
        softAssertEqual(getTextElement(subHeading), textSubHeading);
        endAssert();
        return this;
    }

    @Parameters({"row"})
    public ManageClientsPage addClient(int row){
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

    public ManageClientsPage addClients(Hashtable<String, String> data){
        clickElement(buttonAddNew);
        verifyEqual(getTextElement(subHeadAddNew), textHeadAddNew, "Heading not match");
        setText(inputFirstName, data.get("FIRSTNAME"));
        setText(inputLastName, data.get("LASTNAME"));
        setText(inputPassword, data.get("PASSWORD"));
        setText(inputContactNumber, data.get("CONTACT"));
        handleDropdown(buttonGender, dropdownGender, "text", data.get("GENDER").split(", "));
        setText(inputEmail, data.get("EMAIL"));
        setText(inputUsername, data.get("USERNAME"));
        uploadFileSendKey(inputAttachment, data.get("ATTACHMENT"));
        clickElement(buttonSaveAdd);
        return this;
    }

    @Parameters({"row"})
    public ManageClientsPage editClient(int row){
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
        verifyEqual(getTextElement(messEdit), textAlertEdit, "Not show correct alert");
        goToPreviousPage();
        return this;
    }

    public ManageClientsPage editClients(Hashtable<String, String> data){
        setText(inputSearch, data.get("FIRSTNAME"));
        sleep(2);
        verifyRecordAndPagination(1, data.get("FIRSTNAME"));
        clickElement(buttonDetail);
        sleep(2);
        handleDropdown(buttonStatus, dropdownStatus, "text", data.get("STATUS").split(", "));
        handleDropdown(buttonCountry, dropdownCountry, "text", data.get("COUNTRY").split(", "));
        setText(inputAddress1, data.get("ADDRESS"));
        setText(inputAddress2, data.get("ADDRESS2"));
        setText(inputCity, data.get("CITY"));
        setText(inputState, data.get("STATE"));
        setText(inputZip, data.get("ZIPCODE"));
        clickElement(buttonSaveIf);
        verifyEqual(getTextElement(messEdit), textAlertEdit, "Not show correct alert");
        goToPreviousPage();
        return this;
    }

    @Parameters({"row"})
    public ManageClientsPage searchClient(int row){
        goToPreviousPage();
        setText(inputSearch, excelHelpers.getCellData("FIRSTNAME", row));
        sleep(2);
        verifyRecordAndPagination(1, excelHelpers.getCellData("FIRSTNAME", row));
        return this;
    }

    public ManageClientsPage searchClients(Hashtable<String, String> data){
        clearSetText(inputSearch, data.get("FIRSTNAME"));
        sleep(2);
        verifyRecordAndPagination(1, data.get("FIRSTNAME"));
        return this;
    }

    @Parameters({"row"})
    public ManageClientsPage verifyDataClient(int row){
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

    public ManageClientsPage verifyDataClients(Hashtable<String, String> data){
        clickElement(buttonDetail);
        sleep(2);
        softAssertEqual(getAttributeElement(inputFirstName, "value"), data.get("FIRSTNAME"));
        softAssertEqual(getAttributeElement(inputLastName, "value"), data.get("LASTNAME"));
        softAssertEqual(getAttributeElement(inputEmail, "value"), data.get("EMAIL"));
        softAssertEqual(getAttributeElement(inputUsername, "value"), data.get("USERNAME"));
        softAssertEqual(getAttributeElement(inputContactNumber, "value"), data.get("CONTACT"));
        softAssertEqual(getFirstOptionSelected(dropdownGender), data.get("GENDER"));
        softAssertEqual(getFirstOptionSelected(dropdownCountry), data.get("GENDER"));
        softAssertEqual(getAttributeElement(inputAddress1, "value"), data.get("ADDRESS"));
        softAssertEqual(getAttributeElement(inputAddress2, "value"), data.get("ADDRESS2"));
        softAssertEqual(getAttributeElement(inputCity, "value"), data.get("CITY"));
        softAssertEqual(getAttributeElement(inputState, "value"), data.get("STATE"));
        softAssertEqual(getAttributeElement(inputZip, "value"), data.get("ZIPCODE"));
        return this;
    }

    public ManageClientsPage deleteClient(int row){
        searchClient(row);
//        clickElement(buttonDelete);
//        verifyEqual(getTextElement(messDel), textClose, "Alert not correct message");
//        clickElement(buttonClose);
        clickElement(buttonDelete);
        sleep(2);
        clickElement(buttonConfirm);
        verifyEqual(getTextElement(messConf), textConfirm, "Alert not correct message");
        searchClient(row);
        return this;
    }

    public ManageClientsPage deleteClients(Hashtable<String, String> data){
        searchClients(data);
        clickElement(buttonDelete);
        clickElement(buttonConfirm);
        verifyEqual(getTextElement(messConf), textConfirm, "Alert not correct message");
        searchClients(data);
        return this;
    }
    
}
