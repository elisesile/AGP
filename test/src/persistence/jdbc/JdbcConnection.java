package persistence.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Scanner;

public class JdbcConnection {
	private static JdbcConnection jdbcConnection = new JdbcConnection();
	private static Connection connection;
		
	/**
	* Get the connection
	* 
	* @return connection or null
	*/
	public static Connection getConnection(){
		if (connection == null) {
			try {
				ArrayList<String> arrayInformation = jdbcConnection.readConnectionInfo("src/persistence/config/connectiondb.conf");
				if(arrayInformation != null) {
					DriverManager.registerDriver(new com.mysql.jdbc.Driver());
					connection = DriverManager.getConnection(arrayInformation.get(2), arrayInformation.get(0), arrayInformation.get(1));
				}
				else {
					return null;
				}
				
			} catch (Exception e) {
				System.err.println("ERROR: Connection failed: " + e.getMessage());
			}
		}
		return connection; 
	}
	
	
	/**
	* Read a file to get database information
	* 
	* @param fileName the path and file name to read
	* 
	* @return arrayInformation or null
	*/
	private ArrayList<String> readConnectionInfo(String fileName){
		File file = new File(fileName);
		
		System.out.println(file.getAbsolutePath());
		Scanner reader = null;
		ArrayList<String> arrayInformation =  new ArrayList<String>();
		String dbname = null, hostname = null;
		
		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found " + e.getMessage());
		}
		
		while(reader.hasNextLine()){
			String line = reader.nextLine();
			
			if(line.contains("hostname")){
				hostname = line.split("=")[1];
				hostname = hostname.split(";")[0];
			}
			if(line.contains("dbname")){
				dbname = line.split("=")[1];
				dbname = dbname.split(";")[0];
			}
			if(line.contains("username")){
				String username = line.split("=")[1];
				username = username.split(";")[0];
				arrayInformation.add(username);
			}
			if(line.contains("password")){
				String password = line.split("=")[1];
				password = password.split(";")[0];
				if(password.equals(" ")) {
					password = null;
				}
				arrayInformation.add(password);
			}
		}
		reader.close();
		
		if(hostname != null && dbname != null) {
			arrayInformation.add("jdbc:mysql://" + hostname + "/" + dbname);
			
			return arrayInformation;
		}
		return null;
	}
}