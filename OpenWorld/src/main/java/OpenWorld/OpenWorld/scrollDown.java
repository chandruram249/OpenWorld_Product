package OpenWorld.OpenWorld;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class scrollDown {
	public static void main(String a[]) {
		// System.setProperty("webdriver.chrome.driver","./Driver/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.seleniumeasy.com/test/table-data-download-demo.html");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement e = driver.findElement(By.xpath("//input[@type='search']"));
		// js.executeScript("window.scrollBy(0,1000)");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// js.executeScript("arguments[0].scrollIntoView();", e);
	}

}
