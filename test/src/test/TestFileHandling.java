package test;

import persistence.QueryProcess;

public class TestFileHandling {
	public static void main(String[] args) {
		
		boolean result = QueryProcess.getInstance().addSite("Test site", "Historic", 160, -1.65, -1.65, "Ceci est un test de texte pour le fichier texte du nouveau site qui se nomme Test site.");
	    //assertEquals(true,result);
		System.out.println(result);
		QueryProcess.getInstance().closeIndex();
	}
}
