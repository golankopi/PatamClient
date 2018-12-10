package view;

import java.io.File;

import javafx.stage.FileChooser;

public class MainViewController {
	public void Solve() {
		System.out.println("solve!");
	}
	
	public void LoadLevel() {
		System.out.println("load!");
		FileChooser fileChooser = new FileChooser();

		//Extension filter
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extentionFilter);

		File userDirectory = new File("c:/");
		fileChooser.setInitialDirectory(userDirectory);
		
		//Choose the file, make sure a file was selected
		File chosenFile;
		chosenFile = fileChooser.showOpenDialog(null);
		
		System.out.println(chosenFile);

		if (chosenFile == null) return;
		
		String path = chosenFile.getPath();

		System.out.println(path);

	}
}
