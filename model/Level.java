package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Level {
	private int moves;
	private int startTime;
	private int endTime;
	private int previousTime; //when level is saved
	public Matrix matrix;
	
	public Level (File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String level = ""; 
		String line; 
		while ((line = br.readLine()) != null) 
			level += line + System.lineSeparator();
		this.matrix = new Matrix(level);
	}
}


