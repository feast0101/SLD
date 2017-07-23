package com.kmbt.csa.dav.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("graphDao")
public class GraphDaoImpl{
	private static final Logger logger = Logger.getLogger(GraphDaoImpl.class);
	public static Properties pl = new Properties();
	//@Value("${jdbc.driverClassName}")  
	private static String DB_DRIVER; 
	//@Value("${jdbc.url}")  
	private static String DB_CONNECTION; 
	//@Value("${jdbc.username}") 
	private static String DB_USER; 
	//@Value("${jdbc.password}") 
	private static String DB_PASSWORD; 
	//@Value("${tableNamePattern1}") 
	private static String tableNamePattern; 

	public String[] getResourceData() throws IOException{
		String[] tables =new String [3];
		InputStream is = this.getClass().getResourceAsStream("/database.properties");
		pl.load(is);
	    DB_DRIVER= (String) pl.get("jdbc.driverClassName");
	    DB_CONNECTION= (String) pl.get("jdbc.url");
	    DB_USER = (String) pl.get("jdbc.username");
	    DB_PASSWORD = (String) pl.get("jdbc.password");
	    tables[0]=(String) pl.get("tableNamePattern1");
	    tables[1]=(String) pl.get("tableNamePattern2");
	    tables[2]=(String) pl.get("tableNamePattern3");
	    return tables;
	}
	public String[] getMetaData(String tableName) throws SQLException, IOException {
		tableNamePattern = tableName;
		String catalog = null;
		String schemaPattern = null;
		String[] columns = null;
		String columnNamePattern = null;
		String[] types = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = getDBConnection();
			DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
		
			ResultSet result2 = databaseMetaData.getColumns(catalog,
					schemaPattern, tableNamePattern, columnNamePattern);
			result2.last();
			columns = new String[result2.getRow()];
			ResultSet result3 = databaseMetaData.getColumns(catalog,
					schemaPattern, tableNamePattern, columnNamePattern);
			int i = 0;
			while (result3.next()) {
				String columnName = result3.getString(4);
				logger.debug(columnName + " " + result3.getType());
				columns[i] = columnName;
				i++;
			}

		} finally {
			
			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return columns;
	}

	public String[][] getData(String[] columns) throws SQLException {

		String[][] json = null;
		ResultSet rs = null;
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectTableSQL = "SELECT * FROM DBO." + tableNamePattern;

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectTableSQL);
			rs = preparedStatement.executeQuery();
			rs.last();
			json = new String[rs.getRow() + 1][columns.length];

			rs = preparedStatement.executeQuery();
			logger.info("All Records are retrieved from "+tableNamePattern);
			int j = 0;
			json[j] = columns;
			while (rs.next()) {
				String[] data = new String[columns.length];
				for (int i = 0; i < columns.length; i++) {
					data[i] = rs.getString(columns[i]);
					logger.debug(rs.getString(columns[i]));
				}
				json[++j] = data;
			}
		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return json;
	}

	private static Connection getDBConnection() {

		Connection dbConnection = null;
		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
					DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

}
