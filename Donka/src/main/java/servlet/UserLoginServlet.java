package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.entity.UserBean;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
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
		String password = request.getParameter("password");		
		
		//nicknameはdaoから持ってくる
		String nickname = "";
		
		//操作が成功した時のURL
		String url = "loginComplete.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		
		if(userId == null || userId.length() == 0 || password == null || password.length() == 0 ) {
			
			if(userId == null || userId.length() == 0) {
				errorLog += "利用者IDを入力して下さい。<br>";
			}
			if(password == null || password.length() == 0) {
				errorLog += "パスワードを入力して下さい。<br>";
			}
			
			//遷移先の設定
			url = "login.jsp";
			validationFlag = false;
			
			request.setAttribute("errorLog", errorLog);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
		}
		/*                   */
		
		//DAOの生成
		UserDAO dao = new UserDAO();
		
		//DAOの利用
		try {
			nickname = dao.loginCheck(userId,password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//成功、失敗した時の値の設定
		
		/*成功したとき*/
		if(nickname != null) {	
			
			
			//ビーンズの中に値を挿入する
			UserBean user = new UserBean();
			user.setUserId(userId);
			user.setNickname(nickname);
			
			//セッションスコープへの属性の設定
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			
		
		/*失敗したとき*/	
		}else { 
			
			url = "failure.jsp";
			//リクエストスコープへの属性の設定
			request.setAttribute("url","login.jsp");//aタグの中身に入るurlの値(失敗した時の遷移先を設定)
			
		}
			
		//リクエストの転送
		
		if(validationFlag) {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		}
	}

}
