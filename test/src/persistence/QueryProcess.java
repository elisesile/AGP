package persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import data.AbstractSite;
import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

public class QueryProcess{
	private ResultSet sqlResult;
	private ScoreDoc textResult;
	private boolean alreadyIndexed = false;
	private Indexer indexer;
	private static QueryProcess instance = new QueryProcess();
	private Searcher searcher;
	private HashMap<Integer,HashMap<String, String>> resultHashMap; 
	
	private QueryProcess() {}
	
	public static QueryProcess getInstance() {
		return instance;
	}
	
	
	public void executeQuery(ResultSet sqlResult, String luceneParams) throws SQLException, CorruptIndexException, IOException {
		this.setSqlResult(sqlResult);
		this.executeText(luceneParams);
		this.joinQuery();
	}
	
	private void executeText(String luceneKeywords) {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		
//		Searcher searcher;
		try {
			searcher = new Searcher(this.getIndexer().getIndexDirectoryPath(), this.getIndexer().getSecondFieldName());
			
			try {
				searcher.search(this.getIndexer().getMaxSearch(), luceneKeywords);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// this.luceneQuerySavedResults = searcher.search(this.getIndexer().getMaxSearch(), luceneKeywords);
		/*
		searcher.initIterator();
		ScoreDoc currentInfo;
		while((currentInfo = searcher.nextIterator()) != null) {
			//searcher.getDocument(currentInfo);
			int currentId = searcher.getDocumentName(currentInfo, indexer.getFirstFieldName());
			float currentScore = searcher.getDocumentScore(currentInfo);
			System.out.println("currentId="+currentId+" currentScore="+currentScore);
		}*/
	}
	
	private HashMap<Integer,HashMap<String, String>> joinQuery() throws SQLException, CorruptIndexException, IOException {
		int foundDocuments = 0;
		ScoreDoc actualScoreDoc;
		resultHashMap = new HashMap<Integer,HashMap<String, String>>();
		
//		ResultSetMetaData rsmd = sqlResult.getMetaData();
		
		while(sqlResult.next() && foundDocuments < searcher.getDocsIterator().length) {
			while((actualScoreDoc = searcher.nextIterator()) != null) {
				int fileName = Integer.valueOf(searcher.getDocumentName(actualScoreDoc, indexer.getFirstFieldName()));
				if (sqlResult.getInt(1) == fileName) {
					HashMap<String,String> sitesInformations = new HashMap<String,String>();
					
					int id = sqlResult.getInt(1);
					String name = sqlResult.getString(2);
					String type = sqlResult.getString(3);
					int price = sqlResult.getInt(4);
					int id_coordinates= sqlResult.getInt(5);
					
					sitesInformations.put("name", name);
					sitesInformations.put("type", type);
					sitesInformations.put("price", String.valueOf(price));
					sitesInformations.put("id_coordinates", String.valueOf(id_coordinates));
					
					resultHashMap.put(id, sitesInformations); 
					
					foundDocuments++;
				}
			}
			
			searcher.initIterator();
		}
		return resultHashMap;
		
	}
	
	public HashMap<Integer, HashMap<String, String>> getResultHashMap() {
		return resultHashMap;
	}

	public void setResultHashMap(HashMap<Integer, HashMap<String, String>> resultHashMap) {
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
}
