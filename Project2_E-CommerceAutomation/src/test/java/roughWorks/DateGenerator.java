package roughWorks;

import java.util.Date;

public class DateGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		System.out.println(date);
		String newDate=date.toString().replaceAll(" ", "").replaceAll(":", "")+"@gmail.com";
		String newDate2=date.toString().replaceAll("\\s", "").replaceAll("\\:", "")+"@gmail.com";

		System.out.println(newDate);
		System.out.println(newDate2);

	}

}
