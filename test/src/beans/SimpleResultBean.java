package beans;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import data.AbstractSite;
import data.ActivitySite;
import data.HistoricSite;
import data.Hotel;
import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;

@ManagedBean
@SessionScoped
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
    	PreparedStatement preparedStatement = null;
    	ResultSet hotelsResult = queries.searchHotelByPrice(preparedStatement, simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
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
    
}