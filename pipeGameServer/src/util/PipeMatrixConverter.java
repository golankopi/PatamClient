package util;

import java.io.File;

import exceptions.MatrixException;
import logic.Location;
import logic.Matrix;
import logic.Problem;

public class PipeMatrixConverter implements MatrixConvertor {
	private int rows;
	private int cols;
	private Location start;
	private Location end;
	private char[][] matrix = null;
	

	@Override
	public char[][] convert(File file) throws MatrixException {
		rows = FileHandler.getNumRows(file);
		cols = FileHandler.getFirstRow(file).length();
		if(rows <= 0 || cols <= 0)
			throw new MatrixException("Problem initiate matrix becuse of file missmatch rows:"+rows + " cols:"+ cols );
		
		char[][] matrix = new char[rows][cols];
		for(int i = 0; i < rows; i++)
		{
			String line = FileHandler.getRow(file, i);
			if(line == null)
				throw new MatrixException("Problem initiate matrix (line is null Problem) line:" + i); 
			for(int j = 0; j < cols; j++)
			{
				matrix[i][j] = line.charAt(j);
			}
		}
		this.matrix = matrix;
		start = findStartLocation(this.matrix, rows, cols);
		end = findEndLocation(this.matrix, rows, cols);
		return this.matrix ;
	}

	@Override
	public Problem convert(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String reConvert(Problem p) {
		// TODO Auto-generated method stub
		return null;
	}

	public void printMatrix(char[][] matrix,int rows,int cols)
	{
		for(int i =0 ; i < rows; i++)
		{
			for(int j =0 ; j < cols; j++)
			{
				System.out.print(matrix[i][j]);
			}
			//System.out.println("");
		}
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
			////System.out.println("");
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
			////System.out.println("");
		}
		return null;
	}
	
	
	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}
	

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
	}

	public Location getEnd() {
		return end;
	}

	public void setEnd(Location end) {
		this.end = end;
	}

}
