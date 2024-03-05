package clientandvalidator;

import java.util.List;
import java.util.Map;

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
	
	public <T> List<Map<T,T>>  readJsonValueAsListOfMap(Response response , String jsonPath) {
		String jsonResponse = response.getBody().asString();
		try {
			return JsonPath.read(jsonResponse,jsonPath);
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(jsonPath + " not found.");
		}
	}

}
