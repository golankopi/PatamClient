package pipeGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.ClientHandler;
import server.Server;
import pipeGame.PipeSimpleClientHandler;

@WebServlet(name = "GetSolution", urlPatterns = {"/solve"})
public class PipeHTTPServer extends HttpServlet {
    private ServerSocket serverSocket;
    private boolean stop = false;
	final PipeSimpleClientHandler clientHandler = new PipeSimpleClientHandler();

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletInputStream in = request.getInputStream();
    	ServletOutputStream out = response.getOutputStream();
    	    	
    	clientHandler.handle(in, out);
    }
}
