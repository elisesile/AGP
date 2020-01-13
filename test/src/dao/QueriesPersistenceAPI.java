package dao;

import business.ActivitySite;
import business.Coordinates;
import business.HistoricSite;
import business.Hotel;
import business.Ride;
//import business.Site;
import business.Transport;

public interface QueriesPersistenceAPI {
	void dataInit();

	Hotel hotelsInformations();
	
	ActivitySite activitySitesInformations();
	
	HistoricSite historicSitesInformations();
	
	Ride rideInformations();
	
	Transport transportInformations();
	
	Coordinates CoordinatesInformations();
	
}
