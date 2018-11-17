package Thread;

import java.awt.BasicStroke;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Control.IngameControler;
import View.IngameView;

public class CanVasReceiveThread extends Thread{
	
	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private Socket s;	//그림판 소켓변수
	private IngameView iv;
	private IngameControler ic;
	private String ID,x1,y1,x2,y2,thickness;
	
	public CanVasReceiveThread(Socket s,IngameView view,String ID) {
		this.s = s;
		this.iv=view;
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
			
			while(inputstream!=null) {
				x1=inputstream.readUTF();
				y1=inputstream.readUTF();
				x2=inputstream.readUTF();
				y2=inputstream.readUTF();
				thickness=inputstream.readUTF();
				
				
				iv.getGraphics2().setStroke(new BasicStroke(Integer.parseInt(thickness), BasicStroke.CAP_ROUND, 0));
				
				iv.getGraphics2().drawLine(Integer.parseInt(x1),Integer.parseInt(y1),Integer.parseInt(x2),Integer.parseInt(y2));
				
				
	//			iv.getGraphics2().setStroke(new BasicStroke(iv.getThickness(), BasicStroke.CAP_ROUND, 0));
	//			
	//			iv.getGraphics2().drawLine(iv.getStartX()+82, iv.getStartY()+100, iv.getEndX()+82, iv.getEndY()+100);
	//
	//			//writeUFT(e.getX().gety)
	//			//writeUTF(getThickness)
	//			iv.setStartX(iv.getEndX());
	//			iv.setStartY(iv.getEndY());
	//			
				iv.setStartX(Integer.parseInt(x2));
				iv.setStartY(Integer.parseInt(y2));
				}
		}catch(IOException e){
			e.printStackTrace();
		}
}
}


