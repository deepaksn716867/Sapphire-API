package org.sapphire.appconsole.dao.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.sapphire.appconsole.dao.AppDao;
import org.sapphire.appconsole.dao.DAOFactory;
import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;
import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.model.UIComponent;

public class MongoAppDAOImpl extends BaseDao implements AppDao {
	
	public MongoAppDAOImpl() throws Exception
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
	
	private final static Logger LOG = Logger.getLogger(MongoAppDAOImpl.class) ;
	
	public boolean save(String appJson) throws Exception {
		LOG.debug("AppDaoImpl::save  object :: ", appJson);
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			App app = mapper.readValue(appJson, App.class);
			
			if(app.getComponentID() == null || app.getComponentID().trim() == ""){
				app.setComponentID(org.sapphire.appconsole.model.objectfactory.ObjectFactory.uniqueID());
			}
			
			String appString =  mapper.writeValueAsString(app);
			mongodatasourceprovider.insertJson(COLLECTION_APP, appString);
		}catch(Exception e)	{
			LOG.error("AppImpl::save object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}

	public List<UIComponent> list() throws Exception {
		LOG.debug("AppDaoImpl::list called");
		List<UIComponent> uiComponents =  new java.util.ArrayList<UIComponent>();
		try {
			List<String> results  =  mongodatasourceprovider.search(COLLECTION_APP);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			
			for (String result : results){
				App appObj = mapper.readValue(result, App.class);
				LOG.info("App component ID for list:::" + appObj.getComponentID());
				uiComponents.add(appObj);
			}

		}catch(Exception e){
			LOG.error("AppImpl::list failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return uiComponents;
	}

	public App get(String appid) throws ResourceNotFoundException, Exception {
		LOG.debug("AppDaoImpl::get called for component ID ", appid);
		App app = null;
		try {
			String appJson = mongodatasourceprovider.getById(COLLECTION_APP, appid);
			LOG.info("App get result:::" + appJson);
			
			if(appJson == null){
				throw new ResourceNotFoundException();
			}else{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				app = mapper.readValue(appJson, App.class);
			}
		} catch (ResourceNotFoundException e) {
			LOG.error("AppDaoImpl::get method failed resource not found :: ", e);
			throw new ResourceNotFoundException(MongoAppDAOImpl.class.getName(), "AppDaoImpl get failed");
		} catch (Exception e) {
			LOG.error("AppDaoImpl::get method failed with exception :: ", e);
			throw new Exception(e.getMessage());
		}
		
		return app;
	}
	
	public boolean update(String appJson, String Id) throws Exception {
		LOG.debug("AppDaoImpl::update called for component ID ",  Id);
		try {
			//String appID = Id.substring(1); // In Apache camel , the string had a leading '/' but jersey handles it differently.
			String appID = Id.substring(0);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			App app = mapper.readValue(appJson, App.class);
			
			if(mongodatasourceprovider.getById(COLLECTION_APP, appID)!= null){
				LOG.info(MongoAppDAOImpl.class.getName() + "::update found App Id" , appID);
				app.setComponentID(appID);
				mongodatasourceprovider.update(COLLECTION_APP, appJson, appID);
			}
			else{
				LOG.info(MongoAppDAOImpl.class.getName(),  "Inserting new record" );
				app.setComponentID(appID);
				String appString =  mapper.writeValueAsString(app);
				mongodatasourceprovider.insertJson(COLLECTION_APP, appString);
			}

		}catch(Exception e){
			LOG.error("AppDaoImpl::update AppDaoImpl object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}
	
	public boolean delete(String Id) throws Exception {
		LOG.debug("AppDaoImpl::delete method called for Id :: ", Id);
		try {
			return mongodatasourceprovider.delete(COLLECTION_APP, Id);
		}catch (Exception e) {
			LOG.error("AppDaoImpl::delete method failed for Id :: ", Id);
			throw new Exception(e.getMessage());
		}
	}

}
