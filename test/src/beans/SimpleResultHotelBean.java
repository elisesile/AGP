package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import business.SimpleResultInitialisator;
import business.data.AbstractSite;
import business.data.Hotel;

@ManagedBean
@RequestScoped
public class SimpleResultHotelBean {

	private List<Hotel> hotels = new ArrayList<Hotel>();
	private Hotel selectedHotel;
	private SimpleResultInitialisator initialize;

	@ManagedProperty(value = "#{simpleSearchHotelBean}")
	private SimpleSearchHotelBean simpleSearchBean;

	@PostConstruct
	public void init() {
		initialize = new SimpleResultInitialisator();
		hotels = initialize.initHotelList(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
		simpleSearchBean.setNumberOfHotels(hotels.size());
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public Hotel getSelectedHotel() {
		return selectedHotel;
	}

	public void setSelectedHotel(Hotel selectedHotel) {
		this.selectedHotel = selectedHotel;
	}

	public SimpleSearchHotelBean getSimpleSearchBean() {
		return simpleSearchBean;
	}

	public void setSimpleSearchBean(SimpleSearchHotelBean simpleSearchBean) {
		this.simpleSearchBean = simpleSearchBean;
	}

	public int getNumberOfHotels() {
		return simpleSearchBean.getNumberOfHotels();
	}

	public void setNumberOfHotels(int numberOfHotels) {
		simpleSearchBean.setNumberOfHotels(numberOfHotels);
	}

}