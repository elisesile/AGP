package business;

public class Ride {
	//private int id_ride;
	private AbstractSite departure_site;
	private AbstractSite arrival_site;
	private Transport transport;
	
	public AbstractSite getDeparture_site() {
		return departure_site;
	}
	
	public void setDeparture_site(AbstractSite departure_site) {
		this.departure_site = departure_site;
	}
	
	public AbstractSite getArrival_site() {
		return arrival_site;
	}
	
	public void setArrival_site(AbstractSite arrival_site) {
		this.arrival_site = arrival_site;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}
}
