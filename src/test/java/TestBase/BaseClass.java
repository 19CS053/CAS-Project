package TestBase;


import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utilities.PropertiesUtiliy;



public class BaseClass 
{
	protected WebDriver driver;
	
	@BeforeClass
	@Parameters("Browser")
	public void beforeTest(String br) throws IOException 
	{
		if(br.equals("Chrome"))
			driver = new ChromeDriver();
		
		else if(br.equals("Edge")) 
			driver = new EdgeDriver();
		
		System.out.println("Browser Lanched");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(PropertiesUtiliy.getURL());
		
		
	}
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
}
