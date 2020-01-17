package test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import business.OfferCalculator;
import business.data.Excursion;
import business.data.Offer;
import business.data.Ride;

public class TestOfferCalculator {

	public static void main(String[] args) {
		OfferCalculator oc = new OfferCalculator();
		ArrayList<Offer> offersList = oc.getOffers(0, 1000, "trou souffleur", "activity", 2); //"" = "cascade"
		
		for(int i=0 ; i < offersList.size() ; i++) {
			System.out.println(offersList.get(i));
			if(i >= 2) {
				break;
			}
		}
		
		Iterator<Offer> offersItr = offersList.listIterator();
		
		while(offersItr.hasNext())
		{
			Offer currentOffer = offersItr.next();
			System.out.println("==== " + currentOffer.getName() + " ====");
			System.out.println("\tPrix de l'offre : " + currentOffer.getPrice() + " €");
			System.out.println("\tNom de l'hôtel : " + currentOffer.getHotel().getName());
			ArrayList<Excursion> excursionsList = currentOffer.getExcursions();
			
			Iterator<Excursion> excursionsIterator = excursionsList.listIterator();
			
			while(excursionsIterator.hasNext())
			{
				Excursion currentExcursion = excursionsIterator.next();
				
				System.out.println("\t==== Excursion ====");
//				System.out.println(currentExcursion.getRides());
				ArrayList<Ride> ridesList = currentExcursion.getRides();
				
				if (ridesList.size() > 0) {
					Iterator<Ride> ridesIterator = ridesList.listIterator();
					
					while(ridesIterator.hasNext())
					{
						Ride currentRide= ridesIterator.next();
						
						System.out.println("\t\t==== Trajet ====");
						System.out.println("\t\t\tSite de départ : " + currentRide.getArrival_site().getName());
						System.out.println("\t\t\tSite d'arrivée : " + currentRide.getDeparture_site().getName());
					}
				} else {
					System.out.println("\t\tJournée Libre");
				}
			}
		}
	}

}
