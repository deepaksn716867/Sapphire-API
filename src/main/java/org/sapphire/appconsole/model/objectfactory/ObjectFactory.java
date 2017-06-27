//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.13 at 04:23:33 PM IST 
//


package org.sapphire.appconsole.model.objectfactory;

import java.util.UUID;

import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.model.Cell;
import org.sapphire.appconsole.model.Container;
import org.sapphire.appconsole.model.Event;
import org.sapphire.appconsole.model.Filter;
import org.sapphire.appconsole.model.FilterCriteria;
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.model.NameQuery;
import org.sapphire.appconsole.model.NameQueryParam;
import org.sapphire.appconsole.model.Page;
import org.sapphire.appconsole.model.Row;
import org.sapphire.appconsole.model.Setting;
import org.sapphire.appconsole.model.Table;
import org.sapphire.appconsole.model.UIComponent;
import org.sapphire.appconsole.model.Widget;
import org.sapphire.appconsole.model.WidgetSettingOption;
import org.sapphire.appconsole.model.WidgetType;
import org.sapphire.appconsole.model.Option;




public final class ObjectFactory {
	
	private static final String DEFAULT_HEIGHT = "AUTO";
	private static final int DEFAULT_WIDTH =100;
	

	public static String uniqueID(){
		 UUID uniqueKey = UUID.randomUUID();   
		 return uniqueKey.toString();
	}
	
	private static UIComponent createUIComponent(UIComponent component){
		 UUID uniqueKey = UUID.randomUUID();   
		 component.setComponentID(component.getClass().getSimpleName() + "-" + uniqueKey);
		 component.setType(component.getClass().getSimpleName());
		 component.setHeight(DEFAULT_HEIGHT);
		 component.setWidth(DEFAULT_WIDTH);
		 return component;
	}


    /**
     * Create an instance of {@link Layout }
     * 
     */
    public static Layout createLayout() {
    	Layout layout = (Layout)createUIComponent(new Layout());
        return layout;
    }

    /**
     * Create an instance of {@link Cell }
     * 
     */
    public static Cell createCell(int width, String height) {
       Cell cell = (Cell)createUIComponent(new Cell());
       cell.setHeight(height);
       cell.setWidth(width);
       return cell;
       
    }

    
    /**
     * Create an instance of {@link Cell }
     * 
     */
    public static Cell createCell() {
          return (Cell)createUIComponent(new Cell());
    
    }
    
    /**
     * 
     * @param width
     * @param height
     * @return
     */
    public static Row createRow(int width, String height) {
    	Row row  = (Row)createUIComponent(new Row());
    	row.setHeight(height);
    	row.setWidth(width);
        return row;
       
    }
    
    /**
     * Create an instance of {@link Row }
     * 
     */
    public static Row createRow() {
    	
        return (Row)createUIComponent(new Row());
       
    }
    
    /**
     * Create an instance of {@link App }
     * 
     */
    public static App createApp(String name) {
    	App app = (App)createUIComponent(new App());;
    	app.setName(name);
       	return app;
    }
    
    
    /**
     * Create an instance of {@link App }
     * 
     */
    public static Page createPage(String name) {
    	Page page = (Page)createUIComponent(new Page());
    	page.setTitle(name);
       	return page;
    }
    
    /**
     * Create an instance of {@link Widget }
     * 
     */
    public static Widget createWidget(WidgetType widgetType) {
    	Widget widget = (Widget)createUIComponent(new Widget());
    	widget.setWidgetType(widgetType);
        return widget;
    }
    

    /**
     * Create an instance of {@link Table }
     * 
     */
    public static Table createTable(int rowCount, int columnCoumn) {
    	Table table = (Table)createUIComponent(new Table());
    	table.setNumberOfCollumns(columnCoumn);
    	table.setNumberOfRows(rowCount);
    	
    	//add banner row
    	Row vrow = createRow(100, String.valueOf(50));
    	Cell vcell = createCell(100, String.valueOf(50));
    	vcell.setColSpan(columnCoumn);
    	vrow.setIsBannerRow(true);
    	vrow.getCells().add(vcell);
    	table.getRows().add(vrow);
    	
    	for (int i = 0; i < rowCount; i++) {
    		int rw = 100;
    		int rh = 100/rowCount;
    		Row row = createRow(rw, String.valueOf(rh));
    		
			for (int j = 0; j < columnCoumn; j++) {
				int cw = 100/columnCoumn;
	    		int ch = 100;
	    		
				Cell cell = createCell(cw, String.valueOf(ch));
				cell.setColSpan(1);
				row.getCells().add(cell);
			}
			
			table.getRows().add(row);
		}
        return table;
    }

    /**
     * Create an instance of {@link Container }
     * 
     */
    public static Container createContainer() {
    	return (Container)createUIComponent(new Container());
    }
    
    public static Layout createGridLayout(int row, int coulmn, String name){
    	Layout layout  = createLayout();
    	layout.setName(name);
    	Container container = createContainer();
    	container.getTables().add(createTable(row, coulmn));
    	layout.setContainer(container);
    	return layout;
    }
    
    public static Layout createGridLayout10X2(){
    	return createGridLayout(10, 2, "Grid Layout 10 * 2");
    }
    
    public static Layout createGridLayout10X3(){
    	return createGridLayout(10, 3, "Grid Layout 10 * 3");
    }
    
    public static Layout createRowLayout(){
    	return createGridLayout(10, 1, "Row Layout");
    }
    
    public static Layout creatFlowLayout(){
    	return createGridLayout(1, 1, "Flow Layout");
    }
    
    public static WidgetSettingOption createWidgetSettingOption(WidgetType widgetType) {
    	WidgetSettingOption widgetSettingOption = new WidgetSettingOption();
    	widgetSettingOption.setId(uniqueID());
    	widgetSettingOption.setWidgetType(widgetType);
    	
    	return widgetSettingOption;
    }
    
    public static Setting createSetting() {
    	Setting setting = new Setting();
    	NameQuery namequery = new NameQuery();
    	setting.setId(uniqueID());
    	setting.setNameQuery(namequery);
    	
    	return setting;
    }
    
    public static Event createEvent() {
    	Event event = new Event();
    	Option option = new Option();
    	event.setId(uniqueID());
    	event.setAppContextVariable(option);

    	return event;
    }
    
    public static Filter createFilter() {
    	Filter filter = new Filter();
    	filter.setId(uniqueID());
    	return filter;
    }
    
    public static FilterCriteria createFilterCriteria() {
    	FilterCriteria filterCriteria= new FilterCriteria();
    	filterCriteria.setID(uniqueID());
    	return filterCriteria;
    }
    
    
    public static Option createOption() {
    	Option option= new Option();
    	return option;
    }
    
    
    public static NameQueryParam createNameQueryParam() {
    	NameQueryParam nameQueryParam = new NameQueryParam();
    	nameQueryParam.setId(uniqueID());
    	return nameQueryParam;
    }
}