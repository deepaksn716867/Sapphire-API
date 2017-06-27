package org.sapphire.appconsole.dao.impl;

import org.sapphire.appconsole.model.objectfactory.ObjectFactory;
import org.sapphire.appconsole.model.AgeType;
import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.model.NameQueryParam;
import org.sapphire.appconsole.model.Option;
import org.sapphire.appconsole.model.Page;
import org.sapphire.appconsole.model.Setting;
import org.sapphire.appconsole.model.SettingType;
import org.sapphire.appconsole.model.Widget;
import org.sapphire.appconsole.model.WidgetDataType;
import org.sapphire.appconsole.model.WidgetSettingOption;
import org.sapphire.appconsole.model.WidgetSettingType;
import org.sapphire.appconsole.model.WidgetType;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;


public class WidgetSettingOptionMockData {

	private static final String APPID = "A111";
	public MongoDataSourceProvider mongodatasourceprovider;

	WidgetSettingOptionMockData(){
		mongodatasourceprovider = new MongoDataSourceProvider("appdb","localhost");
		//mongodatasourceprovider.setDbName("appdb");
		//mongodatasourceprovider.setHost("localhost");
		//mongodatasourceprovider.setHost("ec2-54-68-101-106.us-west-2.compute.amazonaws.com");
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
		/*widgetInsert(WidgetType.PATIENTS);
		widgetInsert(WidgetType.ENCOUNTER);
		widgetInsert(WidgetType.MEDICATION);
		widgetInsert(WidgetType.VITALSIGNS);
		widgetInsert(WidgetType.PROBLEMS);
		widgetInsert(WidgetType.RECOMMENDATION);
		widgetInsert(WidgetType.LABTEST);*/
		widgetInsert(WidgetType.ADVERSEREACTION);
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
	
	
	public void launchappsettingOptionInsert(WidgetType type) throws Exception {

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
		settingHeight.setDefaultValue("AUTO");

		Setting settingTitle =  ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue("Launch App");


		Setting settingappURL =  ObjectFactory.createSetting();
		settingappURL.setLabel("App URL");
		settingappURL.setType(SettingType.TEXT);
		settingappURL.setDefaultValue("http://localhost");


		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);
		widgetSettingOption.getSettings().add(settingTitle);
		widgetSettingOption.getSettings().add(settingappURL);


		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);


	}
	
	
	public void todolistappsettingOptionInsert(WidgetType type) throws Exception {

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
		settingHeight.setDefaultValue("AUTO");

		Setting settingTitle =  ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue("ToDo List");


		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);
		widgetSettingOption.getSettings().add(settingTitle);
	

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);


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
		settingHeight.setDefaultValue("AUTO");

		Setting settingTitle =  ObjectFactory.createSetting();
		settingTitle.setLabel("Title");
		settingTitle.setType(SettingType.TEXT);
		settingTitle.setDefaultValue("Medications");

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
		settingShowColumns.setType(SettingType.CHECKBOX);
		settingShowColumns.setDefaultValue("Medications");
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
		settingHeight.setDefaultValue("AUTO");
		settingHeight.setWidgetSettingType(WidgetSettingType.PROPERTY);

		widgetSettingOption.getSettings().add(settingTitle);
		widgetSettingOption.getSettings().add(settingWidth);
		widgetSettingOption.getSettings().add(settingHeight);

	}
	public void patientWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "Patient");
		
		
		Setting nameFilter = ObjectFactory.createSetting();
		nameFilter.setLabel("Patient Name");
		nameFilter.setDataType(WidgetDataType.STRING);
		nameFilter.setType(SettingType.TEXT);
		nameFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		nameFilter.setDbColumnName("resource.name.given");
		nameFilter.setApiParameterName("name");



		Setting givenNameFilter = ObjectFactory.createSetting();
		givenNameFilter.setLabel("Patient Givenname");
		givenNameFilter.setDataType(WidgetDataType.STRING);
		givenNameFilter.setType(SettingType.TEXT);
		givenNameFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		givenNameFilter.setDbColumnName("resource.name.given");
		givenNameFilter.setApiParameterName("given");

		Setting familyNameFilter = ObjectFactory.createSetting();
		familyNameFilter.setLabel("Patient Familyname");
		familyNameFilter.setDataType(WidgetDataType.STRING);
		familyNameFilter.setType(SettingType.TEXT);
		familyNameFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		familyNameFilter.setDbColumnName("resource.name.family");
		familyNameFilter.setApiParameterName("family");


		Setting birthdayFilter = ObjectFactory.createSetting();
		birthdayFilter.setLabel("Patient DOB");
		birthdayFilter.setType(SettingType.TEXT);
		birthdayFilter.setDataType(WidgetDataType.DATE);
		birthdayFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		birthdayFilter.setDbColumnName("resource.birthDate");
		birthdayFilter.setApiParameterName("birthdate");

		Setting telecomFilter = ObjectFactory.createSetting();
		telecomFilter.setLabel("Patient Telecom");
		telecomFilter.setType(SettingType.TEXT);
		telecomFilter.setDataType(WidgetDataType.STRING);
		telecomFilter.setDbColumnName("resource.telecom.value");
		telecomFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		telecomFilter.setApiParameterName("telecom");

		Setting addressFilter = ObjectFactory.createSetting();
		addressFilter.setLabel("Patient Address");
		addressFilter.setType(SettingType.TEXT);
		addressFilter.setDataType(WidgetDataType.STRING);
		addressFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		addressFilter.setApiParameterName("address");

		Setting activeFilter = ObjectFactory.createSetting();
		activeFilter.setLabel("Is Patient Active");
		activeFilter.setType(SettingType.CHECKBOX);
		activeFilter.setDataType(WidgetDataType.BOOLEAN);
		activeFilter.setDbColumnName("resource.active");
		activeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		activeFilter.setApiParameterName("active");
		
		Setting genderFilterCode = ObjectFactory.createSetting();
		genderFilterCode.setLabel("Patient Gender");
		genderFilterCode.setType(SettingType.TEXT);
		genderFilterCode.setDataType(WidgetDataType.STRING);
		genderFilterCode.setWidgetSettingType(WidgetSettingType.FILTER);
		genderFilterCode.setDbColumnName("resource.gender.coding.code");
		genderFilterCode.setApiParameterName("gender");

		Setting genderFilter = ObjectFactory.createSetting();
		genderFilter.setLabel("Patient Gender Text");
		genderFilter.setType(SettingType.SELECT);
		genderFilter.setDataType(WidgetDataType.STRING);
		genderFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		genderFilter.setDbColumnName("resource.gender.coding.display");
		genderFilter.setApiParameterName("gender:text");
		
		Option genderFilterOption1 = new Option();
		genderFilterOption1.setKey("Male");
		genderFilterOption1.setValue("Male");


		Option genderFilterOption2 = new Option();
		genderFilterOption2.setKey("Female");
		genderFilterOption2.setValue("Female");

		genderFilter.setDefaultOption(genderFilterOption1);

		genderFilter.getOptions().add(genderFilterOption1);
		genderFilter.getOptions().add(genderFilterOption2);

		widgetSettingOption.getSettings().add(nameFilter);
		widgetSettingOption.getSettings().add(givenNameFilter);
		widgetSettingOption.getSettings().add(familyNameFilter);
		widgetSettingOption.getSettings().add(birthdayFilter);
		widgetSettingOption.getSettings().add(activeFilter);
		widgetSettingOption.getSettings().add(addressFilter);
		widgetSettingOption.getSettings().add(telecomFilter);
		widgetSettingOption.getSettings().add(genderFilterCode);

		widgetSettingOption.getSettings().add(genderFilter);

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



		/*************AppContext Variables***************/

		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);

		Option namePrefix = new Option();
		namePrefix.setKey("Name Prefix");
		namePrefix.setValue("currentPatient.name[0].prefix[0]");

		Option patientGivenName = new Option();
		patientGivenName.setKey("Patient Given Name");
		patientGivenName.setValue("currentPatient.name[0].given[0]");

		Option patientFamilyName = new Option();
		patientFamilyName.setKey("Patient Family Name");
		patientFamilyName.setValue("currentPatient.name[0].family[0]");

		Option patientGender = new Option();
		patientGender.setKey("Patient Gender");
		patientGender.setValue("currentPatient.gender.coding[0].code");


		Option patientMaritialCode = new Option();
		patientMaritialCode.setKey("Patient Maritial Status");
		patientMaritialCode.setValue("currentPatient.maritalStatus.coding[0].code");

		Option patientLanguage = new Option();
		patientLanguage.setKey("Patient Language");
		patientLanguage.setValue("currentPatient.communication[0].coding[0].display");

		Option patientRelationship = new Option();
		patientRelationship.setKey("Patient Relationship");
		patientRelationship.setValue("currentPatient.contact[0].relationship[0].coding[0].code");


		Option patientCountry = new Option();
		patientCountry.setKey("Patient Country");
		patientCountry.setValue("currentPatient.address[0].country");



		appcontextvariable.getAppContextVariables().add(namePrefix);
		appcontextvariable.getAppContextVariables().add(patientGivenName);
		appcontextvariable.getAppContextVariables().add(patientFamilyName);
		appcontextvariable.getAppContextVariables().add(patientGender);
		appcontextvariable.getAppContextVariables().add(patientMaritialCode);
		appcontextvariable.getAppContextVariables().add(patientLanguage);
		appcontextvariable.getAppContextVariables().add(patientRelationship);
		appcontextvariable.getAppContextVariables().add(patientCountry);

		widgetSettingOption.getSettings().add(appcontextvariable);




		/*************AppContext Variable***************/


		widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}

	public void allergyIntolleranceWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "Allergyintollerance");
		
		
		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Status");
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setType(SettingType.TEXT);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");
		statusFilter.setApiParameterName("status");

		Setting criticalityFilter = ObjectFactory.createSetting();
		criticalityFilter.setLabel("Criticality");
		criticalityFilter.setDataType(WidgetDataType.STRING);
		criticalityFilter.setType(SettingType.TEXT);
		criticalityFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		criticalityFilter.setDbColumnName("resource.criticality");
		criticalityFilter.setApiParameterName("criticality");
		
		Setting codeFilter = ObjectFactory.createSetting();
		codeFilter.setLabel("Allergy Intolerance code");
		codeFilter.setDataType(WidgetDataType.CODE);
		codeFilter.setType(SettingType.CODE);
		codeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		codeFilter.setDbColumnName("resource.substance.coding.code");
		codeFilter.setApiParameterName("allergyintolerancecode");

		Setting recordedDateFilter = ObjectFactory.createSetting();
		recordedDateFilter.setLabel("Recorded Date");
		recordedDateFilter.setType(SettingType.TEXT);
		recordedDateFilter.setDataType(WidgetDataType.DATE);
		recordedDateFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		recordedDateFilter.setDbColumnName("resource.recordedDate");
		recordedDateFilter.setApiParameterName("recordedDate");

		widgetSettingOption.getSettings().add(statusFilter);
		widgetSettingOption.getSettings().add(criticalityFilter);
		widgetSettingOption.getSettings().add(recordedDateFilter);
		widgetSettingOption.getSettings().add(codeFilter);

		/*************AppContext Variables***************/

		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);

		Option status = new Option();
		status.setKey("Status");
		status.setValue("currentAllergyIntollerance.status");

		Option criticality = new Option();
		criticality.setKey("Criticality");
		criticality.setValue("currentAllergyIntollerance.criticality");

		Option recordedDate = new Option();
		recordedDate.setKey("Recorded Date");
		recordedDate.setValue("currentAllergyIntollerance.recordedDate");
		
		Option substanceCode = new Option();
		substanceCode.setKey("Substance Code");
		substanceCode.setValue("currentAllergyIntollerance.substance.coding.code");

		appcontextvariable.getAppContextVariables().add(status);
		appcontextvariable.getAppContextVariables().add(criticality);
		appcontextvariable.getAppContextVariables().add(recordedDate);
		appcontextvariable.getAppContextVariables().add(substanceCode);

		widgetSettingOption.getSettings().add(appcontextvariable);




		/*************AppContext Variable***************/


		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}
	public void medicationPrescriptionWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "medicationPrescription");

		Setting encounterrefFilter = ObjectFactory.createSetting();
		encounterrefFilter.setLabel("Encounter Reference Id");
		encounterrefFilter.setDataType(WidgetDataType.STRING);
		encounterrefFilter.setType(SettingType.TEXT);
		encounterrefFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encounterrefFilter.setDbColumnName("resource.encounter.reference");
		encounterrefFilter.setApiParameterName("encounter");
		
		Setting medicationFilter = ObjectFactory.createSetting();
		medicationFilter.setLabel("Medication Reference Id");
		medicationFilter.setDataType(WidgetDataType.STRING);
		medicationFilter.setType(SettingType.TEXT);
		medicationFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		medicationFilter.setDbColumnName("resource.medication.reference");
		medicationFilter.setApiParameterName("medication");
		
       //New widget data type Medication Added		
		Setting medicationCodeFilter = ObjectFactory.createSetting();
		medicationCodeFilter.setLabel("Medication Code");
		medicationCodeFilter.setDataType(WidgetDataType.CODE);
		medicationCodeFilter.setType(SettingType.CODE);
		medicationCodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		medicationCodeFilter.setDbColumnName("medication.code");
		medicationCodeFilter.setApiParameterName("medicationcode");
		
		
		
		Setting dateWrittenFilter = ObjectFactory.createSetting();
		dateWrittenFilter.setLabel("Medication Prescription Written Date");
		dateWrittenFilter.setType(SettingType.TEXT);
		dateWrittenFilter.setDataType(WidgetDataType.DATE);
		dateWrittenFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		dateWrittenFilter.setDbColumnName("resource.dateWritten");
		dateWrittenFilter.setApiParameterName("datewritten");
		
		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Medication Prescription Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");
		statusFilter.setApiParameterName("status");
		
		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Active");
		statusFilterOption1.setValue("active");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("On Hold");
		statusFilterOption2.setValue("on hold");

		Option statusFilterOption3 = new Option();
		statusFilterOption3.setKey("completed");
		statusFilterOption3.setValue("completed");

		Option statusFilterOption4 = new Option();
		statusFilterOption4.setKey("entered in error");
		statusFilterOption4.setValue("entered in error");

		Option statusFilterOption5 = new Option();
		statusFilterOption5.setKey("stopped");
		statusFilterOption5.setValue("stopped");

		Option statusFilterOption6 = new Option();
		statusFilterOption6.setKey("superceded");
		statusFilterOption6.setValue("superceded");

		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);
		statusFilter.getOptions().add(statusFilterOption3);
		statusFilter.getOptions().add(statusFilterOption4);
		statusFilter.getOptions().add(statusFilterOption5);
		statusFilter.getOptions().add(statusFilterOption6);


		widgetSettingOption.getSettings().add(encounterrefFilter);
		widgetSettingOption.getSettings().add(dateWrittenFilter);
		widgetSettingOption.getSettings().add(statusFilter);
		widgetSettingOption.getSettings().add(medicationCodeFilter);
		
		


		/******************NAMEQUERY***************************/
		Setting settingNamedQuery = ObjectFactory.createSetting();
		settingNamedQuery.setLabel("Medication History Query");
		settingNamedQuery.getNameQuery().setName("Rule-MedicationHistory");
		settingNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		NameQueryParam nameQueryParam = ObjectFactory.createNameQueryParam();

		nameQueryParam.setAge(AgeType.NEW);
		nameQueryParam.setWidgetType(WidgetType.MEDICATION);

		settingNamedQuery.getNameQuery().getParams().add(nameQueryParam);

		widgetSettingOption.getSettings().add(settingNamedQuery);




		Setting drugInteractionNamedQuery = ObjectFactory.createSetting();
		drugInteractionNamedQuery.setLabel("Drug Drug Interaction Query");
		drugInteractionNamedQuery.getNameQuery().setName("Rule-DrugDrugInteraction");

		drugInteractionNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		NameQueryParam nameQueryParam2 = ObjectFactory.createNameQueryParam();

		nameQueryParam2.setAge(AgeType.NEW);
		nameQueryParam2.setWidgetType(WidgetType.MEDICATION);

		drugInteractionNamedQuery.getNameQuery().getParams().add(nameQueryParam2);


		widgetSettingOption.getSettings().add(drugInteractionNamedQuery);


		/******************NAMEQUERY***************************/
		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		/*************AppContext Variables***************/

		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);

		
		Option medicationReason = new Option();
		medicationReason.setKey("Reason");
		medicationReason.setValue("currentRecord.reasonCodeableConcept.coding[0].display");
		
		Option medicationStrength = new Option();
		medicationStrength.setKey("Strength");
		medicationStrength.setValue("selectedmedicine");
		
	
		Option medref = new Option();
		medref.setKey("Medication Code");
		medref.setValue("medicationToAdd.RXCUI");
		
		Option medname= new Option();
		medname.setKey("Medication Name");
		medname.setValue("medicationToAdd.FULL_NAME");
		
			
		
		Option statusAppContext = new Option();
		statusAppContext.setKey("Status");
		statusAppContext.setValue("currentRecord.status");

		Option medicationDispenseQuantity = new Option();
		medicationDispenseQuantity.setKey("Dispense Quantity");
		medicationDispenseQuantity.setValue("currentRecord.dispense.quantity.value");

		Option medicationName = new Option();
		medicationName.setKey("Name");
		medicationName.setValue("currentRecord.medication.display");

		Option medicationRoute = new Option();
		medicationRoute.setKey("Route");
		medicationRoute.setValue("currentRecord.dosageInstruction[0].route.coding[0].display");
		
		
		Option medicationdosageQuantity = new Option();
		medicationdosageQuantity.setKey("Dosage Quantity");
		medicationdosageQuantity.setValue("currentRecord.dosageInstruction[0].doseQuantity.value");
		
		
		Option medicationFrequency = new Option();
		medicationFrequency.setKey("Frequency");
		medicationFrequency.setValue("currentRecord.dosageInstruction[0].text");
		
		Option medicationStartDate = new Option();
		medicationStartDate.setKey("Start Date");
		medicationStartDate.setValue("currentRecord.dosageInstruction[0].timingSchedule.event[0].start");
		
		Option medicationEndDate = new Option();
		medicationEndDate.setKey("End Date");
		medicationEndDate.setValue("currentRecord.dosageInstruction[0].timingSchedule.event[0].end");
		
		
		Option prescriber = new Option();
		prescriber.setKey("Prescriber");
		prescriber.setValue("currentRecord.prescriber.display");


		
		appcontextvariable.getAppContextVariables().add(medref);
		appcontextvariable.getAppContextVariables().add(medname);
		appcontextvariable.getAppContextVariables().add(medicationReason);
		appcontextvariable.getAppContextVariables().add(statusAppContext);
		appcontextvariable.getAppContextVariables().add(medicationStrength);
		appcontextvariable.getAppContextVariables().add(medicationDispenseQuantity);
		appcontextvariable.getAppContextVariables().add(medicationdosageQuantity);
		appcontextvariable.getAppContextVariables().add(medicationName);
		appcontextvariable.getAppContextVariables().add(medicationRoute);
		appcontextvariable.getAppContextVariables().add(medicationFrequency);
		appcontextvariable.getAppContextVariables().add(medicationStartDate);
		appcontextvariable.getAppContextVariables().add(medicationEndDate);
		appcontextvariable.getAppContextVariables().add(prescriber);



		
		

		widgetSettingOption.getSettings().add(appcontextvariable);




		/*************AppContext Variable***************/

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}

	public void encounterWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "encounter");

		Setting encounterclassFilter= ObjectFactory.createSetting();
		encounterclassFilter.setLabel("Encounter Class");
		encounterclassFilter.setDataType(WidgetDataType.STRING);
		encounterclassFilter.setType(SettingType.TEXT);
		encounterclassFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encounterclassFilter.setDbColumnName("resource.class");

		Setting encountertypeFilter= ObjectFactory.createSetting();
		encountertypeFilter.setLabel("Encounter Code for Type");
		encountertypeFilter.setDataType(WidgetDataType.STRING);
		encountertypeFilter.setType(SettingType.TEXT);
		encountertypeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encountertypeFilter.setDbColumnName("resource.type.coding.code");


		Setting encounterresonFilter= ObjectFactory.createSetting();
		encounterresonFilter.setLabel("Encounter Code for Reason");
		encounterresonFilter.setDataType(WidgetDataType.STRING);
		encounterresonFilter.setType(SettingType.TEXT);
		encounterresonFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encounterresonFilter.setDbColumnName("resource.reason.coding.code");

		Setting encounterStartDateFilter = ObjectFactory.createSetting();
		encounterStartDateFilter.setLabel("Encounter Start Date");
		encounterStartDateFilter.setType(SettingType.TEXT);
		encounterStartDateFilter.setDataType(WidgetDataType.DATE);
		encounterStartDateFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encounterStartDateFilter.setDbColumnName("resource.period.start");

		Setting encounterEndDateFilter = ObjectFactory.createSetting();
		encounterEndDateFilter.setLabel("Encounter End Date");
		encounterEndDateFilter.setType(SettingType.TEXT);
		encounterEndDateFilter.setDataType(WidgetDataType.DATE);
		encounterEndDateFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		encounterEndDateFilter.setDbColumnName("resource.period.end");

		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Encounter Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");

		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Planned");
		statusFilterOption1.setValue("planned");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("InProgress");
		statusFilterOption2.setValue("inProgress");

		Option statusFilterOption3 = new Option();
		statusFilterOption3.setKey("Onleave");
		statusFilterOption3.setValue("onleave");

		Option statusFilterOption4 = new Option();
		statusFilterOption4.setKey("Finished");
		statusFilterOption4.setValue("finished");

		Option statusFilterOption5 = new Option();
		statusFilterOption5.setKey("Cancelled");
		statusFilterOption5.setValue("cancelled");



		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);
		statusFilter.getOptions().add(statusFilterOption3);
		statusFilter.getOptions().add(statusFilterOption4);
		statusFilter.getOptions().add(statusFilterOption5);

		widgetSettingOption.getSettings().add(encounterclassFilter);
		widgetSettingOption.getSettings().add(encountertypeFilter);
		widgetSettingOption.getSettings().add(encounterresonFilter);
		widgetSettingOption.getSettings().add(encounterStartDateFilter);
		widgetSettingOption.getSettings().add(encounterEndDateFilter);
		widgetSettingOption.getSettings().add(statusFilter);

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
		
		
		/*********************APPCONTEXT ****************************/
		  
		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);
		
		Option encounterclass= new Option();
		encounterclass.setKey("Encounter Class");
		encounterclass.setValue("currentRecord.class");
		
		Option encounterType= new Option();
		encounterType.setKey("Encounter Type");
		encounterType.setValue("currentRecord.type[0].text");
		
		Option encounterstatus = new Option();
		encounterstatus.setKey("Encounter Status");
		encounterstatus.setValue("currentRecord.status");
		
		
		Option encounterreason= new Option();
		encounterreason.setKey("Encounter Reason");
		encounterreason.setValue("currentRecord.reason.text");
		
		Option encounterparticipanttype= new Option();
		encounterparticipanttype.setKey("Encounter Participant Type");
		encounterparticipanttype.setValue("currentRecord.participant[0].type[0].coding[0].code");
		
		
		Option encounterparticipantname= new Option();
		encounterparticipantname.setKey("Encounter Participant Name");
		encounterparticipantname.setValue("currentRecord.participant[0].individual.display");
		
		Option encounterpriority= new Option();
		encounterpriority.setKey("Encounter Priority");
		encounterpriority.setValue("currentRecord.priority.coding[0].code");
		
					
		appcontextvariable.getAppContextVariables().add(encounterclass);
		appcontextvariable.getAppContextVariables().add(encounterType);
		appcontextvariable.getAppContextVariables().add(encounterstatus);
		appcontextvariable.getAppContextVariables().add(encounterreason);
		appcontextvariable.getAppContextVariables().add(encounterparticipanttype);
		appcontextvariable.getAppContextVariables().add(encounterparticipantname);
		appcontextvariable.getAppContextVariables().add(encounterpriority);
		
		
		widgetSettingOption.getSettings().add(appcontextvariable);

		
		/***************APPCONTEXT VARIABLE*********/
		

		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}



	public void observationWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);

		if(type.equals(WidgetType.VITALSIGNS)){
			addWidthWidgetsettingOption(widgetSettingOption, "vitalsigns");
		}
		else if(type.equals(WidgetType.LABPANEL)){
			addWidthWidgetsettingOption(widgetSettingOption, "labpanel");
		}
		else{
			addWidthWidgetsettingOption(widgetSettingOption, "labtest");
			
			Setting labtestcodeFilter1 = ObjectFactory.createSetting();
			labtestcodeFilter1.setLabel("Labtest Code");
			labtestcodeFilter1.setDataType(WidgetDataType.CODE);
			labtestcodeFilter1.setType(SettingType.CODE);
			labtestcodeFilter1.setWidgetSettingType(WidgetSettingType.FILTER);
			labtestcodeFilter1.setDbColumnName("labtest.code");
			labtestcodeFilter1.setApiParameterName("labtestcategorycode");
			
			widgetSettingOption.getSettings().add(labtestcodeFilter1);

		}


		Setting observationtypecodeFilter = ObjectFactory.createSetting();
		if(type.equals(WidgetType.VITALSIGNS)){
			observationtypecodeFilter.setLabel("Vital Sign Name Code");
		}
		else if(type.equals(WidgetType.LABPANEL)){
			observationtypecodeFilter.setLabel("Labpanel Name Code");
		}
		else{
			observationtypecodeFilter.setLabel("Labtest Name Code");
		}
		observationtypecodeFilter.setDataType(WidgetDataType.STRING);
		observationtypecodeFilter.setType(SettingType.TEXT);
		observationtypecodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		observationtypecodeFilter.setDbColumnName("resource.name.coding.code");

		Setting appliesDateTimeFilter = ObjectFactory.createSetting();
		if(type.equals(WidgetType.VITALSIGNS)){
			appliesDateTimeFilter.setLabel("VitalSign Applies Date");
		}
		else if(type.equals(WidgetType.LABPANEL)){
			appliesDateTimeFilter.setLabel("Labpanel Applies Date");
		}
		else{
			appliesDateTimeFilter.setLabel("Labtest Applies Date");
		}
		appliesDateTimeFilter.setType(SettingType.TEXT);
		appliesDateTimeFilter.setDataType(WidgetDataType.DATE);
		appliesDateTimeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		appliesDateTimeFilter.setDbColumnName("resource.appliesDateTime");

		Setting issuedDateTimeFilter = ObjectFactory.createSetting();

		if(type.equals(WidgetType.VITALSIGNS)){
			issuedDateTimeFilter.setLabel("VitalSign Issued Date");
		}
		else if(type.equals(WidgetType.LABPANEL)){
			issuedDateTimeFilter.setLabel("Labpanel Issued Date");
		}
		else{
			issuedDateTimeFilter.setLabel("Labtest Issued Date");
		}

		issuedDateTimeFilter.setType(SettingType.TEXT);
		issuedDateTimeFilter.setDataType(WidgetDataType.DATE);
		issuedDateTimeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		issuedDateTimeFilter.setDbColumnName("resource.appliesDateTime");

		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");

		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Registered");
		statusFilterOption1.setValue("registered");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("Preliminary");
		statusFilterOption2.setValue("preliminary");

		Option statusFilterOption3 = new Option();
		statusFilterOption3.setKey("Final");
		statusFilterOption3.setValue("final");

		Option statusFilterOption4 = new Option();
		statusFilterOption4.setKey("Amended");
		statusFilterOption4.setValue("amended");

		Option statusFilterOption5 = new Option();
		statusFilterOption5.setKey("Cancelled");
		statusFilterOption5.setValue("cancelled");

		Option statusFilterOption6 = new Option();
		statusFilterOption6.setKey("Entered in error");
		statusFilterOption6.setValue("entered in error");

		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);
		statusFilter.getOptions().add(statusFilterOption3);
		statusFilter.getOptions().add(statusFilterOption4);
		statusFilter.getOptions().add(statusFilterOption5);
		statusFilter.getOptions().add(statusFilterOption6);

		widgetSettingOption.getSettings().add(observationtypecodeFilter);
		widgetSettingOption.getSettings().add(appliesDateTimeFilter);
		widgetSettingOption.getSettings().add(issuedDateTimeFilter);

		widgetSettingOption.getSettings().add(statusFilter);


		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);

		/*************AppContext Variables***************/
		if(type.equals(WidgetType.VITALSIGNS)){


			Option tempVitalValues= new Option();
			tempVitalValues.setKey("Temp Vital Values");
			tempVitalValues.setValue("tempvitalsigns[0].valueQuantity.value");

			Option pressureVitalValue = new Option();
			pressureVitalValue.setKey("Pressure Vital Values");
			pressureVitalValue.setValue("tempvitalsigns[1].valueQuantity.value");


					
			Option RESVitalValue = new Option();
			RESVitalValue.setKey("Res Vital Values");
			RESVitalValue.setValue("tempvitalsigns[2].valueQuantity.value");
			
			Option DBPVitalValue = new Option();
			DBPVitalValue.setKey("D-BP Values");
			DBPVitalValue.setValue("tempvitalsigns[3].valueQuantity.value");
			
			Option SBPVitalValue = new Option();
			SBPVitalValue.setKey("S-BP Vital Values");
			SBPVitalValue.setValue("tempvitalsigns[4].valueQuantity.value");
			
			Option HTVitalValue = new Option();
			HTVitalValue.setKey("Height Vital Values");
			HTVitalValue.setValue("tempvitalsigns[5].valueQuantity.value");
			
			
			Option WTVitalValue = new Option();
			WTVitalValue.setKey("Weight Vital Values");
			WTVitalValue.setValue("tempvitalsigns[6].valueQuantity.value");
			
			Option BMIVitalValue = new Option();
			BMIVitalValue.setKey("BMI Vital Values");
			BMIVitalValue.setValue("tempvitalsigns[7].valueQuantity.value");
			
			Option SAO2VitalValue = new Option();
			SAO2VitalValue.setKey("Sa02 Vital Values");
			SAO2VitalValue.setValue("tempvitalsigns[8].valueQuantity.value");


			appcontextvariable.getAppContextVariables().add(tempVitalValues);
			appcontextvariable.getAppContextVariables().add(pressureVitalValue);
			appcontextvariable.getAppContextVariables().add(RESVitalValue);
			appcontextvariable.getAppContextVariables().add(DBPVitalValue);
			appcontextvariable.getAppContextVariables().add(pressureVitalValue);
			appcontextvariable.getAppContextVariables().add(SBPVitalValue);
			appcontextvariable.getAppContextVariables().add(HTVitalValue);
			appcontextvariable.getAppContextVariables().add(WTVitalValue);
			appcontextvariable.getAppContextVariables().add(BMIVitalValue);
			appcontextvariable.getAppContextVariables().add(SAO2VitalValue);
			
			
			

		}
		else if(type.equals(WidgetType.LABTEST)){
			
			
			Option name= new Option();
			name.setKey("LabTest Name");
			name.setValue("currentRecord.name.coding[0].code");
			
			Option labtestmeasurement= new Option();
			labtestmeasurement.setKey("LabTest Measurement");
			labtestmeasurement.setValue("currentRecord.valueQuantity.value");
			
			
			Option labtestquantity= new Option();
			labtestquantity.setKey("LabTest Value Quantity");
			labtestquantity.setValue("currentRecord.valueQuantity.units");
			
			Option labtestStatus = new Option();
			labtestStatus.setKey("LabTest Status");
			labtestStatus.setValue("currentRecord.status");
			
						
			appcontextvariable.getAppContextVariables().add(name);
			appcontextvariable.getAppContextVariables().add(labtestquantity);

			appcontextvariable.getAppContextVariables().add(labtestmeasurement);

			appcontextvariable.getAppContextVariables().add(labtestStatus);

					
		}


		widgetSettingOption.getSettings().add(appcontextvariable);





		/*************AppContext Variable***************/



		if(type.equals(WidgetType.LABTEST) || type.equals(WidgetType.LABPANEL)){

			Setting settingNamedQuery = ObjectFactory.createSetting();
			settingNamedQuery.setLabel("Lab Medication Association Query");

			settingNamedQuery.getNameQuery().setName("Rule-LabMedicationAssociationQuery");
			settingNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


			NameQueryParam nameQueryParam = ObjectFactory.createNameQueryParam();

			nameQueryParam.setAge(AgeType.NEW);
			nameQueryParam.setWidgetType(WidgetType.MEDICATION);

			settingNamedQuery.getNameQuery().getParams().add(nameQueryParam);

			widgetSettingOption.getSettings().add(settingNamedQuery);


		}



		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}




	public void problemWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "problem");


		Setting problemidencodeFilter = ObjectFactory.createSetting();
		problemidencodeFilter.setLabel("Problem Identification Code");
		problemidencodeFilter.setDataType(WidgetDataType.STRING);
		problemidencodeFilter.setType(SettingType.TEXT);
		problemidencodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		problemidencodeFilter.setDbColumnName("resource.code.coding.code");
		
		
		/*
		Setting problemcategorycodeFilter = ObjectFactory.createSetting();
		problemcategorycodeFilter.setLabel("Problem Category Code");
		problemcategorycodeFilter.setDataType(WidgetDataType.CODE);
		problemcategorycodeFilter.setType(SettingType.CODE);
		problemcategorycodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		problemcategorycodeFilter.setDbColumnName("problem.code");
		problemcategorycodeFilter.setApiParameterName("problemcategorycode");
*/
		
		Setting problemcategorycodeFilter1 = ObjectFactory.createSetting();
		problemcategorycodeFilter1.setLabel("Problem Category Code");
		problemcategorycodeFilter1.setDataType(WidgetDataType.CODE);
		problemcategorycodeFilter1.setType(SettingType.CODE);
		problemcategorycodeFilter1.setWidgetSettingType(WidgetSettingType.FILTER);
		problemcategorycodeFilter1.setDbColumnName("problem.code");
		problemcategorycodeFilter1.setApiParameterName("problemcategorycode");
