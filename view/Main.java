package view;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Level;
import viewModel.LevelViewModel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxl = new FXMLLoader();
			BorderPane root = (BorderPane)fxl.load(getClass().getResource("MainView.fxml").openStream());
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Level l = new Level();
			LevelViewModel vm=new LevelViewModel(l); // View-Model
			l.addObserver(vm);
			
			MainViewController mwc = (MainViewController) fxl.getController(); // View
			mwc.setViewModel(vm);
			vm.addObserver(mwc);
			

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
