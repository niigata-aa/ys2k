package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDAO;
import model.entity.CommentBean;

/**
 * Servlet implementation class CommentRegistConfirmServlet
 */
@WebServlet("/CommentRegistConfirm")
public class CommentRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentRegistConfirmServlet() {
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
		String url= "BreweryView";
		
		// リクエストオブジェクトのエンコーディング方式の指定
		request.setCharacterEncoding("UTF-8");
		
		CommentBean comment = new CommentBean();

		// リクエストパラメータの取得
		String content = request.getParameter("content");
		String userId = request.getParameter("userId");
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		
		comment.setContent(content);
        comment.setUserId(userId);
        comment.setBreweryId(breweryId);
        
  
		
		// DAOの生成
		CommentDAO dao = new CommentDAO();
		
		try {
			if(content!="") {
				// DAOの利用
				//System.out.println("aaaaaaaa");
				int count= dao.insert(comment);
			} else {
				url = "failure.jsp";
				request.setAttribute("url","home.jsp");
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
