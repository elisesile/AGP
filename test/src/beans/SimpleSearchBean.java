package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import data.SimpleEntry;

@ManagedBean(name = "simpleSearchBean")
@RequestScoped
public class SimpleSearchBean {
    
	private SimpleEntry simpleEntry = new SimpleEntry();
     
    @PostConstruct
    public void init() {
       //TODO
    }
    
    public String searchAction() {
    	//TODO
		System.out.println("A implémenter ");
		return "simple-result";
	}

	public String getKeywords() {
		return simpleEntry.getKeywords();
	}

	public void setKeywords(String keywords) {
		simpleEntry.setKeywords(keywords);
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
	
	public boolean isHotelSearch() {
		return simpleEntry.isHotelSearch();
	}

	public void setHotelSearch(boolean isHotelSearch) {
		simpleEntry.setHotelSearch(isHotelSearch);
	}

	public boolean isSiteSearch() {
		return simpleEntry.isSiteSearch();
	}

	public void setSiteSearch(boolean isActivitySearch) {
		simpleEntry.setSiteSearch(isActivitySearch);
	} 
   
	public int getNumberOfHotels() {
		return simpleEntry.getNumberOfHotels();
	}

	public void setNumberOfHotels(int numberOfHotels) {
		simpleEntry.setNumberOfHotels(numberOfHotels);
	}

	public int getNumberOfSites() {
		return simpleEntry.getNumberOfSites();
	}

	public void setNumberOfSites(int numberOfSites) {
		simpleEntry.setNumberOfSites(numberOfSites);
	}
}