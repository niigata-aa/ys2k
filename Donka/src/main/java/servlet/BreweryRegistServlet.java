package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.BreweryBean;

/**
 * Servlet implementation class BreweryRegistServlet
 */
@WebServlet("/BreweryRegist")
public class BreweryRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreweryRegistServlet() {
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
		// TODO Auto-generated method stub
		
		//リクエストのエンコーディングを指定
		request.setCharacterEncoding("UTF-8");
		
		//リクエストパラメータの取得
		
		String breweryId          = "1234";
		String breweryName        = request.getParameter("breweryName");
		String bImgPath           = request.getParameter("bImgPath");
		String breweryExplanation = request.getParameter("breweryExplanation");
		String latitude           = request.getParameter("latitude");
		String longitude          = request.getParameter("longitude");
		String areaId             = request.getParameter("areaId");
		String address            = request.getParameter("address");
		String reservetionFlag    = request.getParameter("reservetionFlag");
		String reservationPath    = request.getParameter("reservetionPath");
		

		int iBreweryId = 0;
		double dLatitude = 0;
		double dLongitude = 0;
		int iAreaId = 0;
		boolean bReservetionFlag = false;
		
		if(reservetionFlag.equals("on")){
			bReservetionFlag = true; 
		}
		
		
		System.out.println(bReservetionFlag);
		
		
		//リクエストパラメータの型を合わせる
		try {
			
			iBreweryId = Integer.parseInt(breweryId);
			dLatitude = Double.parseDouble(latitude);
			dLongitude = Double.parseDouble(longitude);
			iAreaId = Integer.parseInt(areaId);
			
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			
		}
		
		//遷移先URLの設定
		String url = "breweryRegistrationConfirm.jsp";
		
		//リクエストスコープへの属性の設定
		BreweryBean brewery = new BreweryBean();
		
		brewery.setBreweryId(iBreweryId);
		brewery.setBreweryName(breweryName);
		brewery.setbImgPath(bImgPath);
		brewery.setBreweryExplanation(breweryExplanation);
		brewery.setLatitude(dLatitude);
		brewery.setLongitude(dLongitude);
		brewery.setAreaId(iAreaId);
		brewery.setAddress(address);
		brewery.setReservationFlag(bReservetionFlag);
		brewery.setReservationPath(reservationPath);
		
		request.setAttribute("brewery", brewery);
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request,response);
	} 

}
