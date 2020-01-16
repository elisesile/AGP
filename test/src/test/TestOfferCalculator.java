package test;

import java.util.ArrayList;

import business.OfferCalculator;
import business.data.Offer;

public class TestOfferCalculator {

	public static void main(String[] args) {
		OfferCalculator oc = new OfferCalculator();
		ArrayList<Offer> offersList = oc.getOffers(0, 300, "", "Activity", 2); //"" = "cascade"
		
		for(int i=0 ; i < offersList.size() ; i++) {
			System.out.println(offersList.get(i));
			if(i >= 2) {
				break;
			}
		}
	}

}
