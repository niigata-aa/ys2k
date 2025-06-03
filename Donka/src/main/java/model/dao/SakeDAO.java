package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
				sake.setsImgPath(sImgPath);
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

		String sql = "insert into m_sake(sake_name,s_img_path,alc,f_drink,taste,brewery_id,sake_explanation) values(?,?,?,?,?,?,?)";

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setString(1,sake.getSakeName());
			pstmt.setString(2,sake.getsImgPath());
			pstmt.setDouble(3, sake.getAlc());
			pstmt.setString(4,sake.getfDrink());
			pstmt.setString(5,sake.getTaste());
			pstmt.setInt(6,sake.getBreweryId());
			pstmt.setString(7,sake.getSakeExplanation());

			processingNumber = pstmt.executeUpdate();
		}

		return processingNumber;
	}

	public int update(SakeBean sake) throws SQLException, ClassNotFoundException {

		int processingNumber = 0;

		String sql = "update m_sake set sake_name=?,s_img_path=?,alc=?,f_drink=?,taste=?,brewery_id=?,sake_explanation=? where sake_id=?";

		try(Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){

			pstmt.setString(1,sake.getSakeName());
			pstmt.setString(2,sake.getsImgPath());
			pstmt.setDouble(3, sake.getAlc());
			pstmt.setString(4,sake.getfDrink());
			pstmt.setString(5,sake.getTaste());
			pstmt.setInt(6,sake.getBreweryId());
			pstmt.setString(7,sake.getSakeExplanation());

			pstmt.setInt(8,sake.getSakeId());

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

	public List<SakeBean> searchSakes(String sakeName, Set<String> alcRanges, String taste, String fDrink)throws SQLException, ClassNotFoundException {

		ResultSet rs = null;
		List<SakeBean> sakeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		List<Object> params = new ArrayList<>(); 

		try 
		(Connection con = ConnectionManager.getConnection()){// データベース接続を取得

			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT s.sake_id, s.sake_name, s.s_img_path, s.alc, s.f_drink, s.taste, s.brewery_id, s.update_day, s.sake_explanation, b.brewery_name ");
			sqlBuilder.append("FROM m_sake s ");
			sqlBuilder.append("LEFT JOIN m_brewery b ON s.brewery_id = b.brewery_id ");
			sqlBuilder.append("WHERE 1=1 ");// 常にtrueとなる条件から始める (後続のAND条件を付けやすくするため

			// 1. 酒の名前の一部検索 (sake_nameはVARCHAR型)
			if (sakeName != null && !sakeName.trim().isEmpty()) {
				sqlBuilder.append("AND s.sake_name LIKE ? ");
				params.add("%" + sakeName.trim() + "%");
			}

			// 2. アルコール度数 (度数) 検索 (alcはDOUBLE型) (OR条件)
			if (alcRanges != null && !alcRanges.isEmpty()) {
				sqlBuilder.append("AND (");
				boolean firstAlcCondition = true;
				for (String range : alcRanges) {
					if (!firstAlcCondition) {
						sqlBuilder.append(" OR "); // 2つ目以降の条件の前にはORを追加
					}
					if ("~12".equals(range)) { // ~12% (alc <= 12.0)
						sqlBuilder.append("s.alc <= ? ");
						params.add(12.0); // DOUBLE型の値をパラメータとして追加
					} else if ("13-15".equals(range)) { // 13%~15% (alc >= 13.0 AND alc <= 15.0)
						sqlBuilder.append("(s.alc >= ? AND s.alc <= ?) ");
						params.add(13.0); // DOUBLE型の値をパラメータとして追加
						params.add(15.0); // DOUBLE型の値をパラメータとして追加
					} else if ("16~".equals(range)) { // 16%~ (alc >= 16.0)
						sqlBuilder.append("s.alc >= ? ");
						params.add(16.0); // DOUBLE型の値をパラメータとして追加
					}
					firstAlcCondition = false;
				}
				sqlBuilder.append(") ");
			}

			// 3. 味わい (tasteはVARCHAR型)
			if (taste != null && !taste.trim().isEmpty()) {
				sqlBuilder.append("AND s.taste = ? ");
				params.add(taste.trim()); // VARCHAR型の値をパラメータとして追加
			}

			// 4. 飲み方 (f_drinkはVARCHAR型)
			if (fDrink != null && !fDrink.trim().isEmpty()) {
				sqlBuilder.append("AND s.f_drink = ? ");
				params.add(fDrink.trim()); // VARCHAR型の値をパラメータとして追加
			}
			sqlBuilder.append("ORDER BY s.sake_id"); // 検索結果を酒IDでソート

			pstmt = con.prepareStatement(sqlBuilder.toString()); // 構築したSQLでPreparedStatementを生成

			// パラメータを設定: List<Object> に追加されたパラメータの型をチェックし、適切なsetterメソッドを使用
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				if (param instanceof String) {
					pstmt.setString(i + 1, (String) param);
				} else if (param instanceof Double) {
					pstmt.setDouble(i + 1, (Double) param);
				} else if (param instanceof Integer) {
					pstmt.setInt(i + 1, (Integer) param);
				}
				// 必要に応じて他の型（Timestampなど）が増える場合はここに追加
			}

			rs = pstmt.executeQuery(); // SQLを実行し、結果セットを取得

			while (rs.next()) { // 結果セットを1行ずつ処理
				SakeBean sake = new SakeBean(); // 新しいSakeオブジェクトを生成し、DBのデータをセット
				sake.setSakeId(rs.getInt("sake_id")); // INT型
				sake.setSakeName(rs.getString("sake_name")); // VARCHAR型
				sake.setsImgPath(rs.getString("s_img_path")); // VARCHAR型
				sake.setAlc(rs.getDouble("alc")); // DOUBLE型
				sake.setfDrink(rs.getString("f_drink")); // VARCHAR型
				sake.setTaste(rs.getString("taste")); // VARCHAR型
				sake.setBreweryId(rs.getInt("brewery_id")); // INT型
				sake.setSakeExplanation(rs.getString("sake_explanation")); // VARCHAR型
				sakeList.add(sake); // リストに追加
			}

		}
		return sakeList; // 取得した酒リストを返す
	}
	public List<SakeBean> selectBrew(int id) throws SQLException, ClassNotFoundException {

		List<SakeBean> sakeList = new ArrayList<>();

		// データベースへの接続の取得、Statementの取得、SQLステートメントの実行
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement("select * from m_sake where brewery_id=?")){

					pstmt.setInt(1,id);
					
					ResultSet res = pstmt.executeQuery();
					
					// 結果の操作
					while (res.next()) {
						String sakeName = res.getString("sake_name");
						String sImgPath = res.getString("s_img_path");
						Double alc = res.getDouble("alc");
						String fDrink = res.getString("f_drink");
						String taste = res.getString("taste");
						String sakeExplanation = res.getString("sake_explanation");

						SakeBean sake = new SakeBean();

						sake.setSakeName(sakeName);
						sake.setsImgPath(sImgPath);
						sake.setAlc(alc);
						sake.setfDrink(fDrink);
						sake.setTaste(taste);
						sake.setSakeExplanation(sakeExplanation);

						sakeList.add(sake);
					}
				}
				return sakeList;
	}

    /**
     * いいね数でソートされた酒のリストを取得する（ランキング用）
     * @return いいね数が多い順にソートされたSakeBeanのリスト
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<SakeBean> selectSakeRanking() throws SQLException, ClassNotFoundException {
        List<SakeBean> sakeList = new ArrayList<>();
        String sql = "SELECT s.sake_id, s.sake_name, s.s_img_path, s.alc, s.f_drink, s.taste, s.brewery_id, s.sake_explanation, " +
                     "COUNT(v.sake_id) AS vote_count " +
                     "FROM m_sake s " +
                     "LEFT JOIN t_vote v ON s.sake_id = v.sake_id " +
                     "GROUP BY s.sake_id, s.sake_name, s.s_img_path, s.alc, s.f_drink, s.taste, s.brewery_id, s.sake_explanation " +
                     "ORDER BY vote_count DESC, s.sake_id ASC"; // いいね数が多い順、同数の場合はsake_id順

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                SakeBean sake = new SakeBean();
                sake.setSakeId(res.getInt("sake_id"));
                sake.setSakeName(res.getString("sake_name"));
                sake.setsImgPath(res.getString("s_img_path"));
                sake.setAlc(res.getDouble("alc"));
                sake.setfDrink(res.getString("f_drink"));
                sake.setTaste(res.getString("taste"));
                sake.setBreweryId(res.getInt("brewery_id"));
                sake.setSakeExplanation(res.getString("sake_explanation"));
                sake.setVoteCount(res.getInt("vote_count")); // いいね数をSakeBeanにセット

                sakeList.add(sake);
            }
        }
        return sakeList;
    }
    
    public SakeBean selectById(int sakeId) throws SQLException, ClassNotFoundException {
        SakeBean sake = null;
        String sql = "SELECT sake_id, sake_name, s_img_path, alc, f_drink, taste, brewery_id, sake_explanation FROM m_sake WHERE sake_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, sakeId);
            try (ResultSet res = pstmt.executeQuery()) {
                if (res.next()) {
                    sake = new SakeBean();
                    sake.setSakeId(res.getInt("sake_id"));
                    sake.setSakeName(res.getString("sake_name"));
                    sake.setsImgPath(res.getString("s_img_path"));
                    sake.setAlc(res.getDouble("alc"));
                    sake.setfDrink(res.getString("f_drink"));
                    sake.setTaste(res.getString("taste"));
                    sake.setBreweryId(res.getInt("brewery_id"));
                    sake.setSakeExplanation(res.getString("sake_explanation"));
                }
            }
        }
        return sake;
    }
}