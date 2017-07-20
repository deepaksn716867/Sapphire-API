package org.sapphire.appconsole.rest;

import static org.junit.Assert.assertEquals;

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
    public void initialize() {
    		webResource = client().resource(TestUtils.readPropertyFile("baseurl"));
    }
 
    @Test
    public void testFetchAll() {
		ClientResponse output = webResource.path("/appconsole-api/rest/widget/search").head();
		System.out.print("hello::"+output.getEntity(String.class));
        assertEquals("should return status 200", 200, output.getStatus());
    }

}
