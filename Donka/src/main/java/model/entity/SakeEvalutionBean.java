package model.entity;

import java.io.Serializable;

public class SakeEvalutionBean implements Serializable {

	/*
	 * 酒ID
	 */
	private int sakeId;
	
	/*
	 * 合計評価数
	 */
	private int totalEvalution;
	
	public SakeEvalutionBean() {
		
	}
	
	public int getSakeId() {
		return sakeId;
	}
	public void setSakeId(int sakeId) {
		this.sakeId = sakeId;
	}
	public int getTotalEvalution() {
		return totalEvalution;
	}
	public void setTotalEvalution(int totalEvalution) {
		this.totalEvalution = totalEvalution;
	}
	
}
