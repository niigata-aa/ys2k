package model.entity;

import java.io.Serializable;
import java.sql.Date;

public class CommentBean implements Serializable{
	private int commentId; // コメント識別ID
	private String content; // コメントの中身
	private Date insertDay; // 入力日
	private String userId; // ユーザID
	private int breweryId; // 対応酒造ID
	
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
	
	public Date getInsertDay() {
		return insertDay;
	}
	
	public void setInsertDay(Date insertDay) {
		this.insertDay = insertDay;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getBreweryId() {
		return breweryId;
	}
	
	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}
	
}
