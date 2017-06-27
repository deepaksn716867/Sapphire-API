/**
 * 
 */
package org.sapphire.appconsole.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.sapphire.appconsole.service.SapphireService;

/**
 * @author deepak
 *
 */
@Path("/app/")
public class AppRoute {
	
	private final static Logger LOG = Logger.getLogger(AppRoute.class) ;

	/**
	 * Constructor
	 */
	public AppRoute() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appRouteSearch() throws Exception
	{
		LOG.info("Request Received for /search");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String appSearchjson = "";
		try
		{
			appSearchjson = service.appRouteSearch();
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The search result is::"+appSearchjson);
		
		response = Response.status(Response.Status.OK).entity(appSearchjson).build();
        return response;
	}

}
