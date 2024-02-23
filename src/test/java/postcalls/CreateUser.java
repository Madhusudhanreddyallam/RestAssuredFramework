package postcalls;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import client.RestClient;
import pojo.User;
import util.StringUtils;

public class CreateUser {
	@Test
	public void getAllUsers() {
		
		//post call
		User user = new User("madhu" , StringUtils.getRandomEmailId() , "male" , "active");
		RestClient restClient = new RestClient();
		
		int id = restClient.post("/public/v2/users", user, "json", true)
							.then().log().all()
								.statusCode(201)
								.extract().path("id");
		
		RestClient restClientGet = new RestClient();
		restClientGet.get("/public/v2/users/" + id, true)
		.then().log().all()
		.assertThat().statusCode(200).body("id", equalTo(id));
	}

}

