package org.sapphire.appconsole.dao;

import java.util.List;

import org.sapphire.appconsole.dao.exception.ResourceNotFoundException;
import org.sapphire.appconsole.model.App;
import org.sapphire.appconsole.model.UIComponent;

public interface AppDao {
	public boolean save(String appJson) throws Exception;
	public List<UIComponent> list() throws Exception;
	public App get(String appid) throws ResourceNotFoundException, Exception;
	public boolean update(String app, String id) throws Exception;
	public boolean delete(String appID) throws Exception;
}
