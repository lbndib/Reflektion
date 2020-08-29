package RESTAPIAutomation.Reflektion;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaceHolderPUTRequest {
	
	@Test
	public void verifyPUTStatusCode() {
		
		
		int id=1;
		RequestSpecification httpRequest = RestAssured.given();	
		
		RestAssured.baseURI ="https://jsonplaceholder.typicode.com";
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", "1");
		requestParams.put("title", "abc"); 
		requestParams.put("body", "xyz");		 
		requestParams.put("userId", "1");
		
		httpRequest.header("Content-Type", "application/json"); 
		httpRequest.header("charset","UTF-8");
		 
		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		 
		
		Response response = httpRequest.put("/posts"+ id);
		
		
       //Scenario 1 - validate status code is 200
        int statusCode = response.getStatusCode();
        System.out.println(response.asString());
        Assert.assertEquals(statusCode, 200);
        
        //Scenario 2 - validate schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("put_schema.json"));	
        
        //Scenario 2 - validate record updated
      //Scenario 3 - Validating record created
        Assert.assertEquals(response.path("title"), "abc");
        Assert.assertEquals(response.path("body"), "xyz");
        Assert.assertEquals(response.path("userId"), "1");
        

	}

}
