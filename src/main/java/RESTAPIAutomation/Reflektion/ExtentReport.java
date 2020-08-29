package RESTAPIAutomation.Reflektion;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

public class ExtentReport {	 
	String projectPath = ".\\Reflektion\\src\\test\\test-output\\extent.html";	
	ExtentHtmlReporter htmlReporter; 
	ExtentReports extent; 
	
	@BeforeTest
	public void setUp() {	
		
		htmlReporter = new ExtentHtmlReporter(projectPath);
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
	
	}
	@Test
	public void test1() throws IOException {
			
		ExtentTest test = extent.createTest("MyFirstTest", "Report shuld be great");		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\laksh\\Downloads\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		test.log(Status.INFO,"this step shows usage of log(status,details)");
		
		driver.get("http://www.nopcommerce.com/register");	
		Assert.assertTrue(false);
		
		test.fail("details",MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
		
		test.addScreenCaptureFromPath("screenshot.png");
		
		driver.close();
		driver.quit();		
	
	}}