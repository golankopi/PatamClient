package model;

public class Matrix {
	private int rows;
	private int cols;
	private char[][]matrix = null;
	
	public Matrix() {
		int rows = 0;
		int cols = 0;
		char[][]matrix = null;
	}
	
	public Matrix(String level) throws IllegalArgumentException {
		String[] spllitedLevel = level.split(System.lineSeparator());
		rows = spllitedLevel.length;
		cols = spllitedLevel[0].length();
		if(rows <= 0 || cols <= 0)
			throw new IllegalArgumentException("Problem initiate matrix becuse of file missmatch rows:"+rows + " cols:"+ cols );
		
		this.matrix = new char[rows][cols];
		for(int i = 0; i < rows; i++)
		{
			String line = spllitedLevel[i];
			if(line == null)
				throw new IllegalArgumentException("Problem initiate matrix (line is null Problem) line:" + i); 
			for(int j = 0; j < cols; j++)
			{
				//System.out.println("got a char of "+ line.charAt(j));
				this.matrix[i][j] = line.charAt(j);
			}
		}
	}
	
	public void turn(int i, int j) {
		char current =  matrix[i][j];
		switch (current) {
		case('-'): current = '|'; break;
		case('|'): current = '-'; break;
		case('L'): current = 'F'; break;
		case('F'): current = '7'; break;
		case('7'): current = 'J'; break;
		case('J'): current = 'L'; break;
		}
		matrix[i][j] = current;
	}
	

	public char getValue(int i , int j)
	{
		return matrix[i][j];
	}	
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public void setLocation(Location location) {
		//location.print();
		matrix[location.getI()][location.getJ()] = location.getValue(); 
	}

	public char[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(char[][] matrix) {
		this.matrix = matrix;
	}
	
	public void printMatrix(char[][] matrix,int rows,int cols)
	{
		for(int i =0 ; i < rows; i++)
		{
			for(int j =0 ; j < cols; j++)
			{
				//System.out.print(matrix[i][j]);
			}
			//System.out.println("");
		}
	}

	
	public boolean equals(Matrix mat)
	{
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.cols; j++)
			{
				if(!(this.matrix[i][j] == mat.matrix[i][j]))
					return false;
			}
		}
		return true;
	}

	public Location findStartLocation(char[][] matrix,int rows,int cols)
	{
		for(int i =0 ; i < rows; i++)
		{
			for(int j =0 ; j < cols; j++)
			{
				if(matrix[i][j] == 's')
					return new Location(i,j,'s');
			}
			//System.out.println("");
		}
		return null;
	}
	
	public Location findEndLocation(char[][] matrix,int rows,int cols)
	{
		for(int i =0 ; i < rows; i++)
		{
			for(int j =0 ; j < cols; j++)
			{
				if(matrix[i][j] == 'g')
					return new Location(i,j,'g');
			}
			//System.out.println("");
		}
		return null;
	}
}
