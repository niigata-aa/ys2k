package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.entity.BreweryBean;

public class BreweryDAO {

    public int insert(BreweryBean brewery) throws SQLException, ClassNotFoundException {
    	
        int processingNumber = 0;

        String sql = "INSERT INTO m_brewery (brewary_name, b_img_path, latitude, longitude, address, brewery_explanation, reservation, area_id) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, brewery.getBreweryName());
            pstmt.setString(2, brewery.getBImgPath());
            pstmt.setDouble(3, brewery.getLatitude());
            pstmt.setDouble(4, brewery.getLongitude());
            pstmt.setString(5, brewery.getAddress());
            pstmt.setString(6, brewery.getBreweryExplanation());
            pstmt.setBoolean(7,  brewery.getReservation());
            pstmt.setInt(7, brewery.getAreaId());

            return processingNumber;
        }
    }
    
    public int update(BreweryBean brewery) throws SQLException, ClassNotFoundException {
    	
        int processingNumber = 0;
        
        String sql = "UPDATE m_brewery SET brewery_explanation = ? , latitude = ?, longitude = ?, address = ? , area_id = ? , b_img_path = ? "
        		   + "WHERE brewery_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, brewery.getBreweryExplanation());
            pstmt.setDouble(2, brewery.getLatitude());
            pstmt.setDouble(3, brewery.getLongitude());
            pstmt.setString(4, brewery.getAddress());
            pstmt.setInt(5, brewery.getAreaId());
            pstmt.setString(6, brewery.getBImgPath());
            pstmt.setString(7, brewery.getBreweryId()); //変更したい酒蔵のid

            return processingNumber;
        }
    }
    
    
}