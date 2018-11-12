package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Starter.Application;
import View.RegisterView;

public class RegisterRecieveThread extends Thread {
	private String result;
	private DataInputStream inputstream;
	private Socket s;
	private RegisterView view;
	
	public RegisterRecieveThread(RegisterView view, Socket s) {
		this.view = view;
		this.s = s;
		try {
			inputstream = new DataInputStream(s.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	public void run() {
		try {
			result = inputstream.readUTF();
		

			if(result.equals("true")) {

				JOptionPane.showMessageDialog(null, "회원가입 되었습니다");
				// 프레임 종료
				
				view.dispose();
				// 취소 버튼 선택시
				// Application이 card Layout으로 되있다고 했지?
				// Application 프레임 안에 순서대로 로그인, 대기실, 인게임이 들어있는데 changePanel이
				// 바로 다음 순서 패널로 화면 전환시켜주는애임
			} else {
				JOptionPane.showMessageDialog(null, "중복된 아이디가 존재합니다.");
			}

		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
