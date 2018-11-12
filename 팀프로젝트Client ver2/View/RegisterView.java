package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterView extends JFrame{
	private JTextField id_tx;		// 텍스트필드 참조변수
	private JPasswordField pw_pw;	// 패스워드필드 참조변수
	private JLabel back_l, id_l, pw_l, menubar_l, char1_l, char2_l, char3_l, choose_l;	//라벨 참조변수
	private JButton sign_bt, cancel_bt;	// 버튼 참조변수
	private JRadioButton character1, character2, character3;	// 라디오버튼 참조변수
	private ButtonGroup charGroup;	// 버튼그룹 참조변수

	// 마우스의 x좌표 y좌표를 저장할 변수.
	private int mouseX, mouseY;
	
	// back_l라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon back_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\registerback.jpg");
	// 메뉴바에 관한 이미지아이콘 생성.
	private ImageIcon menu_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\menubar.png");
	// id_l라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon id_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\idImg.png");
	// pw_l라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon pw_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\passwordImg.png");
	// sign_l라벨 이미지에 관한 이미지아이콘 생성.
	private ImageIcon signupBasic_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\signupBasic.png");
	// 회원가입 버튼에 마우스를 올렸을 경우에 보여줄 이미지에 관한 이미지아이콘 생성.
	private ImageIcon signupEntered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\signupEntered.png");
	// 회원가입 창내의 취소버튼의 이미지에 관한 이미지아이콘 생성
	private ImageIcon cancelBasic_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\cancelBasic.png");
	// 회원가입 창내의 취소버튼위에 마우스를 올렸을 경우에 보여줄 이미지에 관한 이미지아이콘 생성.
	private ImageIcon cancelEntered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\cancelEntered.png");
	// 캐릭터1에 대한 이미지아이콘 생성.
	private ImageIcon character1_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\character1.png");
	// 캐릭터2에 대한 이미지아이콘 생성.
	private ImageIcon character2_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\character2.png");
	// 캐릭터3에 대한 이미지아이콘 생성.
	private ImageIcon character3_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\character3.png");
	// 아바타를 선택해주세요. 라는 문구가 적힌 이미지
	private ImageIcon choose_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\choose.png");
	
	
	//생성자 메소드
	public RegisterView() {
		// 절대경로를 사용하기 위한 설정.
				setLayout(null);
				// 프레임의 사이즈를 지정.
				setSize(450, 650);
				// 기존 메뉴바를 없앰.
				setUndecorated(true);
				// 크기를 변경할 수 없게끔 함.
				setResizable(false);
				// 프레임의 위치를 지정함.
				setLocation(400, 80);
				
				// 배경라벨에 이미지를 넣으며 생성
				back_l = new JLabel(back_img);
				// 배경라벨의 위치를 지정
				back_l.setBounds(0, 0, 450, 650);
				
				// 메뉴바 라벨을 menu_img를 삽입하며 생성.
				menubar_l = new JLabel(menu_img);
				// 메뉴바의 위치를 설정.
				menubar_l.setBounds(0, 0, 1280, 30);

				
				
				// 아이디와 비번 필드에 사용할  폰트생성
				Font font = new Font("arian", Font.BOLD, 20);
				
				// 텍스트필드 생성.
				id_tx = new  JTextField();
				// 필드의 폰트를 font로 지정.
				id_tx.setFont(font);
				// 필드의 테두리를 지움
				id_tx.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				// 필드의 위치를 지정.
				id_tx.setBounds(150, 62, 260, 35);
				
				// 패스워드필드 생성.
				pw_pw = new JPasswordField();
				// 필드의 폰트를 font로 지정.
				pw_pw.setFont(font);
				// 필드의 테두리를 지움.
				pw_pw.setBorder(javax.swing.BorderFactory.createEmptyBorder());
				// 필드의 위치를 지정.
				pw_pw.setBounds(150, 112, 260, 35);
				
				// 아이디에 관한 라벨을 생성
				id_l = new JLabel(id_img);
				// 아이디라벨의 위치를 지정.
				id_l.setBounds(50, 30, 100, 100);
				// 비번에 관한 라벨을 생성
				pw_l = new JLabel(pw_img);
				// 비번라벨의 위치를 지정.
				pw_l.setBounds(50, 80, 100, 100);
				
				// 가입버튼을 생성.
				sign_bt = new JButton(signupBasic_img);
				// 가입버튼의 위치를 지정.
				sign_bt.setBounds(50, 580, 150, 36);
				// 가입버튼의 테두리 설정을 해제시킴.
				sign_bt.setBorderPainted(false);
				// 가입버튼의 배경을 채우지 않음.
				sign_bt.setContentAreaFilled(false);
				// 가입버튼의 포커스 표시 설정.
				sign_bt.setFocusPainted(false);
				
				
				
				// 종료버튼을 생성.
				cancel_bt = new JButton(cancelBasic_img);
				// 종료버튼의 위치를 지정.
				cancel_bt.setBounds(250, 570, 150, 60);
				// 종료버튼의 테두리 설정을 해제시킴.
				cancel_bt.setBorderPainted(false);
				// 종료버튼의 배경을 채우지 않음.
				cancel_bt.setContentAreaFilled(false);
				// 종료버튼의 포커스 표시 설정.
				cancel_bt.setFocusPainted(false);
				
				
				
				// 캐릭터1을 나타낼 라디오버튼 생성.
				character1 = new JRadioButton();
				// 라디오버튼의 위치를 지정.
				character1.setBounds(70, 500, 50, 50);
				// 라디오버튼의 테두리를 없앰.
				character1.setBorderPainted(false);
				// 라디오버튼의 배경을 채우지않게 설정.
				character1.setContentAreaFilled(false);
				// 포커스 효과를 없앰.
				character1.setFocusPainted(false);
				
				
				
				
				// 캐릭터2을 나타낼 라디오버튼 생성.
				character2 = new JRadioButton();
				// 라디오버튼의 위치를 지정.
				character2.setBounds(220, 500, 50, 50);
				// 라디오버튼의 테두리를 없앰.
				character2.setBorderPainted(false);
				// 라디오버튼의 배경을 채우지않게 설정.
				character2.setContentAreaFilled(false);
				// 포커스 효과를 없앰.
				character2.setFocusPainted(false);
				
				
				
				// 캐릭터3을 나타낼 라디오버튼 생성.
				character3 = new JRadioButton();
				// 라디오버튼의 위치를 지정.
				character3.setBounds(360, 500, 50, 50);
				// 라디오버튼의 테두리를 없앰.
				character3.setBorderPainted(false);
				// 라디오버튼의 배경을 채우지않게 설정.
				character3.setContentAreaFilled(false);
				// 포커스 효과를 없앰.
				character3.setFocusPainted(false);
				
				
				
				
				// 캐릭터1이 선택되어 있게 함.
				character1.setSelected(true);
				
				// 캐릭터에 관한 버튼들을 그룹화 시키기 위한 버튼그룹 생성.
				charGroup = new ButtonGroup();
				// 버튼그룹에 캐릭터1을 추가.
				charGroup.add(character1);
				// 버튼그룹에 캐릭터2를 추가.
				charGroup.add(character2);
				// 버튼그룹에 캐릭터3을 추가.
				charGroup.add(character3);
				
				// 캐릭터1에 관한 라벨생성.
				char1_l = new JLabel(character1_img);
				// 라벨의 위치를 지정
				char1_l.setBounds(30, 360, 100, 100);
				// 캐릭터2에 관한 라벨생성.
				char2_l = new JLabel(character2_img);
				// 라벨의 위치를 지정
				char2_l.setBounds(180, 320, 100, 150);
				// 캐릭터3에 관한 라벨생성.
				char3_l = new JLabel(character3_img);
				// 라벨의 위치를 지정
				char3_l.setBounds(320, 330, 100, 150);
				
				// 아바타를 선택해 주세요. 라는 문구에 대한 라벨 생성.
				choose_l = new JLabel(choose_img);
				// 위치를 지정
				choose_l.setBounds(20, 90, 400, 250);
				
				// 프레임에 삽입해주는 부분
				add(id_l);
				add(pw_l);
				add(id_tx);
				add(pw_pw);
				add(sign_bt);
				add(cancel_bt);
				add(character1);
				add(character2);
				add(character3);
				add(char1_l);
				add(char2_l);
				add(char3_l);
				add(choose_l);
				add(menubar_l);
				add(back_l);
				
				// 화면을 보이게한다.
				setVisible(true);
	}
//
//	public void dispose() {
//		this.dispose();
//	}
//	
	public ImageIcon getSignupBasic_img() {
		return signupBasic_img;
	}



	public ImageIcon getSignupEntered_img() {
		return signupEntered_img;
	}



	public ImageIcon getCancelBasic_img() {
		return cancelBasic_img;
	}



	public ImageIcon getCancelEntered_img() {
		return cancelEntered_img;
	}



	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}



	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}



	public JLabel getMenubar_l() {
		return menubar_l;
	}



	public JButton getSign_bt() {
		return sign_bt;
	}



	public JButton getCancel_bt() {
		return cancel_bt;
	}



	public JRadioButton getCharacter1() {
		return character1;
	}



	public JRadioButton getCharacter2() {
		return character2;
	}



	public JRadioButton getCharacter3() {
		return character3;
	}



	public int getMouseX() {
		return mouseX;
	}



	public int getMouseY() {
		return mouseY;
	}



	
	
	public String getID() {
		return id_tx.getText();
	}
	
	public String getPW() {
		return pw_pw.getText();
	}
	
	public void resetText() {
		id_tx.setText("");
		pw_pw.setText("");
	}
	
}
