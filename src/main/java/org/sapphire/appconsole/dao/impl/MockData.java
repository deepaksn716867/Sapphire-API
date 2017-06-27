package org.sapphire.appconsole.dao.impl;

import org.sapphire.appconsole.model.objectfactory.ObjectFactory;
import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.model.Filter;
import org.sapphire.appconsole.model.FilterCriteria;
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.model.OperatorType;
import org.sapphire.appconsole.model.Option;
import org.sapphire.appconsole.model.Page;
import org.sapphire.appconsole.model.Setting;
import org.sapphire.appconsole.model.SettingType;
import org.sapphire.appconsole.model.Widget;
import org.sapphire.appconsole.model.WidgetDataType;
import org.sapphire.appconsole.model.WidgetDateFilterOption;
import org.sapphire.appconsole.model.WidgetSettingOption;
import org.sapphire.appconsole.model.WidgetSettingType;
import org.sapphire.appconsole.model.WidgetType;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;


public class MockData {
	
	private static final String APPID = "A111";
	public MongoDataSourceProvider mongodatasourceprovider;
	
	MockData(){
		mongodatasourceprovider = new MongoDataSourceProvider("appdb","localhost");
		//mongodatasourceprovider.setDbName("appdb");
		//mongodatasourceprovider.setHost("localhost");
	}
	
	public void appInsert() throws Exception {

		MongoAppDAOImpl appDAO = new MongoAppDAOImpl();
		appDAO.setMongodatasourceprovider(mongodatasourceprovider);
		
		App app = ObjectFactory.createApp("Demo APP");
		app.setComponentID(APPID);
		
		Layout layoutPage1 = ObjectFactory.createRowLayout();
		
		
		Layout layoutPage2 = ObjectFactory.createGridLayout10X2();
		layoutPage2.getContainer().getTables().get(0).getRows().get(0).getCells().get(1).getWidgets().add(ObjectFactory.createWidget(WidgetType.MEDICATION));
		
		Page page1 =  ObjectFactory.createPage("Home Page");
		page1.setHomePage(true);
		page1.setLayout(layoutPage1);
		
		
		Page page2 =  ObjectFactory.createPage("Detail Page");
		page2.setHomePage(false);
		page2.setLayout(layoutPage2);
		
		app.getPages().add(page1);
		app.getPages().add(page2);
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String appString =  mapper.writeValueAsString(app);
		
		System.out.println("App Jsn :: " + appString);
		appDAO.save(appString);
	}
	
	public static App getApp() throws Exception {

		MongoAppDAOImpl appDAO = new MongoAppDAOImpl();
		return appDAO.get(APPID);
	}
	
	public void layoutInsert10X2() throws Exception {

		MongoLayoutDAOImpl layoutDAO = new MongoLayoutDAOImpl();
		layoutDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Layout layout = ObjectFactory.createGridLayout10X2();
		//layout.getContainer().getTables().get(0).getRows().get(0).getCells().get(1).getWidgets().add(ObjectFactory.createWidget(WidgetType.MEDICATION));

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String layoutJson =  mapper.writeValueAsString(layout);
		
		layoutDAO.save(layoutJson);
	}
	
	public void layoutInsert10X3() throws Exception {

		MongoLayoutDAOImpl layoutDAO = new MongoLayoutDAOImpl();
		layoutDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Layout layout = ObjectFactory.createGridLayout10X3();
		//layout.getContainer().getTables().get(0).getRows().get(0).getCells().get(1).getWidgets().add(ObjectFactory.createWidget(WidgetType.MEDICATION));

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String layoutJson =  mapper.writeValueAsString(layout);
		
		layoutDAO.save(layoutJson);
	}
	
	public void creatFlowLayout() throws Exception {

		MongoLayoutDAOImpl layoutDAO = new MongoLayoutDAOImpl();
		layoutDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Layout layout = ObjectFactory.creatFlowLayout();
		//layout.getContainer().getTables().get(0).getRows().get(0).getCells().get(1).getWidgets().add(ObjectFactory.createWidget(WidgetType.MEDICATION));

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String layoutJson =  mapper.writeValueAsString(layout);
		
		layoutDAO.save(layoutJson);
	}
	public void layoutInsertRowLayout() throws Exception {

		MongoLayoutDAOImpl layoutDAO = new MongoLayoutDAOImpl();
		layoutDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Layout layout = ObjectFactory.createRowLayout();
		//layout.getContainer().getTables().get(0).getRows().get(0).getCells().get(1).getWidgets().add(ObjectFactory.createWidget(WidgetType.MEDICATION));

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String layoutJson =  mapper.writeValueAsString(layout);
		
		layoutDAO.save(layoutJson);
	}
	
	public void layoutInsert() throws Exception {
		creatFlowLayout();
		layoutInsertRowLayout();
		layoutInsert10X2();
		layoutInsert10X3();
	}
	
