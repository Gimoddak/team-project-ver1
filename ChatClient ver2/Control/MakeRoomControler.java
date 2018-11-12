package Control;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import View.MakeRoomView;
import Control.WaitingControler;
import Control.IngameControler;

import Starter.Application;

public class MakeRoomControler {
	private MakeRoomView view;
	private ButtonListener listen;
	private Application app;
	private WaitingControler wc;
	private IngameControler ic;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// 확인 버튼 클릭시
			if(event.getSource() == view.getOk()) {
				// 게임 방 객체의 방 제목을 지정
				ic.setTitle(wc.getRoom_num(), view.getTitle());
				// 게임 방 객체의 비밀번호를 지정
				ic.setPW(wc.getRoom_num(), view.getPW());
				JOptionPane.showMessageDialog(null, "방에 입장합니다");
				app.changePanel();
				app.setSize(1280,720);
				app.setTitle("방제목 : " + ic.getTitle(wc.getRoom_num()));
				//ic.setInfo(wc.getRoom_num());
				// 대기실에서 방 버튼을 활성화
				wc.Enable_Room(view.getTitle());
				// 방만들기 창 종료
				view.getN().dispose();
			// 취소 버튼 선택시
			} else {
				// 방만들기 창 종료
				view.getN().dispose();
			}
		}
	}
	
	// 생성자 메소드
	public MakeRoomControler(Application app, MakeRoomView mv, WaitingControler wc, IngameControler ic) {
		this.app = app;
		view = mv;
		this.wc = wc;
		this.ic = ic;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getOk().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
