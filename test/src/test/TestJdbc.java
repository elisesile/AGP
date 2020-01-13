/**
 * 
 */
package test;

import java.sql.Connection;

import persistence.jdbc.JdbcConnection;

/**
 * @author Anne-Sophie
 *
 */
public class TestJdbc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = JdbcConnection.getConnection();
		System.out.println("getConnection: " + connection);
		
	}

}
