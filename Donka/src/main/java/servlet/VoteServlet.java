package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException; // この行がimportされていることを確認
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.VoteDAO;
import model.entity.UserBean;

@WebServlet("/Vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public VoteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this URL.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		
		try { 
			if (user == null) {
	            String jsonResponse = objectMapper.writeValueAsString(new VoteResponse(false, "ログインしていません。", 0, false));
	            System.out.println("DEBUG: Not logged in JSON: " + jsonResponse);
				out.print(jsonResponse);
				out.close(); 
				return;
			}
		} catch (JsonProcessingException e) { 
			e.printStackTrace();
			System.err.println("ERROR: JSON serialization failed during login check: " + e.getMessage());
			out.print("{\"success\":false,\"message\":\"サーバー内部エラー: JSON変換失敗\"}");
			out.close();
			return;
		}

		String userId = user.getUserId();
		int sakeId = Integer.parseInt(request.getParameter("sakeId"));
		String action = request.getParameter("action"); // 'vote' または 'unvote' がここに入ります

		VoteDAO voteDAO = new VoteDAO();
		int newVoteCount = 0;
		boolean votedByUser = false;
		
		try {
			if ("vote".equals(action)) {
				voteDAO.insertVote(userId, sakeId); // いいねを登録
			} else if ("unvote".equals(action)) {
				voteDAO.deleteVote(userId, sakeId); // いいねを削除
			}
			newVoteCount = voteDAO.getVoteCount(sakeId); // いいね数を再取得
			votedByUser = voteDAO.hasVoted(userId, sakeId); // ユーザーがいいね済みか再確認
			
            String jsonResponse = objectMapper.writeValueAsString(new VoteResponse(true, "Success", newVoteCount, votedByUser));
            System.out.println("DEBUG: Generated JSON: " + jsonResponse); 
            out.print(jsonResponse);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("ERROR: Database or ClassNotFound exception: " + e.getMessage());
            try { 
                String jsonResponse = objectMapper.writeValueAsString(new VoteResponse(false, "データベースエラーが発生しました。", 0, false));
                System.out.println("DEBUG: Error JSON: " + jsonResponse);
                out.print(jsonResponse);
            } catch (JsonProcessingException jsonE) { 
                jsonE.printStackTrace();
                System.err.println("ERROR: JSON serialization failed during error response: " + jsonE.getMessage());
                out.print("{\"success\":false,\"message\":\"サーバー内部エラー: JSON変換失敗\"}"); 
            }
		} catch (JsonProcessingException e) { 
			e.printStackTrace();
			System.err.println("ERROR: JSON serialization failed: " + e.getMessage());
			out.print("{\"success\":false,\"message\":\"サーバー内部エラー: JSON変換失敗\"}");
		} finally {
			out.close();
		}
	}

    private static class VoteResponse {
        public boolean success; // ObjectMapperがアクセスできるようpublic
        public String message;
        public int newVoteCount;
        public boolean votedByUser;

        public VoteResponse(boolean success, String message, int newVoteCount, boolean votedByUser) {
            this.success = success;
            this.message = message;
            this.newVoteCount = newVoteCount;
            this.votedByUser = votedByUser;
        }
    }
}