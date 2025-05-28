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
 * Servlet implementation class BreweryRegistConfirmServlet
 */
@WebServlet("/BreweryRegistConfirm")
public class BreweryRegistConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryRegistConfirmServlet() {
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
		int breweryId = Integer.parseInt(request.getParameter("breweryId"));
		String breweryName = request.getParameter("breweryName");
		String bImgPath = request.getParameter("bImgPath");
		String explanation = request.getParameter("explanation");
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));
		String address = request.getParameter("address");
		int areaId = Integer.parseInt(request.getParameter("areaId"));
		Boolean reserveFlag = Boolean.parseBoolean(request.getParameter("reserveFlag"));
		String reservePath = request.getParameter("reservePath");
		
		String url = null;
		
		BreweryBean brewery = new BreweryBean();
		brewery.setBreweryId(breweryId);
		brewery.setBreweryName(breweryName);
		brewery.setBImgPath(bImgPath);
		brewery.setBreweryExplanation(explanation);
		brewery.setLatitude(latitude);
		brewery.setLongitude(longitude);
		brewery.setAddress(address);
		brewery.setAreaId(areaId);
		brewery.setReservationFlag(reserveFlag);
		brewery.setReservationPath(reservePath);
		
		try {
			
			BreweryDAO dao = new BreweryDAO();
			
			/*
			dao.insert(brewery);	
			
			url = "adminComplete.jsp";
			*/
			
			if (dao.insert(brewery)!=0) {	//update_dayのNOT NULLを外せば動きます
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
