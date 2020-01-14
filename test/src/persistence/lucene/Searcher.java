package persistence.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Searcher {
	   IndexSearcher indexSearcher;
	   QueryParser queryParser;
	   Query query;
	   
	   
	   /**
	    * Initialize the searcher
	    * 
	    * @param indexDirectoryPath
	    * 
	    * @throws IOException
	    */
	   public Searcher(String indexDirectoryPath, String fileContent) throws IOException {
	      Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
	      IndexReader reader = DirectoryReader.open(indexDirectory);
	      indexSearcher = new IndexSearcher(reader);
	      queryParser = new QueryParser(fileContent, new StandardAnalyzer());
	   }
	   
	   /**
	    * Get documents which corresponds to the given query
	    * 
	    * @param maxSearch
	    * @param searchQuery
	    * 
	    * @return documents
	    * 
	    * @throws IOException(TopDocs)
	    * @throws ParseException
	    */
	   public TopDocs search(int maxSearch, String searchQuery) 
	      throws IOException, ParseException {
	      query = queryParser.parse(searchQuery);
	      return indexSearcher.search(query, maxSearch);
	   }
	   
	   /**
	    * Get a document information
	    * 
	    * @param scoreDoc
	    * 
	    * @return document
	    * 
	    * @throws CorruptIndexException
	    * @throws IOException
	    */
	   public Document getDocument(ScoreDoc scoreDoc) 
	      throws CorruptIndexException, IOException {
	      return indexSearcher.doc(scoreDoc.doc);	
	   }
	   
	   /**
	    * Get the id which correspond to the document name
	    * 
	    * @param scoreDoc
	    * @param fieldName
	    * 
	    * @return id of the document name
	    * 
	    * @throws CorruptIndexException
	    * @throws IOException
	    */
	   public int getDocumentName(ScoreDoc scoreDoc, String fieldName) throws CorruptIndexException, IOException {
		   Document document = this.getDocument(scoreDoc);
		   String fileName = document.getField(fieldName).stringValue();
		   fileName = fileName.split(".txt")[0];
		   
		   return Integer.valueOf(fileName);
	   }
	   
	   /**
	    * Get the score of the given document
	    * 
	    * @param scoreDoc
	    * 
	    * @return document score
	    * 
	    * @throws CorruptIndexException
	    * @throws IOException
	    */
	   public float getDocumentScore(ScoreDoc scoreDoc) throws CorruptIndexException, IOException {
		   return scoreDoc.score;
	   }
}
