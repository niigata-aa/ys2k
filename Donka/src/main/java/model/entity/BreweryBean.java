package model.entity;

import java.io.Serializable;

public class BreweryBean implements Serializable{
	
	/*
	 * 酒蔵ID
	 */
	private String breweryId;
	
	/*
	 * 酒蔵の名前
	 */
	private String breweryName;
	
	/*
	 * 写真のURL
	 */
	private String bImgPath;
	
	/*
	 * 緯度
	 */
	private int latitude;
	
	/*
	 * 経度
	 */
	private int longitude;
	
	/*
	 * 住所
	 */
	private String address;
	
	/*
	 *予約ができるかどうか
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


	public String getBImgPath() {
		return bImgPath;
	}


	public void setBImgPath(String bImgPath) {
		this.bImgPath = bImgPath;
	}


	public int getLatitude() {
		return latitude;
	}


	public void setLatitude(int lattitude) {
		this.latitude = lattitude;
	}


	public int getLongitude() {
		return longitude;
	}


	public void setLongitude(int longitude) {
		this.longitude = longitude;
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
