package pipeGame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import exceptions.MatrixException;
import logic.Matrix;
import logic.Problem;
import logic.Solution;
import managment.CacheManager;
import managment.FileCacheManager;
import managment.Solver;
import server.ClientHandler;

public class PipeSimpleClientHandler implements ClientHandler {
	
	private CacheManager cacheManager;
	private Solver solver;
	private Solution solution;
	
	@Override
	public void handle(InputStream is, OutputStream os)  {

        PrintWriter outTC = new PrintWriter(os);
        BufferedReader inFClient = new BufferedReader(new InputStreamReader(is));
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        String level;
        try {
            while (!(line = inFClient.readLine()).equals("done")) {
                lines.add(line);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(lines);
        level = String.join(System.lineSeparator(), lines);
        //TODO change to a problem intead of matrix
    	Problem m;
		try {
			m = new Matrix(level);
		} catch (MatrixException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			return;
		}
        cacheManager = new FileCacheManager(m.getRepr());
        String solvedLevel = null;
        /*
        try {
			 solvedLevel = cacheManager.load();
		} catch (IOException e1) {
			solvedLevel = null;
		}
        */
        if (solvedLevel == null) {
        	solver = new PGS();
        	solution = solver.solve(m);       	
        	solvedLevel = String.join(System.lineSeparator(), solution.getSolutionList());

	
	        try {
				cacheManager.save(solvedLevel);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        else {
        	//System.out.println("Awesome! level found in cache!");
        }
        System.out.println("going to return:");
        System.out.println(solvedLevel);
        
        for(String solvedLine : solvedLevel.split(System.lineSeparator())) {
        	outTC.println(solvedLine);
            System.out.println("sending:");
            System.out.println(solvedLine);
        }
        outTC.println("done");
        outTC.flush();
        try {
			inFClient.close();
	        outTC.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}

}