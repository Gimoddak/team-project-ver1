package Control;
import View.RoomLockView;
import Control.WaitingControler;
import Control.IngameControler;
import java.awt.event.*;
import javax.swing.*;
import Starter.Application;
public class RoomLockControler {
	private Application app;
	private ButtonListener listen;
	private RoomLockView view;
	private WaitingControler wc;
	private IngameControler ic;
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			// 확인 버튼 선택시
			if(event.getSource() == view.getOk()) {
				// 입력한 비밀번호가 입장하려는 방의 비밀번호와 같으면
				if(ic.getPW(wc.getSelect()).equals(view.getPW())) {
					JOptionPane.showMessageDialog(null, "방에 입장합니다");
					app.changePanel();
					app.setTitle("방제목 : " + ic.getTitle(wc.getSelect()));
					//ic.setInfo(wc.getSelect());
					view.getN().dispose();
				// 입력한 비밀번호가 입장하려는 방의 비밀번호와 틀리면
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다");
					view.resetPW();
				}
			// 취소 버튼 선택시
			} else {
				view.getN().dispose();
			}
		}
		
	}
	
	// 생성자 메소드
	public RoomLockControler(Application app, RoomLockView rv, WaitingControler wc, IngameControler ic) {
		this.app = app;
		view = rv;
		this.wc = wc;
		this.ic = ic;
	}
	
	public void buttonHandler() {
		listen = new ButtonListener();
		view.getOk().addActionListener(listen);
		view.getCancel().addActionListener(listen);
	}
}
