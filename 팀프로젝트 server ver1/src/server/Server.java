package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Thread.LoginThread;

public class Server {

	private Socket client = null;
	private ServerSocket server =null;
	private LoginThread lt = null;

	
	private void setting() {
		try {
			server = new ServerSocket(5124);   
			while(true) {
				client = server.accept();
				lt = new LoginThread(client);
				lt.start();
			}   
		}catch(IOException e) {
			e.printStackTrace();
		}
	}   


	public static void main(String[] args) {
		Server mainserver = new Server();
		mainserver.setting();
	}

}