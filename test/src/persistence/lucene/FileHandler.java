package persistence.lucene;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	public Boolean createFile(String name) {
		Boolean isCreated = false;
		try {
			File fileToCreate = new File(name);
			
			if (fileToCreate.createNewFile()) {
//				System.out.println("File created: " + fileToCreate.getName());
				isCreated = true;
			} else {
//				System.out.println("File already exists.");
				isCreated = false;
			}
		} catch (IOException e) {
//			System.out.println("An error occurred.");
			e.printStackTrace();
			isCreated = false;
		}
		
		return isCreated;
	}
	
	public Boolean deleteFile(String name) {
		Boolean isDeleted = false;
		File fileToDelete = new File(name);
		if (fileToDelete.delete()) { 
			isDeleted = true;
//			System.out.println("Deleted the file: " + fileToDelete.getName());
		} else {
			isDeleted = false;
//			System.out.println("Failed to delete the file.");
		} 
		
		return isDeleted;
	}
	
	public Boolean writeInFile(String name, String textToWrite) {
		Boolean isModified = false;
		
		if(textToWrite.length() > 0) {
			try {
				FileWriter myWriter = new FileWriter(name);
				myWriter.write(textToWrite);
				myWriter.close();
				
				isModified = true;
//				System.out.println("Successfully wrote to the file.");
			} catch (IOException e) {
				isModified = false;
//				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		
		return isModified;
	}
}
