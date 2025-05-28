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
import model.entity.UserBean;

/**
 * Servlet implementation class UserRegistConfirmServlet
 */
@WebServlet("/UserRegistConfirm")
public class UserRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistConfirmServlet() {
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
		
		//リクエストのエンコーディング方式を指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
		String userId = request.getParameter("userId");
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");	
		
		
		//成功判定に用いるフラグの設定
		boolean flag = false;
		
			
		//遷移先URLの設定
		String url = "userComplete.jsp";
		
		UserDAO dao = new UserDAO();
		
		
		try {	
			flag = dao.insert(userId, nickname, password);	
		} catch (SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		
		
		if(flag) {//成功した時
			//ビーンズの中に値を挿入する
			UserBean user = new UserBean();
			
			user.setUserId(userId);
			user.setNickname(nickname);
			
			//リクエストスコープへの属性の設定
			request.setAttribute("user",user);
		
		}else {//失敗した時
			
			url = "failure.jsp";
			//リクエストスコープへの属性の設定
			request.setAttribute("url","userRegistration.jsp");
			
		}

		//リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
		
	}

}
