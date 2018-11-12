package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Starter.Application;


public class IngameView extends JPanel{
//	private JPanel g_t, g_b, button;
//	private JTextArea paint, chat, user, undecided;
//	private JButton start, exit;
//	private Application app;
//	private String title;
//	private String pw;
	
	private JPanel paint_p, paintgui_p, paintdraw_p, gui_p;	// 그림판 전체패널, 그림판중 ui패널, 그림판중 그림패널
	private JTextArea chat_txa;	 // 채팅판
	private JButton start_bt, exit_bt, pencil_bt, eraser_bt, eraseall_bt, colorselect_bt, menuexit_bt;	// 스타트버튼, 종료버튼, 연필버튼, 지우개버튼, 색변경버튼
	private JLabel background_l, linethickness_l, menubar_l;	// 뒷배경용 라벨, 선굵기를 나타내는 라벨
	private JTextField setlinethickness_tx, chat_tx;		// 선 굵기를 입력시키는 텍스트필드
	private Color selectedColor;		// 선택된 컬러를 나타냄.
	private Graphics graphics;		// 그래픽스 변수
	private Graphics2D graphics2;	// 선굵기를 위해 그래픽스2D 사용
	private int thickness = 10;		// 선굵기를 입력받을 변수
	private int startX;			// 시작 x좌표
	private int startY;			// 시작 y좌표
	private int endX;			// 끝 x좌표
	private int endY;			// 끝 y좌표
	private int mouseX, mouseY; // 메뉴바에사용할 좌표값.
	private boolean tf = false;		// ???????
	
	// 뒷배경라벨에 사용할 이미지아이콘 생성
	private ImageIcon ingameback_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\ingameback.jpg");
	// 메뉴바에 관한 이미지아이콘 생성.
	private ImageIcon menu_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\menubar.png");
	// 기본 종료버튼에 관한 이미지아이콘 생성.
	private ImageIcon exitbasic_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonBasic.png");
	// 종료버튼위에 마우스를 올렸을시에 보여줄 이미지에 관한 이미지 아이콘 생성.
	private ImageIcon exitentered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonEntered.png");
	
	
	//생성자 메소드
	public IngameView(Application app) {
		
		// 절대경로를 사용하기 위한 설정.
				setLayout(null);
				// 넘겨받은 프레임객체 지정.
				
				
				// ingameback_img를 삽입하면서 배경용 라벨생성
				background_l = new JLabel(ingameback_img);
				// 배경라벨의 위치와 크기 지정
				background_l.setBounds(0, 0, 1280, 720);

				// 메뉴바 라벨을 menu_img를 삽입하며 생성.
				menubar_l = new JLabel(menu_img);
				// 메뉴바의 위치를 설정.
				menubar_l.setBounds(0, 0, 1280, 30);

				

				
				// 종료버튼에 exitbasic_img를 삽입하며 생성.
				menuexit_bt = new JButton(exitbasic_img);
				// 종료버튼의 위치를 지정.
				menuexit_bt.setBounds(1245, 0, 30, 30);
				// 종료버튼의 테두리 설정을 해제시킴.
				menuexit_bt.setBorderPainted(false);
				// 종료버튼의 배경을 채우지 않음.
				menuexit_bt.setContentAreaFilled(false);
				// 종료버튼의 포커스 표시 설정.
				menuexit_bt.setFocusPainted(false);
				
				

				// 그림판 패널 생성
				paint_p = new JPanel();
				// 절대경로를 사용하기 위한 설정.
				paint_p.setLayout(null);
				
				// 그림판 중 ui패널 생성
				paintgui_p = new JPanel();
				// 배경을 회색으로 지정.
				paintgui_p.setBackground(Color.gray);
				// 절대경로를 사용하기 위한 설정.
				paintgui_p.setLayout(null);
				
				// font 생성
				Font font = new Font("함초롱돋움", Font.BOLD, 25);
				// 연필버튼을 생성
				pencil_bt = new JButton("연필");
				// 연필버튼의 폰트를 font로 설정
				pencil_bt.setFont(font);
				// 연필버튼의 위치와 크기를 지정.
				pencil_bt.setBounds(10,10,90,45);
				
				// 지우개 버튼을 생성
				eraser_bt = new JButton("지우개");
				// 지우개 버튼의 폰트를 font로 설정.
				eraser_bt.setFont(font);
				// 지우개 버튼의 위치와 크기를 지정.
				eraser_bt.setBounds(105,10,109,45);
				
				eraseall_bt = new JButton("clear");
				eraseall_bt.setFont(font);
				eraseall_bt.setBounds(245, 10, 110, 45);
				
				// 색변경 버튼 생성
				colorselect_bt = new  JButton("색변경");
				// 색변경 버튼의 폰트를 font로 설정.
				colorselect_bt.setFont(font);
				// 색변경 버튼의 위치와 크기를 지정.
				colorselect_bt.setBounds(370,10,110,45);
				
				// "굵기변경" 을 나타낼 라벨생성
				linethickness_l = new JLabel("굵기변경");
				// 굵기변경 라벨의 폰트를 font로 설정.
				linethickness_l.setFont(font);
				// 굵기변경 라벨의 위치와 크기 설정.
				linethickness_l.setBounds(680,10,100,45);
				
				// 굵기를 입력할 텍스트필드 생성 기본값 10을 줌
				setlinethickness_tx = new JTextField("10", 5);
				// 폰트를  font로 설정
				setlinethickness_tx.setFont(font);
				// 가운데 정렬로 설정.
				setlinethickness_tx.setHorizontalAlignment(JTextField.CENTER);
				// 위치와 크기를 지정.
				setlinethickness_tx.setBounds(780, 15, 50, 35);
				
				// ui패널에 삽입하는 부분.
				paintgui_p.add(pencil_bt);
				paintgui_p.add(eraser_bt);
				paintgui_p.add(eraseall_bt);
				paintgui_p.add(colorselect_bt);
				paintgui_p.add(linethickness_l);
				paintgui_p.add(setlinethickness_tx);
				
				// 그림을 그릴 패널생성.
				paintdraw_p = new JPanel();
				// 배경을 하얀색으로 설정.
				paintdraw_p.setBackground(Color.WHITE);
				
				// ui패널의 위치와 크기 지정.
				paintgui_p.setBounds(0,0,900,60);
				// 그림패널의 위치와 크기 지정.
				paintdraw_p.setBounds(0, 50, 900, 800);
				// 그림판 패널에 ui와 그림패널을 삽입
				paint_p.add(paintgui_p);
				paint_p.add(paintdraw_p);
				// 그림판 패널의 위치와 크기를 지정.
				paint_p.setBounds(80, 50, 900, 520);
				
				//selectedColor = Color.BLACK;
				
				// 그래픽변수 초기화
				graphics = app.getGraphics();
				// graphics2에 graphic변수를 Graphics2D화 시켜 저장함. -> 선굵기변경 기능을 사용하기 위함
				graphics2 = (Graphics2D)graphics;
				graphics2.setColor(selectedColor);
				
				
				

				chat_txa = new JTextArea(20, 5);
				chat_txa.setBackground(Color.LIGHT_GRAY);
				chat_txa.setBounds(80, 570, 900, 100);
				
				chat_tx = new JTextField(20);
				chat_tx.setBounds(80,665,900,30);		
				
				gui_p = new JPanel();
				gui_p.setLayout(null);
				gui_p.setBounds(980, 50, 250, 644);
				
				
				add(paint_p);
				add(gui_p);
				add(chat_txa);
				add(chat_tx);
				add(menuexit_bt);
				add(menubar_l);
				add(background_l);
				
				setVisible(true);
//		setLayout(new BorderLayout());
//		title = "";
//		pw = "";
//		
//		g_t = new JPanel();
//		g_b = new JPanel();
//		button = new JPanel();
//		
//		g_t.setLayout(new BorderLayout());
//		g_b.setLayout(new BorderLayout());
//		button.setLayout(new GridLayout(2,1));
//		
//		paint = new JTextArea("그림판",18,50);
//		chat = new JTextArea("채팅판",10,70);
//		user = new JTextArea("사용자 UI",18,15);
//		undecided = new JTextArea("미정",18,15);
//		
//		start = new JButton("시작");
//		exit = new JButton("종료");
//		
//		button.add(start);
//		button.add(exit);
//		
//		g_t.add(paint, BorderLayout.CENTER);
//		g_t.add(user, BorderLayout.EAST);
//		g_t.add(undecided, BorderLayout.WEST);
//		g_b.add(chat, BorderLayout.CENTER);
//		g_b.add(button, BorderLayout.EAST);
//		add(g_t, BorderLayout.CENTER);
//		add(g_b, BorderLayout.SOUTH);
//		
//		Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
//        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
//        chat.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        paint.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        user.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
//        undecided.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		
	}

	
	
