package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class CanVasThread extends Thread {
	private String  m_ID,m_x1,m_y1,m_x2,m_y2,m_thickness;
	private DataOutputStream outputstream;
	private DataInputStream inputstream;
	private Socket m_s;
	private HashMap m_hm;
	
	public CanVasThread(Socket s,HashMap hm,String ID){
		try {
			m_s = s;
			m_hm=hm;
			m_ID=ID;
			
			inputstream = new DataInputStream(m_s.getInputStream());
			outputstream = new DataOutputStream(m_s.getOutputStream());       
		
			
			synchronized(m_hm) {
				m_hm.put(m_ID, outputstream);
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
		
		public void run() {
			try{
				while(inputstream!=null) {
					
					m_x1=inputstream.readUTF();
					m_y1=inputstream.readUTF();
					m_x2=inputstream.readUTF();
					m_y2=inputstream.readUTF();
					m_thickness=inputstream.readUTF();
					
					broadcast(m_x1,m_y1,m_x2,m_y2,m_thickness);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
				
			}
			finally {
				synchronized(m_hm) {
					m_hm.remove(m_ID);

				}
				try {
					if(m_s!=null)
						m_s.close();
				}catch(Exception ex) {}
			}		
	}
		public void broadcast(String x1,String y1,String x2, String y2, String thickness) {
			synchronized(m_hm) {
				Collection collection =m_hm.values();
				Iterator iter=collection.iterator();
				try {
				while(iter.hasNext()) {
					outputstream=(DataOutputStream)iter.next();
					outputstream.writeUTF(x1);
					outputstream.writeUTF(y1);
					outputstream.writeUTF(x2);
					outputstream.writeUTF(y2);
					outputstream.writeUTF(thickness);				
				}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			}
}
