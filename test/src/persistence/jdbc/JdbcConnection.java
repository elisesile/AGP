package persistence.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class JdbcConnection {
	private static JdbcConnection jdbcConnection = new JdbcConnection();
	private static Connection connection;

	private String hostname;
	private String dbname;
	private String username;
	private String password;
	
	
	/**
	* Get the connection
	* 
	* @return connection
	*/
	public static Connection getConnection(){
		if (connection == null) {
			try {
				jdbcConnection.readConnectionInfo("persistence/config/connectiondb.conf");
				
				String hostname = jdbcConnection.getHostname();
				String dbname = jdbcConnection.getDbName();
				String username = jdbcConnection.getUsername();
				String password = jdbcConnection.getPassword();
				
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				connection = DriverManager.getConnection("jdbc:mysql://" + hostname + "/" + dbname, username, password);
			} catch (Exception e) {
				System.err.println("ERROR: Connection failed: " + e.getMessage());
			}
		}
		return connection;
	}
	
	/**
	* Get the hostname
	* 
	* @return hostname
	*/
	private String getHostname(){
		return this.hostname;
	}
	
	/**
	* Get the dbname
	* 
	* @return dbname 
	*/
	private String getDbName(){
		return this.dbname;
	}
	
	/**
	* Get the username
	* 
	* @return username 
	*/
	private String getUsername(){
		return this.username;
	}
	
	/**
	* Get the password
	* 
	* @return password 
	*/
	private String getPassword(){
		return this.password;
	}
	
	/**
	* Set the connection
	* 
	* @param connection 
	*/
	private void setConnection(Connection connection){
		JdbcConnection.connection = connection;
	}
	
	/**
	* Set the hostname
	* 
	* @param hostname 
	*/
	private void setHostname(String hostname){
		this.hostname = hostname;
	}
	
	/**
	* Set the dbname
	* 
	* @param dbname 
	*/
	private void setDbName(String dbname){
		this.dbname = dbname;
	}
	
	/**
	* Set the username
	* 
	* @param username 
	*/
	private void setUsername(String username){
		this.username = username;
	}
	
	/**
	* Set the password
	* 
	* @param password 
	*/
	private void setPassword(String password){
		this.password = password;
	}
	
	
	/**
	* Read a file to get database information
	* 
	* @param fileName the path and file name to read
	*/
	private void readConnectionInfo(String fileName){
		File file = new File(fileName);
		Scanner reader;
		try {
			reader = new Scanner(file);
		
			while(reader.hasNext()){
				String line = reader.next();
				
				if(line.contains("hostname")){
					String hostname = line.split("=")[1];
					hostname = hostname.split(";")[0];
					this.setHostname(hostname);
				}
				if(line.contains("dbname")){
					String dbname = line.split("=")[1];
					dbname = dbname.split(";")[0];
					this.setDbName(dbname);
				}
				if(line.contains("username")){
					String username = line.split("=")[1];
					username = username.split(";")[0];
					this.setUsername(username);
				}
				if(line.contains("password")){
					String password = line.split("=")[1];
					password = password.split(";")[0];
					this.setPassword(password);
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found " + e.getMessage());
		}
	}
}
