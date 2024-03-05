package getcalls;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import constants.HttpsStatus;
import util.BaseClass;

public class GetUserData extends BaseClass {
	
 
	@Test
	public void getAllUsers() {
		restClient.get(GO_REST_ENDPOINT, true)
		.then().log().all()
		.assertThat().statusCode(HttpsStatus.OK_200.getCode());
		}
	
	@Test
	public void getAllUsersQueryParams() {
		Map<String , String> queryParams = new HashMap<String , String> ();
		queryParams.put("name", "Madhuri Agarwal");
		queryParams.put("status", "active");

		restClient.get(GO_REST_ENDPOINT, null, queryParams, true)
					.then().log().all()
						.assertThat().statusCode(HttpsStatus.OK_200.getCode());		
	}
	
	@Test
	public void getAllUsersQueryParamss() {
		Map<String , String> queryParams = new HashMap<String , String> ();
		queryParams.put("status", "inactive");

		restClient.get(GO_REST_ENDPOINT, null, queryParams, true)
					.then().log().all()
						.assertThat().statusCode(HttpsStatus.OK_200.getCode());
	}
	
}
