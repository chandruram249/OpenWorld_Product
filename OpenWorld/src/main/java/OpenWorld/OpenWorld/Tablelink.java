package OpenWorld.OpenWorld;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tablelink {
	static int count=0;
	static WebDriver driver;
	public static void main(String ar[]) throws InterruptedException
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.17:5000");
		//linkTable();
		//uploadDoc();
		sample();
		/*JavascriptExecutor js=(JavascriptExecutor)driver; 
		js.executeScript("document.getElementByXPath(\"//input[@type='text']\").value=lalith");
		js.executeScript("document.getElementByXPath(\"//input[@type='password']\").value=Pass@123");*/
		
	}
	public static void linkTable()
	{
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("lalith");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='Report Supervision']")).click();
		//driver.findElement(By.xpath("//td[@class=' interimReports']/a")).click();
		//driver.findElement(By.xpath("//tbody/tr[3]/td[11]/a")).click();
		//driver.findElement(By.linkText("Interim1")).click();
		List<WebElement> alllink=new LinkedList<WebElement>();
		alllink=driver.findElements(By.tagName("a"));
		int count1 = 0;
		for(WebElement link:alllink)
		{
			if(link.getText().equals("Interim1"))
			{
				count1++;
			}
		}
		System.out.println(count1+" "+alllink.size());
		
	}
	public static void uploadDoc() throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demoemp");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='Case Registration']")).click();
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()=' Register New Case']")).click();
		driver.findElement(By.xpath(".//*[@id='client_chzn']/a/div/b")).click();
		driver.findElement(By.xpath(".//*[@id='client_chzn']/div/div/input")).sendKeys("ram new client",Keys.ENTER);
		driver.findElement(By.xpath("//button[text()='Case Document(s)']")).click();
		driver.findElement(By.xpath("//span[text()='Upload Document(s)']/following-sibling::input")).click();
		uploadFile("/home/kadamba/Desktop/Live_Attachments/ID.jpg");
	}
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	public static void uploadFile(String fileLocation) {
        try {
        	Thread.sleep(1500);
        	//Setting clipboard with file location
        	//System.out.println(fileLocation);
        	setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	public static void sample() throws InterruptedException {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demoemp");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Pass@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//span[text()='Verification']")).click();
		driver.findElement(By.xpath("//tr[@class='even']/td[1]")).click();
		Thread.sleep(2000);
		String gettext=driver.findElement(By.xpath("//section[@class='content-header']/h1")).getText();
		System.out.println(gettext);
	}

}
