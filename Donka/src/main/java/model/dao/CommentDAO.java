package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.CommentBean;

public class CommentDAO {
	
	
	
	public int insert(CommentBean comment) throws SQLException, ClassNotFoundException {
	    int count = 0;
	    
	    String sql = "INSERT INTO sake_user_comment ( content, user_id, brewery_id) VALUES ( ?, ?, ?)";
	    
	    try (Connection con = ConnectionManager.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, comment.getContent());
	        pstmt.setString(2, comment.getUserId());
	        pstmt.setInt(3, comment.getBreweryId());
	        
	        count = pstmt.executeUpdate();
	    }
	    return count;
	}

	
	
	public int delete(CommentBean comment) throws SQLException, ClassNotFoundException {
		int count = 0;
		// SQL文で酒蔵IDもないとコメントが削除できない可能性がある
		String sql = "DELETE FROM sake_user_comment WHERE comment_id = ? AND brewery_id=?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			
			// DTOからのデータの取り出し
			int commentId = comment.getCommentId();
			int breweryId = comment.getBreweryId();
			
			// プレースホルダへの値の設定
			pstmt.setInt(1, commentId);
			pstmt.setInt(2, breweryId);
			
			// SQL ステートメントの実行
			count = pstmt.executeUpdate();
		}
		return count;
	}
	
	

	
	public List<CommentBean> selectComment(int brewery_id) throws SQLException, ClassNotFoundException {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		String sql = "SELECT user_id, content, comment_id FROM sake_user_comment WHERE brewery_id = ?";
		
		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql))  {
			
			// プレースホルダへの値の設定
			pstmt.setInt(1, brewery_id);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				String userId = res.getString("user_id"); 
				String content = res.getString("content"); 
				int commentId = res.getInt("comment_id"); 
				
				CommentBean comment = new CommentBean();
				
				comment.setUserId(userId);
				comment.setContent(content);
				comment.setCommentId(commentId);
				
				commentList.add(comment);
			}
			return commentList;
		}
	}
	

	public List<CommentBean> selectConfirmComment(int comment_id, int brewery_id) throws SQLException, ClassNotFoundException {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		String sql = "SELECT user_id, content, comment_id FROM sake_user_comment WHERE comment_id = ? AND brewery_id = ?";
		
		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql))  {
			
			// プレースホルダへの値の設定
			pstmt.setInt(1, comment_id);
			pstmt.setInt(2, brewery_id);

			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				String content = res.getString("content"); 
				
				CommentBean comment = new CommentBean();

				comment.setContent(content);

				
				commentList.add(comment);
			}
			return commentList;
		}
	}
	
	public List<CommentBean> selectBrewery() throws SQLException, ClassNotFoundException {
		List<CommentBean> commentList = new ArrayList<CommentBean>();
		
		String sql = "SELECT brewery_id FROM sake_user_comment";
		
		// データベースへの接続の取得、PreparedStatementの取得
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql))  {
			
			// SQLステートメントの実行
			ResultSet res = pstmt.executeQuery();
			
			// 結果の操作
			while (res.next()) {
				int breweryId = res.getInt("brewery_id"); 
				
				CommentBean comment = new CommentBean();
				
				comment.setBreweryId(breweryId);
				
				commentList.add(comment);
			}
			return commentList;
		}
	}

}
