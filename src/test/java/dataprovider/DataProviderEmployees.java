package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderEmployees {

    @DataProvider(name = "data_add_employees", parallel = false)
    public Object[][] dataClients(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Employees", 1, 2);
        return data;
    }
}
