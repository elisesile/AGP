package beans;

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

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import data.AbstractSite;
import data.ActivitySite;
import data.HistoricSite;
import data.Hotel;
import persistence.jdbc.Queries;

@ManagedBean
@RequestScoped
public class SimpleResultBean {
    
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private List<AbstractSite> sites = new ArrayList<AbstractSite>();
	private Hotel selectedHotel;
	private AbstractSite selectedSite;
	
	@ManagedProperty(value="#{simpleSearchBean}")
	private SimpleSearchBean simpleSearchBean;
     
    @PostConstruct
    public void init() {
    	Queries queries = new Queries();

    	if(simpleSearchBean.getKeywords().equals("")) {
	    	if(simpleSearchBean.isHotelSearch()) {
	    		queries.searchHotelByPrice(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
	    		ResultSet hotelsResult = queries.getResultsSet();
		    	try {
					while(hotelsResult.next()){
						simpleSearchBean.setNumberOfHotels(simpleSearchBean.getNumberOfHotels()+1);
				    	Hotel hotel = new Hotel();
						hotel.setName(hotelsResult.getString(2));
						hotel.setPrice(hotelsResult.getInt(3));
						hotel.setDescription(hotelsResult.getString(4));
						hotels.add(hotel);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    	if(simpleSearchBean.isSiteSearch()) {
	    		queries.searchSitesByPrice(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
	    		ResultSet sitesResult = queries.getResultsSet();
		    	try {
					while(sitesResult.next()){
						simpleSearchBean.setNumberOfSites(simpleSearchBean.getNumberOfSites()+1);
				    	AbstractSite site;
				    	if(sitesResult.getString(2) == "Historic") {
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
	    	}
    	}
    	
    	
    	
    	/*AbstractSite s1 = new ActivitySite();
    	s1.setName("Volcan");
    	s1.setDescription("Un joli volcan");
    	AbstractSite s2 = new HistoricSite();
    	s2.setName("Ruines");
    	s2.setDescription("Une jolie ruine");
    	sites.add(s1);
    	sites.add(s2);*/
    }

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<AbstractSite> getSites() {
		return sites;
	}

	public void setSites(List<AbstractSite> sites) {
		this.sites = sites;
	}

	public Hotel getSelectedHotel() {
		return selectedHotel;
	}

	public void setSelectedHotel(Hotel selectedHotel) {
		this.selectedHotel = selectedHotel;
	}

	public AbstractSite getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(AbstractSite selectedSite) {
		this.selectedSite = selectedSite;
	}

	public SimpleSearchBean getSimpleSearchBean() {
		return simpleSearchBean;
	}

	public void setSimpleSearchBean(SimpleSearchBean simpleSearchBean) {
		this.simpleSearchBean = simpleSearchBean;
	}
   
	public int getNumberOfHotels() {
		return simpleSearchBean.getNumberOfHotels();
	}

	public void setNumberOfHotels(int numberOfHotels) {
		simpleSearchBean.setNumberOfHotels(numberOfHotels);
	}

	public int getNumberOfSites() {
		return simpleSearchBean.getNumberOfSites();
	}

	public void setNumberOfSites(int numberOfSites) {
		simpleSearchBean.setNumberOfSites(numberOfSites);
	}
    
}