/**
 * 
 */
package org.sapphire.appconsole.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author deepak
 *
 */
public class TestUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("The property value is::"+readPropertyFile("baseurl"));
	}
	
	public static String readPropertyFile(String propertyName)
	{
		Properties props = new Properties();
		InputStream propFile = TestUtils.class.getResourceAsStream("/Users/deepak/work/Sapphire-API/src/main/resources/test.properties");
		try {
			props.load(propFile);
			propFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("gonna throw error");
			e.printStackTrace();
		}
		
		return props.getProperty(propertyName);
		}
	}
