package clientandvalidator;

import java.util.Map;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestClientChainingDummy {
	private static final String BASE_URI = "https://gorest.co.in";
	private static final String BEARER_TOKEN = "";
	

	public RequestSpecBuilder requestSpecBuilder() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .addHeader("Authorization", "Bearer " + BEARER_TOKEN);
    }

	public RequestSpecification createRequestSpec() {
		return	requestSpecBuilder().build();
	}

	public RequestSpecification createRequestSpec(Map<String , String> headersMap) {
		return	requestSpecBuilder().addHeaders(headersMap).build();
	}

	public RequestSpecification createRequestSpec(Map<String , String> headersMap, Map<String , String> queryParam) {
		return	requestSpecBuilder().addHeaders(headersMap).addQueryParams(headersMap).build();
	}
	
	// for post
	public RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		return	requestSpecBuilder().setContentType(contentType).setBody(requestBody).build();
	}
	
	public RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String , String> headersMap) {
		return	requestSpecBuilder().setContentType(contentType).addHeaders(headersMap).setBody(requestBody).build();
	}

}
