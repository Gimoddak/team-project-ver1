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

			// DBMS ����

			db.connect();

			// DBMS�� "account ���̺��� id�� pw�� �����ؿͶ�"��� ��ɾ �����ϰ� ������� rs�� ����

			db.read("select id,pw from account;");

			// DB���� ������ id�� pw�� �ϳ��� �����ͼ� �Է��� ID, PW�� ������ boolean ������ true�� ����

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

			// DBMS ����

			db.connect();

			// DBMS�� "account ���̺��� id�� pw�� �����ؿͶ�"��� ��ɾ �����ϰ� ������� rs�� ����

			db.read("select id from account;");

			// DB���� ������ id�� pw�� �ϳ��� �����ͼ� �Է��� ID, PW�� ������ boolean ������ true�� ����

			while(db.rs.next()) {

				String sqlRecipeProcess_id = db.rs.getString("id");

				if(ID.equals(sqlRecipeProcess_id)) {

					result = "false";

				}

			}

			// �� �κп��� ID�߰�!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

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