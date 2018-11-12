package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;

public class LoginSendThread extends Thread {
	private Socket s;
	private String ID, PW, msg;
	private DataOutputStream outputstream;

	
	public LoginSendThread(Socket s, String msg, String ID, String PW){
		this.s = s;
		this.msg = msg;
		this.ID = ID;
		this.PW = PW;
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
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}


