package model.entity;

import java.io.Serializable;

public class AdminBean implements Serializable{
	/*
	 * 管理者ID
	 */
	private String adminId;
	
	/**
	 * AdminBeanを構築
	 */
	public AdminBean() {
	}
	
	
	public String getAdminId() {
		return adminId;
	}
	
	public void setAdminId(String adminId) {
		this.adminId=adminId;
	}

}
