package org.sapphire.appconsole.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class AppRouteTest extends JerseyTest {
	
	private WebResource webResource = null;

    @Override
    public AppDescriptor configure() {
    	return new WebAppDescriptor.Builder().build();
    }

    @Before
    public void initialize() throws Exception {
    		try{
    			webResource = client().resource(TestUtils.readPropertyFile("baseurl"));
    		}
    		catch(Exception e)
    		{
    			throw e;
    		}
    }
    
    //This method is for testing the app search functionality.
    @Test
    public void appSearchTest() {
		ClientResponse output = webResource.path("/appconsole-api/rest/app/search").head();
        assertEquals("should return status 200", 200, output.getStatus());
    }
    
    //This method is for testing the app save functionality.
    @Test
    public void appSaveTest() throws Exception {
    	
    	String appSaveTestjson = "";
    	ClassLoader classLoader = getClass().getClassLoader();
    	
    	//Reading JSON File for the put body. 
    	try 
    	{
    		appSaveTestjson = IOUtils.toString(classLoader.getResourceAsStream("appSave.json"));
    	} 
    	catch (IOException e) 
    	{
    		//oops something bad happened :(
    		e.printStackTrace();
    		throw e;
    	}
    	
    	if(appSaveTestjson == null && appSaveTestjson.isEmpty())
    	{
    		//oops something bad happened :(
    		throw new Exception("Unexpected Error while reading appSave json file");
    	}
    	
    	//Gonna make the PUT request.
    	ClientResponse output = null;
	output = webResource.path("/appconsole-api/rest/app/b594afad-1e6c-42c4-d58c-715c62cfdfc").type("application/json").put(ClientResponse.class,appSaveTestjson); 
    	
    	assertEquals("should return status 201", 201, output.getStatus());
    }
    
    //We are gonna get the app json we saved in the previous save test. 
    @Test
    public void appGetTest() throws Exception {
    	
    	//Gonna make the GET request.
    	ClientResponse output = null;
	output = webResource.path("/appconsole-api/rest/app/b594afad-1e6c-42c4-d58c-715c62cfdfc").get(ClientResponse.class);
	
	String appGetTestjson = "";
	ClassLoader classLoader = getClass().getClassLoader();
	
	//Reading JSON File for the put body. 
	try 
	{
		appGetTestjson = IOUtils.toString(classLoader.getResourceAsStream("appGet.json"));
	} 
	catch (IOException e) 
	{
		//oops something bad happened :(
		e.printStackTrace();
		throw e;
	}
	
	if(appGetTestjson == null && appGetTestjson.isEmpty())
	{
		//oops something bad happened :(
		throw new Exception("Unexpected Error while reading appSave json file");
	}
	
	assertEquals("json does not match",appGetTestjson,output.getEntity(String.class));
    }
    
}
