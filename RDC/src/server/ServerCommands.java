package server;

import java.net.*; 
import java.io.*; 

public class ServerCommands  implements Runnable { 
	private Socket socket; 
	private ServerSocket server; 
	private DataInputStream in; 
	private ReadClient x;
	private int port;
	private boolean connected = false;

	public ServerCommands(int inputPort) { 
		port = inputPort;
		startServer();
	} 
	
	private boolean startServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server started");
			connected = true;
			return true;
		} catch (IOException e) {
			System.out.println("Error occured. Is server already running?"); 
			e.printStackTrace();
			System.exit(0);
			return false;
		} 
	}
	
	
	public boolean closeServer() {
		System.out.println("Closing connection"); 
		try { 
			socket.close(); 
			in.close();
			System.out.println("Closed connection"); 
			connected = false;
			return true;
		} catch (IOException e) {
			System.out.println("Error occured. Could not close connection"); 
			e.printStackTrace();
			System.exit(0);
			return false;
		}
	}
	
	public void run(){
		while (true) {
        	try {
				socket = server.accept();
				System.out.println("Client accepted: " + socket.getInetAddress().getHostName());
				x = new ReadClient(socket);
				Thread t = new Thread(x);
		        t.start();
        	} catch (IOException e) {
        		System.out.println("Client could not be accepted.");
				e.printStackTrace();
				System.exit(0);
			} 
		}
	}
} 
