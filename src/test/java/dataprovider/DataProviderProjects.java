package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderProjects {

    @DataProvider(name = "data_add_projects" , parallel = true)
    public Object[][] dataProjects(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Projects", 3, 4);
        return data;
    }
}
