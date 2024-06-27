package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelOutputUtility;
import Utilities.Screenshot;

public class TimeSheet extends BasePage{
	String Details;
	String[] dt =new String[4];
	
	//Get the driver value
	public TimeSheet(WebDriver driver)
	{
		super(driver);
	}
	
	//Declaration part of Explicit wait
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	
	//Declaration part of JavascriptExecutor
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	
	//Locators for TimeSheet
	@FindBy(xpath="//a[contains(@id,'CTS_TS_LAND_PER_DESCR')]")
	List<WebElement>  dates;
	
	@FindBy(xpath="//select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")
	WebElement select_element;
	
	@FindBy(xpath="//a[@id='CTS_TS_LAND_WRK_DATE$prompt']")
	WebElement Calendar_Icon;
	
	@FindBy(xpath="//a[@id='curdate']")
	WebElement currentDate_element;
	
	@FindBy(xpath="//a[@id='CTS_TS_LAND_PER_DESCR30$0']")
	WebElement CurrentDate_Details;
	
	@FindBy(xpath="//a[@id='CTS_TS_LAND_WRK_SEARCH']")
	WebElement Search_element;
	
	@FindBy(xpath="//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")
	WebElement selectStatus_element;
	
	@FindBy(xpath="//div[@class='ps_box-scrollarea-row']/div[2]/div[2]/div[2]/span")
	List<WebElement> status_listDetails;
	
	@FindBy(xpath="//div[@id='shortmsg']")
	WebElement alert_message;
	
	@FindBy(xpath="//a[@id='#ICOK']")
	WebElement alert_okButton;
	
	//Actions for 3Weeks Details
	public String[] getDates() throws InterruptedException, IOException, InvalidFormatException
	{
		int j=0;
		Thread.sleep(5000);
		Screenshot.captureScreenShot(driver,"LastThreeWeeks");//Call this method for take screenshot
		for(int i=1;i<=3;i++)
		{
			WebElement date=dates.get(i);
			
			dt[j]=date.getText();
			System.out.println("Week"+i+" :"+dt[j]);
			j++;
		}       
		return dt;
	}
	
	//Write Three Weeks details to this method
	public void ThreeWeeksDetailsExcelWrite(String sheetName) throws InvalidFormatException, IOException
	{
		ExcelOutputUtility.setExcelWrite(sheetName, 0, 0, "Week Date");
		
		for(int i=0;i<3;i++)
		{
		ExcelOutputUtility.setExcelWrite(sheetName, i+1, 0, dt[i]);
		}
	}
	
	
	//Actions for current week
	public String getCurrentDate() throws InterruptedException, IOException, InvalidFormatException
	{
	Select sl=new Select(select_element);
	
	Screenshot.highlightElement(jscript,select_element);
	sl.selectByVisibleText("Date");
	Thread.sleep(2000);
	
	Calendar_Icon.click();
	Thread.sleep(2000);
	Screenshot.captureScreenShot(driver,"CurrentDate");//Call this method for take screenshot
	
	Screenshot.highlightElement(jscript,currentDate_element);
	currentDate_element.click();
	Thread.sleep(2000);
	
	Screenshot.highlightElement(jscript,Search_element);
	Search_element.click();
	Thread.sleep(3000);
	Screenshot.captureScreenShot(driver,"WeekDetails");//Call this method for take screenshot
	
	WebElement today=wait.until(ExpectedConditions.visibilityOf(CurrentDate_Details));
	
	Details=today.getText();
	System.out.println("======================\n");
	System.out.println("Current Week: "+Details);
	
	return Details;
	}
	
	//Use this method to write the Current week details in Excel
	public void CurrentWeekDetailsExcelWrite(String sheetName) throws InvalidFormatException, IOException, InterruptedException
	{
		ExcelOutputUtility.setExcelWrite(sheetName, 0, 0, "Current Week Date");
		
		
		ExcelOutputUtility.setExcelWrite(sheetName, 1, 0, Details);
	}
	
	//Actions for Status of list elements(Approved and Pending)
	public boolean getStatusOfList(String expectedStatus,String txt_status) throws InterruptedException, IOException, InvalidFormatException
	{
		Select search=new Select(select_element);
		Screenshot.highlightElement(jscript,select_element);
		search.selectByVisibleText("Status");
		Thread.sleep(2000);
		
		Select  status1=new Select(wait.until(ExpectedConditions.visibilityOf(selectStatus_element)));
		Screenshot.highlightElement(jscript,selectStatus_element);
		status1.selectByVisibleText(txt_status);
		
		WebElement search_click=wait.until(ExpectedConditions.visibilityOf(Search_element));
		Screenshot.highlightElement(jscript,Search_element);
		search_click.click();
		Thread.sleep(2000);
		
		
		boolean result=false;

		for(int i=0;i<status_listDetails.size();i++)
		{
			Screenshot.highlightElement(jscript,status_listDetails.get(i));
			String actualStatus=status_listDetails.get(i).getText();
			
			if(actualStatus.equals(expectedStatus))
			result=true;
			
			else
			break;
			
		}
		return result;
		
	}
	
	
	//Actions for status of single Element
	public void getStatus(String txt_status) throws InterruptedException, IOException
	{
		Select search2=new Select(wait.until(ExpectedConditions.visibilityOf(select_element)));
		Screenshot.highlightElement(jscript,select_element);
		search2.selectByVisibleText("Status");
		Thread.sleep(3000);
		
		Select  status2=new Select(wait.until(ExpectedConditions.visibilityOf(selectStatus_element)));
		Screenshot.highlightElement(jscript,selectStatus_element);
		status2.selectByVisibleText(txt_status);
		
		WebElement search_click=wait.until(ExpectedConditions.visibilityOf(Search_element));
		Screenshot.highlightElement(jscript,Search_element);
		search_click.click();
		
		Screenshot.highlightElement(jscript,alert_message);
		String alertMsg1=alert_message.getText();
		System.out.println(alertMsg1);
		Thread.sleep(2000);
		
		Screenshot.highlightElement(jscript,alert_okButton);
		alert_okButton.click();
	}
	

}
