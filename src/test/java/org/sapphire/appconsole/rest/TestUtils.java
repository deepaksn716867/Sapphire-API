package org.sapphire.appconsole.rest;

import java.io.InputStream;
import java.util.Properties;

/**
 * /**
 * This class is the Utils class for the Junit test cases , 
 * that has the utility functions.
 * @author deepak
 */
public class TestUtils {

	public static String readPropertyFile(String propertyName) throws Exception
	{
		Properties props = new Properties();
		
        try 
        {
        			ClassLoader classLoader = TestUtils.class.getClassLoader();
		    		InputStream propFile = classLoader.getResourceAsStream("test.properties");
		    		props.load(propFile);
		    		propFile.close();
        }
        catch(Exception e)
        {
        		e.printStackTrace();
        		throw new Exception(e.getMessage());
        }
        
        return props.getProperty(propertyName);
	}
}
