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

import model.dao.BreweryDAO;
import model.entity.BreweryBean;

/**
 * Servlet implementation class SakeRegistPreparationServlet
 */
@WebServlet("/SakeRegistPreparation")
public class SakeRegistPreparationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SakeRegistPreparationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List<BreweryBean> breweryList = null;
		BreweryDAO dao = new BreweryDAO();
		
		String url = "sakeRegistration.jsp"; 
		
		try {
			breweryList = dao.selectAll();
		}catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("breweryList", breweryList);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
