package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import data.AbstractSite;
import data.ActivitySite;
import data.Excursion;
import data.HistoricSite;
import data.Hotel;
import data.Offer;
import data.Ride;
import data.Transport;
import data.TransportEnum;

@ManagedBean
@SessionScoped
public class OfferResultBean {
    
	private List<Offer> offres = new ArrayList<Offer>();
	
	@ManagedProperty(value="#{offerSearchBean}")
	private OfferSearchBean offerSearchBean;
     
    @PostConstruct
    public void init() {
    	
    	Transport transport = new Transport();
    	transport.setType(TransportEnum.BUS);
    	
    	Hotel h1 = new Hotel();
     	h1.setName("Tokyo Hotel");
     	h1.setPrice(125);
     	h1.setDescription("Moonson");
     	
     	AbstractSite s1 = new HistoricSite();
     	s1.setName("Ruine");
     	s1.setPrice(5);
     	s1.setDescription("Une belle ruine");
     	
     	AbstractSite s2 = new ActivitySite();
     	s2.setName("Volcan");
     	s2.setPrice(200);
     	s2.setDescription("Un bon volcan");
     	
     	AbstractSite s3 = new ActivitySite();
     	s3.setName("TestActivity");
     	s3.setPrice(50);
     	s3.setDescription("Un bon test");
     	
     	Ride ride = new Ride();
    	ride.setTransport(transport);	
    	ride.setArrival_site(s1);
    	ride.setDeparture_site(s2);
    	
    	Ride ride2 = new Ride();
    	ride.setTransport(transport);	
    	ride.setArrival_site(s2);
    	ride.setDeparture_site(s1);
    	
    	Ride ride3 = new Ride();
    	ride.setTransport(transport);	
    	ride.setArrival_site(s1);
    	ride.setDeparture_site(s3);
     	
     	ArrayList<Ride> rides = new ArrayList<Ride>();
    	rides.add(ride);
    	rides.add(ride2);
    	rides.add(ride3);
     	
    	Excursion e1 = new Excursion();
    	e1.setName("Excursion 1");
    	e1.setDescription("Volcan -> Ruines -> Surf");
    	e1.setRides(rides);
    	
    	Excursion e2 = new Excursion();
    	e2.setName("Excursion 2");
    	e2.setDescription("Ruines -> Surf -> Volcan");
    	e2.setRides(rides);
     
    	ArrayList<Excursion> excursions = new ArrayList<Excursion>();
    	excursions.add(e1);
    	excursions.add(e2);
    	
    	Offer offre = new Offer();
    	offre.setName("Offre 1");
    	offre.setPrice(1750);
    	offre.setHotel(h1);
    	offre.setExcursions(excursions);
    	
    	Offer offre2 = new Offer();
    	offre2.setName("Offre 2");
    	offre2.setPrice(3584);
    	offre2.setHotel(h1);
    	offre2.setExcursions(excursions);
    	
    	offres.add(offre);
    	offres.add(offre2);
    }

	public List<Offer> getOffres() {
		return offres;
	}

	public void setOffres(List<Offer> offres) {
		this.offres = offres;
	}

	public OfferSearchBean getOfferSearchBean() {
		return offerSearchBean;
	}

	public void setOfferSearchBean(OfferSearchBean offerSearchBean) {
		this.offerSearchBean = offerSearchBean;
	}
 
    
}