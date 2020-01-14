package moteur;

import java.util.ArrayList;

import business.AbstractSite;
import business.Excursion;
import business.Ride;

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
}
