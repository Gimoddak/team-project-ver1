package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginView extends JPanel{
	// 추가 변수
	private JButton login_bt, join_bt, exit_bt;			// 버튼 참조변수
	private JLabel id_l, pw_l, back_l, menubar_l;		// 라벨 참조변수
	private JTextField id_tx;							// 텍스트필드 참조변수
	private JPasswordField pw_pw;						// 패스워드필드 참조변수

	// 배경화면이미지에 관한 이미지아이콘 생성.
	private ImageIcon back_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\background.jpg");
	// 메뉴바에 관한 이미지아이콘 생성.
	private ImageIcon menu_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\menubar.png");
	// 기본 종료버튼에 관한 이미지아이콘 생성.
	private ImageIcon exitbasic_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonBasic.png");
	// 종료버튼위에 마우스를 올렸을시에 보여줄 이미지에 관한 이미지 아이콘 생성.
	private ImageIcon exitentered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonEntered.png");

	// login_bt버튼에 관한 이미지아이콘 생성.
	private ImageIcon login_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\login.png");
	// login_bt버튼에 마우스를 올렸을시에 이미지에 관한 이미지아이콘 생성.
	private ImageIcon loginEntered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\loginEntered.png");
	// join_bt버튼에 관한 이미지아이콘 생성.
	private ImageIcon join_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\join.png");
	// join_bt버튼에 마우스를 올렸을시에 이미지에 관한 이미지아이콘 생성.
	private ImageIcon joinEntered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\joinEntered.png");
	// id_tx라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon id_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\id.png");
	// pw_pw라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon pw_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\pw.png");

	// 마우스의 x좌표 y좌표를 저장할 변수.
	private int mouseX, mouseY;

	// 생성자 메소드
	public LoginView() {
		// 절대경로를 사용하기 위한 설정.
		setLayout(null);

		// back_img를 삽입하면서 배경용 라벨을 생성.
		back_l = new JLabel(back_img);
		// 배경이미지의 위치와 크기 지정.
		back_l.setBounds(0, 0, 1280, 720);

		// 종료버튼에 exitbasic_img를 삽입하며 생성.
		exit_bt = new JButton(exitbasic_img);
		// 종료버튼의 위치를 지정.
		exit_bt.setBounds(1245, 0, 30, 30);
		// 종료버튼의 테두리 설정을 해제시킴.
		exit_bt.setBorderPainted(false);
		// 종료버튼의 배경을 채우지 않음.
		exit_bt.setContentAreaFilled(false);
		// 종료버튼의 포커스 표시 설정.
		exit_bt.setFocusPainted(false);



		// login_img를 삽입하며 버튼객체 생성.
		login_bt = new JButton(login_img);
		// login_bt의 위치와 크기 설정.
		login_bt.setBounds(515, 550, 120, 60);
		// 테두리 지움.
		login_bt.setBorderPainted(false);
		// 배경을 채우지 않게 설정.
		login_bt.setContentAreaFilled(false);
		// 포커스 효과를 없앰.
		login_bt.setFocusPainted(false);
		// 배경을 투명하게함.
		login_bt.setOpaque(false);




		// join_img를 삽입하며 버튼객체 생성.
		join_bt = new JButton(join_img);
		// 크기와 위치를 지정.
		join_bt.setBounds(660, 550, 120, 60);
		// 테두리 지움.
		join_bt.setBorderPainted(false);
		// 배경을 채우지 않게 설정.
		join_bt.setContentAreaFilled(false);
		// 포커스 효과를 없앰.
		join_bt.setFocusPainted(false);
		// 배경을 투명하게함.
		join_bt.setOpaque(false);



		// 메뉴바 라벨을 menu_img를 삽입하며 생성.
		menubar_l = new JLabel(menu_img);
		// 메뉴바의 위치를 설정.
		menubar_l.setBounds(0, 0, 1280, 30);





		// id_img를 삽입하며 id_l라벨을 생성.
		id_l = new JLabel(id_img);
		// id_l의 위치와 크기를 지정.
		id_l.setBounds(490, 413, 100,100);

		// pw_img를 삽입하며 pw_l라벨을 생성.
		pw_l = new JLabel(pw_img);
		// pw_l의 위치와 크기를 지정.
		pw_l.setBounds(490, 463, 100,100);

		// 텍스트필드 객체 생성.
		id_tx = new JTextField(20);
		// 텍스트필드에 입력되는 글자의 폰트를 변경하기 위해 폰트객체를 생성.
		Font font = new Font("arian", Font.BOLD, 20);
		// id_tx 텍스트필드의 폰트를 font로 설정.
		id_tx.setFont(font);
		// id_tx의 위치와 크기를 지정.
		id_tx.setBounds(570, 450, 200, 25);
		// 텍스트필드에 입력되는 글자의 색을 지정.
		id_tx.setForeground(Color.BLACK);
		// 텍스트필드의 테두리를 지움.
		id_tx.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// 패스워드필드 객체 생성.
		pw_pw = new JPasswordField(20);
		// pw_pw의 위치와 크기를 지정.
		pw_pw.setBounds(570, 500, 200, 25);
		// pw_pw의 폰트를 font로 설정.
		pw_pw.setFont(font);
		// 필드에 입력되는 글자의 색을 지정.
		pw_pw.setForeground(Color.BLACK);
		// 패스워드필드의 테두리를 지움.
		pw_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());

		// add 부분
		add(login_bt);
		add(join_bt);
		add(id_l);
		add(pw_l);
		add(id_tx);
		add(pw_pw);
		add(exit_bt);
		add(menubar_l);
		add(back_l);
	}

	public JButton getLogin_bt() {
		return login_bt;
	}

	public JButton getJoin_bt() {
		return join_bt;
	}

	public JButton getExit_bt() {
		return exit_bt;
	}

	public JLabel getMenubar_l() {
		return menubar_l;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public ImageIcon getExitbasic_img() {
		return exitbasic_img;
	}

	public ImageIcon getExitentered_img() {
		return exitentered_img;
	}

	public ImageIcon getLogin_img() {
		return login_img;
	}

	public ImageIcon getLoginEntered_img() {
		return loginEntered_img;
	}

	public ImageIcon getJoin_img() {
		return join_img;
	}

	public ImageIcon getJoinEntered_img() {
		return joinEntered_img;
	}

	public String getID() {
		return id_tx.getText();
	}
	
	public String getPW() {
		return pw_pw.getText();
	}
	
	public void resetPW() {
		pw_pw.setText("");
	}
	
	public void resetText() {
		id_tx.setText("");
		pw_pw.setText("");
	}
	
}
