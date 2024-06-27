package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtiliy {
static String URL;
	
	//This method for create url as properties file
	public static String getURL() throws FileNotFoundException, IOException
	{
		File file = new File(System.getProperty("user.dir")+"/TestData/Properties.properties");//file path;
		try(InputStream input = new FileInputStream(file))
		{
			Properties properties = new Properties(); //creating the object of properties;
			properties.load(input);//loading the inputstream;
			URL = properties.getProperty("Test_URL");//Getting the url;
		}
		catch(Exception exception){}
		return URL;//returning the url;
	}

}
