package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import View.RegisterView;
import View.WaitingView;

public class ChattingReceiveThread extends Thread {
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private Socket s;
	private WaitingView wv;
	private String tot,ID,total="";
//	int first = 1;
	public ChattingReceiveThread(Socket s,WaitingView view,String ID) {
		this.s = s;
		this.wv=view;
		this.ID=ID;
		
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream=new DataOutputStream((s.getOutputStream())); 
			
			outputstream.writeUTF(ID);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void run() {
		try {
			
			while((tot=inputstream.readUTF())!=null) {
			//tot=inputstream.readUTF();
				total+=tot+"\n";
				
				wv.setChatText(total);	
		}
			

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}


	
	
