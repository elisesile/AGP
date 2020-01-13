package dao;

import java.sql.ResultSet;

public interface QueriesPersistenceAPI {
	void dataInit();

	ResultSet searchHotelByPrice(int startRange, int endRange);
	
	ResultSet searchSiteByType(String type);
	
	ResultSet getSites();
	
	ResultSet searchSitesByPrice(int startRange, int endRange);
	
	ResultSet getRides();
}
