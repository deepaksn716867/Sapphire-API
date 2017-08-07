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
import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.service.SapphireService;

import io.swagger.annotations.*;

/**
 * @author deepak
 *
 */
@Path("/app/")
@Api(tags = {"app"})
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
	@ApiOperation(value = "Get all the apps", 
    notes = "Returns all the Apps or if not will return an error",
    response = App.class)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Apps not found"),
	@ApiResponse(code = 200 , message = "Success")})
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
	@ApiOperation(value = "Save an App", 
    notes = "Creates a New App or Updates an existing App",
    response = App.class)
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "App not found"),
	@ApiResponse(code = 201 , message = "App Successfully created/updated")})
	public Response appRouteSave(@ApiParam(value="The HTTP App Create/update Body",required = true)String httpBody, @ApiParam(value="The App id which needs to be created/updated",required = true)@PathParam("appid") String appID) throws Exception
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
	@ApiOperation(value = "Delete an App", 
    notes = "Deletes a particular app with the given app id",
    response = String.class)
	@ApiResponses(value= {@ApiResponse(code = 200, message = "Successfully deleted the app"),
	@ApiResponse(code = 404 , message="Cannot find the App with the given APP ID"),
	@ApiResponse(code = 500 , message = "Internal server error")})
	public Response appRouteDelete(@ApiParam(value="The app id to be deleted" , required = true) @PathParam("appid") String appID) throws Exception
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
	@ApiOperation(value = "Get a particular App", 
    notes = "Get the App settings of a particular App",
    response = String.class)
	@ApiResponses(value= {@ApiResponse(code = 200, message = "Successfully retreived the app settings"),
	@ApiResponse(code = 404 , message="Cannot find the App with the given APP ID"),
	@ApiResponse(code = 500 , message = "Internal server error")})
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
