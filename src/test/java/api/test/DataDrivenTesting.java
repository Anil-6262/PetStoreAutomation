package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTesting {
	
	
	
	@Test(priority=1, dataProvider="Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userID, String userName, String fName, String lName, String email,String pwd,String phNo)
	{
		
		UserPojo userPayload = new UserPojo();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phNo);
		
		Response response = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		
	}
	
	@Test(priority = 2, dataProvider = "UserName", dataProviderClass = DataProviders.class)
	public void testDeleteUserByUserName(String userName)
	{
		Response response = UserEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}
