package com.anand.jdbcconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class DbConnection {

	public DbConnectivityPoJo connectToDb(DbConnectivityPoJo mDbConnectivityPoJo)
	{
		DbConnectionImpl dbConnection= new DbConnectionImpl(mDbConnectivityPoJo);
		return dbConnection.getConnection();

	}

	private class DbConnectionImpl{

		private DbConnectivityPoJo mDbConnectivityPoJo;
		private DbConnectivityPoJo cloneDbConnectivityPoJo;

		DbConnectionImpl(DbConnectivityPoJo mDbConnectivityPoJo)
		{
			this.cloneDbConnectivityPoJo=mDbConnectivityPoJo;
			this.mDbConnectivityPoJo=mDbConnectivityPoJo;
		}

		public DbConnectivityPoJo getConnection() {
			try {
				System.out.println("Get Connection");
				if(cloneDbConnectivityPoJo.getPropertyFilename()!=null)
				{
					readPropertiesFromFile();
				}

				//Connect to DB
				if(cloneDbConnectivityPoJo.getDbDriverClass()!=null &&
						cloneDbConnectivityPoJo.getDbUrl()!=null &&
						cloneDbConnectivityPoJo.getDbUserName()!=null &&
						cloneDbConnectivityPoJo.getDbPassword()!=null)
				{
					Class.forName(cloneDbConnectivityPoJo.getDbDriverClass());
					Connection con=DriverManager.getConnection(cloneDbConnectivityPoJo.getDbUrl(),cloneDbConnectivityPoJo.getDbUserName(),cloneDbConnectivityPoJo.getDbPassword());
					verifyConnection(con);
				}
				else{
					ArrayList<String> buffer= new ArrayList<String>();
					if(cloneDbConnectivityPoJo.getDbDriverClass()==null) buffer.add("Driver Class is null");
					if(cloneDbConnectivityPoJo.getDbUrl()==null) buffer.add("DB URL is null");
					if(cloneDbConnectivityPoJo.getDbUserName()==null) buffer.add("DB UserName is null");
					if(cloneDbConnectivityPoJo.getDbPassword()==null) buffer.add("DB Password is null");
					throw new Exception(buffer.toString());
				}

			} catch (Exception e) {
				mDbConnectivityPoJo.setDbConnectionStatus(false);
				mDbConnectivityPoJo.setDbConnectivityException(e.getMessage());
			}

			return mDbConnectivityPoJo;
		}

		private void verifyConnection(Connection con) throws SQLException {

			String querry=new String("select 'Hello' from dual");
			Statement stmt= con.createStatement();
			ResultSet result=stmt.executeQuery(querry);
			String d1="";
			while(result.next())
			{
				d1=result.getString(1);
			}
			stmt.close();
			if(d1.equals("Hello"))
			{
				mDbConnectivityPoJo.setDbConnection(con);
				mDbConnectivityPoJo.setDbConnectionStatus(true);
			}
			else{
				throw new SQLException("Keyword Expected is Hello");
			}

		}

		private void readPropertiesFromFile() throws IOException {
			InputStream inputfile= new FileInputStream(cloneDbConnectivityPoJo.getPropertyFilename());
			Properties prop = new Properties();
			prop.load(inputfile);

			if(cloneDbConnectivityPoJo.getPropertyPrefixString()!=null)
			{
				String prefix=cloneDbConnectivityPoJo.getPropertyPrefixString();
				cloneDbConnectivityPoJo.setDbDriverClass(validateProperties(prop.getProperty(prefix+"_"+"db_driver_class")));
				cloneDbConnectivityPoJo.setDbUrl(validateProperties(prop.getProperty(prefix+"_"+"db_url")));
				cloneDbConnectivityPoJo.setDbUserName(validateProperties(prop.getProperty(prefix+"_"+"db_userName")));
				cloneDbConnectivityPoJo.setDbPassword(validateProperties(prop.getProperty(prefix+"_"+"db_password")));
			}
			else{
				cloneDbConnectivityPoJo.setDbDriverClass(validateProperties(prop.getProperty("db_driver_class")));
				cloneDbConnectivityPoJo.setDbUrl(validateProperties(prop.getProperty("db_url")));
				cloneDbConnectivityPoJo.setDbUserName(validateProperties(prop.getProperty("db_userName")));
				cloneDbConnectivityPoJo.setDbPassword(validateProperties(prop.getProperty("db_password")));
			}
		}

		private String validateProperties(String propertyValue)
		{
			if(propertyValue!=null)
			{
				if(propertyValue.length()>1)
					return propertyValue;
			}

			return null;

		}

	}

}
