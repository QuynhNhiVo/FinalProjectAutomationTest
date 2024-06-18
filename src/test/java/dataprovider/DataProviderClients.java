package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.LogUtils;

public class DataProviderClients {

    @DataProvider(name = "data_add_clients", parallel = false)
    public Object[][] dataClients(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Clients", 8, 9);
        return data;
    }

    @DataProvider(name = "data_delete_clients", parallel = true)
    public Object[][] dataDelClients(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Clients", 4, 5);
        return data;
    }
}
