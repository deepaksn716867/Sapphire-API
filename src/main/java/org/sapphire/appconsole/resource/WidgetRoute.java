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

@Path("/widget/")
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
	public Response widgetRouteSave(String httpBody, @PathParam("widgetid") String widgetID) throws Exception
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
	public Response widgetRouteDelete(@PathParam("widgetid") String widgetID) throws Exception
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
