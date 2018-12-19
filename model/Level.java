package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

public class Level extends Observable{
	private int moves;
	private int startTime;
	private int endTime;
	private int previousTime; //when level is saved
	public Matrix matrix;

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	
	public void loadFromFile (File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String level = ""; 
		String line; 
		while ((line = br.readLine()) != null) 
			level += line + System.lineSeparator();
		this.matrix = new Matrix(level);
		setChanged();
		notifyObservers();
	}
	
	public void turn(int i, int j) {
		this.matrix.turn(i, j);
		setChanged();
		notifyObservers();
	}
	
	
}


