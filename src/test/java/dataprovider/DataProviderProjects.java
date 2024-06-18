package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import org.testng.collections.Lists;
import utils.LogUtils;

import java.util.Arrays;
import java.util.List;

public class DataProviderProjects {

    @DataProvider(name = "data_add_projects" , parallel = true)
    public Object[][] dataProjects(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Projects", 3, 5);
        return data;
    }

    @DataProvider(name = "data_add_tasks" , parallel = false)
    public Object[][] dataTasks(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] data = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Tasks", 5, 7);
        return data;
    }

}
