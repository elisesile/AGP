package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;

public class TestPrice {
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		Connection connection = JdbcConnection.getConnection();
		System.out.println("getConnection: " + connection);
		
		
		Queries queries = new Queries();
	//	PreparedStatement preparedStatement = null;
		
		ArrayList<Integer> idRides = new ArrayList<Integer>();
		idRides.add(1);
		idRides.add(2);
		
		int hotel = 17;
				
		int totalPrice = queries.getTotalPriceRidesAndHotel(idRides, hotel);
		System.out.println(totalPrice);
			
	}
}
