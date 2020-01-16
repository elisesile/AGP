package dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.lucene.index.CorruptIndexException;

import persistence.jdbc.Queries;
import persistence.lucene.Searcher;

public interface BDAeAPI {
	Queries executeSQL(String query);
	
	Searcher executeTextual(String query);
	
	HashMap<BigDecimal,HashMap<String, String>> mergeQueries(String query) throws CorruptIndexException, SQLException, IOException;
	
	boolean addSite(String name, String type, int price, double latitude, double longitude, String fileContent);
	
	void createIndex();
}
