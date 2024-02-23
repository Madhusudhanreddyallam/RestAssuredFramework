package getcalls;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import client.RestClient;

public class GetUserData {
	RestClient restClient;
	@Test
	public void getAllUsers() {
		restClient = new RestClient();
		restClient.get("/public/v2/users", true)
		.then().log().all()
		.assertThat().statusCode(200);
	}
	
	@Test
	public void getAllUsersQueryParams() {
		restClient = new RestClient();
		Map<String , String> queryParams = new HashMap<String , String> ();
		queryParams.put("name", "Damodara Gill III");
		queryParams.put("status", "active");

		restClient.get("/public/v2/users/", null, queryParams, true)
					.then().log().all()
						.assertThat().statusCode(200);
	}
	
}
