package solution;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import model.Matrix;

public interface ServerSolver {
	ArrayList<String> solve(Matrix matrix) throws IOException;

}
