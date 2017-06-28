package org.sapphire.appconsole.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.sapphire.appconsole.service.SapphireService;

/**
 * This is the class for the layout route resource.
 * @author deepak
 *
 */
@Path("/layout/")
public class LayoutRoute {

	public LayoutRoute() {
		// TODO Auto-generated constructor stub
	}
	
private final static Logger LOG = Logger.getLogger(WidgetRoute.class) ;
	
	@GET
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response widgetRouteSearch() throws Exception
	{
		LOG.info("Request Received for /layout/search");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String layoutSearchjson = "";
		try
		{
			layoutSearchjson = service.layoutRouteSearch();
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The layout search result is::"+layoutSearchjson);
		
		response = Response.status(Response.Status.OK).entity(layoutSearchjson).build();
        return response;
	}

}
