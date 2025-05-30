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
		
		//DAOで使うオブジェクトの生成
		BreweryDAO Bdao = new BreweryDAO();
		SakeDAO Sdao = new SakeDAO();
		
		//DAOの実行
		try {
			breweryList = Bdao.selectAll();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			sakeList = Sdao.selectAll();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//リクエストパラメータに値を設定する
		request.setAttribute("sakeList", sakeList);
		request.setAttribute("breweryList", breweryList);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
