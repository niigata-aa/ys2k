package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDAO {

    /**
     * いいねを登録する
     *
     * @param userId ユーザーID
     * @param sakeId 酒ID
     * @return 処理件数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int insertVote(String userId, int sakeId) throws SQLException, ClassNotFoundException {
        int count = 0;
        String sql = "INSERT INTO t_vote (user_id, sake_id) VALUES (?, ?)";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setInt(2, sakeId);

            count = pstmt.executeUpdate();
        }
        return count;
    }

    /**
     * いいねを取り消す（削除する）
     *
     * @param userId ユーザーID
     * @param sakeId 酒ID
     * @return 処理件数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int deleteVote(String userId, int sakeId) throws SQLException, ClassNotFoundException {
        int count = 0;
        String sql = "DELETE FROM t_vote WHERE user_id = ? AND sake_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setInt(2, sakeId);

            count = pstmt.executeUpdate();
        }
        return count;
    }

    /**
     * 特定の酒のいいねの総数を取得する
     *
     * @param sakeId 酒ID
     * @return いいねの総数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int getVoteCount(int sakeId) throws SQLException, ClassNotFoundException {
        int count = 0;
        String sql = "SELECT COUNT(*) AS vote_count FROM t_vote WHERE sake_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, sakeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("vote_count");
                }
            }
        }
        return count;
    }

    /**
     * ユーザーが特定の酒にいいね済みかどうかを確認する
     *
     * @param userId ユーザーID
     * @param sakeId 酒ID
     * @return いいね済みであればtrue、そうでなければfalse
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean hasVoted(String userId, int sakeId) throws SQLException, ClassNotFoundException {
        boolean hasVoted = false;
        String sql = "SELECT COUNT(*) FROM t_vote WHERE user_id = ? AND sake_id = ?";

        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.setInt(2, sakeId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    hasVoted = rs.getInt(1) > 0;
                }
            }
        }
        return hasVoted;
    }
}