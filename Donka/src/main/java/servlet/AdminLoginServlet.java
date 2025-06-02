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

import model.dao.AdminDAO;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLogin")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String adminId = request.getParameter("adminId");
		String password = request.getParameter("password");
		
		//遷移先の設定
		String url = "adminTop.jsp";
		
		/* validation Check */
		String errorLog = "";
		boolean validationFlag = true;
		
		if(adminId.length() == 0 || password.length() == 0 ) {
			
			if(adminId == null || adminId.length() == 0) {
				errorLog += "管理者IDを入力して下さい。<br>";
			}
			if(password == null || password.length() == 0) {
				errorLog += "パスワードを入力して下さい。<br>";
			}
			
			//遷移先の設定
			url = "adminLogin.jsp";
			validationFlag = false;
			
			request.setAttribute("errorLog", errorLog);
			
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
		}
		

		try {
			
			AdminDAO dao = new AdminDAO();
			
			if (dao.loginCheck(adminId,password)) {
				
				HttpSession session = request.getSession();
				
				session.setAttribute("userid", adminId);
				
				
			} else {
				url = "adminLoginFailure.jsp";
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(validationFlag) {
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request,response);
		}
	} 
		

}
