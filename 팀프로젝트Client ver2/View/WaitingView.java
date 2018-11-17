package View;
import Starter.Application;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
public class WaitingView extends JPanel{

	public static JButton r1_bt, r2_bt, r3_bt, r4_bt;
	private JButton mkr_bt, exit_bt, userexit_bt;
	private JTextArea chatarea_ta, rank_ta;
	private JTextField chatfield_tf, id_tf;
	private JLabel back_l, menubar_l, character_l;
	private JPanel user_p, userbottom_p, room_p, roomtop_p, roombot_p, rank_p;
	private JScrollPane scroll;
	
	// 배경화면이미지에 관한 이미지아이콘 생성.
	private ImageIcon back_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\waitingback.jpg");
	// 메뉴바에 관한 이미지아이콘 생성.
	private ImageIcon menu_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\menubar.png");
	// 기본 종료버튼에 관한 이미지아이콘 생성.
	private ImageIcon exitbasic_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonBasic.png");
	// 종료버튼위에 마우스를 올렸을시에 보여줄 이미지에 관한 이미지 아이콘 생성.
	private ImageIcon exitentered_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\exitButtonEntered.png");
	// 캐릭터1에 대한 이미지아이콘 생성.
	private ImageIcon character1_img = new ImageIcon("C:\\Users\\admin\\Desktop\\prj_Client_v.2\\images\\character1.png");

	// 마우스의 x좌표 y좌표를 저장할 변수.
	private int mouseX, mouseY;


	private Application app;
	private DefaultTableModel model;	// 테이블에 들어갈 데이터를 모델링하는 객체
	private JTable table;				// 눈에 보이는 테이블
	private JScrollPane sc;				// 스크롤을 오르내릴 수 있는 판
	
	//생성자 메소드
	public WaitingView() {
		// 테이블의 데이터 내용들
        String []a= {"순위","ID","점수"};
        // 우선은 각 칸의 값을 빈칸으로 초기화
		String [][]b = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		
		
		// 데이터를 넣을 모델링 생성 및 테이블에 모델링 추가
		// 테이블 셀 접근 방지
		model = new DefaultTableModel(b,a) {
			public boolean isCellEditable(int i, int c){ 
				return false; 
			}
		};
		table = new JTable(model);
		// 테이블의 칸별 크기 조정
		table.getColumnModel().getColumn(0).setPreferredWidth(30); 
		table.getColumnModel().getColumn(1).setPreferredWidth(130); 
		table.getColumnModel().getColumn(2).setPreferredWidth(40); 
		// 스크롤판에 테이블 추가
		sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(240,420));
		//sc.setPreferredSize(new Dimension(200,345));
		
		
		// DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();
		// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		// 정렬할 테이블의 ColumnModel을 가져옴
		TableColumnModel tcmSchedule = table.getColumnModel();

		
		// 반복문을 이용하여 테이블을 가운데 정렬로 지정
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
		tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
		}
		
		setLayout(null);
		
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

		
		
		// 메뉴바 라벨을 menu_img를 삽입하며 생성.
		menubar_l = new JLabel(menu_img);
		// 메뉴바의 위치를 설정.
		menubar_l.setBounds(0, 0, 1280, 30);

		
		
		userexit_bt = new JButton("종료하기"); 
		
		
		
		room_p = new JPanel();
		room_p.setLayout(new BorderLayout());
		
		roomtop_p = new JPanel();
		roomtop_p.setLayout(new GridLayout(2, 2, 10, 10));
		
		roombot_p = new JPanel();
		roombot_p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		r1_bt = new JButton("방1");
		r2_bt = new JButton("방2");
		r3_bt = new JButton("방3");
		r4_bt = new JButton("방4");

		roomtop_p.add(r1_bt);
		roomtop_p.add(r2_bt);
		roomtop_p.add(r3_bt);
		roomtop_p.add(r4_bt);
		
		r1_bt.setEnabled(false);
		r2_bt.setEnabled(false);
		r3_bt.setEnabled(false);
		r4_bt.setEnabled(false);
		
		
		mkr_bt = new JButton("방만들기");
		roombot_p.add(mkr_bt);
		
		room_p.add(roomtop_p, BorderLayout.CENTER);
		room_p.add(roombot_p, BorderLayout.SOUTH);
		
		room_p.setBounds(20, 50, 975, 450);
        
		chatarea_ta = new JTextArea(50,10);
		chatarea_ta.setEditable(false);
		
		scroll = new JScrollPane(chatarea_ta, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(20, 510, 975, 155);
		
		rank_p = new JPanel();
		rank_p.setBounds(1010, 50, 250, 450);
		rank_p.add(sc);

		
		chatfield_tf = new JTextField("");
		chatfield_tf.setBounds(20, 665, 975, 48);
		chatfield_tf.setCaretPosition(chatfield_tf.getText().length());
		
		
		
		back_l = new JLabel(back_img);
		back_l.setBounds(0, 0, 1280, 720);
		
		user_p = new JPanel();
		user_p.setLayout(new GridLayout(2, 1));
		
		userbottom_p = new JPanel();
		userbottom_p.setLayout(new GridLayout(2, 1));
		
		id_tf = new JTextField("User ID");
		character_l = new JLabel(character1_img);
		
		userbottom_p.add(id_tf);
		userbottom_p.add(userexit_bt);
		
		user_p.add(character_l);
		user_p.add(userbottom_p);
		
		user_p.setBounds(1010, 510, 250, 210);
	
		Border lineBorder = BorderFactory.createLineBorder(Color.black, 3);
        Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
       
        room_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		chatfield_tf.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		rank_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		user_p.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
		
		add(room_p);
		add(scroll);
		add(chatfield_tf);
		add(rank_p);
		add(user_p);
		add(exit_bt);
		add(menubar_l);
		add(back_l);
		
		setVisible(true);

	}

	
	// 방 버튼 반환
	public static JButton getR(int i) {
		if(i == 0) {
			return r1_bt;
		} else if(i == 1) {
			return r2_bt;
		} else if(i == 2) {
			return r3_bt;
		} else {
			return r4_bt;
		}
	}
	
	
	
	public void setCharacter1_img(String character_num) {
		
		character1_img = new ImageIcon("C:\\Users\\rwlsd\\Desktop\\prj_Client_v.2\\images\\" + character_num);
		character_l = new JLabel(character1_img);
	}


	public JButton getMkr_bt() {
		return mkr_bt;
	}



	public JButton getExit_bt() {
		return exit_bt;
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



	public ImageIcon getExitbasic_img() {
		return exitbasic_img;
	}



	public ImageIcon getExitentered_img() {
		return exitentered_img;
	}



	public JButton getUserexit_bt() {
		return userexit_bt;
	}



	public JLabel getMenubar_l() {
		return menubar_l;
	}



	public JTable getTable() {
		return table;
	}
	
	public JTextField getTextField() {
		return chatfield_tf;
	}
	
	public String getChatText() {
		return chatfield_tf.getText();
	}
	
	public void setInitChatText() {
		chatfield_tf.setText("");
	}
	
	public void setChatText(String s) {
		chatarea_ta.append(s);;
	}
	
	public JTextArea getTextArea()
	{
		return chatarea_ta;
	}



}


