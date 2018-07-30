package OpenWorld.OpenWorld;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class Sikulicheck {
	
	public static void main(String a[]) throws FindFailed
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.17:5000");
		Screen s = new Screen();
		Pattern usernameTextBox = new Pattern("/home/kadamba/Open_World/OpenWorld/password.png");
        Pattern passwordTextBox = new Pattern("/home/kadamba/Open_World/OpenWorld/username.png");
        Pattern loginButton = new Pattern("/home/kadamba/Open_World/OpenWorld/login.png");
        s.wait(usernameTextBox, 20);
        s.type(usernameTextBox, "demoemp");
        s.type(passwordTextBox, "Pass@123");
        s.click(loginButton);
	}

}
