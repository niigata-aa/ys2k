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
 * Servlet implementation class CommentListServlet
 */
@WebServlet("/CommentList")
public class CommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentListServlet() {
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
		List<CommentBean> commentList = null;
		List<UserBean> userList = null;
		String myName = null;
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		String userId = request.getParameter("userId");
		request.setAttribute("breweryId", breweryId);
		request.setAttribute("userId", userId);
		
		// DAOの生成
		CommentDAO dao = new CommentDAO();
		UserDAO dao2 = new UserDAO();
		
		try {
			// DAOの利用
			 commentList= dao.selectComment(breweryId);
			 userList= dao2.selectNickname(breweryId);
			 myName= dao2.selectMyNickname(userId);
			// リクエストスコープへの属性の設定
			request.setAttribute("commentList", commentList);
			request.setAttribute("userList", userList);
			request.setAttribute("myName", myName);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("comment.jsp");
		rd.forward(request, response);
	}

}
