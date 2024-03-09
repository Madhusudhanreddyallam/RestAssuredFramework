package getcalls;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import clientandvalidator.JsonPathValidater;
import io.restassured.response.Response;
import pojo.Formula1CircuitData;
import pojo.Formula1CircuitData.MRData.CircuitTable.Circuits;
import util.BaseClass;
import util.JsonFileReader;

public class GetCircuitComplexJSON extends BaseClass{

	private JsonPathValidater jsonPathValidater;
	private JsonFileReader jsonFileReader;
	
	@BeforeClass
	public void initElements(){
		jsonPathValidater = new JsonPathValidater();
		jsonFileReader = new JsonFileReader();
	}

	@Test
	public void getAllCircuits() {

		//Reading JSON File data as Map
		Map<String , String> expected = jsonFileReader.getJsonAsMap(CIRCUIT_JSON_FILE_PATH);

		//Getting Response 
		Response response = restClient.get(CIRCUIT_ENDPOINT, true);	

		//ParsingResponse To String
		Formula1CircuitData mrData = JsonPathValidater.parseResponse(response, Formula1CircuitData.class);

		//Filtering a set of Data (Able to apply stream because getCircuits is list)
		Circuits albertParkCircuit = mrData.getMRData().getCircuitTable().getCircuits()
				.stream()
				.filter(circuitt -> circuitt.getCircuitId().equals("albert_park") )
				.findFirst()
				.orElse(null);
		
		//adding response data in actual map
		Map<String , String> actual = new HashMap<String , String>();
		actual.put("circuitId", albertParkCircuit.getCircuitId());
		actual.put("url", albertParkCircuit.getUrl());
		actual.put("circuitName", albertParkCircuit.getCircuitName());
		actual.put("lat",albertParkCircuit.getLocation().getLat());
		actual.put("long", albertParkCircuit.getLocation().get_long());
		actual.put( "locality", albertParkCircuit.getLocation().getLocality());
		actual.put("country", albertParkCircuit.getLocation().getCountry());

		Assert.assertEquals(actual, expected);
	}

	@Test
	public void getAllCircuitIds() {
		Response response = restClient.get(CIRCUIT_ENDPOINT, true);			
		List<String> circuits = jsonPathValidater.readJsonValueAsList(response, "MRData.CircuitTable.Circuits[*].circuitId");
		for(String values : circuits) {
			System.out.println(values);
		}
	}
	
	@Test
	public void getCircuitLocations() {
		Response response = restClient.get(CIRCUIT_ENDPOINT, true);			
		List<Map<String, Object>> data = jsonPathValidater.readJsonValueAsListOfMap(response,
				"$.MRData.CircuitTable.Circuits[?(@.Location.country == 'Australia' )].Location[\"lat\",\"locality\",\"country\"]");

		List<Map<String, Object>> data2 = jsonPathValidater.readJsonValueAsListOfMap(response,
				"$.MRData.CircuitTable.Circuits[0,1,2].Location[\"lat\",\"locality\",\"country\"]");
		
		for(Map<String , Object> map : data) {
			String lat = (String) map.get("lat");
			String country = (String) map.get("country");
			String locality = (String) map.get("locality");
			System.out.println(lat + " " + country + " " + locality);
		}
		
		for(Map<String , Object> map : data2) {
			String lat = (String) map.get("lat");
			String country = (String) map.get("country");
			String locality = (String) map.get("locality");
			System.out.println(lat + " " + country + " " + locality);
		}
	}

}
