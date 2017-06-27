package org.sapphire.appconsole.dao;

import java.util.List;
import java.util.Map;

import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.model.Setting;
import org.sapphire.appconsole.model.WidgetDataType;
import org.sapphire.appconsole.model.WidgetSettingOption;

public interface WidgetSettingOptionDao {
	public boolean save(String widgetJson) throws Exception;
	public WidgetSettingOption get(String widgetsettingOptionID) throws ResourceNotFoundException, Exception;
	public List<Setting> getSetting(String widgetType) throws Exception;
	public Map<WidgetDataType, List<String>> getSupportedOperators() throws Exception;
	public List<WidgetSettingOption> list() throws Exception;
	public boolean update(String widget, String id) throws Exception;
	public boolean delete(String widgetSettingOptionId) throws Exception;

}
