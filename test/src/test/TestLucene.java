package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import persistence.lucene.LuceneAPI;

public class TestLucene {
	public static void main(String[] args) {
		LuceneAPI testAPI = new LuceneAPI();
//		testAPI.readLuceneInfo("lucene.conf");
		testAPI.readLuceneInfo();
		System.out.println(testAPI.getDataDirectoryPath());
		System.out.println(testAPI.getIndexDirectoryPath());
		System.out.println(testAPI.getMaxSearch());
		System.out.println(testAPI.getFirstFieldName());
		System.out.println(testAPI.getSecondFieldName());
		
		try {
			try {
				testAPI.initLuceneAP();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			testAPI.indexateFiles();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			testAPI.getIndexWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
