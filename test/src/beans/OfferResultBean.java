package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import business.OfferCalculator;
import business.data.Offer;

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