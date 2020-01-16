package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import business.data.SimpleSiteEntry;

@ManagedBean(name = "simpleSearchSiteBean")
@SessionScoped
public class SimpleSearchSiteBean {
    
	private SimpleSiteEntry simpleEntry = new SimpleSiteEntry();
     
    @PostConstruct
    public void init() {

    }
    
    public String searchAction() {
    	//TODO
		return "simple-result-site";
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
   
	public int getNumberOfSites() {
		return simpleEntry.getNumberOfSites();
	}

	public void setNumberOfSites(int numberOfSites) {
		simpleEntry.setNumberOfSites(numberOfSites);
	}
	
	public String getKeywords() {
		return simpleEntry.getKeywords();
	}
	
	public void setKeywords(String keywords) {
		simpleEntry.setKeywords(keywords);
	}

}