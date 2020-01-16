package data;

public class Transport {
	private TransportEnum type;
	private int price;
	private boolean isPerKm;
	
	public TransportEnum getType() {
		return type;
	}
	public void setType(TransportEnum type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isPerKm() {
		return isPerKm;
	}
	public void setPerKm(boolean isPerKm) {
		this.isPerKm = isPerKm;
	}

}
