package Utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterManager implements ITestListener{
	 
	//For UI of Report
	public ExtentSparkReporter sparkReporter;
	//For CommonProperities
	public ExtentReports report;
	//For Creating Test-Case-Entries in Report
	public ExtentTest test;
	//WebDriver driver;
 
	public void onStart(ITestContext context) {
		
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Output/ExtentReport/ExtentHtmlReport.html");//specify location of the report
		
		
		sparkReporter.config().setDocumentTitle("One Cognizant");
		sparkReporter.config().setReportName("Time Sheet");
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//Setting Properties
		report = new ExtentReports();
		report.attachReporter(sparkReporter);
		report.setSystemInfo("Application", "One Cognizant");
		report.setSystemInfo("Module", "Time Sheet");
		report.setSystemInfo("Operating System", System.getProperty("os.name"));
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		
 
	}
	public void onTestStart(ITestResult result) {
		System.out.println();
		System.out.println("->TEST-CASE STARTED");
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("->TEST-CASE SUCCESS!!");
		//Creates the Test-Case which is success
		test = report.createTest(result.getName());
		//Updates the Status of Test-Case
		test.log(Status.PASS, "Test-Case \"" + result.getName() + "\" is Passed");
		try
		{
			String imgPath=Screenshot.ScreenShotReport(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("->TEST-CASE FAILURE");
		//Creates the Test-Case which fails
		test = report.createTest(result.getName());
		//Updates the Status of Test-Case
		test.log(Status.FAIL, "Test-Case \"" + result.getName() + "\" is Failed");
		test.log(Status.FAIL, "Because it has " + result.getThrowable());
		try
		{
			String imgPath=Screenshot.ScreenShotReport(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void onTestSkipped(ITestResult result){
		System.out.println("->TEST-CASE SKIPPED");
		//Creates the Test-Case which skipped
		test = report.createTest(result.getName());
		test.log(Status.SKIP, "Test-Case \"" + result.getName() + "\" is Skipped");//Updates the Status of Test-Case
	}	
	public void onFinish(ITestContext context) {
		System.out.println();
		System.out.println("->TEST-SUITE FINISHED");
		//Writes the test-information in output-view
		report.flush();
	}
 
}