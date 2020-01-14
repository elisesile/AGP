package dao;

import java.util.ArrayList;

public interface QueriesPersistenceAPI {
	void dataInit();
	
	void searchHotelByPrice(int startRange, int endRange);
	
	void searchSiteByType(String type);
	
	void getSites();
	
	void searchSitesByPrice(int startRange, int endRange);
	
	void getRides();
	
	void getRidesCoordAndTransportTypePrice(ArrayList<Integer> idRides);
	
	void getHotel(int idHotel);
}
