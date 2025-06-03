package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
				//Date updateDay = res.getDate("update_day"); //データの更新日時
				boolean reservationFlag = res.getBoolean("reservation_flag"); //酒蔵の予約可否
				String reservationPath = res.getString("reservation_path"); //酒蔵に予約する際のURL
				String breweryExplanation = res.getString("brewery_explanation"); //酒蔵の説明
				int areaId = res.getInt("area_id"); //酒蔵がある地区
				

				BreweryBean brewery = new BreweryBean();

				brewery.setBreweryId(breweryId);
				brewery.setBreweryName(breweryName);
				brewery.setbImgPath(bImgPath);
				brewery.setLatitude(latitude);
				brewery.setLongitude(longitude);
				brewery.setAddress(address);
				//brewery.setUpdateDay(updateDay); //ユーザが更新日時に値を設定することは多分無い
				brewery.setReservationFlag(reservationFlag);
				brewery.setReservationPath(reservationPath);
				brewery.setBreweryExplanation(breweryExplanation);
				brewery.setAreaId(areaId);

				breweryList.add(brewery);
			}
		}
		return breweryList;
	}


	public List<BreweryBean> selectArea(int areaId) throws SQLException, ClassNotFoundException {

		List<BreweryBean> breweryList = new ArrayList<BreweryBean>();

		String sql = "select * from m_brewery where area_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setInt(1, areaId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int breweryId = res.getInt("brewery_id"); //酒蔵ID
				String breweryName = res.getString("brewery_name"); //酒蔵の名前
				String bImgPath = res.getString("b_img_path"); //酒蔵の写真のパス

				BreweryBean brewery = new BreweryBean();

				brewery.setBreweryId(breweryId);
				brewery.setBreweryName(breweryName);
				brewery.setbImgPath(bImgPath);

				breweryList.add(brewery);
			}
		}
		return breweryList;
	}

	public List<BreweryBean> findByAreaIds(List<Integer> areaIdList) throws SQLException, ClassNotFoundException {
		List<BreweryBean> breweryList = new ArrayList<>();


		//何もなかったら空のままで
		if (areaIdList == null || areaIdList.isEmpty()) {
			return breweryList;
		}

		//チェック数だけプレースホルダ作成（あんまりよくわからん）
		String placeholders = String.join(",", Collections.nCopies(areaIdList.size(), "?"));
		String sql = "SELECT * FROM m_brewery WHERE area_id IN (" + placeholders + ")";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {

			//プレースホルダにareaIdの値を入れる
			for (int i = 0; i < areaIdList.size(); i++) {
				ps.setInt(i + 1, areaIdList.get(i));
			}

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				BreweryBean brewery = new BreweryBean();
				brewery.setBreweryId(rs.getInt("brewery_id"));
				brewery.setBreweryName(rs.getString("brewery_name"));
				brewery.setbImgPath(rs.getString("b_img_path"));
				brewery.setAreaId(rs.getInt("area_id"));
				breweryList.add(brewery);
			}
		}

		return breweryList;
	}


	public int insert(BreweryBean brewery) throws SQLException, ClassNotFoundException {

		int processingNumber = 0;

		String sql = "INSERT INTO m_brewery (brewery_name, b_img_path, latitude, longitude, address, brewery_explanation, reservation_flag, reservation_path, area_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, brewery.getBreweryName());
			pstmt.setString(2, brewery.getbImgPath());
			pstmt.setDouble(3, brewery.getLatitude());
			pstmt.setDouble(4, brewery.getLongitude());
			pstmt.setString(5, brewery.getAddress());
			pstmt.setString(6, brewery.getBreweryExplanation());
			pstmt.setBoolean(7, brewery.getReservationFlag());
			pstmt.setString(8, brewery.getReservationPath());
			pstmt.setInt(9, brewery.getAreaId());

			processingNumber = pstmt.executeUpdate();


		}
		return processingNumber;
	}

	public int update(BreweryBean brewery) throws SQLException, ClassNotFoundException {

		int processingNumber = 0;

        String sql = "UPDATE m_brewery SET brewery_name = ?, brewery_explanation = ?, latitude = ?, longitude = ?, "
                + "area_id = ?, address = ?, reservation_flag = ?, reservation_path = ?, b_img_path = ? WHERE brewery_id = ?";

		//String sql = "UPDATE m_brewery SET reservation_flag = ? , reservation_path = ? , brewery_explanation = ?"
		//		+ "WHERE brewery_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			
			
            pstmt.setString(1, brewery.getBreweryName());
            pstmt.setString(2, brewery.getBreweryExplanation());
            pstmt.setDouble(3, brewery.getLatitude());
            pstmt.setDouble(4, brewery.getLongitude());
            pstmt.setInt(5, brewery.getAreaId());
            pstmt.setString(6, brewery.getAddress());
            pstmt.setBoolean(7, brewery.getReservationFlag());
            pstmt.setString(8, brewery.getReservationPath());
            pstmt.setString(9, brewery.getbImgPath());
            pstmt.setInt(10, brewery.getBreweryId());
			
			/*
			pstmt.setBoolean(1, brewery.getReservationFlag());
			pstmt.setString(2, brewery.getReservationPath());
			pstmt.setString(3, brewery.getBreweryExplanation());
			pstmt.setInt(4, brewery.getBreweryId());
			*/
			
			processingNumber = pstmt.executeUpdate();

		}
		return processingNumber;
	}

	public List<BreweryBean> breweryDetail(int breweryId) throws SQLException, ClassNotFoundException {

		List<BreweryBean> breweryList = new ArrayList<BreweryBean>();

		String sql = "select * from m_brewery where brewery_id = ?";

		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setInt(1, breweryId);

			ResultSet res = pstmt.executeQuery();

			while (res.next()) {
				int breweryId1 = res.getInt("brewery_id"); //酒蔵ID
				String breweryName = res.getString("brewery_name"); //酒蔵の名前
				String bImgPath = res.getString("b_img_path"); //酒蔵の写真のパス
				Double latitude = res.getDouble("latitude"); //酒蔵の位置の緯度
				Double longitude = res.getDouble("longitude"); //酒蔵の位置の経度
				String address = res.getString("address"); //酒蔵の住所
				//Date updateDay = res.getDate("update_day"); //データの更新日時
				boolean reservationFlag = res.getBoolean("reservation_flag"); //酒蔵の予約可否
				String reservationPath = res.getString("reservation_path"); //酒蔵に予約する際のURL
				String breweryExplanation = res.getString("brewery_explanation"); //酒蔵の説明
				int areaId = res.getInt("area_id"); //酒蔵がある地区

				BreweryBean brewery = new BreweryBean();

				brewery.setBreweryId(breweryId1);
				brewery.setBreweryName(breweryName);
				brewery.setbImgPath(bImgPath);
				brewery.setLatitude(latitude);
				brewery.setLongitude(longitude);
				brewery.setAddress(address);
				brewery.setReservationFlag(reservationFlag);
				brewery.setReservationPath(reservationPath);
				brewery.setBreweryExplanation(breweryExplanation);
				brewery.setAreaId(areaId);

				breweryList.add(brewery);
			}
		}
		return breweryList;
	}
	
	public BreweryBean selectById(int breweryId) throws SQLException, ClassNotFoundException {
        BreweryBean brewery = null;
        String sql = "SELECT brewery_id, brewery_name, b_img_path, latitude, longitude, address, brewery_explanation, reservation_flag, reservation_path, area_id FROM m_brewery WHERE brewery_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, breweryId);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    brewery = new BreweryBean();
                    brewery.setBreweryId(res.getInt("brewery_id"));
                    brewery.setBreweryName(res.getString("brewery_name"));
                    brewery.setbImgPath(res.getString("b_img_path"));
                    brewery.setLatitude(res.getDouble("latitude"));
                    brewery.setLongitude(res.getDouble("longitude"));
                    brewery.setAddress(res.getString("address"));
                    brewery.setReservationFlag(res.getBoolean("reservation_flag"));
                    brewery.setReservationPath(res.getString("reservation_path"));
                    brewery.setBreweryExplanation(res.getString("brewery_explanation"));
                    brewery.setAreaId(res.getInt("area_id"));
                }
            }
        }
        return brewery;
    }
}