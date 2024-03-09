package getcalls;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.HttpsStatus;
import io.restassured.response.Response;
import util.BaseClass;
import util.JsonFileReader;

public class GetUserData extends BaseClass {
	private JsonFileReader jsonFileReader;

	@BeforeClass
	public void initElements(){
		jsonFileReader = new JsonFileReader();
	}

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
	public void getAllHeaders() {
		Response response = restClient.get(GO_REST_ENDPOINT, false);
		
		Map<String , String> expHeaderValues = jsonFileReader.getJsonAsMap(USER_HEADERS_JSON_FILE_PATH);
		Map<String , String> actHeaderValues = new HashMap<String , String> ();
		
		Set<String> expectedHeaderNames = Set.of("x-pagination-limit", "x-pagination-page", "Content-Type");// add only required headers to compare into set

		for(String header : expectedHeaderNames) {
			actHeaderValues.put(header, response.header(header));
		}
		Assert.assertEquals(actHeaderValues, expHeaderValues);
	}

}
