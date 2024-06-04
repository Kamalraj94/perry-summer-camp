package utilities;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.RestAssured;
import junit.framework.Assert;
import java.io.IOException;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

public class ApiRequests {
	
	String endPointUrl ;
	ConfigFileReader config ;
	JsonPath json ;


	public ApiRequests() throws IOException {
		 config = new ConfigFileReader();
		request = RestAssured.given();
	}
	
	 private RequestSpecification request;
	 private  Response response;




	 public String getRequestforUsers(String id){
         endPointUrl = config.getURL()+config.getUrlForId()+id;
		 response = RestAssured.get(endPointUrl).then().contentType(ContentType.JSON).extract().response();
		 Assert.assertEquals(response.getStatusCode(),200);
		 json = response.jsonPath();
		 return json.getString("id");
	 }


	public void postRequestforUsers(String name , String uuid){
		endPointUrl = config.getURL()+config.postUrlForId();
		request.put("name",name);
		request.put("id",uuid);
		response = request.post(endPointUrl);
		Assert.assertEquals(response.getStatusCode(),201);
	}

	public void postRequestforSendingMessage(String fromUserId , String toUserId ,String messageContent,String uuid){
		endPointUrl = config.getURL()+config.postRequestForSendingMessage();
		request.put("from", new JSONObject().put("id", fromUserId.toString()));
		request.put("to", new JSONObject().put("id", toUserId.toString()));
		request.put("message", messageContent);
		request.put("id", uuid);
		request.put("time", "2021-03-04T00:54:30.288Z");
		response = request.post(endPointUrl);
		Assert.assertEquals(response.getStatusCode(),201);
	}

	public String getMessageDetails(String fromId , String toId){
		endPointUrl = config.getURL()+config.getRequestForMessageValidation();
		response = given().queryParam("from", fromId)
				.queryParam("to", toId)
				.when()
				.get("/message")
				.then()
				.statusCode(200)
				.extract()
				.response();
		json = response.jsonPath();
		return json.getString("message");
	}

	public void updateMessageDetails(String uuid,String messageContent){
		endPointUrl = config.getURL()+config.putRequestForMessageValidation()+uuid;
		request.put("message", messageContent);
		response = request.put(endPointUrl);
		Assert.assertEquals(response.getStatusCode(),201);
	}

	public String getUpdatedMessageDetails(String uuid){
		endPointUrl = config.getURL()+config.getRequestForMessageValidation()+uuid;
		response = request.get(endPointUrl);
		Assert.assertEquals(response.getStatusCode(),201);
		return json.getString("message");
	}

	public void deleteMessageDetails(String uuid){
		endPointUrl = config.getURL()+config.getRequestForMessageValidation()+uuid;
		response = request.delete(endPointUrl);
		Assert.assertEquals(response.getStatusCode(),204);
	}

}
