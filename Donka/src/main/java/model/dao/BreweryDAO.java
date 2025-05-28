package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.entity.BreweryBean;

public class BreweryDAO {

	
	
	public List<BreweryBean> selectAll() throws SQLException, ClassNotFoundException {
		
		List<BreweryBean> breweryList = new ArrayList<BreweryBean>();
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("SELECT * FROM m_brewery")) {
			
			while (res.next()) {
				int breweryId = res.getInt("brewery_id"); //酒蔵ID
				String breweryName = res.getString("brewery_name"); //酒蔵の名前
				String bImgPath = res.getString("b_img_path"); //酒蔵の写真のパス
				Double latitude = res.getDouble("latitude"); //酒蔵の位置の緯度
				Double longitude = res.getDouble("longitude"); //酒蔵の位置の経度
				String address = res.getString("address"); //酒蔵の住所
				Date updateDay = res.getDate("update_day"); //データの更新日時
				boolean reservationFlag = res.getBoolean("reservation_flag"); //酒蔵の予約可否
				String reservationPath = res.getString("reservation_path"); //酒蔵に予約する際のURL
				String breweryExplanation = res.getString("brewery_explanation"); //酒蔵の説明
				int areaId = res.getInt("area_id"); //酒蔵がある地区
				
				BreweryBean brewery = new BreweryBean();
				
				brewery.setBreweryId(breweryId);
				brewery.setBreweryName(breweryName);
				brewery.setBImgPath(bImgPath);
				brewery.setBImgPath(breweryName);
				brewery.setLatitude(latitude);
				brewery.setLongitude(longitude);
				brewery.setAddress(address);
				brewery.setUpdateDay(updateDay);
				brewery.setReservationFlag(reservationFlag);
				brewery.setReservationPath(reservationPath);
				brewery.setBreweryExplanation(breweryExplanation);
				brewery.setAreaId(areaId);
				
				breweryList.add(brewery);
			}
		}
		return breweryList;
	}
	
    public int insert(BreweryBean brewery) throws SQLException, ClassNotFoundException {
    	
        int processingNumber = 0;

        String sql = "INSERT INTO m_brewery (brewary_name, b_img_path, latitude, longitude, address, brewery_explanation, reservation_flag, reservation_path, area_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, brewery.getBreweryName());
            pstmt.setString(2, brewery.getBImgPath());
            pstmt.setDouble(3, brewery.getLatitude());
            pstmt.setDouble(4, brewery.getLongitude());
            pstmt.setString(5, brewery.getAddress());
            pstmt.setString(6, brewery.getBreweryExplanation());
            pstmt.setBoolean(7,  brewery.getReservationFlag());
            pstmt.setString(8,  brewery.getReservationPath());
            pstmt.setInt(9, brewery.getAreaId());
            
			processingNumber = pstmt.executeUpdate();

            return processingNumber;
        }
    }
    
    public int update(BreweryBean brewery) throws SQLException, ClassNotFoundException {
    	
        int processingNumber = 0;
        
        String sql = "UPDATE m_brewery SET brewery_explanation = ? , latitude = ?, longitude = ?, address = ? , area_id = ? , b_img_path = ? , reservation_flag = ?, reservation_path = ?"
        		   + "WHERE brewery_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, brewery.getBreweryExplanation());
            pstmt.setDouble(2, brewery.getLatitude());
            pstmt.setDouble(3, brewery.getLongitude());
            pstmt.setString(4, brewery.getAddress());
            pstmt.setInt(5, brewery.getAreaId());
            pstmt.setString(6, brewery.getBImgPath());
            pstmt.setBoolean(7, brewery.getReservationFlag());
            pstmt.setString(8, brewery.getReservationPath());
            pstmt.setInt(9, brewery.getBreweryId());
            
			processingNumber = pstmt.executeUpdate();

            return processingNumber;
        }
    }
}