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
import model.dao.CommentDAO;
import model.dao.SakeDAO;
import model.dao.UserDAO;
import model.entity.BreweryBean;
import model.entity.CommentBean;
import model.entity.SakeBean;
import model.entity.UserBean;

/**
 * Servlet implementation class BreweryDetailServlet
 */
@WebServlet("/BreweryDetail")
public class BreweryDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("brewid"));
		
		List<BreweryBean> brewList = null;
		List<SakeBean> sakeList = null;
		List<CommentBean> commentList = null;
		List<UserBean> userList = null;
		
		BreweryDAO brew = new BreweryDAO();
		SakeDAO sake = new SakeDAO();
		CommentDAO dao = new CommentDAO();
		UserDAO dao2 = new UserDAO();
		
		try {
			// DAOの利用
			 brewList = brew.breweryDetail(id);
			 sakeList = sake.selectBrew(id);
			 commentList= dao.selectComment(id);
			 userList= dao2.selectNickname(id);

			// リクエストスコープへの属性の設定
			request.setAttribute("brewList", brewList);
			request.setAttribute("sakeList",sakeList);
			request.setAttribute("commentList", commentList);
			request.setAttribute("userList", userList);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("detail.jsp");
		rd.forward(request, response);
		
	}

}
