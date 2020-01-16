package beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


import business.SimpleResultInitialisator;
import business.data.AbstractSite;

@ManagedBean
@RequestScoped
public class SimpleResultSiteBean {

	private List<AbstractSite> sites = new ArrayList<AbstractSite>();
	private AbstractSite selectedSite;
	private SimpleResultInitialisator initialize;

	@ManagedProperty(value = "#{simpleSearchSiteBean}")
	private SimpleSearchSiteBean simpleSearchBean;

	@PostConstruct
	public void init() {
		initialize = new SimpleResultInitialisator();
		if (simpleSearchBean.getKeywords().equals("")) {
			sites = initialize.initSiteList(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice());
		} else {
			sites = initialize.initSiteListLucene(simpleSearchBean.getMinPrice(), simpleSearchBean.getMaxPrice(),simpleSearchBean.getKeywords());
		}
		simpleSearchBean.setNumberOfSites(sites.size());
	}
	
	public List<AbstractSite> getSites() {
		return sites;
	}

	public void setSites(List<AbstractSite> sites) {
		this.sites = sites;
	}

	public AbstractSite getSelectedSite() {
		return selectedSite;
	}

	public void setSelectedSite(AbstractSite selectedSite) {
		this.selectedSite = selectedSite;
	}

	public SimpleSearchSiteBean getSimpleSearchBean() {
		return simpleSearchBean;
	}

	public void setSimpleSearchBean(SimpleSearchSiteBean simpleSearchBean) {
		this.simpleSearchBean = simpleSearchBean;
	}

	public int getNumberOfSites() {
		return simpleSearchBean.getNumberOfSites();
	}

	public void setNumberOfSites(int numberOfSites) {
		simpleSearchBean.setNumberOfSites(numberOfSites);
	}

}