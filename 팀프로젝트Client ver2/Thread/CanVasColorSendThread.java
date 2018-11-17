package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CanVasColorSendThread extends Thread {

	private Socket s;	//그림판 소켓 변수
	private String ID;
	private String color;
	private DataOutputStream outputstream;
	
	public CanVasColorSendThread(Socket s, String color){
		this.s = s;
		this.color=color;	
		
		try {
			outputstream=new DataOutputStream((s.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void run() {
		try {
			
			outputstream.writeUTF(color);
			
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			outputstream = null;
		}
	}
}
