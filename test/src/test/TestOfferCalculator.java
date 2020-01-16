package test;

import java.util.ArrayList;
import java.util.Comparator;

import business.OfferCalculator;
import business.data.Offer;

public class TestOfferCalculator {

	public static void main(String[] args) {
		OfferCalculator oc = new OfferCalculator();
		ArrayList<Offer> offersList = oc.getOffers(0, 300, "cascade", "historics", 2);
		
//		offersList.sort(new Comparator() {
//			@Override
//			public int compare(Offer firstOffer, Offer secondOffer) {
//				// TODO Auto-generated method stub
//				return Integer.compare(firstOffer.getPrice(), secondOffer.getPrice());
//			}
//		});
		
		for(int i=0 ; i < offersList.size() ; i++) {
			System.out.println(offersList.get(i));
			if(i >= 10) {
				break;
			}
		}
	}

}
