package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
	
	/*
	 * ログインを行う
	 */
	
	public boolean loginCheck(String adminId,String password)throws ClassNotFoundException,SQLException{
		
		String sql="SELECT * FROM m_admin WHERE admin_id=? AND password=?";
		
		try(Connection con=ConnectionManager.getConnection();
				PreparedStatement pstmt=con.prepareStatement(sql)){
			
			pstmt.setString(1, adminId);
			pstmt.setString(2, password);
			
			ResultSet res=pstmt.executeQuery();
			if(res.next()) {
				return true;
			}
			
			
		}
		return false;
	}

} 
