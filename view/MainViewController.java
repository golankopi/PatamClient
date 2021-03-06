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

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Level;
import model.Matrix;
import model.HTTPServerSolver;
import solution.ServerSolver;
import viewModel.LevelViewModel;

public class MainViewController implements Initializable, Observer{
	LevelViewModel levelViewModel;
	private ServerSolver serverSolver;
	private static final Integer STARTTIME = 0;
	Integer steps = 0;
	private IntegerProperty playDuration = new SimpleIntegerProperty(STARTTIME);
	public StringProperty PORT = new SimpleStringProperty("8080");
	public StringProperty SERVER = new SimpleStringProperty("localhost");
	
	
	
	private Timeline timer;
	private Timeline durationTimer;
	
	@FXML
	PipeGameDrawer pipeGameDrawer;

	@FXML
	private Text stepsText = new Text();
	@FXML
	private Text durationText = new Text();
	@FXML
	private TextField portTextField;
	@FXML
	private TextField serverTextField;
	
	
	/*
	 * TODO: This is the onAction for binding the timer to the UI. 
	 * 			For some reason it throw a compilation error
	 */
//	startButton.setOnAction(new EventHandler() {
//		
//		public void handle(ActionEvent event) {
//			initTimer();
//			}
//		});
	
	public void start() {
		if (durationTimer != null)
			durationTimer.play();
	}
	
	public void stop() {
		if (durationTimer != null)
			durationTimer.stop();
	}
	
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
		
		System.out.println(path);
		levelViewModel.loadLevel(chosenFile);
		pipeGameDrawer.redraw();
		durationTimer.play();
	}

//	private void loadPlayerProgression(String path) {
//		
//		List<char[]> gameBuilder = new ArrayList<char[]>();
//		BufferedReader br = null;
//		try {
//			br = new BufferedReader(new FileReader(path));
//			String line;
//			while((line = br.readLine()) != null) {
//				if (line.contains("Steps:")) {
//					this.steps = Integer.parseInt(line.split(":")[1]);
//				} 
//				else if(line.contains("Duration:")) {
//					this.playDuration.set( Integer.parseInt(line.split(":")[1]));
//				}
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}

	public void saveLevel() throws IOException {
		durationTimer.pause();
		FileChooser fc = new FileChooser();
		fc.setTitle("Save Level");
		fc.setInitialDirectory(new File("c:/"));
		FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
		fc.getExtensionFilters().add(extentionFilter);
		File chosenFile = fc.showSaveDialog(null);
	
		if (chosenFile == null) return;
		
		levelViewModel.saveLevelProgressionToFile(chosenFile);
		pipeGameDrawer.redraw();
		durationTimer.play();

	}

	public void loadSavedGame() throws IOException {
		
		
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

		
		levelViewModel.loadProgressionFromFile(chosenFile);
		pipeGameDrawer.redraw();
		durationTimer.play();
	}
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		pipeGameDrawer.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(event.getX());
				System.out.println(event.getY());
				int j = pipeGameDrawer.getColFromX(event.getX());
				int i = pipeGameDrawer.getRowFromY(event.getY());
				levelViewModel.turn(i, j);
				pipeGameDrawer.redraw();
				durationTimer.play();
			}
		});
		
		durationTimer = new Timeline(
			    new KeyFrame(
			        Duration.millis( 1000 ),
			        event -> {
			            levelViewModel.incTime();
			        }
			    )
		);
		durationTimer.setCycleCount( Animation.INDEFINITE );
	}
	
	public void setViewModel(LevelViewModel vm) {
		this.levelViewModel=vm;
		vm.charMatrix.bindBidirectional(pipeGameDrawer.charMatrix);
		vm.moves.bindBidirectional(this.stepsText.textProperty());
		vm.time.bindBidirectional(this.durationText.textProperty());
		}
	
	@FXML
	private void pipeTheme() {
		if (pipeGameDrawer.getTheme() == "/resources/dark") 
			return;
		pipeGameDrawer.setTheme("./view/resources/dark");
	//	pipeGameDrawer.stopMusic();
		pipeGameDrawer.loadMusic();
		pipeGameDrawer.redraw();
		//pipeGameDrawer.playMusic();
	}
	@FXML
	private void roadTheme() {
		if (pipeGameDrawer.getTheme() == "/resources/light") 
			return;
		pipeGameDrawer.setTheme("./view/resources/light");
		//pipeGameDrawer.stopMusic();
		pipeGameDrawer.loadMusic();
		pipeGameDrawer.redraw();
		//pipeGameDrawer.playMusic();
	}
	
	public void changeTheme(String theme) {
		this.pipeGameDrawer.setTheme(theme);
	}
	
	public void initTimer() {
		if (timer != null) 
			timer.stop();
		durationText.setText(Integer.toString(playDuration.get()));
		timer = new Timeline(new KeyFrame(Duration.millis(1000), e->  {
			playDuration.set(playDuration.get() + 1);
			durationText.setText(Integer.toString(playDuration.get()));
		}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
	}
	
	public void configureSettings() {		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setController(this);
			BorderPane root = (BorderPane)fxmlLoader.load(getClass().getResource("settings.fxml").openStream());
			Scene scene = new Scene(root, 400, 200);
			Stage stage = new Stage();
			stage.setTitle("Server Settings");
			stage.setScene(scene);
			
			serverTextField.setText(SERVER.get());
			portTextField.setText(PORT.get());

			stage.show();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void solve() {
		try {
			// TODO read from xml config
			serverSolver = new HTTPServerSolver("http", SERVER.get(), Integer.parseInt(PORT.get()), "/pipeGameServer/solve");
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
	
	public void saveSettings() {
		System.out.println(serverTextField.getText());
		SERVER.set(serverTextField.getText());
		PORT.set(portTextField.getText());
	}
}
