package View;

import javax.swing.*;
import java.awt.*;

public class MakeRoomView extends JPanel{
	private JPanel p_t, p_b, l1, l2;
	private JTextField title, pw;
	private JLabel l_title, l_pw;
	private JButton ok, cancel;
	private Frame n;
	
	//생성자 메소드
	public MakeRoomView() {
		n = new Frame("방 만들기");
		n.setSize(330,150);
		n.add(this);
		n.setLocation(400, 100);
		setLayout(new GridLayout(2,1));
		
		l1 = new JPanel();
	    l2 = new JPanel();
	    l1.setLayout(new FlowLayout(FlowLayout.CENTER));
	    l2.setLayout(new FlowLayout(FlowLayout.CENTER));
	       
		p_t = new JPanel();
		p_b = new JPanel();
		l_title = new JLabel("방 제목");
		l_pw = new JLabel("비밀번호");
		title = new JTextField(20);
		pw = new JTextField(20);
		ok = new JButton("확인");
		cancel = new JButton("취소");
		p_b.setLayout(new GridLayout(1,2));
		p_t.setLayout(new GridLayout(2,2));
		l1.add(l_title);
		p_t.add(l1);
		p_t.add(title);
		l2.add(l_pw);
		p_t.add(l2);
		p_t.add(pw);
		p_b.add(ok);
		p_b.add(cancel);
		add(p_t);
		add(p_b);
		n.setVisible(true);
	}


	public JButton getOk() {
		return ok;
	}


	public JButton getCancel() {
		return cancel;
	}
	
	public Frame getN() {
		return n;
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public String getPW() {
		return pw.getText();
	}
}
