/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;

public class TestJdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = JdbcConnection.getConnection();
		System.out.println("getConnection: " + connection);
		
		Queries queries = new Queries();
		queries.executeQuery("SELECT * FROM hotel");
		ResultSet results = queries.getResultsSet();
		try {
			while(queries.nextIterator()) {
				int id = results.getInt(1);
				String name = results.getString(2);
				int price = results.getInt(3);
				String beachName = results.getString(4);
				
				System.out.println(id+" || "+name+ " || "+price+" || "+beachName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
