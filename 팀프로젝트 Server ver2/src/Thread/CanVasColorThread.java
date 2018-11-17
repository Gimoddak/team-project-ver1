package Thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class CanVasColorThread extends Thread {
	private String  m_ID, m_color;
	private DataOutputStream outputstream;
	private DataInputStream inputstream;
	private Socket m_s;
	private HashMap m_hm;
	
	public CanVasColorThread(Socket s,HashMap hm,String ID){
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
					m_color=inputstream.readUTF();
					broadcast(m_color);
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
		public void broadcast(String color) {
			synchronized(m_hm) {
				Collection collection =m_hm.values();
				Iterator iter=collection.iterator();
				try {
				while(iter.hasNext()) {
					outputstream=(DataOutputStream)iter.next();
					outputstream.writeUTF(color);						
				}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			}
}
