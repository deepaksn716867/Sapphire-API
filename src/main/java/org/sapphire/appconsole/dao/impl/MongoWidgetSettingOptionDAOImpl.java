package org.sapphire.appconsole.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.sapphire.appconsole.dao.WidgetSettingOptionDao;
import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.model.OperatorType;
import org.sapphire.appconsole.model.Setting;
import org.sapphire.appconsole.model.WidgetDataType;
import org.sapphire.appconsole.model.WidgetSettingOption;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

public class MongoWidgetSettingOptionDAOImpl extends BaseDao implements WidgetSettingOptionDao {

	private final static Logger LOG = Logger.getLogger(MongoWidgetSettingOptionDAOImpl.class) ;

	public boolean save(String widgetSettingOptionJson) throws Exception {

		LOG.debug("WidgetSettingOptionDaoImpl::save object :: ", widgetSettingOptionJson);
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			WidgetSettingOption widgetSettingOption = mapper.readValue(widgetSettingOptionJson, WidgetSettingOption.class);

			if(widgetSettingOption.getId() == null || widgetSettingOption.getId().trim() == ""){
				widgetSettingOption.setId(org.sapphire.appconsole.model.objectfactory.ObjectFactory.uniqueID());
			}

			String widgetSettingOptionString =  mapper.writeValueAsString(widgetSettingOption);
			mongodatasourceprovider.insertJson(COLLECTION_WIDGET_SETTING_OPTION, widgetSettingOptionString);
		}catch(Exception e)	{
			LOG.error("WidgetSettingOptionDaoImpl::save object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}

	public List<WidgetSettingOption> list() throws Exception {
		LOG.debug("WidgetSettingOptionDaoImpl::list");
		List<WidgetSettingOption> widgetSettingOptions =  new java.util.ArrayList<WidgetSettingOption>();
		try {
			List<String> results  =  mongodatasourceprovider.search(COLLECTION_WIDGET_SETTING_OPTION);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			for (String result : results){
				WidgetSettingOption widgetSettingOption = mapper.readValue(result, WidgetSettingOption.class);
				widgetSettingOptions.add(widgetSettingOption);
			}
		}catch(Exception e){
			LOG.error("WidgetSettingOptionDaoImpl::list failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return widgetSettingOptions;

	}

	public WidgetSettingOption get(String widgetSettingOptionID) throws ResourceNotFoundException, Exception{
		WidgetSettingOption widgetSettingOption = null;
		try {
			System.out.println("WidgetSettingOptionDaoImpl Get called" + widgetSettingOptionID);
			String widgetOptionSettingJson = mongodatasourceprovider.getById(COLLECTION_WIDGET_SETTING_OPTION, widgetSettingOptionID);

			if(widgetOptionSettingJson == null){
				throw new ResourceNotFoundException();
			}else{
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				widgetSettingOption = mapper.readValue(widgetOptionSettingJson, WidgetSettingOption.class);
			}
		} catch (ResourceNotFoundException e) {
			LOG.error("WidgetSettingOptionDaoImpl::get method failed resource not found :: ", e);
			throw new ResourceNotFoundException(MongoWidgetSettingOptionDAOImpl.class.getName(), "WidgetSettingOptionDaoImpl get failed");
		} catch (Exception e) {
			LOG.error("WidgetSettingOptionDaoImpl::get method failed with exception :: ", e);
			throw new Exception(e.getMessage());
		}

		return widgetSettingOption;
	}

	public boolean update (String widgetSettingOptionJson, String Id) throws Exception {
		LOG.debug(MongoWidgetDAOImpl.class.getName(), "update :: " + Id);
		try {
			String widgetSettingOptionId = Id.substring(1);
			LOG.debug("WidgetSettingOptionDaoImpl ID :::: " + widgetSettingOptionId);

			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			WidgetSettingOption widgetsettingOption = mapper.readValue(widgetSettingOptionJson, WidgetSettingOption.class);

			if(mongodatasourceprovider.getById(COLLECTION_WIDGET_SETTING_OPTION, widgetSettingOptionId)!= null){
				LOG.info(MongoWidgetDAOImpl.class.getName() + "::update found Widget Id" , widgetSettingOptionId);
				widgetsettingOption.setId(widgetSettingOptionId);
				mongodatasourceprovider.update(COLLECTION_WIDGET_SETTING_OPTION, widgetSettingOptionJson, widgetSettingOptionId);
			}
			else{
				LOG.info(MongoWidgetDAOImpl.class.getName(),  "Inserting new record" );
				widgetsettingOption.setId(widgetSettingOptionId);
				String widgetSettingOptionString =  mapper.writeValueAsString(widgetsettingOption);
				mongodatasourceprovider.insertJson(COLLECTION_WIDGET_SETTING_OPTION, widgetSettingOptionString);
			}

		}catch(Exception e){
			LOG.error("WidgetSettingOptionDaoImpl::update object got error :: ", e);
			throw new Exception(e.getMessage());
		}

		return true;
	}
	
	public Map<WidgetDataType, List<String>> getSupportedOperators() throws Exception {
		LOG.debug("WidgetDaoImpl::geteventOptions ");
		Map<WidgetDataType, List<String>> operators = new java.util.HashMap<WidgetDataType, List<String>>();
		try {
			List<String> booleanList=new ArrayList<String>();
			List<String> stringList=new ArrayList<String>();
			List<String> dateList=new ArrayList<String>();
			
			
			List<String> CodeList=new ArrayList<String>();

			
			booleanList.add(OperatorType.EQUAL.toString());
			booleanList.add(OperatorType.NOT_EQUAL.toString());
			
			stringList.add(OperatorType.EXACT_MATCH.toString());
			stringList.add(OperatorType.START_WITH.toString());
			stringList.add(OperatorType.END_WITH.toString());
			stringList.add(OperatorType.LIKE.toString());
			stringList.add(OperatorType.NOT_LIKE.toString());
			stringList.add(OperatorType.NOT_EQUAL.toString());

			
			dateList.add(OperatorType.LESS_THAN.toString());
			dateList.add(OperatorType.LESS_THAN_EQUAL.toString());
			dateList.add(OperatorType.GREATER_THAN.toString());
			dateList.add(OperatorType.GREATER_THAN_EQUAL.toString());
			dateList.add(OperatorType.EQUAL.toString());
			dateList.add(OperatorType.AT_LEAST.toString());
			dateList.add(OperatorType.NO_MORE_THAN.toString());
			
			CodeList.add(OperatorType.IS_A.toString());
			CodeList.add(OperatorType.IS_ASSOCIATED_WITH_FLAG.toString());
			CodeList.add(OperatorType.IS_ASSOCIATED_WITH_CODE.toString());

		
			operators.put(WidgetDataType.BOOLEAN, booleanList);
			operators.put(WidgetDataType.STRING, stringList);
			operators.put(WidgetDataType.DATE, dateList);
			operators.put(WidgetDataType.CODE, CodeList);

			
		}catch(Exception e){
			LOG.error("WidgetDaoImpl::getEventOptions failed :: ", e);
			throw new Exception(e.getMessage());
		}

		return operators;
	}


	public List<Setting> getSetting(String widgetType) throws Exception {
		LOG.debug("WidgetSettingOptionDaoImpl::getsetting called");
		List<Setting> widgetSetting =  new java.util.ArrayList<Setting>();
		try {
			List<String> results  =  mongodatasourceprovider.search(COLLECTION_WIDGET_SETTING_OPTION);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			for (String result : results){
				
				WidgetSettingOption widgetSettingOption = mapper.readValue(result, WidgetSettingOption.class);
				if(widgetSettingOption.getWidgetType().toString().equals(widgetType)){
					return widgetSettingOption.getSettings();
				}
			}
		}catch(Exception e){
			LOG.error("WidgetSettingOptionDaoImpl::getSetting failed :: ", e);
			throw new Exception(e.getMessage());
		}
		
		return widgetSetting;
	}

	public boolean delete(String Id) throws Exception {
		LOG.debug("WidgetSettingOptionDaoImpl::delete method called with Id :: ", Id);
		try {
			return mongodatasourceprovider.delete(COLLECTION_WIDGET_SETTING_OPTION, Id);
		}catch (Exception e) {
			LOG.error("WidgetSettingOptionDaoImpl::delete method failed for Id :: ", Id);
			throw new Exception(e.getMessage());
		}

	}

}
