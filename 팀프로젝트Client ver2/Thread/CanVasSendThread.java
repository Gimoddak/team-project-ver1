package Thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import View.WaitingView;

public class CanVasSendThread extends Thread{
	private Socket s;	//그림판 소켓 변수
	private String ID;
	private String x1,y1,x2, y2,thickness;
	private DataOutputStream outputstream;
	
	public CanVasSendThread(Socket s, String x1, String y1,String x2, String y2, String thickness){
		this.s = s;
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.thickness=thickness;		
		
		try {
			outputstream=new DataOutputStream((s.getOutputStream()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void run() {
		try {
			
			outputstream.writeUTF(x1);
			outputstream.writeUTF(y1);
			outputstream.writeUTF(x2);
			outputstream.writeUTF(y2);
			outputstream.writeUTF(thickness);
			
					
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally {
			outputstream = null;
		}
	}
}
