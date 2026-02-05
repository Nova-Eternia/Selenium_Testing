package rough;

import java.util.Date;

public class TestTimeStamp {
	
	public static void main(String[]arg) {
		Date date = new Date();
		String ScreenshotName = date.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		System.out.println(date);
		System.out.println(ScreenshotName);

	}
}
