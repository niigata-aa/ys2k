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
import model.entity.CommentBean;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/CommentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
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
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		
		request.setAttribute("breweryId", breweryId);
		request.setAttribute("commentId", commentId);
		
		// DAOの生成
		CommentDAO dao = new CommentDAO();
		
		try {
			// DAOの利用
			commentList = dao.selectConfirmComment(commentId, breweryId);
			// リクエストスコープへの属性の設定
			request.setAttribute("commentList", commentList);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("deleteCommentConfirm.jsp");
		rd.forward(request, response);
	}

}
