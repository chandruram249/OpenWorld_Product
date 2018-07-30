package Flow;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utility.ConfigFile;
import Utility.Operations;

public class ForgotPassword {
	WebDriver driver;	
	Boolean bool;
	WebDriverWait wait;
	public ForgotPassword(WebDriver drivers, WebDriverWait waits)
	{
		driver=drivers;
		wait=waits;
	}
	
	public void serviceProvider_valid(String checkmail) throws IOException
	{
		new Operations(driver).clickElement("xpath","//p[text()='Forgot Password?']");
		bool=false;
			bool=new Operations(driver).visibleElement("xpath","//div[@class='message']");
			Assert.assertTrue(bool,"Alert missing");
			String emailname=new Operations(driver).getText("xpath", "//div[@class='message']/p[2]/b");			
			Assert.assertEquals(emailname, new ConfigFile().getInput("forgotpasswordmailid"), "Email id not matched");
			new Operations(driver).clickElement("xpath", "//button[text()='Yes']");			
	}
	

}
