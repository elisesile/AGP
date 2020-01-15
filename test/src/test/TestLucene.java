package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.index.CorruptIndexException;

import persistence.QueryProcess;
import persistence.jdbc.Queries;

public class TestLucene {
	public static void main(String[] args) {
		
		Queries query = new Queries();
		query.getSites();
		ResultSet result = query.getResultsSet();
		try {
			System.out.println(QueryProcess.getInstance().executeQuery(result, "cascade culture"));
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*Indexer indexer = new Indexer();
		
		try {
			indexer.initIndexer();
			indexer.createIndexFromDirectory();
			indexer.closeIndexer();
			
			Searcher searcher = new Searcher(indexer.getIndexDirectoryPath(), indexer.getSecondFieldName());
			
			try {
				searcher.search(indexer.getMaxSearch(), "cascade culture");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			searcher.initIterator();
			ScoreDoc currentInfo;
			while((currentInfo = searcher.nextIterator()) != null) {
				int currentId = searcher.getDocumentName(currentInfo, indexer.getFirstFieldName());
				float currentScore = searcher.getDocumentScore(currentInfo);
				System.out.println("currentId="+currentId+" currentScore="+currentScore);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}
}