package board.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyUtil {
	
	public static String getCurrentDate(String dateFormat) {
		//static이 붙어있으면 MyUtil util = new MyUtil();를 할 필요가 없음.
		// => MyUtil.getCurrentDate() 가 가능함!!!
		
		LocalDate now = LocalDate.now();
		//포멧적용하기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		String formatedNow = now.format(formatter);
		
		return formatedNow;
	}
}
