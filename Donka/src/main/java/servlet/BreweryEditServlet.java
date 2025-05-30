package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BreweryEditServlet
 */
@WebServlet("/BreweryEdit")
public class BreweryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryEditServlet() {
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

		request.setCharacterEncoding("UTF-8");
		
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		String breweryExplanation = request.getParameter("breweryExplanation");
		boolean reservationFlag = Boolean.parseBoolean(request.getParameter("reservationFlag"));
		String reservationPath = request.getParameter("reservationPath");
		
        request.setAttribute("breweryId", breweryId);
        request.setAttribute("breweryExplanation", breweryExplanation);
        request.setAttribute("reservationFlag", reservationFlag);
        request.setAttribute("reservationPath", reservationPath);
		
		String url = "breweryUpdateConfirm.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	}

}
