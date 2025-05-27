package model.entity;

import java.io.Serializable;

public class BreweryBean implements Serializable{
	
	/*
	 * 酒ID
	 */
	private String breweryId;
	
	/*
	 * 酒の名前
	 */
	private String breweryName;
	
	/*
	 * 写真のURL
	 */
	private String blmgPath;
	
	/*
	 * 緯度
	 */
	private int lattitude;
	
	/*
	 * 経度
	 */
	private int longtiude;
	
	/*
	 * 住所
	 */
	private String address;
	
	/*
	 * 味わい
	 */
	private Boolean reservation;
	
	/*
	 * 酒蔵の説明
	 */
	private String breweryExplanation;
	
	/*
	 * 地域ID
	 */
	private int areaId;
	
	
	/*
	 * BrewreyBeanを構築
	 */
	public BreweryBean() {
		
	}


	public String getBreweryId() {
		return breweryId;
	}


	public void setBreweryId(String breweryId) {
		this.breweryId = breweryId;
	}


	public String getBreweryName() {
		return breweryName;
	}


	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}


	public String getBlmgPath() {
		return blmgPath;
	}


	public void setBlmgPath(String blmgPath) {
		this.blmgPath = blmgPath;
	}


	public int getLattitude() {
		return lattitude;
	}


	public void setLattitude(int lattitude) {
		this.lattitude = lattitude;
	}


	public int getLongtiude() {
		return longtiude;
	}


	public void setLongtiude(int longtiude) {
		this.longtiude = longtiude;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Boolean getReservation() {
		return reservation;
	}


	public void setReservation(Boolean reservation) {
		this.reservation = reservation;
	}


	public String getBreweryExplanation() {
		return breweryExplanation;
	}


	public void setBreweryExplanation(String breweryExplanation) {
		this.breweryExplanation = breweryExplanation;
	}


	public int getAreaId() {
		return areaId;
	}


	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

}
