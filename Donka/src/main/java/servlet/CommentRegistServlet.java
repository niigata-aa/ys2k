package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;

/**
 * Servlet implementation class CommentRegistServlet
 */
@WebServlet("/CommentRegist")
public class CommentRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentRegistServlet() {
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
		String url="commentConfirm.jsp";
		
		// TODO Auto-generated method stub
		String myName = null;
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));

		request.setAttribute("content", content);
		request.setAttribute("userId", userId);
		request.setAttribute("breweryId", breweryId);
		
		
		
		// DAOの生成
		UserDAO dao = new UserDAO();
		
		try {
			// DAOの利用
			myName= dao.selectMyNickname(userId);
			// リクエストスコープへの属性の設定
			request.setAttribute("myName", myName);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
			url="failure.jsp";
			request.setAttribute("url","home.jsp");
		}
		
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
