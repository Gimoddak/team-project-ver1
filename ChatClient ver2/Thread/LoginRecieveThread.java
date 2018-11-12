package Thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import Control.WaitingControler;
import Starter.Application;
import View.LoginView;
import View.WaitingView;

public class LoginRecieveThread extends Thread  {
   private String result, ID, avatar_selected;
   private DataInputStream inputstream;
   private Socket s, test;
   private Application app;
   private WaitingControler wc;
   private WaitingView wv;
   private LoginView view;

   public LoginRecieveThread(Socket s,Application app, LoginView view, WaitingControler wc) {
      this.app = app;
      this.wc = wc;
      this.s = s;
      this.view = view;
      wv = new WaitingView();
      try {
         inputstream = new DataInputStream(s.getInputStream());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }



   public void run() {
      try {
         result = inputstream.readUTF();
         ID = inputstream.readUTF();
         //avatar_selected = inputstream.readUTF();
         if(result.equals("true")) {
            JOptionPane.showMessageDialog(null, "로그인 되었습니다");
            s = new Socket(s.getInetAddress(), s.getPort()+1);
            System.out.println(s.getInetAddress() + "    " + s.getPort() + "    " + ID);
            
            wc.setUserInfo(s, ID);
            
            
            // Application이 card Layout으로 되있다고 했지?
            // Application 프레임 안에 순서대로 로그인, 대기실, 인게임이 들어있는데 changePanel이
            // 바로 다음 순서 패널로 화면 전환시켜주는애임
            
            
            //wv.setCharacter1_img(avatar_selected);
            //wv.repaint();
            
            
            app.changePanel();
            app.setTitle("대기실");
         } else if(result.equals("false_PW")){
            JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다");
            view.resetPW();
         }else {
            JOptionPane.showMessageDialog(null, "계정 정보가 올바르지 않습니다");
            view.resetText();
         }

      }
      catch(IOException e){
         e.printStackTrace();
      }
   }
}



