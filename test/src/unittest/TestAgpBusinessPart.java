package unittest;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import business.OfferCalculator;
import business.data.Offer;
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

    @Test
    public void testCalculDistance() {
        assertEquals(9,(int)business.ExcursionCalculator.getDistanceKM(-17.5402758,-149.5669825,-17.4942887,-149.4943525));
    }

/*
 * Testable uniquement si les SpringIoc.getBean("nom_de_la_classe") sont remplaces
 * par des new nom_de_la_classe() dans les methodes de la classe OfferCalculator
 *
 *     @Test
    public void testCreateHotel() {
        OfferCalculator oc = new OfferCalculator();
        ArrayList<Offer> offersList = oc.getOffers(0, 300, "", "activity", 2);

        for(int i=0 ; i < 2 ; i++) {
            offersList.get(i);
        }
    }*/

}