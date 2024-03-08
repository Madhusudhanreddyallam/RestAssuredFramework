package util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileReader {
	
	public <T> Map<String , T> getJsonAsMap(String filePath){
		ObjectMapper  objectMapper = new ObjectMapper();
		try {
			Map<String , T> data = objectMapper.readValue(new File(filePath), new TypeReference<>(){});
			return data;

		}  catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
