package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ReadClient implements Runnable {
	private Socket socket = null; 
	private DataInputStream in = null; 
	

	public ReadClient(Socket inputSocket) {
		socket = inputSocket;
	}
	

	public void run() {
		while (true){
			try	{ 	
				// takes input from the client socket 
				if (!socket.isClosed()) {
	                InputStream is = socket.getInputStream();
	                InputStreamReader isr = new InputStreamReader(is);
	                BufferedReader br = new BufferedReader(isr);
	                String number = br.readLine();
	                System.out.println("Message received from " + socket.getInetAddress().getHostName() + ": " + number);
	                if (number.equals("/d")) {
	                	System.out.println(socket.getInetAddress().getHostName() + " has disconnected.");
	                	socket.close();
	                }
				}
			} 
			catch(IOException i) { 
				System.out.println(i); 	
			} 
		}
		
	}
}
