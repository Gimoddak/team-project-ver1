package Model;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;	
	private boolean isLoop; // 노래가 무한반복인지에 대한 설정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	public Music(String name, boolean isLoop)
	{
		try {
			this.isLoop = isLoop;
			/*
			// music이라는 폴더안에 있는 해당이름의 파일을 실행시킴 toURI로 위치를 가져옴.
			file = new File(Application.class.getResource("C:\\Users\\hojja\\git\\tm_project14\\prj_MVC\\prj_MVC\\src\\music\\" + name).toURI());
			fis = new FileInputStream(file);
			//해당 파일을 버퍼에 담아서 읽어올 수 있도록 한다.
			bis = new BufferedInputStream(fis);
			//player에 해당 파일을 담는다.
			player = new Player(bis);
			*/
			String path = "C:\\Users\\admin\\Desktop\\prj_Client_v.2\\music\\" + name;
			player = new Player(new FileInputStream(path));
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 곡을 종료하는 메소드
	public void close()
	{
		isLoop = false;  // false 값을 넣어주면서 run메소드에서 곡 재생을 종료시킴.
		player.close();
		this.interrupt(); //곡을 재생하는 스레드를 종료함.
	}
	
	
	// 스레드를 사용하기위해 꼭 필요한 run메소드
	@Override
	public void run()
	{
		try {
			//isLoop값이 true라면 곡을 무한반복시킴.
			do {
				player.play();
			}while(isLoop);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}