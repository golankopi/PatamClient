package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import solution.ServerSolver;

public class SimpleServerHandler implements ServerSolver{

	@Override
	public ArrayList<String> solve(Matrix matrix) throws IOException {

        Socket theServer = new Socket("localhost", 31);
        System.out.println("Connected to server");
        		
        PrintWriter outTServer = new PrintWriter(theServer.getOutputStream());
        BufferedReader inFServer = new BufferedReader(new InputStreamReader(theServer.getInputStream()));
        
        char[][] charMatrix = matrix.getMatrix();
        String levelLine;
        
        for (int i=0; i< matrix.getRows(); i++) {
        	levelLine = "";
            for (int j=0; j< matrix.getCols(); j++)
            	levelLine += charMatrix[i][j];
            outTServer.println(levelLine);
        }

        outTServer.println("done");
        outTServer.flush();	
        
        System.out.println("Waiting for response");
        
        String line;
        ArrayList<String> lines = new ArrayList<String>();
        String level;
        try {
            while (!(line = inFServer.readLine()).equals("done")) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
        	inFServer.close();
        	outTServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return lines;
	}

}
