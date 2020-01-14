package business;

import java.util.ArrayList;

import data.AbstractSite;
import data.Excursion;
import data.Ride;

public class ExcursionCalculator {

	
	
	public void getSiteList(Excursion excursion){
		
		ArrayList<Ride> rides =  excursion.getRides();
		ArrayList<AbstractSite> sites = new ArrayList<AbstractSite>();
		for ( int index = 0; index<rides.size();index++) 
			
		{
			AbstractSite arrival = rides.get(index).getArrival_site();
			AbstractSite departure = rides.get(index).getDeparture_site();
			sites.add(rides.get(index).getArrival_site());
			sites.contains(rides.get(index).getArrival_site());
			
			if( !sites.contains(arrival)) {
				sites.add(arrival);
				
			}
			if( !sites.contains(departure)) {
				sites.add(departure);
				
			}
			
		}
		excursion.setVisitedSites(sites);
		
	}
	
	
	public static double getDistanceKM(double lat1, double long1, double lat2, double long2) {
		double earthRadius = 6378.137;
		double distance;
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		long1 = Math.toRadians(long1);
		long2 = Math.toRadians(long2);
		distance = Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(long1-long2));
		return earthRadius * distance;
	}

	
}
