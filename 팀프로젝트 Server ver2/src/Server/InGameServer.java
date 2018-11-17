package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import Thread.CanVasColorThread;
import Thread.CanVasThread;

public class InGameServer {
	private Socket client_thickness = null,client_color=null;
	
	private ServerSocket server =null;
	private CanVasThread cvt=null;
	private CanVasColorThread cvct=null;
	private HashMap hm_thick, hm_color;
	private DataInputStream inputstream;
	private String ID;
	
	private void settingIngame() {
			try {
				server = new ServerSocket(LoginRegisterServer.getPort()+2);   
				hm_thick=new HashMap();
				hm_color=new HashMap();
					
				while(true) {
					client_thickness = server.accept();
					client_color=server.accept();
				
					inputstream = new DataInputStream(client_thickness.getInputStream());
					ID=inputstream.readUTF();
					
					cvt=new CanVasThread(client_thickness,hm_thick,ID);
					cvct=new CanVasColorThread(client_color,hm_color,ID);
					
					cvt.start();
					cvct.start();
				}   
			}catch(IOException e) {
				e.printStackTrace();
			}
		}   

	
	public static void main(String[] args) {
		InGameServer ingameserver = new InGameServer();
		ingameserver.settingIngame();
	}


}


 

