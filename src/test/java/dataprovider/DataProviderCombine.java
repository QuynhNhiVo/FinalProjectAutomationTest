package dataprovider;

import constants.ConfigData;
import helpers.ExcelHelpers;
import helpers.SystemHelpers;
import org.testng.annotations.DataProvider;
import utils.LogUtils;

public class DataProviderCombine {

    @DataProvider(name = "data_combine_CP", parallel = false)
    public Object[][] dataCombineCP(){
        ExcelHelpers excelHelpers = new ExcelHelpers();
        LogUtils.info("Open file excel: " + SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL);
        Object[][] clients = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Clients", 5, 7);
        Object[][] projects = excelHelpers.getDataHashTable(SystemHelpers.getCurrentDir() + ConfigData.LOGIN_HRM_EXCEL, "Projects", 5, 7);
        int dataLength = Math.min(clients.length, projects.length);// sync lengths
            //Ensures both data sets are the same length by using the smaller one.
            //This avoids IndexOutOfBoundsException if one sheet has more rows than the other.
        Object[][] data = new Object[dataLength][2];
            //Initializes a new 2D array with two columns per test case:
                //[i][0] will hold client data.
                //[i][1] will hold project data.
        for (int i = 0; i < dataLength; i++) {
            data[i][0] = "Clients" + clients[i][0];  // First param: client data
            data[i][1] = "Projects" + projects[i][0]; // Second param: project data
        }
        return data;
    }
}
/**
 * DataProvider that combines data from two Excel sheets: "Clients" and "Projects".
 * <p>
 * - Reads data from row 5 to row 7 (inclusive) from each sheet.
 * - Each row is converted into a Hashtable<String, String>.
 * - Data is combined row-by-row from both sheets:
 *   + data[0][0] = Client A   | data[0][1] = Project A
 *   + data[1][0] = Client B   | data[1][1] = Project B
 *   + ...
 * <p>
 * ❗ If the two sheets have different numbers of rows, only the minimum number of rows will be used
 * to avoid IndexOutOfBoundsException.
 *
 * @return Object[][] containing pairs of data: [Client, Project] to be injected into the test method
 */
