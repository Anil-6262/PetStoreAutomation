package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints2 {
	
	// Method created for getting URL's form properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("routes"); //Load properties file
		return routes;
	}
	
	
	
	public static Response createUser(UserPojo payload)
	{
		
		String post_url=getURL().getString("post_url");
		
		Response response= given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		System.out.println("******* Create ********");
		
		return response;
		
		
			
	}
	
	public static Response getUser(String userName)
	{
		
		String get_url = getURL().getString("get_url");
		
		Response response= given()
				.pathParam("username", userName)
		.when()
			.get(get_url);
		
		System.out.println("******* Get ********");
		
		return response;
	}
	
	public static Response updateUser(String userName, UserPojo payload)
	{
		
		String put_url = getURL().getString("put_url");
		
		Response response=given()
			.contentType(ContentType.JSON)
			.contentType(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(put_url);
		
		System.out.println("******* Update ********");
		
		return response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		
		String delete_url=getURL().getString("delete_url");  
		
		Response response = given()
				.pathParam("username", userName)
			.when()
				.delete(delete_url);
		
		System.out.println("******* Delete ********");
		
		return response;
			
		
	}
	
	

}