	public void widgetInsert() throws Exception{
		widgetInsert(WidgetType.PATIENTS);
		widgetInsert(WidgetType.ENCOUNTER);
		widgetInsert(WidgetType.MEDICATION);
		widgetInsert(WidgetType.VITALSIGNS);
		widgetInsert(WidgetType.PROBLEMS);
		widgetInsert(WidgetType.RECOMMENDATION);
		widgetInsert(WidgetType.LABTEST);
		widgetInsert(WidgetType.LABPANEL);

		widgetInsert(WidgetType.ADVERSEREACTION);
		widgetInsert(WidgetType.SMARTONFHIR);
		widgetInsert(WidgetType.ALLERGYINTOLERANCE);
	}
	public void widgetInsert(WidgetType type) throws Exception {

		MongoWidgetDAOImpl widgetDAO = new MongoWidgetDAOImpl();
		widgetDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Widget widget = ObjectFactory.createWidget(type);
		
		
		/*Event event = ObjectFactory.createEvent();
		event.setEventActionType(EventActionType.RELOAD);
		event.setEventType(EventOptionType.ADD);
		event.getValues().add("Test Value");
		widget.getEvents().add(event);*/
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetJson =  mapper.writeValueAsString(widget);
		
		
		widgetDAO.save(widgetJson);

		
	}
	
	public void widgetPatientInsert() throws Exception {

		MongoWidgetDAOImpl widgetDAO = new MongoWidgetDAOImpl();
		widgetDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Widget widget = ObjectFactory.createWidget(WidgetType.PATIENTS);
		
		Filter nameFilter = ObjectFactory.createFilter();
		nameFilter.setName("name");
		nameFilter.setValue("Peter");
		nameFilter.setOperator(OperatorType.EQUAL);
		nameFilter.setDataType(WidgetDataType.STRING);
		
		
		
		Filter genderFilter = ObjectFactory.createFilter();
		genderFilter.setName("gender");
		genderFilter.setValue("Male");
		genderFilter.setOperator(OperatorType.EQUAL);
		nameFilter.setDbColumnName("resource.gender.coding.display");
		nameFilter.setDataType(WidgetDataType.STRING);

		FilterCriteria fc = ObjectFactory.createFilterCriteria();
		fc.getFilters().add(nameFilter);
		fc.getFilters().add(genderFilter);
	
		widget.getFilterCriterias().add(fc);
		
		widget.setEnabledDataFilter(WidgetDateFilterOption.FILTER);
		
		///widget.getNamedQueries().add("Older Patient");
		//widget.getNamedQueries().add("Young Patient");
		//widget.getNamedQueries().add("Heart Patient");
			
		/*Event event = ObjectFactory.createEvent();
		event.setEventActionType(EventActionType.RELOAD);
		event.setEventType(EventOptionType.ADD);
		event.getValues().add("Test Value");
		widget.getEvents().add(event);*/
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetJson =  mapper.writeValueAsString(widget);
		
		
		widgetDAO.save(widgetJson);

		
	}
	

	
	
	
	public void widgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		
		Setting settingWidth = ObjectFactory.createSetting();
		settingWidth.setLabel("Width");
		settingWidth.setType(SettingType.TEXT);
		settingWidth.setDefaultValue("100");
		
		Setting settingHeight =  ObjectFactory.createSetting();
		settingHeight.setLabel("Height");
		settingHeight.setType(SettingType.TEXT);
		settingHeight.setDefaultValue("100");
		
		Setting settingTitle =  ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue("AllergyIntolerance");
		
		Option option1 = new Option();
		option1.setKey("action");
		option1.setValue("Action");
		
		Option option2 = new Option();
		option2.setKey("date");
		option2.setValue("Date");
		
		Option defaultOption = new Option();
		defaultOption.setKey("all");
		defaultOption.setValue("all");
		
