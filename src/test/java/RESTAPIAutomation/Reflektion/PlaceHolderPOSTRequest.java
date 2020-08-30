package RESTAPIAutomation.Reflektion;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.JSONObject;
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
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceHolderPOSTRequest {
	
	@BeforeTest
	public void setUp() {
		ExtentTestManager.startTest("POST Request", "Creating the record");
		
	}
	
	
	@Test
	public static Response doPostRequest(String endpoint) throws IOException {
		
		
	   RestAssured.defaultParser = Parser.JSON;
	   
	    JSONObject requestParams = new JSONObject();
		requestParams.put("title", "foo"); 
		requestParams.put("body", "bar");		 
		requestParams.put("userId", "1");
	        

	        Response response = 
	        		RestAssured.given().body(requestParams.toJSONString()).headers("Content-Type", ContentType.JSON, "charset", "utf-8").
	                when().log().all().post(endpoint).
	                then().contentType(ContentType.JSON).extract().response();
	       
			return response;
	        
	    }
		
@Test(dependsOnMethods= {"doPostRequest"})	
public static void doGETPostRequest() throws IOException {
		
		Response response = doPostRequest("https://jsonplaceholder.typicode.com/posts");
		assertEquals(201, response.getStatusCode());
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post_schema.json"));
		
		//Validation of Created Records
		Assert.assertEquals(response.path("title"), "foo");
        Assert.assertEquals(response.path("body"), "bar");
        Assert.assertEquals(response.path("userId"), "1");
        
        System.out.println("Test Case Successfully posted the request and recod validated with Asserts");
		
	}
	
@AfterTest()
public void teardown() {
	
	ExtentTestManager.endTest();
	
	
}

}
