package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.SakeBean;

public class SakeDAO {
	
	public List<SakeBean> selectAll() throws SQLException, ClassNotFoundException {

		List<SakeBean> sakeList = new ArrayList<>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery("select * from m_sake")) {

			// 結果の操作
			while (res.next()) {
				int sakeId = res.getInt("sake_id");
				String sakeName = res.getString("sake_name");
				String sImgPath = res.getString("s_img_path");
				Double alc = res.getDouble("alc");
				String fDrink = res.getString("f_drink");
				String taste = res.getString("taste");
				int breweryId = res.getInt("brewery_id");
				String sakeExplanation = res.getString("sake_explanation");

				SakeBean sake = new SakeBean();
				
				sake.setSakeId(sakeId);
				sake.setSakeName(sakeName);
				sake.setSImgPath(sImgPath);
				sake.setAlc(alc);
				sake.setfDrink(fDrink);
				sake.setTaste(taste);
				sake.setBreweryId(breweryId);
				sake.setSakeExplanation(sakeExplanation);
				
				sakeList.add(sake);
			}
		}
		return sakeList;

	}
	
	public int insert(SakeBean sake) throws SQLException, ClassNotFoundException {
		
		int processingNumber = 0;
		
		String sql = "insert into m_sakem_sake(sake_id,sake_name,s_img_path,alc,f_drink,taste,brewery_id,sake_explanation) values(?,?,?,?,?,?,?,?)";
		
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			pstmt.setInt(1,sake.getSakeId());
			pstmt.setString(2,sake.getSakeName());
			pstmt.setString(3,sake.getSImgPath());
			pstmt.setDouble(4, sake.getAlc());
			pstmt.setString(5,sake.getfDrink());
			pstmt.setString(6,sake.getTaste());
			pstmt.setInt(7,sake.getBreweryId());
			pstmt.setString(8,sake.getSakeExplanation());
			
			processingNumber = pstmt.executeUpdate();
		}
		
		return processingNumber;
	}
	
	public int update(SakeBean sake) throws SQLException, ClassNotFoundException {
		
		int processingNumber = 0;
		
		String sql = "update m_sake set sake_name=?,s_img_path=?,alc=?,f_drink=?,taste=?,brewery_id=?,sake_explanation=? where sake_id=?";
		
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql)){
			
			
			pstmt.setInt(1,sake.getSakeId());
			pstmt.setString(2,sake.getSakeName());
			pstmt.setString(3,sake.getSImgPath());
			pstmt.setDouble(4, sake.getAlc());
			pstmt.setString(5,sake.getfDrink());
			pstmt.setString(6,sake.getTaste());
			pstmt.setInt(7,sake.getBreweryId());
			pstmt.setString(8,sake.getSakeExplanation());
			
			processingNumber = pstmt.executeUpdate();
		}
		
		return processingNumber;
	}
	
	public int delete(String sakeName) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM m_sake WHERE sake_name = ?";
        int affectedRows = 0;

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, sakeName);
            affectedRows = pstmt.executeUpdate();
        }
        return affectedRows;
    }
}
