package model.entity;
import java.io.Serializable;
import java.util.Date;

public class BreweryBean implements Serializable{
	
	/*
	 * 酒蔵ID
	 */
	private int breweryId;
	
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
	private double latitude;
	
	/*
	 * 経度
	 */
	private double longitude;
	
	/*
	 * 住所
	 */
	private String address;
	
	/*
	 * 更新日時
	 */
	private Date updateDay;
	
	/*
	 *予約ができるかどうかのフラグ
	 */
	private Boolean reservationFlag;
	
	/*
	 * 予約先のURL
	 */
	private String reservationPath;
	
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


	public int getBreweryId() {
		return breweryId;
	}


	public void setBreweryId(int breweryId) {
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


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double lattitude) {
		this.latitude = lattitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getUpdateDay() {
		return updateDay;
	}


	public void setUpdateDay(Date updateDay) {
		this.updateDay = updateDay;
	}

	public Boolean getReservationFlag() {
		return reservationFlag;
	}


	public void setReservationFlag(Boolean reservationFlag) {
		this.reservationFlag = reservationFlag;
	}

	public String getReservationPath() {
		return reservationPath;
	}


	public void setReservationPath(String reservationPath) {
		this.reservationPath = reservationPath;
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
