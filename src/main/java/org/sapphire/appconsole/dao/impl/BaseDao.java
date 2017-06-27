package org.sapphire.appconsole.dao.impl;

//import org.sapphire.fhir.MongoDataSourceProvider;

public class BaseDao {
	
	protected MongoDataSourceProvider mongodatasourceprovider;

	public static final String COLLECTION_APP ="app";
	public static final String COLLECTION_WIDGET ="widget";
	public static final String COLLECTION_LAYOUT ="layout";
	public static final String COLLECTION_WIDGET_SETTING_OPTION ="widgetsettingoption";
	public static final String COMPONENT_ID ="componentID";
	public static final String WIDGET_SETTING_OPTION_ID = "id";
	public static final String WIDGET_SETTING_TYPE = "widgetType";
	
	public static final String EVENT_OPTION_TYPE_ADD = "Add";
	public static final String EVENT_OPTION_TYPE_EDIT = "Edit";
	public static final String EVENT_OPTION_TYPE_ADD_EDIT = "Add/Edit";
	public static final String EVENT_OPTION_TYPE_CHANGE = "Notify";
	public static final String EVENT_OPTION_TYPE_DELETE = "Delete";
	public static final String EVENT_OPTION_TYPE_SUBSCRIBE = "Subscribe";
	public static final String EVENT_OPTION_TYPE_VIEW = "Detail";
	public static final String EVENT_OPTION_TYPE_GRAPH_VIEW = "Graph View";
	public static final String EVENT_OPTION_ATTACH_APPCONTEXT_VARIABLE_LISTENER = "Create AppContext Flag";
	public static final String EVENT_OPTION_SUBSCRIBE_APPCONTEXT_VARIABLE_CHANGE = "Subscribe AppContext Flag";


	
	public static final String EVENT_ACTION_TYPE_RELOAD = "Reload Widgets";
	public static final String EVENT_ACTION_TYPE_OPEN_PAGE = "View Page";
	public static final String EVENT_ACTION_TYPE_OPEN_POPUP = "Popup";
	
	
	
	public MongoDataSourceProvider getMongodatasourceprovider() {
		return mongodatasourceprovider;
	}

	public void setMongodatasourceprovider(
			MongoDataSourceProvider mongodatasourceprovider) {
		this.mongodatasourceprovider = mongodatasourceprovider;
	}
}
