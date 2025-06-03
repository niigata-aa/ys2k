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
import model.dao.SakeDAO;
import model.entity.BreweryBean;
import model.entity.SakeBean;

/**
 * Servlet implementation class SakeUpdatePreparationServlet
 */
@WebServlet("/SakeUpdatePreparation")
public class SakeUpdatePreparationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeUpdatePreparationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//dopostでaタグをpostを動かす
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//遷移先の設定
		String url = "sakeUpdate.jsp"; 
		
		//リクエストスコープに入れる値の設定
		List<BreweryBean> breweryList = null;
		List<SakeBean> sakeList = null;
		SakeBean selectedSake = null; // 更新対象の酒の情報を格納するBean

		// DAOで使うオブジェクトの生成
		BreweryDAO Bdao = new BreweryDAO();
		SakeDAO Sdao = new SakeDAO();
		
		// リクエストパラメータから酒IDを取得
		String sakeIdParam = request.getParameter("sakeId");
		int sakeId = 0;
		if (sakeIdParam != null && !sakeIdParam.isEmpty()) {
		    try {
		        sakeId = Integer.parseInt(sakeIdParam);
		    } catch (NumberFormatException e) {
		        // 数値変換エラーの場合、エラーハンドリング（例: エラーメッセージ設定、別のJSPへ遷移）
		        e.printStackTrace();
		        url = "adminFailure.jsp"; // 例: エラーページに遷移
		        request.setAttribute("cause", "酒IDが不正です。");
		        RequestDispatcher rd = request.getRequestDispatcher(url);
		        rd.forward(request, response);
		        return; // 処理を中断
		    }
		}

		try {
			breweryList = Bdao.selectAll(); // 全ての酒蔵情報を取得
			sakeList = Sdao.selectAll(); // 全ての酒情報を取得 (セレクトボックス用)

            // sakeId が渡された場合、その酒の情報を取得
            if (sakeId != 0) {
                selectedSake = Sdao.selectById(sakeId); // SakeDAOにselectByIdメソッドが必要です
            }
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			url = "adminFailure.jsp"; // 例: エラーページに遷移
			request.setAttribute("cause", "データベース処理中にエラーが発生しました。");
		}
		
		//リクエストパラメータに値を設定する
		request.setAttribute("sakeList", sakeList); // 全ての酒（セレクトボックス用）
		request.setAttribute("breweryList", breweryList); // 全ての酒蔵（セレクトボックス用）
		request.setAttribute("selectedSake", selectedSake); // 更新対象の酒の情報

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}