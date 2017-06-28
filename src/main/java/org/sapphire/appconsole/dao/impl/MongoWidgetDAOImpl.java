package org.sapphire.appconsole.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.sapphire.appconsole.dao.DAOFactory;
import org.sapphire.appconsole.dao.WidgetDao;
import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;
import org.sapphire.appconsole.model.EventActionType;
import org.sapphire.appconsole.model.EventOptionType;
import org.sapphire.appconsole.model.UIComponent;
import org.sapphire.appconsole.model.Widget;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

public class MongoWidgetDAOImpl extends BaseDao implements WidgetDao {
	
	/**
	 * This is the constructor for instantiating the database connection object. 
	 * @throws Exception
	 */
	public MongoWidgetDAOImpl() throws Exception
	{
		String dbName = DAOFactory.getDAOProperties().getProperty("dbName");
		String host = DAOFactory.getDAOProperties().getProperty("hostName");
		
		if(dbName == null || host == null || dbName.isEmpty() || host.isEmpty())
		{
			try 
			{
				throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,new ObjectMapper()
						                    .writeValueAsString(new ErrorHandler("Error While getting the Connection Object",500)));
			} 
			catch (Exception e) 
			{
				LOG.error("Unexpected error while throwing exception",e);
				e.printStackTrace();
				throw e;
			}
				// TODO Auto-generated catch block
		}
		
		mongodatasourceprovider = new MongoDataSourceProvider(dbName,host);
	}

	private final static Logger LOG = Logger.getLogger(MongoWidgetDAOImpl.class) ;


	public boolean save(String widgetJson) throws Exception {
		LOG.debug("WidgetDaoImpl::save object :: ", widgetJson);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Widget widget = mapper.readValue(widgetJson, Widget.class);

			if(widget.getComponentID() == null || widget.getComponentID().trim() == ""){
				widget.setComponentID(org.sapphire.appconsole.model.objectfactory.ObjectFactory.uniqueID());
			}

			String widgetString =  mapper.writeValueAsString(widget);
			mongodatasourceprovider.insertJson(COLLECTION_WIDGET, widgetString);
		}catch(Exception e){
			LOG.error("WidgetDaoImpl::save object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}



	public List<UIComponent> list() throws Exception {
		LOG.debug("WidgetDaoImpl::list called");
		List<UIComponent> uiComponents =  new java.util.ArrayList<UIComponent>();
		try {
			List<String> results  =  mongodatasourceprovider.search(COLLECTION_WIDGET);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			for (String result : results){
				Widget widget = mapper.readValue(result, Widget.class);
				LOG.info("WidgetDaoImpl component ID for list:::" + widget.getComponentID());
				uiComponents.add(widget);
			}

		}catch(Exception e){
			LOG.error("WidgetDaoImpl::list failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return uiComponents;
	}
	
	public Map<EventOptionType, String> getEventOptions() throws Exception {
		LOG.debug("WidgetDaoImpl::geteventOptions ");
		LinkedHashMap<EventOptionType, String> events = new java.util.LinkedHashMap<EventOptionType, String>();
		try {
			events.put(EventOptionType.VIEW, EVENT_OPTION_TYPE_VIEW);
			events.put(EventOptionType.EDIT, EVENT_OPTION_TYPE_EDIT);
			events.put(EventOptionType.ADD, EVENT_OPTION_TYPE_ADD);
			events.put(EventOptionType.DELETE, EVENT_OPTION_TYPE_DELETE) ;
			events.put(EventOptionType.GRAPH_VIEW, EVENT_OPTION_TYPE_GRAPH_VIEW);
			events.put(EventOptionType.SEPERATOR, "------------------------------");
			//events.put(EventOptionType.ADD_EDIT, EVENT_OPTION_TYPE_ADD_EDIT);
			events.put(EventOptionType.CHANGE, EVENT_OPTION_TYPE_CHANGE);
			
			events.put(EventOptionType.SUBSCRIBE, EVENT_OPTION_TYPE_SUBSCRIBE);

			events.put(EventOptionType.ATTACH_APPCONTEXT_VARIABLE_LISTENER, EVENT_OPTION_ATTACH_APPCONTEXT_VARIABLE_LISTENER);
			events.put(EventOptionType.SUBSCRIBE_APPCONTEXT_VARIABLE_CHANGE, EVENT_OPTION_SUBSCRIBE_APPCONTEXT_VARIABLE_CHANGE);

			

		}catch(Exception e){
			LOG.error("WidgetDaoImpl::getEventOptions failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return events;
	}
	
	public Map<EventActionType, String> getEventActions() throws Exception {
		LOG.debug("WidgetDaoImpl::geteventOptions ");
		Map<EventActionType, String> eventActions=  new java.util.HashMap<EventActionType, String>();
		try {
			eventActions.put(EventActionType.RELOAD, EVENT_ACTION_TYPE_RELOAD);
			eventActions.put(EventActionType.OPEN_PAGE, EVENT_ACTION_TYPE_OPEN_PAGE);
			eventActions.put(EventActionType.OPEN_POPUP, EVENT_ACTION_TYPE_OPEN_POPUP);
		}catch(Exception e){
			LOG.error("WidgetDaoImpl::getEventActions failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return eventActions;
	}
	
	

	public Widget get(String widgetID) throws ResourceNotFoundException, Exception {
		LOG.debug("WidgetDaoImpl::get called for component ID ", widgetID);
		Widget widget = null;
		try {
			String widgetJson = mongodatasourceprovider.getById(COLLECTION_WIDGET, widgetID);
			LOG.info("WidgetDaoImpl::get result::", widgetJson);
			
			if(widgetJson == null){
				throw new ResourceNotFoundException();
			}else{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				widget = mapper.readValue(widgetJson, Widget.class);
			}
		} catch (ResourceNotFoundException e) {
			LOG.error("WidgetDaoImpl::get method failed resource not found :: ", e);
			throw new ResourceNotFoundException(MongoWidgetDAOImpl.class.getName(), "WidgetDaoImpl get failed");
		} catch (Exception e) {
			LOG.error("WidgetDaoImpl::get method failed with exception :: ", e);
			throw new Exception(e.getMessage());
		}

		return widget;
	}

	public boolean update (String widgetJson, String Id) throws Exception {
		LOG.debug("WidgetDaoImpl::get called update :: " + Id);
		try {
			String widgetId = Id.substring(1);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Widget widget = mapper.readValue(widgetJson, Widget.class);

			if(mongodatasourceprovider.getById(COLLECTION_WIDGET, widgetId)!= null){
				LOG.info(MongoWidgetDAOImpl.class.getName() + "::update found Widget Id" , widgetId);
				widget.setComponentID(widgetId);
				mongodatasourceprovider.update(COLLECTION_WIDGET, mapper.writeValueAsString(widget), widgetId);
			}
			else {
				LOG.info(MongoWidgetDAOImpl.class.getName(),  "Inserting new record" );
				widget.setComponentID(widgetId);
				String widgetString =  mapper.writeValueAsString(widget);
				mongodatasourceprovider.insertJson(COLLECTION_WIDGET, widgetString);
			}

		}catch(Exception e){
			LOG.error("WidgetDaoImpl::update got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}

	public boolean delete(String Id) throws Exception {
		LOG.debug("WidgetDaoImpl::delete method called with Id :: ", Id);
		try {
			return mongodatasourceprovider.delete(COLLECTION_WIDGET, Id);
		}catch (Exception e) {
			LOG.error("WidgetDaoImpl::delete method failed for Id :: ", Id);
			throw new Exception(e.getMessage());
		}

	}

}
