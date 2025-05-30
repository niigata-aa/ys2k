package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.SakeDAO;
import model.entity.SakeBean;

/**
 * Servlet implementation class SakeSearchServlet
 */
@WebServlet("/SakeSearch")
public class SakeSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SakeSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8"); // リクエストの文字エンコーディングをUTF-8に設定

		SakeDAO sakeDAO = new SakeDAO(); // SakeDAOのインスタンスを生成

		List<SakeBean> sakeList = null; // 最終的に表示する酒のリスト

		String errorMsg = null; // エラーメッセージを格納する変数

		// --- 検索条件の取得と加工 ---
		String searchSakeName = request.getParameter("searchSakeName");
		if (searchSakeName != null) {
			searchSakeName = searchSakeName.trim();
			if (searchSakeName.isEmpty()) {
				searchSakeName = null;
			}
		}

		String[] alcRangesArray = request.getParameterValues("alcRange");
		Set<String> selectedAlcRanges = null;
		if (alcRangesArray != null && alcRangesArray.length > 0) {
			if (Arrays.asList(alcRangesArray).contains("all")) { // "all"が選択されたら全件
				selectedAlcRanges = null;
			} else {
				selectedAlcRanges = new HashSet<>(Arrays.asList(alcRangesArray));
			}
		}

		String selectedTaste = request.getParameter("taste");
		if (selectedTaste != null) {
			selectedTaste = selectedTaste.trim();
			if (selectedTaste.isEmpty() || "all".equals(selectedTaste)) {
				selectedTaste = null;
			}
		}

		String selectedFDrink = request.getParameter("fDrink");
		if (selectedFDrink != null) {
			selectedFDrink = selectedFDrink.trim();
			if (selectedFDrink.isEmpty() || "all".equals(selectedFDrink)) {
				selectedFDrink = null;
			}
		}

		String breweryIdParam = request.getParameter("breweryId");
		Integer selectedBreweryId = null;
		if (breweryIdParam != null && !breweryIdParam.isEmpty()) {
			try {
				int id = Integer.parseInt(breweryIdParam);
				if (id > 0) {
					selectedBreweryId = id;
				}
			} catch (NumberFormatException e) {
				System.err.println("Invalid breweryId parameter: " + breweryIdParam);
			}
		}

		// --- 検索実行ロジック ---
		// 全ての検索条件がnullであるかチェック
		boolean allConditionsNull = (searchSakeName == null &&
				selectedAlcRanges == null &&
				selectedTaste == null &&
				selectedFDrink == null &&
				selectedBreweryId == null);

		try {
			if (allConditionsNull) {
				// 全ての条件がなかった場合、全件検索を実行
				sakeList = sakeDAO.selectAll();
			} else {
				// いずれかの検索条件が指定された場合、通常の検索を実行
				sakeList = sakeDAO.searchSakes(searchSakeName, selectedAlcRanges, selectedTaste, selectedFDrink);
			}
		} catch (SQLException | ClassNotFoundException e) {
			errorMsg = "酒の検索中にデータベースエラーが発生しました: " + e.getMessage();
			e.printStackTrace();
			request.setAttribute("errorMsg", errorMsg);
		}

		// 取得したデータと検索条件をリクエストスコープにセットし、JSPに渡す
		request.setAttribute("sakeList", sakeList); // 検索結果の酒のリスト

		// フォームに再表示するための検索条件をセット
		request.setAttribute("searchSakeName", searchSakeName);
		request.setAttribute("selectedAlcRanges", selectedAlcRanges);
		request.setAttribute("selectedTaste", selectedTaste);
		request.setAttribute("selectedFDrink", selectedFDrink);
		request.setAttribute("selectedBreweryId", selectedBreweryId != null ? selectedBreweryId : 0);


		// sakeList.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("sakeList.jsp");
		dispatcher.forward(request, response);
	}
}

