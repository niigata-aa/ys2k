package model.entity;

import java.io.Serializable;

public class CommentBean implements Serializable{
	private int commentId; // コメント識別ID
	private String content; // コメントの中身
	private String userId; // ユーザID
	
	public CommentBean() {
		
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
