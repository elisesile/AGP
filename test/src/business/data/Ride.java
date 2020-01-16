package business.data;

public class Ride {
	private AbstractSite departure_site;
	private AbstractSite arrival_site;
	private Transport transport;
	
	public Ride(AbstractSite departureSite, AbstractSite arrivalSite, Transport transport){
		this.setArrival_site(arrivalSite);
		this.setDeparture_site(departureSite);
		this.setTransport(transport);
	}
	
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

	@Override
	public String toString() {
		return "Ride [departure_site=" + departure_site + ", arrival_site=" + arrival_site + ", transport=" + transport
				+ "]";
	}
}
