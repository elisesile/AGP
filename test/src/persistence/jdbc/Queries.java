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
	 * Print an error message to inform the user
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
	
	public void getRidesCoordAndTransportTypePrice(ArrayList<Integer> idRides) {
		try {
			String query =	"SELECT transport.price, coordS.latitude, coordS.longitude, coordE.latitude, coordE.longitude" + 
							" FROM ride" + 
							" INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
							" INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
							" INNER JOIN coordinates AS coordS ON coordS.id_coordinates = siteS.id_coordinates" + 
							" INNER JOIN coordinates AS coordE ON coordE.id_coordinates = siteE.id_coordinates" + 
							" INNER JOIN transport ON transport.id_transport = ride.id_transport" + 
							" WHERE ride.id_ride IN (?)";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			Array array = JdbcConnection.getConnection().createArrayOf("Integer", idRides.toArray());
			this.preparedStatement.setArray(1, array);
			
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public void getHotel(int idHotel) {
		try {
			String query =	"SELECT *" + 
							" FROM hotel" +  
							" WHERE id_hotel = (?)";
			
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(query);
			
			this.preparedStatement.setInt(1, idHotel);
			
			this.initIterator(this.preparedStatement.executeQuery());
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
	
	public int getTotalPriceHotelAndRides(ResultSet rides, ResultSet hotel) throws SQLException {
		int price = 0;
		
		System.out.println("coucou");
		
		try {
			
			System.out.println("coucou");
			
 			while (rides.next()) {
				int transportPrice = rides.getInt(1);
				double latDeparture = rides.getDouble(2); 
				double longDeparture = rides.getDouble(3);
				double latArrival = rides.getDouble(4);
				double longArrival = rides.getDouble(5);
				
				System.out.println(transportPrice);
				
				System.out.println(latDeparture);
				
				price += (int) ExcursionCalculator.getDistanceKM(latDeparture,longDeparture,latArrival,longArrival)*transportPrice;
			
				System.out.println(price);
				
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		
		hotel.next();
		int hotelPrice = hotel.getInt(3);
		
		price += hotelPrice;
		
		return price;
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
