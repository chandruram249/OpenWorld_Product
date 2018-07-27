package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigFile {
	private static Properties prop;
	private static FileInputStream fis;
	private static String propertyFilePath = "./Config.properties";

	public static String getInput(String input) throws IOException {
		fis = new FileInputStream(propertyFilePath);// File(propertyFilePath);
		prop = new Properties();
		prop.load(fis);
		return prop.getProperty(input);
	}
}
