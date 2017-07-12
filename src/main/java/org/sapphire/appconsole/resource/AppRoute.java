/**
 * 
 */
package org.sapphire.appconsole.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
		LOG.info("Request Received for /app/search");
		
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
	
	@PUT
	@Path("/{appid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response appRouteSave(String httpBody, @PathParam("appid") String appID) throws Exception
	{
		LOG.info("Request Received for creating/updating /app/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String appCreatejson = "";
		System.out.println("The appid is::"+appID);
		try
		{
			appCreatejson = service.appRouteSave(httpBody, appID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The create/update app result is::"+appCreatejson);
		
		response = Response.status(Response.Status.CREATED).entity(appCreatejson).build();
        return response;
	}

}
