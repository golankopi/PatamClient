package server;

import java.io.IOException;

public interface Server {
	
	public void start(ClientHandler clientHandler);
	public void stop();
}
