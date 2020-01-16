package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import business.OfferCalculator;
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
@RequestScoped
public class OfferResultBean {
    
	private List<Offer> offres = new ArrayList<Offer>();
	private OfferCalculator offerCalculator = new OfferCalculator();
	
	@ManagedProperty(value="#{offerSearchBean}")
	private OfferSearchBean offerSearchBean;
     
    @PostConstruct
    public void init() {
    	offres = offerCalculator.getOffers(offerSearchBean.getMinPrice(), offerSearchBean.getMaxPrice(), offerSearchBean.getKeywords(), offerSearchBean.getTypeOfTrip(), offerSearchBean.getIntensity());
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