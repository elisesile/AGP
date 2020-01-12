package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class OfferSearchBean {
    
    private int minPrice = 500;
    private int maxPrice = 5000;
    private String typeOfTrip;
    private int intensity = 2;
    private String intensityMessage;
     
    @PostConstruct
    public void init() {
       //TODO
    	intensityMessage = "Voyage moyennement intense";
    }
    
    public String searchAction() {
    	//TODO
		System.out.println("A implémenter");
		return "offer-result";
	}
 
    public void changeMessage() {
    	//Définir de meilleurs messages
    	switch(intensity) {
	    	case 0: intensityMessage ="Voyage non intense"; break;
	    	case 1: intensityMessage ="Voyage peu intense"; break;
	    	case 2: intensityMessage ="Voyage moyennement intense"; break;
	    	case 3: intensityMessage ="Voyage intense"; break;
	    	case 4: intensityMessage ="Voyage très intense"; break;
	    	default: break;
    	}
    }
    
	public int getMinPrice() {
        return minPrice;
    }
 
    public void setMinPrice(int number8) {
        this.minPrice = number8;
    }
 
    public int getMaxPrice() {
        return maxPrice;
    }
 
    public void setMaxPrice(int number9) {
        this.maxPrice = number9;
    }
    
    public String getTypeOfTrip() {
		return typeOfTrip;
	}

	public void setTypeOfTrip(String typeOfTrip) {
		this.typeOfTrip = typeOfTrip;
	}

	public int getIntensity() {
		return intensity;
	}

	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}

	public String getIntensityMessage() {
		return intensityMessage;
	}

	public void setIntensityMessage(String intensityMessage) {
		this.intensityMessage = intensityMessage;
	}
}