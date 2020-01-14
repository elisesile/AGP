package test;

import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.ScoreDoc;

import persistence.lucene.Indexer;
import persistence.lucene.Searcher;

public class TestLucene {
	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		
		try {
			indexer.initIndexer();
			indexer.createIndexFromDirectory();
			
			Searcher searcher = new Searcher(indexer.getIndexDirectoryPath());
			try {
				ScoreDoc[] docs = searcher.search(indexer.getMaxSearch(), "cascade").scoreDocs;
				for(ScoreDoc doc : docs) {
						//V1
					//Document document = searcher.getDocument(doc);
					//System.out.println(document.toString());
					//System.out.println(document.getField("firstFieldName").stringValue()); //File's name:  X.txt
						//V2 (utiliser cette méthode de préférence)
					System.out.println(searcher.getDocumentName(doc, "firstFieldName"));
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			indexer.closeIndexer();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}