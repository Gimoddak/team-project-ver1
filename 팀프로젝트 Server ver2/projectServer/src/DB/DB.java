package DB;
import java.sql.*;
public class DB {
	public Connection connection;	// DB와 연결하는 객체
	public Statement st;			// DBMS에서 실행할 쿼리문을 전달하는 객체
	public ResultSet rs;			// st가 DBMS에서 쿼리문을 실행한 뒤 가져온 결과를 저장하는 객체
	
	// 생성자 메소드
	public DB() {
		connection = null;
		st = null;
		rs = null;
	}
	
	// client와 DB을 연결하는 메소드
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// JDBC를 통해 connection에 (ip주소 = localhost) (포트번호 = 3306) (DataBase명 = db1)
		// (MySQL ID = temp) (MySQL PW = 1234)를 입력하여 연결
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC&useSSL=false" , "temp", "1234");
		st = connection.createStatement();
	}
	
	public void read(String query) throws ClassNotFoundException, SQLException {
		rs = st.executeQuery(query);
	}
	
	public void write(String query) throws ClassNotFoundException, SQLException {
			st.executeUpdate(query);
	}
	
	
}
