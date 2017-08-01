package org.sapphire.appconsole.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WidgetRouteTest extends JerseyTest {
	
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
    
    //This method is for testing the widget search functionality.
    @Test
    public void SearchTest() {
		ClientResponse output = webResource.path("/appconsole-api/rest/widget/search").head();
        assertEquals("should return status 200", 200, output.getStatus());
    }
    
  //This method is for testing the widget eventOption functionality.
    @Test
    public void eventOptionsTest()
    {
    		ClientResponse output = webResource.path("/appconsole-api/rest/widget/eventoptions").head();
        assertEquals("should return status 200", 200, output.getStatus());
    }
    
  //This method is for testing the widget eventTest functionality.
    @Test
    public void eventActionsTest()
    {
    		ClientResponse output = webResource.path("/appconsole-api/rest/widget/eventactions").head();
        assertEquals("should return status 200", 200, output.getStatus());
    }
    
    @Test
    public void saveTest()
    {
    		//Need to check if this endpoint is used.
    }
}
