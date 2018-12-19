package viewModel;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import model.Level;
import model.Matrix;
import view.PipeGameDrawer;

public class LevelViewModel extends Observable implements Observer{
	
	Level level;
	public SimpleObjectProperty<Matrix> charMatrix;

	public LevelViewModel(Level level) {
		this.level = level;
		this.charMatrix = new SimpleObjectProperty<Matrix>();
	}
	
	public void loadLevel(File chosenFile) throws IOException {
		this.level.loadFromFile(chosenFile);
	}
	
	public void turn(int i, int j) {
		charMatrix.get().turn(i, j);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 == level) {
			System.out.println("here setting");
			charMatrix.set(level.getMatrix());
		}
	}
}
