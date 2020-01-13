package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import business.Hotel;
import business.Site;

@ManagedBean
@RequestScoped
public class SimpleResultBean {
    
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private List<Site> sites = new ArrayList<Site>();
	private Hotel selectedHotel;
     
    @PostConstruct
    public void init() {
    	Hotel h1 = new Hotel("Hotel1", 75,"1er hotel");
    	Hotel h2 = new Hotel("Hotel2", 100,"2eme hotel");
    	hotels.add(h1);
    	hotels.add(h2);
    	
    	Site s1 = new Site("Volcan", 250,"Un joli volcan");
    	Site s2 = new Site("Ruines", 0,"De belles ruines");
    	sites.add(s1);
    	sites.add(s2);
    }

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}

	public Hotel getSelectedHotel() {
		return selectedHotel;
	}

	public void setSelectedHotel(Hotel selectedHotel) {
		this.selectedHotel = selectedHotel;
	}
    
}