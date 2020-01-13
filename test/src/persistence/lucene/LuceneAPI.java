package persistence.lucene;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


public class LuceneAPI {
	private String dataDirectoryPath;
	private String indexDirectoryPath;
	private int maxSearch; //nombre max de réponses retournées
	private String firstFieldName;
	private String secondFieldName;
	
	private Analyzer analyser;
//	private Path indexpath;
	private Directory indexDirectory;
	private IndexWriterConfig config;
	private IndexWriter indexWriter;
	private TextFileFilter textFileFilter;
	
//	private IndexSearcher indexSearcher;
//	private QueryParser queryParser;
//	private Query query;
	
	public void initLuceneAP() throws IOException {
//		Boolean isInitialized = false;
		this.analyser = new StandardAnalyzer();
		
		// 2. Creation de l'index
		//Directory index = new RAMDirectory();  //création index en mémoire
		//this.indexpath = FileSystems.getDefault().getPath(this.indexDirectoryPath); //localisation index
		
		this.indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));
		
		//création index sur disque
		this.config = new IndexWriterConfig(analyser);
		this.indexWriter = new IndexWriter(indexDirectory, config);
		this.textFileFilter = new TextFileFilter();
		
		
	}
	
	public int indexateFiles() throws IOException {
		File[] files = new File(this.dataDirectoryPath).listFiles();
		  System.out.println(files);
		  for (File file : files) {
		     if(!file.isDirectory()
		        && !file.isHidden()
		        && file.exists()
		        && file.canRead()
		        && this.textFileFilter.accept(file)
		     ){
		        indexFile(file);
		     }
		  }
		  
		  
		  // A l'origine NumDocs avec l'exemple sur la version 7.3.1 de lucene
		  return indexWriter.numRamDocs();
		
		// 3. Indexation des documents
	    //    Ici on indexe seulement un fichier
//	    File f = new File("tmp/fichier.txt");
//   		Document doc = new Document();
//   		doc.add(new Field("nom", f.getName(), TextField.TYPE_STORED));
//   		doc.add(new Field("contenu", new FileReader(f), TextField.TYPE_NOT_STORED));
//   		w.addDocument(doc);
   		//indexer les autres documents de la même façon
   		
//   		w.close(); //on ferme le index writer après l'indexation de tous les documents
   		
	}
	
	private void indexFile(File file) throws IOException {
		System.out.println("Indexing "+file.getCanonicalPath());
		Document document = getDocument(file);
		this.indexWriter.addDocument(document);
	}
	
	private Document getDocument(File file) throws IOException {
	      Document document = new Document();
	      // Content
	      TextField contentField = new TextField(LuceneConstants.INDEX_SECOND_FIELD_FIELD_NAME, new FileReader(file));
	      TextField fileNameField = new TextField(LuceneConstants.INDEX_FIRST_FIELD_FIELD_NAME,
	         file.getName(),TextField.Store.YES);
	      TextField filePathField = new TextField(LuceneConstants.INDEX_DIRECTORY_FIELD_NAME,
	         file.getCanonicalPath(),TextField.Store.YES);
	      document.add(contentField);
	      document.add(fileNameField);
	      document.add(filePathField);
	      return document;
	   }   
	
	public void readLuceneInfo(){
		File file = new File(LuceneConstants.LUCENE_FILE_CONF);
		Scanner reader = null;

		try {
			reader = new Scanner(file);
			
			try {
				System.out.println(file.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found " + e.getMessage());
		}
		
		while(reader.hasNextLine()){
			String line = reader.nextLine();
			
			if(line.contains(LuceneConstants.DATA_DIRECTORY_FIELD_NAME)){
//				System.out.println(line);
				String dataDirectoryPath = line.split("=")[1];
//				System.out.println(dataDirectory);
				dataDirectoryPath = dataDirectoryPath.split(";")[0];
//				System.out.println(dataDirectory);
//				dataDirectory = dataDirectory.replace("\"", " ");
//				System.out.println(dataDirectory);
				
				this.setDataDirectoryPath(dataDirectoryPath);
			}
			
			if(line.contains(LuceneConstants.INDEX_DIRECTORY_FIELD_NAME)){
//				System.out.println(line);
				String indexDirectoryPath = line.split("=")[1];
//				System.out.println(indexDirectory);
				indexDirectoryPath = indexDirectoryPath.split(";")[0];
//				System.out.println(indexDirectory);
//				indexDirectory = indexDirectory.replace("\"", " ");
//				System.out.println(indexDirectory);
				
				this.setIndexDirectoryPath(indexDirectoryPath);
			}
			
			if(line.contains(LuceneConstants.MAX_SEARCH_NUMBER_FIELD_NAME)){
//				System.out.println(line);
				String maxSearch = line.split("=")[1];
//				System.out.println(maxSearch);
				maxSearch = maxSearch.split(";")[0];
//				System.out.println(maxSearch);
				
				this.setMaxSearch(Integer.valueOf(maxSearch));
			}
			
			if(line.contains(LuceneConstants.INDEX_FIRST_FIELD_FIELD_NAME)){
//				System.out.println(line);
				String firstFieldName = line.split("=")[1];
//				System.out.println(firstFieldName);
				firstFieldName = firstFieldName.split(";")[0];
//				System.out.println(firstFieldName);
//				firstFieldName = firstFieldName.replace("\"", " ");
//				System.out.println(firstFieldName);
				
				this.setFirstFieldName(firstFieldName);
			}
			
			if(line.contains(LuceneConstants.INDEX_SECOND_FIELD_FIELD_NAME)){
//				System.out.println(line);
				String secondFieldName = line.split("=")[1];
//				System.out.println(secondFieldName);
				secondFieldName = secondFieldName.split(";")[0];
//				System.out.println(secondFieldName);
//				secondFieldName = secondFieldName.replace("\"", " ");
//				System.out.println(secondFieldName);
				
				this.setSecondFieldName(secondFieldName);
			}
		}
		reader.close();
	}
	
	public Analyzer getAnalyser() {
		return analyser;
	}

	public void setAnalyseur(Analyzer analyser) {
		this.analyser = analyser;
	}
	
	public String getDataDirectoryPath() {
		return dataDirectoryPath;
	}
	public void setDataDirectoryPath(String dataDirectory) {
		this.dataDirectoryPath = dataDirectory;
	}
	public String getIndexDirectoryPath() {
		return indexDirectoryPath;
	}
	public void setIndexDirectoryPath(String indexDirectoryPath) {
		this.indexDirectoryPath = indexDirectoryPath;
	}
	public int getMaxSearch() {
		return maxSearch;
	}
	public void setMaxSearch(int maxSearch) {
		this.maxSearch = maxSearch;
	}
	public String getFirstFieldName() {
		return firstFieldName;
	}
	public void setFirstFieldName(String firstFieldName) {
		this.firstFieldName = firstFieldName;
	}
	public String getSecondFieldName() {
		return secondFieldName;
	}
	public void setSecondFieldName(String secondFieldName) {
		this.secondFieldName = secondFieldName;
	}

	public Directory getIndexDirectory() {
		return indexDirectory;
	}

	public void setIndexDirectory(Directory indexDirectory) {
		this.indexDirectory = indexDirectory;
	}

	public IndexWriterConfig getConfig() {
		return config;
	}

	public void setConfig(IndexWriterConfig config) {
		this.config = config;
	}

	public IndexWriter getIndexWriter() {
		return indexWriter;
	}

	public void setIndexWriter(IndexWriter indexWriter) {
		this.indexWriter = indexWriter;
	}

	public TextFileFilter getTextFileFilter() {
		return textFileFilter;
	}

	public void setTextFileFilter(TextFileFilter textFileFilter) {
		this.textFileFilter = textFileFilter;
	}

	public void setAnalyser(Analyzer analyser) {
		this.analyser = analyser;
	}
}
