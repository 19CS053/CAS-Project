package TestCase;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import PageObjects.OneCognizant;
import PageObjects.TimeSheet;

public class TC_002_Validate3WeeksDetailsTest extends TC_001_UserDetailsTest {
	
  @Test(priority=1)
  public void  ThreeWeeksVerification() throws InterruptedException, ParseException, IOException, InvalidFormatException{
	  OneCognizant onec=new OneCognizant(driver);
	  onec.setSearchTimesheet();
	  
	  TimeSheet t_Sheet=new TimeSheet(driver);
	  String[] dateDetails=t_Sheet.getDates();
	  
	  SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	  
	 //Call this method for write the data into Excel
	  t_Sheet.ThreeWeeksDetailsExcelWrite("TC_002");
      
      Thread.sleep(5000);
      for (int i = 0; i <= 2; i++) {
          String[] fulldt = dateDetails[i].split(" To ");
          String stdt = fulldt[0];
          String enddt = fulldt[1];
          
          Date startDate = (Date)df.parse(stdt);
          Date endDate = (Date)df.parse(enddt);
          
          Calendar startCalendar = Calendar.getInstance();
			startCalendar.setTime(startDate);
			System.out.println("======================\n");
			System.out.println("Validate last 3 Weeks Dates");
			
			//Check if the start Date is Saturday
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
			{
				System.out.println("Starting date is Saturday");
			}
			else
			{
				System.out.println("Starting date is not a Saturday");
			}
			
			//Check if the end date is Friday
			Calendar endCalendar=Calendar.getInstance();
			endCalendar.setTime(endDate);
			if(endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				System.out.println("Ending date is Friday");
			}
			else
			{
				System.out.println("Ending date is not a Friday");
			}
			
			//Check if the Start and End date both are matched
			if(startCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY && endCalendar.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
			{
				String msg="Both dates are matched";
				System.out.println(msg);
				
			}
			else
			{
				String msg="Both dates are misMatched";
				System.out.println(msg);
			}

          
      }
  }
}
