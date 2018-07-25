package Flow;

import java.io.IOException;
import java.rmi.server.Operation;
import java.util.concurrent.TimeUnit;
import Commons.*;
import javax.management.openmbean.OpenDataException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utility.*;
public class Login {
	private WebDriver driver;
	private WebDriverWait wait;
	private Boolean bool;
	public static String failedstatus;
	//public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest log;
	SoftAssert softassert=new SoftAssert();
	public Login(WebDriver initdriver,WebDriverWait waits,ExtentReports extents,ExtentTest logs) throws IOException
	{
		this.driver=initdriver;
		this.extent=extents;
		this.log=logs;
		this.wait=waits;
	}
	public void validLogin(String username, String password, String firstname)
	{
		new Operations(driver).sendKeys("xpath", "//input[@type='text']", username);
		new Operations(driver).sendKeys("xpath", "//input[@type='password']", password);
		new Operations(driver).clickElement("xpath", "//button[@type='submit']");
		failedstatus= "Not a Home Page";
		Assert.assertEquals(driver.getCurrentUrl(), "http://192.168.2.17:5000/#/",failedstatus );
		//System.out.println("Logged in Successfully");
		log.pass("Logged in Successfully");
		failedstatus= "Not Logged in to valid user";
		Assert.assertEquals(new Operations(driver).getText("xpath","//i[@class='fa fa-user']/following-sibling::span"),firstname, failedstatus);
		//System.out.println("Logged in to valid user");
		log.pass("Logged in to valid user");
	}
    public void invalidLogin(String invalidusername, String invalidpassword, String alert) throws IOException
    {
    	new Operations(driver).sendKeys("xpath", "//input[@type='text']", invalidusername);
		new Operations(driver).sendKeys("xpath", "//input[@type='password']", invalidusername);
		new Operations(driver).clickElement("xpath", "//button[@type='submit']");
		bool=false;
		try
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='message']")));
			bool=true;
		}
		catch(TimeoutException e)
		{
			
			bool=false;
		}
		try
		{
		softassert.assertTrue(bool,"Alert missing");
		//System.out.println("Showing alert");
		log.pass("Showing alert");
		softassert.assertEquals(new Operations(driver).getText("xpath","//div[@class='message']"), alert, "Aert message was not correct");
		//System.out.println("Showing Proper alert message: "+alert);
		log.pass("Showing Proper alert message: "+alert);
		new Operations(driver).clickElement("xpath", "//button[text()='OK']");
		//System.out.println("Click Ok");
		log.pass("Clicked Ok");
		softassert.assertAll();
		}catch(NoSuchElementException e)
		{
			//System.out.println("Alert not displayed so can't click ok button"+e.getMessage());
			log.fail("Alert not displayed so can't click ok button"+e.getMessage());
		}
		//Assert.assertEquals(new Operations(driver).getText("xpath","//i[@class='fa fa-user']/following-sibling::span"),new ConfigFile().getInput("user_firstname"), "Not Logged in to valid user");
		//System.out.println("Logged in to valid user");
    }
    public void logout()
    {
    	new Operations(driver).clickElement("xpath", "//i[@class='fa fa-user']/following-sibling::span");
    	new Operations(driver).clickElement("xpath", "//a[text()='Sign out']");
    	bool=false;
    	try
    	{
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
    	bool=true;
    	//System.out.println("User logged out successfully");
    	log.pass("User logged out successfully");
    	}
    	catch(TimeoutException e)
    	{
    		bool=false;
    	}
    	Assert.assertTrue(bool, "Issue in Sign Out");
    }
}
