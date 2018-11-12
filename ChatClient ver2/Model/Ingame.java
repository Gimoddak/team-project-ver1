package Model;
// Ingame 모델
public class Ingame {
	private String title;	// 방 제목
	private String pw;		// 방 비밀번호
	private String info;	// 방 정보(임시로 사용할 내용. 추후에 삭제 또는 변경 예정)
	
	// 생성자 메소드
	public Ingame(String i) {
		title = "";
		pw = "";
		info = i;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
