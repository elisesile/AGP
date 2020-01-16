package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import business.data.AbstractSite;
import business.data.ActivitySite;
import business.data.Excursion;
import business.data.HistoricSite;
import business.data.Hotel;
import business.data.Offer;
import business.data.Ride;
import business.data.Transport;
import business.data.TransportEnum;
import business.spring.SpringIoC;

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
    	
    	Hotel h1 = (Hotel)SpringIoC.getBean("hotel");//new Hotel();
     	h1.setName("Tokyo Hotel");
     	h1.setPrice(125);
     	h1.setDescription("Moonson");
     	
     	AbstractSite s1 = (HistoricSite)SpringIoC.getBean("historic");//new HistoricSite();
     	s1.setName("Ruine");
     	s1.setPrice(5);
     	s1.setDescription("Une belle ruine");
     	
     	AbstractSite s2 = (ActivitySite)SpringIoC.getBean("activity");//new ActivitySite();
     	s2.setName("Volcan");
     	s2.setPrice(200);
     	s2.setDescription("Un bon volcan");
     	
     	AbstractSite s3 = (ActivitySite)SpringIoC.getBean("activity");//new ActivitySite();
     	s3.setName("TestActivity");
     	s3.setPrice(50);
     	s3.setDescription("Un bon test");
     	
     	Ride ride = (Ride)SpringIoC.getBean("ride");//new Ride(s1,s2,transport);
    	ride.setDeparture_site(s1);
    	ride.setArrival_site(s2);
    	ride.setTransport(transport);
    	
    	Ride ride2 = (Ride)SpringIoC.getBean("ride");//new Ride(s2,s3,transport);
    	ride2.setDeparture_site(s2);
    	ride2.setArrival_site(s3);
    	ride2.setTransport(transport);
    	
    	Ride ride3 = (Ride)SpringIoC.getBean("ride");//new Ride(s3,s2,transport);
    	ride3.setDeparture_site(s3);
    	ride3.setArrival_site(s1);
    	ride3.setTransport(transport);
     	
     	ArrayList<Ride> rides = new ArrayList<Ride>();
    	rides.add(ride);
    	rides.add(ride2);
    	rides.add(ride3);
     	
    	Excursion e1 = (Excursion)SpringIoC.getBean("excursion");//new Excursion();
    	e1.setName("Excursion 1");
    	e1.setDescription("Volcan -> Ruines -> Surf");
    	e1.setRides(rides);
    	
    	Excursion e2 = (Excursion)SpringIoC.getBean("excursion");//new Excursion();
    	e2.setName("Excursion 2");
    	e2.setDescription("Ruines -> Surf -> Volcan");
    	e2.setRides(rides);
     
    	ArrayList<Excursion> excursions = new ArrayList<Excursion>();
    	excursions.add(e1);
    	excursions.add(e2);
    	
    	Offer offre = (Offer)SpringIoC.getBean("offer");//new Offer();
    	offre.setName("Offre 1");
    	offre.setPrice(1750);
    	offre.setHotel(h1);
    	offre.setExcursions(excursions);
    	
    	Offer offre2 = (Offer)SpringIoC.getBean("offer");//new Offer();
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