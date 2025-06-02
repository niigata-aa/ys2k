package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; // HttpSessionをインポートします

import model.dao.SakeDAO;
import model.dao.VoteDAO; // VoteDAOをインポートします
import model.entity.SakeBean;
import model.entity.UserBean; // UserBeanをインポートします

/**
 * Servlet implementation class SakeViewServlet
 */
@WebServlet("/SakeViewServlet")
public class SakeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<SakeBean> sakeList = null;
		
		SakeDAO sakeDAO = new SakeDAO();
		VoteDAO voteDAO = new VoteDAO(); // VoteDAOのインスタンスを生成

		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user"); // セッションからUserBeanを取得
		String userId = (user != null) ? user.getUserId() : null; // ユーザーIDをセット（ログインしていなければnull）
		
		try {
			sakeList = sakeDAO.selectAll(); // すべての酒のリストを取得

			// 各酒のいいね数と、ログインユーザーがいいね済みかどうかを設定
			if (sakeList != null) {
				for (SakeBean sake : sakeList) {
					sake.setVoteCount(voteDAO.getVoteCount(sake.getSakeId())); // 各酒のいいね数を設定
					if (userId != null) {
						// ユーザーがログインしている場合のみ、その酒にいいね済みかを確認
						sake.setVotedByUser(voteDAO.hasVoted(userId, sake.getSakeId())); //
					} else {
						sake.setVotedByUser(false); // ログインしていない場合はfalse
					}
				}
			}

		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// エラー発生時は、JSPにエラーメッセージを渡すなどの処理を検討
		}
		
		request.setAttribute("sakeList", sakeList); // 更新されたsakeListをリクエストスコープにセット
		
		RequestDispatcher rd=request.getRequestDispatcher("sakeList.jsp");
		rd.forward(request, response);
	}
}