package org.sapphire.appconsole.dao;

import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;

/**
 * The DAO Factory is used to load the dao properties file and 
 * instantiate the appropriate DAO Layer
 * @author deepak
 *
 */
public abstract class DAOFactory {

	public DAOFactory() {
		// TODO Auto-generated constructor stub
	}
	
	//The static member, coz it has to be the same for all the instances of the class.
	private static Properties __props;
	public static final int MONGO = 0;
	
	public abstract AppDao getAppDAO() throws Exception;
	public abstract LayoutDao getLayoutDAO() throws Exception;
	public abstract WidgetDao getWidgetDAO() throws Exception;
	public abstract WidgetSettingOptionDao getWidgetSettingOptionDAO();
	private final static Logger LOG = Logger.getLogger(DAOFactory.class) ;
	
	public static Properties getDAOProperties() {
		return __props;
	}
	
		// static initalizer of the dao and the related properties
		static 
		{
	        __props = new Properties();
	        try 
	        {
	        	LOG.info("Going to load dao.properties");
        		InputStream propFile = DAOFactory.class.getResourceAsStream("dao.properties");
        		__props.load(propFile);
        		propFile.close();
	        }
	        catch(Exception e)
	        {
	        	LOG.error("DAOFactory::Static block::56::Error while loading dao.properties",e);
	        	e.printStackTrace();
	        	ObjectMapper mapper = new ObjectMapper();
	        	String JsonErrorMessage = "";
				try 
				{
					JsonErrorMessage = mapper.writeValueAsString(new ErrorHandler("Unexpected error while loading dao.property file, "
							                                                       + "Please try by resending request after sometime",500));
				} 
				catch (Exception e1) {
					// TODO Auto-generated catch block
					LOG.error("Error while writting json error message object",e1);
					e1.printStackTrace();
				}
	        	throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
	        }
		}
		
		/**
		 * This method is a static method which will return the corresponding factory object depending on the type.
		 * @param type The type to get appropriate factory method.
		 * @return The corresponding implementation for the DAO Factory. 
		 */
		public static DAOFactory getFactory(int type)
		{
			switch(type)
			{
				case MONGO :
					return new MongoDAOFactory();
			}
			return null;
		}
}
