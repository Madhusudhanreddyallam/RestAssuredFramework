package postcalls;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pojo.User;
import util.BaseClass;
import util.StringUtils;

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
		User user = new User(name, StringUtils.getRandomEmailId() , gender , status);
		int id = restClient.post(GO_REST_ENDPOINT, user, "json", true)
				.then().log().all()
				.statusCode(201)
				.extract().path("id");

		restClient.get(GO_REST_ENDPOINT + "/"+id, true)
			.then().log().all()
			.assertThat().statusCode(200).body("id", equalTo(id));
	}

}

