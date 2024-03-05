package clientandvalidator;

import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private String baseURI;
	private String apiKey;
	private RequestSpecBuilder specBuilder;

	public RestClient(String baseURI , String apiKey ) {	
		this.apiKey = apiKey;
		this.baseURI = baseURI;
	}

	public void setRequestContentType(String contentType) {
		switch(contentType.toLowerCase()) {
		case "json" :
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml" :
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text" :
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart" :
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default :
			System.out.println("please pass the correct content type");
			throw new RuntimeException("Invalid ContentType");
		}
	}

	private void addAuthHeader() {
		specBuilder.addHeader("Authorization", "Bearer " + apiKey);
	}

	private void setBaseRequestSpec() {
		specBuilder = new RequestSpecBuilder();
		specBuilder.setBaseUri(baseURI);
		addAuthHeader();
	}

	private RequestSpecification createRequestSpec() {
		setBaseRequestSpec();
		return	specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Map<String , String> headersMap, Map<String , String> queryParam) {
		setBaseRequestSpec();
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}	
		if(queryParam!=null) {
			specBuilder.addQueryParams(queryParam);
		}
		return	specBuilder.build();
	}
	// for Post request
	private RequestSpecification createRequestSpec(Object requestBody, String contentType) {
		setBaseRequestSpec();	
		setRequestContentType(contentType);
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}	
		return	specBuilder.build();
	}

	private RequestSpecification createRequestSpec(Object requestBody, String contentType, Map<String , String> headersMap) {
		setBaseRequestSpec();	
		setRequestContentType(contentType);
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}	
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return	specBuilder.build();
	}

	//get calls

	public Response get(String serviceUrl , boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
						.when()
							.get(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec())
					.when()
						.get(serviceUrl);
		}
	}
	
	
	public Response get(String serviceUrl ,Map<String , String> headersMap, Map<String , String> queryParam , boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(headersMap , queryParam)).log().all()
						.when()
							.get(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(headersMap,queryParam))
					.when()
						.get(serviceUrl);
		}

	}
	
	//post call
	
	public Response post(String serviceUrl ,Object requestBody, String contentType, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType)).log().all()
						.when()
							.post(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType))
					.when()
						.post(serviceUrl);
		}

	}
	
	public Response post(String serviceUrl ,Object requestBody, String contentType, Map<String , String> headersMap ,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap)).log().all()
						.when()
							.post(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap))
					.when()
						.post(serviceUrl);
		}

	}
	
	//put call
	
	public Response put(String serviceUrl ,Object requestBody, String contentType, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType)).log().all()
						.when()
							.put(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType))
					.when()
						.put(serviceUrl);
		}

	}
	
	public Response put(String serviceUrl ,Object requestBody, String contentType, Map<String , String> headersMap ,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap)).log().all()
						.when()
							.put(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap))
					.when()
						.put(serviceUrl);
		}

	}
	
	//patch call
	
	public Response patch(String serviceUrl ,Object requestBody, String contentType, boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType)).log().all()
						.when()
							.patch(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType))
					.when()
						.patch(serviceUrl);
		}

	}
	
	public Response patch(String serviceUrl ,Object requestBody, String contentType, Map<String , String> headersMap ,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap)).log().all()
						.when()
							.patch(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec(requestBody , contentType , headersMap))
					.when()
						.patch(serviceUrl);
		}

	}
	
	//delete call
	public Response delete(String serviceUrl , boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec()).log().all()
						.when()
							.delete(serviceUrl);
		}else {
			return RestAssured.given(createRequestSpec())
					.when()
						.delete(serviceUrl);
		}

	}

}
