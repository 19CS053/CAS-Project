package Utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.io.Files;

import PageObjects.BasePage;
import TestBase.BaseClass;

public class Screenshot{
	
	JavascriptExecutor jExecutor;
	static String filepath;
	
	//This method for taking Screenshot
	public static void captureScreenShot(WebDriver driver,String SName) throws IOException
	{
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);//Screenshot of Dom;
		File destinationFile = new File("./Output/ScreenShot/"+SName+"img"+".png");//Proxy file created to copy the screenshot;
		Files.copy(sourceFile, destinationFile);//copying the scrrenshot to the proxy;
		//count++;
		filepath=destinationFile.getAbsolutePath();
	}
	
	//This method for add Screenshot to report
	public static  String  ScreenShotReport(String fileName) 
	{
		return filepath;
	}
	
	//This method for highlight the element
	public static void highlightElement(JavascriptExecutor jExecutor, WebElement element) {
		jExecutor.executeScript("arguments[0].style.border='4px solid yellow'", element);
	}

}
