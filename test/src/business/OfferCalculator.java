package business;

import java.util.ArrayList;

import data.AbstractSite;
import data.Excursion;
import data.Offre;
import data.Ride;

public class OfferCalculator {

	
	private Offre offre ;
	
	public void initExcursions(int intensity){
		
		ArrayList<Excursion> excursions = new ArrayList<Excursion>();
		Excursion e1 = new Excursion();
		Excursion e2 = new Excursion();
		Excursion e3 = new Excursion();
		Excursion e4 = new Excursion();
		
		switch(intensity) {
		case 1:
			e1.setBeach(true);
			e2.setBeach(true);
			e3.setBeach(true);
			e4.setBeach(true);
			break;
		case 2:
			e1.setBeach(true);
			e2.setBeach(true);
			break;
		case 3:
			e2.setBeach(true);
			break;
		default:break;
		}
		excursions.add(e1);
		excursions.add(e2);
		excursions.add(e3);
		excursions.add(e4);
		offre.setExcursions(excursions);
	}
	
	
}
