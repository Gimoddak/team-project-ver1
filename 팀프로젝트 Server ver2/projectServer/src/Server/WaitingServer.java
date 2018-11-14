package Server;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Thread.ChattingThread;
import Thread.LoginRegisterThread;

public class WaitingServer {

	private Socket client = null;
	private ServerSocket server =null;
	private ChattingThread ct=null;
	private HashMap hm;
	private DataInputStream inputstream;
	private String ID;
	
	private void settingWaitingRoom() {
		try {
			server = new ServerSocket(LoginRegisterServer.getPort()+1);   
			HashMap hm=new HashMap();
			
			while(true) {
				client = server.accept();
				inputstream = new DataInputStream(client.getInputStream());
				
				ID=inputstream.readUTF();
				
				ct=new ChattingThread(client,hm,ID);
				ct.start();
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

