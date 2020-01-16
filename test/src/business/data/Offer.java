package business.data;

import java.util.ArrayList;

public class Offer {

	private String name;
	private int price;
	private Hotel hotel;
	private ArrayList<Excursion> excursions = new ArrayList<Excursion>();
	
	public Offer() {
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

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public ArrayList<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(ArrayList<Excursion> excursions) {
		this.excursions = excursions;
	}

	@Override
	public String toString() {
		return "Offer [name=" + name + ", price=" + price + ", hotel=" + hotel + ", excursions=" + excursions + "]";
	}
	
}
