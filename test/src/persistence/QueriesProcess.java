package persistence;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import dao.BDAeAPI;
import persistence.jdbc.Queries;
import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

public class QueriesProcess implements BDAeAPI {
	private boolean alreadyIndexed = false;
	private Indexer indexer = new Indexer();
	private static QueriesProcess instance = new QueriesProcess();
	private Searcher searcher;
	private HashMap<BigDecimal,HashMap<String, String>> resultHashMap;
	
	
	private QueriesProcess() {}
	
	public static QueriesProcess getInstance() {
		return instance;
	}
	
	
	private void createIndex() {
		Indexer indexer = this.getIndexer();
		
		try {
			indexer.initIndexer();
			indexer.createIndexFromDirectory();
			this.setIndexer(indexer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void closeIndex() {
		try {
			this.getIndexer().closeIndexer();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public Queries executeSQL(String query) {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		
		Queries queries = new Queries();
		queries.executeQuery(query);
		
		return queries;
	}

	@Override
	public Searcher executeTextual(String query) {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		this.closeIndex();
		
		try {
			this.setSearcher(new Searcher(this.getIndexer().getIndexDirectoryPath(), this.getIndexer().getSecondFieldName()));
			
			try {
				this.getSearcher().search(this.getIndexer().getMaxSearch(), query);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return this.getSearcher();
	}

	@Override
	public HashMap<BigDecimal, HashMap<String, String>> mergeQueries(String query) throws CorruptIndexException, SQLException, IOException {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		//Split query
		String[] arrayQueries = query.toLowerCase().split(" with ");
		ResultSet sqlResult = this.executeSQL(arrayQueries[0]).getResultsSet();
		this.executeTextual(arrayQueries[1]);
		
		
		int foundDocuments = 0;
		ScoreDoc currentScoreDoc;
		this.resultHashMap = new HashMap<BigDecimal,HashMap<String, String>>();
		
		while(sqlResult.next() && foundDocuments < this.getSearcher().getDocsIterator().length) {
			while((currentScoreDoc = this.getSearcher().nextIterator()) != null) {
				int fileName = Integer.valueOf(this.getSearcher().getDocumentName(currentScoreDoc, this.getIndexer().getFirstFieldName()));
				if (sqlResult.getInt(1) == fileName) {
					HashMap<String,String> sitesInformations = new HashMap<String,String>();
					
					int id = sqlResult.getInt(1);
					String name = sqlResult.getString(2);
					String type = sqlResult.getString(3);
					int price = sqlResult.getInt(4);
					int id_coordinates= sqlResult.getInt(5);
					BigDecimal scoreDoc = BigDecimal.valueOf(currentScoreDoc.score);
					
					sitesInformations.put("id_site", String.valueOf(id));
					sitesInformations.put("name", name);
					sitesInformations.put("type", type);
					sitesInformations.put("price", String.valueOf(price));
					sitesInformations.put("id_coordinates", String.valueOf(id_coordinates));

					this.resultHashMap.put(scoreDoc, sitesInformations); 
					
					foundDocuments++;
				}
			}
			
			searcher.initIterator();
		}
		
		return this.getResultHashMap();
	}

	
	public ArrayList<BigDecimal> generateAndSortScoresArrayList() {
		ArrayList<BigDecimal> scoresArrayList = new ArrayList<BigDecimal>();
		Set set = this.resultHashMap.entrySet();
		Iterator resultHashMapIterator = set.iterator();
		
		while(resultHashMapIterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry)resultHashMapIterator.next();
			scoresArrayList.add((BigDecimal) mapEntry.getKey());
		}
		
		Collections.sort(scoresArrayList);
		Collections.reverse(scoresArrayList);
		
		return scoresArrayList;
	}
	
	
	/**
	 * Add a site
	 * 
	 * @param name
	 * @param type
	 * @param price
	 * @param latitude
	 * @param longitude
	 * @param fileContent
	 * 
	 * @return boolean (true if it's a success)
	 */
	public boolean addSite(String name, String type, int price, double latitude, double longitude, String fileContent){
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		
		Queries queries = new Queries();
		int idSite = queries.addSite(name, type, price, latitude, longitude);
		String fileName = "data/"+String.valueOf(idSite)+".txt";
		
		boolean success = FileHandler.createFile(fileName);
		if(success == true) {
			boolean modified = FileHandler.writeInFile(fileName, fileContent);
			
			File file = new File(fileName);
			try {
				this.getIndexer().createIndexFromFile(file);
			} catch (IOException e) {
				e.getMessage();
				return false;
			}
			
			return modified;
		}
		else {
			return false;
		}
	}
	


	/**
	 * @return the alreadyIndexed
	 */
	private boolean isAlreadyIndexed() {
		return alreadyIndexed;
	}

	/**
	 * @param alreadyIndexed the alreadyIndexed to set
	 */
	private void setAlreadyIndexed(boolean alreadyIndexed) {
		this.alreadyIndexed = alreadyIndexed;
	}

	/**
	 * @return the indexer
	 */
	public Indexer getIndexer() {
		return indexer;
	}

	/**
	 * @param indexer the indexer to set
	 */
	private void setIndexer(Indexer indexer) {
		this.indexer = indexer;
	}

	/**
	 * @return the searcher
	 */
	private Searcher getSearcher() {
		return searcher;
	}

	/**
	 * @param searcher the searcher to set
	 */
	private void setSearcher(Searcher searcher) {
		this.searcher = searcher;
	}

	private HashMap<BigDecimal, HashMap<String, String>> getResultHashMap() {
		return resultHashMap;
	}

	private void setResultHashMap(HashMap<BigDecimal, HashMap<String, String>> resultHashMap) {
		this.resultHashMap = resultHashMap;
	}
}