package postcalls;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import clientandvalidator.JsonPathValidater;
import io.restassured.response.Response;
import pojo.User;
import util.BaseClass;

public class CreateUser extends BaseClass {

	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"madhu" , "male" , "active"},
			{"suma" , "female" , "active"}
		};
	}

	@Test(dataProvider = "getUserData")
	public void createUserAndVerifyDetails(String name, String gender , String status) {
		//post call
		User user = new User(name, getRandomEmailId() , gender , status);
		Response response = restClient.post(GO_REST_ENDPOINT, user, "json", true);
		//De-Serialization
		User responseObj = JsonPathValidater.parseResponse(response, User.class);
		Assert.assertEquals(user.getEmail() , responseObj.getEmail());
		Assert.assertEquals(user.getGender() , responseObj.getGender());
		Assert.assertEquals(user.getName() , responseObj.getName());
		Assert.assertEquals(user.getStatus() , responseObj.getStatus());
	}

	@Test(dataProvider = "getUserData")
	public void createUserAndVerifyDetailsusingID(String name, String gender , String status) {
		//post call
		User user = new User(name, getRandomEmailId() , gender , status);
		int id = restClient.post(GO_REST_ENDPOINT, user, "json", true)
				.then().log().all()
				.statusCode(201) 
				.extract().path("id");
		//Get Call
		Response response = restClient.get(GO_REST_ENDPOINT + "/"+id, true);	
		
		//De-Serialization
		User responseObj = JsonPathValidater.parseResponse(response, User.class);
		Assert.assertEquals(user.getEmail() , responseObj.getEmail());
		Assert.assertEquals(user.getGender() , responseObj.getGender());
		Assert.assertEquals(user.getName() , responseObj.getName());
		Assert.assertEquals(user.getStatus() , responseObj.getStatus());

	}
}

