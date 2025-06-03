package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/UserRegist")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistServlet() {
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
		
		//遷移先URLの設定
		String url = "userConfirm.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		
		if(userId == null || userId.length() == 0 
			|| nickname == null || nickname.length() == 0
			|| password == null || password.length() == 0) {
			
			if(userId == null || userId.length() == 0) {
				errorLog += "登録したい会員番号を入力して下さい。<br>";
			}
			
			if(nickname == null || nickname.length() == 0) {
				errorLog += "登録したい愛称を入力して下さい。<br>";
			}
			
			if(password == null || password.length() == 0) {
				errorLog += "登録したい合言葉を入力して下さい。<br>";
			}
			
			//遷移先の設定
			url = "userRegistration.jsp";
			validationFlag = false;
			
			request.setAttribute("errorLog", errorLog);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
		}
		
		if(!validationFlag || password.length() < 8 || password.length() > 20) {
			
			errorLog += "合言葉は8文字以上20文字以下で入力してください。<br>";
			
			//遷移先の設定
			url = "userRegistration.jsp";
			validationFlag = false;
			
			request.setAttribute("errorLog", errorLog);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
				
		}
		/*                   */
		
		//リクエストスコープへの属性の設定
		request.setAttribute("userId", userId);
		request.setAttribute("nickname",nickname);
		request.setAttribute("password",password);
		
		//リクエストの転送
		if(validationFlag) {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		}
	}

}
