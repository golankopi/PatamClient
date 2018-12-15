package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	
	void handle(InputStream inputStream,OutputStream outputStream );
}
