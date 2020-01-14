package persistence;

import java.io.IOException;
import java.sql.ResultSet;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

public class Process{
	private ResultSet sqlResult;
	private ScoreDoc textResult;
	private boolean alreadyIndexed = false;
	private Indexer indexer;
	private static Process instance = new Process();
	
	
	private Process() {}
	
	public static Process getInstance() {
		return instance;
	}
	
	
	public void executeQuery(ResultSet sqlResult, String luceneParams) {
		this.setSqlResult(sqlResult);
		this.executeText(luceneParams);
	}
	
	private void executeText(String luceneKeywords) {
		if(this.isAlreadyIndexed() == false) {
			this.createIndex();
			this.setAlreadyIndexed(true);
		}
		
		Searcher searcher;
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
	
	private void joinQuery() {
		
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
