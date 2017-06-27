package org.sapphire.appconsole.dao;

import java.util.List;
import java.util.Map;

import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.model.EventActionType;
import org.sapphire.appconsole.model.EventOptionType;
import org.sapphire.appconsole.model.Widget;
import org.sapphire.appconsole.model.UIComponent;

public interface WidgetDao {
	public boolean save(String widgetJson) throws Exception;
	public Widget get(String widgetID) throws ResourceNotFoundException, Exception;
	public List<UIComponent> list() throws Exception;
	public Map<EventOptionType, String> getEventOptions() throws Exception;
	public Map<EventActionType, String> getEventActions() throws Exception;
	public boolean update(String widget, String id) throws Exception;
	public boolean delete(String widgetID) throws Exception;

}
