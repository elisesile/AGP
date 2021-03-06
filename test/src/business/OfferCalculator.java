package business;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.lucene.index.CorruptIndexException;

import business.data.AbstractSite;
import business.data.ActivitySite;
import business.data.Coordinates;
import business.data.Excursion;
import business.data.HistoricSite;
import business.data.Hotel;
import business.data.Offer;
import business.data.Ride;
import business.data.Transport;
import business.data.TransportEnum;
import business.spring.SpringIoC;
import persistence.QueriesProcess;
import persistence.jdbc.Queries;

public class OfferCalculator {
	
	public void initExcursions(int intensity, Offer offre){
		ArrayList<Excursion> excursions = new ArrayList<Excursion>();
		Excursion e1 = (Excursion)SpringIoC.getBean("excursion");
		Excursion e2 = (Excursion)SpringIoC.getBean("excursion");
		Excursion e3 = (Excursion)SpringIoC.getBean("excursion");
		Excursion e4 = (Excursion)SpringIoC.getBean("excursion");
		
		excursions.add(e1);
		excursions.add(e2);
		excursions.add(e3);
		excursions.add(e4);
		offre.setExcursions(excursions);
		
		int min =0, max = 3;
		int index;
		switch(intensity) {
		case 1:
			for(int i=0;i<3;i++) {
				index = (int)(Math.random()*((max-min)+1))+min;
				while(excursions.get(index).isBeach()) {
					index = (int)(Math.random()*((max-min)+1))+min;
				}
				excursions.get(index).setBeach(true);
			}
			break;
		case 2:
			for(int i=0;i<2;i++) {
				index = (int)(Math.random()*((max-min)+1))+min;
				while(excursions.get(index).isBeach()) {
					index = (int)(Math.random()*((max-min)+1))+min;
				}
				excursions.get(index).setBeach(true);
			}
			break;
		case 3:
				index = (int)(Math.random()*((max-min)+1))+min;
				excursions.get(index).setBeach(true);
			break;
		default:break;
		}
		
	}
	