		Setting settingShowColumns = ObjectFactory.createSetting();
		settingShowColumns.setLabel("Title");
		settingShowColumns.setType(SettingType.TEXT);
		settingShowColumns.setDefaultValue("AllergyIntolerance");
		settingShowColumns.getOptions().add(option1);
		settingShowColumns.getOptions().add(option2);
		settingShowColumns.setDefaultOption(defaultOption);
		
		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);
		widgetSettingOption.getSettings().add(settingTitle);
		widgetSettingOption.getSettings().add(settingShowColumns);


		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);
		
		widgetSettingOptionDAO.save(widgetsettingOptionJson);

		
	}
	
	public void addWidthWidgetsettingOption(WidgetSettingOption widgetSettingOption, String title){
		Setting settingTitle = ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue(title);
		settingTitle.setWidgetSettingType(WidgetSettingType.PROPERTY);
		
		Setting settingWidth = ObjectFactory.createSetting();
		settingWidth.setLabel("Width");
		settingWidth.setType(SettingType.TEXT);
		settingWidth.setDefaultValue("100");
		settingWidth.setWidgetSettingType(WidgetSettingType.PROPERTY);
		
		Setting settingHeight =  ObjectFactory.createSetting();
		settingHeight.setLabel("Height");
		settingHeight.setType(SettingType.TEXT);
		settingHeight.setDefaultValue("100");
		settingHeight.setWidgetSettingType(WidgetSettingType.PROPERTY);
		
		widgetSettingOption.getSettings().add(settingTitle);
		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);
		
	}
	public void patientWidgetsettingOptionInsert() throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(WidgetType.PATIENTS);
		addWidthWidgetsettingOption(widgetSettingOption, "Patient");
	
	
		Setting nameFilter = ObjectFactory.createSetting();
		nameFilter.setLabel("name");
		nameFilter.setType(SettingType.TEXT);
		nameFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		
		Setting ageFilter = ObjectFactory.createSetting();
		ageFilter.setLabel("Age");
		ageFilter.setType(SettingType.SELECT);
		ageFilter.setWidgetSettingType(WidgetSettingType.FILTER);
	
		
		Option ageFilterOption1 = new Option();
		ageFilterOption1.setKey("10");
		ageFilterOption1.setValue("10");
				
		Option ageFilterOption2 = new Option();
		ageFilterOption2.setKey("20");
		ageFilterOption2.setValue("20");
		
		Option ageFilterOption3 = new Option();
		ageFilterOption3.setKey("30");
		ageFilterOption3.setValue("30");
		
		ageFilter.getOptions().add(ageFilterOption1);
		ageFilter.getOptions().add(ageFilterOption2);
		ageFilter.getOptions().add(ageFilterOption3);
		
		widgetSettingOption.getSettings().add(ageFilter);
		
		Setting showColumns = ObjectFactory.createSetting();
		showColumns.setLabel("Show Data");
		showColumns.setType(SettingType.CHECKBOX);
	
		Option showColumnsOption1 = new Option();
		showColumnsOption1.setKey("Name");
		showColumnsOption1.setValue("Name");
				
		Option showColumnsOption2 = new Option();
		showColumnsOption2.setKey("Age");
		showColumnsOption2.setValue("Age");
		
		Option showColumnsOption3 = new Option();
		showColumnsOption3.setKey("DOB");
		showColumnsOption3.setValue("DOB");
		
		Option showColumnsOption4 = new Option();
		showColumnsOption4.setKey("Gender");
		showColumnsOption4.setValue("Gender");
		
		showColumns.getOptions().add(showColumnsOption1);
		showColumns.getOptions().add(showColumnsOption2);
		showColumns.getOptions().add(showColumnsOption3);
		showColumns.getOptions().add(showColumnsOption4);
		
		widgetSettingOption.getSettings().add(showColumns);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);
		
		widgetSettingOptionDAO.save(widgetsettingOptionJson);

		
	}

	
	public void widgetsettingOptionFilterInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		
		Setting settingFilter = ObjectFactory.createSetting();
		settingFilter.setLabel("Last Name");
		settingFilter.setType(SettingType.TEXT);
		settingFilter.setDefaultValue("");
		settingFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		
		Setting settingWidth = ObjectFactory.createSetting();
		settingWidth.setLabel("Width");
		settingWidth.setType(SettingType.TEXT);
		settingWidth.setDefaultValue("100");
		
		Setting settingHeight =  ObjectFactory.createSetting();
		settingHeight.setLabel("Height");
		settingHeight.setType(SettingType.TEXT);
		settingHeight.setDefaultValue("100");
		
		Setting settingTitle =  ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue("Medications");
		
		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);
		widgetSettingOption.getSettings().add(settingTitle);
		widgetSettingOption.getSettings().add(settingFilter);
		

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);
		
		widgetSettingOptionDAO.save(widgetsettingOptionJson);

		
	}

	
	/**
	 * @throws Exception
	 */
	private void widgetsettingOptionInsert() throws Exception{
		//widgetsettingOptionInsert(WidgetType.MEDICATION);
		patientWidgetsettingOptionInsert();
		//widgetsettingOptionFilterInsert(WidgetType.MEDICATION);
		//widgetsettingOptionInsert(WidgetType.PATIENTS);
		widgetsettingOptionFilterInsert(WidgetType.PATIENTS);
		widgetsettingOptionInsert(WidgetType.VITALSIGNS);
		widgetsettingOptionInsert(WidgetType.PROBLEMS);
		widgetsettingOptionInsert(WidgetType.RECOMMENDATION);
		widgetsettingOptionInsert(WidgetType.LABTEST);
		
	}
	public static void main(String[] args) throws Exception {
		
		MockData mockData = new MockData();
		//mockData.appInsert();
		//mockData.layoutInsert();
		//mockData.widgetInsert(WidgetType.LAUNCHAPP);
		//mockData.widgetInsert(WidgetType.TODOLIST);
		//mockData.widgetInsert(WidgetType.RECOMMENDATION);
		//mockData.widgetInsert(WidgetType.SMARTONFHIR);
		mockData.widgetInsert(WidgetType.ALLERGYINTOLERANCE);
		//mockData.widgetsettingOptionInsert(WidgetType.ALLERGYINTOLERANCE);
		//mockData.widgetInsert();
		//mockData.widgetsettingOptionInsert();
		
		//System.out.println(MockData.getApp().getComponentID());
		
		
	}

}
