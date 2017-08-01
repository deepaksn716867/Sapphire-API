package org.sapphire.appconsole.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class WidgetSettingRouteTest extends JerseyTest {
	
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
    
    //This method is for testing the widgetsettingoption search functionality.
    @Test
    public void SearchTest() {
		ClientResponse output = webResource.path("/appconsole-api/rest/widgetsettingoption/search").head();
        assertEquals("should return status 200", 200, output.getStatus());
    }
}
