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
    private By buttonAttachment = By.xpath("//label[normalize-space()='Choose file...']");

    private By buttonStatus= By.xpath("//span[@role='presentation']");
    private By dropdownStatus = By.xpath("//select[@name='gender']");
    private By buttonCountry = By.xpath("//span[@role='presentation']");
    private By dropdownCountry = By.xpath("//select[@name='gender']");
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

    public ManageClientsPage addClient(int n){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        clickElement(buttonAddNew);
        verifyEqual(getTextElement(subHeadAddNew), textHeadAddNew, "Heading not match");
        setText(inputFirstName, excelHelpers.getCellData("FIRSTNAME", 2));
        setText(inputLastName, excelHelpers.getCellData("LASTNAME", 2));
        setText(inputPassword, excelHelpers.getCellData("PASSWORD", 2));
        setText(inputContactNumber, excelHelpers.getCellData("CONTACT", 2));
        handleDropdown(buttonGender, dropdownGender, "text", excelHelpers.getCellData("GENDER", 2).split(", "));
        setText(inputEmail, excelHelpers.getCellData("EMAIL", 2));
        setText(inputUsername, excelHelpers.getCellData("USERNAME", 2));
        uploadFileSendKey(inputAttachment, excelHelpers.getCellData("ATTACHMENT", 2));
        clickElement(buttonSaveIf);
        return this;
    }

    public ManageClientsPage verifySearchClient(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.LOGIN_HRM_EXCEL, "CLients");
        setText(inputSearch, excelHelpers.getCellData("FIRSTNAME", 2));

        return this;
    }
}
