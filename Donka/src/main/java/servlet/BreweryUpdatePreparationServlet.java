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

import model.dao.BreweryDAO;
import model.entity.BreweryBean;

/**
 * Servlet implementation class BreweryUpdatePreparationServlet
 */
@WebServlet("/BreweryUpdatePreparation")
public class BreweryUpdatePreparationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryUpdatePreparationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doPostで処理を統一
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//遷移先の設定
		String url = "breweryUpdate.jsp"; 
		
		List<BreweryBean> breweryList = null; // 全ての酒蔵（セレクトボックス用）
		BreweryBean selectedBrewery = null; // 更新対象の酒蔵の情報

		BreweryDAO dao = new BreweryDAO();
		
		// リクエストパラメータから酒蔵IDを取得
		String breweryIdParam = request.getParameter("breweryId");
		int breweryId = 0;
		if (breweryIdParam != null && !breweryIdParam.isEmpty()) {
		    try {
		        breweryId = Integer.parseInt(breweryIdParam);
		    } catch (NumberFormatException e) {
		        // 数値変換エラーの場合、エラーハンドリング
		        e.printStackTrace();
		        url = "adminFailure.jsp"; 
		        request.setAttribute("cause", "酒蔵IDが不正です。");
		        RequestDispatcher rd = request.getRequestDispatcher(url);
		        rd.forward(request, response);
		        return;
		    }
		}
		
		//DAOの実行
		try {
			breweryList = dao.selectAll(); // 全ての酒蔵情報を取得
            if (breweryId != 0) {
                selectedBrewery = dao.selectById(breweryId); // BreweryDAOにselectByIdメソッドが必要です
            }
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			url = "adminFailure.jsp"; 
			request.setAttribute("cause", "データベース処理中にエラーが発生しました。");
		}
		
		//リクエストパラメータに値を設定する
		request.setAttribute("breweryList", breweryList); // 全ての酒蔵
		request.setAttribute("selectedBrewery", selectedBrewery); // 更新対象の酒蔵の情報
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}