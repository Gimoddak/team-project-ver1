package Thread;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JColorChooser;

import Control.IngameControler;
import View.IngameView;

public class CanVasColorReceiveThread extends Thread{

	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private Socket s;	//�׸��� ���Ϻ���
	private IngameView iv;
	private IngameControler ic;
	private String ID,color;
	
	public CanVasColorReceiveThread(Socket s,IngameView view,String ID) {
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
			
				color=inputstream.readUTF();
				Object obj=color;
				
				if(obj == iv.getPencil_bt()) { 
					if(iv.isTf() == false) {
						 // �׷����� ������ ������ ����
						iv.getGraphics2().setColor(Color.BLACK);
						
						
					} else {
						// �׷����� ������ selectedColor������ ������ ����
						iv.getGraphics2().setColor(iv.getSelectedColor());
					
						
					}
				// ���찳��ư�� �������� �� if���� ��Ϲ����� ���� ����
				} else if(obj == iv.getEraser_bt())	{
					// �׷����� ������ ������� ����� ������ ������� ���� �׷��� �������� ��ó�� ���̰� �Ѵ�.
					iv.getGraphics2().setColor(Color.WHITE);
				}
				else if(obj == iv.getColorselect_bt()) {
					// ���� ��������� �˷���.
					iv.setTf(true);
					// JColorChooser ����
					JColorChooser chooser = new JColorChooser();
					// ���õ� �÷����� selectedColor�� ����
					iv.setSelectedColor(chooser.showDialog(null, "Color", Color.orange));
					// ���� ���� ����.
					iv.getGraphics2().setColor(iv.getSelectedColor());
				}
				else
				{
					// �г��� �ʱ�ȭ�Ͽ� �ٽ� �׷��ش�.
					iv.repaint();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
}
}



