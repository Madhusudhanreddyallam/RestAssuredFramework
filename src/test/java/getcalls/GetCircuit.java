package getcalls;

import java.util.List;

import org.testng.annotations.Test;

import clientandvalidator.JsonPathValidater;
import io.restassured.response.Response;
import util.BaseClass;

public class GetCircuit extends BaseClass {
	

		@Test
		public void getAllCercuits() {
			Response response = restClient.get(Circuit_ENDPOINT, true);		
			JsonPathValidater JsonPathValidater = new JsonPathValidater();
			List<String> circuits = JsonPathValidater.readJsonValueAsList(response, "MRData.CircuitTable.Circuits[*].circuitId");
			for(String values : circuits) {
				System.out.println(values);
			}
			}

}
