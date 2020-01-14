package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import business.OfferEntry;

@ManagedBean(name = "offerSearchBean")
@SessionScoped
public class OfferSearchBean {
    
	private OfferEntry entries = new OfferEntry();
     
    @PostConstruct
    public void init() {
       //TODO
    	entries.setIntensityMessage("Voyage moyennement intense");
    }
    
    public String searchAction() {
    	//TODO
		System.out.println("A impl�menter");
		return "offer-result";
	}
 
    public void changeMessage() {
    	//D�finir de meilleurs messages
    	switch(entries.getIntensity()) {
	    	case 0: entries.setIntensityMessage("Voyage non intense"); break;
	    	case 1: entries.setIntensityMessage("Voyage peu intense"); break;
	    	case 2: entries.setIntensityMessage("Voyage moyennement intense"); break;
	    	case 3: entries.setIntensityMessage("Voyage intense"); break;
	    	case 4: entries.setIntensityMessage("Voyage tr�s intense"); break;
	    	default: break;
    	}
    }
    
	public int getMinPrice() {
        return entries.getMinPrice();
    }
 
    public void setMinPrice(int minPrice) {
        entries.setMinPrice(minPrice);
    }
 
    public int getMaxPrice() {
        return entries.getMaxPrice();
    }
 
    public void setMaxPrice(int maxPrice) {
    	entries.setMaxPrice(maxPrice);
    }
    
    public String getTypeOfTrip() {
		return entries.getTypeOfTrip();
	}

	public void setTypeOfTrip(String typeOfTrip) {
		entries.setTypeOfTrip(typeOfTrip);
	}

	public int getIntensity() {
		return entries.getIntensity();
	}

	public void setIntensity(int intensity) {
		entries.setIntensity(intensity);
	}

	public String getIntensityMessage() {
		return entries.getIntensityMessage();
	}

	public void setIntensityMessage(String intensityMessage) {
		entries.setIntensityMessage(intensityMessage);
	}
}