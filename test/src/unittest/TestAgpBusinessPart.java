package unittest;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import persistence.jdbc.JdbcConnection;
import persistence.jdbc.Queries;


public class TestAgpBusinessPart {
	
	private Connection connection;
	private Queries queries;
	
	@Before
	public void preparedConstant() {
		connection = JdbcConnection.getConnection();
		queries = new Queries();
	}
	
/*	@Test
	public void testCalculPrice() {
		ArrayList<Integer> idRides = new ArrayList<Integer>();
		idRides.add(10);
		idRides.add(2);
		
		int hotel = 1;
				
		int totalPrice = queries.getTotalPriceRidesAndHotel(idRides, hotel);
	}	*/
	
	@Test
	public void testCalculDistance() {
		
		//System.out.println(business.ExcursionCalculator.getDistanceKM(-17.5402758,-149.5669825,-17.4942887,-149.4943525));
		
		assertEquals(9,(int)business.ExcursionCalculator.getDistanceKM(-17.5402758,-149.5669825,-17.4942887,-149.4943525));
	}

}
