package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import business.Offre;

@ManagedBean
@RequestScoped
public class OfferDetailBean {
    private Offre offre;
	private List<Offre> offres = new ArrayList<Offre>();
     
    @PostConstruct
    public void init() {
    	 offre = new Offre("test1", 200,"1ere offre");
    	
    }

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}

	public Offre getOffre() {
		return offre;
	}

	public void setOffre(Offre offre) {
		this.offre = offre;
	}
 
    
}