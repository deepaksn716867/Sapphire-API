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
import org.sapphire.appconsole.model.Widget;
import org.sapphire.appconsole.service.SapphireService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/widget/")
@Api(tags = {"widget"})                                                                                                                                                                                                                                                                                                         
public class WidgetRoute {

	/**
	 * This class is for the Widget route 
	 * @author deepak
	 */
	public WidgetRoute() {
		// TODO Auto-generated constructor stub
	}
	
	private final static Logger LOG = Logger.getLogger(WidgetRoute.class) ;
	
	@GET
	@Path("/search/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get all the widgets", 
    notes = "Returns all the Widgets associated with the App",
    response = Widget.class)
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Widget not found"),
	@ApiResponse(code = 200 , message = "Success")})
	public Response widgetRouteSearch() throws Exception
	{
		LOG.info("Request Received for /widget/search");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String widgetSearchjson = "";
		try
		{
			widgetSearchjson = service.widgetRouteSearch();
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The widget search result is::"+widgetSearchjson);
		
		response = Response.status(Response.Status.OK).entity(widgetSearchjson).build();
        return response;
	}
	
	@GET
	@Path("/eventoptions/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the widget Event Options", 
    notes = "Returns the Widgets Event Options associated with the App")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Widget Event Options not found"),
	@ApiResponse(code = 200 , message = "Success")})
	public Response widgetRouteEventOptions() throws Exception
	{
		LOG.info("Request Received for /widget/eventoptions");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String widgetEventOptionjson = "";
		try
		{
			widgetEventOptionjson = service.widgetRouteEventOption();
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The widget search result is::"+widgetEventOptionjson);
		
		response = Response.status(Response.Status.OK).entity(widgetEventOptionjson).build();
        return response;
	}
	
	@GET
	@Path("/eventactions/")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Get the widgets Event Actions", 
    notes = "Returns the Widgets Event actions associated with the App")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Widget Event Action not found"),
	@ApiResponse(code = 200 , message = "Success")})
	public Response widgetRouteEventActions() throws Exception
	{
		LOG.info("Request Received for /widget/eventaction");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String widgetEventActionjson = "";
		try
		{
			widgetEventActionjson = service.widgetRouteEventAction();
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The widget eventaction result is::"+widgetEventActionjson);
		
		response = Response.status(Response.Status.OK).entity(widgetEventActionjson).build();
        return response;
	}
	
	@PUT
	@Path("/{widgetid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create / Update a Widget", 
    notes = "Create a new widget or update an existing one")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Widget not created/Updated"),
	@ApiResponse(code = 201 , message = "Successfully created a new widget / Updated an existing one")})
	public Response widgetRouteSave(@ApiParam(value = "The Widget JSON", required = true) String httpBody,@ApiParam(value = "The Widget ID to be created / Updated", required = true) @PathParam("widgetid") String widgetID) throws Exception
	{
		LOG.info("Request Received for creating/updating /widget/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String widgetCreatejson = "";
		System.out.println("The widget Id is::"+widgetID);
		try
		{
			widgetCreatejson = service.widgetRouteSave(httpBody, widgetID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The create/update widget result is::"+widgetCreatejson);
		
		response = Response.status(Response.Status.CREATED).entity(widgetCreatejson).build();
        return response;
	}
	
	@DELETE
	@Path("/{widgetid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Delete a Widget", 
    notes = "Delete a widget associated with the App")
	@ApiResponses(value = { 
	@ApiResponse(code = 500, message = "Internal Server Error"),
	@ApiResponse(code = 404, message = "Widget not Deleted"),
	@ApiResponse(code = 200 , message = "Successfully created a new widget / Updated an existing one")})
	public Response widgetRouteDelete(@ApiParam(value = "The widget ID to be deleted", required = true) @PathParam("widgetid") String widgetID) throws Exception
	{
		LOG.info("Request Received for Deleting /widget/{id}");
		
		Response response = null;
		SapphireService service = SapphireService.getSapphireServiceInstance();
		String widgetDeletejson = "";
		
		LOG.info("The widget id is::"+widgetID);
		try
		{
			widgetDeletejson = service.widgetRouteDelete(widgetID);
		}
		catch(Exception e)
		{
			LOG.error("Unexpected error",e);
			e.printStackTrace();
			throw e;
		}
		
		LOG.debug("The widget delete result is::"+widgetDeletejson);
		
		response = Response.status(Response.Status.OK).entity(widgetDeletejson).build(); //Returning 200 now but , need to decide whether we have to return 200 or 204.
        return response;
	}

}
