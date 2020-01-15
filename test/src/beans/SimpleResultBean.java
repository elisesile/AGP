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

import business.SimpleResultInitialisator;
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
	private SimpleResultInitialisator init = new SimpleResultInitialisator();
	
	@ManagedProperty(value="#{simpleSearchBean}")
	private SimpleSearchBean simpleSearchBean;
     
    @PostConstruct
    public void init() {

    	if(simpleSearchBean.getKeywords().equals("")) {
	    	if(simpleSearchBean.isHotelSearch()) {
	    		hotels = init.initHotelList(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
	    	}
	    	if(simpleSearchBean.isSiteSearch()) {
	    		sites = init.initSiteListint(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
	    	}
	    	simpleSearchBean.setNumberOfHotels(hotels.size());
	    	simpleSearchBean.setNumberOfSites(sites.size());
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