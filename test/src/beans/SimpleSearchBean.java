package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import business.SimpleEntry;

@ManagedBean(name = "simpleSearchBean")
@SessionScoped
public class SimpleSearchBean {
    
	private SimpleEntry simpleEntry = new SimpleEntry();
     
    @PostConstruct
    public void init() {
       //TODO
    }
    
    public String searchAction() {
    	//TODO
		System.out.println("A impl�menter ");
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


	public boolean isActivitySearch() {
		return simpleEntry.isActivitySearch();
	}


	public void setActivitySearch(boolean isActivitySearch) {
		simpleEntry.setActivitySearch(isActivitySearch);
	}
    
   
}