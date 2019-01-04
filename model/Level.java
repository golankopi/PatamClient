package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;

public class Level extends Observable{
	private int moves;
	private int time;
	public Matrix matrix;
	

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
	
	public void incTime() {
		if (matrix != null) {
			this.time++;
			setChanged();
			notifyObservers();
		}
	}
	
	public int getTime() {
		return time;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}
	
	public void loadFromFile (File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String level = ""; 
		String line; 
		while ((line = br.readLine()) != null) 
			level += line + System.lineSeparator();
		this.matrix = new Matrix(level);
		this.moves=0;
		this.time=0;
		setChanged();
		notifyObservers();
	}
	
	public void turn(int i, int j) {
		this.matrix.turn(i, j);
		this.moves++;
		System.out.println("inner moves?");
		System.out.println(moves);
		setChanged();
		notifyObservers();
	}

	public void saveLevelProgressionToFile(File chosenFile) {
		/**
		 * Serialize DataWrapper
		 */
		DataWrapper dw = new DataWrapper();
		
		dw.setSteps(moves);
		dw.setPlayDuration(time);
		dw.setMatrix(matrix);
		
		try {
			FileOutputStream fos = new FileOutputStream(chosenFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(dw);
			
			oos.close();
			fos.close();			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

	public void loadProgressionFromFile(File chosenFile) {
		/**
		 * Deserialize DataWrapper
		 */
		
		DataWrapper dw = null;
		
		try {
			FileInputStream fis = new FileInputStream(chosenFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			dw = (DataWrapper) ois.readObject();
			
			ois.close();
			fis.close();
			
			this.matrix = dw.getMatrix();
			this.moves = dw.getSteps();
			this.time = dw.getPlayDuration();
			

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}


