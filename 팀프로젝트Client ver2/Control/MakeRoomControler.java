package Control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Starter.Application;
import View.MakeRoomView;

public class MakeRoomControler {
	private MakeRoomView view;
	private ButtonListener listen;
	private Application app;
	private WaitingControler wc;
	private IngameControler ic;
	private Socket s;
	private String ID;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// Ȯ�� ��ư Ŭ����
			if(event.getSource() == view.getOk()) {
				// ���� �� ��ü�� �� ������ ����
				ic.setTitle(wc.getRoom_num(), view.getTitle());
				// ���� �� ��ü�� ��й�ȣ�� ����
				ic.setPW(wc.getRoom_num(), view.getPW());
				
				 try {
					s = new Socket(s.getInetAddress(), s.getPort()+1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				 ic.setUserInfo(s, ID);
				 
				JOptionPane.showMessageDialog(null, "�濡 �����մϴ�");
				app.changePanel();
				app.setSize(1280,720);
				app.setTitle("������ : " + ic.getTitle(wc.getRoom_num()));
				//ic.setInfo(wc.getRoom_num());
				// ���ǿ��� �� ��ư�� Ȱ��ȭ
				wc.Enable_Room(view.getTitle());
				// �游��� â ����
				view.getN().dispose();
			// ��� ��ư ���ý�
			} else {
				// �游��� â ����
				view.getN().dispose();
			}
		}
	}
	
	// ������ �޼ҵ�
	public MakeRoomControler(Application app, MakeRoomView mv, WaitingControler wc, IngameControler ic, Socket s) {
		this.app = app;
		view = mv;
		this.wc = wc;
		this.ic = ic;
		this.s=s;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getOk().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
