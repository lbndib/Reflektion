package RESTAPIAutomation.Reflektion;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceHolderDeleteRequest {
	RequestSpecification httpRequest = RestAssured.given();	
	String projectPath = "C:\\Users\\laksh\\eclipse-workspace\\eCommerce\\test-output\\extent.html";
	ExtentHtmlReporter htmlReporter; 
	ExtentReports extent; 
	
	@BeforeTest
	public void setUp() {	
		
		htmlReporter = new ExtentHtmlReporter(projectPath);
		extent= new ExtentReports();
		extent.attachReporter(htmlReporter);
	
	}
	
	
	@Test
	public void verifyDeleteStatusCode() {
		int id=1;
		ExtentTest test = extent.createTest("MyFirstTest", "Report shuld be great");
		
		RequestSpecification httpRequest = RestAssured.given();			
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
				
		//Adding headers
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.header("charset","UTF-8");
		
		// Add the Json to the body of the request		
		Response response = httpRequest.delete("/posts"+id);
		

		//Scenario 1 - Validating status code 
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "200");
        
        //Scenario 2 - Validating the response
        String jsonString =response.asString();
        Assert.assertEquals(jsonString.contains("successfully! deleted Record"), true);
     
	}
	
}
