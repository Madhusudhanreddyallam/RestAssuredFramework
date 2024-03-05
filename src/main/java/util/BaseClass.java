package util;

import org.testng.annotations.BeforeTest;

import clientandvalidator.RestClient;
import constants.ServiceUrl;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseClass extends ServiceUrl {
	
	private ConfigManager config;
	protected String baseURI;
	protected String apiKey;
	protected RestClient restClient;
	
	@BeforeTest
	public void setup() {
		RestAssured.filters(new AllureRestAssured());
		config = new ConfigManager();
	    baseURI = config.getKeyValue("baseURI");
	    apiKey = config.getKeyValue("token");
    	restClient = new RestClient(baseURI ,apiKey );
	}
	
	public static String getRandomEmailId() {
		return "test"+System.currentTimeMillis()+"@api.com";
	}

}
