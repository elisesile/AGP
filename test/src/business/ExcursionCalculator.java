package business;

import java.util.ArrayList;
import java.util.Iterator;

import business.data.*;

public class ExcursionCalculator {
	
	public static void initSiteList(Excursion excursion){
		ArrayList<Ride> rides =  excursion.getRides();
		ArrayList<AbstractSite> sites = new ArrayList<AbstractSite>();
		for(int index = 0; index<rides.size();index++) {
			AbstractSite arrival = rides.get(index).getArrival_site();
			AbstractSite departure = rides.get(index).getDeparture_site();
			
			if( !sites.contains(departure)) {
				sites.add(departure);
			}
			if( !sites.contains(arrival)) {
				sites.add(arrival);
			}
		}
		excursion.setVisitedSites(sites);
	}
	
	public static int getNearestRideSite(ArrayList<Offer> offers, ArrayList<Ride> rides) {
		int nearest = -1;
		double distanceMin = 6378.137;
		
		for(Offer offer : offers) {
			Hotel hotel = offer.getHotel();
			for(int i=0 ; i < rides.size() ; i++) {
				double hotelLatitude = hotel.getCoordinates().getLatitude();
				double hotelLongitude = hotel.getCoordinates().getLongitude();
				double siteLatitude = rides.get(i).getDeparture_site().getCoordinates().getLatitude();
				double siteLongitude = rides.get(i).getDeparture_site().getCoordinates().getLongitude();
				
				double distance = ExcursionCalculator.getDistanceKM(hotelLatitude, hotelLongitude, siteLatitude, siteLongitude);
				if(distance <= distanceMin) {
					distanceMin = distance;
					nearest = i;
				}
			}
		}
		
		return nearest;
	}
	
	public static int getNextRideSite(ArrayList<Ride> rides, AbstractSite arrivalSite) {
		int nextNearest = -1;
		double distanceMin = 6378.137;
		
		for(int i=0 ; i < rides.size() ; i++) {
			double previousSiteLatitude = arrivalSite.getCoordinates().getLatitude();
			double previousSiteLongitude = arrivalSite.getCoordinates().getLongitude();
			double siteLatitude = rides.get(i).getDeparture_site().getCoordinates().getLatitude();
			double siteLongitude = rides.get(i).getDeparture_site().getCoordinates().getLongitude();
			
			double distance = ExcursionCalculator.getDistanceKM(previousSiteLatitude, previousSiteLongitude, siteLatitude, siteLongitude);
			if(distance <= distanceMin) {
				distanceMin = distance;
				nextNearest = i;
			}
		}
		
		return nextNearest;
	}
	
	public static void organizeExcursions(ArrayList<Offer> offers, ArrayList<Ride> rides) {
		for(Offer offer : offers) {
			ArrayList<Excursion> excursions = offer.getExcursions();
			
			ArrayList<Ride> currentRides = new ArrayList<Ride>();
			Iterator<Ride> iterator = rides.iterator();
			while(iterator.hasNext()){
				currentRides.add((Ride) iterator.next());	
			}
			
			for(Excursion excursion : excursions) {
				if(!excursion.isBeach()) {
					int nearestRide = ExcursionCalculator.getNearestRideSite(offers, currentRides);
					if(nearestRide == -1) {
						excursion.setBeach(true);
						continue;
					}
					excursion.getRides().add(currentRides.get(nearestRide));
					System.out.println("organize = hotel: "+ offer.getHotel().getName()+" -- site: "+ excursion.getRides().get(0).getDeparture_site().getName());
					AbstractSite arrivalSite = currentRides.get(nearestRide).getArrival_site();
					AbstractSite departureSite = currentRides.get(nearestRide).getDeparture_site();
					ExcursionCalculator.removeLinkedRides(arrivalSite, departureSite, currentRides);
					
					int nextRide = ExcursionCalculator.getNextRideSite(currentRides, arrivalSite);
					if(nextRide == -1) {
						excursion.setBeach(true);
						continue;
					}
					excursion.getRides().add(currentRides.get(nextRide));
					ExcursionCalculator.removeLinkedRides(arrivalSite, departureSite, currentRides);
				}
			}
		}
	}
	
	public static void removeLinkedRides(AbstractSite arrivalSite, AbstractSite departureSite, ArrayList<Ride> currentRides) {
		for(int i=0 ; i<currentRides.size() ; i++) {
			if(currentRides.get(i).getArrival_site().getName().equals(arrivalSite.getName())
				|| currentRides.get(i).getArrival_site().getName().equals(departureSite.getName())
				|| currentRides.get(i).getDeparture_site().getName().equals(arrivalSite.getName())
				|| currentRides.get(i).getDeparture_site().getName().equals(departureSite.getName())
				) {
				currentRides.remove(i);
			}
		}
	}
	
	public static void setExcursionDescription(Excursion excursion) {
		if(excursion.isBeach()) {
			excursion.setDescription("Journée libre !");
		}
		else {
			initSiteList(excursion);
			for(int index = 0; index<excursion.getVisitedSites().size()-1;index++) {
				AbstractSite site = excursion.getVisitedSites().get(index);
				if(index!=0) {
					excursion.setDescription(excursion.getDescription()+" --> "+site.getName());
				}
				else {
					excursion.setDescription(site.getName());
				}
				
			}
		}
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
