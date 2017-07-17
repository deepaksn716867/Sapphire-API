/**
 * 
 */
package org.sapphire.appconsole.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
	
	@DELETE
	@Path("/{appid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appRouteDelete(@PathParam("appid") String appID) throws Exception
	{
		LOG.info("Request Received for Deleting /app/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String appDeletejson = "";
		
		LOG.info("The app id is::"+appID);
		try
		{
			appDeletejson = service.appRouteDelete(appID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The app delete result is::"+appDeletejson);
		
		response = Response.status(Response.Status.OK).entity(appDeletejson).build(); //Returning 200 now but , need to decide whether we have to return 200 or 204.
        return response;
	}
	
	@GET
	@Path("/{appid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response appRouteGet(@PathParam("appid") String appID) throws Exception
	{
		LOG.info("Request Received for GET /app/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String appGetjson = "";
		
		LOG.info("The app id is::"+appID);
		try
		{
			appGetjson = service.appRouteGet(appID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The app get result is::"+appGetjson);
		
		response = Response.status(Response.Status.OK).entity(appGetjson).build(); //Returning 200 now but , need to decide whether we have to return 200 or 204.
        return response;
	}

}
