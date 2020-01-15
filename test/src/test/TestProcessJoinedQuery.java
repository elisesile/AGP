package test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.lucene.index.CorruptIndexException;

import persistence.QueryProcess;
import persistence.jdbc.Queries;

public class TestProcessJoinedQuery {
	public static void main(String[] args) {
		ResultSet sqlResult = null;
		
		Queries testQueries = new Queries();
		testQueries.searchSitesByPrice(0, 50);
		sqlResult = testQueries.getResultsSet();
		
		try {
			System.out.println(QueryProcess.getInstance().executeQuery(sqlResult, "cascade les"));
			
//			QueryProcess.getInstance().sortHashMap();
			System.out.println(QueryProcess.getInstance().getScoresArrayList());
//			System.out.println(QueryProcess.getInstance().generateAndSortScoresArrayList());
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
