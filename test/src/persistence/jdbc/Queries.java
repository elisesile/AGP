package persistence.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class Queries {
	private PreparedStatement preparedStatement = null;
	private ResultSet resultsSet;

	
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
			return false;
		}
		return true;
	}
	
	
	public void executeQuery(String query) {
		try {
			Statement statement = (Statement) JdbcConnection.getConnection().createStatement();
			
			ResultSet result = statement.executeQuery(query);
			this.setResultsSet(result);
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	//SELECT * FROM hotel WHERE price <= ? AND price >= ?
	//SELECT * FROM site WHERE type = ?
	//SELECT * FROM site
	//SELECT * FROM site WHERE price <= ? AND price >= ?
	/*
	SELECT ride.id_ride, siteS.*, siteE.*, transport.*" + 
							" FROM ride" + 
							" INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
							" INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
							" INNER JOIN transport ON transport.id_transport = ride.id_transport;
	 * */
	/*
	SELECT transport.price, transport.is_per_km, coordS.latitude, coordS.longitude, coordE.latitude, coordE.longitude, siteS.price" + 
					" FROM ride" + 
					" INNER JOIN site AS siteS ON siteS.id_site = ride.departure_site" + 
					" INNER JOIN site AS siteE ON siteE.id_site = ride.arrival_site" + 
					" INNER JOIN coordinates AS coordS ON coordS.id_coordinates = siteS.id_coordinates" + 
					" INNER JOIN coordinates AS coordE ON coordE.id_coordinates = siteE.id_coordinates" + 
					" INNER JOIN transport ON transport.id_transport = ride.id_transport" + 
					" WHERE ride.id_ride IN (?)
	 * */
	/*
	 SELECT *" + 
					" FROM hotel" +  
					" WHERE hotel.id_hotel = (?)
	 * */
	//SELECT * FROM site ORDER BY type = ? DESC
	
	
	/**
	 * Insert lines in database to add a site
	 * 
	 * @param name
	 * @param type
	 * @param price
	 * @param latitude
	 * @param longitude
	 * 
	 * @return idSite
	 */
	public int addSite(String name, String type, int price, double latitude, double longitude) {
		int idSite = 0;
		try {
			String addCoordinates = "INSERT INTO coordinates (latitude, longitude) VALUES (?, ?)";
	
			this.preparedStatement = JdbcConnection.getConnection().prepareStatement(addCoordinates);
			
			this.preparedStatement.setDouble(1, latitude);
			this.preparedStatement.setDouble(2, longitude);
	
			int result = this.preparedStatement.executeUpdate();
			
			ResultSet keys = this.preparedStatement.getGeneratedKeys();
			keys.next();
			int idCoordinates = keys.getInt(1);
			this.closePreparedStatement();
			
			if(result != 0) {
				String addSite = "INSERT INTO site (name, type, price, id_coordinates) VALUES (?, ?, ?, ?)";
			
				this.preparedStatement = JdbcConnection.getConnection().prepareStatement(addSite);
				
				this.preparedStatement.setString(1, name);
				this.preparedStatement.setString(2, type);
				this.preparedStatement.setInt(3, price);
				this.preparedStatement.setInt(4, idCoordinates);
		
				result = this.preparedStatement.executeUpdate();
				
				keys = this.preparedStatement.getGeneratedKeys();
				keys.next();
				idSite = keys.getInt(1);
				this.closePreparedStatement();
			}
			else {
				return 0;
			}
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		
		return idSite;
	}

	/**
	 * @return the preparedStatement
	 */
	private PreparedStatement getPreparedStatement() {
		return preparedStatement;
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
