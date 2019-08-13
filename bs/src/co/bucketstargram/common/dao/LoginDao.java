package co.bucketstargram.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LoginDao {
	private DataSource ds = null;
	private Context context = null;
	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public LoginDao() {
		// TODO Auto-generated constructor stub
		System.out.println("LoginDao.java | LoginDao()");
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
//			Context envCtx = (Context) context.lookup("java:comp/env");
//		    ds = (DataSource) envCtx.lookup("/jdbc/Oracle11g2");

			conn = ds.getConnection();
			System.out.println("LoginDao.java | getConnection");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("LoginDao.java | Exception");
			e.printStackTrace();
		}
	}
	
	public boolean select(String formID, String formPW) {
		// TODO Auto-generated method stub
		boolean loginSuccess = false;
		//String sql = "SELECT member_id, member_pw FROM member_info_tb WHERE member_id = ?";
		String sql = "SELECT * FROM member_info_tb";
		String dbID = null;
		String dbPW = null;
		try {
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, formID);
			rs = psmt.executeQuery();
			
			System.out.println("LoginDao.java | formID = " + formID);
			System.out.println("LoginDao.java | formPW = " + formPW);
			//System.out.println("LoginDao.java | rs.next() = " + rs.next());

			if (rs.next()) {
				System.out.println("member_id = " + rs.getString("member_id"));
				System.out.println("member_pw = " + rs.getString("member_pw"));
				System.out.println("LoginDao.java | rs.next() = " + rs.next());
				dbID = rs.getString("member_id");
				dbPW = rs.getString("member_pw");
				System.out.println("LoginDao.java | dbID = " + dbID);
				System.out.println("LoginDao.java | dbPW = " + dbPW);
			}

			if ((dbID != null && dbID.equals(formID)) && (dbPW != null && dbPW.equals(formPW))) {
				loginSuccess = true;
				//update(formID, "login");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		//System.out.println("DAO | loginSuccess = " + loginSuccess);

		return loginSuccess;
	}
	
	private void close() {
		// TODO Auto-generated method stub
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}	
	
//	public boolean select(String formID, String formPW) {
//		boolean loginSuccess = false;
//		// String sql = "SELECT member_id, member_pw FROM member_info_tb WHERE member_id
//		// = ?";
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String sql = "SELECT * FROM member_info_tb";
//		String dbID = null;
//		String dbPW = null;
//
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(url, "lee", "1234");
//			psmt = conn.prepareStatement(sql);
//			// psmt.setString(1, formID);
//			rs = psmt.executeQuery();
//			
//			System.out.println("LoginDao.java | formID = " + formID);
//			System.out.println("LoginDao.java | formPW = " + formPW);
//			// System.out.println("LoginDao.java | rs.next() = " + rs.next());
//
//			if (rs.next()) {
//				//System.out.println("member_id = " + rs.getString("member_id"));
//				//System.out.println("member_pw = " + rs.getString("member_pw"));
//				//System.out.println("LoginDao.java | rs.next() = " + rs.next());
//				dbID = rs.getString("member_id");
//				dbPW = rs.getString("member_pw");
//				System.out.println("LoginDao.java | dbID = " + dbID);
//				System.out.println("LoginDao.java | dbPW = " + dbPW);
//			}
//
//			if ((dbID != null && dbID.equals(formID)) && (dbPW != null && dbPW.equals(formPW))) {
//				loginSuccess = true;
//				// update(formID, "login");
//			}
//		} catch (SQLException | ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		// System.out.println("DAO | loginSuccess = " + loginSuccess);
//
//		return loginSuccess;
//	}
//}
