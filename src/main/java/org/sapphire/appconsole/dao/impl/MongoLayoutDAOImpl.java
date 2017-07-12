package org.sapphire.appconsole.dao.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.sapphire.appconsole.dao.DAOFactory;
import org.sapphire.appconsole.dao.LayoutDao;
import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.model.UIComponent;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

public class MongoLayoutDAOImpl extends BaseDao implements LayoutDao {
	
	/**
	 * This is the constructor for instantiating the database connection object. 
	 * @throws Exception
	 */
	public MongoLayoutDAOImpl() throws Exception
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

	private final static Logger LOG = Logger.getLogger(MongoLayoutDAOImpl.class) ;

	public boolean save(String layoutJson) throws Exception {
		LOG.debug("LayoutDaoImpl::save object :: ", layoutJson);
		try {
			ObjectMapper mapper = new ObjectMapper();

			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Layout layout = mapper.readValue(layoutJson, Layout.class);

			if(layout.getComponentID() == null || layout.getComponentID().trim() == ""){
				layout.setComponentID(org.sapphire.appconsole.model.objectfactory.ObjectFactory.uniqueID());
			}

			String layoutString =  mapper.writeValueAsString(layout);
			mongodatasourceprovider.insertJson(COLLECTION_LAYOUT, layoutString);

		}catch(Exception e){
			LOG.error("LayoutDaoImpl::save object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}

	public List<UIComponent> list() throws Exception {
		LOG.debug("LayoutDaoImpl::list called");
		List<UIComponent> uiComponents =  new java.util.ArrayList<UIComponent>();
		try {

			List<String> results  =  mongodatasourceprovider.search(COLLECTION_LAYOUT);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			for (String result : results){
				Layout layout = mapper.readValue(result, Layout.class);
				LOG.info("LayoutDaoImpl component ID for list:::" + layout.getComponentID());
				uiComponents.add(layout);
			}
		}catch(Exception e){
			LOG.error("LayoutDaoImpl::list failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return uiComponents;
	}

	public Layout get(String layoutID) throws ResourceNotFoundException, Exception {
		LOG.debug("LayoutDaoImpl::get called for component ID ", layoutID);
		Layout layout = null;
		try {
			String layoutJson = mongodatasourceprovider.getById(COLLECTION_LAYOUT, layoutID);

			LOG.info("LayoutDaoImpl::get result::", layoutJson);
			
			if(layoutJson == null){
				throw new ResourceNotFoundException();
			}else{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				layout = mapper.readValue(layoutJson, Layout.class);
			}
		} catch (ResourceNotFoundException e) {
			LOG.error("LayoutDaoImpl::get method failed resource not found :: ", e);
			throw new ResourceNotFoundException(MongoLayoutDAOImpl.class.getName(), "LayoutDaoImpl get failed");
		} catch (Exception e) {
			LOG.error("LayoutDaoImpl::get method failed with exception :: ", e);
			throw new Exception(e.getMessage());
		}

		return layout;
	}

	public boolean update (String layoutJson, String Id) throws Exception {
		LOG.debug("LayoutDaoImpl::get called update :: " + Id);
		try {
			//String layoutID = Id.substring(1);
			String layoutID = Id.substring(0); //Changing this while porting from jboss fuse to apache jersey java api.
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			Layout layout = mapper.readValue(layoutJson, Layout.class);

			if(mongodatasourceprovider.getById(COLLECTION_LAYOUT, layoutID)!= null){
				LOG.info(MongoLayoutDAOImpl.class.getName() + "::update found LayoutDaoImpl Id" , layoutID);
				layout.setComponentID(layoutID);
				mongodatasourceprovider.update(COLLECTION_LAYOUT, layoutJson, layoutID);
			}
			else{
				LOG.info(MongoLayoutDAOImpl.class.getName(),  "Inserting new record" );
				layout.setComponentID(layoutID);
				String layoutString =  mapper.writeValueAsString(layout);
				mongodatasourceprovider.insertJson(COLLECTION_LAYOUT, layoutString);
			}
		}catch(Exception e)	{
			LOG.error("LayoutDaoImpl::update object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}

	public boolean delete(String Id) throws Exception {
		LOG.debug("LayOutDaoImpl::delete method called with Id :: ", Id);
		try {
			return mongodatasourceprovider.delete(COLLECTION_LAYOUT, Id);
		}catch (Exception e) {
			LOG.error("LayOutDaoImpl::delete method failed for Id :: ", Id);
			throw new Exception(e.getMessage());
		}

	}

}
