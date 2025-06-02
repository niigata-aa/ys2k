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
 * Servlet implementation class SakeListServlet
 */
@WebServlet("/SakeList")
public class SakeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeListServlet() {
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
		//遷移先の設定
		String url = "adminSakeList.jsp"; 
		
		//リクエストスコープに入れる値の設定
		List<SakeBean> sakeList = null;
		
		//DAOで使うオブジェクトの生成
		SakeDAO Sdao = new SakeDAO();
		
		//DAOの実行
		try {
			sakeList = Sdao.selectAll();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//リクエストパラメータに値を設定する
		request.setAttribute("sakeList", sakeList);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
		
	}

}
