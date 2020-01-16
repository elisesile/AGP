package business.data;

import java.util.ArrayList;

public class Excursion {
	
	private ArrayList<Ride> rides = new ArrayList<Ride>();
	private ArrayList<AbstractSite> visitedSites = new ArrayList<AbstractSite>();
	private String name;
	private boolean beach;
	private String description;
	
	public Excursion() {
	}

	public ArrayList<Ride> getRides() {
		return rides;
	}

	public void setRides(ArrayList<Ride> rides) {
		this.rides = rides;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Excursion [rides=" + rides + ", visitedSites=" + visitedSites + ", name=" + name + ", beach=" + beach
				+ ", description=" + description + "]";
	}
	
}
