package TestCase;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.TimeSheet;
import Utilities.ExcelInput;
import Utilities.Screenshot;

public class TC_004_ValidateAllSearchResultsTest extends TC_003_ValidateCurrentDateDetailsTest{
	
	static int count=1;
  @Test(priority=3,dataProvider="testData1")
  public void verifyApprovedStatusResult(String Status) throws InterruptedException, IOException, InvalidFormatException {
	  TimeSheet tsheet=new TimeSheet(driver);
	  
	  
	  //call this method for Approved status
	  boolean result=tsheet.getStatusOfList("Approved",Status);
	  Screenshot.captureScreenShot(driver,"ApprovedStatus");//Call this method for take screenshot
	  if(result==true)
	  {
		  String msg="Status are valid";
		  System.out.println(msg);
		  Assert.assertTrue(true);
	  }
	  
	  else
	  {
		  String msg="Status are not valid";
		  System.out.println(msg);
		  Assert.assertTrue(false);
	  }
	  
	
  }
  
  @Test(priority=4,dataProvider="testData2")
  public void verifyPendingStatusResult(String Status) throws InterruptedException, IOException, InvalidFormatException {
	  TimeSheet tsheet=new TimeSheet(driver);
	  
	  
	  //call this method for pending status
	  boolean result=tsheet.getStatusOfList("Pending",Status);
	  Thread.sleep(2000);
	  Screenshot.captureScreenShot(driver,"PendingStatus");//Call this method for take screenshot
	  if(result==true)
	  {
		  String msg="Status are valid";
		  System.out.println(msg);
		  Assert.assertTrue(true);
	  }
	  
	  else
	  {
		  String msg="Status are not valid";
		  System.out.println(msg);
		  Assert.assertTrue(false);
	  }
	  
	
  }
  
  @Test(priority=5,dataProvider="testData3")
  public void verifySubmittedforApprovalStatusResult(String Status) throws InterruptedException, IOException, InvalidFormatException {
	  TimeSheet tsheet=new TimeSheet(driver);
	  
	  
	  //call this method for pending status
	  boolean result=tsheet.getStatusOfList("Submitted for Approval",Status);
	  Thread.sleep(2000);
	  Screenshot.captureScreenShot(driver,"SubmittedforApprovalStatus");//Call this method for take screenshot
	  if(result==true)
	  {
		  String msg="Status are valid";
		  System.out.println(msg);
		  Assert.assertTrue(true);
	  }
	  
	  else
	  {
		  String msg="Status are not valid";
		  System.out.println(msg);
		  Assert.assertTrue(false);
	  }
	  
	
  }
  
  @Test(priority=6,dataProvider="testData4")
  public void verifyAlertSearchStatus(String Status) throws InterruptedException, IOException
  {
	  TimeSheet tsheet=new TimeSheet(driver);
	  
	  //Use this method for Search status
	  tsheet.getStatus(Status);
	  Thread.sleep(3000);
	  String value=String.valueOf(count);
	  Screenshot.captureScreenShot(driver,"AllSearchStatus"+value);//Call this method for take screenshot
	  count++;
  }
  
  @DataProvider(name="testData1",indices={0})
  public String[] ExcelData() throws IOException
  {
	  String[] StatusInputs=ExcelInput.getExcelData();
	  
	  return StatusInputs;
  }
  @DataProvider(name="testData2",indices={3})
  public String[] ExcelData1() throws IOException
  {
	  String[] StatusInputs=ExcelInput.getExcelData();
	  
	  return StatusInputs;
  }
  
  @DataProvider(name="testData3",indices={6})
  public String[] ExcelData2() throws IOException
  {
	  String[] StatusInputs=ExcelInput.getExcelData();
	  
	  return StatusInputs;
  }
  
  @DataProvider(name="testData4",indices= {1,2,4,5})
  public String[] ExcelDetails() throws IOException
  {
	  String[] StatusInputs=ExcelInput.getExcelData();
	  
	  return StatusInputs;
  }
  
}
