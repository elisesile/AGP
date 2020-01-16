package test;

import java.util.ArrayList;

import business.OfferCalculator;
import data.Offer;

public class TestOfferCalculator {

	public static void main(String[] args) {
		OfferCalculator oc = new OfferCalculator();
		ArrayList<Offer> offersList = oc.getOffers(0, 5000, "cascade", "historics", 2);
		
		for(int i=0 ; i < offersList.size() ; i++) {
			System.out.println(offersList.get(i));
			if(i >= 2) {
				break;
			}
		}
	}

}
