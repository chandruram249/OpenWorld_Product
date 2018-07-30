package TestReport;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Flow.ForgotPassword;
import Utility.ConfigFile;

public class Forgot_Password_Test {
	WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest log;
	WebDriverWait wait;
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) throws IOException {
		switch (browser.toLowerCase()) {
		case "chrome": {
			try {
				System.setProperty("webdriver.chrome.driver", ConfigFile.getInput("chromepath"));
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(false);
				driver = new ChromeDriver(options);

			} catch (Exception e) {
				throw new RuntimeException("Browser initialize error" + e);
			}
			break;
		}
		case "firefox": {
			try {
				driver = new FirefoxDriver();
			} catch (Exception e) {
				throw new RuntimeException("Browser initialize error" + e);
			}
			break;
		}
		}
		// new Login(driver, extent,log);
		driver.manage().window().maximize();
		driver.get(ConfigFile.getInput("serviceprovider_url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
	}

	@BeforeTest
	@Parameters({ "reportname"})
	public void beforeTest(String reportname) throws IOException {
		htmlReporter = new ExtentHtmlReporter("./TestReport/" + reportname + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("OpenWorld Report");
		htmlReporter.config().setReportName("Forgot_Password Test Report");
		htmlReporter.config().setTheme(Theme.DARK);
		// log = extent.createTest(scriptname);
	}
	@Test(priority=1)
	public void Test1() throws IOException
	{
		new ForgotPassword(driver, wait).serviceProvider_valid(new ConfigFile().getInput("forgotpasswordmailid"));
	}

}
