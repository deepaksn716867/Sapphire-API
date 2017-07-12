package org.sapphire.appconsole.service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.sapphire.appconsole.dao.AppDao;
import org.sapphire.appconsole.dao.DAOFactory;
import org.sapphire.appconsole.dao.LayoutDao;
import org.sapphire.appconsole.dao.WidgetDao;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;


/**
 * This is the service layer , which handles the business logic
 * The class is made as singleton, for design reasons.
 * @author deepak
 *
 */
public class SapphireService {

	private DAOFactory factory = null;
	private static SapphireService serviceInstance = null;
	private final static Logger LOG = Logger.getLogger(SapphireService.class) ;
	
	//Making the Class as Singleton. As only one instance of the object must exists.
	private SapphireService() {
		// TODO Auto-generated constructor stub
		factory = DAOFactory.getFactory(DAOFactory.MONGO);
	}
	
	/**
	 * This is the method to get the instance of the service class (SapphireService).
	 * @return SapphireService - An instance of the Service class.
	 */
	public static SapphireService getSapphireServiceInstance()
	{
		if(serviceInstance == null)
		{
			serviceInstance = new SapphireService();
		}
		
		return serviceInstance;
	}
	
	/**
	 * The service method for handling the app route search.
	 * @return searchList - The appsearch settings json.
	 * @throws Exception 
	 */
	public String appRouteSearch() throws Exception
	{
		
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::appRouteSearch::Line No 66",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		AppDao appDAO = factory.getAppDAO();
		String searchList = null;
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			searchList = mapper.writeValueAsString(appDAO.list());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Json write error in SapphireService::appRouteSearch::87",e);
			e.printStackTrace();
		}
		
		return searchList;
	}
	
	/**
	 * This is the service method for handling the widget search route
	 * @return widgetSearchList - A JSON string with the widget setting option
	 * @throws Exception
	 */
	public String widgetRouteSearch() throws Exception
	{
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::widgetRouteSearch::Line No 109",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		WidgetDao widgetDAO = factory.getWidgetDAO();
		String widgetSearchList = null;
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			widgetSearchList = mapper.writeValueAsString(widgetDAO.list());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Json write error in SapphireService::widgetRouteSearch::124",e);
			e.printStackTrace();
		}
		
		return widgetSearchList;
	}
	
	/**
	 * This is the service method for handling the layout route search
	 * @return layoutSearchList - A JSON String which contains all the layout setting options.
	 * @throws Exception
	 */
	public String layoutRouteSearch() throws Exception
	{
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::layoutRouteSearch::Line No 152",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		LayoutDao layoutDAO = factory.getLayoutDAO();
		String layoutSearchList = null;
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			layoutSearchList = mapper.writeValueAsString(layoutDAO.list());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Json write error in SapphireService::layoutRouteSearch::167",e);
			e.printStackTrace();
		}
		
		return layoutSearchList;
	}
	
	/**
	 * This is the service method for handling the widget eventoption route
	 * @return widgetEventOption - A JSON string with the widget event option
	 * @throws Exception
	 */
	public String widgetRouteEventOption() throws Exception
	{
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::widgetRouteEventOption::Line No 194",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		WidgetDao widgetDAO = factory.getWidgetDAO();
		String widgetEventOption = null;
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			widgetEventOption = mapper.writeValueAsString(widgetDAO.getEventOptions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Json write error in SapphireService::widgetRouteEventAction::209",e);
			e.printStackTrace();
		}
		
		return widgetEventOption;
	}
	
	/**
	 * This is the service method for handling the widget eventaction route
	 * @return widgetEventAction - A JSON string with the widget event action option
	 * @throws Exception
	 */
	public String widgetRouteEventAction() throws Exception
	{
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			ObjectMapper mapper = new ObjectMapper();
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::widgetRouteEventAction::Line No 236",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		WidgetDao widgetDAO = factory.getWidgetDAO();
		String widgetEventAction = null;
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			widgetEventAction = mapper.writeValueAsString(widgetDAO.getEventActions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.error("Json write error in SapphireService::widgetRouteEventAction::251",e);
			e.printStackTrace();
		}
		return widgetEventAction;
	}
	
	/**
	 * The service method for creating/updating a new app.
	 * @return JSONMessage - The appsearch settings json.
	 * @throws Exception 
	 */
	public String appRouteSave(String JSONBody, String appID) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::appRouteSave::Line No 279",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		if(isJSONValid(JSONBody) == false)
		{
			throw new WebApplicationException(400);
		}
		
		AppDao appDAO = factory.getAppDAO();
		boolean result = appDAO.update(JSONBody, appID);
		
		JSONObject reply = new JSONObject();
		if(result)
		{
			reply.put("message", "App "+appID+"has been created/updated successfully");
		}
		else
		{
			String JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while "
					+ "creating/updating app resource",500));
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		return reply.toJSONString();
	}
	
	/**
	 * The service method for creating/updating a new layout.
	 * @return JSONMessage - The appsearch settings json.
	 * @throws Exception 
	 */
	public String layoutRouteSave(String JSONBody, String layoutId) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::layoutRouteSave::Line No 336",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		if(isJSONValid(JSONBody) == false)
		{
			throw new WebApplicationException(400);
		}
		
		LayoutDao layoutdao = factory.getLayoutDAO();
		boolean result = layoutdao.update(JSONBody, layoutId);
		
		JSONObject reply = new JSONObject();
		if(result)
		{
			reply.put("message", "Layout "+layoutId+"has been created/updated successfully");
		}
		else
		{
			String JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while "
					+ "creating/updating layout resource",500));
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		return reply.toJSONString();
	}
	
	/**
	 * The service method for creating/updating a new layout.
	 * @return JSONMessage - The appsearch settings json.
	 * @throws Exception 
	 */
	public String layoutRouteDelete(String layoutId) throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		
		if(factory == null)
		{
			LOG.error("Error while instantiating the factory object");
			
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while"
																	+ "instantiating factory object",500));
			} 
			catch(Exception e)
			{
				LOG.error("Json write error in SapphireService::layoutRouteDelete::Line No 383",e);
				e.printStackTrace();
			}
			
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		LayoutDao layoutdao = factory.getLayoutDAO();
		boolean result = layoutdao.delete(layoutId);
		
		JSONObject reply = new JSONObject();
		if(result)
		{
			reply.put("message", "Layout "+layoutId+"has been Deleted successfully");
		}
		else
		{
			String JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while "
					+ "Deleting resource",500));
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
		
		return reply.toJSONString();
	}
	
	/**
	 * To check if the json string is a valid json or not.
	 * @param jsonString
	 * @return a boolean
	 */
	public static boolean isJSONValid(String jsonString) {
		try 
		{
			JSONParser parser = new JSONParser();
			parser.parse(jsonString);
			return true;
	    } 
		catch (Exception e) 
		{
			System.out.println("Gonna return false");
			return false;
	    }
	}

}
