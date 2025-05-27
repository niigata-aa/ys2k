package model.entity;

import java.io.Serializable;

public class SakeBean implements Serializable{
	
	/*
	 * 酒の名前
	 */
	private int sakeId;
	
	/*
	 * 酒の名前
	 */
	private String sakeName;
	
	/*
	 * 酒の写真
	 */
	private String slmgPath;
	
	/*
	 * 度数
	 */
	private int alc;
	
	/*
	 * おすすめの飲み方
	 */
	private String fDrink;
	
	/*
	 * 味わい
	 */
	private int taste;
	
	/*
	 * 酒蔵のID
	 */
	private int breweryId;
	
	/*
	 * 更新した日
	 */
	private date updateDay;
	
	/*
	 * 酒の詳細
	 */
	private String sakeExplanation;
	
	/*
	 * SakeBeanを構築
	 */
	
	public SakeBean() {

}

	public int getSakeId() {
		return sakeId;
	}

	public void setSakeId(int sakeId) {
		this.sakeId = sakeId;
	}

	public String getSakeName() {
		return sakeName;
	}

	public void setSakeName(String sakeName) {
		this.sakeName = sakeName;
	}

	public String getSlmgPath() {
		return slmgPath;
	}

	public void setSlmgPath(String slmgPath) {
		this.slmgPath = slmgPath;
	}

	public int getAlc() {
		return alc;
	}

	public void setAlc(int alc) {
		this.alc = alc;
	}

	public String getfDrink() {
		return fDrink;
	}

	public void setfDrink(String fDrink) {
		this.fDrink = fDrink;
	}

	public int getTaste() {
		return taste;
	}

	public void setTaste(int taste) {
		this.taste = taste;
	}

	public int getBreweryId() {
		return breweryId;
	}

	public void setBreweryId(int breweryId) {
		this.breweryId = breweryId;
	}

	public date getUpdateDay() {
		return updateDay;
	}

	public void setUpdateDay(date updateDay) {
		this.updateDay = updateDay;
	}

	public String getSakeExplanation() {
		return sakeExplanation;
	}

	public void setSakeExplanation(String sakeExplanation) {
		this.sakeExplanation = sakeExplanation;
	}
	
