package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Thread.LoginThread;

public class WaitingServer {

	private Socket client = null;
	private ServerSocket server =null;
	private LoginThread lt = null;

	private void settingWaitingRoom() {
		try {
			server = new ServerSocket(LoginRegisterServer.getport()+1);   

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
		WaitingServer waitingserver = new WaitingServer();
		waitingserver.settingWaitingRoom();
	}

}