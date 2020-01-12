package beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SimpleSearchBean {
    
    private String keywords;
    private int minPrice = 0;
    private int maxPrice = 500;
     
    @PostConstruct
    public void init() {
       //TODO
    }
    
    public String searchAction() {
    	//TODO
		System.out.println("A implémenter ");
		return "simple-result";
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
   
}