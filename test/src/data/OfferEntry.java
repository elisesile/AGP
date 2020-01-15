package data;

public class OfferEntry {
	 
    private int minPrice = 500;
    private int maxPrice = 5000;
    private String typeOfTrip;
    private int intensity = 2;
    private String intensityMessage;
    private String keywords;
    
    public OfferEntry() {
    	
    }
    
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
    
    

}
