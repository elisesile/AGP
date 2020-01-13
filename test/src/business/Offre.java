package business;

import java.util.ArrayList;

public class Offre {

	private String name;
	private int price;
	private String description;
	private String hotelName;
	private ArrayList<Excursion> excursions = new ArrayList<Excursion>();
	
	public Offre(String name, int price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.hotelName = "hotel Name";
		Excursion e = new Excursion("excursion test");
		this.excursions.add(e);
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

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public ArrayList<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(ArrayList<Excursion> excursions) {
		this.excursions = excursions;
	}
	
}
