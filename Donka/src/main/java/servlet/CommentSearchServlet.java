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

import model.dao.CommentDAO;
import model.dao.UserDAO;
import model.entity.CommentBean;
import model.entity.UserBean;

/**
 * Servlet implementation class CommentSearchServlet
 */
@WebServlet("/CommentSearch")
public class CommentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentSearchServlet() {
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
		String url="commentDelete2.jsp";
		
		List<CommentBean> commentList = null;
		List<UserBean> userList = null;
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		request.setAttribute("breweryId", breweryId);
		
		// DAOの生成
		CommentDAO dao = new CommentDAO();
		UserDAO dao2 = new UserDAO();
		
		try {
			// DAOの利用
			 commentList= dao.selectComment(breweryId);
			 userList= dao2.selectNickname(breweryId);
			// リクエストスコープへの属性の設定
			request.setAttribute("commentList", commentList);
			request.setAttribute("userList", userList);
			

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			url="adminFailure.jsp";
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
