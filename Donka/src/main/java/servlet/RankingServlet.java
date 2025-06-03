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

import model.dao.SakeDAO;
import model.entity.SakeBean;

/**
 * Servlet implementation class RankingServlet
 */
@WebServlet("/Ranking")
public class RankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RankingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response); // GETリクエストもdoPostで処理する
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SakeBean> rankingList = null;
		SakeDAO sakeDAO = new SakeDAO();
		String url = "showRank.jsp"; // 遷移先のJSP

		try {
			// いいね数でソートされた酒のリストを取得
			rankingList = sakeDAO.selectSakeRanking(); //

			// 上位10件に絞る (もし10件以上ある場合)
			if (rankingList != null && rankingList.size() > 10) {
				rankingList = rankingList.subList(0, 10);
			}

			request.setAttribute("rankingList", rankingList);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			// エラー発生時は、エラーページに遷移させるなどの処理を検討
			url = "error.jsp"; // 例: エラーページ
			request.setAttribute("errorMessage", "ランキングの取得中にエラーが発生しました。");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}