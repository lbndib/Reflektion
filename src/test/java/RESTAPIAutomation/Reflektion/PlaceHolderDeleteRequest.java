package RESTAPIAutomation.Reflektion;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class PlaceHolderDeleteRequest {
	
	 @BeforeTest()
	 public void setUp() {
			ExtentTestManager.startTest("Delete Request", "Deleting the record");
			
		}
	
	@Test
	public static Response doDeleteRequest(String endpoint) throws IOException {       
	
   RestAssured.defaultParser = Parser.JSON;
        

        Response response = 
        		RestAssured.given().headers("Content-Type", ContentType.JSON, "charset", "utf-8").
                when().delete(endpoint).
                then().contentType(ContentType.JSON).extract().response();     
       
        
		return response;
        
    }
	@Test
	public static void doGetDeleteResponse() throws IOException {
		
		Response response = doDeleteRequest("https://jsonplaceholder.typicode.com/posts/1");
		assertEquals(200, response.getStatusCode());
		
		String jsonSingleResponse = response.jsonPath().getString("$");
		Assert.assertTrue(jsonSingleResponse.equals("[:]"));
		System.out.println("Test Case Successfully deleted the request");
		
	}
	
	 @AfterTest()
	    public void teardown() {
	    	
	    	ExtentTestManager.endTest();
	    	
	    	
	    }
	
}
