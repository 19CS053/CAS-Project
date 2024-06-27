package PageObjects;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelOutputUtility;
import Utilities.Screenshot;

public class BeCognizant extends BasePage {
	
	//Get the driver value
	public BeCognizant(WebDriver driver)
	{
		super(driver);
	}
	
	//Declaration part of Explicit wait
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	
	//Declaration part of JavascriptExecutor
	JavascriptExecutor jscript=(JavascriptExecutor)driver;	
	Actions clickAction=new Actions(driver);
	
	//Locators for Be.Cognizant page
	@FindBy(xpath="//div[@id='O365_UniversalMeContainer']")
	WebElement user_profile;
	
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement user_name;
	
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement user_email;
	
	//Actions for Be.Cognizant page
	public void setUser_profile() throws InterruptedException, IOException
	{
		clickAction.moveToElement(user_profile);//hover to user
		
		
		clickAction.click().perform();//click the user icon
		Thread.sleep(2000);
		
		clickAction.doubleClick().perform();
		Thread.sleep(2000);
		Screenshot.highlightElement(jscript, user_profile);
		Screenshot.captureScreenShot(driver,"UserDetails");//Call this method for take screenshot
        
	}
	
	//Use this method to get the User Name and email id
	public String[] getUserDetails() throws InterruptedException, IOException, InvalidFormatException
	{
		 setUser_profile();//call this method for perform user action
		 String[] userDetails=new String[2];
		 userDetails[0]=wait.until(ExpectedConditions.visibilityOf(user_name)).getText();
         
         System.out.println("User Name: "+userDetails[0]);
         
         userDetails[1]=wait.until(ExpectedConditions.visibilityOf(user_email)).getText();
         
         System.out.println("Email_Id: "+userDetails[1]);
         Thread.sleep(3000);
         
         return userDetails;
	}
	
	//This is for write the user details to Excel
	public void userDetailsExcelWrite(String sheetName) throws InvalidFormatException, IOException
	{
		ExcelOutputUtility.setExcelWrite(sheetName, 0, 0, "Username");
		ExcelOutputUtility.setExcelWrite(sheetName, 1, 0, "Mail");
		ExcelOutputUtility.setExcelWrite(sheetName, 0, 1, user_name.getText());
		ExcelOutputUtility.setExcelWrite(sheetName, 1, 1, user_email.getText());
	}

}
