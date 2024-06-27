package TestCase;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.BeCognizant;
import TestBase.BaseClass;
import Utilities.Screenshot;



public class TC_001_UserDetailsTest extends BaseClass {
	
  @Test(priority=0)
  public void userVerification() throws InterruptedException, IOException, InvalidFormatException {
	  
	  BeCognizant b_cog=new BeCognizant(driver);
	  
	  String[] userDetails=new String[2];
	  userDetails= b_cog.getUserDetails();
	  
	  Assert.assertEquals(userDetails[0].contains("(Contractor)"),true," Not A Cognizant User");
	  
	//Call this method for write the data into Excel
	  b_cog.userDetailsExcelWrite("TC_001");
	  
	  Assert.assertEquals(userDetails[1].contains("@cognizant.com"), true," Not A Cognizant User");
	 
  }
}
