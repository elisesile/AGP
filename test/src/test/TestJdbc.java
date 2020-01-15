/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;

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
		
		Queries queries = new Queries();
		queries.searchHotelByPrice(100, 200);
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
		
	/*	Queries queries = new Queries();
		queries.getSitesOrderByActivity();
		ResultSet results = queries.getResultsSet();
		try {
			while(queries.nextIterator()) {
				//int id = results.getInt(1);
				String name = results.getString(1);
				//int price = results.getInt(3);
				String type = results.getString(2);
				
				System.out.println(name+ " || "+ type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		/*
		Queries queries = new Queries();
		PreparedStatement preparedStatement = null;
		ResultSet hotels = queries.searchHotelByPrice(preparedStatement, 100, 200);
		System.out.println("\t searchHotelByPrice");
		try {
			while(hotels.next()){ 
				int id = hotels.getInt(1);
				String name = hotels.getString(2);
				int price = hotels.getInt(3);
				String beachName = hotels.getString(4);
				
				System.out.println(id+" || "+name+ " || "+price+" || "+beachName); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet sites = queries.searchSiteByType(preparedStatement, "Historic");
		System.out.println("\t searchSiteByType");
		try {
			while(sites.next()){ 
				int id = sites.getInt(1);
				String name = sites.getString(2);
				String type = sites.getString(3);
				int price = sites.getInt(4);
				
				System.out.println(id+" || "+name+ " || "+type+" || "+price); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet allSites = queries.getSites(preparedStatement);
		System.out.println("\t getSites");
		try {
			while(allSites.next()){ 
				int id = allSites.getInt(1);
				String name = allSites.getString(2);
				String type = allSites.getString(3);
				int price = allSites.getInt(4);
				
				System.out.println(id+" || "+name+ " || "+type+" || "+price); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet sitesByPrice = queries.searchSitesByPrice(preparedStatement, 100, 200);
		System.out.println("\t searchSitesByPrice");
		try {
			while(sitesByPrice.next()){ 
				int id = sitesByPrice.getInt(1);
				String name = sitesByPrice.getString(2);
				String type = sitesByPrice.getString(3);
				int price = sitesByPrice.getInt(4);
				
				System.out.println(id+" || "+name+ " || "+type+" || "+price); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rides = queries.getRides(preparedStatement);
		System.out.println("\t getRides");
		try {
			while(rides.next()){ //ride.id_ride, siteS.*, siteE.*, transport.*
				int idRide = rides.getInt(1);
				int idSiteS = rides.getInt(2);
				String nameS = rides.getString(3);
				String typeS = rides.getString(4);
				int priceS = rides.getInt(5);
				int idCoordS = rides.getInt(6);
				int idSiteE = rides.getInt(7);
				String nameE = rides.getString(8);
				String typeE = rides.getString(9);
				int priceE = rides.getInt(10);
				int idCoordE = rides.getInt(11);
				int idT = rides.getInt(12);
				String typeT = rides.getString(13);
				int priceT = rides.getInt(14);
				
				System.out.println(idRide+" || "+idSiteS+ " || "+nameS+" || "+typeS+" || "+priceS+" || "+idCoordS
										+" || "+idSiteE+ " || "+nameE+" || "+typeE+" || "+priceE+" || "+idCoordE
										+" || "+idT+ " || "+typeT+" || "+priceT); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}

}
