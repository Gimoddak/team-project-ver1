package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class RegisterSendThread extends Thread{
	private Socket s;
	private String ID, PW, msg, avatar;
	private DataOutputStream outputstream;
	
	
	public RegisterSendThread(Socket s, String msg, String ID, String PW, String avatar) {
		this.s = s;
		this.msg = msg;
		this.ID = ID;
		this.PW = PW;
		this.avatar = avatar;
		try {
			outputstream = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
	public void run() {
		try {
			outputstream.writeUTF(msg);
			outputstream.writeUTF(ID);
			outputstream.writeUTF(PW); 
			outputstream.writeUTF(avatar);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}

