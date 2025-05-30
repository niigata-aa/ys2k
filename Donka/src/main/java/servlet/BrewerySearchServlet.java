package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class BrewerySearchServlet
 */
@WebServlet("/BrewerySearch")
public class BrewerySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrewerySearchServlet() {
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
	/* 一つしか検索できない
		List<BreweryBean> breweryList = null; //値を初期化して検索結果だけ入れる
		
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		
		BreweryDAO dao = new BreweryDAO();

		try {
			breweryList = dao.selectArea(areaId);
			request.setAttribute("breweryList", breweryList);
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("breweryList", breweryList);
		
		RequestDispatcher rd=request.getRequestDispatcher("breweryList.jsp");
		rd.forward(request, response);
	*/
		
		//そもそも配列にする
		//getParameterだと最初の値だけ取得、getParameterValuesだと値を全て取得できる
		String[] areaIds = request.getParameterValues("areaId");
		String errorMsg = null; // エラーメッセージを格納する変数
		
		List<Integer> areaIdList = new ArrayList<>(); //文字列配列を整数リストに変換
		
		//何もなかった場合は変換しない
		if (areaIds != null) { 
	        for (String ids : areaIds) {
	            areaIdList.add(Integer.parseInt(ids));
	        }
	    }

	    List<BreweryBean> breweryList = null;
		BreweryDAO dao = new BreweryDAO();
		
	    try {
	        breweryList = dao.findByAreaIds(areaIdList);
	        request.setAttribute("breweryList", breweryList);
	    } catch (SQLException | ClassNotFoundException e) {
			errorMsg = "酒の検索中にデータベースエラーが発生しました: " + e.getMessage();
			e.printStackTrace();
			request.setAttribute("errorMsg", errorMsg);
	    }

	    RequestDispatcher rd = request.getRequestDispatcher("breweryList.jsp");
	    rd.forward(request, response);
	}

}
