package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.AbstractSite;
import data.ActivitySite;
import data.HistoricSite;
import data.Hotel;
import persistence.jdbc.Queries;

public class SimpleResultInitialisator {

	Queries queries = new Queries();
	
	public ArrayList<Hotel> initHotelList(int minPrice, int maxPrice){
		queries.searchHotelByPrice(minPrice, maxPrice);
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		ResultSet hotelsResult = queries.getResultsSet();
    	try {
			while(hotelsResult.next()){
		    	Hotel hotel = new Hotel();
				hotel.setName(hotelsResult.getString(2));
				hotel.setPrice(hotelsResult.getInt(3));
				hotel.setDescription(hotelsResult.getString(4));
				hotels.add(hotel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return hotels;
	}
	
	public ArrayList<AbstractSite> initSiteListint (int minPrice, int maxPrice){
		ArrayList<AbstractSite> sites = new ArrayList<AbstractSite>();
		queries.searchSitesByPrice(minPrice, maxPrice);
		ResultSet sitesResult = queries.getResultsSet();
    	try {
			while(sitesResult.next()){
		    	AbstractSite site;
		    	if(sitesResult.getString(3).equals("Historic")) {
		    		site = new HistoricSite();
		    	}
		    	else {
		    		site = new ActivitySite();
		    	}
		    	site.setName(sitesResult.getString(2));
		    	site.setPrice(sitesResult.getInt(4));
		    	
		    	File text = new File("data/"+sitesResult.getString(1)+".txt");
		    	BufferedReader br;
		    	String line ="", tmp;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(text), "UTF8"));
					
					while((tmp = br.readLine()) != null){
					    line += tmp;
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		    	site.setDescription(line);
		    	//site.setDescription(sitesResult.getString(4));
				sites.add(site);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return sites;
	}
}
