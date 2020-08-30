package RESTAPIAutomation.Reflektion;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class PlaceHolderGETRequest {
	
	@BeforeTest
	public void setUp() {
		
		ExtentTestManager.startTest("GET Request", "Retrieving the Response");
	}
	
    

	@Test
	public static Response doGetRequest(String endpoint) {
		
		
		
        RestAssured.defaultParser = Parser.JSON;
        

        return
        		RestAssured.given().headers("Content-Type", ContentType.JSON, "charset", "utf-8").
                when().log().all().get(endpoint).
                then().contentType(ContentType.JSON).extract().response();
        
        
    }	
	
	@Test
	public static void doGETResponse() {
		
		Response response = doGetRequest("https://jsonplaceholder.typicode.com/posts");
		
		//Schema Validation
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("posts.json"));	
		
		//Status Code Validaation
		assertEquals(200, response.getStatusCode());
		
        //Size Validation
        List<String> jsonResponse = response.jsonPath().getList("$");        
        Assert.assertEquals(jsonResponse.size(), 100);
        System.out.println("Endpoint URL having posts:"+jsonResponse.size());
        
        //Record One
        Response singleresponse = doGetRequest("https://jsonplaceholder.typicode.com/posts/1");
        
        //Schema Validation
        singleresponse.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post1.json"));	
      		
      	//Status Code Validation
      	 assertEquals(200, singleresponse.getStatusCode());
      		
          //Size Validation
          String jsonSingleResponse = singleresponse.jsonPath().getString("userId");  
          Assert.assertEquals(jsonSingleResponse.length(), 1);
          System.out.println("Endpoint URL having posts1:"+jsonSingleResponse.length());
          
          //InvalidPosts
          
          Response invalidrequestresponse = doGetRequest("https://jsonplaceholder.typicode.com/invalidposts");
          
          invalidrequestresponse.then().assertThat().statusCode(404).log().body();
          
          System.out.println("Test Case Successfully retrieved the response");   
          
          
          
        
		
	}
	
	@AfterTest
	public void teardown() {
		ExtentTestManager.endTest();
	}
	
	
   
}
