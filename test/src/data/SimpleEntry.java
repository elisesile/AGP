package data;

public class SimpleEntry {
	
	private boolean isHotelSearch = true, isSiteSearch = true;
    private String keywords ="";
    private int minPrice = 0;
    private int maxPrice = 500;
    private int numberOfHotels;
    private int numberOfSites;
    
    public SimpleEntry() {
    	
    }

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

	public boolean isHotelSearch() {
		return isHotelSearch;
	}

	public void setHotelSearch(boolean isHotelSearch) {
		this.isHotelSearch = isHotelSearch;
	}

	public boolean isSiteSearch() {
		return isSiteSearch;
	}

	public void setSiteSearch(boolean isSiteSearch) {
		this.isSiteSearch = isSiteSearch;
	}

	public int getNumberOfHotels() {
		return numberOfHotels;
	}

	public void setNumberOfHotels(int numberOfHotels) {
		this.numberOfHotels = numberOfHotels;
	}

	public int getNumberOfSites() {
		return numberOfSites;
	}

	public void setNumberOfSites(int numberOfSites) {
		this.numberOfSites = numberOfSites;
	}	
    
}
