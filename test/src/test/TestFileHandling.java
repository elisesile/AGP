package test;

import persistence.QueriesProcess;

public class TestFileHandling {
	public static void main(String[] args) {
		
		boolean result = QueriesProcess.getInstance().addSite("Test site", "Historic", 160, -1.65, -1.65, "Ceci est un test de texte pour le fichier texte du nouveau site qui se nomme Test site.");
		System.out.println(result);
		QueriesProcess.getInstance().closeIndex();
	}
}
