package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import solution.ServerSolver;

public class HTTPServerSolver implements ServerSolver{
	
	URL url;
	
	public HTTPServerSolver(String protocol, String server, int port, String endpoint) throws URISyntaxException, MalformedURLException {
		URI uri = new URI(protocol, null, server, port, endpoint, null, null);
		url = uri.toURL();
	}

	@Override
	public ArrayList<String> solve(Matrix matrix) throws IOException {
		//URL url = new URL("http://localhost:8080/pipeGameServer/solve");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "plain/text");
		con.setDoOutput(true);
        System.out.println("Connected to server");
        
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        		
        PrintWriter outTServer = new PrintWriter(wr);
        
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
        BufferedReader inFServer = new BufferedReader(new InputStreamReader(con.getInputStream()));

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
