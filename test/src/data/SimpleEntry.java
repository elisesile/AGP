package data;

public class SimpleEntry {
	
	private boolean isHotelSearch, isActivitySearch;
    private String keywords;
    private int minPrice = 0;
    private int maxPrice = 500;

    
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


	public boolean isActivitySearch() {
		return isActivitySearch;
	}


	public void setActivitySearch(boolean isActivitySearch) {
		this.isActivitySearch = isActivitySearch;
	}
    
	
    
}
