package data;

public class Coordinates {
	private double latitude;
	private double longitude;
	
	public Coordinates(double latitude, double longitude) {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Coordinates [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
