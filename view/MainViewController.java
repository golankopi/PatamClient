package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Level;
import model.HTTPServerSolver;
import solution.ServerSolver;
import viewModel.LevelViewModel;

public class MainViewController implements Initializable, Observer{
	LevelViewModel levelViewModel;
	private ServerSolver serverSolver;

	int steps = 0;
	int playDuration = 0;
	Timeline timer;
	
	
	
	@FXML
	PipeGameDrawer pipeGameDrawer;

	@FXML
	private TextField TextTimer = new TextField();
	@FXML
	private TextField TextSteps = new TextField();
	
	public void LoadLevel() throws IOException {
		System.out.println("load!");
		FileChooser fileChooser = new FileChooser();

		//Extension filter
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fileChooser.getExtensionFilters().add(extentionFilter);
		fileChooser.setTitle("Load Level");
		File userDirectory = new File("c:/");
		fileChooser.setInitialDirectory(userDirectory);
		
		//Choose the file, make sure a file was selected
		File chosenFile;
		chosenFile = fileChooser.showOpenDialog(null);
		System.out.println(chosenFile);

		if (chosenFile == null) return;
		
		String path = chosenFile.getPath();
		loadPlayerProgression(path);
		System.out.println(path);
		levelViewModel.loadLevel(chosenFile);
		pipeGameDrawer.redraw();
	}

	private void loadPlayerProgression(String path) {
		
		List<char[]> gameBuilder = new ArrayList<char[]>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null) {
				if (line.contains("Steps:")) {
					this.steps = Integer.parseInt(line.split(":")[1]);
				} 
				else if(line.contains("Duration:")) {
					this.playDuration = Integer.parseInt(line.split(":")[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void saveLevel() {
	
		FileChooser fc = new FileChooser();
		fc.setTitle("Save Level");
		fc.setInitialDirectory(new File("c:/"));
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(extentionFilter);
		File chosenFile = fc.showSaveDialog(null);
	
		if (chosenFile == null) return;
		
//		try {
//			PrintWriter pw = new PrintWriter(chosenFile);
//			pw.println("Steps:" + steps);
//			pw.println("Duration:" + playDuration);
//			for (int i=0; i < pipeGameDrawer.getHeight(); i++) {
//				pw.println(new String(pipeGameDrawer.getMatrix())
//			}
//		}
		savePlayerProgression();
	
	}
	private void savePlayerProgression() {
		// TODO Implement the actual saving of the game board
		
	}
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		if (level != null) {
//			pipeGameDrawer.setMatrix(level.matrix);
//		}
		
		pipeGameDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getX());
				System.out.println(event.getY());
				int j = pipeGameDrawer.getColFromX(event.getX());
				int i = pipeGameDrawer.getRowFromY(event.getY());
				levelViewModel.turn(i, j);
				pipeGameDrawer.redraw();
			}
		});
		
	}
	
	public void setViewModel(LevelViewModel vm) {
		this.levelViewModel=vm;
		vm.charMatrix.bindBidirectional(pipeGameDrawer.charMatrix);
		}
	
	@FXML
	private void pipeTheme() {
		if (pipeGameDrawer.getTheme() == "/resources/") 
			return;
		pipeGameDrawer.setTheme("/resources/");
		//pipeGameDrawer.stopMusic();
		//pipeGameDrawer.loadMusic();
		pipeGameDrawer.redraw();
		//pipeGameDrawer.playMusic();
	}
	@FXML
	private void roadTheme() {
		if (pipeGameDrawer.getTheme() == "/resources/") 
			return;
		pipeGameDrawer.setTheme("/resources/");
		//pipeGameDrawer.stopMusic();
		//pipeGameDrawer.loadMusic();
		pipeGameDrawer.redraw();
		//pipeGameDrawer.playMusic();
	}
	
	public void changeTheme(String theme) {
		this.pipeGameDrawer.setTheme(theme);
	}
	
	public void initTimer() {
		if (timer != null) 
			timer.stop();
		
	}
	
	public void configureSettings() {		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("./settings.fxml"));
			Scene scene = new Scene(fxmlLoader.load(), 400, 200);
			Stage stage = new Stage();
			stage.setTitle("Server Settings");
			stage.setScene(scene);
			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
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
			solvedLevel = serverSolver.solve(this.levelViewModel.charMatrix.get());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		Timeline sortLoop = new Timeline();
		
		double curDelay = 0.01;
		
		for (int i=0; i < solvedLevel.size(); i++) {
			String currentLine = solvedLevel.get(i);
			System.out.println(currentLine);
			
			int row = Character.getNumericValue(currentLine.charAt(0));
			int col = Character.getNumericValue(currentLine.charAt(2));
			int currentMoves = Character.getNumericValue(currentLine.charAt(4));

			System.out.println(currentMoves);
			for(int k = 0; k < currentMoves; k++) {
				System.out.printf("%d, %d - du: %.2f \n",row, col, curDelay);
				KeyFrame kf = new KeyFrame(Duration.seconds(curDelay), actionEvent -> {
					levelViewModel.turn(row, col);
					pipeGameDrawer.redraw();
				});
				curDelay += 0.1;
	            sortLoop.getKeyFrames().add(kf);
			}
		}
        sortLoop.play();

	}
		
	@Override
	public void update(Observable o, Object arg) {
	}
}
