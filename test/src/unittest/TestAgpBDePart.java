package unittest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.junit.Before;
import org.junit.Test;

import persistence.QueriesProcess;
import persistence.jdbc.Queries;
import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

/**
 * 
 * @author Chri-
 *
 */

public class TestAgpBDePart {
	
	private Queries queries;
	private Indexer indexer;
	private Searcher searcher;
	private int currentId;
	private ScoreDoc currentInfo;
	private HashMap<BigDecimal, HashMap<String, String>> map;
	
	@Before
	public void prepareQueries() {
		queries = new Queries();
		indexer = new Indexer();
	}
	
	@Test
	public void testSimpleQuery() throws SQLException {
		//Connection connection = JdbcConnection.getConnection();
		queries.executeQuery("SELECT name FROM hotel WHERE  price = 135 AND beach_name = 'Plage Papeete' ");
		ResultSet results = queries.getResultsSet();
		while(queries.nextIterator()) {
			String result = results.getString(1);
			assertEquals("Royal Tahitien", result);
		}
	}
	
	
	@Test
	public void testLuceneQuery() throws IOException, ParseException {
		
		indexer.initIndexer();
		indexer.createIndexFromDirectory();
		indexer.closeIndexer();
		
		searcher = new Searcher(indexer.getIndexDirectoryPath(), indexer.getSecondFieldName());
		searcher.search(indexer.getMaxSearch(), "trou souffleur");
		
		searcher.initIterator();
		currentInfo = searcher.nextIterator();
		
		currentId = searcher.getDocumentName(currentInfo, indexer.getFirstFieldName());
		searcher.getDocumentScore(currentInfo);

		searcher.initIterator();
		
		while((currentInfo = searcher.nextIterator()) != null) {
			currentId = searcher.getDocumentName(currentInfo, indexer.getFirstFieldName());
			searcher.getDocumentScore(currentInfo);
			
			assertEquals(10, currentId);
		}
		
		
	}
	
	@Test
	public void testJoinedQuery() throws CorruptIndexException, SQLException, IOException {
		map = QueriesProcess.getInstance().mergeQueries("SELECT * FROM site WITH trou souffleur");
		//System.out.println(map.keySet());
		assertEquals(true, map.containsKey(BigDecimal.valueOf(3.6221795082092285)));
		//System.out.println(map.keySet());
		
	}	
}
