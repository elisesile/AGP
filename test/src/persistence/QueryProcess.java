package persistence;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

public class QueryProcess {
	private ResultSet sqlResult;
	private ScoreDoc textResult;
	private boolean alreadyIndexed = false;
	private Indexer indexer;
	private static QueryProcess instance = new QueryProcess();
	private Searcher searcher;
	private HashMap<BigDecimal,HashMap<String, String>> resultHashMap; 
	
	private QueryProcess() {}
	
	public static QueryProcess getInstance() {
		return instance;
	}
	
	
	public HashMap<BigDecimal, HashMap<String, String>> executeQuery(ResultSet sqlResult, String luceneParams) throws SQLException, CorruptIndexException, IOException {
		this.setSqlResult(sqlResult);
		this.executeText(luceneParams);
		this.joinQuery();
		
		return this.getResultHashMap();
	}
	
	private void executeText(String luceneKeywords) {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		
		try {
			this.setSearcher(new Searcher(this.getIndexer().getIndexDirectoryPath(), this.getIndexer().getSecondFieldName()));
			
			try {
				this.searcher.search(this.getIndexer().getMaxSearch(), luceneKeywords);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	private void joinQuery() throws SQLException, CorruptIndexException, IOException {
		int foundDocuments = 0;
		ScoreDoc actualScoreDoc;
		this.resultHashMap = new HashMap<BigDecimal,HashMap<String, String>>();
		
		while(this.getSqlResult().next() && foundDocuments < this.getSearcher().getDocsIterator().length) {
			while((actualScoreDoc = this.getSearcher().nextIterator()) != null) {
				int fileName = Integer.valueOf(this.getSearcher().getDocumentName(actualScoreDoc, this.getIndexer().getFirstFieldName()));
				if (this.getSqlResult().getInt(1) == fileName) {
					HashMap<String,String> sitesInformations = new HashMap<String,String>();
					
					int id = this.getSqlResult().getInt(1);
					String name = this.getSqlResult().getString(2);
					String type = this.getSqlResult().getString(3);
					int price = this.getSqlResult().getInt(4);
					int id_coordinates= this.getSqlResult().getInt(5);
					BigDecimal scoreDoc = BigDecimal.valueOf(actualScoreDoc.score);
					
					sitesInformations.put("id_site", String.valueOf(id));
					sitesInformations.put("name", name);
					sitesInformations.put("type", type);
					sitesInformations.put("price", String.valueOf(price));
					sitesInformations.put("id_coordinates", String.valueOf(id_coordinates));
//					sitesInformations.put("scoreDoc", String.valueOf(scoreDoc));
//					this.resultHashMap.
					
//					this.resultHashMap.
					this.resultHashMap.put(scoreDoc, sitesInformations); 
					
					
					foundDocuments++;
				}
			}
			
			searcher.initIterator();
		}
	}
	
	
//	public void sortHashMap() {
//		LinkedHashMap<BigDecimal,HashMap<String, String>> sortedHashMap = new LinkedHashMap<BigDecimal,HashMap<String, String>>();
//		
//		
//		
//		Set set = this.resultHashMap.entrySet();
//		Iterator resultHashMapIterator = set.iterator();
//		
//		System.out.println(this.resultHashMap.keySet());
//		while(resultHashMapIterator.hasNext()) {
//			Map.Entry mapEntry = (Map.Entry)resultHashMapIterator.next();
////			this.resultHashMap.
////			System.out.print(mapEntry.getKey() + ": ");
////			System.out.println(mapEntry.getValue());
//		}
//	}
	
	private HashMap<BigDecimal, HashMap<String, String>> getResultHashMap() {
		return resultHashMap;
	}

	private void setResultHashMap(HashMap<BigDecimal, HashMap<String, String>> resultHashMap) {
		this.resultHashMap = resultHashMap;
	}

	private void createIndex() {
		Indexer indexer = new Indexer();
		
		try {
			indexer.initIndexer();
			indexer.createIndexFromDirectory();
			indexer.closeIndexer();
			this.setIndexer(indexer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * @return the sqlResult
	 */
	private ResultSet getSqlResult() {
		return sqlResult;
	}

	/**
	 * @param sqlResult the sqlResult to set
	 */
	private void setSqlResult(ResultSet sqlResult) {
		this.sqlResult = sqlResult;
	}

	/**
	 * @return the textResult
	 */
	private ScoreDoc getTextResult() {
		return textResult;
	}

	/**
	 * @param textResult the textResult to set
	 */
	private void setTextResult(ScoreDoc textResult) {
		this.textResult = textResult;
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
	private Indexer getIndexer() {
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
}
