package Control;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import Model.Ingame;
import Model.Music;
import Starter.Application;
import View.IngameView;

public class IngameControler {
	private Application app;
	private IngameView iv;
	//private ButtonListener listen;
	private Mouse mouse;
	private ToolActionListener tool;
	private PaintDraw paint;
	
	private Ingame []i;		// 게임 방 객체(Ingame 모델)
	
	private class Mouse extends MouseAdapter{
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource() == iv.getMenubar_l()) {
				// 이벤트가 발생했을때의 그 좌표를 얻어옴.
				iv.setMouseX(e.getX());
				iv.setMouseY(e.getY());
			} else if(e.getSource() == iv.getPaintdraw_p()) {
				iv.setStartX(e.getX());
				iv.setStartY(e.getY());
			} else if(e.getSource() == iv.getMenuexit_bt()) {
				// 종료버튼을 클릭했을시 나오는 효과음 선언.
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				// 효과음 재생.
				buttonPressedMusic.start();

				// 예외 처리.
				try {
					Thread.sleep(1000);	// 버튼을 누르자마자 꺼지면 효과음이 들리지 않기 때문에 1초 정도의 기간을 줌.
				}catch(InterruptedException ex)
				{
					// 에러가 발생한 메소드의 호출 기록을 출력함.
					ex.printStackTrace();
				}
				// 프로그램을 종료시킴.
				app.changePanel();
				app.changePanel();
				app.setTitle("대기실");
				//System.exit(0);
			}
		}
		
		// 드래그가 발생했을때의 이벤트처리.
		@Override
		public void mouseDragged(MouseEvent e)
		{
			// 현재 스크린의 좌표를 얻어옴.
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();

			// 프레임의 위치를 드래그한곳으로 이동시킴.
			app.setLocation(x - iv.getMouseX(), y - iv.getMouseY());
		}
		
		// 종료버튼위에 마우스를 올렸을때의 사건을 구현.
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// 종료버튼의 아이콘을 exitentered_img으로 설정.
				iv.getMenuexit_bt().setIcon(iv.getExitentered_img());
				// 종료버튼위에 커서가 있을시 핸드커서로 변경.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
				// 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				// 효과음 재생.
				buttonEnteredMusic.start();
			}
		}
		
		/// 종료버튼에서 마우스를 내렸을때의 사건을 구현.
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == iv.getMenuexit_bt()) {
				// 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
				iv.getMenuexit_bt().setIcon(iv.getExitbasic_img());
				// 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
				iv.getMenuexit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
	
			// 종료 버튼 선택시
			if(event.getSource() == iv.getExit_bt()) {
				JOptionPane.showMessageDialog(null, "대기실로 나갑니다");
				app.changePanel();
				app.changePanel();
				app.setTitle("대기실");
			}
		}
	}
	
	// 그림을 그릴 패널에 대한 마우스 모션리스너 생성
		public class PaintDraw implements MouseMotionListener
		{

			// 마우스 드래그 메소드
			@Override
			public void mouseDragged(MouseEvent e) {
				// thickness에 setlinethickness_tx에 저장된 문자열값을 정수화시켜 저장함.
				iv.setThickness(Integer.parseInt(iv.getSetlinethickness_tx().getText()));

				// 끝점 x좌표를 구해 저장.
				iv.setEndX(e.getX());

				// 끝점 y좌표를 구해 저장.
				iv.setEndY(e.getY());

				iv.getGraphics2().setStroke(new BasicStroke(iv.getThickness(), BasicStroke.CAP_ROUND, 0));
				iv.getGraphics2().drawLine(iv.getStartX()+82, iv.getStartY()+100, iv.getEndX()+82, iv.getEndY()+100);

				iv.setStartX(iv.getEndX());
				iv.setStartY(iv.getEndY());

			}

			// 사용하지 않지만 implements 했기에 남겨둬야함.
			@Override
			public void mouseMoved(MouseEvent e) 
			{

			}
		}

		// 연필, 지우개, clear, 색변경에 대한 액션리스너
		public class ToolActionListener implements ActionListener {
			
			// 오버라이딩된 actionPerformed메소드 실행
			public void actionPerformed(ActionEvent e)	{
				// 연필버튼이 눌렸을떄 밑 if문장 블록범위내 문장 실행
				if(e.getSource() == iv.getPencil_bt()) { 
					if(iv.isTf() == false) {
						 // 그려지는 색상을 검은색 지정
						iv.getGraphics2().setColor(Color.BLACK);
					} else {
						// 그려지는 색상을 selectedColor변수의 값으로 지정
						iv.getGraphics2().setColor(iv.getSelectedColor());
					}
				// 지우개버튼이 눌렸을떄 밑 if문장 블록범위내 문장 실행
				} else if(e.getSource() == iv.getEraser_bt())	{
					// 그려지는 색상을 흰색으로 해줬기 때문에 흰색으로 펜이 그려져 지워지는 것처럼 보이게 한다.
					iv.getGraphics2().setColor(Color.WHITE);
				}
				else if(e.getSource() == iv.getColorselect_bt()) {
					// 색이 변경됐음을 알려줌.
					iv.setTf(true);
					// JColorChooser 생성
					JColorChooser chooser = new JColorChooser();
					// 선택된 컬러값을 selectedColor에 저장
					iv.setSelectedColor(chooser.showDialog(null, "Color", Color.orange));
					// 선의 색을 지정.
					iv.getGraphics2().setColor(iv.getSelectedColor());
				}
				else
				{
					// 패널을 초기화하여 다시 그려준다.
					iv.repaint();
				}
			}
		}
	
	// 생성자 메소드
	public IngameControler(Application app, IngameView iv) {
		this.app = app;
		this.iv = iv;
		// 게임 방 객체를 4개 생성해준다
		i = new Ingame[4];
		i[0] = new Ingame("1번방");
		i[1] = new Ingame("2번방");
		i[2] = new Ingame("3번방");
		i[3] = new Ingame("4번방");
		
	}
	
	public void buttonHandler() {
		ButtonListener listen = new ButtonListener();
		mouse = new Mouse();
		tool = new ToolActionListener();
		paint = new PaintDraw();
		
		//iv.getExit_bt().addActionListener(listen);
		iv.getMenubar_l().addMouseListener(mouse);
		iv.getMenubar_l().addMouseMotionListener(mouse);
		iv.getMenuexit_bt().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseListener(mouse);
		iv.getPaintdraw_p().addMouseMotionListener(paint);
		
		iv.getPencil_bt().addActionListener(tool);
		iv.getEraser_bt().addActionListener(tool);
		iv.getEraseall_bt().addActionListener(tool);
		iv.getColorselect_bt().addActionListener(tool);
	}
	
	// 게임방 객체의 비밀번호를 가져온다
	public String getPW(int num) {
		return i[num].getPw();
	}
	
	// 게임방 객체의 제목을 가져온다
	public String getTitle(int num) {
		return i[num].getTitle();
	}
	
	// 게임방 객체의 제목을 지정한다
	public void setTitle(int num, String title) {
		i[num].setTitle(title);
	}
	
	// 게임방 객체의 제목을 지정한다
	public void setPW(int num, String pw) {
		i[num].setPw(pw);
	}
	
//	// 알 필요 없음
//	public void setInfo(int num) {
//		iv.setChat(i[num].getInfo());
//	}
	
}
