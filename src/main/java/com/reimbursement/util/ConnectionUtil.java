package com.reimbursement.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton utility for creating and retrieving database connection
 */


public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop;
	

	/**
	 * This method should read in the "database.properties" file and load the values
	 * into the Properties variable
	 */
	public ConnectionUtil() {
		super();
	}

	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}

	/**
	 * This method should create and return a Connection object
	 * 
	 * @return a Connection to the database
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		// Hint: use the Properties variable to setup your Connection object
		Connection conn = null;
		prop = new Properties();
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		
		try {
			prop.load(is);
			String url = prop.getProperty("url");
			String username = prop.getProperty("usr");
			String password = prop.getProperty("pswd");
            Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, username, password);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		return conn;
	}
}
