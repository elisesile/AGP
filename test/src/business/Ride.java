package business;

public class Ride {
	private int id_ride;
	private Site departure_site;
	private Site arrival_site;
//	private Transport id_transport;
	
//	public int getId_ride() {
//		return id_ride;
//	}
//	
//	public void setId_ride(int id_ride) {
//		this.id_ride = id_ride;
//	}
	
	public Site getDeparture_site() {
		return departure_site;
	}
	
	public void setDeparture_site(Site departure_site) {
		this.departure_site = departure_site;
	}
	
	public Site getArrival_site() {
		return arrival_site;
	}
	
	public void setArrival_site(Site arrival_site) {
		this.arrival_site = arrival_site;
	}
	
//	public Transport getId_transport() {
//		return id_transport;
//	}
//	
//	public void setId_transport(Transport id_transport) {
//		this.id_transport = id_transport;
//	}
}
