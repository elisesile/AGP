package persistence.lucene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Indexer {
	private String dataDirectoryPath;
	private String indexDirectoryPath;
	private int maxSearch;
	private String firstFieldName;
	private String secondFieldName;
	private String thirdFieldName;
	private IndexWriter writer;
	private TextFileFilter textFileFilter;
	
	
	/**
	 * Initialize indexer
	 * 
	 * @throws IOException
	 */
	public void initIndexer() throws IOException {
		this.readLuceneConfig();
		
		Directory indexDirectory = FSDirectory.open(Paths.get(this.getIndexDirectoryPath()));
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		config.setOpenMode(OpenMode.CREATE);
		writer = new IndexWriter(indexDirectory, config);
		
		textFileFilter = new TextFileFilter();
	}
	
	/**
	 * Close the indexWriter
	 * 
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public void closeIndexer() throws CorruptIndexException, IOException {
		writer.close();
	}
	
	/**
	 * Get the configuration from config file
	 */
	private void readLuceneConfig(){
		File file = new File(LuceneConstants.LUCENE_FILE_CONF);
		Scanner reader = null;

		try {
			reader = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: File not found " + e.getMessage());
		}
		
		while(reader.hasNextLine()){
			String line = reader.nextLine();
			
			if(line.contains(LuceneConstants.DATA_DIRECTORY_FIELD_NAME)){
				String dataDirectoryPath = line.split("=")[1];
				dataDirectoryPath = dataDirectoryPath.split(";")[0];
				this.setDataDirectoryPath(dataDirectoryPath);
			}
			if(line.contains(LuceneConstants.INDEX_DIRECTORY_FIELD_NAME)){
				String indexDirectoryPath = line.split("=")[1];
				indexDirectoryPath = indexDirectoryPath.split(";")[0];
				this.setIndexDirectoryPath(indexDirectoryPath);
			}
			if(line.contains(LuceneConstants.MAX_SEARCH_NUMBER_FIELD_NAME)){
				String maxSearch = line.split("=")[1];
				maxSearch = maxSearch.split(";")[0];
				this.setMaxSearch(Integer.valueOf(maxSearch));
			}
			if(line.contains(LuceneConstants.INDEX_FIRST_FIELD_FIELD_NAME)){
				String firstFieldName = line.split("=")[1];
				firstFieldName = firstFieldName.split(";")[0];
				this.setFirstFieldName(firstFieldName);
			}
			if(line.contains(LuceneConstants.INDEX_SECOND_FIELD_FIELD_NAME)){
				String secondFieldName = line.split("=")[1];
				secondFieldName = secondFieldName.split(";")[0];
				this.setSecondFieldName(secondFieldName);
			}
			if(line.contains(LuceneConstants.INDEX_THIRD_FIELD_FIELD_NAME)){
				String thirdFieldName = line.split("=")[1];
				thirdFieldName = thirdFieldName.split(";")[0];
				this.setThirdFieldName(thirdFieldName);
			}
		}
		reader.close();
	}
	
	/**
	 * Get the current number of documents buffered in RAM
	 * 
	 * @return numRamDocs
	 * 
	 * @throws IOException
	 */
	public int createIndexFromDirectory() throws IOException {
		File[] files = new File(this.getDataDirectoryPath()).listFiles();
		  
		for(File file : files) {
		     if(!file.isDirectory() && !file.isHidden()  && file.exists() && file.canRead() && this.textFileFilter.accept(file)){
		    	 createIndexFromFile(file);
		     }
		}
		
		return this.getWriter().numRamDocs();
	}
	
	/**
	 * Create the index from the file
	 * 
	 * @param file
	 * 
	 * @throws IOException
	 */
	public void createIndexFromFile(File file) throws IOException {
		Document document = getDocument(file);
		this.getWriter().addDocument(document);
	}
	
	/**
	 * Get document information/content
	 * 
	 * @param file
	 * 
	 * @return document
	 * 
	 * @throws IOException
	 */
	private Document getDocument(File file) throws IOException {
		Document document = new Document();
//		TextField contentField = new TextField(LuceneConstants.INDEX_SECOND_FIELD_FIELD_NAME, new FileReader(file));
//		TextField fileNameField = new TextField(LuceneConstants.INDEX_FIRST_FIELD_FIELD_NAME, file.getName(), TextField.Store.YES);
//		TextField filePathField = new TextField(LuceneConstants.INDEX_DIRECTORY_FIELD_NAME, file.getCanonicalPath(), TextField.Store.YES);
		TextField contentField = new TextField(this.getSecondFieldName(), new FileReader(file));
		TextField fileNameField = new TextField(this.getFirstFieldName(), file.getName(), TextField.Store.YES);
		TextField filePathField = new TextField(this.getThirdFieldName(), file.getCanonicalPath(), TextField.Store.YES);
		document.add(contentField);
		document.add(fileNameField);
		document.add(filePathField);
		
		return document;
	}   

	
	/**
	 * @return the dataDirectoryPath
	 */
	public String getDataDirectoryPath() {
		return dataDirectoryPath;
	}

	/**
	 * @param dataDirectoryPath the dataDirectoryPath to set
	 */
	private void setDataDirectoryPath(String dataDirectoryPath) {
		this.dataDirectoryPath = dataDirectoryPath;
	}

	/**
	 * @return the indexDirectoryPath
	 */
	public String getIndexDirectoryPath() {
		return indexDirectoryPath;
	}

	/**
	 * @param indexDirectoryPath the indexDirectoryPath to set
	 */
	private void setIndexDirectoryPath(String indexDirectoryPath) {
		this.indexDirectoryPath = indexDirectoryPath;
	}

	/**
	 * @return the maxSearch
	 */
	public int getMaxSearch() {
		return maxSearch;
	}

	/**
	 * @param maxSearch the maxSearch to set
	 */
	private void setMaxSearch(int maxSearch) {
		this.maxSearch = maxSearch;
	}

	/**
	 * @return the firstFieldName
	 */
	private String getFirstFieldName() {
		return firstFieldName;
	}

	/**
	 * @param firstFieldName the firstFieldName to set
	 */
	private void setFirstFieldName(String firstFieldName) {
		this.firstFieldName = firstFieldName;
	}

	/**
	 * @return the secondFieldName
	 */
	public String getSecondFieldName() {
		return secondFieldName;
	}

	/**
	 * @param secondFieldName the secondFieldName to set
	 */
	private void setSecondFieldName(String secondFieldName) {
		this.secondFieldName = secondFieldName;
	}
	
	private String getThirdFieldName() {
		return thirdFieldName;
	}

	private void setThirdFieldName(String thirdFieldName) {
		this.thirdFieldName = thirdFieldName;
	}

	/**
	 * @return the writer
	 */
	private IndexWriter getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	private void setWriter(IndexWriter writer) {
		this.writer = writer;
	}

	/**
	 * @return the textFileFilter
	 */
	private TextFileFilter getTextFileFilter() {
		return textFileFilter;
	}

	/**
	 * @param textFileFilter the textFileFilter to set
	 */
	private void setTextFileFilter(TextFileFilter textFileFilter) {
		this.textFileFilter = textFileFilter;
	}
}
