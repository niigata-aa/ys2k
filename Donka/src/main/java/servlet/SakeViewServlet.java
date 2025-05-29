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

import model.dao.SakeDAO;
import model.entity.SakeBean;

/**
 * Servlet implementation class SakeViewServlet
 */
@WebServlet("/SakeViewServlet")
public class SakeViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<SakeBean> sakeList=null;
		
		
		SakeDAO dao=new SakeDAO();
		
		try {
			sakeList=dao.selectAll();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("breweryList", sakeList);
		
		RequestDispatcher rd=request.getRequestDispatcher("sakeList.jsp");
		rd.forward(request, response);
	}
	

}
