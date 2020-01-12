package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import persistence.Offre;

@ManagedBean
@RequestScoped
public class OfferResultBean {
    
	private List<Offre> offres = new ArrayList<Offre>();
     
    @PostConstruct
    public void init() {
    	Offre o1 = new Offre("test1", 200,"1ere offre");
    	Offre o2 = new Offre("test2", 500,"2eme offre");
    	offres.add(o1);
    	offres.add(o2);
    }

	public List<Offre> getOffres() {
		return offres;
	}

	public void setOffres(List<Offre> offres) {
		this.offres = offres;
	}
 
    
}