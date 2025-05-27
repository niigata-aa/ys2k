package model.entity;

import java.io.Serializable;

public class VoteBean implements Serializable{
	private int voteId; // 評価ID
	private String userId; // ユーザID
	private int sakeId; // 酒ID
	
	public VoteBean() {
		
	}
	
	public int getVoteId() {
		return voteId;
	}
	
	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getSakeId() {
		return sakeId;
	}
	
	public void setSakeId(int sakeId) {
		this.sakeId = sakeId;
	}
	
	
}
