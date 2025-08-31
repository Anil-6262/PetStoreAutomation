package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	public static Response createUser(UserPojo payload)
	{
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(RoutesURL.post_url);
		
		return response;
			
	}
	
	public static Response getUser(String userName)
	{
		
		Response response= given()
				.pathParam("username", userName)
		.when()
			.get(RoutesURL.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload)
	{
		
		Response response=given()
			.contentType(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(RoutesURL.put_url);
		
		return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete(RoutesURL.delete_url);
		
		return response;
			
		
	}
	
	

}
