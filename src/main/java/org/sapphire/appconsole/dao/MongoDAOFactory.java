/**
 * 
 */
package org.sapphire.appconsole.dao;

import org.sapphire.appconsole.dao.impl.MongoAppDAOImpl;
import org.sapphire.appconsole.dao.impl.MongoLayoutDAOImpl;
import org.sapphire.appconsole.dao.impl.MongoWidgetDAOImpl;
import org.sapphire.appconsole.dao.impl.MongoWidgetSettingOptionDAOImpl;

/**
 * This is the DAO layer for the MongoDB 
 * @author deepak
 *
 */
public class MongoDAOFactory extends DAOFactory{

	/**
	 * 
	 */
	public MongoDAOFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public AppDao getAppDAO() throws Exception{
		// TODO Auto-generated method stub
		return new MongoAppDAOImpl();
	}

	@Override
	public LayoutDao getLayoutDAO() throws Exception{
		// TODO Auto-generated method stub
		return new MongoLayoutDAOImpl();
	}

	@Override
	public WidgetDao getWidgetDAO() throws Exception{
		// TODO Auto-generated method stub
		return new MongoWidgetDAOImpl();
	}

	@Override
	public WidgetSettingOptionDao getWidgetSettingOptionDAO() {
		// TODO Auto-generated method stub
		return new MongoWidgetSettingOptionDAOImpl();
		
	}

}
