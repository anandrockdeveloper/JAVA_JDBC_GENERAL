package com.anand.jdbcconnection;

import java.io.FileNotFoundException;
import java.sql.Connection;

public class DbConnectivityPoJo {
	
	private String propertyFilename;
	
	private String propertyPrefixString;
	
	private String dbDriverClass;
	
	private String dbUrl;
	
	private String dbUserName;
	
	private String dbPassword;
	
	private String dbConnectivityException;
	
	private boolean dbConnectionStatus;
	
	private Connection dbConnection;
	
	
	
	 protected String getPropertyFilename() {
		return propertyFilename;
	}

	public void setPropertyFilename(String propertyFilename) {
		this.propertyFilename = propertyFilename;
	}

	protected String getPropertyPrefixString() {
		return propertyPrefixString;
	}

	public void setPropertyPrefixString(String prefixString) {
		this.propertyPrefixString = prefixString;
	}

	protected String getDbDriverClass() {
		return dbDriverClass;
	}

	public void setDbDriverClass(String dbDriverClass) {
		this.dbDriverClass = dbDriverClass;
	}

	protected String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	protected String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	protected String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbConnectivityException() {
		return dbConnectivityException;
	}

	public void setDbConnectivityException(String dbConnectivityException) {
		this.dbConnectivityException = dbConnectivityException;
	}

	public boolean getDbConnectionStatus() {
		return dbConnectionStatus;
	}

	protected void setDbConnectionStatus(boolean dbConnectionStatus) {
		this.dbConnectionStatus = dbConnectionStatus;
	}

	public Connection getDbConnection() {
		return dbConnection;
	}

	protected void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}	
}
