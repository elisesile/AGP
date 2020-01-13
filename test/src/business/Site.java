package business;

public abstract class Site {
//	private int id_site;
	private String name;
	private int price;
//	private int id_coord;
	private String description;
	private SiteType type;
	
//	public int getId_site() {
//		return id_site;
//	}
//
//	public void setId_site(int id_site) {
//		this.id_site = id_site;
//	}

//	public int getId_coord() {
//		return id_coord;
//	}
//
//	public void setId_coord(int id_coord) {
//		this.id_coord = id_coord;
//	}

	public SiteType getType() {
		return type;
	}

	public void setType(SiteType type) {
		this.type = type;
	}
	
	public Site(String name, int price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
