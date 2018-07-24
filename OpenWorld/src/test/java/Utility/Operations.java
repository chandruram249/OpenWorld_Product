package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Operations {
	private WebDriver driver;
	private WebDriverWait wait;
	public Operations(WebDriver initdriver)
	{
		this.driver=initdriver;
		this.wait=new WebDriverWait(initdriver, 10);
	}
	
	public void clickElement(String locator,String element)
	{
		if(locator.equalsIgnoreCase("id"))
			driver.findElement(By.id(element)).click();
		if(locator.equalsIgnoreCase("xpath"))
			driver.findElement(By.xpath(element)).click();
//		if(locator.equalsIgnoreCase("linkText"))
//			Driver.instance.findElement(By.linkText(element)).click();
	}
	
	public void sendKeys(String locator,String element,String input)
	{
		if(locator.equalsIgnoreCase("id"))
			driver.findElement(By.id(element)).sendKeys(input);
		if(locator.equalsIgnoreCase("xpath"))
			driver.findElement(By.xpath(element)).sendKeys(input);
//		if(locator.equalsIgnoreCase("linktext"))
//			Driver.instance.findElement(By.linkText(element)).sendKeys(input);
	}
	
	public String getText(String locator,String element)
	{
		String text = null;
		if(locator.equalsIgnoreCase("id"))
			text= driver.findElement(By.id(element)).getText();
		if(locator.equalsIgnoreCase("xpath"))
			text= driver.findElement(By.xpath(element)).getText();
//		if(locator.equalsIgnoreCase("linktext"))
//			text= Driver.instance.findElement(By.linkText(element)).getText();
		
		return text;
	}
	public int getSize(String locator,String element)
	{
		int size = 0;
		if(locator.equalsIgnoreCase("id"))
			size= driver.findElements(By.id(element)).size();
		if(locator.equalsIgnoreCase("xpath"))
			size= driver.findElements(By.xpath(element)).size();
//		if(locator.equalsIgnoreCase("linktext"))
//			text= Driver.instance.findElement(By.linkText(element)).getText();
		
		return size;
	}
}
