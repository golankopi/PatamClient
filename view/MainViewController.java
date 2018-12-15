package view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.Level;
import model.HTTPServerSolver;
import solution.ServerSolver;

public class MainViewController implements Initializable{
	private Level level;
	private ServerSolver serverSolver;

	@FXML
	PipeGameDrawer pipeGameDrawer;

	public void LoadLevel() throws IOException {
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
		this.level = new Level(chosenFile);
		pipeGameDrawer.setMatrix(level.matrix);
		
		System.out.println(level);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (level != null) {
			pipeGameDrawer.setMatrix(level.matrix);
		}
		
		pipeGameDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getX());
				System.out.println(event.getY());
				pipeGameDrawer.setChange(event.getX(), event.getY());
			}
		});
	}
	
	public void solve() {
		try {
			// TODO read from xml config
			serverSolver = new HTTPServerSolver("http", "localhost", 8080, "/pipeGameServer/solve");
		} catch (MalformedURLException | URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<String> solvedLevel;
		try {
			solvedLevel = serverSolver.solve(this.level.matrix);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		AnimationTimer at = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				for (int i=0; i < solvedLevel.size(); i++) {
					String currentLine = solvedLevel.get(i);
					System.out.println(currentLine);
					
					int row = Character.getNumericValue(currentLine.charAt(0));
					int col = Character.getNumericValue(currentLine.charAt(2));
					int currentMoves = Character.getNumericValue(currentLine.charAt(4));

					System.out.println(currentMoves);
					for(int k = 0; k < currentMoves; k++) {
						System.out.printf("%d, %d\n",row, col);

						pipeGameDrawer.drawTurn(row, col);
						//TODO make animation
					}
				}
				stop();
			}
		};
		at.start();

	}
}
