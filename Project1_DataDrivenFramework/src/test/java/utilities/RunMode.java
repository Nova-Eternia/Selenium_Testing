package utilities;

public class RunMode {

	public static boolean isTestRunnable(String testName, ExcelReader excel) {

	    String sheetName = "Test_Suite";

	    int rows = excel.getRowCount(sheetName);
//	    int columns = excel.getColumnCount(sheetName);

	    // Exclude header row
//	    Object[][] data = new Object[rows - 1][columns];

	    for (int row = 2; row <= rows; row++) {          // rows
	    	String testCase_ID = excel.getCellData(sheetName, "TestCase_ID", row);
	    	if (testName.equalsIgnoreCase(testCase_ID)) {
	    		String runMode = excel.getCellData(sheetName, "RunMode", row);
	    		if(runMode.equalsIgnoreCase("y")) {
	    			return true;
	    		}
	    		else {
	    			return false;
	    		}
	    	}
	    }
		return false;
	}
	
}
