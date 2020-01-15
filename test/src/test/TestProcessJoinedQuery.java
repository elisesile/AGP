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
		// TODO Auto-generated method stub
//		QueryProcess test = Process.getInstance();
		
		Queries testQueries = new Queries();
		testQueries.searchSitesByPrice(0, 50);
		sqlResult = testQueries.getResultsSet();
		
		try {
			QueryProcess.getInstance().executeQuery(sqlResult, "cascade trou");
			System.out.println(QueryProcess.getInstance().getResultHashMap());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test");
		
	}
}
