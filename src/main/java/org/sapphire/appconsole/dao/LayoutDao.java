package org.sapphire.appconsole.dao;

import java.util.List;

import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.model.Layout;
import org.sapphire.appconsole.model.UIComponent;

public interface LayoutDao {
	public boolean save(String layoutJson) throws Exception;
	public Layout get(String widgetID) throws ResourceNotFoundException, Exception;
	public List<UIComponent> list() throws Exception;
	public boolean update(String app, String id) throws Exception;
	public boolean delete(String layoutID) throws Exception;
}
