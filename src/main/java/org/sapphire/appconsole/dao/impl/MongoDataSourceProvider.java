package org.sapphire.appconsole.dao.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.sapphire.appconsole.errorHandler.AppExceptionMapper;
import org.sapphire.appconsole.errorHandler.ErrorHandler;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.MongoClientOptions.Builder;

public class MongoDataSourceProvider  extends BaseDao {
	private final static Logger LOG = Logger.getLogger(MongoDataSourceProvider.class) ;

	private	MongoClient mongoClient;
	private  String dbName;
	private  String host;
	private int port;
	
	/**
	 * This is the default constructor which will create connection with dbName and HostName
	 * @param dbName - The database name
	 * @param host - The host name for the connection
	 */
	public MongoDataSourceProvider(String dbName, String host) 
	{
		setDbName(dbName);
		setHost(host);
		System.out.println(port);
		try
		{
			LOG.info("MongoDataSourceProvider::Creating Connection::Line No 40");
			createConn();
		}
		catch(Exception e)
		{
			LOG.error("Error while creating Database Connection",e);
			e.printStackTrace();
			String JsonErrorMessage = "";
			try {
				JsonErrorMessage = new ObjectMapper().writeValueAsString(new ErrorHandler("Error while creating Database Connection",500));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				LOG.error("Error while writing json Error Message in MongoDataSourceProvider at Line No 54",e);
				e1.printStackTrace();
			}
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}	
	}
	
	/**
	 * This is the overloaded constructor which will create connection with dbName and HostName and PortNo
	 * @param dbName - The database name
	 * @param host - The hostName for the connection
	 * @param port - The port No
	 */
	public MongoDataSourceProvider(String dbName, String host,int portNo) 
	{
		setDbName(dbName);
		setHost(host);
		setPort(portNo);
		
		try
		{
			createConnWithPort();
		}
		catch(Exception e)
		{
			LOG.error("Error while creating Database Connection with Port No",e);
			e.printStackTrace();
			String JsonErrorMessage = "";
			try 
			{
				JsonErrorMessage = new ObjectMapper().writeValueAsString(new ErrorHandler("Error while creating Database Connection with Port No",500));
			} 
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				LOG.error("Error while writing json Error Message in MongoDataSourceProvider at Line No 87",e);
				e1.printStackTrace();
			}
			throw new AppExceptionMapper(Response.Status.INTERNAL_SERVER_ERROR,JsonErrorMessage);
		}
	}
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	private void createConn()
			throws UnknownHostException {

		if (mongoClient == null){
			
			MongoClientOptions options = new Builder()
			.connectionsPerHost(5)
			.maxConnectionIdleTime(5)
			.readPreference(ReadPreference.secondaryPreferred())
			.build();
			
			mongoClient = new MongoClient(host, options);
		}
	}
	
	private void createConnWithPort()
			throws UnknownHostException {

		if (mongoClient == null){
			
			mongoClient = new MongoClient(host, port);
		}
	}

	public boolean insertJson(String colName, String jsonStr) throws Exception {
		LOG.debug("MongoDataSourceProvider::insertJson :: ", jsonStr);

		if (mongoClient == null && port <= 0){
			LOG.info("Connection being created with Default port No");
			createConn();
		}
		else if(mongoClient == null && port > 0)
		{
			LOG.info("Connection being created with port No::"+Integer.toString(port));
			createConnWithPort();
		}

		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);
		LOG.info("Data to be Inserted ::::" + jsonStr);
		BasicDBObject dbObj = (BasicDBObject)com.mongodb.util.JSON.parse(jsonStr);
		col.save(dbObj); 
		return true;
	}

	public List<String> search(String colName) throws Exception {
		List<String> results = new java.util.ArrayList<String>();
		String jsonResult = null;
		DBCursor cursor;

		if (mongoClient == null && port <= 0){
			LOG.info("Connection being created with Default port No");
			createConn();
		}
		else if(mongoClient == null && port > 0)
		{
			LOG.info("Connection being created with port No::"+Integer.toString(port));
			createConnWithPort();
		}

		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);
		cursor = col.find();

		while (cursor.hasNext()){
			jsonResult = cursor.next().toString();
			results.add(jsonResult);
		}

		cursor.close();
		return results;
	}


	public String getById(String collectionName, String id) throws Exception {
		LOG.debug("MongoDataSourceProvider::getUIComponentByID :: ", id);
		String uiComponentJson = null;

		BasicDBObject whereQuery = new BasicDBObject();

		if(collectionName == COLLECTION_WIDGET_SETTING_OPTION){
			whereQuery.put(WIDGET_SETTING_OPTION_ID, id);
		}
		else{
			whereQuery.put(COMPONENT_ID, id);	
		}
		uiComponentJson = get(collectionName, whereQuery);
		LOG.info("getById json ::" + uiComponentJson);

		return uiComponentJson;
	}

	public String get(String collectionName, DBObject whereQuery) throws Exception {
		LOG.debug("MongoDataSourceProvider::get :: ", collectionName);
		String uiComponentJson = null;
		DBCursor cursor;

		if (mongoClient == null && port <= 0){
			LOG.info("Connection being created with Default port No");
			createConn();
		}
		else if(mongoClient == null && port > 0)
		{
			LOG.info("Connection being created with port No::"+Integer.toString(port));
			createConnWithPort();
		}

		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(collectionName);
		cursor = col.find(whereQuery);

		if (cursor.hasNext()){
			uiComponentJson = cursor.next().toString();
			LOG.info("Got App and ID is ::" + uiComponentJson);
		}

		cursor.close();

		return uiComponentJson;
	}

	public boolean update(String colName, String jsonStr, String Id) throws Exception {
		LOG.debug("MongoDataSourceProvider::update :: ", Id);

		if (mongoClient == null && port <= 0){
			LOG.info("Connection being created with Default port No");
			createConn();
		}
		else if(mongoClient == null && port > 0)
		{
			LOG.info("Connection being created with port No::"+Integer.toString(port));
			createConnWithPort();
		}

		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);

		BasicDBObject conditionDBObject = new BasicDBObject();

		if(colName == COLLECTION_WIDGET_SETTING_OPTION){
			conditionDBObject.put(WIDGET_SETTING_OPTION_ID, Id);	
		}else{			
			conditionDBObject.put(COMPONENT_ID, Id);
		}

		LOG.info("Update record  ::::" + jsonStr);
		BasicDBObject updateDBObject = (BasicDBObject)com.mongodb.util.JSON.parse(jsonStr);

		col.update(conditionDBObject, updateDBObject);

		return true;
	}

	public boolean delete(String colName, String id) throws Exception {

		if (mongoClient == null && port <= 0){
			LOG.info("Connection being created with Default port No");
			createConn();
		}
		else if(mongoClient == null && port > 0)
		{
			LOG.info("Connection being created with port No::"+Integer.toString(port));
			createConnWithPort();
		}

		BasicDBObject deleteQuery = new BasicDBObject();

		if(colName == COLLECTION_WIDGET_SETTING_OPTION){
			deleteQuery.put(WIDGET_SETTING_OPTION_ID, id);
		}
		else{
			deleteQuery.put(COMPONENT_ID, id);	
		}

		DB db = mongoClient.getDB(dbName);
		DBCollection col = db.getCollection(colName);
		col.remove(deleteQuery);

		return true;		
	}

}
