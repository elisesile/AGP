package data;

import java.util.ArrayList;

public class Excursion {
	
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private ArrayList<AbstractSite> visitedSites = new ArrayList<AbstractSite>();
	private Hotel hotel;
	private int num;
	private boolean beach;
	
	public Excursion() {
	}

	public ArrayList<Ride> getRides() {
		return rides;
	}

	public void setRides(ArrayList<Ride> rides) {
		this.rides = rides;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<AbstractSite> getVisitedSites() {
		return visitedSites;
	}

	public void setVisitedSites(ArrayList<AbstractSite> visitedSites) {
		this.visitedSites = visitedSites;
	}

	public boolean isBeach() {
		return beach;
	}

	public void setBeach(boolean beach) {
		this.beach = beach;
	}
	
}
