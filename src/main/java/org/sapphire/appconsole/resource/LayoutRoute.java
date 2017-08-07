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
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.service.SapphireService;

import io.swagger.annotations.*;

/**
 * This is the class for the layout route resource.
 * @author deepak
 *
 */
@Path("/layout/")
@Api(tags = {"layout"})
public class LayoutRoute {

	public LayoutRoute() {
		// TODO Auto-generated constructor stub
	}
	
private final static Logger LOG = Logger.getLogger(WidgetRoute.class) ;
	
	@GET
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the Layouts", 
    notes = "Returns all the associated layouts",
    response = Layout.class)
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Layout not found"),
	@ApiResponse(code = 200, message = "Success")})
	public Response layoutRouteSearch() throws Exception
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
	
	@PUT
	@Path("/{layoutid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create / Update a Layout", 
    notes = "Create a new layout or update an existing one")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Layout cannot be created / updated"),
	@ApiResponse(code = 201, message = "Successfully created a new layout")})
	public Response layoutRouteSave(@ApiParam(value="Layout Body",required = true) String httpBody,@ApiParam(value="Layout ID to be created / updated",required = true) @PathParam("layoutid") String layoutID) throws Exception
	{
		LOG.info("Request Received for creating/updating /layout/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String layoutCreatejson = "";
		System.out.println("The layout is::"+layoutID);
		try
		{
			layoutCreatejson = service.layoutRouteSave(httpBody, layoutID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The create/update layout result is::"+layoutCreatejson);
		
		response = Response.status(Response.Status.CREATED).entity(layoutCreatejson).build();
        return response;
	}
	
	@DELETE
	@Path("/{layoutid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete a Layout",
    notes = "Delete a Layout with the given layout ID")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Layout cannot be found"),
	@ApiResponse(code = 200, message = "Successfully deleted the layout")})
	public Response layoutRouteDelete(@PathParam("layoutid") String layoutID) throws Exception
	{
		LOG.info("Request Received for Deleting /layout/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String layoutDeletejson = "";
		
		LOG.info("The layout is::"+layoutID);
		try
		{
			layoutDeletejson = service.layoutRouteDelete(layoutID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The Delete layout result is::"+layoutDeletejson);
		
		response = Response.status(Response.Status.OK).entity(layoutDeletejson).build(); //Returning 200 now but , need to decide whether we have to return 200 or 204.
        return response;
	}

}
