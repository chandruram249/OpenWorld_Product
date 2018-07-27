package TestReport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Commons.Message;
import Commons.Screenshot;
import Utility.Operations;
import Utility.ConfigFile;
import Flow.Login;
public class Login_Test {
	WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest log;
	WebDriverWait wait;
	/*@BeforeSuite
	@Parameters({"reportname","scriptname"})
	public void beforeSuite(String reportname,String scriptname) throws IOException {
		
			
			//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./reportname.html");
			
			//ExtentReports extent1 = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        //extent1.attachReporter(htmlReporter1);
	        log = extent.createTest(scriptname);
		
	}*/
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) throws IOException
	{
		switch(browser.toLowerCase())
		{
		case "chrome":
		{
			try
			{
				System.setProperty("webdriver.chrome.driver", ConfigFile.getInput("chromepath"));
				ChromeOptions options=new ChromeOptions();
				options.setHeadless(false);
				driver=new ChromeDriver(options);
				
			}
			catch(Exception e)
			{
				throw new RuntimeException("Browser initialize error"+e);
			}
			break;
		}
		case "firefox":
		{
			try
			{
				driver=new FirefoxDriver();
			}
			catch(Exception e)
			{
				throw new RuntimeException("Browser initialize error"+e);
			}
			break;
		}
		}
		//new Login(driver, extent,log);
		driver.manage().window().maximize();
		driver.get(ConfigFile.getInput("serviceprovider_url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait=new WebDriverWait(driver,20);
	}
	@BeforeTest
	@Parameters({"reportname","scriptname"})
	public void beforeTest(String reportname, String scriptname) throws IOException
	{
		htmlReporter = new ExtentHtmlReporter("./TestReport/"+reportname+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("OpenWorld");
		htmlReporter.config().setReportName("Login_Page Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		//log = extent.createTest(scriptname);
	}
	
	/*@DataProvider
	public Object[] name(ITestContext context)
	{
		String param1= context.getCurrentXmlTest().getLocalParameters().get("reportname");
		String param2= context.getCurrentXmlTest().getLocalParameters().get("scriptname");
		Object[] obj=new Object[1];
		obj[0]=param1;
		obj[1]=param1;
		return obj;
	}*/
	@Test(priority=1, testName="Verify Login Page")
	public void Test1() throws IOException
	{
		log = extent.createTest("Verify Login Page");
		Message.failedstatus="Login page not opened";
		Assert.assertEquals(new Operations(driver).getSize("xpath", "//button[@type='submit']"), 1,Message.failedstatus);
		//System.out.println("Open login page");
		log.pass("Open login page");		
	}
	@Test(testName="Verify valid login credentials", dependsOnMethods="Test1", priority=2, enabled=true)
	public void Test2() throws IOException
	{
		log = extent.createTest("Verify valid login credentials");
		new Login(driver,wait,extent,log).validLogin(ConfigFile.getInput("sp_username"), ConfigFile.getInput("sp_password"), ConfigFile.getInput("user_firstname"));
		new Login(driver,wait,extent,log).logout();
	}
	@Test(testName="Verify invalid login credentials", dependsOnMethods="Test1", priority=3)
	public void Test3() throws IOException
	{
		log = extent.createTest("Verify invalid login credentials");
		new Login(driver,wait,extent,log).invalidLogin(ConfigFile.getInput("invalid_username"), ConfigFile.getInput("invalid_password"), ConfigFile.getInput("alertcontent1"));
	}
	@AfterMethod
	public void afterMethod(ITestResult testresult) throws IOException, InterruptedException
	{
		if(testresult.getStatus()==ITestResult.FAILURE)
		{
			new Screenshot(driver,testresult.getName());
			log.fail(Message.failedstatus+" @"+testresult.getName(), MediaEntityBuilder.createScreenCaptureFromPath(new ConfigFile().getInput("screenshot_directory")+testresult.getName()+".png").build());
		}
		driver.quit();
	}
	@AfterTest
	public void afterTest()
	{
		extent.flush();
	}


}
