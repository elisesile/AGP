package test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.ScoreDoc;

import persistence.QueriesProcess;
import persistence.jdbc.Queries;
import persistence.lucene.Searcher;

public class TestProcessJoinedQuery {
	public static void main(String[] args) {
		try {
			//SQL + Lucene
			HashMap<BigDecimal, HashMap<String, String>> map = QueriesProcess.getInstance().mergeQueries("SELECT * FROM site WITH cascade");
			System.out.println(map);
			
			System.out.println(QueriesProcess.getInstance().generateAndSortScoresArrayList());
			
			//SQL
			Queries queries = QueriesProcess.getInstance().executeSQL("SELECT * FROM hotel");
			ResultSet results = queries.getResultsSet();
			try {
				while(queries.nextIterator()) {
					int id = results.getInt(1);
					String name = results.getString(2);
					int price = results.getInt(3);
					String beachName = results.getString(4);
					
					System.out.println(id+" || "+name+ " || "+price+" || "+beachName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			//Lucene
			Searcher searcher = QueriesProcess.getInstance().executeTextual("cascade culture");
			ScoreDoc currentScoreDoc;
			
			while((currentScoreDoc = searcher.nextIterator()) != null) {
				int fileName = Integer.valueOf(searcher.getDocumentName(currentScoreDoc, QueriesProcess.getInstance().getIndexer().getFirstFieldName()));
				System.out.println(fileName);
			}
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
