package TestCase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import PageObjects.TimeSheet;

public class TC_003_ValidateCurrentDateDetailsTest extends TC_002_Validate3WeeksDetailsTest{
	
  @Test(priority=2)
  public void verifyCurrentDateDetails() throws ParseException, InterruptedException, IOException, InvalidFormatException {
	 TimeSheet ts=new TimeSheet(driver);
		
	 	String Details=ts.getCurrentDate();
		String[] currentDate=Details.split(" To ");
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		Date startCurrentDate=(Date)df.parse(currentDate[0]);
		Date endCurrentDate=(Date)df.parse(currentDate[1]);
		
		Calendar startCalendar1 = Calendar.getInstance();
		startCalendar1.setTime(startCurrentDate);
		
		//Call this method for write the data into Excel
		ts.CurrentWeekDetailsExcelWrite("TC_003");
		
		System.out.println("======================\n");
		System.out.println("Validate Current Week Dates");
		
		//Check if the current week start date correct or not
		if(startCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
		{
			System.out.println("Current week start day is matched");
		}
		else
		{
			System.out.println("Current week start day is misMatched");
		}
		
		
		//Check if the current week end date correct or not
		Calendar endCalendar1 = Calendar.getInstance();
		endCalendar1.setTime(endCurrentDate);
		
		if(endCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		{
			System.out.println("Current week end day is matched");
		}
		else
		{
			System.out.println("Current week end day is misMatched");
		}
		
		//Check if the current week start and end date both are matched or not
		if(startCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && endCalendar1.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		{
			String msg="Current Week Both days are matched";
			System.out.println(msg);
		}
		else
		{
			String msg="Current Week Both days are not matched";
			System.out.println(msg);
		}
		Thread.sleep(3000);
  }
}
