package exceptions;

public class MatrixException extends Exception{
	String message;
	public MatrixException(String message) {
		super(message);
		//System.out.println(message);
		//this.message = message;
    }
	public void PrintErrorMSG()
	{
		System.out.println(message);
	}
}
