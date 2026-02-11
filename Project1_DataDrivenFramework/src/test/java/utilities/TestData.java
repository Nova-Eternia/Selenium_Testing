package utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import base.TestBase;

public class TestData extends TestBase {
	
	@DataProvider(name = "commonData")
	public Object[][] getData(Method m) {

	    String sheetName = m.getName();

	    int rows = excel.getRowCount(sheetName);
	    int columns = excel.getColumnCount(sheetName);

	    // Exclude header row
	    Object[][] data = new Object[rows - 1][columns];

	    for (int i = 2; i <= rows; i++) {          // rows
	        for (int j = 0; j < columns; j++) {    // columns
	            data[i - 2][j] = excel.getCellData(sheetName, j, i);
	        }
	    }
	    return data;
	}

}
