package util;

import java.io.File;

import exceptions.MatrixException;
import logic.Problem;

public interface MatrixConvertor {
	public char[][] convert(File file) throws MatrixException;
	public Problem convert(String s) throws MatrixException;
	public String reConvert(Problem p) throws MatrixException;
}
