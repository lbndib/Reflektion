package RESTAPIAutomation.Reflektion;

import static org.hamcrest.CoreMatchers.is;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceHolderGETRequest 
    
{	
	RequestSpecification httpRequest = RestAssured.given();	
	@Test
	public void verifyStatusCode() {
		
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		
		//Adding headders
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.header("charset","UTF-8");
		
		//GET Scenario-1 :Validating the Status Code,schema & count of records equal to 100
		
		
		Response response = (Response) httpRequest.get("/posts").then().assertThat().body("id.size()",is(100));
		assertEquals(200, response.getStatusCode());
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("posts.json"));		
		
		//GET Scenario 2 : Validating the status,schema,count of records including value
		
		Response responseOne = (Response) httpRequest.get("/posts/1").then().assertThat().body("id.size()",is(1));
		assertEquals(200, responseOne.getStatusCode());
		responseOne.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post1.json"));
		
		
		//GET Scenario 3 : Validating invaidposts statuscode and logging request and response
		Response invalidresponse = (Response) httpRequest.get("/invalidposts").then().assertThat().statusCode(404).log().all();
		
		
	}
	
	
	
   
}
