package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import business.ActivitySite;
import business.HistoricSite;
import business.Hotel;
import business.AbstractSite;

@ManagedBean
@RequestScoped
public class SimpleResultBean {
    
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private List<AbstractSite> sites = new ArrayList<AbstractSite>();
	private Hotel selectedHotel;
	private AbstractSite selectedSite;
     
    @PostConstruct
    public void init() {
    	Hotel h1 = new Hotel();
    	h1.setName("Tokyo Hotel");
    	h1.setPrice(125);
    	h1.setDescription("Moonson");
    	Hotel h2 = new Hotel();
    	h2.setName("Hotel California");
    	h2.setPrice(400);
    	h2.setDescription("Welcome to the Hotel California");
    	hotels.add(h1);
    	hotels.add(h2);
    	
    	AbstractSite s1 = new ActivitySite();
    	s1.setName("Volcan");
    	s1.setDescription("Un joli volcan");
    	AbstractSite s2 = new HistoricSite();
    	s2.setName("Ruines");
    	s2.setDescription("Une jolie ruine");
    	sites.add(s1);
    	sites.add(s2);
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
    
}