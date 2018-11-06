package Thread;



import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

import java.net.Socket;

import java.sql.SQLException;

import DB.DB;



public class LoginThread extends Thread {

	private String ID, PW, result, msg;

	private DataOutputStream outputstream;

	private DataInputStream inputstream;

	private DB db;

	private Socket s;



	public LoginThread(Socket s) {

		try {

			this.s = s;

			db = new DB();

			inputstream = new DataInputStream(s.getInputStream());

			outputstream = new DataOutputStream(s.getOutputStream());       

		}

		catch(IOException e) {

			e.printStackTrace();

		}

	}



	@Override

	public void run() {

		try{

			while(true) {

				msg = inputstream.readUTF();

				if(msg.equals("Login")) {

					ID = inputstream.readUTF();

					PW = inputstream.readUTF();

					outputstream.writeUTF(getLoginEquals());

					outputstream.writeUTF(ID);

					interrupt();

				}else {

					ID = inputstream.readUTF();

					PW = inputstream.readUTF();

					outputstream.writeUTF(getRegisterEquals());

				}

			}

		}

		catch(IOException e) {

			e.printStackTrace();



		}

	}



	public String getLoginEquals() {

		result = "false";

		try {

			// DBMS 연결

			db.connect();

			// DBMS에 "account 테이블에서 id랑 pw를 추출해와라"라는 명령어를 실행하고 결과값을 rs에 저장

			db.read("select id,pw from account;");

			// DB에서 가져온 id와 pw를 하나씩 가져와서 입력한 ID, PW와 같으면 boolean 변수를 true로 설정

			while(db.rs.next()) {

				String sqlRecipeProcess_id = db.rs.getString("id");

				String sqlRecipeProcess_pw = db.rs.getString("pw");

				if(ID.equals(sqlRecipeProcess_id)) {

					if(PW.equals(sqlRecipeProcess_pw)) {

						result = "true";

					}

				}

			}

			db.connection.close();

			db.rs.close();

			db.st.close();

		}catch (SQLException se1) {

			se1.printStackTrace();

		}catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			try {

				if (db.st != null)

					db.st.close();

			} catch (SQLException se2) {

			}

			try {

				if (db.connection != null)

					db.connection.close();

			} catch (SQLException se) {

				se.printStackTrace();

			}

		} 

		return result;

	}

	

	

	

	public String getRegisterEquals() {

		result = "true";

		try {

			// DBMS 연결

			db.connect();

			// DBMS에 "account 테이블에서 id랑 pw를 추출해와라"라는 명령어를 실행하고 결과값을 rs에 저장

			db.read("select id from account;");

			// DB에서 가져온 id와 pw를 하나씩 가져와서 입력한 ID, PW와 같으면 boolean 변수를 true로 설정

			while(db.rs.next()) {

				String sqlRecipeProcess_id = db.rs.getString("id");

				if(ID.equals(sqlRecipeProcess_id)) {

					result = "false";

				}

			}

			// 이 부분에서 ID추가!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

			if(result.equals("true")) {

				db.st.executeUpdate("insert into account values('" + ID + "','" + PW + "',0);");

			}

			db.connection.close();

			db.rs.close();

			db.st.close();

		}catch (SQLException se1) {

			se1.printStackTrace();

		}catch (Exception ex) {

			ex.printStackTrace();

		} finally {

			try {

				if (db.st != null)

					db.st.close();

			} catch (SQLException se2) {

			}

			try {

				if (db.connection != null)

					db.connection.close();

			} catch (SQLException se) {

				se.printStackTrace();

			}

		} 

		return result;

	}





}