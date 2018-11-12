package Control;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import Model.Music;
import Starter.Application;
import Thread.LoginRecieveThread;
import Thread.LoginSendThread;
import Thread.RegisterRecieveThread;
import Thread.RegisterSendThread;
import View.LoginView;
import View.RegisterView;
import View.WaitingView;

public class LoginControler {
   private Application app;
   private LoginView view;
   private ButtonListener listen;
   private RegisterView rv;
   private RegisterControler rc;
   private listen mouse;
   private WaitingView wv;
   private WaitingControler wc;
   private Socket server;
   private LoginRecieveThread lrt;
   private LoginSendThread lst;
   private RegisterRecieveThread rrt;
   private RegisterSendThread rst;
   private class listen extends MouseAdapter{
      // 종료버튼위에 마우스를 올렸을때의 사건을 구현.
      public void mouseEntered(MouseEvent e){
         if(e.getSource() == view.getExit_bt()) {
            // 종료버튼의 아이콘을 exitentered_img으로 설정.
            view.getExit_bt().setIcon(view.getExitentered_img());
            // 종료버튼위에 커서가 있을시 핸드커서로 변경.
            view.getExit_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
            // 종료버튼위에 커서를 올렸을 시 나오는 효과음 선언.
            Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
            // 효과음 재생.
            buttonEnteredMusic.start();
         } else if(e.getSource() == view.getLogin_bt()) {
            // 로그인버튼의 아이콘을 loginEntered_img으로 설정.
            view.getLogin_bt().setIcon(view.getLoginEntered_img());
            // 로그인버튼위에 커서가 있을시 핸드커서로 변경.
            view.getLogin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
            // 로그인버튼위에 커서를 올렸을 시 나오는 효과음 선언.
            Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
            // 효과음 재생.
            buttonEnteredMusic.start();
         } else if(e.getSource() == view.getJoin_bt()){
            // 가입버튼의 아이콘을 joinEntered_img으로 설정.
            view.getJoin_bt().setIcon(view.getJoinEntered_img());
            // 가입버튼위에 커서가 있을시 핸드커서로 변경.
            view.getJoin_bt().setCursor(new Cursor(Cursor.HAND_CURSOR));
            // 가입버튼위에 커서를 올렸을 시 나오는 효과음 선언.
            Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
            // 효과음 재생.
            buttonEnteredMusic.start();
         }
      }

      /// 종료버튼에서 마우스를 내렸을때의 사건을 구현.

      public void mouseExited(MouseEvent e){
         if(e.getSource() == view.getExit_bt()) {
            // 종료버튼에서 마우스를 내렸을때의 아이콘을 exitbasic_img로 설정.
            view.getExit_bt().setIcon(view.getExitbasic_img());
            // 종료버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
            view.getExit_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         } else if(e.getSource() == view.getLogin_bt()) {
            // 로그인버튼에서 마우스를 내렸을때의 아이콘을 login_img로 설정.
            view.getLogin_bt().setIcon(view.getLogin_img());
            // 로그인버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
            view.getLogin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         } else if(e.getSource() == view.getJoin_bt()){
            // 가입버튼에서 마우스를 내렸을때의 아이콘을 join_img로 설정.
            view.getJoin_bt().setIcon(view.getJoin_img());
            // 가입버튼에서 마우스를 내렸을때 커서를 기본커서로 설정.
            view.getJoin_bt().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
      }

      // 종료버튼을 클릭했을때의 사건을 구현.
      public void mousePressed(MouseEvent e){
         if(e.getSource() == view.getMenubar_l()) {
            // 이벤트가 발생했을때의 그 좌표를 얻어옴.
            view.setMouseX(e.getX());
            view.setMouseY(e.getY());
         } else if(e.getSource() == view.getExit_bt()){
            // 종료버튼을 클릭했을시 나오는 효과음 선언.
            Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
            // 효과음 재생.
            buttonPressedMusic.start();

            // 예외 처리.
            try {
               Thread.sleep(1000);   // 버튼을 누르자마자 꺼지면 효과음이 들리지 않기 때문에 1초 정도의 기간을 줌.
            }catch(InterruptedException ex)
            {
               // 에러가 발생한 메소드의 호출 기록을 출력함.
               ex.printStackTrace();
            }
            // 프로그램을 종료시킴.
            System.exit(0);
         } else {
            // 종료버튼을 클릭했을시 나오는 효과음 선언.
            Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
            // 효과음 재생.
            buttonPressedMusic.start();

            // 예외 처리.
            try {
               Thread.sleep(1000);   // 버튼을 누르자마자 꺼지면 효과음이 들리지 않기 때문에 1초 정도의 기간을 줌.
            }catch(InterruptedException ex)
            {
               // 에러가 발생한 메소드의 호출 기록을 출력함.
               ex.printStackTrace();
            }
         }
      }

      public void mouseDragged(MouseEvent e){
         // 현재 스크린의 좌표를 얻어옴.
         int x = e.getXOnScreen();
         int y = e.getYOnScreen();

         // 프레임의 위치를 드래그한곳으로 이동시킴.
         app.setLocation(x - view.getMouseX(), y - view.getMouseY());
      }




   }


   private class ButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event){
         // 로그인 버튼 선택시
         if(event.getSource() == view.getLogin_bt()) {
            lst = new LoginSendThread(server, "Login", view.getID(), view.getPW());
            lrt = new LoginRecieveThread(server,app, view, wc);
            lst.start();   
            lrt.start();
            // 회원가입 버튼 선택   
         } else {
            // 회원가입 창 생성
            rv = new RegisterView();
            // 회원가입 Controler 연결
            rc = new RegisterControler(rv, server);
            // 회원가입 창에서도 버튼은 있으니까 걔네 listener들을 연결시켜주는 메소드
            rc.buttonHandler();
         }

      }

   }

   // 생성자 메소드
   public LoginControler(Application app, LoginView v, Socket s, WaitingControler wc) throws IOException {
      this.wc = wc;
      this.server = s;
      this.app = app;
      view = v;

      eventHandler();
   }


   public void eventHandler() {
      listen = new ButtonListener();
      mouse = new listen();
      view.getLogin_bt().addActionListener(listen);
      view.getJoin_bt().addActionListener(listen);
      view.getExit_bt().addMouseListener(mouse);
      view.getLogin_bt().addMouseListener(mouse);
      view.getJoin_bt().addMouseListener(mouse);
      view.getMenubar_l().addMouseListener(mouse);
      view.getMenubar_l().addMouseMotionListener(mouse);

   }
}