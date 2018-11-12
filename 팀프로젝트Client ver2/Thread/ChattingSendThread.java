package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.WaitingView;

public class ChattingSendThread extends Thread {
	private Socket s;
	private String ID, msg, tot;
	private WaitingView wv;
	private DataOutputStream outputstream;
	
	public ChattingSendThread(Socket s, String ID,String msg,WaitingView view){
		this.s = s;
		this.ID=ID;
		this.msg=msg;
		this.wv=view;
		this.tot = this.ID + ":" + this.msg;
		try {
			outputstream=new DataOutputStream((s.getOutputStream()));

		
			
			wv.setInitChatText();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void run() {
		try {
			outputstream.writeUTF(tot);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			outputstream = null;
		}
	}

}
