package schema;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import clientandvalidator.JsonPathValidater;
import io.restassured.response.Response;
import util.BaseClass;

public class SchemaValidation extends BaseClass {
	private JsonPathValidater jsonPathValidater;
	
	@BeforeClass
	public void initObj() {
		jsonPathValidater = new JsonPathValidater();
	}
	@Test
	public void getAllUsers() {
		Response responses = restClient.get(GO_REST_ENDPOINT, true);
		List<Integer> ids = jsonPathValidater.readJsonValueAsList(responses , "$.[*].id");
		
		Response response = restClient.get(GO_REST_ENDPOINT+"/"+ids.get(0), true);
		response.prettyPrint();
		response.then()
					.assertThat()
					.body(matchesJsonSchemaInClasspath("schemavalidation.json"));
	}

}