	public int getStartX() {
		return startX;
	}



	public int getStartY() {
		return startY;
	}



	public JTextField getSetlinethickness_tx() {
		return setlinethickness_tx;
	}



	public Color getSelectedColor() {
		return selectedColor;
	}



	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}



	public int getEndX() {
		return endX;
	}



	public void setEndX(int endX) {
		this.endX = endX;
	}



	public int getEndY() {
		return endY;
	}



	public void setEndY(int endY) {
		this.endY = endY;
	}



	public boolean isTf() {
		return tf;
	}



	public void setTf(boolean tf) {
		this.tf = tf;
	}



	public Graphics2D getGraphics2() {
		return graphics2;
	}



	public void setThickness(int thickness) {
		this.thickness = thickness;
	}



	public int getThickness() {
		return thickness;
	}



	public int getMouseX() {
		return mouseX;
	}



	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}



	public int getMouseY() {
		return mouseY;
	}



	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}



	public JPanel getPaintdraw_p() {
		return paintdraw_p;
	}



	public JButton getPencil_bt() {
		return pencil_bt;
	}



	public JButton getEraser_bt() {
		return eraser_bt;
	}



	public JButton getEraseall_bt() {
		return eraseall_bt;
	}



	public JButton getColorselect_bt() {
		return colorselect_bt;
	}



	public JButton getMenuexit_bt() {
		return menuexit_bt;
	}



	public JLabel getMenubar_l() {
		return menubar_l;
	}



	public ImageIcon getExitbasic_img() {
		return exitbasic_img;
	}



	public ImageIcon getExitentered_img() {
		return exitentered_img;
	}



	public void setStartX(int startX) {
		this.startX = startX;
	}



	public void setStartY(int startY) {
		this.startY = startY;
	}



	public JButton getExit_bt() {
		return exit_bt;
	}


//
//	public JButton getStart() {
//		return start;
//	}
//
//	public JButton getExit() {
//		return exit;
//	}
//	
//	public void setChat(String str) {
//		chat.setText(str);
//	}
}
