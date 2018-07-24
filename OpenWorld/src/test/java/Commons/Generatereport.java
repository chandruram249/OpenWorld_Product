package Commons;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Generatereport {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest log;
	public Generatereport(String reportname,String Scriptname) throws IOException
	{
		htmlReporter = new ExtentHtmlReporter("./"+reportname+".html");
		//ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./reportname.html");
		extent = new ExtentReports();
		//ExtentReports extent1 = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //extent1.attachReporter(htmlReporter1);
        log = extent.createTest(Scriptname);
        //ExtentTest log1 = extent.createTest("Automation Script Test");
        //log.log(Status.INFO,"passed");
        //log.log(Status.INFO, "failed");
       // log.info("verified");
        log.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("./Screenshot/Test2.png").build());
        //test.addScreenCaptureFromPath("./"+screenshotname+".jpg");
        //log.log(Status.INFO, "passed1");
        //htmlReporter.setAppendExisting(true);
        extent.flush();
	}

	public static void main(String[] args) throws IOException {
		new Generatereport("Selection_005", "Automation_SCript");
		new Generatereport("Selection_005", "Automation_SCript");
	}
	}