package OpenWorld.OpenWorld;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class Fluentwait {
	static WebDriver driver;
	public static String xpath;
	public static void main(String ar[])
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.17:5000");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demoemp");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		fw("//span[text()='Report Supervision']");
		driver.findElement(By.xpath(xpath)).click();
	}
	@SuppressWarnings("deprecation")
	public static void fw(String xpaths)
	{
		xpath=xpaths;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			        .withTimeout(20, TimeUnit.SECONDS)
			        .pollingEvery(5, TimeUnit.SECONDS)
			        .ignoring(NoSuchElementException.class,StaleElementReferenceException.class);
		     wait.until(new Function<WebDriver, WebElement>() {
			 public WebElement apply(WebDriver driver) {
				 return driver.findElement(By.xpath(xpath));
			  }
			 });
		//driver.findElement(By.xpath("//span[text()='Report Supervision']")).click();
	}
	}

