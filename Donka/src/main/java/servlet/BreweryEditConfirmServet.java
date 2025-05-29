package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.BreweryDAO;
import model.entity.BreweryBean;

/**
 * Servlet implementation class BreweryEditConfirmServet
 */
@WebServlet("/BreweryEditConfirm")
public class BreweryEditConfirmServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryEditConfirmServet() {
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

		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		String breweryName = request.getParameter("breweryName");
		String bImgPath = request.getParameter("bImgPath");
		
		String url = null;
		
		BreweryBean brewery = new BreweryBean();
		brewery.setBreweryId(breweryId);
		brewery.setBreweryName(breweryName);
		brewery.setBImgPath(bImgPath);
		
		try {
			
			BreweryDAO dao = new BreweryDAO();
			
			if (dao.update(brewery)!=0) {
				url = "adminComplete.jsp";
			} else {
				url = "adminFailure.jsp";
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			url = "adminFailure.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}
}
