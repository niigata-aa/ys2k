package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.UserBean;

public class UserDAO {
	/*
	 * ログインを行う
	 */
	
	public String loginCheck(String userId,String password)throws ClassNotFoundException,SQLException{
		
		String sql="SELECT nickname FROM m_user WHERE user_id=? AND password=?";
		
		try(Connection con=ConnectionManager.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql)){
			
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			
			ResultSet res = pstmt.executeQuery();
			
			if(res.next()) {
				return res.getString("nickname");
			}
			
			
		}
		return null;
	}
	
	 public boolean insert(String userId, String nickname, String password) throws SQLException, ClassNotFoundException {

	        String sql = "INSERT INTO m_user(user_id, nickname, password) "
	                   + "VALUES (?, ?, ?)";

	        try (Connection con = ConnectionManager.getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {

	            pstmt.setString(1, userId);
	            pstmt.setString(2, nickname);
	            pstmt.setString(3, password);

				int row = pstmt.executeUpdate();
				
				if(row != 0) {//処理に成功した時
					return true;
				}
	            
	        }
	        return false;
	    }
	
		public List<UserBean> selectNickname(int breweryId) throws SQLException, ClassNotFoundException {
			List<UserBean> userList = new ArrayList<UserBean>();
			
			String sql = "SELECT T1.nickname FROM m_user T1 INNER JOIN sake_user_comment T2 ON T1.user_id = T2.user_id WHERE T2.brewery_id = ?";
			
			// データベースへの接続の取得、PreparedStatementの取得
			try (Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql))  {
				
				// プレースホルダへの値の設定
				pstmt.setInt(1, breweryId);
				
				// SQLステートメントの実行
				ResultSet res = pstmt.executeQuery();
				
				
				// 結果の操作
				while (res.next()) {
					String nickName = res.getString("nickname"); 
					
					UserBean user = new UserBean();
					
					user.setNickname(nickName);
					
					userList.add(user);
					System.out.println(user);
				}
				
			}
			return userList;
		}
		
		public String selectMyNickname(String userId) throws SQLException, ClassNotFoundException {
			String myName = null;
			
			String sql = "SELECT nickname FROM m_user WHERE user_id = ?";
			
			// データベースへの接続の取得、PreparedStatementの取得
			try (Connection con = ConnectionManager.getConnection();
					PreparedStatement pstmt = con.prepareStatement(sql))  {
				
				// プレースホルダへの値の設定
				pstmt.setString(1, userId);
				
				// SQLステートメントの実行
				ResultSet res = pstmt.executeQuery();
				
				
				// 結果の操作
				while (res.next()) {
					myName = res.getString("nickname"); 
					
					//UserBean user = new UserBean();
					
					//user.setNickname(nickName);
					
					//userList.add(user);
					//System.out.println(user);
				}
				
			}
			return myName;
		}
}
