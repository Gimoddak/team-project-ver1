package Model;
// 대기실 모델
public class waiting {
	private int room_num;		// 생성되있는 방 갯수
	private int select;			// 현재 선택한 방 번호
	
	// 생성자 메소드
	public waiting() {
		room_num = 0;
		select = 0;
	}

	public int getRoom_num() {
		return room_num;
	}

	// 생성된 방 갯수를 1 증가시키는 메소드
	// 방만들기 창에서 방을 만들 때 이 메소드를 호출한다
	public void addRoom_Num() {
		room_num += 1;
	}

	public int getSelect() {
		return select;
	}
	
	// 선택한 방의 버튼이 몇번째 방인지 그 숫자를 저장하는 메소드
	// 대기실에서 방에 입장할 때 이 메소드를 호출한다
	public void setSelect(int select) {
		this.select = select;
	}
	
	
}
