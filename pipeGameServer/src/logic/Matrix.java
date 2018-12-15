package logic;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import exceptions.MatrixException;
import util.PipeMatrixConverter;

public class Matrix implements Problem {
	private int rows;
	private int cols;
	private Location start;
	private Location end;
	private char[][]matrix = null;
	
	public Matrix() {
		int rows = 0;
		int cols = 0;
		Location start = new Location();
		Location end = new Location();;
		char[][]matrix = null;
	}
	
	public Matrix(String level) throws MatrixException {
		String[] spllitedLevel = level.split(System.lineSeparator());
		rows = spllitedLevel.length;
		cols = spllitedLevel[0].length();
		if(rows <= 0 || cols <= 0)
			throw new MatrixException("Problem initiate matrix becuse of file missmatch rows:"+rows + " cols:"+ cols );
		
		this.matrix = new char[rows][cols];
		for(int i = 0; i < rows; i++)
		{
			String line = spllitedLevel[i];
			if(line == null)
				throw new MatrixException("Problem initiate matrix (line is null Problem) line:" + i); 
			for(int j = 0; j < cols; j++)
			{
				//System.out.println("got a char of "+ line.charAt(j));
				this.matrix[i][j] = line.charAt(j);
			}
		}
		start = new Location(findStartLocation(this.matrix, rows, cols));
		end = new Location(findEndLocation(this.matrix, rows, cols));
		
		//System.out.println("BEFORE CONSTUCTOR END: ");
		start.print();
		end.print();
	}

	public Matrix(File file) throws MatrixException
	{
		
		PipeMatrixConverter pmc = new PipeMatrixConverter();
		this.matrix = pmc.convert(file);
		this.rows = pmc.getRows();
		this.cols = pmc.getCols();
		this.setStart(pmc.findStartLocation(matrix, rows, cols));
		this.setEnd(pmc.findEndLocation(matrix, rows, cols));
	}
	

	public Matrix(char[][] matrix2) {
		for(int i = 0; i < rows; i++)
		{
			for(int j = 0; j < cols; j++)
			{
				this.matrix[i][j] = matrix2[i][j];
			}
		}
	}
	
	public Matrix(Matrix mat) throws MatrixException {
		this.rows = mat.getRows();
		this.cols = mat.getCols();
		if(rows <= 0 || cols <= 0)
			throw new MatrixException("Problem initiate matrix becuse of file missmatch rows:"+rows + " cols:"+ cols );
		
		this.matrix = new char[rows][cols];
		//System.out.println(rows);
		//System.out.println(cols);
		//System.out.println(mat.matrix[0][1]);
		for(int i = 0; i < this.rows; i++)
		{
			for(int j = 0; j < this.cols; j++)
			{
				//System.out.println(i + "   " + j);
				//System.out.println(mat.matrix[i][j]);
				this.matrix[i][j] = mat.matrix[i][j];
			}
		}
		this.setEnd(mat.getEnd());
		this.setStart(mat.getStart());
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

	public Location getEnd() {
		return end;
	}

	public void setEnd(Location end) {
		this.end = end;
	}

	public Location getStart() {
		return start;
	}

	public void setStart(Location start) {
		this.start = start;
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

	@Override
	public String toString() {
		String[] rowsArr = new String[rows];
		String line;
		for (int i=0; i < rows; i++) {
			line = "";
			for (int j=0; j < cols; j++)
				line += matrix[i][j];
			rowsArr[i] = line;
		}
		return String.join(System.lineSeparator(), rowsArr);
	}

	@Override
	public void print() {
		printMatrix(matrix, rows, cols);
	}
	
	@Override
	public String getRepr() {
		String matrixRepr;

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			matrixRepr = (new HexBinaryAdapter()).marshal(md.digest(toString().getBytes()));
			return matrixRepr;

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "foo";
		}
	}
	
	public static void main(String[]ags)
	{
	    File file = new File("C:\\test\\level1.txt");
	    try {
			Matrix mat = new Matrix(file);
			mat.print();
			Matrix mat2 = new Matrix(mat);
		} catch (MatrixException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}

