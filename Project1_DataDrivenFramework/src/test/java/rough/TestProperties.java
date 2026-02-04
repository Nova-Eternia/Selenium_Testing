package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[]args) throws IOException
	{
		Properties config = new Properties();
		Properties OR = new Properties();
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//propreties//config.properties");
		config.load(file);
		System.out.println(System.getProperty("user.dir"));
		System.out.println(config.getProperty("browser"));
		
		file = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//propreties//OR.properties");
		OR.load(file);
//		driver.findElement(By.cssSelecter(Or.getProperty("bmlBtn"))).click();
		System.out.println(OR.getProperty("bmlBtn"));
		
	}
}
