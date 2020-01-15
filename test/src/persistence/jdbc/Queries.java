package persistence.jdbc;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import business.ExcursionCalculator;

import dao.QueriesPersistenceAPI;

public class Queries implements QueriesPersistenceAPI {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultsSet;

	
	/**
	 * Print an error messa ge to inform the user
	 */
	public void dataInit() {
		System.err.println("Please don't forget to create tables manually by importing config/create_tables.sql and config/insert_tahiti.sql in your database !");
	}
	
	/**
	 * Close the preparedStatement
	 */
	private void closePreparedStatement() {
		try {
			this.getPreparedStatement().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the iterator to the first element
	 * 
	 * @param results
	 */
	public void initIterator(ResultSet results){
		this.setResultsSet(results);
	}
	
	/**
	 * Get the next element of the iterator
	 */
	public boolean nextIterator(){
		boolean currentElement = false;
		try {
			currentElement = this.getResultsSet().next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(currentElement == false){
			this.closePreparedStatement();
			return false;
		}
		return true;
	}

	
	/**
	 * Search by hotel price range
	 * 
	 * @param startRange
	 * @param endRange
	 */
	public void searchHotelByPrice(int startRange, int endRange) {
		try {
			String query = "SELECT * FROM hotel WHERE price <= ? AND price >= ?";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.preparedStatement.setInt(1, endRange);
			this.preparedStatement.setInt(2, startRange);
	
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	/**
	 * Get sites with the correspond type in database
	 * 
	 * @param type
	 */
	public void searchSiteByType(String type) {
		try {
			String query = "SELECT * FROM site WHERE type = ?";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.preparedStatement.setString(1, type);
	
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	/**
	 * Get all sites in database
	 * 
	 * @return result
	 */
	public void getSites() {
		try {
			String query = "SELECT * FROM site";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	/**
	 * Search by site price range
	 * 
	 * @param startRange
	 * @param endRange
	 * 
	 * @return result
	 */
	public void searchSitesByPrice(int startRange, int endRange) {
		try {
			String query = "SELECT * FROM site WHERE price <= ? AND price >= ?";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.preparedStatement.setInt(1, endRange);
			this.preparedStatement.setInt(2, startRange);
	
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	/**
	 * Get all rides (with information about sites and transport)
	 * 
	 * @param startRange
	 * @param endRange
	 * 
	 * @return result
	 */
	public void getRides() {
		try {
			String query = "SELECT ride.id_ride, siteS.*, siteE.*, transport.*" + 
							" FROM ride" + 
							" INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
							" INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
							" INNER JOIN transport ON transport.id_transport = ride.id_transport;";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
	
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
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
	
	public int getTotalPriceRidesAndHotel(ArrayList<Integer> idRides, int idHotel) {
		ResultSet result = null;
		ResultSet result1 = null;
		int totalPrice = 0;
		try {

			String query =	"SELECT transport.price, transport.id_transport, coordS.latitude, coordS.longitude, coordE.latitude, coordE.longitude, siteS.price" + 
					" FROM ride" + 
					" INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
					" INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
					" INNER JOIN coordinates AS coordS ON coordS.id_coordinates = siteS.id_coordinates" + 
					" INNER JOIN coordinates AS coordE ON coordE.id_coordinates = siteE.id_coordinates" + 
					" INNER JOIN transport ON transport.id_transport = ride.id_transport" + 
					" WHERE ride.id_ride IN (?)";
			
			for(int ride : idRides) {
				
				this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
				
				this.preparedStatement.setInt(1, ride);
				result = this.preparedStatement.executeQuery();
				
				result.next();
								
				totalPrice += result.getInt(7);
			    
			    if(result.getInt(2)==1) {   	
			    	
			    	totalPrice += (int) (business.ExcursionCalculator.getDistanceKM(result.getDouble(3),result.getDouble(4),result.getDouble(5),result.getDouble(6)) * result.getInt(1));
			    }else {
			    	totalPrice += result.getInt(1);
			    }
			    
			    this.preparedStatement.close();
			}

			query =	"SELECT *" + 
					" FROM hotel" +  
					" WHERE hotel.id_hotel = (?)";

			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			this.preparedStatement.setInt(1, idHotel);
	
			result1 = this.preparedStatement.executeQuery();
			result1.next();
			
			totalPrice += result1.getInt(3);
	
			this.preparedStatement.close();
			
			
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return totalPrice;
	}

	/**
	 * Get all sites in database
	 * 
	 * @return result
	 */
	public void getSitesOrderByActivity() {
		try {
			String query = "SELECT name, type FROM site ORDER BY type";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	/**
	 * @return the preparedStatement
	 */
	private PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}


	/**
	 * @param preparedStatement the preparedStatement to set
	 */
	private void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}


	/**
	 * @return the resultsSet
	 */
	public ResultSet getResultsSet() {
		return resultsSet;
	}


	/**
	 * @param resultsSet the resultsSet to set
	 */
	private void setResultsSet(ResultSet resultsSet) {
		this.resultsSet = resultsSet;
	}
	
}
