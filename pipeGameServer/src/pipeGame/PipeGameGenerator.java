package pipeGame;

import java.util.ArrayList;
import java.util.Random;

import logic.Location;
import logic.Matrix;
import logic.State;
import logic.algo.HC_Heap;

public class PipeGameGenerator {
	
	public Matrix generate(int size)
	{
		Matrix game = new Matrix();
		
		game.setRows(size);
		game.setCols(size);
		boolean lvlIsFine = false;
		
		while(!lvlIsFine){
			char[][] mat = generateRandomMatrix(size);
			game.setMatrix(mat);
			Location start = generateStartLocation(size);
			Location end = generateEndLocation(size);
			
			game.setLocation(start);
			game.setLocation(end);
			game.setStart(start);
			game.setEnd(end);
			ArrayList<State> list = new HC_Heap().search(new PipeSearchable(new PipeState(game)));
			if(list != null)
				lvlIsFine = true;
		}
		game.print();
		//System.out.println("game has been generated!!!!!");
		return null;
	}
	
	private Location generateStartLocation(int size)
	{
		int startI = getRandomNumberInRange(0, size-1);
		int startJ = getRandomNumberInRange(0, size-1);
		Location start = new Location(startI,startJ,'s');
		return start;
	}
	private Location generateEndLocation(int size)
	{
		int endI = getRandomNumberInRange(0, size-1);
		int endJ = getRandomNumberInRange(0, size-1);
		Location end = new Location(endI,endJ,'g');
		return end;
	}
	
	private char[][] generateRandomMatrix(int size)
	{
		char[][] mat = new char[size][size];
		for(int i = 0; i < size ; i++)
		{
			for(int j = 0; j < size ; j++)
			{
				mat[i][j] = getRandomPipeChar();
			}	
		}
		printMatrix(mat, size, size);
		return mat;
	}

	private void printMatrix(char[][]mat,int rows,int cols)
	{
		for(int i =0 ; i < rows; i++)
		{
			for(int j =0 ; j < cols; j++)
			{
				System.out.print(mat[i][j]);
			}
			//System.out.println("");
		}
	}
	
	private char getRandomPipeChar()
	{
		char[] options = {'L','-'};
	    char[] result =  new char[1];
	    Random r=new Random();
	    for(int i=0;i<result.length;i++){
	        result[i]=options[r.nextInt(options.length)];
	    }
	    return result[0];
	}
	private int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	public static void main(String[]args)
	{
		PipeGameGenerator pgg = new PipeGameGenerator();
		pgg.generate(3);
	}
}
