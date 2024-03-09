package clientandvalidator;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.restassured.response.Response;

public class JsonPathValidater {
	
	public <T> T readJsonValue(Response response , String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse,jsonPath);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(jsonPath + " not found.");
		}
	}
	
	public <T> List<T>  readJsonValueAsList(Response response , String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse,jsonPath);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(jsonPath + " not found.");
		}
	}
	
	public <T> List<Map<String, Object>>  readJsonValueAsListOfMap(Response response , String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse,jsonPath);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(jsonPath + " not found.");
		}
	}
	
	public static <T> T parseResponse(Response response, Class<T> clazz)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			return objectMapper.readValue(response.getBody().asString(), clazz);
		}  catch (JsonProcessingException e) {
			e.printStackTrace();
	        throw new RuntimeException("Error processing JSON response in parseResponse method: " + e.getMessage());
		}

    }

}
