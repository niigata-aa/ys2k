package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
