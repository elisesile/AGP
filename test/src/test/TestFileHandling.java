package test;

import persistence.FileHandler;

public class TestFileHandling {
	public static void main(String[] args) {
		FileHandler testFileHandler = new FileHandler();
		
		testFileHandler.createFile("test0.txt");
		testFileHandler.writeInFile("test0.txt", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc volutpat mauris vel eros dapibus tincidunt. Aliquam et dignissim nisl. Aenean euismod scelerisque nulla ac eleifend. Nunc nulla eros, viverra sagittis arcu id, imperdiet tristique lorem. Nulla rhoncus, risus vel porttitor varius, turpis sem eleifend ipsum, in cursus diam est vitae enim. Integer mollis augue a sapien lobortis ultricies. Donec ornare vehicula ipsum ut sagittis. Proin eu mollis libero. Vivamus pretium bibendum bibendum. Phasellus semper, neque gravida pharetra elementum, justo massa accumsan erat, eget gravida ligula elit ac turpis. Vestibulum ac bibendum dui, in ullamcorper nulla.\r\n" + 
				"\r\n" + 
				"Donec fermentum sed quam vitae aliquet. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean eget semper lorem. Morbi ac imperdiet lectus. Vivamus in ligula laoreet, malesuada dui quis, hendrerit sem. Mauris vel dui eget quam tincidunt volutpat. Phasellus quam arcu, tristique porttitor dignissim quis, euismod vel lorem. Suspendisse dictum pretium tristique. Nulla vel diam odio. Vestibulum fermentum facilisis dui, in imperdiet lectus auctor pharetra. Vestibulum eget ornare eros. Fusce aliquam eros quis magna volutpat interdum. Aliquam aliquet ultrices vulputate. Aliquam dignissim tincidunt neque. Aenean sed leo vel purus dictum sodales ut sit amet tortor.\r\n" + 
				"\r\n" + 
				"Nullam at leo sed urna faucibus interdum. Nunc nec eros tempor est ornare sollicitudin. Donec finibus mollis nisl, sit amet volutpat elit feugiat quis. Integer imperdiet ligula sit amet massa consequat, sed finibus nunc aliquam. Cras placerat pretium nunc sed tristique. Maecenas quis sem vitae libero luctus imperdiet. Mauris turpis ante, interdum quis blandit vel, condimentum id nunc.\r\n" + 
				"\r\n" + 
				"Interdum et malesuada fames ac ante ipsum primis in faucibus. Cras malesuada nec tortor id dapibus. Ut mauris dolor, finibus at mauris id, euismod sagittis dui. Fusce sit amet justo augue. Nam mattis erat a lacus ultrices malesuada. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum nec leo nisl. Etiam dignissim mattis justo sed mattis. Ut vitae elementum ligula, sit amet luctus purus.\r\n" + 
				"\r\n" + 
				"Cras vel diam metus. Fusce eget nulla a turpis maximus varius. Nunc dictum hendrerit commodo. Donec sollicitudin sodales semper. Aliquam ullamcorper pulvinar ex at sodales. Vestibulum non magna ac metus rutrum accumsan. Aliquam et convallis lorem. Nam dignissim tristique elit ut pretium. Duis vehicula placerat ipsum, nec scelerisque urna cursus non. Donec ut imperdiet quam. Donec et finibus nulla. Duis sagittis gravida ipsum, vitae rhoncus dolor lobortis vel."
				);
		testFileHandler.deleteFile("test0.txt");
		
	}
}
