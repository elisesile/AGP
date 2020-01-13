package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface QueriesPersistenceAPI {
	void dataInit();

	ResultSet searchHotelByPrice(PreparedStatement preparedStatement, int startRange, int endRange);
	
	ResultSet searchSiteByType(PreparedStatement preparedStatement, String type);
	
	ResultSet getSites(PreparedStatement preparedStatement);
	
	ResultSet searchSitesByPrice(PreparedStatement preparedStatement, int startRange, int endRange);
	
	ResultSet getRides(PreparedStatement preparedStatement);
}
