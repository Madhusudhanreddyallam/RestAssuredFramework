package getcalls;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import clientandvalidator.JsonPathValidater;
import io.restassured.response.Response;
import pojo.Formula1CircuitData;
import pojo.Formula1CircuitData.MRData.CircuitTable.Circuits;
import util.BaseClass;
import util.JsonFileReader;

public class GetCircuitComplexJSON extends BaseClass{

	@Test
	public void getAllCircuits() {
		
		//Reading JSON File data as Map
		JsonFileReader jsonFileReader = new JsonFileReader();
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

}
