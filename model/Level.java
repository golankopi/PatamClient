package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	
}


