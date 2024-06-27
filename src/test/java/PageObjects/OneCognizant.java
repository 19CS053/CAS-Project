package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.Screenshot;

public class OneCognizant extends BasePage{
	
	//Get the driver value
	public OneCognizant(WebDriver driver)
	{
		super(driver);
	}
	
	Actions action=new Actions(driver);
	
	//Declaration part of Explicit wait
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	//Declaration part of JavascriptExecutor
	JavascriptExecutor jscript=(JavascriptExecutor)driver;
	
	//Locators for OneCognizant 
	@FindBy(xpath="//*[@id=\"spPageCanvasContent\"]/div/div/div/div/div/div/div[3]/div/div/div")
	WebElement scroll_oneCognizant;
	
	@FindBy(xpath="//div[text()='OneCognizant']")
	WebElement oneCognizant_click;
	
	@FindBy(xpath="//ul[@class='searchBasedTopBar']//li")
	WebElement Search_icon;
	
	@FindBy(xpath="//input[@id='oneCSearchTop']")
	WebElement txt_Search;
	
	@FindBy(xpath="//div[@class='quickActionsResultText' and text()='View Timesheet']")
	WebElement timeSheet_click;
	
	
	//Actions for OneCognizant page
	public void setScrollPage() throws InterruptedException
	{
		Thread.sleep(5000);
		action.moveToElement(scroll_oneCognizant).perform();//Scroll down till OneCognizant
		
		
      
	}
	
	//Click OneCognizant for this method 
	public void setOnecognizant() throws InterruptedException
	{
		setScrollPage();//call the method for scroll down
		Screenshot.highlightElement(jscript,oneCognizant_click);
		oneCognizant_click.click();
		Set<String> windows = driver.getWindowHandles();
		List<String> windowsList = new ArrayList<String>(windows);
		driver.switchTo().window(windowsList.get(1));
		
	}
	
	//Search "TimeSheet" text in this method
	public void setSearchTimesheet() throws InterruptedException, IOException
	{
		setOnecognizant();//call this method for click oneCognizant
		Thread.sleep(3000);
		
		driver.manage().window().minimize();
        WebElement Search_textBox=wait.until(ExpectedConditions.visibilityOf(Search_icon));
        Screenshot.highlightElement(jscript, Search_icon);
        Search_textBox.click();
        
		WebElement search_element=wait.until(ExpectedConditions.visibilityOf(txt_Search));
		Screenshot.highlightElement(jscript, txt_Search);
		search_element.sendKeys("Timesheet",Keys.ENTER);
			
		Screenshot.captureScreenShot(driver,"TimesheetSearch");
		Thread.sleep(3000);
		driver.manage().window().maximize();
		
		WebElement timeSheet_element=wait.until(ExpectedConditions.visibilityOf(timeSheet_click));
		Screenshot.highlightElement(jscript, timeSheet_click);
		timeSheet_element.click();
		
		Thread.sleep(3000);
        Set<String> windows1 = driver.getWindowHandles();
		List<String> windowsList2 = new ArrayList<String>(windows1);
		Thread.sleep(2000);
		driver.switchTo().window(windowsList2.get(2));//switching to third timesheet window
		
	}
	


}
