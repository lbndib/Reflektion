package RESTAPIAutomation.Reflektion;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceHolderPOSTRequest {
RequestSpecification httpRequest = RestAssured.given();	
	
	@Test
	public void verifyStatusCode() {
		
		RequestSpecification httpRequest = RestAssured.given();	
		
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("title", "foo"); 
		requestParams.put("body", "bar");		 
		requestParams.put("userId", "1");
		
		//Adding headers
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.header("charset","UTF-8");
		
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/posts");
		

		//Scenario 1 - Validating status code 
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "201");
        
       //Scenario 2 - validate schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("post_schema.json"));	
        
        //Scenario 3 - Validating record created
        Assert.assertEquals(response.path("title"), "foo");
        Assert.assertEquals(response.path("body"), "bar");
        Assert.assertEquals(response.path("userId"), "1");
	}

}
