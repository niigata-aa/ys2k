package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entity.CommentBean;

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
		// TODO Auto-generated method stub
		List<CommentBean> commentList = null;
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータの取得
		int commentId = Integer.parseInt(request.getParameter("commentId"));
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		
		HttpSession session = request.getSession();

		session.setAttribute("commentId", commentId);
		session.setAttribute("content", content);
		session.setAttribute("userId", userId);
		session.setAttribute("breweryId", breweryId);
		System.out.println(content);
		/*
		// DAOの生成
		CommentDAO dao = new CommentDAO();
		
		try {
			// DAOの利用
			 commentList= dao.selectComment(breweryId);
			// リクエストスコープへの属性の設定
			request.setAttribute("commentList", commentList);

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("commentConfirm.jsp");
		rd.forward(request, response);
	}

}
