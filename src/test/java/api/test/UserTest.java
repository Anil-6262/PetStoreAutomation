package api.test;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTest {
	
	
	Faker fk;
	UserPojo userPayload;
	
	public Logger logger;   // For logs
	
	@BeforeClass
	public void setUpData()
	{
		fk = new Faker();
		userPayload = new UserPojo();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password(5,10));
		userPayload.setPhone(fk.phoneNumber().phoneNumber());
		
		
		
		// Create Logs 
		logger = LogManager.getLogger(this.getClass());
		
	}
	 
	
	@Test(priority=1)
	public void testPostUser()
	{
		
		logger.info("************* Creating User **************");
		
		Response response= UserEndPoints.createUser(userPayload);
		response.then().log().all();
		System.out.println("Fetching user with username: " + this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************* User is Created  **************");
	}
	
	
	@Test(priority =2)
	public void testGetUserByUserName()
	{
		
		logger.info("************* Get user Info **************");
		
		Response response = UserEndPoints.getUser(this.userPayload.getUsername());
		response.then().log().all();
		System.out.println("Fetching user with username: " + this.userPayload.getUsername());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************* User Info is displayed **************");
		
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		
		logger.info("*************	Updating User **************");
		
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setPhone(fk.phoneNumber().phoneNumber());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************* User is updated **************");
		
		// Checking data after update
		
		Response res = UserEndPoints.getUser(this.userPayload.getUsername());
		res.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		
		logger.info("************* Deleting user **************");
		
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************* User deleted **************");
		
	}
	
	
	
	

}
