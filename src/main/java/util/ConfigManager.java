package util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigManager {
	
	private String propFileName = System.getProperty("env");
    private String propertyFilePath = "./src/test/resources/Data/"+propFileName +".properties"; 
	private Properties prop;

	public ConfigManager() {
		prop = new Properties();
		try( FileInputStream file = new FileInputStream(propertyFilePath)) {
			prop.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public String getKeyValue(String key) {
		String value = prop.getProperty(key);
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("Key not found in the configuration.properties file: " + key);
		}
	}

}