	private ArrayList<Hotel> getHotels() {
		ArrayList<Hotel> hotelsList = new ArrayList<Hotel>();
		
		Queries queries = QueriesProcess.getInstance().executeSQL("SELECT id_hotel, name, price, beach_name, latitude, longitude FROM hotel INNER JOIN coordinates ON coordinates.id_coordinates = hotel.id_coordinates;");
		ResultSet hotels = queries.getResultsSet();
		try {
			while(queries.nextIterator()) {
				String name = hotels.getString(2);
				int price = hotels.getInt(3);
				String beachName = hotels.getString(4);
				double latitude = hotels.getDouble(5);
				double longitude = hotels.getDouble(6);
				
				Hotel hotel = (Hotel)SpringIoC.getBean("hotel");
				hotel.setName(name);
				hotel.setPrice(price);
				hotel.setBeachName(beachName);
				hotel.setCoordinates(new Coordinates(latitude, longitude));
				hotelsList.add(hotel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotelsList;
	}
	
	private ArrayList<Ride> getRides(String siteType){
		ArrayList<Ride> ridesList = new ArrayList<Ride>();
		ArrayList<Integer> idsSite = new ArrayList<Integer>();
		
		try {
			String query =  "SELECT * FROM site";
			if(!siteType.isEmpty()) {
				query += " WHERE type='"+siteType+"'";
			}
			Queries queries = QueriesProcess.getInstance().executeSQL(query);
			ResultSet sites = queries.getResultsSet();
			while(queries.nextIterator()) {
				int currentSite = sites.getInt(1);
				String currentType = sites.getString(3);
				if(currentType.equals(siteType)|| siteType.equals("")) {
					idsSite.add(currentSite);
				}
			}
			
			for(Integer key : idsSite) {
				queries = QueriesProcess.getInstance().executeSQL("SELECT siteD.name, siteD.type, siteD.price, coordD.latitude, coordD.longitude, siteA.name, siteA.type, siteA.price, coordA.latitude, coordA.longitude, transport.type, transport.price, transport.is_per_km FROM ride INNER JOIN site AS siteD ON siteD.id_site = ride.departure_site INNER JOIN site AS siteA ON siteA.id_site = ride.arrival_site INNER JOIN coordinates AS coordD ON coordD.id_coordinates = siteD.id_coordinates INNER JOIN coordinates AS coordA ON coordA.id_coordinates = siteA.id_coordinates INNER JOIN transport ON transport.id_transport = ride.id_transport WHERE ride.departure_site = "+key+" OR ride.arrival_site = "+key);
				ResultSet rides = queries.getResultsSet();
				try {
					while(queries.nextIterator()) {
						String nameD = rides.getString(1);
						String typeD = rides.getString(2);
						int priceD = rides.getInt(3);
						double latitudeD = rides.getDouble(4);
						double longitudeD = rides.getDouble(5);
						String nameA = rides.getString(6);
						String typeA = rides.getString(7);
						int priceA = rides.getInt(8);
						double latitudeA = rides.getDouble(9);
						double longitudeA = rides.getDouble(10);
						String typeT = rides.getString(11);
						int priceT = rides.getInt(12);
						boolean isPerKmT = rides.getBoolean(13);
						
						AbstractSite siteD, siteA;
						if(typeD.equals("historic")) {
							siteD = (HistoricSite)SpringIoC.getBean("historic");
				    	}
				    	else {
				    		siteD = (ActivitySite)SpringIoC.getBean("activity");
				    	}
						if(typeA.equals("historic")) {
							siteA = (HistoricSite)SpringIoC.getBean("historic");
				    	}
				    	else {
				    		siteA = (ActivitySite)SpringIoC.getBean("activity");
				    	}
						siteD.setName(nameD);
						siteD.setPrice(priceD);
						siteD.setCoordinates(new Coordinates(latitudeD, longitudeD));
						siteA.setName(nameA);
						siteA.setPrice(priceA);
						siteA.setCoordinates(new Coordinates(latitudeA, longitudeA));
						Transport transport = (Transport)SpringIoC.getBean("transport");
						transport.setPerKm(isPerKmT);
						transport.setPrice(priceT);
						if(typeT.equals("bus")) {
							transport.setType(TransportEnum.BUS);
						}
						else {
							transport.setType(TransportEnum.BOAT);
						}

						if(siteType.isEmpty() || (typeA.equals(siteType) && typeD.equals(siteType))) {
							Ride ride = (Ride)SpringIoC.getBean("ride");
							ride.setArrival_site(siteA);
							ride.setDeparture_site(siteD);
							ride.setTransport(transport);
							ridesList.add(ride);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ridesList;
	}
	
	private ArrayList<Ride> getRidesLucene(String enteredKeywords, String siteType){
		ArrayList<Ride> ridesList = new ArrayList<Ride>();
		
		try {
			String query =  "SELECT * FROM site";
			if(!siteType.isEmpty()) {
				query += " WHERE type='"+siteType+"'";
			}
			query += " WITH "+enteredKeywords;
			
			HashMap<BigDecimal, HashMap<String, String>> sites = QueriesProcess.getInstance().mergeQueries(query);
			ArrayList<BigDecimal> keys = QueriesProcess.getInstance().generateAndSortScoresArrayList();
			for(BigDecimal key : keys) {
				if(siteType.equals(sites.get(key).get("type")) || siteType.equals("")) {
					HashMap<String,String> currentSite = sites.get(key);
					
					Queries queries = QueriesProcess.getInstance().executeSQL("SELECT siteD.name, siteD.type, siteD.price, coordD.latitude, coordD.longitude, siteA.name, siteA.type, siteA.price, coordA.latitude, coordA.longitude, transport.type, transport.price, transport.is_per_km FROM ride INNER JOIN site AS siteD ON siteD.id_site = ride.departure_site INNER JOIN site AS siteA ON siteA.id_site = ride.arrival_site INNER JOIN coordinates AS coordD ON coordD.id_coordinates = siteD.id_coordinates INNER JOIN coordinates AS coordA ON coordA.id_coordinates = siteA.id_coordinates INNER JOIN transport ON transport.id_transport = ride.id_transport WHERE ride.departure_site = "+currentSite.get("id_site")+" OR ride.arrival_site = "+currentSite.get("id_site"));
					ResultSet rides = queries.getResultsSet();
					try {
						while(queries.nextIterator()) {
							String nameD = rides.getString(1);
							String typeD = rides.getString(2);
							int priceD = rides.getInt(3);
							double latitudeD = rides.getDouble(4);
							double longitudeD = rides.getDouble(5);
							String nameA = rides.getString(6);
							String typeA = rides.getString(7);
							int priceA = rides.getInt(8);
							double latitudeA = rides.getDouble(9);
							double longitudeA = rides.getDouble(10);
							String typeT = rides.getString(11);
							int priceT = rides.getInt(12);
							boolean isPerKmT = rides.getBoolean(13);
							
							AbstractSite siteD, siteA;
							if(typeD.equals("historic")) {
								siteD = (HistoricSite)SpringIoC.getBean("historic");
					    	}
					    	else {
					    		siteD = (ActivitySite)SpringIoC.getBean("activity");
					    	}
							if(typeA.equals("historic")) {
								siteA = (HistoricSite)SpringIoC.getBean("historic");
					    	}
					    	else {
					    		siteA = (ActivitySite)SpringIoC.getBean("activity");
					    	}
							siteD.setName(nameD);
							siteD.setPrice(priceD);
							siteD.setCoordinates(new Coordinates(latitudeD, longitudeD));
							siteA.setName(nameA);
							siteA.setPrice(priceA);
							siteA.setCoordinates(new Coordinates(latitudeA, longitudeA));
							Transport transport = new Transport();
							transport.setPerKm(isPerKmT);
							transport.setPrice(priceT);
							if(typeT.equals("bus")) {
								transport.setType(TransportEnum.BUS);
							}
							else {
								transport.setType(TransportEnum.BOAT);
							}

							if(siteType.isEmpty() || (siteA.getType().equals(siteType) && siteD.getType().equals(siteType))) {
								Ride ride = (Ride)SpringIoC.getBean("ride");
								ride.setArrival_site(siteA);
								ride.setDeparture_site(siteD);
								ride.setTransport(transport);
								ridesList.add(ride);
							}
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (CorruptIndexException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return ridesList;
	}
	
	public void calculateOfferPrice(ArrayList<Offer> offersList) {
		for(Offer offer : offersList) {
			int hotelPrice = offer.getHotel().getPrice()*4;
			int transportPrice = 0, sitePrice = 0;
			ArrayList<Excursion> excursions = offer.getExcursions();
			
			double hotelLatitude = offer.getHotel().getCoordinates().getLatitude();
			double hotelLongitude = offer.getHotel().getCoordinates().getLongitude();
			
			for(Excursion excursion : excursions) {
				ArrayList<Ride> ridesList = excursion.getRides();
				
				if(!excursion.isBeach()) {
					sitePrice += ridesList.get(0).getDeparture_site().getPrice();
					for(Ride ride : ridesList) {
						sitePrice += ride.getArrival_site().getPrice();
						
						double siteDLatitude = ride.getDeparture_site().getCoordinates().getLatitude();
						double siteDLongitude = ride.getDeparture_site().getCoordinates().getLongitude();
						double siteALatitude = ride.getArrival_site().getCoordinates().getLatitude();
						double siteALongitude = ride.getArrival_site().getCoordinates().getLongitude();
						
						double distance = ExcursionCalculator.getDistanceKM(siteDLatitude, siteDLongitude, siteALatitude, siteALongitude);
						
						if(ride.getTransport().isPerKm()) {
							transportPrice += (int) Math.floor(distance * ride.getTransport().getPrice());
						}
						else {
							transportPrice += ride.getTransport().getPrice();
						}
					}
					
					double firstSiteLatitude = ridesList.get(0).getDeparture_site().getCoordinates().getLatitude();
					double firstSiteLongitude = ridesList.get(0).getDeparture_site().getCoordinates().getLongitude();
					double lastSiteLatitude = ridesList.get(ridesList.size()-1).getDeparture_site().getCoordinates().getLatitude();
					double lastSiteLongitude = ridesList.get(ridesList.size()-1).getDeparture_site().getCoordinates().getLongitude();
					
					double distance = ExcursionCalculator.getDistanceKM(hotelLatitude, hotelLongitude, firstSiteLatitude, firstSiteLongitude);
					if(ridesList.get(0).getTransport().isPerKm()) {
						transportPrice += (int) Math.floor(distance * ridesList.get(0).getTransport().getPrice());
					}
					else {
						transportPrice += ridesList.get(0).getTransport().getPrice();
					}
					
					distance = ExcursionCalculator.getDistanceKM(lastSiteLatitude, lastSiteLongitude, hotelLatitude, hotelLongitude);
					if(ridesList.get(ridesList.size()-1).getTransport().isPerKm()) {
						transportPrice += (int) Math.floor(distance * ridesList.get(ridesList.size()-1).getTransport().getPrice());
					}
					else {
						transportPrice += ridesList.get(ridesList.size()-1).getTransport().getPrice();
					}
				}
			}
			
			int totalPrice = hotelPrice+transportPrice+sitePrice;
			offer.setPrice(totalPrice);
		}
	}
	
	public ArrayList<Offer> getOffers(int minPrice, int maxPrice, String enteredKeywords, String siteType, int intensity) {
		ArrayList<Offer> generatedOffersList = new ArrayList<Offer>();
		ArrayList<Offer> sortedOffersList = new ArrayList<Offer>();
		ArrayList<Hotel> hotelsList = new ArrayList<Hotel>();
		ArrayList<Ride> ridesList = new ArrayList<Ride>();
		
		
		hotelsList = this.getHotels();
		if(enteredKeywords.isEmpty()) {
			ridesList = this.getRides(siteType);
		}
		else {
			ridesList = this.getRidesLucene(enteredKeywords, siteType);
		}
		
		for(Hotel hotel : hotelsList) {
			Offer offer = (Offer)SpringIoC.getBean("offer");
			offer.setHotel(hotel);
			initExcursions(intensity, offer);
			generatedOffersList.add(offer);
		}
		ExcursionCalculator.organizeExcursions(generatedOffersList, ridesList);
		
		this.calculateOfferPrice(generatedOffersList);

		for(Offer currentOffer : generatedOffersList) {
			if (currentOffer.getPrice() >= minPrice && currentOffer.getPrice() <= maxPrice) {
				sortedOffersList.add(0, currentOffer);
			} else {
				sortedOffersList.add(currentOffer);
			}
		}
		initNameAndDescription(sortedOffersList);
		return sortedOffersList;
	}
	
	public void initNameAndDescription(ArrayList<Offer> offers) {
		int offerNum =1;
		for(Offer offer: offers) {
			offer.setName("Offre N??"+offerNum++);
			for(int index=0;index<offer.getExcursions().size();index++) {
				Excursion excursion = offer.getExcursions().get(index);
				ExcursionCalculator.setExcursionDescription(excursion);
				excursion.setName("Jour N??"+index);
			}	
		}
	}
}
