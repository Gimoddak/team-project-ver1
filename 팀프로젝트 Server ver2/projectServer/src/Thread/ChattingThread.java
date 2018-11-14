package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ChattingThread extends Thread {
	private String  ID,tot;
	private DataOutputStream outputstream;
	private DataInputStream inputstream;
	private Socket s;
	private HashMap hm;
	
	public ChattingThread(Socket s,HashMap hm,String ID){
		try {
			this.s = s;
			this.hm=hm;
			
			inputstream = new DataInputStream(s.getInputStream());
			outputstream = new DataOutputStream(s.getOutputStream());       
		
			
			synchronized(hm) {
				hm.put(ID, outputstream);
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
		
		public void run() {
			try{
				while((tot=inputstream.readUTF())!=null) {
					//tot=inputstream.readUTF();
					
					broadcast(tot);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
				
			}
			finally {
				synchronized(hm) {
					hm.remove(ID);

				}
				try {
					if(s!=null)
						s.close();
				}catch(Exception ex) {}
			}		
	}
		public void broadcast(String message) {
			synchronized(hm) {
				Collection collection =hm.values();
				Iterator iter=collection.iterator();
				try {
				while(iter.hasNext()) {
					outputstream=(DataOutputStream)iter.next();
					outputstream.writeUTF(message);
					
				}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			}
}