/*
		Setting problemcategorycodeFilter = ObjectFactory.createSetting();
		problemcategorycodeFilter.setLabel("Problem Category Code");
		problemcategorycodeFilter.setDataType(WidgetDataType.STRING);
		problemcategorycodeFilter.setType(SettingType.TEXT);
		problemcategorycodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		problemcategorycodeFilter.setDbColumnName("resource.category.coding.code");
*/
		Setting dateAssertedTimeFilter = ObjectFactory.createSetting();
		dateAssertedTimeFilter.setLabel("Problem estimated or actual date");
		dateAssertedTimeFilter.setType(SettingType.TEXT);
		dateAssertedTimeFilter.setDataType(WidgetDataType.DATE);
		dateAssertedTimeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		dateAssertedTimeFilter.setDbColumnName("resource.dateAsserted");

		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Problem Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");

		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Provisional");
		statusFilterOption1.setValue("provisional");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("Working");
		statusFilterOption2.setValue("working");

		Option statusFilterOption3 = new Option();
		statusFilterOption3.setKey("Confirmed");
		statusFilterOption3.setValue("confirmed");

		Option statusFilterOption4 = new Option();
		statusFilterOption4.setKey("Refuted");
		statusFilterOption4.setValue("refuted");

		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);
		statusFilter.getOptions().add(statusFilterOption3);
		statusFilter.getOptions().add(statusFilterOption4);


		widgetSettingOption.getSettings().add(problemidencodeFilter);
		widgetSettingOption.getSettings().add(problemcategorycodeFilter1);
		widgetSettingOption.getSettings().add(dateAssertedTimeFilter);
		widgetSettingOption.getSettings().add(statusFilter);
		
		
		
		/*********************APPCONTEXT ****************************/
		  
		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);
		
		Option problemname= new Option();
		problemname.setKey("Problem Name");
		problemname.setValue("currentRecord.code.text");
		
		Option assertedby= new Option();
		assertedby.setKey("Problem Asserted By");
		assertedby.setValue("currentRecord.asserter.display");
		
		Option problemstatus = new Option();
		problemstatus.setKey("Problem Status");
		problemstatus.setValue("currentRecord.status");
		
		
		Option problemcat= new Option();
		problemcat.setKey("Problem Category");
		problemcat.setValue("currentRecord.category.coding[0].code");
		
					
		appcontextvariable.getAppContextVariables().add(problemname);
		appcontextvariable.getAppContextVariables().add(assertedby);
		appcontextvariable.getAppContextVariables().add(problemstatus);
		appcontextvariable.getAppContextVariables().add(problemcat);
		
		
		widgetSettingOption.getSettings().add(appcontextvariable);

		
		/***************APPCONTEXT VARIABLE*********/

		
		
		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}


	public void alertWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "Alert");



		Setting identifierLabelFilter = ObjectFactory.createSetting();
		identifierLabelFilter.setLabel("Alert Identifier Label");

		identifierLabelFilter.setDataType(WidgetDataType.STRING);
		identifierLabelFilter.setType(SettingType.TEXT);
		identifierLabelFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		identifierLabelFilter.setDbColumnName("resource.identifier.label");

		
		Setting alertcategorycodeFilter = ObjectFactory.createSetting();
		alertcategorycodeFilter.setLabel("Alert Category Code");

		alertcategorycodeFilter.setDataType(WidgetDataType.STRING);
		alertcategorycodeFilter.setType(SettingType.SELECT);
		alertcategorycodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		alertcategorycodeFilter.setDbColumnName("resource.category.coding.code");
		
		
		Option alertcategorycodeFilterOption1 = new Option();
		alertcategorycodeFilterOption1.setKey("Administrative");
		alertcategorycodeFilterOption1.setValue("58138004");

		Option alertcategorycodeFilterOption2 = new Option();
		alertcategorycodeFilterOption2.setKey("Sign Out Note");
		alertcategorycodeFilterOption2.setValue("24581000000100");
		
		Option alertcategorycodeFilterOption3 = new Option();
		alertcategorycodeFilterOption3.setKey("Clinical");
		alertcategorycodeFilterOption3.setValue("58147004");
		
		Option alertcategorycodeFilterOption4 = new Option();
		alertcategorycodeFilterOption4.setKey("To-Do");
		alertcategorycodeFilterOption4.setValue("38651000000103");

		alertcategorycodeFilter.setDefaultOption(alertcategorycodeFilterOption1);

		alertcategorycodeFilter.getOptions().add(alertcategorycodeFilterOption1);
		alertcategorycodeFilter.getOptions().add(alertcategorycodeFilterOption2);
		alertcategorycodeFilter.getOptions().add(alertcategorycodeFilterOption3);
		alertcategorycodeFilter.getOptions().add(alertcategorycodeFilterOption4);

		Setting alertNoteFilter = ObjectFactory.createSetting();
		alertNoteFilter.setLabel("Alert Note");

		alertNoteFilter.setDataType(WidgetDataType.STRING);
		alertNoteFilter.setType(SettingType.TEXT);
		alertNoteFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		alertNoteFilter.setDbColumnName("resource.note");
		 
		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Recommendation Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.status");

		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Active");
		statusFilterOption1.setValue("active");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("Inactive");
		statusFilterOption2.setValue("inactive");

		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);


		widgetSettingOption.getSettings().add(identifierLabelFilter);
		widgetSettingOption.getSettings().add(alertcategorycodeFilter);

		widgetSettingOption.getSettings().add(statusFilter);
		widgetSettingOption.getSettings().add(alertNoteFilter);
		


		/**************NAMED QUERY ************************/
		Setting settingNamedQuery = ObjectFactory.createSetting();
		settingNamedQuery.setLabel("High Dosage Medication Query");
		//settingNamedQuery.setNameQueryName("Rule-HighDosageMedicationAlert");
		settingNamedQuery.getNameQuery().setName("Rule-HighDosageMedicationAlert");
		settingNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		//NameQueryParam nameQueryParam = ObjectFactory.createNameQueryParam();
		//nameQueryParam.setAge(AgeType.NEW);
		//nameQueryParam.setWidgetType(WidgetType.MEDICATION);
		//settingNamedQuery.getNameQuery().getParams().add(nameQueryParam);

		NameQueryParam nameQueryAppConextMedName = ObjectFactory.createNameQueryParam();
		nameQueryAppConextMedName.setAge(AgeType.APPCONTEXT);
		Option pramOptionMedName = new Option();
		pramOptionMedName.setKey("Medication Name");
		nameQueryAppConextMedName.setParams(pramOptionMedName);
		settingNamedQuery.getNameQuery().getParams().add(nameQueryAppConextMedName);
		
		NameQueryParam nameQueryAppConextMedCode = ObjectFactory.createNameQueryParam();
		nameQueryAppConextMedCode.setAge(AgeType.APPCONTEXT);
		Option pramOptionMedCode = new Option();
		pramOptionMedCode.setKey("Medication Code");
		nameQueryAppConextMedCode.setParams(pramOptionMedCode);
		settingNamedQuery.getNameQuery().getParams().add(nameQueryAppConextMedCode);
		
		NameQueryParam nameQueryAppConextParamMedStrength = ObjectFactory.createNameQueryParam();
		nameQueryAppConextParamMedStrength.setAge(AgeType.APPCONTEXT);
		Option pramOptionStrength = new Option();
		pramOptionStrength.setKey("Medication Quantity");
		nameQueryAppConextParamMedStrength.setParams(pramOptionStrength);
		settingNamedQuery.getNameQuery().getParams().add(nameQueryAppConextParamMedStrength);
		
		
		widgetSettingOption.getSettings().add(settingNamedQuery);



		Setting medicationApprovalNamedQuery = ObjectFactory.createSetting();
		medicationApprovalNamedQuery.setLabel("Medication Approval Query");
		//medicationApprovalNamedQuery.setNameQueryName("Rule-MedicationApprovalAlert");
		medicationApprovalNamedQuery.getNameQuery().setName("Rule-MedicationApprovalAlert");
		medicationApprovalNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		//NameQueryParam nameQueryParam1 = ObjectFactory.createNameQueryParam();

		//nameQueryParam1.setAge(AgeType.NEW);
		//nameQueryParam1.setWidgetType(WidgetType.MEDICATION);

		//medicationApprovalNamedQuery.getNameQueryParams().add(nameQueryParam1);
		//medicationApprovalNamedQuery.getNameQuery().getParams().add(nameQueryParam1);
		
		NameQueryParam nameQueryAppConextParam1 = ObjectFactory.createNameQueryParam();
		nameQueryAppConextParam1.setAge(AgeType.APPCONTEXT);
		Option pramOption1 = new Option();
		pramOption1.setKey("Medication Code");
		nameQueryAppConextParam1.setParams(pramOption1);
		
		medicationApprovalNamedQuery.getNameQuery().getParams().add(nameQueryAppConextParam1);
		
		NameQueryParam nameQueryAppConextParam2 = ObjectFactory.createNameQueryParam();
		nameQueryAppConextParam2.setAge(AgeType.APPCONTEXT);
		Option pramOption2 = new Option();
		pramOption2.setKey("Medication Name");
		nameQueryAppConextParam2.setParams(pramOption2);
		
		medicationApprovalNamedQuery.getNameQuery().getParams().add(nameQueryAppConextParam2);
		

		widgetSettingOption.getSettings().add(medicationApprovalNamedQuery);



		Setting drugADENamedQuery = ObjectFactory.createSetting();
		drugADENamedQuery.setLabel("Drug ADE Interaction Query");
		//drugADENamedQuery.setNameQueryName("Rule-DrugADEInteraction");
		drugADENamedQuery.getNameQuery().setName("Rule-DrugADEInteraction");
		drugADENamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		NameQueryParam nameQueryParam2 = ObjectFactory.createNameQueryParam();

		nameQueryParam2.setAge(AgeType.NEW);
		nameQueryParam2.setWidgetType(WidgetType.MEDICATION);

		//drugADENamedQuery.getNameQueryParams().add(nameQueryParam1);
		drugADENamedQuery.getNameQuery().getParams().add(nameQueryParam2);

		widgetSettingOption.getSettings().add(drugADENamedQuery);




		/*************NAMED QUERY **************************/
		
		
		
		/*********************APPCONTEXT ****************************/
		  
		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);
		
		Option alertTitle= new Option();
		alertTitle.setKey("Alert Title");
		alertTitle.setValue("currentRecord.identifier[0].label");
		
		Option alertsource= new Option();
		alertsource.setKey("Alert Source ");
		alertsource.setValue("currentRecord.author.display");
		
		Option alertstatus = new Option();
		alertstatus.setKey("Alert Status");
		alertstatus.setValue("currentRecord.status");
		
		
		Option alertcat= new Option();
		alertcat.setKey("Alert Category");
		alertcat.setValue("currentRecord.category.coding[0].code");
		
					
		appcontextvariable.getAppContextVariables().add(alertTitle);
		appcontextvariable.getAppContextVariables().add(alertsource);
		appcontextvariable.getAppContextVariables().add(alertcat);
		appcontextvariable.getAppContextVariables().add(alertstatus);
		
		
		widgetSettingOption.getSettings().add(appcontextvariable);

		
		/***************APPCONTEXT VARIABLE*********/

		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}

	public void adverseWidgetsettingOptionInsert(WidgetType type) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		WidgetSettingOption widgetSettingOption = ObjectFactory.createWidgetSettingOption(type);
		addWidthWidgetsettingOption(widgetSettingOption, "AdverseReaction");


		Setting symptomcodeFilter = ObjectFactory.createSetting();
		symptomcodeFilter.setLabel("Symptom Category Code");

		symptomcodeFilter.setDataType(WidgetDataType.STRING);
		symptomcodeFilter.setType(SettingType.TEXT);
		symptomcodeFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		symptomcodeFilter.setDbColumnName("resource.symptom.code.coding.code");

		Setting adverseReactionDateFilter = ObjectFactory.createSetting();
		adverseReactionDateFilter.setLabel("Adverse Reaction Date");
		adverseReactionDateFilter.setType(SettingType.TEXT);
		adverseReactionDateFilter.setDataType(WidgetDataType.DATE);
		adverseReactionDateFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		adverseReactionDateFilter.setDbColumnName("resource.date");


		Setting exposureDateFilter = ObjectFactory.createSetting();
		exposureDateFilter.setLabel("Exposure Date");
		exposureDateFilter.setType(SettingType.TEXT);
		exposureDateFilter.setDataType(WidgetDataType.DATE);
		exposureDateFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		exposureDateFilter.setDbColumnName("resource.exposure.date");

		Setting statusFilter = ObjectFactory.createSetting();
		statusFilter.setLabel("Adverse Reaction Status");
		statusFilter.setType(SettingType.SELECT);
		statusFilter.setDataType(WidgetDataType.STRING);
		statusFilter.setWidgetSettingType(WidgetSettingType.FILTER);
		statusFilter.setDbColumnName("resource.text.status");

		Option statusFilterOption1 = new Option();
		statusFilterOption1.setKey("Severe");
		statusFilterOption1.setValue("severe");

		Option statusFilterOption2 = new Option();
		statusFilterOption2.setKey("Serious");
		statusFilterOption2.setValue("serious");

		Option statusFilterOption3 = new Option();
		statusFilterOption3.setKey("Moderate");
		statusFilterOption3.setValue("moderate");

		Option statusFilterOption4 = new Option();
		statusFilterOption4.setKey("Minor");
		statusFilterOption4.setValue("minor");

		statusFilter.setDefaultOption(statusFilterOption1);

		statusFilter.getOptions().add(statusFilterOption1);
		statusFilter.getOptions().add(statusFilterOption2);
		statusFilter.getOptions().add(statusFilterOption3);
		statusFilter.getOptions().add(statusFilterOption4);

		widgetSettingOption.getSettings().add(symptomcodeFilter);
		widgetSettingOption.getSettings().add(adverseReactionDateFilter);
		widgetSettingOption.getSettings().add(exposureDateFilter);


		widgetSettingOption.getSettings().add(statusFilter);
		
		
		
		
		/*********************APPCONTEXT ****************************/
		  
		Setting appcontextvariable = ObjectFactory.createSetting();
		appcontextvariable.setLabel("AppContext Variable");
		appcontextvariable.setType(SettingType.SELECT);
		appcontextvariable.setWidgetSettingType(WidgetSettingType.APP_CONTEXT_VARIABLES);
		
		Option adverseSymptoms= new Option();
		adverseSymptoms.setKey("Adverse Reaction Symptom");
		adverseSymptoms.setValue("currentRecord.symptom[0].code.coding[0].display");
		
		Option adversesubstance= new Option();
		adversesubstance.setKey("Adverse Reaction Substance");
		adversesubstance.setValue("substance.type.coding[0].code");
		
		Option adverseserverity = new Option();
		adverseserverity.setKey("Adverse Reaction Severity");
		adverseserverity.setValue("currentRecord.symptom[0].severity");
		
		
		Option adversetype= new Option();
		adversetype.setKey("Adverse Reaction Type");
		adversetype.setValue("currentRecord.exposure[0].type");
		
		
		
		
					
		appcontextvariable.getAppContextVariables().add(adverseSymptoms);
		appcontextvariable.getAppContextVariables().add(adverseserverity);
		appcontextvariable.getAppContextVariables().add(adversesubstance);
		appcontextvariable.getAppContextVariables().add(adversetype);
			
		widgetSettingOption.getSettings().add(appcontextvariable);

		
		/***************APPCONTEXT VARIABLE*********/

		//widgetsettingOptionPatientNamedQueryInsert(widgetSettingOption);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String widgetsettingOptionJson =  mapper.writeValueAsString(widgetSettingOption);

		widgetSettingOptionDAO.save(widgetsettingOptionJson);

	}



	public void widgetsettingOptionPatientNamedQueryInsert(WidgetSettingOption widgetSettingOption) throws Exception {

		MongoWidgetSettingOptionDAOImpl widgetSettingOptionDAO = new MongoWidgetSettingOptionDAOImpl();
		widgetSettingOptionDAO.setMongodatasourceprovider(mongodatasourceprovider);

		Setting settingNamedQuery = ObjectFactory.createSetting();
		settingNamedQuery.setLabel("Patient Named Query");
		//settingNamedQuery.setNameQueryName("Rule-OldPatient");
		settingNamedQuery.getNameQuery().setName("Rule-OldPatient");
		settingNamedQuery.setWidgetSettingType(WidgetSettingType.NAMED_QUERY);


		NameQueryParam nameQueryParam = ObjectFactory.createNameQueryParam();

		nameQueryParam.setAge(AgeType.NEW);
		nameQueryParam.setWidgetType(WidgetType.PATIENTS);

		//settingNamedQuery.getNameQueryParams().add(nameQueryParam);
		settingNamedQuery.getNameQuery().getParams().add(nameQueryParam);

		widgetSettingOption.getSettings().add(settingNamedQuery);


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
		settingHeight.setDefaultValue("AUTO");

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


	public static void main(String[] args) throws Exception {

		WidgetSettingOptionMockData mockData = new WidgetSettingOptionMockData();
		//mockData.patientWidgetsettingOptionInsert(WidgetType.PATIENTS);
		//mockData.medicationPrescriptionWidgetsettingOptionInsert(WidgetType.MEDICATION);
		//mockData.encounterWidgetsettingOptionInsert(WidgetType.ENCOUNTER);
		//mockData.observationWidgetsettingOptionInsert(WidgetType.VITALSIGNS);
		//mockData.observationWidgetsettingOptionInsert(WidgetType.LABTEST);
		//mockData.observationWidgetsettingOptionInsert(WidgetType.LABPANEL);

		mockData.allergyIntolleranceWidgetsettingOptionInsert(WidgetType.ALLERGYINTOLERANCE);
		
		//mockData.problemWidgetsettingOptionInsert(WidgetType.PROBLEMS);
		 //mockData.alertWidgetsettingOptionInsert(WidgetType.RECOMMENDATION);
		 //mockData.todolistappsettingOptionInsert(WidgetType.TODOLIST);
		//mockData.adverseWidgetsettingOptionInsert(WidgetType.ADVERSEREACTION);
		//mockData.launchappsettingOptionInsert(WidgetType.LAUNCHAPP);


	}

}
