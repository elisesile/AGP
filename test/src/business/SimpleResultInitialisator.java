package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;

import business.data.AbstractSite;
import business.data.ActivitySite;
import business.data.HistoricSite;
import business.data.Hotel;
import persistence.QueriesProcess;
import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;

public class SimpleResultInitialisator {

	private Queries queries = new Queries();
	
	public SimpleResultInitialisator() {
		
	}
	
	public ArrayList<Hotel> initHotelList(int minPrice, int maxPrice){
		String query = "SELECT * FROM Hotel WHERE price<="+maxPrice+" AND price>="+minPrice;
		queries.executeQuery(query);
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
	
	public ArrayList<AbstractSite> initSiteList (int minPrice, int maxPrice){
		String query = "SELECT * FROM Site WHERE price<="+maxPrice+" AND price>="+minPrice;
		ArrayList<AbstractSite> sites = new ArrayList<AbstractSite>();
		queries.executeQuery(query);
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
	
	public ArrayList<AbstractSite> initSiteListLucene(int minPrice, int maxPrice,String keywords){
		ArrayList<AbstractSite> sites = new ArrayList<AbstractSite>();
		ArrayList<BigDecimal> keys;
		
		try {
			HashMap<BigDecimal, HashMap<String, String>> map = QueriesProcess.getInstance().mergeQueries("SELECT * FROM Site WHERE price<="+maxPrice+" AND price>="+minPrice+" WITH "+keywords);
			keys = QueriesProcess.getInstance().generateAndSortScoresArrayList();
			for(BigDecimal key:keys) {
				HashMap<String,String> currentMap = map.get(key);
				AbstractSite site;
				if(currentMap.get("type").equals("Historic")) {
					site = new HistoricSite();
		    	}
		    	else {
		    		site = new ActivitySite();
		    	}
				site.setName(currentMap.get("name"));
				site.setPrice(Integer.parseInt(currentMap.get("price")));
				//sitesInformations.put("id_coordinates", String.valueOf(id_coordinates));
				
				File text = new File("data/"+currentMap.get("id_site")+".txt");
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
		    	sites.add(site);
			}
		} catch (CorruptIndexException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		return sites;
	}
}
