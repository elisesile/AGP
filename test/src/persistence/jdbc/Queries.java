package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.QueriesPersistenceAPI;

public class Queries implements QueriesPersistenceAPI {
	
	/**
	 * Print an error message to inform the user
	 */
	public void dataInit() {
		System.err.println("Please don't forget to create tables manually by importing config/create_tables.sql and config/insert_tahiti.sql in your database !");
	}

	
	/**
	 * Search by hotel price range
	 * 
	 * @param startRange
	 * @param endRange
	 * 
	 * @return result
	 */
	public ResultSet searchHotelByPrice(int startRange, int endRange) {
		ResultSet result = null;
		try {
			String query = "SELECT *"
							+ "FROM hotel"
							+ "WHERE price <= ? AND price >= ?";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setInt(1, endRange);
			preparedStatement.setInt(2, startRange);
	
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/**
	 * Get sites with the correspond type in database
	 * 
	 * @param type
	 * 
	 * @return result
	 */
	public ResultSet searchSiteByType(String type) {
		ResultSet result = null;
		try {
			String query = "SELECT *"
							+ "FROM site"
							+ "WHERE type = ?";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, type);
	
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/**
	 * Get all sites in database
	 * 
	 * @return result
	 */
	public ResultSet getSites() {
		ResultSet result = null;
		try {
			String query = "SELECT *"
							+ "FROM site";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/**
	 * Search by site price range
	 * 
	 * @param startRange
	 * @param endRange
	 * 
	 * @return result
	 */
	public ResultSet searchSitesByPrice(int startRange, int endRange) {
		ResultSet result = null;
		try {
			String query = "SELECT *"
							+ "FROM site"
							+ "WHERE price <= ? AND price >= ?";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setInt(1, endRange);
			preparedStatement.setInt(2, startRange);
	
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/**
	 * Get all rides (with information about sites and transport)
	 * 
	 * @param startRange
	 * @param endRange
	 * 
	 * @return result
	 */
	public ResultSet getRides() {
		ResultSet result = null;
		try {
			String query = "SELECT ride.id_ride, siteS.*, siteE.*, transport.*" + 
							"FROM ride" + 
							"INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
							"INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
							"INNER JOIN transport ON transport.id_transport = ride.id_transport;";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
	
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	
	/*
	private ResultSet getTotalPriceForHotelAndRides(int idHotel, ArrayList<Integer> idSites) {
		ResultSet result = null;
		try {
			String query = "SELECT SUM(total_price)" + 
						"FROM (" + 
							"SELECT (SUM(siteS.price)+SUM(siteE.price)+SUM(transport.price)) AS total_price" + 
							"FROM ride" + 
							"INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
							"INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
							"INNER JOIN coordinates AS coordS ON coordS.id_coordinates = siteS.id_coordinates" + 
							"INNER JOIN coordinates AS coordE ON coordE.id_coordinates = siteE.id_coordinates" + 
							"INNER JOIN transport ON transport.id_transport = ride.id_transport" + 
							"WHERE ride.id_ride IN (?)" + 
							"UNION" + 
							"SELECT price AS total_price" + 
							"FROM hotel" + 
							"WHERE id_hotel = ?" + 
						") AS tables";
	
			PreparedStatement preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			Array array = JdbcConnection.getConnection().createArrayOf("Integer", idRides.toArray());
			preparedStatement.setArray(1, array);
			preparedStatement.setInt(2, idHotel);
			
			result = preparedStatement.executeQuery();
	
			preparedStatement.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return result;
	}
	*/
}
