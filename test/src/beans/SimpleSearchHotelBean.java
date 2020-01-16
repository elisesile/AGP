package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.data.SimpleHotelEntry;

@ManagedBean(name = "simpleSearchHotelBean")
@SessionScoped
public class SimpleSearchHotelBean {
    
	private SimpleHotelEntry simpleEntry = new SimpleHotelEntry();
     
    @PostConstruct
    public void init() {
    }
    
    public String searchAction() {
    	//TODO
		return "simple-result-hotel";
	}

	public int getMinPrice() {
		return simpleEntry.getMinPrice();
	}

	public void setMinPrice(int minPrice) {
		simpleEntry.setMinPrice(minPrice);
	}

	public int getMaxPrice() {
		return simpleEntry.getMaxPrice();
	}

	public void setMaxPrice(int maxPrice) {
		simpleEntry.setMaxPrice(maxPrice);
	}
   
	public int getNumberOfHotels() {
		return simpleEntry.getNumberOfHotels();
	}

	public void setNumberOfHotels(int numberOfHotels) {
		simpleEntry.setNumberOfHotels(numberOfHotels);
	}
}