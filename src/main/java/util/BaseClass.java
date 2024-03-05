package util;

import org.testng.annotations.BeforeTest;

import client.RestClient;
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

}
