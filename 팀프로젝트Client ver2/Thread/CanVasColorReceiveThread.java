package Thread;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JColorChooser;

import Control.IngameControler;
import View.IngameView;

public class CanVasColorReceiveThread extends Thread{

	private DataInputStream inputstream;
	private DataOutputStream outputstream;
	private Socket s;	//그림판 소켓변수
	private IngameView iv;
	private IngameControler ic;
	private String ID,color;
	
	public CanVasColorReceiveThread(Socket s,IngameView view,String ID) {
		this.s = s;
		this.iv=view;
		this.ID=ID;
		
		try {
			inputstream = new DataInputStream(s.getInputStream());
			outputstream=new DataOutputStream((s.getOutputStream())); 
			
			outputstream.writeUTF(ID);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void run() {
		try {
			while(inputstream!=null) {
			
				color=inputstream.readUTF();
				Object obj=color;
				
				if(obj == iv.getPencil_bt()) { 
					if(iv.isTf() == false) {
						 // 그려지는 색상을 검은색 지정
						iv.getGraphics2().setColor(Color.BLACK);
						
						
					} else {
						// 그려지는 색상을 selectedColor변수의 값으로 지정
						iv.getGraphics2().setColor(iv.getSelectedColor());
					
						
					}
				// 지우개버튼이 눌렸을떄 밑 if문장 블록범위내 문장 실행
				} else if(obj == iv.getEraser_bt())	{
					// 그려지는 색상을 흰색으로 해줬기 때문에 흰색으로 펜이 그려져 지워지는 것처럼 보이게 한다.
					iv.getGraphics2().setColor(Color.WHITE);
				}
				else if(obj == iv.getColorselect_bt()) {
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
		}catch(IOException e){
			e.printStackTrace();
		}
}
}



